"""Shared, named palettes used by generator effects."""

from __future__ import annotations

import numpy as np


PALETTES: dict[str, tuple[tuple[int, int, int], ...]] = {
    "Aurora": ((2, 8, 24), (4, 55, 91), (22, 174, 139), (126, 255, 153), (245, 255, 220)),
    "Fire": ((10, 0, 0), (125, 12, 0), (255, 65, 0), (255, 190, 12), (255, 255, 210)),
    "Ocean": ((0, 8, 45), (0, 58, 150), (0, 174, 200), (95, 255, 224), (232, 255, 255)),
    "Sunset": ((20, 8, 62), (111, 27, 119), (229, 46, 92), (255, 126, 57), (255, 226, 125)),
    "Neon": ((3, 0, 14), (24, 25, 255), (0, 246, 231), (255, 32, 208), (255, 250, 67)),
    "Rainbow": ((255, 0, 0), (255, 160, 0), (255, 255, 0), (0, 220, 35), (0, 120, 255), (90, 0, 255), (255, 0, 160)),
    "Candy": ((24, 8, 60), (114, 45, 200), (255, 75, 174), (255, 181, 215), (98, 226, 255)),
    "Monochrome": ((0, 0, 0), (64, 72, 88), (150, 164, 178), (255, 255, 255)),
}

PALETTE_NAMES = tuple(PALETTES)


def parse_hex_color(value: str) -> np.ndarray:
    text = str(value).strip().lstrip("#")
    if len(text) == 3:
        text = "".join(ch * 2 for ch in text)
    if len(text) != 6:
        return np.array((255, 255, 255), dtype=np.float32)
    try:
        return np.array(tuple(int(text[i:i + 2], 16) for i in (0, 2, 4)), dtype=np.float32)
    except ValueError:
        return np.array((255, 255, 255), dtype=np.float32)


def colorize(values: np.ndarray, name: str = "Aurora") -> np.ndarray:
    """Interpolate a normalized scalar image through a preset RGB palette."""
    stops = np.asarray(PALETTES.get(name, PALETTES["Aurora"]), dtype=np.float32)
    scalar = np.clip(np.asarray(values, dtype=np.float32), 0.0, 1.0)
    scaled = scalar * (len(stops) - 1)
    lower = np.floor(scaled).astype(np.intp)
    upper = np.minimum(lower + 1, len(stops) - 1)
    amount = (scaled - lower)[..., None]
    rgb = stops[lower] * (1.0 - amount) + stops[upper] * amount
    return np.clip(rgb, 0, 255).astype(np.uint8)


def blend_colors(a: str, b: str, amount: np.ndarray) -> np.ndarray:
    start, end = parse_hex_color(a), parse_hex_color(b)
    mix = np.clip(np.asarray(amount, dtype=np.float32), 0, 1)[..., None]
    return np.clip(start * (1 - mix) + end * mix, 0, 255).astype(np.uint8)
