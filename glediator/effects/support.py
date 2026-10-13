"""Shared state/configuration and low-level rendering helpers for effects."""

from __future__ import annotations

import os
import time
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageFont

from glediator.effects.base import Effect
from glediator.generators.palettes import PALETTE_NAMES, colorize
from glediator.generators.settings import sanitize_parameters


AURORA_SETTINGS = {
    "speed": ("float", 1.0, 0.1, 8.0, 0.1),
    "scale": ("float", 1.0, 0.1, 8.0, 0.1),
    "palette": ("choice", "Aurora", PALETTE_NAMES, "", ""),
}


class ConfiguredEffect(Effect):
    """Convenience base for effects using the application's settings schema."""

    settings: dict = {}

    def __init__(self) -> None:
        self.parameters: dict = {}
        self._rng = np.random.default_rng()
        self.last_error = ""

    def setup(self) -> None:
        self.configure({})

    def configure(self, parameters: dict | None = None) -> None:
        self.parameters = sanitize_parameters(self.name, parameters, self.settings)


def coordinates(width: int, height: int) -> tuple[np.ndarray, np.ndarray]:
    """Return normalized x/y coordinate grids in the legacy renderer layout."""
    return (np.linspace(0, 1, width, dtype=np.float32)[None, :],
            np.linspace(0, 1, height, dtype=np.float32)[:, None])


def aurora_coordinates(width: int, height: int):
    x, y = coordinates(width, height)
    xx, yy = np.broadcast_to(x, (height, width)), np.broadcast_to(y, (height, width))
    nx, ny = xx * 2 - 1, yy * 2 - 1
    return x, y, xx, yy, nx, ny, np.sqrt(nx * nx + ny * ny), np.arctan2(ny, nx)


def load_font(family: str, size: int) -> ImageFont.FreeTypeFont | ImageFont.ImageFont:
    font_files = {
        "Arial": "arial.ttf", "Calibri": "calibri.ttf", "Consolas": "consola.ttf",
        "Courier New": "cour.ttf", "Georgia": "georgia.ttf", "Segoe UI": "segoeui.ttf",
        "Tahoma": "tahoma.ttf", "Times New Roman": "times.ttf", "Verdana": "verdana.ttf",
    }
    candidate = Path(os.environ.get("WINDIR", "C:/Windows")) / "Fonts" / font_files.get(family, "")
    if candidate.is_file():
        return ImageFont.truetype(str(candidate), size=size)
    for candidate_name in (family, "DejaVuSans.ttf"):
        try:
            return ImageFont.truetype(candidate_name, size=size)
        except OSError:
            continue
    return ImageFont.load_default(size=size)


def scrolling_text(width: int, height: int, t: float, params: dict) -> np.ndarray:
    text = str(params["text"])
    if not text:
        return np.zeros((height, width, 3), dtype=np.uint8)
    font = load_font(str(params["font"]), int(params["font_size"]))
    measure = ImageDraw.Draw(Image.new("RGB", (1, 1)))
    bounds = measure.textbbox((0, 0), text, font=font)
    text_width, text_height = max(1, bounds[2] - bounds[0]), max(1, bounds[3] - bounds[1])
    direction = params["direction"]
    canvas = Image.new("RGB", (width, height), (0, 0, 0))
    color = (30, 220, 255)
    distance = int(t * float(params["speed"]))
    if direction in ("Left", "Right"):
        strip = Image.new("RGB", (text_width, height), (0, 0, 0))
        strip_draw = ImageDraw.Draw(strip)
        text_y = (height - text_height) // 2 - bounds[1]
        strip_draw.text((-bounds[0], text_y), text, font=font, fill=color)
        phase = distance % (width + text_width)
        canvas.paste(strip, (width - phase if direction == "Left" else -text_width + phase, 0))
    else:
        strip = Image.new("RGB", (width, text_height), (0, 0, 0))
        strip_draw = ImageDraw.Draw(strip)
        text_x = (width - text_width) // 2 - bounds[0]
        strip_draw.text((text_x, -bounds[1]), text, font=font, fill=color)
        phase = distance % (height + text_height)
        canvas.paste(strip, (0, height - phase if direction == "Up" else -text_height + phase))
    return np.asarray(canvas, dtype=np.uint8).copy()


def capture_screen(effect: ConfiguredEffect, width: int, height: int, params: dict) -> np.ndarray:
    signature = None
    try:
        if not hasattr(effect, "_capture_backend") or effect._capture_backend is None:
            import mss
            effect._capture_backend = mss.MSS()
            effect._capture_black_frames = 0
        capture = effect._capture_backend
        monitors = capture.monitors
        monitor_index = max(1, min(int(params["monitor"]), len(monitors) - 1)) if len(monitors) > 1 else 0
        monitor = monitors[monitor_index]
        x, y = int(params["x"]), int(params["y"])
        region = {"left": monitor["left"] + x, "top": monitor["top"] + y,
                  "width": max(1, int(params["width"]) or monitor["width"] - x),
                  "height": max(1, int(params["height"]) or monitor["height"] - y)}
        capture_fps = max(1, min(30, int(params.get("capture_fps", 8))))
        signature = (monitor_index, region["left"], region["top"], region["width"], region["height"],
                     width, height, capture_fps)
        now = time.monotonic()
        cached = getattr(effect, "_capture_frame_cache", None)
        if (cached is not None and getattr(effect, "_capture_signature", None) == signature
                and now - getattr(effect, "_capture_last_time", 0.0) < 1.0 / capture_fps):
            return cached.copy()
        shot = np.asarray(capture.grab(region), dtype=np.uint8)[..., :3][..., ::-1]
        if np.any(shot):
            effect._capture_black_frames = 0
            effect.last_error = ""
        else:
            effect._capture_black_frames += 1
            if effect._capture_black_frames >= 30:
                effect.last_error = (
                    "Screen Capture is returning black frames. Verify the selected monitor/region; "
                    "Windows secure desktop, protected video, or a locked/remote session may block capture."
                )
        ys = np.linspace(0, shot.shape[0] - 1, height).astype(np.intp)
        xs = np.linspace(0, shot.shape[1] - 1, width).astype(np.intp)
        frame = shot[ys[:, None], xs[None, :]].copy()
        effect._capture_frame_cache = frame
        effect._capture_signature = signature
        effect._capture_last_time = now
        return frame.copy()
    except Exception as exc:
        effect.last_error = f"Screen capture failed: {exc}"
        frame = np.zeros((height, width, 3), dtype=np.uint8)
        if signature is not None:
            effect._capture_frame_cache = frame
            effect._capture_signature = signature
            effect._capture_last_time = time.monotonic()
        return frame


def close_capture(effect: ConfiguredEffect) -> None:
    backend = getattr(effect, "_capture_backend", None)
    if backend is not None:
        try:
            backend.close()
        except Exception:
            pass
        effect._capture_backend = None
    effect._capture_frame_cache = None
    effect._capture_signature = None
