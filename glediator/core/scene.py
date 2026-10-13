from __future__ import annotations

from dataclasses import dataclass, field

import numpy as np

from glediator.core.frame import new_frame
from glediator.core.pipeline import apply_filter, mix
from glediator.effects import (create_effect, default_effect_parameters, effect_names,
                               sanitize_effect_parameters)


@dataclass
class Layer:
    generator_name: str = "Plasma"
    enabled: bool = True
    configured: bool = True
    brightness: int = 100
    filter_name: str = "None"
    mix_mode: str = "Add"
    parameters: dict = field(default_factory=dict)


class Scene:
    """A scene owns five independently configurable generator layers."""

    def __init__(self, name: str, width: int = 64, height: int = 32) -> None:
        self.name = name
        self.width, self.height = width, height
        self.layers = [Layer(generator_name="Plasma" if i == 0 else "Black", enabled=(i == 0), configured=(i == 0)) for i in range(5)]
        self._generators = [create_effect(layer.generator_name) for layer in self.layers]
        self.active_layer = 0
        self._frame = new_frame(width, height)

    def resize(self, width: int, height: int) -> None:
        self.width, self.height = width, height
        self._frame = new_frame(width, height)

    def set_generator(self, index: int, name: str) -> None:
        if name == "Expanding Objects":
            name = "Expanding Circles"
        if name not in effect_names():
            raise ValueError(f"Unknown effect: {name}")
        self._generators[index].cleanup()
        self.layers[index].generator_name = name
        self.layers[index].configured = True
        self.layers[index].parameters = default_effect_parameters(name)
        self._generators[index] = create_effect(name)
        self.layers[index].enabled = True

    def add_layer(self, name: str) -> int | None:
        """Add an enabled effect in the first free slot, up to the five-layer limit."""
        for index, layer in enumerate(self.layers):
            if not layer.configured:
                self.set_generator(index, name)
                return index
        return None

    def remove_layer(self, index: int) -> None:
        self._generators[index].cleanup()
        self.layers[index] = Layer(generator_name="Black", enabled=False, configured=False)
        self._generators[index] = create_effect("Black")

    def move_layer(self, source: int, destination: int) -> None:
        if source == destination:
            return
        self.layers[source], self.layers[destination] = self.layers[destination], self.layers[source]
        self._generators[source], self._generators[destination] = self._generators[destination], self._generators[source]

    def render(self, time_seconds: float, audio_state: dict | None = None) -> np.ndarray:
        self._frame.fill(0)
        has_layer = False
        for layer, generator in zip(self.layers, self._generators):
            if not layer.enabled:
                continue
            image = generator.drawFrame(self.width, self.height, time_seconds, audio_state)
            image = apply_filter(image, layer.filter_name)
            if layer.brightness != 100:
                image = np.clip(image.astype(np.float32) * (layer.brightness / 100.0), 0, 255).astype(np.uint8)
            self._frame[:] = image if not has_layer else mix(self._frame, image, layer.mix_mode)
            has_layer = True
        return self._frame.copy()

    def to_dict(self) -> dict:
        return {
            "name": self.name,
            "active_layer": self.active_layer,
            "layers": [
                {"generator": layer.generator_name, "enabled": layer.enabled, "configured": layer.configured,
                 "brightness": layer.brightness, "filter": layer.filter_name,
                 "mix_mode": layer.mix_mode, "parameters": layer.parameters}
                for layer in self.layers
            ],
        }

    def restore(self, data: dict) -> None:
        self.name = str(data.get("name", self.name))
        self.active_layer = max(0, min(4, int(data.get("active_layer", 0))))
        saved_layers = data.get("layers", [])
        for index in range(min(5, len(saved_layers))):
            saved = saved_layers[index]
            if not isinstance(saved, dict):
                continue
            name = saved.get("generator", "Black")
            if name == "Expanding Objects":
                name = "Expanding Circles"
            if name not in effect_names():
                name = "Black"
            self._generators[index].cleanup()
            effect = create_effect(name)
            parameters = sanitize_effect_parameters(name, saved.get("parameters"))
            effect.configure(parameters)
            self._generators[index] = effect
            self.layers[index] = Layer(
                generator_name=name,
                enabled=bool(saved.get("enabled", index == 0)),
                configured=bool(saved.get("configured", saved.get("enabled", index == 0) or name != "Black")),
                brightness=max(0, min(100, int(saved.get("brightness", 100)))),
                filter_name=saved.get("filter", "None") if saved.get("filter") in ("None", "Invert", "Monochrome", "Threshold") else "None",
                mix_mode=saved.get("mix_mode", "Add") if saved.get("mix_mode") in ("Add", "Subtract", "Multiply", "Difference", "Screen", "Maximum") else "Add",
                parameters=parameters,
            )

    def close(self) -> None:
        for generator in self._generators:
            generator.cleanup()
