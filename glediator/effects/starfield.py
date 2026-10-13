import numpy as np

from glediator.effects.support import AURORA_SETTINGS, ConfiguredEffect
from glediator.generators.palettes import colorize


class StarfieldEffect(ConfiguredEffect):
    name = "Starfield"
    description = "Stars moving through a simulated depth field."
    settings = AURORA_SETTINGS

    def __init__(self):
        super().__init__()
        self._stars = None

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        if self._stars is None:
            self._stars = np.column_stack((self._rng.uniform(-1, 1, 90), self._rng.uniform(-1, 1, 90), self._rng.random(90))).astype(np.float32)
        depth = np.mod(self._stars[:, 2] + time_seconds * self.parameters["speed"] * .12, 1)
        factor = .08 + depth * .9
        px = np.rint(width * (.5 + self._stars[:, 0] * factor * .5)).astype(int)
        py = np.rint(height * (.5 + self._stars[:, 1] * factor * .5)).astype(int)
        valid = (px >= 0) & (px < width) & (py >= 0) & (py < height)
        out = np.zeros((height, width, 3), dtype=np.uint8)
        out[py[valid], px[valid]] = colorize(depth[valid], self.parameters["palette"])
        return out
