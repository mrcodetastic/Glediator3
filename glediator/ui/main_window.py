from __future__ import annotations

from PySide6.QtCore import QSize, Qt, QTimer
from PySide6.QtGui import QColor, QFont, QIntValidator
from PySide6.QtWidgets import (QAbstractItemView, QCheckBox, QComboBox, QDialog, QDialogButtonBox, QDoubleSpinBox, QFormLayout,
                               QColorDialog, QFontComboBox, QFrame, QGroupBox, QHBoxLayout,
                               QInputDialog, QLabel, QLineEdit, QListWidget, QListWidgetItem, QMainWindow, QMessageBox,
                               QPushButton, QSlider, QSpinBox, QVBoxLayout, QWidget)

from glediator.core.config import EngineConfig
from glediator.core.engine import RenderEngine
from glediator.generators import generator_names
from glediator.generators.settings import GENERATOR_SETTINGS, default_parameters
from glediator.mapper import MAPPING_NAMES
from glediator.network.discovery import DeviceDiscovery
from glediator.output.mrcodetastic_websocket import MrCodetasticWebSocketOutput
from glediator.ui.preview import FramePreview


class EffectPickerDialog(QDialog):
    """Immediate, visible generator list; double-click an entry to choose it."""

    def __init__(self, parent: QWidget, names: list[str]) -> None:
        super().__init__(parent)
        self.setWindowTitle("Add effect")
        self.setMinimumSize(320, 420)
        layout = QVBoxLayout(self)
        hint = QLabel("Double-click an effect to add it to this source.")
        hint.setWordWrap(True)
        layout.addWidget(hint)
        self.effects = QListWidget()
        self.effects.addItems(names)
        self.effects.itemDoubleClicked.connect(self._choose)
        layout.addWidget(self.effects, 1)
        buttons = QDialogButtonBox(QDialogButtonBox.StandardButton.Cancel)
        buttons.rejected.connect(self.reject)
        layout.addWidget(buttons)
        self.selected_effect: str | None = None

    def _choose(self, item: QListWidgetItem) -> None:
        self.selected_effect = item.text()
        self.accept()


class ColorButton(QPushButton):
    def __init__(self, value: str, parent: QWidget | None = None) -> None:
        super().__init__(parent)
        self.color_value = value
        self.clicked.connect(self._choose_color)
        self._update_swatch()

    def _update_swatch(self) -> None:
        self.setText(self.color_value.upper())
        self.setStyleSheet(f"background:{self.color_value}; color:#101820; border:1px solid #647486; padding:6px;")

    def _choose_color(self) -> None:
        color = QColorDialog.getColor(QColor(self.color_value), self, "Choose effect color")
        if color.isValid():
            self.color_value = color.name(QColor.NameFormat.HexRgb)
            self._update_swatch()


