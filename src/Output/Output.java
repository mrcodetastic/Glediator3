// 
// Decompiled by Procyon v0.5.36
// 

package Output;

import java.awt.Color;
import Options.Options;

public class Output
{
    SerialOutput serial;
    ArtnetOutput artnet;
    TPM2NetOutput tpm_net;
    MiniDMXProtocol mini_dmx;
    TPM2SerialProtocol tpm_serial;
    TPM2LiteSerialProtocol tpm_lite_serial;    
    GlediatorProtocol glediator_protocol;
    private OutputType output_type;
    byte[] output_buffer;
    ColorOrder color_order;
    
    public Output() {
        this.serial = new SerialOutput();
        this.artnet = new ArtnetOutput();
        this.tpm_net = new TPM2NetOutput();
        this.mini_dmx = new MiniDMXProtocol();
        this.tpm_serial = new TPM2SerialProtocol();
        this.tpm_lite_serial = new TPM2LiteSerialProtocol();
        this.glediator_protocol = new GlediatorProtocol();
        this.output_buffer = new byte[0];
        this.output_type = OutputType.NO_DATA_TRANSMISSION;
        this.color_order = ColorOrder.RGB;
    }
    
    public void configureOutput(final Options options) {
        this.output_type = options.getOutputType();
        this.setOutputBufferSize(options.getMatrixSize()[0] * options.getMatrixSize()[1]);
        this.color_order = options.getColorOrder();
    }
    
    public void setOutputBufferSize(final int frame_size) {
        switch (this.output_type) {
            case MINI_DMX: {
                byte packet_size_byte = -96;
                int packet_data_size = 96;
                if (frame_size * 3 > 96) {
                    packet_size_byte = -95;
                    packet_data_size = 256;
                }
                if (frame_size * 3 > 256) {
                    packet_size_byte = -94;
                    packet_data_size = 512;
                }
                if (frame_size * 3 > 512) {
                    packet_size_byte = -80;
                    packet_data_size = 768;
                }
                if (frame_size * 3 > 768) {
                    packet_size_byte = -79;
                    packet_data_size = 1536;
                }
                if (frame_size * 3 > 1536) {
                    packet_size_byte = -78;
                    packet_data_size = 3072;
                }
                this.output_buffer = new byte[packet_data_size + 3];
                break;
            }
            case GLEDIATOR_PROTOCOL: {
                this.output_buffer = new byte[frame_size * 3 + 1];
                break;
            }
            case TPM2: {
                this.output_buffer = new byte[frame_size * 3 + 5];
                break;
            }
            case TPM2_LITE: {
                this.output_buffer = new byte[frame_size * 2 + 5]; // 16 bit color in 2 bytes hence hte * 2
                break;
            }            
        }
    }
    
    public String[] getComPorts() {
        return this.serial.getComPorts();
    }
    
    public String startSerial(final String comport, final BaudRate baud_rate) {
        return this.serial.startSerial(comport, baud_rate);
    }
    
    public String stopSerial(final String comport) {
        return this.serial.stopSerial(comport);
    }
    
    public String startArtnet() {
        return this.artnet.startArtnet();
    }
    
    public String stopArtnet() {
        return this.artnet.stopArtnet();
    }
    
    public String startTPM2net() {
        return this.tpm_net.startTPM2_Net();
    }
    
    public String stopTPM2net() {
        return this.tpm_net.stopTPM2_Net();
    }
    
    public boolean getArtnetStatus() {
        return this.artnet.get_artnet_status();
    }
    
    public boolean getTPM2netStatus() {
        return this.tpm_net.get_TPM2_Status();
    }
    
    public void setArtnetParameters(final int[][] unis, final int[][][] map) {
        this.artnet.set_parameters(unis, map);
    }
    
    public void setTPM2netParameters(final int[][] unis, final int[][][] map) {
        this.tpm_net.set_parameters(unis, map);
    }
    
    public void doOutput(final Color[] frame) {
        switch (this.output_type) {
            case ARTNET: {
                this.artnet.send_out_one_frame(frame);
                break;
            }
            case TPM2: {
                this.tpm_serial.do_protocol(frame, this.output_buffer, this.color_order);
                this.serial.sendSerialData(this.output_buffer, this.output_buffer.length);
                break;
            }
            case TPM2_LITE: {
                this.tpm_lite_serial.do_protocol(frame, this.output_buffer, this.color_order);
                this.serial.sendSerialData(this.output_buffer, this.output_buffer.length);
                break;
            }            
            case TPM2_NET: {
                this.tpm_net.send_out_one_frame(frame);
                break;
            }
            case MINI_DMX: {
                this.mini_dmx.do_protocol(frame, this.output_buffer, this.color_order);
                this.serial.sendSerialData(this.output_buffer, this.output_buffer.length);
                break;
            }
            case GLEDIATOR_PROTOCOL: {
                this.glediator_protocol.do_protocol(frame, this.output_buffer, this.color_order);
                this.serial.sendSerialData(this.output_buffer, this.output_buffer.length);
                break;
            }
        }
    }
}
