from __future__ import annotations

import json
from dataclasses import asdict, dataclass, field
from pathlib import Path
from urllib.parse import urlsplit


CONFIG_PATH = Path.home() / ".glediator" / "settings.json"


@dataclass
class EngineConfig:
    width: int = 64
    height: int = 32
    fps: int = 60
    websocket_host: str = "127.0.0.1"
    websocket_port: int = 12345
    mapping: str = "horizontal-snake"
    presets: list[dict] = field(default_factory=list)
    live_state: dict = field(default_factory=dict)

    @classmethod
    def load(cls, path: Path = CONFIG_PATH) -> "EngineConfig":
        try:
            values = json.loads(path.read_text(encoding="utf-8"))
            fields = asdict(cls()).keys()
            config = cls(**{key: values[key] for key in fields if key in values})
            if "websocket_url" in values and "websocket_host" not in values:
                parsed = urlsplit(values["websocket_url"])
                config.websocket_host = parsed.hostname or config.websocket_host
                config.websocket_port = parsed.port or config.websocket_port
            config.validate()
            return config
        except (OSError, ValueError, TypeError, json.JSONDecodeError):
            return cls()

    def save(self, path: Path = CONFIG_PATH) -> None:
        self.validate()
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(json.dumps(asdict(self), indent=2), encoding="utf-8")

    def validate(self) -> None:
        self.width = max(8, min(512, int(self.width)))
        self.height = max(8, min(512, int(self.height)))
        self.fps = max(1, min(120, int(self.fps)))
        self.websocket_host = str(self.websocket_host).strip() or "127.0.0.1"
        self.websocket_port = max(1, min(65535, int(self.websocket_port)))
        self.presets = [preset for preset in self.presets if isinstance(preset, dict) and preset.get("name")]

    @property
    def websocket_url(self) -> str:
        host = self.websocket_host
        if ":" in host and not host.startswith("["):
            host = f"[{host}]"
        return f"ws://{host}:{self.websocket_port}"
