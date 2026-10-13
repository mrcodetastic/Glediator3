from __future__ import annotations

import threading
from collections import deque

import numpy as np


class AudioCapture:
    """Background system-output loopback or microphone capture and analysis.

    Uses SoundCard's WASAPI loopback microphone on Windows for output audio;
    soundcard also maps loopback microphones on supported macOS/Linux backends.
    """

    SAMPLE_RATE = 44100
    BLOCK_SIZE = 1024
    SPECTRUM_BINS = 1024

    def __init__(self) -> None:
        self._lock = threading.Lock()
        self._stop = threading.Event()
        self._thread: threading.Thread | None = None
        self._source = "System Output"
        self._failed = False
        self._auto_sensitivity = False
        self._target_level = 0.36
        self._sensitivity_gain = 1.0
        self._level_history: deque[float] = deque(maxlen=300)
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

    def set_auto_sensitivity(self, enabled: bool, effect_sensitivity: float = 2.5) -> None:
        """Normalize input so the configured effect reaches full scale only rarely."""
        with self._lock:
            self._auto_sensitivity = bool(enabled)
            self._target_level = 0.9 / max(0.2, float(effect_sensitivity))
            if not enabled:
                self._sensitivity_gain = 1.0
                self._level_history.clear()

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

    @classmethod
    def _analyse_unscaled(cls, samples: np.ndarray) -> tuple[float, float, np.ndarray]:
        values = np.asarray(samples, dtype=np.float32)
        if values.ndim == 2:
            values = values.mean(axis=1)
        if values.size == 0:
            return 0.0, 0.0, np.zeros(cls.SPECTRUM_BINS, dtype=np.float32)
        rms = float(np.sqrt(np.mean(values * values)))
        raw_peak = float(np.max(np.abs(values)))
        window = np.hanning(values.size).astype(np.float32)
        # Zero-pad to 2048 points and retain 1024 positive-frequency bins (DC excluded).
        # This provides a stable, fixed-width spectrum for effects to compress/map.
        magnitudes = np.abs(np.fft.rfft(values * window, n=cls.SPECTRUM_BINS * 2))[1:cls.SPECTRUM_BINS + 1]
        spectrum = (magnitudes * (40.0 / max(1, values.size))).astype(np.float32)
        return rms * 12.0, raw_peak * 4.0, spectrum

    @staticmethod
    def analyse(samples: np.ndarray, previous_level: float = 0.0,
                previous_peak: float = 0.0) -> tuple[float, float, np.ndarray]:
        """Analyse a block into normalized level, peak, and 1024 spectrum bins."""
        raw_level, raw_peak, raw_spectrum = AudioCapture._analyse_unscaled(samples)
        level = max(min(1.0, raw_level), previous_level * 0.72)
        peak = max(min(1.0, raw_peak), previous_peak * 0.94)
        return level, peak, np.clip(raw_spectrum, 0, 1).astype(np.float32)

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

            analysis_window = np.zeros(self.SPECTRUM_BINS * 2, dtype=np.float32)
            with microphone.recorder(samplerate=self.SAMPLE_RATE, blocksize=self.BLOCK_SIZE) as recorder:
                self.last_error = ""
                while not self._stop.is_set():
                    samples = recorder.record(numframes=self.BLOCK_SIZE)
                    mono = np.asarray(samples, dtype=np.float32)
                    if mono.ndim == 2:
                        mono = mono.mean(axis=1)
                    if mono.size >= analysis_window.size:
                        analysis_window[:] = mono[-analysis_window.size:]
                    elif mono.size:
                        analysis_window[:-mono.size] = analysis_window[mono.size:]
                        analysis_window[-mono.size:] = mono
                    raw_level, raw_peak, raw_spectrum = self._analyse_unscaled(analysis_window)
                    with self._lock:
                        if self._auto_sensitivity and raw_level > 1e-5:
                            self._level_history.append(raw_level)
                            reference = float(np.percentile(self._level_history, 90))
                            desired_gain = float(np.clip(self._target_level / max(reference, 1e-5), 0.05, 100.0))
                            self._sensitivity_gain += (desired_gain - self._sensitivity_gain) * 0.08
                        else:
                            self._sensitivity_gain = 1.0
                        gain = self._sensitivity_gain
                        level = max(min(1.0, raw_level * gain), self.level * 0.72)
                        peak = max(min(1.0, raw_peak * gain), self.peak * 0.94)
                        spectrum = np.clip(raw_spectrum * gain, 0, 1).astype(np.float32)
                        self.level, self.peak, self.spectrum = level, peak, spectrum
        except Exception as exc:
            self.last_error = f"Audio input unavailable ({self._source}): {exc}"
            self._failed = True
            self._stop.set()
