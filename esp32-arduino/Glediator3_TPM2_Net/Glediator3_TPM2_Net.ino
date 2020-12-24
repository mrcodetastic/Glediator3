/* -------------------------- Class Initialisation -------------------------- */
#include <WiFi.h>
#include <WiFiUdp.h>
#include "TPM2.h" // https://github.com/rstephan/TPM2

#include <ESP32-RGB64x32MatrixPanel-I2S-DMA.h>
RGB64x32MatrixPanel_I2S_DMA matrix;

// Wifi settings
const char* ssid = "";
const char* password = "";

// Udp
WiFiUDP Udp;

// TPM2_NET
#define NO_OF_LEDS MATRIX_WIDTH*MATRIX_HEIGHT
bool led_off = true;
uint8_t buffer[NO_OF_LEDS * 3];
// uint8_t buffer[NO_OF_LEDS * 3]; tpm2 lite
TPM2 tpm2Driver(&Udp, buffer, sizeof(buffer));

void CallbackRx(uint8_t* data, uint16_t len)
{
  uint16_t pixel;

  Serial.println("Frame received.");
  
  for (int y = 0; y < MATRIX_HEIGHT; y++)
  {
      for (int x = 0; x < MATRIX_WIDTH; x++)
      {
          pixel = (y * MATRIX_WIDTH) + x;
          pixel *= 3;
          
          matrix.drawPixelRGB888(x, y, data[pixel], data[pixel+1], data[pixel+2]);

          //matrix.drawPixelRGB565(x, y, (((uint16_t)data[pixel+1] << 8) | data[pixel])); // tpm2 light

      } // end x
  } // end row
  
}



void setup()
{
  // Setup serial interface
  Serial.begin(112500);
  delay(250);


  // attempt to connect to Wifi network:

  while ( WiFi.status()  != WL_CONNECTED) {

    Serial.print("Attempting to connect to SSID: ");
    Serial.println(ssid);

    // Connect to WPA/WPA2 network. Change this line if using open or WEP network:
    WiFi.begin(ssid, password);

    // wait 10 seconds for connection:
    delay(5000);

  }

  Serial.println("Connected to wifi");

  printWifiStatus();

  Serial.println("\nStarting connection to server...");
    
  
  tpm2Driver.registerRxData(CallbackRx);

  matrix.begin();  // setup the LED matrix
}

void loop()
{
    tpm2Driver.update();
}








void printWifiStatus() {

  // print the SSID of the network you're attached to:

  Serial.print("SSID: ");

  Serial.println(WiFi.SSID());

  // print your WiFi shield's IP address:

  IPAddress ip = WiFi.localIP();

  Serial.print("IP Address: ");

  Serial.println(ip);

  // print the received signal strength:

  long rssi = WiFi.RSSI();

  Serial.print("signal strength (RSSI):");

  Serial.print(rssi);

  Serial.println(" dBm");
}
