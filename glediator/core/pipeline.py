from __future__ import annotations

import numpy as np


def apply_filter(frame: np.ndarray, filter_name: str) -> np.ndarray:
    if filter_name == "Invert":
        return 255 - frame
    if filter_name == "Monochrome":
        luminance = np.dot(frame[..., :3], [0.2126, 0.7152, 0.0722]).astype(np.uint8)
        return np.repeat(luminance[..., None], 3, axis=2)
    if filter_name == "Threshold":
        return np.where(frame >= 128, 255, 0).astype(np.uint8)
    return frame


def mix(a: np.ndarray, b: np.ndarray, mode: str = "Add") -> np.ndarray:
    aa, bb = a.astype(np.uint16), b.astype(np.uint16)
    if mode == "Subtract":
        value = np.maximum(aa.astype(np.int16) - bb.astype(np.int16), 0)
    elif mode == "Multiply":
        value = aa * bb // 255
    elif mode == "Difference":
        value = np.abs(aa.astype(np.int16) - bb.astype(np.int16))
    elif mode == "Screen":
        value = 255 - ((255 - aa) * (255 - bb) // 255)
    elif mode == "Maximum":
        value = np.maximum(aa, bb)
    else:
        value = aa + bb
    return np.clip(value, 0, 255).astype(np.uint8)
