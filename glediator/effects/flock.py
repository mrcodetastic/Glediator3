import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class FlockEffect(ConfiguredEffect):
    name = "Flock"
    description = "A flock-like trail of moving points."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, xx, yy, _, _, _, _ = aurora_coordinates(width, height)
        field = np.zeros((height, width), dtype=np.float32)
        speed = self.parameters["speed"]
        for i in range(14):
            u = i / 14
            cx = .5 + .34 * np.sin(time_seconds * speed + u * 2 * np.pi) + .08 * np.sin(time_seconds * speed * 1.7 + u * 9)
            cy = .5 + .30 * np.sin(time_seconds * speed * .8 + u * 2 * np.pi + 1.3)
            field = np.maximum(field, np.exp(-((xx - cx) ** 2 + (yy - cy) ** 2) * 950))
        return colorize(field, self.parameters["palette"])
