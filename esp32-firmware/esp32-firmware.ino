/*
 * Minimal MrCodeTastic HUB75 receiver.
 *
 * Wire protocol: one WebSocket binary message per complete frame:
 *   0x81 0x81 | width*height RGB565 pixels, high byte first
 * Discovery: UDP broadcast "CODETASTIC" to port 20586.
 */

#include <Arduino.h>
#include <WiFi.h>
#include <WiFiUdp.h>
#include <ESPmDNS.h>

// Match these dimensions to the physical panel / Python LED Panel Config.
#define PANEL_WIDTH 64
#define PANEL_HEIGHT 32
#define PANEL_CHAIN 1

// WebSocketsServer uses a compile-time receive buffer. Set it large enough
// for the complete (unfragmented) frame message, including its two marker bytes.
#ifndef WEBSOCKETS_MAX_DATA_SIZE
#define WEBSOCKETS_MAX_DATA_SIZE (PANEL_WIDTH * PANEL_HEIGHT * 2 + 2)
#endif

#include <ESP32-HUB75-MatrixPanel-I2S-DMA.h>
#include <WebSocketsServer.h>
#include <array>

// Set credentials for the Wi-Fi network used by the desktop application.
constexpr char WIFI_SSID[] = "<YOUR SSID>";
constexpr char WIFI_PASSWORD[] = "<YOUR PASSWORD>";
constexpr char MDNS_HOST[] = "codetastic-panel";
constexpr uint16_t WEBSOCKET_PORT = 12345;
constexpr uint16_t DISCOVERY_PORT = 20586;
constexpr uint8_t FRAME_MARKER = 0x81;


constexpr size_t FRAME_PIXELS = PANEL_WIDTH * PANEL_HEIGHT;
constexpr size_t FRAME_BYTES = FRAME_PIXELS * sizeof(uint16_t);
constexpr size_t MESSAGE_BYTES = FRAME_BYTES + 2;

MatrixPanel_I2S_DMA *display = nullptr;
WebSocketsServer webSocket(WEBSOCKET_PORT);
WiFiUDP discoveryUdp;
std::array<uint16_t, FRAME_PIXELS> pendingFrame{};
bool framePending = false;
uint32_t lastDiscoveryMs = 0;
uint32_t lastFlipUs = 0;
uint32_t rejectedFrameCount = 0;

static void reportWebSocketEvent(uint8_t clientId, WStype_t type, uint8_t *payload, size_t length) {
  switch (type) {
    case WStype_CONNECTED: {
      IPAddress ip = webSocket.remoteIP(clientId);
      Serial.printf("WebSocket client connected: %s\n", ip.toString().c_str());
      break;
    }
    case WStype_DISCONNECTED:
      Serial.printf("WebSocket client disconnected: %u\n", clientId);
      break;
    case WStype_BIN:
      if (length != MESSAGE_BYTES || payload[0] != FRAME_MARKER || payload[1] != FRAME_MARKER) {
        ++rejectedFrameCount;
        if ((rejectedFrameCount % 30) == 1) {
          Serial.printf("Rejected frame: got %u bytes, expected %u and marker 81 81\n",
                        static_cast<unsigned>(length), static_cast<unsigned>(MESSAGE_BYTES));
        }
        return;
      }

      // Buffer only after validating the whole WebSocket message. The HUB75
      // DMA buffer is not touched until the full frame has arrived.
      for (size_t i = 0; i < FRAME_PIXELS; ++i) {
        const size_t offset = 2 + i * 2;
        pendingFrame[i] = (static_cast<uint16_t>(payload[offset]) << 8) |
                          static_cast<uint16_t>(payload[offset + 1]);
      }
      framePending = true;
      break;
    default:
      break;
  }
}

static void displayPendingFrame() {
  if (!framePending || display == nullptr) return;
  framePending = false;

  // The driver is configured for double buffering. Draw the complete frame
  // into the non-visible buffer first; no partial network frame is displayed.
  for (size_t i = 0; i < FRAME_PIXELS; ++i) {
    int16_t x, y;
    display->drawPixel(x, y, pendingFrame[i]);
  }

  // Avoid flipping faster than the panel's calculated scan refresh rate.
  uint32_t refreshHz = display->calculated_refresh_rate;
  if (refreshHz == 0) refreshHz = 60;
  const uint32_t minimumFrameUs = 1000000UL / refreshHz;
  if (lastFlipUs != 0) {
    const uint32_t elapsedUs = micros() - lastFlipUs;
    if (elapsedUs < minimumFrameUs) delayMicroseconds(minimumFrameUs - elapsedUs);
  }

  // Present the finished back buffer atomically.
  display->flipDMABuffer();
  lastFlipUs = micros();
}

static void broadcastDiscoveryBeacon() {
  if (WiFi.status() != WL_CONNECTED) return;
  const IPAddress broadcastAddress(255, 255, 255, 255);
  discoveryUdp.beginPacket(broadcastAddress, DISCOVERY_PORT);
  discoveryUdp.write(reinterpret_cast<const uint8_t *>("CODETASTIC"), 10);
  discoveryUdp.endPacket();
}

static void startNetworkServices() {
  WiFi.mode(WIFI_STA);
  WiFi.setSleep(false);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.printf("Connecting to Wi-Fi %s", WIFI_SSID);
  while (WiFi.status() != WL_CONNECTED) {
    delay(250);
    Serial.print('.');
  }
  Serial.printf("\nWi-Fi connected: %s\n", WiFi.localIP().toString().c_str());

  if (MDNS.begin(MDNS_HOST)) {
    MDNS.addService("ws", "tcp", WEBSOCKET_PORT);
    MDNS.addServiceTxt("ws", "tcp", "device", "MrCodeTastic HUB75");
    Serial.printf("mDNS: %s.local\n", MDNS_HOST);
  } else {
    Serial.println("mDNS startup failed; UDP discovery remains enabled");
  }

  discoveryUdp.begin(DISCOVERY_PORT);
  webSocket.begin();
  webSocket.onEvent(reportWebSocketEvent);
  Serial.printf("WebSocket listening on port %u; UDP CODETASTIC beacon on %u\n",
                WEBSOCKET_PORT, DISCOVERY_PORT);
}

static void startDisplay() {
  HUB75_I2S_CFG config(PANEL_WIDTH, PANEL_HEIGHT, PANEL_CHAIN);
  config.double_buff = true;

  display = new MatrixPanel_I2S_DMA(config);
  if (!display->begin()) {
    Serial.println("HUB75 display initialization failed");
    while (true) delay(1000);
  }
  display->clearScreen();
  // begin() initializes the DMA buffers and selects a drawing buffer.
  Serial.printf("HUB75 ready: %ux%u, refresh %u Hz, double buffering on\n",
                PANEL_WIDTH, PANEL_HEIGHT, display->calculated_refresh_rate);
}

void setup() {
  Serial.begin(115200);
  delay(500);
  startDisplay();
  startNetworkServices();
  lastDiscoveryMs = millis() - 2000;
}

void loop() {
  webSocket.loop();
  const uint32_t now = millis();
  if (now - lastDiscoveryMs >= 2000) {
    broadcastDiscoveryBeacon();
    lastDiscoveryMs = now;
  }
  displayPendingFrame();
  delay(1);
}
