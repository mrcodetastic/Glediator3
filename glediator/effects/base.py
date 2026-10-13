"""Base contract shared by effect instances."""

from __future__ import annotations

from abc import ABC, abstractmethod

import numpy as np


class Effect(ABC):
    """One configured effect instance owned by a source layer."""

    name: str = ""
    description: str = ""
    settings: dict = {}

    def setup(self) -> None:
        """Initialize per-instance resources. Called once by the effect loader."""

    @abstractmethod
    def configure(self, parameters: dict | None = None) -> None:
        """Apply this layer's persisted/configured parameter values."""

    @abstractmethod
    def drawFrame(self, width: int, height: int, time_seconds: float,
                  audio_state: dict | None = None) -> np.ndarray:
        """Return the current RGB frame as an array shaped (height, width, 3)."""

    def cleanup(self) -> None:
        """Release resources held by this instance, if any."""

    def close(self) -> None:
        """Compatibility alias for cleanup()."""
        self.cleanup()

