// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.util.Random;
import java.awt.Color;

public class FallingObjects extends SuperGenerator
{
    boolean[] fo_is_active;
    int[] fo_position;
    int fo_red_value;
    int fo_green_value;
    int fo_blue_value;
    int fo_length;
    int fo_number;
    boolean fo_random_color;
    String fo_direction;
    int fo_random_intervall;
    int fo_counter;
    FallingObjectsOptions options;
    
    public FallingObjects(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.fo_red_value = 255;
        this.fo_green_value = 0;
        this.fo_blue_value = 255;
        this.fo_length = 12;
        this.fo_number = 5;
        this.fo_random_color = true;
        this.fo_direction = "Bottom";
        this.fo_random_intervall = 10;
        this.fo_counter = 0;
        this.options = null;
    }
    
    public FallingObjects(final int[] _size, final int _speed) {
        super("Falling Objects;0;255;255;20;1;10;true;Bottom;50", _size, _speed);
        this.fo_red_value = 255;
        this.fo_green_value = 0;
        this.fo_blue_value = 255;
        this.fo_length = 12;
        this.fo_number = 5;
        this.fo_random_color = true;
        this.fo_direction = "Bottom";
        this.fo_random_intervall = 10;
        this.fo_counter = 0;
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new FallingObjectsOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.fo_red_value = Integer.parseInt(this.parameter_array[1]);
            this.fo_green_value = Integer.parseInt(this.parameter_array[2]);
            this.fo_blue_value = Integer.parseInt(this.parameter_array[3]);
            this.fo_length = Integer.parseInt(this.parameter_array[4]);
            this.fo_number = Integer.parseInt(this.parameter_array[5]);
            this.fo_random_color = Boolean.parseBoolean(this.parameter_array[7]);
            this.fo_direction = this.parameter_array[8];
            this.fo_random_intervall = Integer.parseInt(this.parameter_array[9]);
            if (this.size[0] > this.size[1]) {
                this.fo_is_active = new boolean[this.size[0]];
                this.fo_position = new int[this.size[0]];
            }
            else {
                this.fo_is_active = new boolean[this.size[1]];
                this.fo_position = new int[this.size[1]];
            }
            this.parameter_changed = false;
            this.options = null;
        }
        final Random rnd = new Random();
        if (this.fo_random_color) {
            if (this.fo_counter > this.fo_random_intervall) {
                this.fo_red_value = (int)(rnd.nextDouble() * 255.0);
                this.fo_green_value = (int)(rnd.nextDouble() * 255.0);
                this.fo_blue_value = (int)(rnd.nextDouble() * 255.0);
                this.fo_counter = 0;
            }
            else {
                ++this.fo_counter;
            }
        }
        if (this.fo_direction.equals("Bottom")) {
            for (int x = 0; x < this.size[0]; ++x) {
                for (int y = 1; y < this.size[1]; ++y) {
                    image[(this.size[1] - y) * this.size[0] + x] = image[(this.size[1] - y - 1) * this.size[0] + x];
                }
            }
            for (int x = 0; x < this.size[0]; ++x) {
                final int ra = (int)(rnd.nextDouble() * 100.0);
                if (ra < this.fo_number) {
                    this.fo_is_active[x] = true;
                    this.fo_position[x] = 0;
                }
                if (this.fo_is_active[x]) {
                    int r = this.fo_red_value - this.fo_position[x] * (int)Math.floor(this.fo_red_value / (double)this.fo_length);
                    int g = this.fo_green_value - this.fo_position[x] * (int)Math.floor(this.fo_green_value / (double)this.fo_length);
                    int b = this.fo_blue_value - this.fo_position[x] * (int)Math.floor(this.fo_blue_value / (double)this.fo_length);
                    if (r > 255) {
                        r = 255;
                    }
                    if (g > 255) {
                        g = 255;
                    }
                    if (b > 255) {
                        b = 255;
                    }
                    if (r < 0) {
                        r = 0;
                    }
                    if (g < 0) {
                        g = 0;
                    }
                    if (b < 0) {
                        b = 0;
                    }
                    image[x] = new Color(r, g, b);
                    final int[] fo_position = this.fo_position;
                    final int n = x;
                    ++fo_position[n];
                    if (this.fo_position[x] > this.fo_length) {
                        this.fo_is_active[x] = false;
                        this.fo_position[x] = 0;
                    }
                }
                else {
                    image[x] = Color.BLACK;
                }
            }
        }
        if (this.fo_direction.equals("Top")) {
            for (int x = 0; x < this.size[0]; ++x) {
                for (int y = 0; y < this.size[1] - 1; ++y) {
                    image[y * this.size[0] + x] = image[(y + 1) * this.size[0] + x];
                }
            }
            for (int x = 0; x < this.size[0]; ++x) {
                final int ra = (int)(rnd.nextDouble() * 100.0);
                if (ra < this.fo_number) {
                    this.fo_is_active[x] = true;
                    this.fo_position[x] = 0;
                }
                if (this.fo_is_active[x]) {
                    int r = this.fo_red_value - this.fo_position[x] * (int)Math.floor(this.fo_red_value / (double)this.fo_length);
                    int g = this.fo_green_value - this.fo_position[x] * (int)Math.floor(this.fo_green_value / (double)this.fo_length);
                    int b = this.fo_blue_value - this.fo_position[x] * (int)Math.floor(this.fo_blue_value / (double)this.fo_length);
                    if (r > 255) {
                        r = 255;
                    }
                    if (g > 255) {
                        g = 255;
                    }
                    if (b > 255) {
                        b = 255;
                    }
                    if (r < 0) {
                        r = 0;
                    }
                    if (g < 0) {
                        g = 0;
                    }
                    if (b < 0) {
                        b = 0;
                    }
                    image[(this.size[1] - 1) * this.size[0] + x] = new Color(r, g, b);
                    final int[] fo_position2 = this.fo_position;
                    final int n2 = x;
                    ++fo_position2[n2];
                    if (this.fo_position[x] > this.fo_length) {
                        this.fo_is_active[x] = false;
                        this.fo_position[x] = 0;
                    }
                }
                else {
                    image[(this.size[1] - 1) * this.size[0] + x] = Color.BLACK;
                }
            }
        }
        if (this.fo_direction.equals("Right")) {
            for (int x = 1; x < this.size[0]; ++x) {
                for (int y = 0; y < this.size[1]; ++y) {
                    image[y * this.size[0] + (this.size[0] - x)] = image[y * this.size[0] + (this.size[0] - x - 1)];
                }
            }
            for (int y2 = 0; y2 < this.size[1]; ++y2) {
                final int ra = (int)(rnd.nextDouble() * 100.0);
                if (ra < this.fo_number) {
                    this.fo_is_active[y2] = true;
                    this.fo_position[y2] = 0;
                }
                if (this.fo_is_active[y2]) {
                    int r = this.fo_red_value - this.fo_position[y2] * (int)Math.floor(this.fo_red_value / (double)this.fo_length);
                    int g = this.fo_green_value - this.fo_position[y2] * (int)Math.floor(this.fo_green_value / (double)this.fo_length);
                    int b = this.fo_blue_value - this.fo_position[y2] * (int)Math.floor(this.fo_blue_value / (double)this.fo_length);
                    if (r > 255) {
                        r = 255;
                    }
                    if (g > 255) {
                        g = 255;
                    }
                    if (b > 255) {
                        b = 255;
                    }
                    if (r < 0) {
                        r = 0;
                    }
                    if (g < 0) {
                        g = 0;
                    }
                    if (b < 0) {
                        b = 0;
                    }
                    image[y2 * this.size[0]] = new Color(r, g, b);
                    final int[] fo_position3 = this.fo_position;
                    final int n3 = y2;
                    ++fo_position3[n3];
                    if (this.fo_position[y2] > this.fo_length) {
                        this.fo_is_active[y2] = false;
                        this.fo_position[y2] = 0;
                    }
                }
                else {
                    image[y2 * this.size[0]] = Color.BLACK;
                }
            }
        }
        if (this.fo_direction.equals("Left")) {
            for (int x = 0; x < this.size[0] - 1; ++x) {
                for (int y = 0; y < this.size[1]; ++y) {
                    image[y * this.size[0] + x] = image[y * this.size[0] + (x + 1)];
                }
            }
            for (int y2 = 0; y2 < this.size[1]; ++y2) {
                final int ra = (int)(rnd.nextDouble() * 100.0);
                if (ra < this.fo_number) {
                    this.fo_is_active[y2] = true;
                    this.fo_position[y2] = 0;
                }
                if (this.fo_is_active[y2]) {
                    int r = this.fo_red_value - this.fo_position[y2] * (int)Math.floor(this.fo_red_value / (double)this.fo_length);
                    int g = this.fo_green_value - this.fo_position[y2] * (int)Math.floor(this.fo_green_value / (double)this.fo_length);
                    int b = this.fo_blue_value - this.fo_position[y2] * (int)Math.floor(this.fo_blue_value / (double)this.fo_length);
                    if (r > 255) {
                        r = 255;
                    }
                    if (g > 255) {
                        g = 255;
                    }
                    if (b > 255) {
                        b = 255;
                    }
                    if (r < 0) {
                        r = 0;
                    }
                    if (g < 0) {
                        g = 0;
                    }
                    if (b < 0) {
                        b = 0;
                    }
                    image[y2 * this.size[0] + this.size[0] - 1] = new Color(r, g, b);
                    final int[] fo_position4 = this.fo_position;
                    final int n4 = y2;
                    ++fo_position4[n4];
                    if (this.fo_position[y2] > this.fo_length) {
                        this.fo_is_active[y2] = false;
                        this.fo_position[y2] = 0;
                    }
                }
                else {
                    image[y2 * this.size[0] + this.size[0] - 1] = Color.BLACK;
                }
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
