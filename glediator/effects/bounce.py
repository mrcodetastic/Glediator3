import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class BounceEffect(ConfiguredEffect):
    name = "Bounce"
    description = "Multiple glowing points bouncing through the matrix."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, xx, yy, _, _, _, _ = aurora_coordinates(width, height)
        field = np.zeros((height, width), dtype=np.float32)
        for i in range(4):
            cx = .5 + .36 * np.sin(time_seconds * self.parameters["speed"] + i * np.pi / 2)
            cy = .18 + .68 * abs(np.sin(time_seconds * self.parameters["speed"] * (1.1 + i * .08) + i * 1.9))
            field = np.maximum(field, np.exp(-((xx - cx) ** 2 + (yy - cy) ** 2) * 1250))
        return colorize(field, self.parameters["palette"])
