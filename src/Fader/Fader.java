// 
// Decompiled by Procyon v0.5.36
// 

package Fader;

import java.awt.Color;
import Dimmer.Dimmer;

public class Fader
{
    public static final FaderMode DEFAULT_FADER_MODE;
    public static final int DEFAULT_FADER_VALUE = 50;
    public static final int DEFAULT_FADE_TIME = 5;
    public static final int MIN_FADE_TIME = 1;
    public static final int MAX_FADE_TIME = 100;
    public static final int DEFAULT_INTENSITY = 100;
    private FaderMode fader_mode;
    private int fader_value;
    private int fade_time;
    private int[] matrix_size;
    private Dimmer left_dimmer;
    private Dimmer right_dimmer;
    private Dimmer master_dimmer;
    
    public Fader() {
        this.matrix_size = new int[2];
        this.fader_mode = Fader.DEFAULT_FADER_MODE;
        this.fader_value = 50;
        this.fade_time = 5;
        (this.left_dimmer = new Dimmer()).setDimmerLevel(100);
        (this.right_dimmer = new Dimmer()).setDimmerLevel(100);
        (this.master_dimmer = new Dimmer()).setDimmerLevel(100);
    }
    
    public void setMatrixSize(final int[] matrix_size) {
        if (matrix_size[0] > 0 && matrix_size[1] > 0) {
            this.matrix_size[0] = matrix_size[0];
            this.matrix_size[1] = matrix_size[1];
        }
        else {
            System.out.println("Fader: Wrong matrix size.");
        }
    }
    
    public void setFaderMode(final FaderMode fader_mode) {
        this.fader_mode = fader_mode;
    }
    
    public FaderMode getFaderMode() {
        return this.fader_mode;
    }
    
    public void setFaderValue(final int fader_value) {
        if (fader_value >= 0 && fader_value <= 100) {
            this.fader_value = fader_value;
        }
        else {
            System.out.println("Fader: Wrong fader value.");
        }
    }
    
    public int getFaderValue() {
        return this.fader_value;
    }
    
    public void setFadeTime(final int fade_time) {
        if (fade_time >= 1 && fade_time <= 100) {
            this.fade_time = fade_time;
        }
        else {
            System.out.println("Fader: Wrong fade time value.");
        }
    }
    
    public int getFaderTime() {
        return this.fade_time;
    }
    
    public void setLeftIntensity(final int left_intensity) {
        if (left_intensity >= 0 && left_intensity <= 100) {
            this.left_dimmer.setDimmerLevel(left_intensity);
        }
        else {
            System.out.println("Fader: Wrong value for left intensity.");
        }
    }
    
    public int getLeftIntensity() {
        return this.left_dimmer.getDimmerLevel();
    }
    
    public void setRightIntensity(final int right_intensity) {
        if (right_intensity >= 0 && right_intensity <= 100) {
            this.right_dimmer.setDimmerLevel(right_intensity);
        }
        else {
            System.out.println("Fader: Wrong value for right intensity.");
        }
    }
    
    public int getRightIntensity() {
        return this.right_dimmer.getDimmerLevel();
    }
    
    public void setMasterIntensity(final int master_intensity) {
        if (master_intensity >= 0 && master_intensity <= 100) {
            this.master_dimmer.setDimmerLevel(master_intensity);
        }
        else {
            System.out.println("Fader: Wrong value for master intensity.");
        }
    }
    
    public int getMasterIntensity() {
        return this.master_dimmer.getDimmerLevel();
    }
    
    public void doFading(final Color[] left_image, final Color[] right_image, final Color[] main_image) {
        this.left_dimmer.doDimming(left_image);
        this.right_dimmer.doDimming(right_image);
        switch (this.fader_mode) {
            case LINEAR: {
                this.fadeLinear(left_image, right_image, main_image);
                break;
            }
            case PROGRESSIVE: {
                this.fadeProgressive(left_image, right_image, main_image);
                break;
            }
            case STRIPES_HOR: {
                this.fadeStripesHor(left_image, right_image, main_image);
                break;
            }
            case STRIPES_VERT: {
                this.fadeStripesVert(left_image, right_image, main_image);
                break;
            }
            case SHIFT_HOR: {
                this.fadeShiftHor(left_image, right_image, main_image);
                break;
            }
            case SHIFT_VERT: {
                this.fadeShiftVert(left_image, right_image, main_image);
                break;
            }
        }
        this.master_dimmer.doDimming(main_image);
    }
    
