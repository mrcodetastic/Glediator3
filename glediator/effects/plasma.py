import numpy as np

from glediator.effects.support import ConfiguredEffect, coordinates
from glediator.generators.palettes import PALETTE_NAMES, colorize


class PlasmaEffect(ConfiguredEffect):
    name = "Plasma"
    description = "Layered animated plasma fields."
    settings = {
        "scale": ("float", 8.0, 2.0, 24.0, 0.5),
        "speed": ("float", 1.0, 0.1, 5.0, 0.1),
        "palette": ("choice", "Aurora", PALETTE_NAMES, "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        x, y = coordinates(width, height)
        p = self.parameters
        v = (np.sin(x * p["scale"] * 1.4 + time_seconds * p["speed"])
             + np.sin(y * p["scale"] * 1.6 - time_seconds * p["speed"] * .8)
             + np.sin((x + y) * p["scale"] + time_seconds * p["speed"] * .5)) / 6 + .5
        return colorize(v, p.get("palette", "Aurora"))
