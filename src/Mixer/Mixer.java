// 
// Decompiled by Procyon v0.5.36
// 

package Mixer;

import java.awt.Color;

public class Mixer
{
    private MixerMode mixer_mode;
    
    public Mixer() {
        this.mixer_mode = MixerMode.ADD;
    }
    
    public void setMixerMode(final MixerMode mixer_mode) {
        this.mixer_mode = mixer_mode;
    }
    
    public MixerMode getMixerMode() {
        return this.mixer_mode;
    }
    
    public void do_mixing(final Color[] image_a, final Color[] image_b, final Color[] image_sum) {
        switch (this.mixer_mode) {
            case ADD: {
                this.mixAdd(image_a, image_b, image_sum);
                break;
            }
            case SUBSTRACT: {
                this.mixSubstract(image_a, image_b, image_sum);
                break;
            }
            case MULTIPLY: {
                this.mixMultiply(image_a, image_b, image_sum);
                break;
            }
            case DIVIDE: {
                this.mixDevide(image_a, image_b, image_sum);
                break;
            }
            case DIFFERENCE: {
                this.mixDifference(image_a, image_b, image_sum);
                break;
            }
            case AND: {
                this.mixAnd(image_a, image_b, image_sum);
                break;
            }
            case OR: {
                this.mixOr(image_a, image_b, image_sum);
                break;
            }
            case XOR: {
                this.mixXor(image_a, image_b, image_sum);
                break;
            }
        }
    }
    
