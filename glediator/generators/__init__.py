from glediator.generators.builtin import BuiltinGenerator, GENERATORS


def generator_names() -> list[str]:
    return list(GENERATORS)


def create_generator(name: str) -> BuiltinGenerator:
    if name not in GENERATORS:
        raise ValueError(f"Unknown generator: {name}")
    return BuiltinGenerator(name)
