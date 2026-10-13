import numpy as np

from glediator.effects.support import AURORA_SETTINGS, ConfiguredEffect, coordinates
from glediator.generators.palettes import colorize


class AuroraWaveEffect(ConfiguredEffect):
    name = "Aurora Wave"
    description = "Layered flowing aurora waves."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        x, y = coordinates(width, height)
        field = np.zeros((height, width), dtype=np.float32)
        speed = self.parameters["speed"]
        for i in range(5):
            center = .5 + .32 * np.sin(x * (2 + i * .55) * np.pi + time_seconds * speed * (.5 + i * .08) + i)
            field += np.exp(-((y - center) ** 2) * height * 5) / 5
        return colorize(np.clip(field, 0, 1), self.parameters["palette"])