    private void mixAdd(final Color[] image_a, final Color[] image_b, final Color[] image_sum) {
        for (int frame_size = image_a.length, i = 0; i < frame_size; ++i) {
            int r = image_a[i].getRed() + image_b[i].getRed();
            int g = image_a[i].getGreen() + image_b[i].getGreen();
            int b = image_a[i].getBlue() + image_b[i].getBlue();
            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }
            final Color color = new Color(r, g, b);
            image_sum[i] = color;
        }
    }
    
    private void mixSubstract(final Color[] image_a, final Color[] image_b, final Color[] image_sum) {
        for (int frame_size = image_a.length, i = 0; i < frame_size; ++i) {
            int r = image_a[i].getRed() - image_b[i].getRed();
            int g = image_a[i].getGreen() - image_b[i].getGreen();
            int b = image_a[i].getBlue() - image_b[i].getBlue();
            if (r < 0) {
                r = 0;
            }
            if (g < 0) {
                g = 0;
            }
            if (b < 0) {
                b = 0;
            }
            final Color color = new Color(r, g, b);
            image_sum[i] = color;
        }
    }
    
    private void mixMultiply(final Color[] image_a, final Color[] image_b, final Color[] image_sum) {
        for (int frame_size = image_a.length, i = 0; i < frame_size; ++i) {
            final int r = (int)(image_a[i].getRed() * image_b[i].getRed() / 255.0f);
            final int g = (int)(image_a[i].getGreen() * image_b[i].getGreen() / 255.0f);
            final int b = (int)(image_a[i].getBlue() * image_b[i].getBlue() / 255.0f);
            final Color color = new Color(r, g, b);
            image_sum[i] = color;
        }
    }
    
    private void mixDevide(final Color[] image_a, final Color[] image_b, final Color[] image_sum) {
        for (int frame_size = image_a.length, i = 0; i < frame_size; ++i) {
            int r;
            if (image_b[i].getRed() != 0) {
                r = Math.round(image_a[i].getRed() / (float)image_b[i].getRed());
            }
            else {
                r = 255;
            }
            int g;
            if (image_b[i].getGreen() != 0) {
                g = Math.round(image_a[i].getGreen() / (float)image_b[i].getGreen());
            }
            else {
                g = 255;
            }
            int b;
            if (image_b[i].getBlue() != 0) {
                b = Math.round(image_a[i].getBlue() / (float)image_b[i].getBlue());
            }
            else {
                b = 255;
            }
            final Color color = new Color(r, g, b);
            image_sum[i] = color;
        }
    }
    
    private void mixDifference(final Color[] image_a, final Color[] image_b, final Color[] image_sum) {
        for (int frame_size = image_a.length, i = 0; i < frame_size; ++i) {
            final int r = Math.abs(image_a[i].getRed() - image_b[i].getRed());
            final int g = Math.abs(image_a[i].getGreen() - image_b[i].getGreen());
            final int b = Math.abs(image_a[i].getBlue() - image_b[i].getBlue());
            final Color color = new Color(r, g, b);
            image_sum[i] = color;
        }
    }
    
    private void mixAnd(final Color[] image_a, final Color[] image_b, final Color[] image_sum) {
        for (int frame_size = image_a.length, i = 0; i < frame_size; ++i) {
            int r;
            if (image_a[i].getRed() > 0 && image_b[i].getRed() > 0) {
                r = image_a[i].getRed() + image_b[i].getRed();
                if (r > 255) {
                    r = 255;
                }
            }
            else {
                r = 0;
            }
            int g;
            if (image_a[i].getGreen() > 0 && image_b[i].getGreen() > 0) {
                g = image_a[i].getGreen() + image_b[i].getGreen();
                if (g > 255) {
                    g = 255;
                }
            }
            else {
                g = 0;
            }
            int b;
            if (image_a[i].getBlue() > 0 && image_b[i].getBlue() > 0) {
                b = image_a[i].getBlue() + image_b[i].getBlue();
                if (b > 255) {
                    b = 255;
                }
            }
            else {
                b = 0;
            }
            final Color color = new Color(r, g, b);
            image_sum[i] = color;
        }
    }
    
    private void mixOr(final Color[] image_a, final Color[] image_b, final Color[] image_sum) {
        for (int frame_size = image_a.length, i = 0; i < frame_size; ++i) {
            int r;
            if (image_a[i].getRed() > 0 || image_b[i].getRed() > 0) {
                r = image_a[i].getRed() + image_b[i].getRed();
                if (r > 255) {
                    r = 255;
                }
            }
            else {
                r = 0;
            }
            int g;
            if (image_a[i].getGreen() > 0 || image_b[i].getGreen() > 0) {
                g = image_a[i].getGreen() + image_b[i].getGreen();
                if (g > 255) {
                    g = 255;
                }
            }
            else {
                g = 0;
            }
            int b;
            if (image_a[i].getBlue() > 0 || image_b[i].getBlue() > 0) {
                b = image_a[i].getBlue() + image_b[i].getBlue();
                if (b > 255) {
                    b = 255;
                }
            }
            else {
                b = 0;
            }
            final Color color = new Color(r, g, b);
            image_sum[i] = color;
        }
    }
    
    private void mixXor(final Color[] image_a, final Color[] image_b, final Color[] image_sum) {
        for (int frame_size = image_a.length, i = 0; i < frame_size; ++i) {
            int r;
            if (image_a[i].getRed() > 0 ^ image_b[i].getRed() > 0) {
                r = image_a[i].getRed() + image_b[i].getRed();
                if (r > 255) {
                    r = 255;
                }
            }
            else {
                r = 0;
            }
            int g;
            if (image_a[i].getGreen() > 0 ^ image_b[i].getGreen() > 0) {
                g = image_a[i].getGreen() + image_b[i].getGreen();
                if (g > 255) {
                    g = 255;
                }
            }
            else {
                g = 0;
            }
            int b;
            if (image_a[i].getBlue() > 0 ^ image_b[i].getBlue() > 0) {
                b = image_a[i].getBlue() + image_b[i].getBlue();
                if (b > 255) {
                    b = 255;
                }
            }
            else {
                b = 0;
            }
            final Color color = new Color(r, g, b);
            image_sum[i] = color;
        }
    }
    
    public enum MixerMode
    {
        ADD("Add"), 
        SUBSTRACT("Substract"), 
        MULTIPLY("Multiply"), 
        DIVIDE("Divide"), 
        DIFFERENCE("Difference"), 
        AND("AND"), 
        OR("OR"), 
        XOR("XOR");
        
        private final String displayed_text;
        
        private MixerMode(final String s) {
            this.displayed_text = s;
        }
        
        @Override
        public String toString() {
            return this.displayed_text;
        }
    }
}
