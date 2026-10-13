import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class IncrementalDrift2Effect(ConfiguredEffect):
    name = "Incremental Drift 2"
    description = "A second, higher-frequency drift pattern."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, _, _, nx, ny, _, _ = aurora_coordinates(width, height)
        p = self.parameters
        a = np.sin(nx * (5 * p["scale"]) + time_seconds * p["speed"])
        b = np.sin(ny * (7 * p["scale"]) - time_seconds * p["speed"] * .73)
        c = np.sin((nx - ny * 1.4) * (9 * p["scale"]) + time_seconds * p["speed"] * .31)
        return colorize((a + b + c + 3) / 6, p["palette"])
