import numpy as np

from glediator.effects.support import AURORA_SETTINGS, ConfiguredEffect
from glediator.generators.palettes import colorize


class MunchEffect(ConfiguredEffect):
    name = "Munch"
    description = "A moving XOR-based Munch pattern."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        xi, yi = np.meshgrid(np.arange(width), np.arange(height))
        speed = self.parameters["speed"]
        phase = int(time_seconds * speed * 2)
        sweep = (time_seconds * speed * 8) % (2 * width)
        threshold = sweep if sweep <= width else 2 * width - sweep
        active = (xi ^ yi ^ phase) < threshold
        color_index = np.mod((xi ^ yi) * .035 + time_seconds * speed * .08, 1)
        return colorize(np.where(active, .2 + .8 * color_index, 0), self.parameters["palette"])
