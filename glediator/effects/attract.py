import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class AttractEffect(ConfiguredEffect):
    name = "Attract"
    description = "An animated attractor pattern."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, _, _, _, _, radius, angle = aurora_coordinates(width, height)
        p = self.parameters
        phase = np.sin(angle * 5 + radius * 11 - time_seconds * p["speed"] * 2)
        field = np.exp(-(radius - (.18 + .18 * (phase + 1))) ** 2 * 48)
        field += np.exp(-((radius * np.cos(angle) - .38 * np.sin(time_seconds * p["speed"])) ** 2
                           + (radius * np.sin(angle) - .35 * np.cos(time_seconds * p["speed"] * .7)) ** 2) * 85)
        return colorize(np.clip(field, 0, 1), p["palette"])
