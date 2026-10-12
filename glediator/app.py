from __future__ import annotations

import sys

from PySide6.QtWidgets import QApplication

from glediator.core.config import EngineConfig
from glediator.core.engine import RenderEngine
from glediator.ui.main_window import MainWindow


def main() -> None:
    app = QApplication(sys.argv)
    app.setApplicationName("Glediator")
    app.setOrganizationName("Glediator")
    config = EngineConfig.load()
    engine = RenderEngine(config)
    window = MainWindow(engine, config)
    window.show()
    engine.start()
    app.aboutToQuit.connect(engine.stop)
    sys.exit(app.exec())
