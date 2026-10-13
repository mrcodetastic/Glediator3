import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class SnakeEffect(ConfiguredEffect):
    name = "Snake"
    description = "A glowing snake moving along a curved path."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, xx, yy, _, _, _, _ = aurora_coordinates(width, height)
        field = np.zeros((height, width), dtype=np.float32)
        speed = self.parameters["speed"]
        for i in range(20):
            u = i / 20
            phase = time_seconds * speed - u * 6
            cx = .5 + .34 * np.sin(phase)
            cy = .5 + .34 * np.sin(phase * 1.35 + .8)
            field = np.maximum(field, np.exp(-((xx - cx) ** 2 + (yy - cy) ** 2) * 950))
        return colorize(field, self.parameters["palette"])
