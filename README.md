# Glediator3
Unofficial port (reverse engineer) of the Glediator LED Matrix controlling software, with some minor improvements so the Serial Port connection works cross-platform.

 
## How to use (via. WiFi)
1. Install  the JAVA JRE onto your computer: https://jdk.java.net/14/
2. Load the `Glediator3_TPM2_Net` example sketch onto the ESP32 with the SSID and WiFi configured.
3. Make a note of the IP address assigned to the ESP32
4. Execute `java -jar Glediator3-binary-XXXX.jar` (from the releases directory)
5. Set the Matrix Size in the Software Options, then go into 'Output' and configure the correct destination IP address:
![TPM2_Net Connection Settings](tpm2_matrix_settings.jpg)

6. Then click 'Open Socket' to get things started. Glediator3 will start transmitting to the ESP32.

 
## How to use (via. Serial Port - Slow)
1. Install  the JAVA JRE onto your computer: https://jdk.java.net/14/
2. Execute `java -jar Glediator3-binary-XXXX.jar` (from the releases directory)
3. Connect to your Matrix_Panel via. Serial Port once having loaded 'Glediator3_TPM2_MatrixPanel' example onto it.

Note: The serial port will differ depending on your computer and what your Arduino / ESP32 is connected to.

4. Then click 'Serial Open' to get things started.


## Credits

* R. Heller for creating Glediator

![Glediator3](screenshot.jpg)
