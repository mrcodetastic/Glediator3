import numpy as np

from glediator.effects.support import ConfiguredEffect, coordinates
from glediator.generators.palettes import PALETTE_NAMES, colorize


class FireEffect(ConfiguredEffect):
    name = "Fire"
    description = "Procedural flickering fire with configurable heat and sparks."
    settings = {
        "cooling": ("float", 1.0, 0.3, 2.0, 0.1),
        "sparks": ("float", 0.12, 0.0, 0.5, 0.01),
        "palette": ("choice", "Fire", PALETTE_NAMES, "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        x, y = coordinates(width, height)
        p = self.parameters
        heat = np.maximum(0, (1 - y) * (.55 + .45 * np.sin(x * 22 + time_seconds * 4)
                                       + .25 * np.sin(x * 39 - time_seconds * 3)))
        heat = np.clip(heat * p["cooling"] + self._rng.random((height, width)) * p["sparks"], 0, 1)
        return colorize(heat, p.get("palette", "Fire"))
