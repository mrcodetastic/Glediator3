// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.util.Random;
import java.awt.Color;

public class FadingPixel extends SuperGenerator
{
    int fp_num;
    int fp_speed;
    String fp_color;
    int index;
    Color temp_color;
    int r;
    int g;
    int b;
    Random rnd;
    FadingPixelOptions options;
    
    public FadingPixel(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.fp_num = 10;
        this.fp_speed = 5;
        this.fp_color = "Random";
        this.index = 0;
        this.temp_color = Color.BLACK;
        this.rnd = new Random();
        this.options = null;
    }
    
    public FadingPixel(final int[] _size, final int _speed) {
        super("Fading Pixel;10;15;Green;", _size, _speed);
        this.fp_num = 10;
        this.fp_speed = 5;
        this.fp_color = "Random";
        this.index = 0;
        this.temp_color = Color.BLACK;
        this.rnd = new Random();
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new FadingPixelOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.fp_num = Integer.parseInt(this.parameter_array[1]);
            this.fp_speed = Integer.parseInt(this.parameter_array[2]);
            this.fp_color = this.parameter_array[3];
            this.parameter_changed = false;
            this.options = null;
        }
        for (int x = 0; x < this.size[0]; ++x) {
            for (int y = 0; y < this.size[1]; ++y) {
                this.index = y * this.size[0] + x;
                this.temp_color = image[this.index];
                this.r = this.temp_color.getRed();
                this.g = this.temp_color.getGreen();
                this.b = this.temp_color.getBlue();
                if (this.temp_color.equals(Color.black)) {
                    if (this.rnd.nextInt(100) < this.fp_num && this.rnd.nextInt(100) < this.fp_num) {
                        if (this.fp_color.equals("White")) {
                            this.temp_color = new Color(255, 255, 255);
                        }
                        if (this.fp_color.equals("Red")) {
                            this.temp_color = new Color(255, 0, 0);
                        }
                        if (this.fp_color.equals("Green")) {
                            this.temp_color = new Color(0, 255, 0);
                        }
                        if (this.fp_color.equals("Blue")) {
                            this.temp_color = new Color(0, 0, 255);
                        }
                        if (this.fp_color.equals("Random")) {
                            if (this.rnd.nextInt(100) < 50) {
                                this.r = 0;
                            }
                            else {
                                this.r = 255;
                            }
                            if (this.rnd.nextInt(100) < 50) {
                                this.g = 0;
                            }
                            else {
                                this.g = 255;
                            }
                            if (this.rnd.nextInt(100) < 50) {
                                this.b = 0;
                            }
                            else {
                                this.b = 255;
                            }
                            this.temp_color = new Color(this.r, this.g, this.b);
                        }
                    }
                }
                else {
                    this.r -= 21 - this.fp_speed;
                    this.g -= 21 - this.fp_speed;
                    this.b -= 21 - this.fp_speed;
                    if (this.r < 0) {
                        this.r = 0;
                    }
                    if (this.g < 0) {
                        this.g = 0;
                    }
                    if (this.b < 0) {
                        this.b = 0;
                    }
                    this.temp_color = new Color(this.r, this.g, this.b);
                }
                image[this.index] = this.temp_color;
            }
        }
    }
    
    @Override
    public void closeConfigWindow() {
        if (this.options != null) {
            this.options.setVisible(false);
            this.options = null;
        }
    }
}
