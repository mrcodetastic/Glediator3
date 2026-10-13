import numpy as np

from glediator.effects.support import ConfiguredEffect


class VUMeterEffect(ConfiguredEffect):
    name = "VU Meter"
    description = "Audio-level meter with a peak indicator."
    settings = {
        "sensitivity": ("float", 2.5, 0.2, 8.0, 0.1),
        "auto_sensitivity": ("choice", "Off", ("Off", "On"), "", ""),
        "source": ("choice", "System Output", ("System Output", "Microphone"), "", ""),
    }

    def drawFrame(self, width, height, time_seconds, audio_state=None):
        p = dict(self.parameters)
        if audio_state:
            p.update(audio_state)
        level, peak = float(p.get("audio_level", 0)), float(p.get("audio_peak", 0))
        bar_height = int(np.clip(level * p["sensitivity"], 0, 1) * height)
        rows = np.arange(height)[:, None]
        active = rows >= height - bar_height
        red = np.where(rows < height * .22, 255, np.where(rows < height * .5, 255, 30))
        green = np.where(rows < height * .22, 25, 210)
        rgb = np.where(active[..., None], np.stack((red, green, np.zeros_like(red)), axis=-1), 0)
        out = np.broadcast_to(rgb, (height, width, 3)).copy().astype(np.uint8)
        peak_row = height - 1 - int(np.clip(peak * p["sensitivity"], 0, 1) * max(0, height - 1))
        if peak > 0 and 0 <= peak_row < height:
            out[peak_row, :, :] = (255, 255, 255)
        return out
