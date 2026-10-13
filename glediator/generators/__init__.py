from glediator.effects import create_effect, effect_names


def generator_names() -> list[str]:
    """Compatibility name for the UI's effect picker."""
    return effect_names()


def create_generator(name: str):
    """Compatibility alias; each call returns a separately configured effect."""
    return create_effect(name)
