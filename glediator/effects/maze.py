import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class MazeEffect(ConfiguredEffect):
    name = "Maze"
    description = "A shifting, procedurally generated maze."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, xx, yy, _, _, _, _ = aurora_coordinates(width, height)
        gx, gy = np.floor(xx * width / 4), np.floor(yy * height / 4)
        phase = np.floor(time_seconds * self.parameters["speed"])
        walls = np.sin(gx * 12.9898 + gy * 78.233 + phase * 37.719) > .1
        edge = (np.mod(xx * width, 4) < 1) | (np.mod(yy * height, 4) < 1)
        return colorize((walls & edge).astype(np.float32), self.parameters["palette"])
