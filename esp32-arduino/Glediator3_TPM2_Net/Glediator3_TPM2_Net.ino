// CUSTOM / HACKED FORM OF TPM2_NET based on Glediator3.

// Refer to /Glediator3/src/Output/TPM2NetOutput.java

/* -------------------------- Class Includes  -------------------------- */
#include <WiFi.h>
#include <WiFiUdp.h>
#include <ESP32-RGB64x32MatrixPanel-I2S-DMA.h>

RGB64x32MatrixPanel_I2S_DMA matrix;
WiFiUDP Udp;

#define PAYLOAD_SIZE_MAX 1200 // don't change this

/* -------------------------- Class Includes  -------------------------- */
// Wifi settings
const char* ssid = "";
const char* password = "";

// TPM2_NET
#define NO_OF_LEDS MATRIX_WIDTH*MATRIX_HEIGHT
uint8_t frame_buffer[NO_OF_LEDS * 3];
uint8_t packet_buffer[1500];

unsigned long last_fps_display_ms     = 0;
int frames_since_last_fps_stats = 0; 

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
   Udp.begin(65506); //tpm2net port apparently

   
 //  tpm2Net.registerRxData(CallbackRx);
   matrix.begin();  // setup the LED matrix
}


// get a packet and parse 
int previous_packet_seq_num = 0;
void loop()
{
/*   
 *  As per hacked TPM2NetOutput.java:

        private static final int TPM2_NET_HEADER_LENGTH = 6;
        private static final int TPM2_NET_MAX_PACKET_PAYLOAD_SIZE = PAYLOAD_SIZE_MAX; 
    
        this.output_buffer[0] = -100; // magic number
        this.output_buffer[1] = -38; // signed 8 bit = 11011010 
        this.output_buffer[2] = (byte)(length >> 8 & 0xFF);
        this.output_buffer[3] = (byte)(length & 0xFF);
        this.output_buffer[4] = (byte)seq_num;
        this.output_buffer[5] = (byte)total_packet_count;
        System.arraycopy(data, 0, this.output_buffer, 6, length);
 */
  
  int packetSize = Udp.parsePacket();
  if (packetSize)
  {
  //  Serial.printf("Received %d bytes from %s, port %d\n", packetSize, Udp.remoteIP().toString().c_str(), Udp.remotePort());

    int len = Udp.read(packet_buffer, sizeof(packet_buffer));
    if (len > 0)
    {
     //   Serial.println((int8_t)packet_buffer[0], DEC);
       if ( ((int8_t)packet_buffer[0]) == -100) {
   //     Serial.println("Valid Packet");

        uint8_t packet_seq = (uint8_t)((int8_t)packet_buffer[4]);
        uint8_t packet_num = (uint8_t)((int8_t)packet_buffer[5]);

     //   Serial.println(packet_seq); Serial.println(packet_num);

        if ( packet_seq == 0)
        {
          memset(frame_buffer, 0x00, sizeof(packet_buffer));
        }

        if ( len > PAYLOAD_SIZE_MAX) // 1207 bytes will be received for a full payload (PAYLOAD_SIZE_MAX b) + headers ( 7 b)
        {
                memcpy(&frame_buffer[PAYLOAD_SIZE_MAX*packet_seq], &packet_buffer[6], PAYLOAD_SIZE_MAX);
        }
        else
        {
                memcpy(&frame_buffer[PAYLOAD_SIZE_MAX*packet_seq], &packet_buffer[6], len-7);
        }


        // Got the last packet? End of the frame then, so display
        if ( packet_seq == packet_num-1)
        {

            uint16_t pixel;
     //       Serial.println("Frame received.");
            
            for (int y = 0; y < MATRIX_HEIGHT; y++) {
                for (int x = 0; x < MATRIX_WIDTH; x++) {
                    pixel = (y * MATRIX_WIDTH) + x;
                    pixel *= 3;
                    
                    matrix.drawPixelRGB888(x, y, frame_buffer[pixel], frame_buffer[pixel+1], frame_buffer[pixel+2]);
                    
                } // end x
            } // end row          

            if ((millis() - last_fps_display_ms) > 5000)
            {
                Serial.print("Receiving at: ");
                Serial.print( frames_since_last_fps_stats / 5, DEC);
                Serial.println (" fps");

                frames_since_last_fps_stats = 0;
                last_fps_display_ms = millis();
            }

            frames_since_last_fps_stats++;
          
        }
        
        
       } // valid packet check
       
    }    
  } // end packetSize

  // always clear packet buffer
  memset(packet_buffer, 0x00, sizeof(packet_buffer));  
  
} // end loop


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
