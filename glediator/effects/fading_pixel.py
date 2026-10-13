import numpy as np

from glediator.effects.support import ConfiguredEffect
from glediator.generators.palettes import PALETTE_NAMES, colorize


class FadingPixelEffect(ConfiguredEffect):
    name = "Fading Pixel"
    description = "A continuously fading field of colored sparkles."
    settings = {
        "density": ("float", 0.01, 0.001, 0.1, 0.001),
        "decay": ("float", 0.78, 0.4, 0.99, 0.01),
        "palette": ("choice", "Rainbow", PALETTE_NAMES, "", ""),
    }

    def __init__(self):
        super().__init__()
        self._sparkles = None

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        shape = (height, width, 3)
        if self._sparkles is None or self._sparkles.shape != shape:
            self._sparkles = np.zeros(shape, dtype=np.uint8)
        self._sparkles = (self._sparkles.astype(np.float32) * self.parameters["decay"]).astype(np.uint8)
        count = max(1, int(width * height * self.parameters["density"]))
        yy, xx = self._rng.integers(0, height, count), self._rng.integers(0, width, count)
        self._sparkles[yy, xx] = colorize(self._rng.random(count), self.parameters.get("palette", "Rainbow"))
        return self._sparkles.copy()
