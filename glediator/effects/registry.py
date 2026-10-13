"""Discovers effect classes from modules in this package at application boot."""

from __future__ import annotations

import importlib
import inspect
import pkgutil
from typing import Type

from glediator.effects.base import Effect


_EFFECT_CLASSES: dict[str, Type[Effect]] = {}


def load_effects() -> dict[str, Type[Effect]]:
    """Import effect modules and index their concrete Effect subclasses."""
    package = importlib.import_module("glediator.effects")
    for module_info in pkgutil.iter_modules(package.__path__):
        if module_info.name.startswith("_") or module_info.name == "registry":
            continue
        module = importlib.import_module(f"{package.__name__}.{module_info.name}")
        for _, candidate in inspect.getmembers(module, inspect.isclass):
            if candidate is Effect or not issubclass(candidate, Effect) or inspect.isabstract(candidate):
                continue
            name = getattr(candidate, "name", "")
            description = getattr(candidate, "description", "")
            if not isinstance(name, str) or not name.strip():
                continue
            if name in _EFFECT_CLASSES and _EFFECT_CLASSES[name] is not candidate:
                raise ValueError(f"Duplicate effect name: {name}")
            if not isinstance(description, str):
                raise TypeError(f"Effect {name!r} must provide a string description")
            _EFFECT_CLASSES[name] = candidate
    return dict(_EFFECT_CLASSES)


def effect_names() -> list[str]:
    return list(load_effects())


def effect_descriptions() -> dict[str, str]:
    return {name: cls.description for name, cls in load_effects().items()}


def effect_settings(name: str) -> dict:
    effect_class = load_effects().get(name)
    if effect_class is None:
        return {}
    settings = getattr(effect_class, "settings", {})
    if isinstance(settings, dict):
        return settings
    return {}


def default_effect_parameters(name: str) -> dict:
    from glediator.generators.settings import default_parameters

    return default_parameters(name, effect_settings(name))


def sanitize_effect_parameters(name: str, values: dict | None) -> dict:
    from glediator.generators.settings import sanitize_parameters

    schema = effect_settings(name)
    if schema:
        return sanitize_parameters(name, values, schema)
    # Effects without a UI schema own their validation in configure(); keep
    # JSON-style values intact so their configuration survives save/restore.
    return dict(values) if isinstance(values, dict) else {}


def create_effect(name: str) -> Effect:
    effect_class = load_effects().get(name)
    if effect_class is None:
        raise ValueError(f"Unknown effect: {name}")
    instance = effect_class()
    instance.setup()
    return instance

