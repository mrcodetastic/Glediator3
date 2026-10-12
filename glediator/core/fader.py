from __future__ import annotations

import numpy as np


def crossfade(left: np.ndarray, right: np.ndarray, amount: float) -> np.ndarray:
    """Blend two RGB frames; amount 0 selects left and 1 selects right."""
    alpha = float(np.clip(amount, 0.0, 1.0))
    return np.rint(left.astype(np.float32) * (1.0 - alpha) + right.astype(np.float32) * alpha).astype(np.uint8)
