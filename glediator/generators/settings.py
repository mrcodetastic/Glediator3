"""Compatibility helpers for applying effect-owned parameter schemas.

Settings now live with each effect class. These functions remain available to
older callers but no longer maintain a separate central effect catalogue.
"""

from __future__ import annotations


def _settings_for(effect_name: str, settings: dict | None) -> dict:
    if settings is not None:
        return settings
    from glediator.effects import effect_settings

    return effect_settings(effect_name)


def default_parameters(effect_name: str, settings: dict | None = None) -> dict:
    schema = _settings_for(effect_name, settings)
    return {name: spec[1] for name, spec in schema.items()}


def sanitize_parameters(effect_name: str, values: dict | None, settings: dict | None = None) -> dict:
    schema = _settings_for(effect_name, settings)
    result = default_parameters(effect_name, schema)
    if not isinstance(values, dict):
        return result
    for name, spec in schema.items():
        if name not in values:
            continue
        kind, default, low, high, _step = spec
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
