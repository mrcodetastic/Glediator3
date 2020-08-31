// 
// Decompiled by Procyon v0.5.36
// 

package Output;

import java.awt.Color;

public class MiniDMXProtocol
{
    void Mini_DMX_Protocol() {
    }
    
    public void do_protocol(final Color[] frame, final byte[] output_buffer, final ColorOrder color_order) {
        final int frame_size = frame.length;
        if (frame_size < 1025) {
            int index = 0;
            output_buffer[index] = 90;
            ++index;
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
            output_buffer[index] = packet_size_byte;
            ++index;
            for (final Color temp_color : frame) {
                byte b1 = 0;
                byte b2 = 0;
                byte b3 = 0;
                switch (color_order) {
                    case RGB: {
                        b1 = (byte)temp_color.getRed();
                        b2 = (byte)temp_color.getGreen();
                        b3 = (byte)temp_color.getBlue();
                        break;
                    }
                    case RBG: {
                        b1 = (byte)temp_color.getRed();
                        b2 = (byte)temp_color.getBlue();
                        b3 = (byte)temp_color.getGreen();
                        break;
                    }
                    case BGR: {
                        b1 = (byte)temp_color.getBlue();
                        b2 = (byte)temp_color.getGreen();
                        b3 = (byte)temp_color.getRed();
                        break;
                    }
                    case BRG: {
                        b1 = (byte)temp_color.getBlue();
                        b2 = (byte)temp_color.getRed();
                        b3 = (byte)temp_color.getGreen();
                        break;
                    }
                    case GRB: {
                        b1 = (byte)temp_color.getGreen();
                        b2 = (byte)temp_color.getRed();
                        b3 = (byte)temp_color.getBlue();
                        break;
                    }
                    case GBR: {
                        b1 = (byte)temp_color.getGreen();
                        b2 = (byte)temp_color.getBlue();
                        b3 = (byte)temp_color.getRed();
                        break;
                    }
                    default: {
                        b1 = 0;
                        b2 = 0;
                        b3 = 0;
                        break;
                    }
                }
                output_buffer[index] = b1;
                ++index;
                output_buffer[index] = b2;
                ++index;
                output_buffer[index] = b3;
                ++index;
            }
            if (frame_size * 3 < packet_data_size) {
                for (int i = frame_size * 3; i < packet_data_size; ++i) {
                    output_buffer[index] = 0;
                    ++index;
                }
            }
            output_buffer[index] = -91;
        }
    }
}
