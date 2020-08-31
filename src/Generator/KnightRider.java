// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Color;

public class KnightRider extends SuperGenerator
{
    int kr_width;
    int kr_segment_size;
    int kr_length;
    int kr_red;
    int kr_green;
    int kr_blue;
    int kr_vert_pos;
    int[] kr_intensities;
    int[] kr_hor_pos;
    int[] kr_dir;
    int kr_max_index;
    int r;
    int g;
    int b;
    Color temp_color;
    KnightRiderOptions options;
    
    public KnightRider(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.kr_width = 1;
        this.kr_segment_size = 2;
        this.kr_length = 20;
        this.kr_red = 255;
        this.kr_green = 0;
        this.kr_blue = 0;
        this.kr_vert_pos = 50;
        this.kr_intensities = new int[this.size[0]];
        this.kr_hor_pos = new int[1];
        this.kr_dir = new int[1];
        this.options = null;
    }
    
    public KnightRider(final int[] _size, final int _speed) {
        super("Knight Rider;5;20;2;255;0;0;", _size, _speed);
        this.kr_width = 1;
        this.kr_segment_size = 2;
        this.kr_length = 20;
        this.kr_red = 255;
        this.kr_green = 0;
        this.kr_blue = 0;
        this.kr_vert_pos = 50;
        this.kr_intensities = new int[this.size[0]];
        this.kr_hor_pos = new int[1];
        this.kr_dir = new int[1];
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new KnightRiderOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.kr_width = Integer.parseInt(this.parameter_array[1]);
            this.kr_length = Integer.parseInt(this.parameter_array[2]);
            this.kr_segment_size = Integer.parseInt(this.parameter_array[3]);
            this.kr_red = Integer.parseInt(this.parameter_array[4]);
            this.kr_green = Integer.parseInt(this.parameter_array[5]);
            this.kr_blue = Integer.parseInt(this.parameter_array[6]);
            this.kr_intensities = new int[this.size[0]];
            this.kr_dir[0] = 1;
            this.kr_hor_pos[0] = 0;
            this.kr_width = (int)Math.ceil(this.size[1] * (float)this.kr_width / 100.0f);
            this.kr_vert_pos = (int)Math.ceil((float)this.size[1] * 50.0 / 100.0) - this.kr_width / 2;
            if (this.size[1] % 2 == 1) {
                --this.kr_vert_pos;
            }
            this.kr_max_index = this.size[0] / this.kr_segment_size;
            for (int x = 0; x < this.size[0]; ++x) {
                for (int y = 0; y < this.size[1]; ++y) {
                    image[y * this.size[0] + x] = Color.black;
                }
            }
            this.parameter_changed = false;
            this.options = null;
        }
        if (this.kr_dir[0] == 1) {
            final int[] kr_hor_pos = this.kr_hor_pos;
            final int n = 0;
            ++kr_hor_pos[n];
        }
        else {
            final int[] kr_hor_pos2 = this.kr_hor_pos;
            final int n2 = 0;
            --kr_hor_pos2[n2];
        }
        if (this.kr_hor_pos[0] == this.kr_max_index) {
            this.kr_dir[0] = 0;
        }
        if (this.kr_hor_pos[0] == 0) {
            this.kr_dir[0] = 1;
        }
        for (int a = 0; a < this.kr_max_index; ++a) {
            if (this.kr_intensities[a] > 0) {
                final int[] kr_intensities = this.kr_intensities;
                final int n3 = a;
                --kr_intensities[n3];
            }
            if (a == this.kr_hor_pos[0]) {
                this.kr_intensities[a] = this.kr_length;
            }
            this.r = (int)(this.kr_red * (this.kr_intensities[a] / (float)this.kr_length));
            this.g = (int)(this.kr_green * (this.kr_intensities[a] / (float)this.kr_length));
            this.b = (int)(this.kr_blue * (this.kr_intensities[a] / (float)this.kr_length));
            this.temp_color = new Color(this.r, this.g, this.b);
            for (int y = 0; y < this.kr_width; ++y) {
                for (int j = 0; j < this.kr_segment_size; ++j) {
                    image[(this.kr_vert_pos + y) * this.size[0] + (j + a * this.kr_segment_size)] = this.temp_color;
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
