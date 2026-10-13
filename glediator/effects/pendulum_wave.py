import numpy as np

from glediator.effects.support import AURORA_SETTINGS, ConfiguredEffect, coordinates
from glediator.generators.palettes import colorize


class PendulumWaveEffect(ConfiguredEffect):
    name = "Pendulum Wave"
    description = "A set of phase-shifted pendulum waves."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        x, y = coordinates(width, height)
        wave = np.zeros((height, width), dtype=np.float32)
        for i in range(14):
            center = .5 + .42 * np.sin(x * np.pi * 2 + time_seconds * self.parameters["speed"] * (.5 + i * .035) + i * .48)
            wave = np.maximum(wave, np.exp(-((y - center) ** 2) * height * 5))
        return colorize(wave, self.parameters["palette"])
