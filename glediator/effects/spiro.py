import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class SpiroEffect(ConfiguredEffect):
    name = "Spiro"
    description = "A glowing spirograph curve."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, _, _, _, _, radius, angle = aurora_coordinates(width, height)
        value = np.sin(angle * 7 + np.sin(angle * 3 + time_seconds * self.parameters["speed"]) * 3)
        field = np.exp(-((radius - (.38 + .12 * value)) ** 2) * 180)
        return colorize(field, self.parameters["palette"])
