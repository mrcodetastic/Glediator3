import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class FireworksEffect(ConfiguredEffect):
    name = "Fireworks"
    description = "Timed multi-burst fireworks."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, xx, yy, _, _, _, _ = aurora_coordinates(width, height)
        speed = self.parameters["speed"]
        field = np.zeros((height, width), dtype=np.float32)
        period = 3.2 / max(.2, speed)
        age = (time_seconds % period) / period
        if age < .78:
            for burst in range(3):
                cx = .2 + burst * .3 + .08 * np.sin(time_seconds * .3 + burst)
                cy = .25 + .2 * (burst % 2)
                for spark in range(18):
                    theta = 2 * np.pi * spark / 18 + burst * .2
                    radius = age * (.2 + .12 * ((spark * 7 + burst) % 5) / 5)
                    sx, sy = cx + np.cos(theta) * radius, cy + np.sin(theta) * radius
                    glow = np.exp(-((xx - sx) ** 2 + (yy - sy) ** 2) * (1600 / (1 + age * 12))) * (1 - age / .85)
                    field = np.maximum(field, glow)
        return colorize(np.clip(field, 0, 1), self.parameters["palette"])