class MainWindow(QMainWindow):
    def __init__(self, engine: RenderEngine, config: EngineConfig) -> None:
        super().__init__()
        self.engine, self.config = engine, config
        self.output: MrCodetasticWebSocketOutput | None = None
        self.source_widgets: dict[str, dict] = {}
        self.discovered_ips: set[str] = set()
        self.device_connect_buttons: dict[str, QPushButton] = {}
        self._connection_button: QPushButton | None = None
        self._connected_device_ip: str | None = None
        self.setWindowTitle("Glediator — LED Matrix Studio")
        self.resize(1260, 780)
        self._build_menu()
        self._build_ui()
        self.fader_slider.setValue(round(self.engine.fader * 100))
        self._sync_source_controls("left")
        self._sync_source_controls("right")
        self._apply_style()
        self.discovery = DeviceDiscovery()
        self.discovery.start()
        self.statusBar().showMessage("Listening for CODETASTIC beacons on UDP 20586")
        self.timer = QTimer(self)
        self.timer.timeout.connect(self._refresh)
        self.timer.start(33)

    def _build_menu(self) -> None:
        file_menu = self.menuBar().addMenu("&File")
        about_action = file_menu.addAction("About")
        about_action.triggered.connect(self._show_about)
        panel_config_action = file_menu.addAction("LED Panel Config…")
        panel_config_action.triggered.connect(self._show_panel_config)
        file_menu.addSeparator()
        save_action = file_menu.addAction("Save Settings")
        save_action.setShortcut("Ctrl+S")
        save_action.triggered.connect(self._save_settings)
        file_menu.addSeparator()
        exit_action = file_menu.addAction("Exit")
        exit_action.setShortcut("Alt+F4")
        exit_action.triggered.connect(self.close)

    def _show_about(self) -> None:
        QMessageBox.about(self, "About Glediator", "<b>Glediator LED Matrix Studio</b><br>Python + Qt6 rebuild<br><br>Live effects, scene presets and MrCodetastic WebSocket output.<br><br>2026 Vibe coded by whatever LLM MrCodeTastic wanted to experiment with")

    def _save_settings(self) -> None:
        if hasattr(self, "websocket_host"):
            self.config.websocket_host = self.websocket_host.text().strip() or self.config.websocket_host
            try:
                self.config.websocket_port = int(self.websocket_port.text())
            except ValueError:
                pass
        self.config.live_state = self.engine.export_state()
        self.config.save()
        self.statusBar().showMessage("Settings, live sources and scene presets saved", 4000)

    def _show_panel_config(self) -> None:
        dialog = QDialog(self)
        dialog.setWindowTitle("LED Panel Config")
        form = QFormLayout(dialog)
        width = QSpinBox()
        width.setRange(8, 512)
        width.setValue(self.config.width)
        height = QSpinBox()
        height.setRange(8, 512)
        height.setValue(self.config.height)
        mapping = QComboBox()
        for key, label in MAPPING_NAMES.items():
            mapping.addItem(label, key)
        current = mapping.findData(self.config.mapping)
        if current >= 0:
            mapping.setCurrentIndex(current)
        form.addRow("Matrix width", width)
        form.addRow("Matrix height", height)
        form.addRow("Pixel order", mapping)
        buttons = QDialogButtonBox(QDialogButtonBox.StandardButton.Apply | QDialogButtonBox.StandardButton.Cancel)
        buttons.button(QDialogButtonBox.StandardButton.Apply).clicked.connect(dialog.accept)
        buttons.rejected.connect(dialog.reject)
        form.addRow(buttons)
        if dialog.exec() != QDialog.DialogCode.Accepted:
            return
        self.engine.set_matrix_size(width.value(), height.value())
        self.config.width, self.config.height = width.value(), height.value()
        self.config.mapping = mapping.currentData()
        if self.output:
            self.output.mapping = self.config.mapping
        for preview in (self.left_preview, self.right_preview, self.mix_preview):
            preview.set_matrix_size(self.config.width, self.config.height)
        self._relayout_previews()
        self._save_settings()
        self.statusBar().showMessage(
            f"LED panel set to {self.config.width} × {self.config.height} · {mapping.currentText()}", 4000)

    def _build_ui(self) -> None:
        root = QWidget()
        self.setCentralWidget(root)
        layout = QVBoxLayout(root)
        layout.setContentsMargins(24, 5, 24, 16)
        header = QHBoxLayout()
        title = QLabel("GLEDIATOR")
        title.setObjectName("appTitle")
        subtitle = QLabel("REAL-TIME MATRIX STUDIO")
        subtitle.setObjectName("subtitle")
        header.addWidget(title)
        header.addWidget(subtitle, 0, Qt.AlignmentFlag.AlignVCenter)
        header.addStretch()
        self.fps_label = QLabel("●  Starting renderer")
        self.fps_label.setObjectName("status")
        header.addWidget(self.fps_label)
        layout.addLayout(header)
        divider = QFrame()
        divider.setFrameShape(QFrame.Shape.HLine)
        divider.setFrameShadow(QFrame.Shadow.Plain)
        divider.setObjectName("headerDivider")
        layout.addWidget(divider)

        preset_row = QHBoxLayout()
        preset_row.addWidget(QLabel("LOAD / SAVE SCENE"))
        self.preset_combo = QComboBox()
        self.preset_combo.setMinimumWidth(220)
        self._refresh_presets()
        preset_row.addWidget(self.preset_combo)
        save_preset = QPushButton("Save as preset…")
        save_preset.clicked.connect(self._save_preset)
        load_preset = QPushButton("Load preset")
        load_preset.clicked.connect(self._load_preset)
        delete_preset = QPushButton("Delete")
        delete_preset.clicked.connect(self._delete_preset)
        preset_row.addWidget(save_preset)
        preset_row.addWidget(load_preset)
        preset_row.addWidget(delete_preset)
        preset_row.addStretch(1)
        layout.addLayout(preset_row)

        columns = QHBoxLayout()
        columns.setSpacing(14)
        self.left_preview = FramePreview("LIVE A  ·  SOURCE A", "#3c9eff", self.config.width, self.config.height)
        self.right_preview = FramePreview("LIVE B  ·  SOURCE B", "#c56bff", self.config.width, self.config.height)
        self.mix_preview = FramePreview("FINAL OUTPUT", "#ffb84a", self.config.width, self.config.height)

        source_a_column = QVBoxLayout()
        source_a_column.setSpacing(10)
        source_a_column.addWidget(self.left_preview)
        source_a_column.addWidget(self._source_controls("A", "left"))
        source_a_column.addStretch(1)
        source_b_column = QVBoxLayout()
        source_b_column.setSpacing(10)
        source_b_column.addWidget(self.right_preview)
        source_b_column.addWidget(self._source_controls("B", "right"))
        source_b_column.addStretch(1)

        output_column = QVBoxLayout()
        output_column.setSpacing(10)
        output_column.addWidget(self.mix_preview)
        output_panel = QGroupBox("FINAL OUTPUT")
        output_layout = QVBoxLayout(output_panel)
        output_layout.addWidget(QLabel("Crossfade between Source A and Source B"))
        fader_line = QHBoxLayout()
        fader_line.addWidget(QLabel("A"))
        self.fader_slider = QSlider(Qt.Orientation.Horizontal)
        self.fader_slider.setRange(0, 100)
        self.fader_slider.setValue(50)
        self.fader_slider.valueChanged.connect(lambda v: self.engine.set_fader(v / 100))
        fader_line.addWidget(self.fader_slider, 1)
        fader_line.addWidget(QLabel("B"))
        output_layout.addLayout(fader_line)
        output_column.addWidget(output_panel)

        network_panel = QGroupBox("NETWORK OUTPUT")
        network_layout = QVBoxLayout(network_panel)
        self.device_list = QListWidget()
        self.device_list.setMinimumHeight(68)
        self.device_list.setMaximumHeight(170)
        self.device_list.setSelectionMode(QAbstractItemView.SelectionMode.NoSelection)
        self.device_hint = QLabel("Searching for CODETASTIC devices…")
        self.device_hint.setWordWrap(True)
        network_layout.addWidget(self.device_hint)
        network_layout.addWidget(self.device_list)

        self.manual_toggle = QCheckBox("Connect manually")
        self.manual_toggle.toggled.connect(self._show_manual_connection)
        network_layout.addWidget(self.manual_toggle)
        self.manual_form = QWidget()
        manual_row = QHBoxLayout(self.manual_form)
        manual_row.setContentsMargins(0, 0, 0, 0)
        self.websocket_host = QLineEdit(self.config.websocket_host)
        self.websocket_host.setPlaceholderText("IP address or host")
        self.websocket_host.setMaxLength(253)
        self.websocket_host.setMinimumWidth(self.fontMetrics().horizontalAdvance("0" * 16) + 20)
        self.websocket_port = QLineEdit(str(self.config.websocket_port))
        self.websocket_port.setValidator(QIntValidator(1, 65535, self.websocket_port))
        self.websocket_port.setMaxLength(5)
        self.websocket_port.setFixedWidth(self.fontMetrics().horizontalAdvance("0" * 5) + 20)
        self.manual_connect_button = QPushButton("Connect")
        self.manual_connect_button.setFixedWidth(98)
        self.manual_connect_button.setMinimumHeight(34)
        self.manual_connect_button.clicked.connect(self._connect_manually)
        manual_row.addWidget(self.websocket_host, 1)
        manual_row.addWidget(QLabel(":"))
        manual_row.addWidget(self.websocket_port)
        manual_row.addWidget(self.manual_connect_button)
        self.manual_form.setVisible(False)
        network_layout.addWidget(self.manual_form)
        output_column.addWidget(network_panel)
        output_column.addStretch(1)

        columns.addLayout(source_a_column, 1)
        columns.addLayout(source_b_column, 1)
        columns.addLayout(output_column, 1)
        layout.addLayout(columns, 1)

        self.statusBar().showMessage("Ready · output is disabled until connected")

    def _source_controls(self, label: str, side: str) -> QWidget:
        panel = QGroupBox(f"SOURCE {label}")
        layout = QVBoxLayout(panel)
        effects = QListWidget()
        effects.setMinimumHeight(125)
        effects.setMaximumHeight(175)
        effects.setToolTip("Select an effect to edit it; double-click to open its detailed settings.")
        effects.currentItemChanged.connect(lambda current, previous, s=side: self._sync_source_controls(s))
        effects.itemChanged.connect(lambda item, s=side: self._effect_item_changed(s, item))
        effects.itemDoubleClicked.connect(lambda item, s=side: self._show_generator_settings(
            s, int(item.data(Qt.ItemDataRole.UserRole))))
        layout.addWidget(effects)

        actions = QHBoxLayout()
        add_button = QPushButton("+ Add effect")
        add_button.clicked.connect(lambda checked=False, s=side: self._add_effect(s))
        remove_button = QPushButton("Remove")
        remove_button.clicked.connect(lambda checked=False, s=side: self._remove_effect(s))
        up_button = QPushButton("↑")
        up_button.setToolTip("Move selected effect up")
        up_button.clicked.connect(lambda checked=False, s=side: self._move_effect(s, -1))
        down_button = QPushButton("↓")
        down_button.setToolTip("Move selected effect down")
        down_button.clicked.connect(lambda checked=False, s=side: self._move_effect(s, 1))
        actions.addWidget(add_button, 1)
        actions.addWidget(remove_button)
        actions.addWidget(up_button)
        actions.addWidget(down_button)
        layout.addLayout(actions)

        form = QFormLayout()
        layer_filter = QComboBox()
        layer_filter.addItems(["None", "Invert", "Monochrome", "Threshold"])
        layer_filter.currentTextChanged.connect(lambda name, s=side: self._update_selected_layer(s, filter_name=name))
        form.addRow("Filter", layer_filter)
        blend = QComboBox()
        blend.addItems(["Add", "Subtract", "Multiply", "Difference", "Screen", "Maximum"])
        blend.currentTextChanged.connect(lambda name, s=side: self._update_selected_layer(s, mix_mode=name))
        form.addRow("Layer blend", blend)
        brightness = QSpinBox()
        brightness.setRange(0, 100)
        brightness.setValue(100)
        brightness.setSuffix(" %")
        brightness.valueChanged.connect(lambda value, s=side: self._update_selected_layer(s, brightness=value))
        form.addRow("Brightness", brightness)
        settings_button = QPushButton("Effect settings…")
        settings_button.clicked.connect(lambda checked=False, s=side: self._edit_selected_effect(s))
        form.addRow(settings_button)
        layout.addLayout(form)
        self.source_widgets[side] = {"list": effects, "add": add_button, "remove": remove_button,
                                     "up": up_button, "down": down_button, "filter": layer_filter,
                                     "blend": blend, "brightness": brightness, "settings": settings_button}
        self._refresh_effect_list(side)
        return panel

    def _refresh_presets(self, selected: str | None = None) -> None:
        if not hasattr(self, "preset_combo"):
            return
        self.preset_combo.blockSignals(True)
        self.preset_combo.clear()
        for preset in self.config.presets:
            self.preset_combo.addItem(preset["name"])
        if selected:
            index = self.preset_combo.findText(selected)
            if index >= 0:
                self.preset_combo.setCurrentIndex(index)
        self.preset_combo.blockSignals(False)

    def _save_preset(self) -> None:
        name, accepted = QInputDialog.getText(self, "Save scene preset", "Preset name:")
        name = name.strip()
        if not accepted or not name:
            return
        preset = {"name": name, "state": self.engine.export_state(), "mapping": self.config.mapping}
        existing = next((i for i, entry in enumerate(self.config.presets) if entry["name"].casefold() == name.casefold()), None)
        if existing is None:
            self.config.presets.append(preset)
        else:
            self.config.presets[existing] = preset
        self._refresh_presets(name)
        self._save_settings()

    def _load_preset(self) -> None:
        name = self.preset_combo.currentText()
        preset = next((entry for entry in self.config.presets if entry["name"] == name), None)
        if not preset:
            return
        self.engine.restore_state(preset.get("state", {}))
        self.config.width, self.config.height = self.engine.config.width, self.engine.config.height
        for preview in (self.left_preview, self.right_preview, self.mix_preview):
            preview.set_matrix_size(self.config.width, self.config.height)
        self._relayout_previews()
        self.fader_slider.setValue(round(self.engine.fader * 100))
        if preset.get("mapping") in MAPPING_NAMES:
            self.config.mapping = preset["mapping"]
            if self.output:
                self.output.mapping = self.config.mapping
        for side in ("left", "right"):
            self._refresh_effect_list(side)
        self._save_settings()
        self.statusBar().showMessage(f"Loaded scene preset: {name}", 3000)

    def _relayout_previews(self) -> None:
        central = self.centralWidget()
        if central and central.layout():
            central.layout().invalidate()
            central.layout().activate()
        self.updateGeometry()
        # Preserve the current width but grow the window if a square/tall panel
        # aspect needs more vertical room for the live matrix previews.
        required_height = self.minimumSizeHint().height()
        if required_height > self.height():
            screen = self.screen()
            if screen:
                required_height = min(required_height, screen.availableGeometry().height())
            self.resize(self.width(), required_height)

    def _delete_preset(self) -> None:
        name = self.preset_combo.currentText()
        if not name:
            return
        self.config.presets = [entry for entry in self.config.presets if entry["name"] != name]
        self._refresh_presets()
        self._save_settings()

    def _sync_source_controls(self, side: str) -> None:
        scene = self.engine.left_scene if side == "left" else self.engine.right_scene
        controls = self.source_widgets[side]
        item = controls["list"].currentItem()
        layer_index = int(item.data(Qt.ItemDataRole.UserRole)) if item else None
        controls["add"].setEnabled(sum(layer.configured for layer in scene.layers) < 5)
        for key in ("remove", "settings"):
            controls[key].setEnabled(item is not None)
        row = controls["list"].currentRow()
        controls["up"].setEnabled(item is not None and row > 0)
        controls["down"].setEnabled(item is not None and row < controls["list"].count() - 1)
        if layer_index is None:
            for key in ("filter", "blend", "brightness"):
                controls[key].setEnabled(False)
            return
        layer = scene.layers[layer_index]
        for key, value in (("filter", layer.filter_name), ("blend", layer.mix_mode)):
            widget = controls[key]
            widget.blockSignals(True)
            widget.setCurrentText(value)
            widget.blockSignals(False)
            widget.setEnabled(True)
        controls["brightness"].blockSignals(True)
        controls["brightness"].setValue(layer.brightness)
        controls["brightness"].blockSignals(False)
        controls["brightness"].setEnabled(True)

    def _refresh_effect_list(self, side: str, selected_layer: int | None = None) -> None:
        scene = self.engine.left_scene if side == "left" else self.engine.right_scene
        controls = self.source_widgets.get(side)
        if not controls:
            return
        widget: QListWidget = controls["list"]
        if selected_layer is None and widget.currentItem():
            selected_layer = int(widget.currentItem().data(Qt.ItemDataRole.UserRole))
        widget.blockSignals(True)
        widget.clear()
        row_to_select = 0
        for layer_index, layer in enumerate(scene.layers):
            if not layer.configured:
                continue
            item = QListWidgetItem(f"Layer {layer_index + 1}  ·  {layer.generator_name}")
            item.setData(Qt.ItemDataRole.UserRole, layer_index)
            item.setFlags(item.flags() | Qt.ItemFlag.ItemIsUserCheckable)
            item.setCheckState(Qt.CheckState.Checked if layer.enabled else Qt.CheckState.Unchecked)
            widget.addItem(item)
            if layer_index == selected_layer:
                row_to_select = widget.count() - 1
        if widget.count():
            widget.setCurrentRow(row_to_select)
        widget.blockSignals(False)
        self._sync_source_controls(side)

    def _effect_item_changed(self, side: str, item: QListWidgetItem) -> None:
        layer_index = int(item.data(Qt.ItemDataRole.UserRole))
        self.engine.set_layer_enabled(side, layer_index, item.checkState() == Qt.CheckState.Checked)

    def _selected_layer(self, side: str) -> int | None:
        item = self.source_widgets[side]["list"].currentItem()
        return int(item.data(Qt.ItemDataRole.UserRole)) if item else None

    def _update_selected_layer(self, side: str, **options) -> None:
        index = self._selected_layer(side)
        if index is not None:
            self.engine.set_layer_options(side, index, **options)

    def _add_effect(self, side: str) -> None:
        scene = self.engine.left_scene if side == "left" else self.engine.right_scene
        if sum(layer.configured for layer in scene.layers) >= 5:
            return
        picker = EffectPickerDialog(self, generator_names())
        if picker.exec() != QDialog.DialogCode.Accepted or picker.selected_effect is None:
            return
        index = self.engine.add_layer(side, picker.selected_effect)
        if index is not None:
            self._refresh_effect_list(side, index)

    def _remove_effect(self, side: str) -> None:
        index = self._selected_layer(side)
        if index is None:
            return
        self.engine.remove_layer(side, index)
        self._refresh_effect_list(side)

    def _move_effect(self, side: str, direction: int) -> None:
        controls = self.source_widgets[side]
        row = controls["list"].currentRow()
        target_row = row + direction
        if row < 0 or not 0 <= target_row < controls["list"].count():
            return
        source = int(controls["list"].item(row).data(Qt.ItemDataRole.UserRole))
        target = int(controls["list"].item(target_row).data(Qt.ItemDataRole.UserRole))
        self.engine.move_layer(side, source, target)
        self._refresh_effect_list(side, target)

    def _edit_selected_effect(self, side: str) -> None:
        index = self._selected_layer(side)
        if index is not None:
            self._show_generator_settings(side, index)

    def _show_generator_settings(self, side: str, layer_index: int) -> None:
        scene = self.engine.left_scene if side == "left" else self.engine.right_scene
        layer = scene.layers[layer_index]
        schema = GENERATOR_SETTINGS.get(layer.generator_name, {})
        if not schema:
            QMessageBox.information(self, "Effect settings", f"{layer.generator_name} has no adjustable parameters.")
            return
        dialog = QDialog(self)
        dialog.setWindowTitle(f"{layer.generator_name} settings · {side.upper()} / Layer {layer_index + 1}")
        form = QFormLayout(dialog)
        editors = {}
        values = default_parameters(layer.generator_name)
        values.update(layer.parameters)
        for key, spec in schema.items():
            kind, default, minimum, maximum, step = spec
            if kind == "str":
                editor = QLineEdit(str(values.get(key, default)))
            elif kind == "color":
                editor = ColorButton(str(values.get(key, default)))
            elif kind == "font":
                editor = QFontComboBox()
                editor.setCurrentFont(QFont(str(values.get(key, default))))
            elif kind == "choice":
                editor = QComboBox()
                editor.addItems(list(minimum))
                editor.setCurrentText(str(values.get(key, default)))
            elif kind == "int":
                editor = QSpinBox()
                editor.setRange(minimum, maximum)
                editor.setSingleStep(step)
                editor.setValue(int(values.get(key, default)))
            else:
                editor = QDoubleSpinBox()
                editor.setRange(minimum, maximum)
                editor.setSingleStep(step)
                editor.setDecimals(3)
                editor.setValue(float(values.get(key, default)))
            editors[key] = editor
            form.addRow(key.replace("_", " ").title(), editor)
        buttons = QDialogButtonBox(QDialogButtonBox.StandardButton.Ok | QDialogButtonBox.StandardButton.Cancel)
        buttons.accepted.connect(dialog.accept)
        buttons.rejected.connect(dialog.reject)
        form.addRow(buttons)
        if dialog.exec() != QDialog.DialogCode.Accepted:
            return
        params = {}
        for key, editor in editors.items():
            if isinstance(editor, QLineEdit):
                params[key] = editor.text()
            elif isinstance(editor, ColorButton):
                params[key] = editor.color_value
            elif isinstance(editor, QFontComboBox):
                params[key] = editor.currentFont().family()
            elif isinstance(editor, QComboBox):
                params[key] = editor.currentText()
            else:
                params[key] = editor.value()
        self.engine.set_generator_parameters(side, layer_index, params)
        self.statusBar().showMessage(f"Updated {layer.generator_name} settings", 3000)

    def _add_discovered_device(self, address: str) -> None:
        if address in self.discovered_ips:
            return
        self.discovered_ips.add(address)
        self.device_hint.setVisible(False)
        item = QListWidgetItem(self.device_list)
        row = QWidget()
        row_layout = QHBoxLayout(row)
        row_layout.setContentsMargins(5, 2, 5, 2)
        ip_label = QLabel(f"{address}  ·  WS :{DeviceDiscovery.WEBSOCKET_PORT}")
        ip_label.setTextInteractionFlags(Qt.TextInteractionFlag.TextSelectableByMouse)
        connect_button = QPushButton("Connect")
        connect_button.setMinimumWidth(100)
        connect_button.setMinimumHeight(34)
        connect_button.clicked.connect(
            lambda checked=False, ip=address, button=connect_button: self._toggle_discovered_output(ip, button))
        row_layout.addWidget(ip_label, 1)
        row_layout.addWidget(connect_button)
        item.setSizeHint(QSize(max(260, self.device_list.viewport().width()), 47))
        self.device_list.setItemWidget(item, row)
        self.device_connect_buttons[address] = connect_button
        self._update_output_controls()

    def _discovery_ready(self) -> None:
        if not self.discovered_ips:
            self.device_hint.setText("No devices found yet. Listening for CODETASTIC beacons…")
        self.statusBar().showMessage("Listening for CODETASTIC beacons on UDP 20586")

    def _discovery_error(self, message: str) -> None:
        self.device_hint.setText("Discovery unavailable. Use Connect manually.")
        self.device_hint.setVisible(True)
        self.statusBar().showMessage(message)

    def _show_manual_connection(self, visible: bool) -> None:
        self.manual_form.setVisible(visible)

    def _connect_manually(self) -> None:
        if self.output:
            if self._connection_button is self.manual_connect_button:
                self._disconnect_output()
            return
        host = self.websocket_host.text().strip()
        if not host:
            QMessageBox.warning(self, "WebSocket destination", "Enter a destination IP address or host name.")
            return
        try:
            port = int(self.websocket_port.text())
        except ValueError:
            port = 0
        if not 1 <= port <= 65535:
            QMessageBox.warning(self, "WebSocket destination", "Enter a port from 1 to 65535.")
            return
        self._connect_output(host, port, self.manual_connect_button)

    def _toggle_discovered_output(self, address: str, button: QPushButton) -> None:
        if self.output:
            if self._connection_button is button and self._connected_device_ip == address:
                self._disconnect_output()
            return
        self._connect_output(address, DeviceDiscovery.WEBSOCKET_PORT, button)

    def _connect_output(self, host: str, port: int, source_button: QPushButton) -> None:
        if self.output is not None:
            return
        host = host.strip()
        if not host:
            return
        self.config.websocket_host = host
        self.config.websocket_port = int(port)
        self.websocket_host.setText(host)
        self.websocket_port.setText(str(port))
        self.config.live_state = self.engine.export_state()
        self.config.save()
        self.output = MrCodetasticWebSocketOutput(self.config.websocket_url, self.config.mapping)
        self._connection_button = source_button
        self._connected_device_ip = host if source_button in self.device_connect_buttons.values() else None
        self.output.start()
        self._update_output_controls()
        self.statusBar().showMessage(f"Connecting to {self.config.websocket_url}")

    def _disconnect_output(self) -> None:
        if self.output:
            self.output.stop()
            self.output = None
        self._connection_button = None
        self._connected_device_ip = None
        self._update_output_controls()
        self.statusBar().showMessage("Output disconnected")

    def _update_output_controls(self) -> None:
        connected = bool(self.output and self.output.connected)
        connecting = self.output is not None and not connected
        for address, button in self.device_connect_buttons.items():
            is_selected = self._connection_button is button
            button.setText("Disconnect" if connected and is_selected else
                           "Connecting…" if connecting and is_selected else "Connect")
            button.setEnabled(self.output is None or is_selected)
        self.manual_toggle.setEnabled(self.output is None)
        self.websocket_host.setEnabled(self.output is None)
        self.websocket_port.setEnabled(self.output is None)
        manual_selected = self._connection_button is self.manual_connect_button
        self.manual_connect_button.setText("Disconnect" if connected and manual_selected else
                                           "Connecting…" if connecting and manual_selected else "Connect")
        self.manual_connect_button.setEnabled(self.output is None or manual_selected)

    def _refresh(self) -> None:
        for event, payload in self.discovery.poll_events():
            if event == "device":
                self._add_discovered_device(payload)
            elif event == "error":
                self._discovery_error(payload)
            elif event == "ready":
                self._discovery_ready()
        if self.output:
            self._update_output_controls()
        frames = self.engine.snapshot()
        if not frames:
            return
        self.left_preview.set_frame(frames["left"])
        self.mix_preview.set_frame(frames["mixed"])
        self.right_preview.set_frame(frames["right"])
        status = f"●  {self.engine.fps:.0f} FPS  ·  {self.config.width} × {self.config.height}"
        effect_errors = []
        for scene in (self.engine.left_scene, self.engine.right_scene):
            for layer, generator in zip(scene.layers, scene._generators):
                if layer.enabled and layer.generator_name == "Screen Capture" and generator.last_error:
                    effect_errors.append(generator.last_error)
        if effect_errors:
            status += "  ·  SCREEN CAPTURE ERROR"
            self.statusBar().showMessage(effect_errors[0])
        audio_error = self.engine.audio_capture.last_error
        if audio_error:
            status += "  ·  AUDIO INPUT ERROR"
            self.statusBar().showMessage(audio_error)
        if self.output:
            self.output.send(frames["mixed"])
            status += "  ·  OUTPUT ONLINE" if self.output.connected else "  ·  CONNECTING"
            if self.output.last_error:
                self.statusBar().showMessage(self.output.last_error)
        self.fps_label.setText(status)

    def closeEvent(self, event) -> None:
        self.discovery.stop()
        if self.output:
            self.output.stop()
        self.config.websocket_host = self.websocket_host.text().strip() or self.config.websocket_host
        try:
            self.config.websocket_port = int(self.websocket_port.text())
        except ValueError:
            pass
        self.config.live_state = self.engine.export_state()
        self.config.save()
        super().closeEvent(event)

    def _apply_style(self) -> None:
        self.setStyleSheet("""
            QMainWindow, QWidget { background:#0c1118; color:#dce5ef; font-family:'Segoe UI'; font-size:10pt; }
            QMenuBar { background:#cbd3dc; color:#1b2733; border-bottom:1px solid #aab5c0; padding:3px 8px; }
            QMenuBar::item { background:transparent; color:#1b2733; padding:5px 10px; border-radius:3px; }
            QMenuBar::item:selected { background:#b5c2cf; color:#102438; }
            QMenu { background:#f5f7fa; color:#1b2733; border:1px solid #b9c2cc; }
            QMenu::item:selected { background:#d4e0ed; color:#102438; }
            QLabel#appTitle { font-size:22pt; font-weight:700; letter-spacing:2px; color:#f4f7fb; }
            QLabel#subtitle { color:#93a8bd; font-size:8pt; letter-spacing:2px; padding-left:10px; }
            QFrame#headerDivider { color:#39434e; background:#39434e; max-height:1px; }
            QLabel#previewTitle { color:#a8b8c9; font-size:9pt; font-weight:600; letter-spacing:1px; }
            QLabel#status { color:#67dfa3; font-size:9pt; }
            QWidget#controlPanel { background:#111923; border:1px solid #263343; border-radius:9px; }
            QGroupBox { background:#111923; border:1px solid #263343; border-radius:9px; margin-top:10px; padding:10px; font-weight:600; }
            QGroupBox::title { subcontrol-origin:margin; left:12px; padding:0 5px; color:#a8b8c9; }
            QListWidget { background:#0b1118; border:1px solid #263343; border-radius:5px; padding:3px; }
            QListWidget::item { padding:5px 6px; border-radius:3px; }
            QListWidget::item:selected { background:#243a51; color:#ffffff; }
            QComboBox, QSpinBox { background:#182330; border:1px solid #34475b; border-radius:5px; padding:6px; min-width:105px; }
            QLineEdit { background:#182330; border:1px solid #34475b; border-radius:5px; padding:6px; }
            QPushButton { background:#2688e8; border:0; border-radius:5px; padding:8px 14px; color:white; font-weight:600; }
            QPushButton:hover { background:#409cf4; }
            QSlider::groove:horizontal { height:5px; background:#34475b; border-radius:2px; }
            QSlider::handle:horizontal { background:#ffb84a; width:15px; margin:-6px 0; border-radius:7px; }
            QStatusBar { color:#8ea0b4; }
        """)
