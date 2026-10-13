from glediator.effects.support import ConfiguredEffect, capture_screen, close_capture


class ScreenCaptureEffect(ConfiguredEffect):
    name = "Screen Capture"
    description = "Capture and scale a selected screen region."
    settings = {
        "monitor": ("int", 1, 1, 16, 1),
        "x": ("int", 0, 0, 8192, 1),
        "y": ("int", 0, 0, 8192, 1),
        "width": ("int", 0, 0, 8192, 1),
        "height": ("int", 0, 0, 8192, 1),
        "capture_fps": ("int", 8, 1, 30, 1),
    }

    def __init__(self):
        super().__init__()
        self._capture_backend = None
        self._capture_black_frames = 0

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        return capture_screen(self, width, height, self.parameters)

    def cleanup(self):
        close_capture(self)
