// 
// Decompiled by Procyon v0.5.36
// 

package Dimmer;

import java.awt.Color;

public class Dimmer
{
    int dimmer_level;
    
    public Dimmer() {
        this.dimmer_level = 100;
    }
    
    public void setDimmerLevel(final int dimmer_level) {
        if (dimmer_level >= 0 && dimmer_level <= 100) {
            this.dimmer_level = dimmer_level;
        }
        else {
            System.out.println("Dimmer: Wrong dimmer level value.");
        }
    }
    
    public int getDimmerLevel() {
        return this.dimmer_level;
    }
    
    public void doDimming(final Color[] image) {
        for (int image_size = image.length, i = 0; i < image_size; ++i) {
            int r = image[i].getRed();
            int g = image[i].getGreen();
            int b = image[i].getBlue();
            r = (int)(r * (float)this.dimmer_level / 100.0f);
            g = (int)(g * (float)this.dimmer_level / 100.0f);
            b = (int)(b * (float)this.dimmer_level / 100.0f);
            image[i] = new Color(r, g, b);
        }
    }
}
