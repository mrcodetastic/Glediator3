import numpy as np

from glediator.effects.support import ConfiguredEffect


class SpectrumAnalyserEffect(ConfiguredEffect):
    name = "Spectrum Analyser"
    description = "Configurable audio-frequency bars, averaged from a 1024-bin FFT."
    settings = {
        "sensitivity": ("float", 4.0, 0.2, 12.0, 0.1),
        "auto_sensitivity": ("choice", "Off", ("Off", "On"), "", ""),
        "source": ("choice", "System Output", ("System Output", "Microphone"), "", ""),
        "bins": ("int", 0, 0, 512, 1),
        "bin_width": ("int", 1, 1, 32, 1),
    }

    @staticmethod
    def _compress_spectrum(spectrum: np.ndarray, count: int) -> np.ndarray:
        """Average the 1024 FFT values into perceptually-spaced output bins."""
        if spectrum.size == 0 or count <= 0:
            return np.zeros(max(0, count), dtype=np.float32)
        # Geometric frequency grouping gives useful low-frequency detail while
        # still averaging hundreds of FFT bins into the panel's output bins.
        boundaries = np.geomspace(1, spectrum.size + 1, count + 1) - 1
        starts = np.floor(boundaries[:-1]).astype(int)
        ends = np.ceil(boundaries[1:]).astype(int)
        result = np.zeros(count, dtype=np.float32)
        for i, (start, end) in enumerate(zip(starts, ends)):
            start = min(max(0, start), spectrum.size - 1)
            end = min(spectrum.size, max(start + 1, end))
            result[i] = float(np.mean(spectrum[start:end]))
        return result

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        p = dict(self.parameters)
        if audio_state:
            p.update(audio_state)
        bin_width = max(1, min(width, int(p.get("bin_width", 1))))
        max_bins = max(1, (width + bin_width - 1) // bin_width)
        requested_bins = int(p.get("bins", 0))
        count = min(max_bins, requested_bins if requested_bins > 0 else max_bins)
        spectrum = np.asarray(p.get("audio_spectrum", []), dtype=np.float32)
        bars = self._compress_spectrum(spectrum, count)
        bars = np.clip(bars * float(p.get("sensitivity", 4.0)), 0, 1)
        bar_heights = (bars * height).astype(int)

        out = np.zeros((height, width, 3), dtype=np.uint8)
        y = np.linspace(0, 1, height, dtype=np.float32)[:, None]
        for index, bar_height in enumerate(bar_heights):
            x0 = index * bin_width
            x1 = min(width, x0 + bin_width)
            if x0 >= width:
                break
            rows = np.arange(height) >= height - bar_height
            out[:, x0:x1, 0] = np.where(rows[:, None], np.clip(255 * y, 0, 255), 0).astype(np.uint8)
            out[:, x0:x1, 1] = np.where(rows[:, None], np.clip(255 * (1 - y), 0, 255), 0).astype(np.uint8)
            out[:, x0:x1, 2] = np.where(rows[:, None], 35, 0).astype(np.uint8)
        return out
