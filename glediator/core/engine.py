from __future__ import annotations

import threading
import time

import numpy as np

from glediator.audio.capture import AudioCapture
from glediator.core.config import EngineConfig
from glediator.core.fader import crossfade
from glediator.core.scene import Scene


class RenderEngine:
    """Threaded render loop. Public mutations are protected by one small lock."""

    def __init__(self, config: EngineConfig) -> None:
        config.validate()
        self.config = config
        self.left_scene = Scene("Live A", config.width, config.height)
        self.right_scene = Scene("Live B", config.width, config.height)
        self.fader = 0.5
        self.running = False
        self._lock = threading.RLock()
        self._latest: dict[str, np.ndarray] = {}
        self._thread: threading.Thread | None = None
        self.frame_count = 0
        self.fps = 0.0
        self._start_time = time.perf_counter()
        self.audio_capture = AudioCapture()
        if config.live_state:
            self.restore_state(config.live_state)

    def start(self) -> None:
        if self.running:
            return
        self.running = True
        self._thread = threading.Thread(target=self._run, name="GlediatorRender", daemon=True)
        self._thread.start()

    def stop(self) -> None:
        self.running = False
        if self._thread and self._thread is not threading.current_thread():
            self._thread.join(timeout=2.0)
        self.audio_capture.stop()
        with self._lock:
            self.left_scene.close()
            self.right_scene.close()

    def set_generator(self, side: str, layer: int, name: str) -> None:
        with self._lock:
            self._scene(side).set_generator(layer, name)

    def add_layer(self, side: str, name: str) -> int | None:
        with self._lock:
            return self._scene(side).add_layer(name)

    def remove_layer(self, side: str, layer: int) -> None:
        with self._lock:
            self._scene(side).remove_layer(layer)

    def set_layer_enabled(self, side: str, layer: int, enabled: bool) -> None:
        with self._lock:
            target = self._scene(side).layers[layer]
            if target.configured:
                target.enabled = bool(enabled)

    def move_layer(self, side: str, source: int, destination: int) -> None:
        with self._lock:
            self._scene(side).move_layer(source, destination)

    def set_fader(self, amount: float) -> None:
        with self._lock:
            self.fader = min(1.0, max(0.0, float(amount)))

    def set_matrix_size(self, width: int, height: int) -> None:
        width, height = max(8, int(width)), max(8, int(height))
        with self._lock:
            self.config.width, self.config.height = width, height
            self.left_scene.resize(width, height)
            self.right_scene.resize(width, height)

    def set_layer_options(self, side: str, layer: int, brightness: int | None = None,
                          filter_name: str | None = None, mix_mode: str | None = None) -> None:
        with self._lock:
            item = self._scene(side).layers[layer]
            if brightness is not None:
                item.brightness = min(100, max(0, int(brightness)))
            if filter_name is not None:
                item.filter_name = filter_name
            if mix_mode is not None:
                item.mix_mode = mix_mode

    def set_generator_parameters(self, side: str, layer: int, parameters: dict) -> None:
        from glediator.generators.settings import sanitize_parameters
        with self._lock:
            scene = self._scene(side)
            target = scene.layers[layer]
            target.parameters = sanitize_parameters(target.generator_name, parameters)

    def export_state(self) -> dict:
        with self._lock:
            return {"left": self.left_scene.to_dict(), "right": self.right_scene.to_dict(),
                    "fader": self.fader, "width": self.config.width, "height": self.config.height}

    def restore_state(self, state: dict) -> None:
        if not isinstance(state, dict):
            return
        with self._lock:
            width = max(8, min(512, int(state.get("width", self.config.width))))
            height = max(8, min(512, int(state.get("height", self.config.height))))
            self.config.width, self.config.height = width, height
            self.left_scene.resize(width, height)
            self.right_scene.resize(width, height)
            if isinstance(state.get("left"), dict):
                self.left_scene.restore(state["left"])
            if isinstance(state.get("right"), dict):
                self.right_scene.restore(state["right"])
            self.fader = min(1.0, max(0.0, float(state.get("fader", .5))))

    def _scene(self, side: str) -> Scene:
        if side.lower() == "left":
            return self.left_scene
        if side.lower() == "right":
            return self.right_scene
        raise ValueError("side must be 'left' or 'right'")

    def snapshot(self) -> dict[str, np.ndarray]:
        with self._lock:
            return {name: frame.copy() for name, frame in self._latest.items()}

    def _run(self) -> None:
        period = 1.0 / self.config.fps
        while self.running:
            started = time.perf_counter()
            with self._lock:
                t = started - self._start_time
                audio_layers = [layer for scene in (self.left_scene, self.right_scene)
                                for layer in scene.layers
                                if layer.configured and layer.enabled
                                and layer.generator_name in ("VU Meter", "Simple Spectrum")]
                if audio_layers:
                    self.audio_capture.set_source(audio_layers[0].parameters.get("source", "System Output"))
                    self.audio_capture.start()
                else:
                    self.audio_capture.stop()
                audio_state = self.audio_capture.snapshot()
                left = self.left_scene.render(t, audio_state)
                right = self.right_scene.render(t, audio_state)
                mixed = crossfade(left, right, self.fader)
                self._latest = {"left": left, "right": right, "mixed": mixed}
                self.frame_count += 1
                if self.frame_count % 10 == 0:
                    self.fps = 10.0 / max(started - (self._last_fps_time if hasattr(self, "_last_fps_time") else started - 0.1), 1e-6)
                    self._last_fps_time = started
            time.sleep(max(0.0, period - (time.perf_counter() - started)))
