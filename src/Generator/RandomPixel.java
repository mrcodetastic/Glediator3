// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.util.Random;
import java.awt.Color;

public class RandomPixel extends SuperGenerator
{
    boolean rp_random_r;
    boolean rp_random_g;
    boolean rp_random_b;
    int rp_value_r;
    int rp_value_g;
    int rp_value_b;
    int rp_speed;
    int rp_counter;
    RandomPixelOptions options;
    
    public RandomPixel(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.rp_random_r = true;
        this.rp_random_g = false;
        this.rp_random_b = false;
        this.rp_value_r = 255;
        this.rp_value_g = 0;
        this.rp_value_b = 0;
        this.rp_speed = 2;
        this.rp_counter = 0;
        this.options = null;
    }
    
    public RandomPixel(final int[] _size, final int _speed) {
        super("Random Pixel;True;False;False;0;0;150;3", _size, _speed);
        this.rp_random_r = true;
        this.rp_random_g = false;
        this.rp_random_b = false;
        this.rp_value_r = 255;
        this.rp_value_g = 0;
        this.rp_value_b = 0;
        this.rp_speed = 2;
        this.rp_counter = 0;
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new RandomPixelOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.rp_random_r = Boolean.parseBoolean(this.parameter_array[1]);
            this.rp_random_g = Boolean.parseBoolean(this.parameter_array[2]);
            this.rp_random_b = Boolean.parseBoolean(this.parameter_array[3]);
            this.rp_value_r = Integer.parseInt(this.parameter_array[4]);
            this.rp_value_g = Integer.parseInt(this.parameter_array[5]);
            this.rp_value_b = Integer.parseInt(this.parameter_array[6]);
            this.rp_speed = Integer.parseInt(this.parameter_array[7]);
            this.parameter_changed = false;
            this.options = null;
        }
        final Random rnd = new Random();
        if (this.rp_counter > this.rp_speed) {
            for (int x = 0; x < this.size[0]; ++x) {
                for (int y = 0; y < this.size[1]; ++y) {
                    int r;
                    if (this.rp_random_r) {
                        r = (int)(255.0f * rnd.nextFloat());
                    }
                    else {
                        r = this.rp_value_r;
                    }
                    int g;
                    if (this.rp_random_g) {
                        g = (int)(255.0f * rnd.nextFloat());
                    }
                    else {
                        g = this.rp_value_g;
                    }
                    int b;
                    if (this.rp_random_b) {
                        b = (int)(255.0f * rnd.nextFloat());
                    }
                    else {
                        b = this.rp_value_b;
                    }
                    image[y * this.size[0] + x] = new Color(r, g, b);
                }
            }
            this.rp_counter = 0;
        }
        ++this.rp_counter;
    }
    
    @Override
    public void closeConfigWindow() {
        if (this.options != null) {
            this.options.setVisible(false);
            this.options = null;
        }
    }
}
