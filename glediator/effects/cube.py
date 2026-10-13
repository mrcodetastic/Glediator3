import numpy as np

from glediator.effects.support import ConfiguredEffect, aurora_coordinates, AURORA_SETTINGS
from glediator.generators.palettes import colorize


class CubeEffect(ConfiguredEffect):
    name = "Cube"
    description = "A rotating wireframe cube."
    settings = AURORA_SETTINGS

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        _, _, xx, yy, _, _, _, _ = aurora_coordinates(width, height)
        v = np.array([[-1, -1, -1], [-1, -1, 1], [-1, 1, -1], [-1, 1, 1],
                      [1, -1, -1], [1, -1, 1], [1, 1, -1], [1, 1, 1]], dtype=np.float32)
        speed = self.parameters["speed"]
        ax, ay = time_seconds * speed * .7, time_seconds * speed
        rx = np.array([[1, 0, 0], [0, np.cos(ax), -np.sin(ax)], [0, np.sin(ax), np.cos(ax)]])
        ry = np.array([[np.cos(ay), 0, np.sin(ay)], [0, 1, 0], [-np.sin(ay), 0, np.cos(ay)]])
        points = (v @ (rx @ ry).T)
        projected = points[:, :2] / (2.8 + points[:, 2:3])
        projected = projected * .7 + .5
        field = np.zeros((height, width), dtype=np.float32)
        for a in range(8):
            for b in range(a + 1, 8):
                if np.sum(v[a] != v[b]) != 1:
                    continue
                p, q = projected[a], projected[b]
                vx, vy = q[0] - p[0], q[1] - p[1]
                d2 = vx * vx + vy * vy + 1e-8
                u = np.clip(((xx - p[0]) * vx + (yy - p[1]) * vy) / d2, 0, 1)
                distance = (xx - (p[0] + u * vx)) ** 2 + (yy - (p[1] + u * vy)) ** 2
                field = np.maximum(field, np.exp(-distance * min(width, height) ** 2 * .8))
        return colorize(field, self.parameters["palette"])
