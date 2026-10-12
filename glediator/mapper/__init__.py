from __future__ import annotations

import numpy as np


MAPPING_NAMES = {
    "horizontal-lines": "Horizontal lines",
    "horizontal-snake": "Horizontal snake",
    "vertical-lines": "Vertical lines",
    "vertical-snake": "Vertical snake",
}


def map_frame(frame: np.ndarray, order: str) -> np.ndarray:
    """Return pixels in physical scan order for common panel wiring layouts."""
    h, w, _ = frame.shape
    if order == "vertical-lines":
        return frame.transpose(1, 0, 2).reshape(-1, 3)
    if order == "vertical-snake":
        view = frame.transpose(1, 0, 2).copy()
        view[1::2] = view[1::2, ::-1]
        return view.reshape(-1, 3)
    if order == "horizontal-lines":
        return frame.reshape(-1, 3)
    view = frame.copy()
    view[1::2] = view[1::2, ::-1]
    return view.reshape(-1, 3)
