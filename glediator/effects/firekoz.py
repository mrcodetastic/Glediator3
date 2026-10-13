import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class FireKozEffect(ConfiguredEffect):
    name = "FireKoz"
    description = "A stylized animated fire pattern."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, xx, yy, _, _, _, _ = aurora_coordinates(width, height)
        speed = self.parameters["speed"]
        heat = np.clip((1 - yy) * (.65 + .35 * np.sin(xx * 27 + time_seconds * speed * 3))
                       + self._rng.random((height, width)) * .12, 0, 1)
        return colorize(heat, self.parameters["palette"])
