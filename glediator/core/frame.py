from __future__ import annotations

import numpy as np


def new_frame(width: int, height: int) -> np.ndarray:
    return np.zeros((height, width, 3), dtype=np.uint8)


def to_rgb565(frame: np.ndarray) -> np.ndarray:
    rgb = np.asarray(frame, dtype=np.uint8)
    return (((rgb[..., 0].astype(np.uint16) >> 3) << 11)
            | ((rgb[..., 1].astype(np.uint16) >> 2) << 5)
            | (rgb[..., 2].astype(np.uint16) >> 3))
