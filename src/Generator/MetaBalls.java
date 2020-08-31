// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.util.Random;
import java.awt.Color;

public class MetaBalls extends SuperGenerator
{
    int mb_r_start;
    int mb_g_start;
    int mb_b_start;
    int mb_dia;
    int mb_number;
    int mb_speed;
    boolean mb_random_color;
    int mb_random_time;
    int[][] mb_vx;
    int[][] mb_vy;
    int[] mb_px;
    int[] mb_py;
    int[] mb_dx;
    int[] mb_dy;
    int mb_counter;
    MetaBallsOptions options;
    
    public MetaBalls(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.mb_r_start = 255;
        this.mb_g_start = 0;
        this.mb_b_start = 255;
        this.mb_dia = 14;
        this.mb_number = 4;
        this.mb_speed = 1;
        this.mb_random_color = false;
        this.mb_random_time = 10;
        this.mb_vx = new int[4][32];
        this.mb_vy = new int[4][16];
        this.mb_counter = 0;
        this.options = null;
    }
    
    public MetaBalls(final int[] _size, final int _speed) {
        super("Meta Balls;255;0;255;5;7;1;true;20", _size, _speed);
        this.mb_r_start = 255;
        this.mb_g_start = 0;
        this.mb_b_start = 255;
        this.mb_dia = 14;
        this.mb_number = 4;
        this.mb_speed = 1;
        this.mb_random_color = false;
        this.mb_random_time = 10;
        this.mb_vx = new int[4][32];
        this.mb_vy = new int[4][16];
        this.mb_counter = 0;
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new MetaBallsOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        final Random rnd = new Random();
        if (this.parameter_changed) {
            this.mb_r_start = Integer.parseInt(this.parameter_array[1]);
            this.mb_g_start = Integer.parseInt(this.parameter_array[2]);
            this.mb_b_start = Integer.parseInt(this.parameter_array[3]);
            this.mb_dia = Integer.parseInt(this.parameter_array[4]);
            this.mb_number = Integer.parseInt(this.parameter_array[5]);
            this.mb_speed = Integer.parseInt(this.parameter_array[6]);
            this.mb_random_color = Boolean.parseBoolean(this.parameter_array[7]);
            this.mb_random_time = Integer.parseInt(this.parameter_array[8]);
            this.mb_px = new int[this.mb_number];
            this.mb_py = new int[this.mb_number];
            this.mb_dx = new int[this.mb_number];
            this.mb_dy = new int[this.mb_number];
            this.mb_vx = new int[this.mb_number][this.size[0]];
            this.mb_vy = new int[this.mb_number][this.size[1]];
            for (int i = 0; i < this.mb_number; ++i) {
                this.mb_px[i] = (int)(rnd.nextDouble() * this.size[0]);
                this.mb_py[i] = (int)(rnd.nextDouble() * this.size[1]);
                if (rnd.nextDouble() < 0.5) {
                    this.mb_dx[i] = -1;
                }
                else {
                    this.mb_dx[i] = 1;
                }
                if (rnd.nextDouble() < 0.5) {
                    this.mb_dy[i] = -1;
                }
                else {
                    this.mb_dy[i] = 1;
                }
            }
            this.parameter_changed = false;
            this.options = null;
        }
        if (this.mb_random_color) {
            if (this.mb_counter > this.mb_random_time) {
                this.mb_r_start = rnd.nextInt(256);
                this.mb_g_start = rnd.nextInt(256);
                this.mb_b_start = rnd.nextInt(256);
                this.mb_counter = 0;
            }
            else {
                ++this.mb_counter;
            }
        }
        for (int j = 0; j < this.mb_number; ++j) {
            this.mb_px[j] += this.mb_speed * this.mb_dx[j];
            this.mb_py[j] += this.mb_speed * this.mb_dy[j];
            if (this.mb_px[j] < 0) {
                this.mb_dx[j] = 1;
            }
            if (this.mb_px[j] > this.size[0]) {
                this.mb_dx[j] = -1;
            }
            if (this.mb_py[j] < 0) {
                this.mb_dy[j] = 1;
            }
            if (this.mb_py[j] > this.size[1]) {
                this.mb_dy[j] = -1;
            }
            for (int x = 0; x < this.size[0]; ++x) {
                this.mb_vx[j][x] = (this.mb_px[j] - x) * (this.mb_px[j] - x);
            }
            for (int y = 0; y < this.size[1]; ++y) {
                this.mb_vy[j][y] = (this.mb_py[j] - y) * (this.mb_py[j] - y);
            }
        }
        for (int x2 = 0; x2 < this.size[0]; ++x2) {
            for (int y = 0; y < this.size[1]; ++y) {
                int R = 0;
                int G = 0;
                int B = 0;
                for (int k = 0; k < this.mb_number; ++k) {
                    final double distance = this.mb_vx[k][x2] + this.mb_vy[k][y] + 1;
                    R += (int)(this.mb_dia / distance * this.mb_r_start);
                    G += (int)(this.mb_dia / distance * this.mb_g_start);
                    B += (int)(this.mb_dia / distance * this.mb_b_start);
                }
                if (R > 255) {
                    R = 255;
                }
                if (G > 255) {
                    G = 255;
                }
                if (B > 255) {
                    B = 255;
                }
                final Color temp_color = new Color(R, G, B);
                image[y * this.size[0] + x2] = temp_color;
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
