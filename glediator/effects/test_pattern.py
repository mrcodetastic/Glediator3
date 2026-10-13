import numpy as np

from glediator.effects.support import AURORA_SETTINGS, ConfiguredEffect


class TestPatternEffect(ConfiguredEffect):
    name = "Test"
    description = "A solid-color test frame."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        out = np.empty((height, width, 3), dtype=np.uint8)
        out[:] = (128, 0, 0)
        return out
