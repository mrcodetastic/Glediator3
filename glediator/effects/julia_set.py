import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class JuliaSetEffect(ConfiguredEffect):
    name = "Julia Set Fractal"
    description = "An animated Julia set fractal."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, _, _, nx, ny, _, _ = aurora_coordinates(width, height)
        z = nx + 1j * ny
        speed = self.parameters["speed"]
        c = complex(-.72 + .12 * np.sin(time_seconds * speed * .2), .21 + .18 * np.cos(time_seconds * speed * .17))
        escaped = np.zeros((height, width), dtype=np.float32)
        alive = np.ones((height, width), dtype=bool)
        for iteration in range(18):
            z[alive] = z[alive] * z[alive] + c
            newly = alive & (np.abs(z) > 2)
            escaped[newly] = iteration / 18
            alive[newly] = False
        escaped[alive] = 1
        return colorize(escaped, self.parameters["palette"])
