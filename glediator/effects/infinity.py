import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class InfinityEffect(ConfiguredEffect):
    name = "Infinity"
    description = "Crossing infinity-shaped animated curves."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, _, _, nx, ny, _, _ = aurora_coordinates(width, height)
        curve = np.abs(ny - .55 * np.sin(nx * 3.3 + time_seconds * self.parameters["speed"]))
        second = np.abs(ny + .55 * np.sin(nx * 3.3 + time_seconds * self.parameters["speed"]))
        field = np.maximum(np.exp(-curve * min(width, height) * .45), np.exp(-second * min(width, height) * .45))
        return colorize(field, self.parameters["palette"])
