import numpy as np

from glediator.effects.support import ConfiguredEffect, coordinates
from glediator.generators.palettes import PALETTE_NAMES


class SimpleSpectrumEffect(ConfiguredEffect):
    name = "Simple Spectrum"
    description = "Audio-frequency spectrum bars."
    settings = {
        "sensitivity": ("float", 4.0, 0.2, 12.0, 0.1),
        "source": ("choice", "System Output", ("System Output", "Microphone"), "", ""),
        "palette": ("choice", "Aurora", PALETTE_NAMES, "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        p = dict(self.parameters)
        if audio_state:
            p.update(audio_state)
        spectrum = np.asarray(p.get("audio_spectrum", []), dtype=np.float32)
        bars = (np.clip(spectrum[np.linspace(0, spectrum.size - 1, width).astype(np.intp)] * p["sensitivity"], 0, 1)
                if spectrum.size else np.zeros(width, dtype=np.float32))
        heights = (bars * height).astype(int)
        y = np.linspace(0, 1, height, dtype=np.float32)[:, None]
        mask = np.arange(height)[:, None] >= (height - heights[None, :])
        out = np.zeros((height, width, 3), dtype=np.uint8)
        out[..., 0] = np.where(mask, np.clip(255 * y, 0, 255), 0).astype(np.uint8)
        out[..., 1] = np.where(mask, np.clip(255 * (1 - y), 0, 255), 0).astype(np.uint8)
        out[..., 2] = np.where(mask, 35, 0).astype(np.uint8)
        return out
