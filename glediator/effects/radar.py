import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class RadarEffect(ConfiguredEffect):
    name = "Radar"
    description = "A rotating radar sweep with range rings."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, _, _, _, _, radius, angle = aurora_coordinates(width, height)
        sweep = np.mod(angle - time_seconds * self.parameters["speed"], 2 * np.pi)
        beam = np.exp(-sweep * 9) + np.exp(-(2 * np.pi - sweep) * 30)
        rings = np.exp(-np.abs(np.mod(radius * 8, 1) - .5) * 24)
        return colorize(np.clip(np.maximum(beam, rings * .25), 0, 1), self.parameters["palette"])
