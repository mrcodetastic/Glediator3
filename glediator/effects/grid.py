import numpy as np

from glediator.effects.support import ConfiguredEffect, coordinates
from glediator.generators.palettes import PALETTE_NAMES, colorize


class GridEffect(ConfiguredEffect):
    name = "Grid"
    description = "Animated neon grid lines."
    settings = {
        "spacing": ("int", 8, 2, 32, 1),
        "speed": ("float", 3.0, 0.0, 20.0, 0.5),
        "palette": ("choice", "Neon", PALETTE_NAMES, "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        x, y = coordinates(width, height)
        spacing = self.parameters["spacing"]
        lines = ((np.mod(x * width + time_seconds * self.parameters["speed"], spacing) < 1.2)
                 | (np.mod(y * height, spacing) < 1.2)).astype(np.float32)
        return colorize(np.broadcast_to(lines, (height, width)), self.parameters.get("palette", "Neon"))
