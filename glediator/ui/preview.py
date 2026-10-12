from __future__ import annotations

import numpy as np
from PySide6.QtCore import QSize, Qt
from PySide6.QtGui import QImage, QPixmap
from PySide6.QtWidgets import QLabel, QSizePolicy, QVBoxLayout, QWidget


class FramePreview(QWidget):
    def __init__(self, title: str, accent: str, matrix_width: int = 64, matrix_height: int = 32) -> None:
        super().__init__()
        self._aspect = max(1, matrix_width) / max(1, matrix_height)
        self._source_image: QImage | None = None
        self.setObjectName("previewCard")
        self.setStyleSheet(f"#previewCard {{ background:#111923; border:1px solid #263343; border-top:3px solid {accent}; border-radius:9px; }}")
        size_policy = QSizePolicy(QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Preferred)
        size_policy.setHeightForWidth(True)
        self.setSizePolicy(size_policy)
        # Give vertical box layouts a meaningful initial size. Without this hint,
        # some Qt styles use only the title's height and collapse the preview.
        self.setMinimumSize(240, 210)
        layout = QVBoxLayout(self)
        layout.setContentsMargins(14, 12, 14, 14)
        self.title = QLabel(title)
        self.title.setObjectName("previewTitle")
        self.image = QLabel("Waiting for first frame…")
        self.image.setAlignment(Qt.AlignmentFlag.AlignCenter)
        self.image.setMinimumSize(1, 1)
        self.image.setSizePolicy(QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Preferred)
        self.image.setStyleSheet("background:#080c11; border-radius:5px; color:#627386;")
        layout.addWidget(self.title)
        layout.addWidget(self.image, 1)

    def hasHeightForWidth(self) -> bool:
        return True

    def heightForWidth(self, width: int) -> int:
        layout = self.layout()
        margins = layout.contentsMargins()
        image_width = max(1, width - margins.left() - margins.right())
        return (margins.top() + margins.bottom() + self.title.sizeHint().height()
                + layout.spacing() + round(image_width / self._aspect))

    def sizeHint(self) -> QSize:
        width = 360
        return QSize(width, max(210, self.heightForWidth(width)))

    def minimumSizeHint(self) -> QSize:
        width = 240
        return QSize(width, max(210, self.heightForWidth(width)))

    def set_matrix_size(self, width: int, height: int) -> None:
        self._aspect = max(1, int(width)) / max(1, int(height))
        self._update_minimum_height(max(self.width(), 240))
        self.updateGeometry()
        self._activate_parent_layouts()

    def set_frame(self, frame: np.ndarray) -> None:
        height, width, _ = frame.shape
        self._source_image = QImage(frame.data, width, height, width * 3, QImage.Format.Format_RGB888).copy()
        self._render_image()

    def _render_image(self) -> None:
        if self._source_image is None or self.image.width() < 2 or self.image.height() < 2:
            return
        # Expand-to-fill then center crop: no letterbox bars if the host layout is
        # a few pixels off the target ratio; with the aspect-aware size hint it is
        # normally an exact fit.
        scaled = QPixmap.fromImage(self._source_image).scaled(
            self.image.size(), Qt.AspectRatioMode.KeepAspectRatioByExpanding,
            Qt.TransformationMode.SmoothTransformation)
        x = max(0, (scaled.width() - self.image.width()) // 2)
        y = max(0, (scaled.height() - self.image.height()) // 2)
        self.image.setPixmap(scaled.copy(x, y, self.image.width(), self.image.height()))

    def _update_minimum_height(self, width: int) -> None:
        desired = max(210, self.heightForWidth(width))
        if self.minimumHeight() != desired:
            self.setMinimumHeight(desired)

    def _activate_parent_layouts(self) -> None:
        parent = self.parentWidget()
        while parent is not None:
            parent.updateGeometry()
            if parent.layout() is not None:
                parent.layout().invalidate()
                parent.layout().activate()
            parent = parent.parentWidget()

    def resizeEvent(self, event) -> None:
        self._update_minimum_height(max(self.width(), 240))
        self._render_image()
        super().resizeEvent(event)
