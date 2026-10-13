import numpy as np

from glediator.effects.support import AURORA_SETTINGS, ConfiguredEffect
from glediator.generators.palettes import colorize


class NoiseSmearingEffect(ConfiguredEffect):
    name = "Noise Smearing"
    description = "Animated noise smeared across the matrix."
    settings = AURORA_SETTINGS

    def __init__(self):
        super().__init__()
        self._noise_smear = None

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        if self._noise_smear is None or self._noise_smear.shape != (height, width):
            self._noise_smear = self._rng.random((height, width), dtype=np.float32)
        self._noise_smear = np.roll(self._noise_smear, 1, axis=1)
        self._noise_smear = np.clip(self._noise_smear * .9 + self._rng.random((height, width), dtype=np.float32) * .1, 0, 1)
        return colorize(self._noise_smear, self.parameters["palette"])
