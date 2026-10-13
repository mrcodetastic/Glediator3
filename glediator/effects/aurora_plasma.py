import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class AuroraPlasmaEffect(ConfiguredEffect):
    name = "Aurora Plasma"
    description = "Aurora-colored plasma interference."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, _, _, nx, ny, _, _ = aurora_coordinates(width, height)
        p = self.parameters
        a = np.sin(nx * (5 * p["scale"]) + time_seconds * p["speed"])
        b = np.sin(ny * (7 * p["scale"]) - time_seconds * p["speed"] * .73)
        v = (np.sin(a * 3 + time_seconds) + np.sin(b * 3 - time_seconds * .7) + 2) / 4
        return colorize(v, p["palette"])
