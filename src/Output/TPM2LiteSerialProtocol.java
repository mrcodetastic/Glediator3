// 
// Decompiled by Procyon v0.5.36
// 

package Output;

import java.awt.Color;

public class TPM2LiteSerialProtocol
{
    void TPM2Lite_serial_Protocol() {
    }
    
    public void do_protocol(final Color[] frame, final byte[] output_buffer, final ColorOrder color_order) {
        final int frame_size = frame.length;
        int index = 0;
        output_buffer[index] = -55;
        ++index;
        output_buffer[index] = -38;
        ++index;
        final byte frame_size_byte_high = (byte)(frame_size * 2 >> 8 & 0xFF);
        final byte frame_size_byte_low = (byte)(frame_size * 2 & 0xFF);
        output_buffer[index] = frame_size_byte_high;
        ++index;
        output_buffer[index] = frame_size_byte_low;
        ++index;
        for (final Color temp_color : frame) {
            byte b1 = 0;
            byte b2 = 0;
            byte b3 = 0;
            
            /*
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
            */
            
            b1 = (byte)temp_color.getRed();
            b2 = (byte)temp_color.getGreen();
            b3 = (byte)temp_color.getBlue();
            
            int B = (int)((b3 & 0x000000F8) >> 3);
            int G = (int)((b2 & 0x0000FC00) >> 5);
            int R = (int)((b1 & 0x00F80000) >> 8);
            
            int output = (R | G | B);
            
            
    		//int output =  (char) (((b1 & 0xF8) << 8) | ((b2 & 0xFC) << 3) | (b3 >> 3));
    		
    		
    		// https://stackoverflow.com/questions/4826453/get-two-lower-bytes-from-int-variable
    		//https://stackoverflow.com/questions/4421400/how-to-get-0-padded-binary-representation-of-an-integer-in-java
    		
    		/*
    		System.out.println(Integer.toBinaryString(output));
    		System.out.println(Integer.toBinaryString(output & 0xFF ));
    		System.out.println(Integer.toBinaryString((output >> 8) & 0xFF ));
    		
    		11111100000
			11100000
			111
    		*/	
    		// 565 color conversion
            output_buffer[index] = (byte) (output & 0xFF);
            ++index;
            output_buffer[index] = (byte) ((output >> 8) & 0xFF);
            ++index;
        }
        output_buffer[index] = 54;
    }
}
