// 
// Decompiled by Procyon v0.5.36
// 

package Output;

import java.awt.Color;

public class TPM2SerialProtocol
{
    void TPM2_serial_Protocol() {
    }
    
    public void do_protocol(final Color[] frame, final byte[] output_buffer, final ColorOrder color_order) {
        final int frame_size = frame.length;
        int index = 0;
        output_buffer[index] = -55;
        ++index;
        output_buffer[index] = -38;
        ++index;
        final byte frame_size_byte_high = (byte)(frame_size * 3 >> 8 & 0xFF);
        final byte frame_size_byte_low = (byte)(frame_size * 3 & 0xFF);
        output_buffer[index] = frame_size_byte_high;
        ++index;
        output_buffer[index] = frame_size_byte_low;
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
        output_buffer[index] = 54;
    }
}
