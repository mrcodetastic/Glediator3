// 
// Decompiled by Procyon v0.5.36
// 

package Output;

import java.awt.Color;

public class GlediatorProtocol
{
    public void do_protocol(final Color[] frame, final byte[] output_buffer, final ColorOrder color_order) {
        int index = 0;
        output_buffer[index] = 1;
        ++index;
        for (int i = 0; i < frame.length; ++i) {
            final Color temp_color = frame[i];
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
            if (b1 == 1) {
                b1 = 2;
            }
            if (b2 == 1) {
                b2 = 2;
            }
            if (b3 == 1) {
                b3 = 2;
            }
            output_buffer[index + 0] = b1;
            output_buffer[index + 1] = b2;
            output_buffer[index + 2] = b3;
            index += 3;
        }
    }
}
