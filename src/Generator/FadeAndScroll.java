// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Color;

public class FadeAndScroll extends SuperGenerator
{
    int fs_speed;
    int fs_zoom;
    int fs_index;
    String fs_scroll_type;
    String fs_dir;
    String fs_palette;
    int[] fs_height;
    Color[] fs_lut;
    FadeAndScrollOptions options;
    
    public FadeAndScroll(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.fs_speed = 1;
        this.fs_zoom = 1;
        this.fs_index = 0;
        this.fs_scroll_type = "Flat";
        this.fs_dir = "Forward";
        this.fs_palette = "RGB";
        this.fs_height = new int[512];
        this.fs_lut = new Color[512];
        this.options = null;
    }
    
    public FadeAndScroll(final int[] _size, final int _speed) {
        super("Fade and Scroll;10;30;Horizontal Symetric;Forward;RGB", _size, _speed);
        this.fs_speed = 1;
        this.fs_zoom = 1;
        this.fs_index = 0;
        this.fs_scroll_type = "Flat";
        this.fs_dir = "Forward";
        this.fs_palette = "RGB";
        this.fs_height = new int[512];
        this.fs_lut = new Color[512];
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new FadeAndScrollOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.fs_speed = Integer.parseInt(this.parameter_array[1]);
            this.fs_zoom = Integer.parseInt(this.parameter_array[2]);
            this.fs_scroll_type = this.parameter_array[3];
            this.fs_dir = this.parameter_array[4];
            this.fs_palette = this.parameter_array[5];
            if (this.fs_palette.equals("RGB")) {
                this.fs_lut = new Color[1536];
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 0] = new Color(255, i, 0);
                }
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 256] = new Color(255 - i, 255, 0);
                }
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 512] = new Color(0, 255, i);
                }
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 768] = new Color(0, 255 - i, 255);
                }
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 1024] = new Color(i, 0, 255);
                }
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 1280] = new Color(255, 0, 255 - i);
                }
            }
            if (this.fs_palette.equals("Red")) {
                this.fs_lut = new Color[512];
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 0] = new Color(i, 0, 0);
                }
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 256] = new Color(255 - i, 0, 0);
                }
            }
            if (this.fs_palette.equals("Green")) {
                this.fs_lut = new Color[512];
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 0] = new Color(0, i, 0);
                }
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 256] = new Color(0, 255 - i, 0);
                }
            }
            if (this.fs_palette.equals("Blue")) {
                this.fs_lut = new Color[512];
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 0] = new Color(0, 0, i);
                }
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 256] = new Color(0, 0, 255 - i);
                }
            }
            if (this.fs_palette.equals("White")) {
                this.fs_lut = new Color[512];
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 0] = new Color(i, i, i);
                }
                for (int i = 0; i < 256; ++i) {
                    this.fs_lut[i + 256] = new Color(255 - i, 255 - i, 255 - i);
                }
            }
            if (this.fs_palette.equals("Half")) {
                this.fs_lut = new Color[768];
                for (int i = 0; i < 128; ++i) {
                    this.fs_lut[i + 0] = new Color(254 - 2 * i, 0, 127 - i);
                }
                for (int i = 0; i < 128; ++i) {
                    this.fs_lut[i + 128] = new Color(i, 2 * i, 0);
                }
                for (int i = 0; i < 128; ++i) {
                    this.fs_lut[i + 256] = new Color(127 - i, 254 - 2 * i, 0);
                }
                for (int i = 0; i < 128; ++i) {
                    this.fs_lut[i + 384] = new Color(0, i, 2 * i);
                }
                for (int i = 0; i < 128; ++i) {
                    this.fs_lut[i + 512] = new Color(0, 127 - i, 254 - 2 * i);
                }
                for (int i = 0; i < 128; ++i) {
                    this.fs_lut[i + 640] = new Color(2 * i, 0, i);
                }
            }
            this.fs_height = new int[this.size[0] * this.size[1]];
            if (this.fs_scroll_type.equals("Flat")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = 0;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Vertical")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = y * this.fs_zoom % this.fs_lut.length;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Horizontal")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = x * this.fs_zoom % this.fs_lut.length;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Diagonal Left")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = (x + y) * this.fs_zoom % this.fs_lut.length;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Diagonal Right")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = (x + this.size[1] - y) * this.fs_zoom % this.fs_lut.length;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Horizontal Symetric")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = Math.abs(x - this.size[0] / 2) * this.fs_zoom % this.fs_lut.length;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Vertical Symetric")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = Math.abs(y - this.size[1] / 2) * this.fs_zoom % this.fs_lut.length;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Hyperbel")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = Math.abs(y - this.size[1] / 2) * Math.abs(x - this.size[0] / 2) * this.fs_zoom / 10 % this.fs_lut.length;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Diamond")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = (Math.abs(y - this.size[1] / 2) + Math.abs(x - this.size[0] / 2)) * this.fs_zoom % this.fs_lut.length;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Circle")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = ((y - this.size[1] / 2) * (y - this.size[1] / 2) + (x - this.size[0] / 2) * (x - this.size[0] / 2)) * this.fs_zoom / 10 % this.fs_lut.length;
                    }
                }
            }
            if (this.fs_scroll_type.equals("Plasma")) {
                for (int x = 0; x < this.size[0]; ++x) {
                    for (int y = 0; y < this.size[1]; ++y) {
                        this.fs_index = y * this.size[0] + x;
                        this.fs_height[this.fs_index] = (int)((this.fs_lut.length / 2.0f + (float)this.fs_lut.length / 2.0 * Math.sin(x / (float)this.fs_zoom) + (this.fs_lut.length / 2.0f + (float)this.fs_lut.length / 2.0 * Math.sin(y / (float)this.fs_zoom))) / 2.0);
                    }
                }
            }
            this.parameter_changed = false;
            this.options = null;
        }
        if (this.fs_dir.equals("Forward")) {
            for (int x = 0; x < this.size[0]; ++x) {
                for (int y = 0; y < this.size[1]; ++y) {
                    this.fs_index = y * this.size[0] + x;
                    this.fs_height[this.fs_index] += this.fs_speed;
                    if (this.fs_height[this.fs_index] > this.fs_lut.length - 1) {
                        this.fs_height[this.fs_index] = 0;
                    }
                    image[this.fs_index] = this.fs_lut[this.fs_height[this.fs_index]];
                }
            }
        }
        if (this.fs_dir.equals("Backward")) {
            for (int x = 0; x < this.size[0]; ++x) {
                for (int y = 0; y < this.size[1]; ++y) {
                    this.fs_index = y * this.size[0] + x;
                    this.fs_height[this.fs_index] -= this.fs_speed;
                    if (this.fs_height[this.fs_index] < 0) {
                        this.fs_height[this.fs_index] = this.fs_lut.length - 1;
                    }
                    image[this.fs_index] = this.fs_lut[this.fs_height[this.fs_index]];
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
