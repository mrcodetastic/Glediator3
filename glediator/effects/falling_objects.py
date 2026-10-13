import numpy as np

from glediator.effects.support import ConfiguredEffect, coordinates
from glediator.generators.palettes import PALETTE_NAMES, colorize


class FallingObjectsEffect(ConfiguredEffect):
    name = "Falling Objects"
    description = "Downward-moving streaks of light."
    settings = {
        "speed": ("float", 10.0, 1.0, 40.0, 1.0),
        "density": ("float", 1.1, 0.1, 4.0, 0.1),
        "palette": ("choice", "Aurora", PALETTE_NAMES, "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, y = coordinates(width, height)
        intensity = np.maximum(0, np.sin((y * height + time_seconds * self.parameters["speed"])
                                         * self.parameters["density"])) ** 12
        return colorize(np.broadcast_to(intensity, (height, width)), self.parameters.get("palette", "Aurora"))
