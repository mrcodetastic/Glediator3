import numpy as np

from glediator.effects.support import ConfiguredEffect, coordinates
from glediator.generators.palettes import PALETTE_NAMES, colorize


class ExpandingCirclesEffect(ConfiguredEffect):
    name = "Expanding Circles"
    description = "Concentric expanding rings."
    settings = {
        "speed": ("float", 5.0, 0.5, 20.0, 0.5),
        "spacing": ("float", 7.0, 2.0, 20.0, 0.5),
        "palette": ("choice", "Neon", PALETTE_NAMES, "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        x, y = coordinates(width, height)
        radius = np.sqrt(((x - .5) * width) ** 2 + ((y - .5) * height) ** 2)
        spacing = self.parameters["spacing"]
        pulse = np.abs(np.mod(radius - time_seconds * self.parameters["speed"], spacing) - spacing / 2)
        light = np.clip(1 - pulse / 1.3, 0, 1)
        return colorize(light, self.parameters.get("palette", "Neon"))
