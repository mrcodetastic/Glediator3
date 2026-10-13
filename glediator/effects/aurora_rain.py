import numpy as np

from glediator.effects.support import AURORA_SETTINGS, ConfiguredEffect, coordinates
from glediator.generators.palettes import colorize


class AuroraRainEffect(ConfiguredEffect):
    name = "Aurora Rain"
    description = "A colored vertical rain effect."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        x, y = coordinates(width, height)
        column = np.arange(width, dtype=np.float32)[None, :]
        head = np.mod(column * 7.13 + time_seconds * self.parameters["speed"] * 10, height + 12)
        trail = np.mod(head - y * height, height + 12)
        intensity = np.clip(1 - trail / 10, 0, 1) * (np.sin((x * width + y * height * 2 + column) * 2.4) > .1)
        return colorize(intensity, self.parameters["palette"])
