from glediator.effects.support import ConfiguredEffect, scrolling_text
from glediator.generators.palettes import PALETTE_NAMES


class ScrollingTextEffect(ConfiguredEffect):
    name = "Scrolling Text"
    description = "Scrolling text using a selected font and palette."
    settings = {
        "text": ("str", "GLEDIATOR", "", "", ""),
        "font": ("font", "Arial", "", "", ""),
        "font_size": ("int", 18, 6, 96, 1),
        "direction": ("choice", "Left", ("Left", "Right", "Up", "Down"), "", ""),
        "speed": ("float", 5.0, 0.0, 30.0, 0.5),
        "palette": ("choice", "Ocean", PALETTE_NAMES, "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        return scrolling_text(width, height, time_seconds, self.parameters)
