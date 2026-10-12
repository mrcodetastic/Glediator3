# ESP32 HUB75 firmware

This standalone Arduino sketch receives Glediator frames, advertises itself on the LAN, and displays completed frames on a HUB75 matrix using the `ESP32-HUB75-MatrixPanel-I2S-DMA` library with double buffering.

## Libraries

Install these Arduino libraries:

- **ESP32-HUB75-MatrixPanel-I2S-DMA** by mrcodetastic
- **WebSockets** by Markus Sattler (Links2004)
- ESP32 Arduino core (includes `WiFi`, `WiFiUdp`, and `ESPmDNS`)

Open `esp32-firmware.ino` in Arduino IDE. Edit `WIFI_SSID` and `WIFI_PASSWORD`, select the correct ESP32 board/port, then compile and upload. The sketch uses the HUB75 library's default pin mapping; adjust `HUB75_I2S_CFG` pin settings for a custom board/panel wiring.

## Panel and pixel order

The defaults are one 64×32 panel. Set `PANEL_WIDTH`, `PANEL_HEIGHT`, and `PANEL_CHAIN` to match the physical setup and the Python app's **File → LED Panel Config** dimensions. The default `INPUT_PIXEL_ORDER` is `HorizontalSnake`, matching the app's default pixel mapping. It can be changed to `HorizontalLines`, `VerticalLines`, or `VerticalSnake` to match the app selection.

For dimensions larger than the default, the sketch raises `WEBSOCKETS_MAX_DATA_SIZE` to fit one complete frame message. The WebSockets library and ESP32 heap must support a message of `2 + width × height × 2` bytes; for example, 128×128 needs 32,770 bytes.

## Discovery and WebSocket protocol

- The ESP32 advertises `_ws._tcp` via mDNS as `codetastic-panel.local` and periodically broadcasts the exact UDP payload `CODETASTIC` to port **20586**.
- It accepts WebSocket connections on port **12345**.
- Each binary WebSocket message must be exactly `0x81 0x81` followed by `width × height` RGB565 pixels, high byte first. The sketch rejects frames with a wrong marker or byte count.
- The complete frame is decoded into a staging array, copied to the HUB75 back buffer, and displayed with `flipDMABuffer()` only after all pixels are ready. No incomplete network frame is presented.

The sketch has no authentication and is intended for a trusted local network. Open TCP port 12345 and allow UDP broadcast on port 20586 through the LAN firewall.