    private void fadeLinear(final Color[] left_image, final Color[] right_image, final Color[] main_image) {
        final float left = 1.0f - this.fader_value / 100.0f;
        final float right = this.fader_value / 100.0f;
        for (int frame_size = main_image.length, i = 0; i < frame_size; ++i) {
            int r = (int)(left * left_image[i].getRed() + right * right_image[i].getRed());
            int g = (int)(left * left_image[i].getGreen() + right * right_image[i].getGreen());
            int b = (int)(left * left_image[i].getBlue() + right * right_image[i].getBlue());
            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }
            main_image[i] = new Color(r, g, b);
        }
    }
    
    private void fadeProgressive(final Color[] left_image, final Color[] right_image, final Color[] main_image) {
        final int frame_size = main_image.length;
        float left;
        float right;
        if (this.fader_value < 50) {
            left = 1.0f;
            right = this.fader_value / 100.0f * 2.0f;
        }
        else {
            left = (1.0f - this.fader_value / 100.0f) * 2.0f;
            right = 1.0f;
        }
        for (int i = 0; i < frame_size; ++i) {
            int r = (int)(left * left_image[i].getRed() + right * right_image[i].getRed());
            int g = (int)(left * left_image[i].getGreen() + right * right_image[i].getGreen());
            int b = (int)(left * left_image[i].getBlue() + right * right_image[i].getBlue());
            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }
            main_image[i] = new Color(r, g, b);
        }
    }
    
    private void fadeStripesHor(final Color[] left_image, final Color[] right_image, final Color[] main_image) {
        int shift = (int)(this.matrix_size[0] * (float)this.fader_value / 100.0f);
        for (int y = 0; y < this.matrix_size[1]; y += 2) {
            for (int x = 0; x < this.matrix_size[0]; ++x) {
                if (x < shift) {
                    main_image[y * this.matrix_size[0] + x] = new Color(right_image[y * this.matrix_size[0] + x].getRGB());
                }
                else {
                    main_image[y * this.matrix_size[0] + x] = new Color(left_image[y * this.matrix_size[0] + x].getRGB());
                }
            }
        }
        shift = this.matrix_size[0] - shift;
        for (int y = 1; y < this.matrix_size[1]; y += 2) {
            for (int x = 0; x < this.matrix_size[0]; ++x) {
                if (x >= shift) {
                    main_image[y * this.matrix_size[0] + x] = new Color(right_image[y * this.matrix_size[0] + x].getRGB());
                }
                else {
                    main_image[y * this.matrix_size[0] + x] = new Color(left_image[y * this.matrix_size[0] + x].getRGB());
                }
            }
        }
    }
    
    private void fadeStripesVert(final Color[] left_image, final Color[] right_image, final Color[] main_image) {
        int shift = (int)(this.matrix_size[1] * (float)this.fader_value / 100.0f);
        for (int x = 0; x < this.matrix_size[0]; x += 2) {
            for (int y = 0; y < this.matrix_size[1]; ++y) {
                if (y < shift) {
                    main_image[y * this.matrix_size[0] + x] = new Color(right_image[y * this.matrix_size[0] + x].getRGB());
                }
                else {
                    main_image[y * this.matrix_size[0] + x] = new Color(left_image[y * this.matrix_size[0] + x].getRGB());
                }
            }
        }
        shift = this.matrix_size[1] - shift;
        for (int x = 1; x < this.matrix_size[0]; x += 2) {
            for (int y = 0; y < this.matrix_size[1]; ++y) {
                if (y >= shift) {
                    main_image[y * this.matrix_size[0] + x] = new Color(right_image[y * this.matrix_size[0] + x].getRGB());
                }
                else {
                    main_image[y * this.matrix_size[0] + x] = new Color(left_image[y * this.matrix_size[0] + x].getRGB());
                }
            }
        }
    }
    
    private void fadeShiftHor(final Color[] left_image, final Color[] right_image, final Color[] main_image) {
        final int shift = (int)(this.matrix_size[0] * (float)this.fader_value / 100.0f);
        for (int y = 0; y < this.matrix_size[1]; ++y) {
            for (int x = 0; x < this.matrix_size[0]; ++x) {
                if (x < shift) {
                    main_image[y * this.matrix_size[0] + x] = new Color(right_image[y * this.matrix_size[0] + x].getRGB());
                }
                else {
                    main_image[y * this.matrix_size[0] + x] = new Color(left_image[y * this.matrix_size[0] + x].getRGB());
                }
            }
        }
    }
    
    private void fadeShiftVert(final Color[] left_image, final Color[] right_image, final Color[] main_image) {
        final int shift = (int)(this.matrix_size[1] * (float)this.fader_value / 100.0f);
        for (int y = 0; y < this.matrix_size[1]; ++y) {
            for (int x = 0; x < this.matrix_size[0]; ++x) {
                if (y < shift) {
                    main_image[y * this.matrix_size[0] + x] = new Color(right_image[y * this.matrix_size[0] + x].getRGB());
                }
                else {
                    main_image[y * this.matrix_size[0] + x] = new Color(left_image[y * this.matrix_size[0] + x].getRGB());
                }
            }
        }
    }
    
    static {
        DEFAULT_FADER_MODE = FaderMode.LINEAR;
    }
    
    public enum FaderMode
    {
        LINEAR("Linear"), 
        PROGRESSIVE("Progressive"), 
        STRIPES_HOR("Stripes_Hor"), 
        STRIPES_VERT("Stripes_Vert"), 
        SHIFT_HOR("Shift_Hor"), 
        SHIFT_VERT("Shift_Vert");
        
        private final String displayed_text;
        
        private FaderMode(final String s) {
            this.displayed_text = s;
        }
        
        @Override
        public String toString() {
            return this.displayed_text;
        }
    }
}
