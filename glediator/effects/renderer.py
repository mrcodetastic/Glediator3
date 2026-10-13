"""Deprecated compatibility adapter for the old BuiltinGenerator API.

Runtime scenes create effect classes directly from the effect registry. This
adapter only exists for clients that still call ``BuiltinGenerator.render``.
"""

from __future__ import annotations


class BuiltinGenerator:
    def __init__(self, name: str) -> None:
        from glediator.effects import create_effect

        self.name = name
        self._effect = create_effect(name)

    def render(self, width: int, height: int, t: float, parameters: dict | None = None,
               audio_state: dict | None = None):
        if parameters is not None:
            self._effect.configure(parameters)
        return self._effect.drawFrame(width, height, t, audio_state)

    @property
    def last_error(self) -> str:
        return getattr(self._effect, "last_error", "")

    def close(self) -> None:
        self._effect.cleanup()

    def __getattr__(self, name: str):
        return getattr(self._effect, name)

    def __setattr__(self, name: str, value) -> None:
        if name in {"name", "_effect"} or "_effect" not in self.__dict__:
            object.__setattr__(self, name, value)
        elif hasattr(self._effect, name):
            setattr(self._effect, name, value)
        else:
            object.__setattr__(self, name, value)


def __getattr__(name: str):
    if name == "GENERATORS":
        from glediator.effects.registry import load_effects

        return {effect_name: effect_name.lower().replace(" ", "_") for effect_name in load_effects()}
    raise AttributeError(name)
