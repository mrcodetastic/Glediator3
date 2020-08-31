// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.util.Random;
import java.awt.Color;

public class Fractal extends SuperGenerator
{
    int[][] color;
    int[][] level;
    int grow_size;
    boolean distortion;
    int dist_strength;
    Color temp_color;
    Random rand;
    FractalOptions options;
    
    public Fractal(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.grow_size = 3;
        this.distortion = true;
        this.dist_strength = 30;
        this.temp_color = Color.BLACK;
        this.rand = new Random();
        this.options = null;
    }
    
    public Fractal(final int[] _size, final int _speed) {
        super("Fractal;true;30;", _size, _speed);
        this.grow_size = 3;
        this.distortion = true;
        this.dist_strength = 30;
        this.temp_color = Color.BLACK;
        this.rand = new Random();
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new FractalOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.distortion = Boolean.parseBoolean(this.parameter_array[1]);
            this.dist_strength = Integer.parseInt(this.parameter_array[2]);
            this.color = new int[this.size[0]][this.size[1]];
            this.level = new int[this.size[0]][this.size[1]];
            for (int x = 0; x < this.size[0]; ++x) {
                for (int y = 0; y < this.size[1]; ++y) {
                    this.color[x][y] = 0;
                    this.level[x][y] = 0;
                }
            }
            for (int x = 0; x < this.size[0]; ++x) {
                this.color[x][this.size[1] / 3 * 1] = 1;
                this.color[x][this.size[1] / 3 * 2] = 3;
            }
            for (int y2 = 0; y2 < this.size[1]; ++y2) {
                this.color[this.size[0] / 3 * 1][y2] = 2;
                this.color[this.size[0] / 3 * 2][y2] = 3;
            }
            this.parameter_changed = false;
            this.options = null;
        }
        for (int x = 0; x < this.size[0]; ++x) {
            for (int y = 0; y < this.size[1]; ++y) {
                int pos_x;
                int pos_y;
                for (pos_x = 0, pos_y = 0; pos_x == 0 && pos_y == 0; pos_x = this.rand.nextInt(3), pos_y = this.rand.nextInt(3)) {}
                pos_x += x - 1;
                pos_y += y - 1;
                if (pos_x < 0) {
                    pos_x = this.size[0] - 1;
                }
                if (pos_y < 0) {
                    pos_y = this.size[1] - 1;
                }
                if (pos_x > this.size[0] - 1) {
                    pos_x = 0;
                }
                if (pos_y > this.size[1] - 1) {
                    pos_y = 0;
                }
                if (this.color[x][y] == 0 && this.color[pos_x][pos_y] != 0 && this.level[pos_x][pos_y] < this.grow_size) {
                    this.color[x][y] = this.color[pos_x][pos_y];
                    this.level[x][y] = this.level[pos_x][pos_y] + 1;
                }
                else {
                    switch (this.color[x][y]) {
                        case 1: {
                            if (this.color[pos_x][pos_y] == 3) {
                                --this.level[x][y];
                                ++this.level[pos_x][pos_y];
                            }
                            if (this.color[pos_x][pos_y] == 2) {
                                ++this.level[x][y];
                                --this.level[pos_x][pos_y];
                                break;
                            }
                            break;
                        }
                        case 2: {
                            if (this.color[pos_x][pos_y] == 1) {
                                --this.level[x][y];
                                ++this.level[pos_x][pos_y];
                            }
                            if (this.color[pos_x][pos_y] == 3) {
                                ++this.level[x][y];
                                --this.level[pos_x][pos_y];
                                break;
                            }
                            break;
                        }
                        case 3: {
                            if (this.color[pos_x][pos_y] == 2) {
                                --this.level[x][y];
                                ++this.level[pos_x][pos_y];
                            }
                            if (this.color[pos_x][pos_y] == 1) {
                                ++this.level[x][y];
                                --this.level[pos_x][pos_y];
                                break;
                            }
                            break;
                        }
                    }
                    if (this.level[x][y] < 0) {
                        this.level[x][y] = 0;
                    }
                    if (this.level[x][y] > this.grow_size) {
                        this.color[x][y] = this.color[pos_x][pos_y];
                        this.level[x][y] = 0;
                    }
                }
            }
        }
        for (int x = 0; x < this.size[0]; ++x) {
            for (int y = 0; y < this.size[1]; ++y) {
                if (this.distortion) {
                    final int r = this.rand.nextInt(10000);
                    if (r < this.dist_strength) {
                        this.color[x][y] = this.rand.nextInt(3) + 1;
                    }
                }
                switch (this.color[x][y]) {
                    case 0: {
                        this.temp_color = Color.BLACK;
                        break;
                    }
                    case 1: {
                        this.temp_color = Color.RED;
                        break;
                    }
                    case 2: {
                        this.temp_color = Color.GREEN;
                        break;
                    }
                    case 3: {
                        this.temp_color = Color.BLUE;
                        break;
                    }
                }
                image[y * this.size[0] + x] = this.temp_color;
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
