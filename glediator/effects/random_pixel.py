import numpy as np

from glediator.effects.support import ConfiguredEffect
from glediator.generators.palettes import PALETTE_NAMES, colorize


class RandomPixelEffect(ConfiguredEffect):
    name = "Random Pixel"
    description = "Randomly spawned pixels that fade over time."
    settings = {
        "spawn_rate": ("int", 22, 1, 500, 1),
        "fade_duration": ("float", 1.8, 0.1, 15.0, 0.1),
        "palette": ("choice", "Rainbow", PALETTE_NAMES, "", ""),
    }

    def __init__(self):
        super().__init__()
        self._random_age = None
        self._random_hue = None
        self._random_time = None

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        shape = (height, width)
        if self._random_age is None or self._random_age.shape != shape:
            self._random_age = np.full(shape, -1.0, dtype=np.float32)
            self._random_hue = np.zeros(shape, dtype=np.float32)
        dt = 1 / 60 if self._random_time is None else float(np.clip(time_seconds - self._random_time, 0, .5))
        self._random_time = time_seconds
        duration = float(self.parameters["fade_duration"])
        active = self._random_age >= 0
        self._random_age[active] += dt
        self._random_age[self._random_age >= duration] = -1
        count = int(self._rng.poisson(float(self.parameters["spawn_rate"]) * dt))
        if count:
            yy, xx = self._rng.integers(0, height, count), self._rng.integers(0, width, count)
            self._random_age[yy, xx] = 0
            self._random_hue[yy, xx] = self._rng.random(count)
        alive = self._random_age >= 0
        fade = np.clip(1 - self._random_age / duration, 0, 1)
        colors = colorize(self._random_hue, self.parameters["palette"]).astype(np.float32)
        return np.clip(colors * fade[..., None] * alive[..., None], 0, 255).astype(np.uint8)
