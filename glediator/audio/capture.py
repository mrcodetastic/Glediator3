from __future__ import annotations

import threading

import numpy as np


class AudioCapture:
    """Background system-output loopback or microphone capture and analysis.

    Uses SoundCard's WASAPI loopback microphone on Windows for output audio;
    soundcard also maps loopback microphones on supported macOS/Linux backends.
    """

    SAMPLE_RATE = 44100
    BLOCK_SIZE = 1024

    def __init__(self) -> None:
        self._lock = threading.Lock()
        self._stop = threading.Event()
        self._thread: threading.Thread | None = None
        self._source = "System Output"
        self._failed = False
        self.level = 0.0
        self.peak = 0.0
        self.spectrum = np.zeros(128, dtype=np.float32)
        self.last_error = ""

    def set_source(self, source: str) -> None:
        source = source if source in ("System Output", "Microphone") else "System Output"
        if source == self._source:
            return
        self.stop()
        self._source = source
        self._failed = False
        self.last_error = ""

    def start(self) -> None:
        if self._failed or (self._thread and self._thread.is_alive()):
            return
        self._stop.clear()
        self._thread = threading.Thread(target=self._capture_loop, name="GlediatorAudioCapture", daemon=True)
        self._thread.start()

    def stop(self) -> None:
        thread = self._thread
        if not thread or not thread.is_alive():
            self._thread = None
            return
        self._stop.set()
        thread.join(timeout=1.5)
        if not thread.is_alive():
            self._thread = None
        with self._lock:
            self.level = 0.0
            self.peak = 0.0
            self.spectrum.fill(0)

    def snapshot(self) -> dict:
        with self._lock:
            return {"audio_level": self.level, "audio_peak": self.peak,
                    "audio_spectrum": self.spectrum.copy()}

    @staticmethod
    def analyse(samples: np.ndarray, previous_level: float = 0.0, previous_peak: float = 0.0) -> tuple[float, float, np.ndarray]:
        values = np.asarray(samples, dtype=np.float32)
        if values.ndim == 2:
            values = values.mean(axis=1)
        if values.size == 0:
            return 0.0, 0.0, np.zeros(128, dtype=np.float32)
        rms = float(np.sqrt(np.mean(values * values)))
        raw_peak = float(np.max(np.abs(values)))
        level = min(1.0, rms * 12.0)
        peak = max(min(1.0, raw_peak * 4.0), previous_peak * 0.94)
        level = max(level, previous_level * 0.72)
        window = np.hanning(values.size).astype(np.float32)
        magnitudes = np.abs(np.fft.rfft(values * window))[1:]
        if magnitudes.size:
            bins = np.linspace(0, magnitudes.size - 1, 128).astype(np.intp)
            spectrum = np.clip(magnitudes[bins] * (40.0 / max(1, values.size)), 0, 1).astype(np.float32)
        else:
            spectrum = np.zeros(128, dtype=np.float32)
        return level, peak, spectrum

    def _capture_loop(self) -> None:
        try:
            import soundcard as sc

            if self._source == "System Output":
                speaker = sc.default_speaker()
                if speaker is None:
                    raise RuntimeError("No default playback device is available")
                microphone = sc.get_microphone(id=speaker.name, include_loopback=True)
                if microphone is None:
                    raise RuntimeError(f"No loopback capture found for {speaker.name}")
            else:
                microphone = sc.default_microphone()
                if microphone is None:
                    raise RuntimeError("No default microphone is available")

            with microphone.recorder(samplerate=self.SAMPLE_RATE, blocksize=self.BLOCK_SIZE) as recorder:
                self.last_error = ""
                while not self._stop.is_set():
                    samples = recorder.record(numframes=self.BLOCK_SIZE)
                    level, peak, spectrum = self.analyse(samples, self.level, self.peak)
                    with self._lock:
                        self.level, self.peak, self.spectrum = level, peak, spectrum
        except Exception as exc:
            self.last_error = f"Audio input unavailable ({self._source}): {exc}"
            self._failed = True
            self._stop.set()
