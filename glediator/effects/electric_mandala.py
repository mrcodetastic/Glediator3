import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class ElectricMandalaEffect(ConfiguredEffect):
    name = "Electric Mandala"
    description = "A radial electric mandala."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, _, _, _, _, radius, angle = aurora_coordinates(width, height)
        petals = np.sin(angle * 12 + np.sin(radius * 18 - time_seconds * self.parameters["speed"]) * 2)
        field = np.clip((petals + 1) / 2, 0, 1) * np.clip(1 - radius * .55, 0, 1)
        return colorize(field, self.parameters["palette"])
