import numpy as np

from glediator.effects.support import ConfiguredEffect
from glediator.generators.palettes import PALETTE_NAMES, colorize


class MetaBallsEffect(ConfiguredEffect):
    name = "Meta Balls"
    description = "Soft, moving metaballs merged into a single field."
    settings = {
        "count": ("int", 4, 1, 8, 1),
        "speed": ("float", 0.7, 0.1, 3.0, 0.1),
        "radius": ("float", 0.20, 0.05, 0.45, 0.01),
        "palette": ("choice", "Aurora", PALETTE_NAMES, "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        p = self.parameters
        field = np.zeros((height, width), dtype=np.float32)
        radius = max(1.0, min(width, height) * p["radius"])
        yy, xx = np.arange(height)[:, None], np.arange(width)[None, :]
        for i in range(p["count"]):
            angle = 2 * np.pi * i / p["count"]
            cx = width * (.5 + .34 * np.sin(time_seconds * p["speed"] + angle))
            cy = height * (.5 + .34 * np.cos(time_seconds * p["speed"] * .83 + angle))
            blob = np.exp(-((yy - cy) ** 2 + (xx - cx) ** 2) / (2 * radius * radius))
            field = np.maximum(field, blob)
        return colorize(field, p.get("palette", "Aurora"))
