from __future__ import annotations

from glediator.core.scene import Scene


class SceneList:
    """Small scene collection with a 300-scene safety limit."""

    def __init__(self, width: int = 64, height: int = 32) -> None:
        self.scenes = [Scene("Scene 1", width, height), Scene("Scene 2", width, height)]
        self.selected = 0

    def add(self, width: int, height: int) -> Scene:
        if len(self.scenes) >= 300:
            raise ValueError("A project may contain at most 300 scenes")
        scene = Scene(f"Scene {len(self.scenes) + 1}", width, height)
        self.scenes.append(scene)
        return scene
