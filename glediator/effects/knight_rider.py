import numpy as np

from glediator.effects.support import ConfiguredEffect

class KnightRiderEffect(ConfiguredEffect):
    name = "Knight Rider"
    description = "A red scanner bar with a soft trail."
    settings = {"speed": ("float", 1.7, 0.1, 8.0, 0.1), "trail": ("float", 0.11, 0.02, 0.5, 0.01)}

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        out = np.zeros((height, width, 3), dtype=np.uint8)
        col = int((.5 + .5 * np.sin(time_seconds * self.parameters["speed"])) * (width - 1))
        span = max(1, int(width * self.parameters["trail"]))
        strength = np.maximum(0, 1 - np.abs(np.arange(width) - col) / span)
        out[..., 0] = (strength[None, :] * 255).astype(np.uint8)
        out[..., 1] = (strength[None, :] * 24).astype(np.uint8)
        return out
