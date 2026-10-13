import numpy as np

from glediator.effects.support import ConfiguredEffect


class BlackEffect(ConfiguredEffect):
    name = "Black"
    description = "A blank RGB frame."

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        return np.zeros((height, width, 3), dtype=np.uint8)
