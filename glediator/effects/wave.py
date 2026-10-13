import numpy as np

from glediator.effects.support import ConfiguredEffect, coordinates
from glediator.generators.palettes import PALETTE_NAMES, blend_colors, colorize, parse_hex_color


class WaveEffect(ConfiguredEffect):
    name = "Wave"
    description = "A glowing, configurable travelling wave."
    settings = {
        "amplitude": ("float", 0.28, 0.0, 0.48, 0.01),
        "frequency": ("float", 1.25, 0.1, 8.0, 0.05),
        "position": ("float", 0.5, 0.0, 1.0, 0.01),
        "thickness": ("float", 0.10, 0.02, 0.4, 0.01),
        "speed": ("float", 2.0, 0.0, 10.0, 0.1),
        "palette": ("choice", "Aurora", PALETTE_NAMES + ("Custom",), "", ""),
        "color_a": ("color", "#35D9FF", "", "", ""),
        "color_b": ("color", "#FF58BD", "", "", ""),
        "background": ("color", "#030714", "", "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        x, y = coordinates(width, height)
        p = self.parameters
        center = p["position"] + p["amplitude"] * np.sin(x * p["frequency"] * 2 * np.pi + time_seconds * p["speed"])
        glow = np.exp(-((y - center) ** 2) / max(1e-4, p["thickness"] ** 2))
        if p["palette"] == "Custom":
            rgb = blend_colors(p["color_a"], p["color_b"], np.broadcast_to(x, (height, width)))
        else:
            rgb = colorize(np.broadcast_to((x + 1) / 2, (height, width)), p["palette"])
        background = parse_hex_color(p["background"])
        return np.clip(rgb.astype(np.float32) * glow[..., None]
                       + background * (1 - glow[..., None]), 0, 255).astype(np.uint8)
