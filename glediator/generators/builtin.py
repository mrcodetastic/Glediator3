from __future__ import annotations

import os
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageFont

from glediator.generators.palettes import blend_colors, colorize, parse_hex_color
from glediator.generators.settings import AURORA_PATTERN_NAMES, default_parameters


GENERATORS = {
    "Black": "black", "Plasma": "plasma", "Fire": "fire", "Grid": "grid",
    "Wave": "wave", "Random Pixel": "random", "Knight Rider": "scanner",
    "Meta Balls": "metaballs", "Fading Pixel": "sparkle", "Falling Objects": "rain",
    "Expanding Circles": "rings", "Scrolling Text": "text", "VU Meter": "vu",
    "Simple Spectrum": "spectrum", "Screen Capture": "capture",
}
GENERATORS.update({name: "aurora" for name in AURORA_PATTERN_NAMES})


class BuiltinGenerator:
    """Vectorized procedural LED effects; generator state persists between frames."""

    def __init__(self, name: str) -> None:
        self.name = name
        self._rng = np.random.default_rng()
        self._sparkles: np.ndarray | None = None
        self._capture_backend = None
        self.last_error = ""
        self._capture_black_frames = 0
        self._random_age: np.ndarray | None = None
        self._random_hue: np.ndarray | None = None
        self._random_time: float | None = None
        self._noise_smear: np.ndarray | None = None
        self._stars: np.ndarray | None = None

    def render(self, width: int, height: int, t: float, parameters: dict | None = None,
               audio_state: dict | None = None) -> np.ndarray:
        params = default_parameters(self.name)
        if parameters:
            params.update(parameters)
        if audio_state:
            params.update(audio_state)
        x = np.linspace(0, 1, width, dtype=np.float32)[None, :]
        y = np.linspace(0, 1, height, dtype=np.float32)[:, None]
        kind = GENERATORS[self.name]
        out = np.zeros((height, width, 3), dtype=np.uint8)
        if kind == "aurora":
            return self._render_aurora(width, height, t, params, x, y)
        if kind == "black":
            return out
        if kind == "plasma":
            scale, speed = params["scale"], params["speed"]
            v = (np.sin(x * scale * 1.4 + t * speed) + np.sin(y * scale * 1.6 - t * speed * .8) + np.sin((x + y) * scale + t * speed * .5)) / 6 + 0.5
            out[:] = colorize(v, params.get("palette", "Aurora"))
        elif kind == "fire":
            heat = np.maximum(0, (1 - y) * (0.55 + 0.45 * np.sin(x * 22 + t * 4) + 0.25 * np.sin(x * 39 - t * 3)))
            heat = np.clip(heat * params["cooling"] + self._rng.random((height, width)) * params["sparks"], 0, 1)
            out[:] = colorize(heat, params.get("palette", "Fire"))
        elif kind == "grid":
            spacing = params["spacing"]
            lines = ((np.mod(x * width + t * params["speed"], spacing) < 1.2) | (np.mod(y * height, spacing) < 1.2)).astype(np.float32)
            out[:] = colorize(np.broadcast_to(lines, (height, width)), params.get("palette", "Neon"))
        elif kind == "wave":
            center = params["position"] + params["amplitude"] * np.sin(x * params["frequency"] * 2 * np.pi + t * params["speed"])
            glow = np.exp(-((y - center) ** 2) / max(1e-4, params["thickness"] ** 2))
            if params["palette"] == "Custom":
                rgb = blend_colors(params["color_a"], params["color_b"], np.broadcast_to(x, (height, width)))
            else:
                rgb = colorize(np.broadcast_to((x + 1) / 2, (height, width)), params["palette"])
            background = parse_hex_color(params["background"])
            out[:] = np.clip(rgb.astype(np.float32) * glow[..., None] + background * (1 - glow[..., None]), 0, 255).astype(np.uint8)
        elif kind == "random":
            return self._render_random_pixels(width, height, t, params)
        elif kind == "scanner":
            col = int((0.5 + 0.5 * np.sin(t * params["speed"])) * (width - 1))
            span = max(1, int(width * params["trail"]))
            strength = np.maximum(0, 1 - np.abs(np.arange(width) - col) / span)
            out[..., 0] = (strength[None, :] * 255).astype(np.uint8)
            out[..., 1] = (strength[None, :] * 24).astype(np.uint8)
        elif kind == "metaballs":
            field = np.zeros((height, width), dtype=np.float32)
            radius = max(1.0, min(width, height) * params["radius"])
            for i in range(params["count"]):
                speed = params["speed"]
                angle = 2 * np.pi * i / params["count"]
                cx = width * (0.5 + 0.34 * np.sin(t * speed + angle))
                cy = height * (0.5 + 0.34 * np.cos(t * speed * .83 + angle))
                distance_sq = (np.arange(height)[:, None] - cy) ** 2 + (np.arange(width)[None, :] - cx) ** 2
                blob = np.exp(-distance_sq / (2 * radius * radius))
                field = np.maximum(field, blob)
            out[:] = colorize(field, params.get("palette", "Aurora"))
        elif kind == "sparkle":
            if self._sparkles is None or self._sparkles.shape != out.shape:
                self._sparkles = np.zeros_like(out)
            self._sparkles = (self._sparkles.astype(np.float32) * params["decay"]).astype(np.uint8)
            count = max(1, int(width * height * params["density"]))
            yy, xx = self._rng.integers(0, height, count), self._rng.integers(0, width, count)
            hue = self._rng.random(count)
            self._sparkles[yy, xx] = colorize(hue, params.get("palette", "Rainbow"))
            out[:] = self._sparkles
        elif kind == "rain":
            intensity = np.maximum(0, np.sin((y * height + t * params["speed"]) * params["density"])) ** 12
            out[:] = colorize(np.broadcast_to(intensity, (height, width)), params.get("palette", "Aurora"))
        elif kind == "rings":
            radius = np.sqrt(((x - .5) * width) ** 2 + ((y - .5) * height) ** 2)
            pulse = np.abs(np.mod(radius - t * params["speed"], params["spacing"]) - params["spacing"] / 2)
            light = np.clip(1 - pulse / 1.3, 0, 1) * 255
            out[:] = colorize(np.clip(light / 255, 0, 1), params.get("palette", "Neon"))
        elif kind in ("vu", "spectrum"):
            level = float(params.get("audio_level", 0.0))
            peak = float(params.get("audio_peak", 0.0))
            if kind == "vu":
                bar_height = int(np.clip(level * params["sensitivity"], 0.0, 1.0) * height)
                rows = np.arange(height)[:, None]
                active = rows >= height - bar_height
                color_r = np.where(rows < height * .22, 255, np.where(rows < height * .5, 255, 30))
                color_g = np.where(rows < height * .22, 25, 210)
                out[:] = np.where(active[..., None], np.stack((color_r, color_g, np.zeros_like(color_r)), axis=-1), 0)
                peak_row = height - 1 - int(np.clip(peak * params["sensitivity"], 0, 1) * max(0, height - 1))
                if peak > 0 and 0 <= peak_row < height:
                    out[peak_row, :, :] = (255, 255, 255)
            else:
                spectrum = np.asarray(params.get("audio_spectrum", []), dtype=np.float32)
                if spectrum.size:
                    bins = np.linspace(0, spectrum.size - 1, width).astype(np.intp)
                    bars = np.clip(spectrum[bins] * params["sensitivity"], 0.0, 1.0)
                else:
                    bars = np.zeros(width, dtype=np.float32)
                bar_heights = (bars * height).astype(int)
                mask = np.arange(height)[:, None] >= (height - bar_heights[None, :])
                out[..., 0] = np.where(mask, np.clip(255 * y, 0, 255), 0).astype(np.uint8)
                out[..., 1] = np.where(mask, np.clip(255 * (1 - y), 0, 255), 0).astype(np.uint8)
                out[..., 2] = np.where(mask, 35, 0).astype(np.uint8)
        elif kind == "text":
            return self._render_scrolling_text(width, height, t, params)
        elif kind == "capture":
            out[:] = self._capture_screen(width, height, params)
        return out

    def _render_random_pixels(self, width: int, height: int, t: float, params: dict) -> np.ndarray:
        shape = (height, width)
        if self._random_age is None or self._random_age.shape != shape:
            self._random_age = np.full(shape, -1.0, dtype=np.float32)
            self._random_hue = np.zeros(shape, dtype=np.float32)
        dt = 1 / 60 if self._random_time is None else float(np.clip(t - self._random_time, 0.0, 0.5))
        self._random_time = t
        duration = float(params["fade_duration"])
        active = self._random_age >= 0
        self._random_age[active] += dt
        self._random_age[self._random_age >= duration] = -1
        new_count = int(self._rng.poisson(float(params["spawn_rate"]) * dt))
        if new_count:
            yy = self._rng.integers(0, height, new_count)
            xx = self._rng.integers(0, width, new_count)
            self._random_age[yy, xx] = 0
            self._random_hue[yy, xx] = self._rng.random(new_count)
        alive = self._random_age >= 0
        fade = np.clip(1 - self._random_age / duration, 0, 1)
        colors = colorize(self._random_hue, params["palette"]).astype(np.float32)
        return np.clip(colors * fade[..., None] * alive[..., None], 0, 255).astype(np.uint8)

    def _render_aurora(self, width: int, height: int, t: float, params: dict,
                       x: np.ndarray, y: np.ndarray) -> np.ndarray:
        name, speed, scale, palette = self.name, params["speed"], params["scale"], params["palette"]
        out = np.zeros((height, width, 3), dtype=np.uint8)
        xx = np.broadcast_to(x, (height, width))
        yy = np.broadcast_to(y, (height, width))
        nx, ny = xx * 2 - 1, yy * 2 - 1
        radius = np.sqrt(nx * nx + ny * ny)
        angle = np.arctan2(ny, nx)
        if name == "Attract":
            phase = np.sin(angle * 5 + radius * 11 - t * speed * 2)
            field = np.exp(-(radius - (0.18 + .18 * (phase + 1))) ** 2 * 48)
            field += np.exp(-((nx - .38 * np.sin(t * speed)) ** 2 + (ny - .35 * np.cos(t * speed * .7)) ** 2) * 85)
            return colorize(np.clip(field, 0, 1), palette)
        if name == "Bounce":
            field = np.zeros((height, width), dtype=np.float32)
            for i in range(4):
                cx = .5 + .36 * np.sin(t * speed + i * np.pi / 2)
                cy = .18 + .68 * abs(np.sin(t * speed * (1.1 + i * .08) + i * 1.9))
                field = np.maximum(field, np.exp(-((xx - cx) ** 2 + (yy - cy) ** 2) * 1250))
            return colorize(field, palette)
        if name == "Cube":
            vertices = np.array([[-1, -1, -1], [-1, -1, 1], [-1, 1, -1], [-1, 1, 1],
                                 [1, -1, -1], [1, -1, 1], [1, 1, -1], [1, 1, 1]], dtype=np.float32)
            ax, ay = t * speed * .7, t * speed
            rx = np.array([[1, 0, 0], [0, np.cos(ax), -np.sin(ax)], [0, np.sin(ax), np.cos(ax)]])
            ry = np.array([[np.cos(ay), 0, np.sin(ay)], [0, 1, 0], [-np.sin(ay), 0, np.cos(ay)]])
            rotated = vertices @ (rx @ ry).T
            projected = rotated[:, :2] / (2.8 + rotated[:, 2:3])
            projected = projected * .7 + .5
            field = np.zeros((height, width), dtype=np.float32)
            edges = [(a, b) for a in range(8) for b in range(a + 1, 8) if np.sum(vertices[a] != vertices[b]) == 1]
            for a, b in edges:
                p, q = projected[a], projected[b]
                vx, vy = q[0] - p[0], q[1] - p[1]
                d2 = vx * vx + vy * vy + 1e-8
                u = np.clip(((xx - p[0]) * vx + (yy - p[1]) * vy) / d2, 0, 1)
                distance = (xx - (p[0] + u * vx)) ** 2 + (yy - (p[1] + u * vy)) ** 2
                field = np.maximum(field, np.exp(-distance * min(width, height) ** 2 * .8))
            return colorize(field, palette)
        if name == "Electric Mandala":
            petals = np.sin(angle * 12 + np.sin(radius * 18 - t * speed) * 2)
            field = np.clip((petals + 1) / 2, 0, 1) * np.clip(1 - radius * .55, 0, 1)
            return colorize(field, palette)
        if name in ("FireKoz", "Fireworks"):
            if name == "FireKoz":
                heat = np.clip((1 - yy) * (.65 + .35 * np.sin(xx * 27 + t * speed * 3))
                               + self._rng.random((height, width)) * .12, 0, 1)
                return colorize(heat, palette)
            field = np.zeros((height, width), dtype=np.float32)
            period = 3.2 / max(.2, speed)
            cycle = t % period
            age = cycle / period
            if age < .78:
                for burst in range(3):
                    cx = .2 + burst * .3 + .08 * np.sin(t * .3 + burst)
                    cy = .25 + .2 * (burst % 2)
                    for spark in range(18):
                        theta = 2 * np.pi * spark / 18 + burst * .2
                        r = age * (.2 + .12 * ((spark * 7 + burst) % 5) / 5)
                        sx, sy = cx + np.cos(theta) * r, cy + np.sin(theta) * r
                        glow = np.exp(-((xx - sx) ** 2 + (yy - sy) ** 2) * (1600 / (1 + age * 12))) * (1 - age / .85)
                        field = np.maximum(field, glow)
            return colorize(np.clip(field, 0, 1), palette)
        if name in ("Flock", "Snake"):
            field = np.zeros((height, width), dtype=np.float32)
            count = 14 if name == "Flock" else 20
            for i in range(count):
                u = i / count
                if name == "Flock":
                    cx = .5 + .34 * np.sin(t * speed + u * 2 * np.pi) + .08 * np.sin(t * speed * 1.7 + u * 9)
                    cy = .5 + .30 * np.sin(t * speed * .8 + u * 2 * np.pi + 1.3)
                else:
                    phase = t * speed - u * 6
                    cx = .5 + .34 * np.sin(phase)
                    cy = .5 + .34 * np.sin(phase * 1.35 + .8)
                field = np.maximum(field, np.exp(-((xx - cx) ** 2 + (yy - cy) ** 2) * 950))
            return colorize(field, palette)
        if name == "Flow Field":
            flow = np.sin(nx * scale * 5 + np.sin(ny * 4 + t * speed) * 2)
            flow += np.sin(ny * scale * 7 - np.cos(nx * 3 - t * speed) * 2)
            return colorize((flow + 2) / 4, palette)
        if name in ("Green Scroll", "Aurora Rain"):
            column = np.arange(width, dtype=np.float32)[None, :]
            speed_scale = 6 if name == "Green Scroll" else 10
            head = np.mod(column * 7.13 + t * speed * speed_scale, height + 12)
            trail = np.mod(head - y * height, height + 12)
            intensity = np.clip(1 - trail / 10, 0, 1) * (np.sin((x * width + y * height * 2 + column) * 2.4) > .1)
            return colorize(intensity, palette)
        if name in ("Incremental Drift", "Incremental Drift 2", "Aurora Plasma"):
            a = np.sin(nx * (5 * scale) + t * speed)
            b = np.sin(ny * (7 * scale) - t * speed * .73)
            c = np.sin((nx + ny) * (4 * scale) + t * speed * .4)
            if name == "Incremental Drift 2":
                c = np.sin((nx - ny * 1.4) * (9 * scale) + t * speed * .31)
                v = (a + b + c + 3) / 6
            elif name == "Aurora Plasma":
                v = (np.sin(a * 3 + t) + np.sin(b * 3 - t * .7) + 2) / 4
            else:
                v = (a + b + c + 3) / 6
            return colorize(v, palette)
        if name == "Infinity":
            curve = np.abs(ny - .55 * np.sin(nx * 3.3 + t * speed))
            second = np.abs(ny + .55 * np.sin(nx * 3.3 + t * speed))
            field = np.maximum(np.exp(-curve * min(width, height) * .45), np.exp(-second * min(width, height) * .45))
            return colorize(field, palette)
        if name == "Julia Set Fractal":
            z = nx + 1j * ny
            c = complex(-.72 + .12 * np.sin(t * speed * .2), .21 + .18 * np.cos(t * speed * .17))
            escaped = np.zeros((height, width), dtype=np.float32)
            alive = np.ones((height, width), dtype=bool)
            for iteration in range(18):
                z[alive] = z[alive] * z[alive] + c
                newly = alive & (np.abs(z) > 2)
                escaped[newly] = iteration / 18
                alive[newly] = False
            escaped[alive] = 1
            return colorize(escaped, palette)
        if name == "Maze":
            gx, gy = np.floor(xx * width / 4), np.floor(yy * height / 4)
            walls = (np.sin(gx * 12.9898 + gy * 78.233 + np.floor(t * speed) * 37.719) > .1)
            edge = (np.mod(xx * width, 4) < 1) | (np.mod(yy * height, 4) < 1)
            return colorize((walls & edge).astype(np.float32), palette)
        if name == "Munch":
            xi, yi = np.meshgrid(np.arange(width), np.arange(height))
            phase = int(t * speed * 2)
            sweep = (t * speed * 8) % (2 * width)
            threshold = sweep if sweep <= width else 2 * width - sweep
            active = (xi ^ yi ^ phase) < threshold
            color_index = np.mod((xi ^ yi) * .035 + t * speed * .08, 1)
            return colorize(np.where(active, .2 + .8 * color_index, 0), palette)
        if name == "Noise Smearing":
            if self._noise_smear is None or self._noise_smear.shape != (height, width):
                self._noise_smear = self._rng.random((height, width), dtype=np.float32)
            self._noise_smear = np.roll(self._noise_smear, 1, axis=1)
            self._noise_smear = np.clip(self._noise_smear * .9 + self._rng.random((height, width), dtype=np.float32) * .1, 0, 1)
            return colorize(self._noise_smear, palette)
        if name == "Pendulum Wave":
            wave = np.zeros((height, width), dtype=np.float32)
            for i in range(14):
                center = .5 + .42 * np.sin(x * np.pi * 2 + t * speed * (0.5 + i * .035) + i * .48)
                wave = np.maximum(wave, np.exp(-((y - center) ** 2) * height * 5))
            return colorize(wave, palette)
        if name == "Radar":
            sweep = np.mod(angle - t * speed, 2 * np.pi)
            beam = np.exp(-sweep * 9) + np.exp(-(2 * np.pi - sweep) * 30)
            rings = np.exp(-np.abs(np.mod(radius * 8, 1) - .5) * 24)
            return colorize(np.clip(np.maximum(beam, rings * .25), 0, 1), palette)
        if name == "Simplex Noise":
            noise = np.sin(nx * 3 + t * speed) * np.sin(ny * 4 - t * speed * .7)
            noise += .45 * np.sin((nx + ny) * 9 + t * .23) + .2 * np.cos(nx * 17 - ny * 13)
            return colorize((noise + 1.65) / 3.3, palette)
        if name == "Spin":
            v = .5 + .5 * np.sin(angle * 8 + radius * 15 - t * speed * 3)
            return colorize(v, palette)
        if name == "Spiral":
            v = .5 + .5 * np.sin(angle * 4 - radius * 22 + t * speed * 2)
            return colorize(v, palette)
        if name == "Spiro":
            v = np.sin(angle * 7 + np.sin(angle * 3 + t * speed) * 3)
            field = np.exp(-((radius - (.38 + .12 * v)) ** 2) * 180)
            return colorize(field, palette)
        if name == "Starfield":
            if self._stars is None:
                self._stars = np.column_stack((self._rng.uniform(-1, 1, 90), self._rng.uniform(-1, 1, 90), self._rng.random(90))).astype(np.float32)
            depth = np.mod(self._stars[:, 2] + t * speed * .12, 1)
            factor = .08 + depth * .9
            px = np.rint(width * (.5 + self._stars[:, 0] * factor * .5)).astype(int)
            py = np.rint(height * (.5 + self._stars[:, 1] * factor * .5)).astype(int)
            valid = (px >= 0) & (px < width) & (py >= 0) & (py < height)
            out[py[valid], px[valid]] = colorize(depth[valid], palette)
            return out
        if name == "Test":
            out[:] = (128, 0, 0)
            return out
        if name == "Aurora Wave":
            field = np.zeros((height, width), dtype=np.float32)
            for i in range(5):
                center = .5 + .32 * np.sin(x * (2 + i * .55) * np.pi + t * speed * (.5 + i * .08) + i)
                field += np.exp(-((y - center) ** 2) * height * 5) / 5
            return colorize(np.clip(field, 0, 1), palette)
        return out

    @staticmethod
    def _load_font(family: str, size: int) -> ImageFont.FreeTypeFont | ImageFont.ImageFont:
        font_files = {
            "Arial": "arial.ttf", "Calibri": "calibri.ttf", "Consolas": "consola.ttf",
            "Courier New": "cour.ttf", "Georgia": "georgia.ttf", "Segoe UI": "segoeui.ttf",
            "Tahoma": "tahoma.ttf", "Times New Roman": "times.ttf", "Verdana": "verdana.ttf",
        }
        windows_fonts = Path(os.environ.get("WINDIR", "C:/Windows")) / "Fonts"
        candidate = windows_fonts / font_files.get(family, "")
        if candidate.is_file():
            return ImageFont.truetype(str(candidate), size=size)
        for candidate_name in (family, "DejaVuSans.ttf"):
            try:
                return ImageFont.truetype(candidate_name, size=size)
            except OSError:
                continue
        return ImageFont.load_default(size=size)

    def _render_scrolling_text(self, width: int, height: int, t: float, params: dict) -> np.ndarray:
        text = str(params["text"])
        if not text:
            return np.zeros((height, width, 3), dtype=np.uint8)
        font = self._load_font(str(params["font"]), int(params["font_size"]))
        measure = ImageDraw.Draw(Image.new("RGB", (1, 1)))
        bounds = measure.textbbox((0, 0), text, font=font)
        text_width, text_height = max(1, bounds[2] - bounds[0]), max(1, bounds[3] - bounds[1])
        direction = params["direction"]
        canvas = Image.new("RGB", (width, height), (0, 0, 0))
        draw = ImageDraw.Draw(canvas)
        color = (30, 220, 255)
        distance = int(t * float(params["speed"]))
        if direction in ("Left", "Right"):
            strip = Image.new("RGB", (text_width, height), (0, 0, 0))
            strip_draw = ImageDraw.Draw(strip)
            text_y = (height - text_height) // 2 - bounds[1]
            strip_draw.text((-bounds[0], text_y), text, font=font, fill=color)
            phase = distance % (width + text_width)
            x = width - phase if direction == "Left" else -text_width + phase
            canvas.paste(strip, (x, 0))
        else:
            strip = Image.new("RGB", (width, text_height), (0, 0, 0))
            strip_draw = ImageDraw.Draw(strip)
            text_x = (width - text_width) // 2 - bounds[0]
            strip_draw.text((text_x, -bounds[1]), text, font=font, fill=color)
            phase = distance % (height + text_height)
            y = height - phase if direction == "Up" else -text_height + phase
            canvas.paste(strip, (0, y))
        return np.asarray(canvas, dtype=np.uint8).copy()

    def _capture_screen(self, width: int, height: int, params: dict) -> np.ndarray:
        try:
            if self._capture_backend is None:
                import mss
                self._capture_backend = mss.MSS()
            capture = self._capture_backend
            monitors = capture.monitors
            monitor_index = max(1, min(int(params["monitor"]), len(monitors) - 1)) if len(monitors) > 1 else 0
            monitor = monitors[monitor_index]
            left = monitor["left"] + int(params["x"])
            top = monitor["top"] + int(params["y"])
            region_width = int(params["width"]) or monitor["width"] - int(params["x"])
            region_height = int(params["height"]) or monitor["height"] - int(params["y"])
            region = {"left": left, "top": top, "width": max(1, region_width), "height": max(1, region_height)}
            shot = np.asarray(capture.grab(region), dtype=np.uint8)[..., :3][..., ::-1]
            if np.any(shot):
                self._capture_black_frames = 0
                self.last_error = ""
            else:
                self._capture_black_frames += 1
                if self._capture_black_frames >= 30:
                    self.last_error = (
                        "Screen Capture is returning black frames. Verify the selected monitor/region; "
                        "Windows secure desktop, protected video, or a locked/remote session may block capture."
                    )
            ys = np.linspace(0, shot.shape[0] - 1, height).astype(np.intp)
            xs = np.linspace(0, shot.shape[1] - 1, width).astype(np.intp)
            return shot[ys[:, None], xs[None, :]].copy()
        except Exception as exc:
            self.last_error = f"Screen capture failed: {exc}"
            return np.zeros((height, width, 3), dtype=np.uint8)

    def close(self) -> None:
        if self._capture_backend is not None:
            try:
                self._capture_backend.close()
            except Exception:
                pass
            self._capture_backend = None
