// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.util.Random;
import java.awt.Color;

public class Fire extends SuperGenerator
{
    int fire_stepwidth;
    int fire_rand_fraction;
    int[] fire_sector_speed;
    int[] fire_line_zero;
    int fire_max_speed;
    Color[] fire_palette;
    int fire_max_start_int;
    int fire_index;
    int[] fire_lut;
    FireOptions options;
    
    public Fire(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.fire_stepwidth = 5;
        this.fire_rand_fraction = 5;
        this.fire_sector_speed = new int[32];
        this.fire_line_zero = new int[32];
        this.fire_max_speed = 5;
        this.fire_palette = new Color[100];
        this.fire_max_start_int = 99;
        this.fire_index = 0;
        this.fire_lut = new int[512];
        this.options = null;
    }
    
    public Fire(final int[] _size, final int _speed) {
        super("Fire;2;2;35;99", _size, _speed);
        this.fire_stepwidth = 5;
        this.fire_rand_fraction = 5;
        this.fire_sector_speed = new int[32];
        this.fire_line_zero = new int[32];
        this.fire_max_speed = 5;
        this.fire_palette = new Color[100];
        this.fire_max_start_int = 99;
        this.fire_index = 0;
        this.fire_lut = new int[512];
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new FireOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        final Random rand = new Random();
        final int max = this.fire_palette.length - 1;
        if (this.parameter_changed) {
            this.fire_stepwidth = Integer.parseInt(this.parameter_array[1]);
            this.fire_rand_fraction = Integer.parseInt(this.parameter_array[2]);
            this.fire_max_speed = Integer.parseInt(this.parameter_array[3]);
            this.fire_max_start_int = Integer.parseInt(this.parameter_array[4]);
            this.fire_sector_speed = new int[this.size[0]];
            this.fire_line_zero = new int[this.size[0]];
            for (int i = 0; i < this.size[0]; ++i) {
                this.fire_sector_speed[i] = this.fire_max_speed - rand.nextInt(2 * this.fire_max_speed);
                this.fire_line_zero[i] = 0;
            }
            this.fire_lut = new int[this.size[0] * this.size[1]];
            for (int i = 0; i < 50; ++i) {
                this.fire_palette[i + 0] = new Color(245, i * 4, i * 2);
            }
            for (int i = 0; i < 25; ++i) {
                this.fire_palette[i + 50] = new Color(245 - 5 * i, 196 - 7 * i, 98 - 4 * i);
            }
            for (int i = 0; i < 25; ++i) {
                this.fire_palette[i + 75] = new Color(0, 0, 0);
            }
            this.parameter_changed = false;
            this.options = null;
        }
        for (int x = 0; x < this.size[0]; ++x) {
            this.fire_line_zero[x] = this.fire_line_zero[x] + this.fire_sector_speed[x] - rand.nextInt(25);
            if (this.fire_line_zero[x] < 0) {
                this.fire_line_zero[x] = 0;
                this.fire_sector_speed[x] = this.fire_max_speed / 2 + rand.nextInt(this.fire_max_speed) / 2;
            }
            if (this.fire_line_zero[x] > this.fire_max_start_int) {
                this.fire_line_zero[x] = this.fire_max_start_int;
                this.fire_sector_speed[x] = 0 - this.fire_max_speed / 2 - rand.nextInt(this.fire_max_speed) / 2;
            }
            if (rand.nextInt(100) < 20) {
                this.fire_sector_speed[x] *= (int)rand.nextFloat();
            }
        }
        this.fire_index = this.size[0] * (this.size[1] - 1);
        int temp = (this.fire_line_zero[0] + this.fire_line_zero[1]) / 2 + this.fire_stepwidth + rand.nextInt(this.fire_rand_fraction);
        if (temp > max) {
            temp = max;
        }
        if (temp < 0) {
            temp = 0;
        }
        image[this.fire_index] = this.fire_palette[temp];
        this.fire_index = this.size[0] * this.size[1] - 1;
        temp = (this.fire_line_zero[this.size[0] - 1] + this.fire_line_zero[this.size[0] - 2]) / 2 + this.fire_stepwidth + rand.nextInt(this.fire_rand_fraction);
        if (temp > max) {
            temp = max;
        }
        if (temp < 0) {
            temp = 0;
        }
        image[this.fire_index] = this.fire_palette[temp];
        for (int j = 1; j < this.size[0] - 1; ++j) {
            this.fire_index = this.size[0] * (this.size[1] - 1) + j;
            this.fire_lut[this.fire_index] = (this.fire_line_zero[j - 1] + this.fire_line_zero[j] + this.fire_line_zero[j + 1]) / 3 + this.fire_stepwidth + rand.nextInt(this.fire_rand_fraction);
            if (this.fire_lut[this.fire_index] > max) {
                this.fire_lut[this.fire_index] = max;
            }
            image[this.fire_index] = this.fire_palette[this.fire_lut[this.fire_index]];
        }
        for (int y = 1; y < this.size[1]; ++y) {
            this.fire_index = this.size[1] - 1 - y;
            int index_a = this.fire_index * this.size[0];
            int index_c = (this.fire_index + 1) * this.size[0];
            int index_d = (this.fire_index + 1) * this.size[0] + 1;
            this.fire_lut[index_a] = (this.fire_lut[index_c] + this.fire_lut[index_d]) / 2 + this.fire_stepwidth + rand.nextInt(this.fire_rand_fraction);
            if (this.fire_lut[index_a] > max) {
                this.fire_lut[index_a] = max;
            }
            image[index_a] = this.fire_palette[this.fire_lut[index_a]];
            index_a = this.fire_index * this.size[0] + (this.size[0] - 1);
            index_c = (this.fire_index + 1) * this.size[0] + (this.size[0] - 1);
            index_d = (this.fire_index + 1) * this.size[0] + (this.size[0] - 2);
            this.fire_lut[index_a] = (this.fire_lut[index_c] + this.fire_lut[index_d]) / 2 + this.fire_stepwidth + rand.nextInt(this.fire_rand_fraction);
            if (this.fire_lut[index_a] > max) {
                this.fire_lut[index_a] = max;
            }
            image[index_a] = this.fire_palette[this.fire_lut[index_a]];
        }
        for (int x2 = 1; x2 < this.size[0] - 1; ++x2) {
            for (int y2 = 1; y2 < this.size[1]; ++y2) {
                this.fire_index = this.size[1] - 1 - y2;
                final int index_a2 = this.fire_index * this.size[0] + x2;
                final int index_b = (this.fire_index + 1) * this.size[0] + (x2 - 1);
                final int index_c2 = (this.fire_index + 1) * this.size[0] + x2;
                final int index_d2 = (this.fire_index + 1) * this.size[0] + (x2 + 1);
                this.fire_lut[index_a2] = (this.fire_lut[index_b] + this.fire_lut[index_c2] + this.fire_lut[index_d2]) / 3 + this.fire_stepwidth + rand.nextInt(this.fire_rand_fraction);
                if (this.fire_lut[index_a2] > max) {
                    this.fire_lut[index_a2] = max;
                }
                image[index_a2] = this.fire_palette[this.fire_lut[index_a2]];
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
