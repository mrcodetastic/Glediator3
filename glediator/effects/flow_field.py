import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class FlowFieldEffect(ConfiguredEffect):
    name = "Flow Field"
    description = "A procedural animated flow field."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, _, _, nx, ny, _, _ = aurora_coordinates(width, height)
        p = self.parameters
        flow = np.sin(nx * p["scale"] * 5 + np.sin(ny * 4 + time_seconds * p["speed"]) * 2)
        flow += np.sin(ny * p["scale"] * 7 - np.cos(nx * 3 - time_seconds * p["speed"]) * 2)
        return colorize((flow + 2) / 4, p["palette"])
