"""Parameter metadata used by generator settings dialogs and persistence."""

from glediator.generators.palettes import PALETTE_NAMES


AURORA_PATTERN_NAMES = (
    "Attract", "Bounce", "Cube", "Electric Mandala", "FireKoz", "Fireworks", "Flock",
    "Flow Field", "Green Scroll", "Incremental Drift", "Incremental Drift 2", "Infinity",
    "Julia Set Fractal", "Maze", "Munch", "Noise Smearing", "Pendulum Wave", "Aurora Plasma",
    "Radar", "Aurora Rain", "Simplex Noise", "Snake", "Spin", "Spiral", "Spiro", "Starfield",
    "Test", "Aurora Wave",
)

GENERATOR_SETTINGS = {
    "Black": {},
    "Plasma": {"scale": ("float", 8.0, 2.0, 24.0, 0.5), "speed": ("float", 1.0, 0.1, 5.0, 0.1), "palette": ("choice", "Aurora", PALETTE_NAMES, "", "")},
    "Fire": {"cooling": ("float", 1.0, 0.3, 2.0, 0.1), "sparks": ("float", 0.12, 0.0, 0.5, 0.01), "palette": ("choice", "Fire", PALETTE_NAMES, "", "")},
    "Grid": {"spacing": ("int", 8, 2, 32, 1), "speed": ("float", 3.0, 0.0, 20.0, 0.5), "palette": ("choice", "Neon", PALETTE_NAMES, "", "")},
    "Wave": {
        "amplitude": ("float", 0.28, 0.0, 0.48, 0.01), "frequency": ("float", 1.25, 0.1, 8.0, 0.05),
        "position": ("float", 0.5, 0.0, 1.0, 0.01), "thickness": ("float", 0.10, 0.02, 0.4, 0.01),
        "speed": ("float", 2.0, 0.0, 10.0, 0.1), "palette": ("choice", "Aurora", PALETTE_NAMES + ("Custom",), "", ""),
        "color_a": ("color", "#35D9FF", "", "", ""), "color_b": ("color", "#FF58BD", "", "", ""),
        "background": ("color", "#030714", "", "", ""),
    },
    "Random Pixel": {
        "spawn_rate": ("int", 22, 1, 500, 1), "fade_duration": ("float", 1.8, 0.1, 15.0, 0.1),
        "palette": ("choice", "Rainbow", PALETTE_NAMES, "", ""),
    },
    "Knight Rider": {"speed": ("float", 1.7, 0.1, 8.0, 0.1), "trail": ("float", 0.11, 0.02, 0.5, 0.01)},
    "Meta Balls": {"count": ("int", 4, 1, 8, 1), "speed": ("float", 0.7, 0.1, 3.0, 0.1), "radius": ("float", 0.20, 0.05, 0.45, 0.01), "palette": ("choice", "Aurora", PALETTE_NAMES, "", "")},
    "Fading Pixel": {"density": ("float", 0.01, 0.001, 0.1, 0.001), "decay": ("float", 0.78, 0.4, 0.99, 0.01), "palette": ("choice", "Rainbow", PALETTE_NAMES, "", "")},
    "Falling Objects": {"speed": ("float", 10.0, 1.0, 40.0, 1.0), "density": ("float", 1.1, 0.1, 4.0, 0.1), "palette": ("choice", "Aurora", PALETTE_NAMES, "", "")},
    "Expanding Circles": {"speed": ("float", 5.0, 0.5, 20.0, 0.5), "spacing": ("float", 7.0, 2.0, 20.0, 0.5), "palette": ("choice", "Neon", PALETTE_NAMES, "", "")},
    "Scrolling Text": {
        "text": ("str", "GLEDIATOR", "", "", ""),
        "font": ("font", "Arial", "", "", ""),
        "font_size": ("int", 18, 6, 96, 1),
        "direction": ("choice", "Left", ("Left", "Right", "Up", "Down"), "", ""),
        "speed": ("float", 5.0, 0.0, 30.0, 0.5),
        "palette": ("choice", "Ocean", PALETTE_NAMES, "", ""),
    },
    "VU Meter": {
        "sensitivity": ("float", 2.5, 0.2, 8.0, 0.1),
        "source": ("choice", "System Output", ("System Output", "Microphone"), "", ""),
    },
    "Simple Spectrum": {
        "sensitivity": ("float", 4.0, 0.2, 12.0, 0.1),
        "source": ("choice", "System Output", ("System Output", "Microphone"), "", ""),
        "palette": ("choice", "Aurora", PALETTE_NAMES, "", ""),
    },
    "Screen Capture": {
        "monitor": ("int", 1, 1, 16, 1), "x": ("int", 0, 0, 8192, 1), "y": ("int", 0, 0, 8192, 1),
        "width": ("int", 0, 0, 8192, 1), "height": ("int", 0, 0, 8192, 1),
    },
}

for _pattern_name in AURORA_PATTERN_NAMES:
    GENERATOR_SETTINGS[_pattern_name] = {
        "speed": ("float", 1.0, 0.1, 8.0, 0.1),
        "scale": ("float", 1.0, 0.1, 8.0, 0.1),
        "palette": ("choice", "Aurora", PALETTE_NAMES, "", ""),
    }


def default_parameters(generator_name: str) -> dict:
    return {name: spec[1] for name, spec in GENERATOR_SETTINGS.get(generator_name, {}).items()}


def sanitize_parameters(generator_name: str, values: dict | None) -> dict:
    result = default_parameters(generator_name)
    if not isinstance(values, dict):
        return result
    for name, spec in GENERATOR_SETTINGS.get(generator_name, {}).items():
        if name not in values:
            continue
        kind, default, low, high, _ = spec
        try:
            if kind in ("str", "font", "color"):
                result[name] = str(values[name])[:120]
            elif kind == "choice":
                result[name] = values[name] if values[name] in low else default
            elif kind == "int":
                result[name] = max(low, min(high, int(values[name])))
            else:
                result[name] = max(low, min(high, float(values[name])))
        except (TypeError, ValueError):
            result[name] = default
    return result
