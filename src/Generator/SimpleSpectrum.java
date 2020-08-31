// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Color;

public class SimpleSpectrum extends SuperGenerator
{
    String ss_position;
    int ss_gain;
    int ss_red_start;
    int ss_red_end;
    int ss_green_start;
    int ss_green_end;
    int ss_blue_start;
    int ss_blue_end;
    SimpleSpectrumOptions options;
    
    public SimpleSpectrum(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.ss_position = "Bottom";
        this.ss_gain = 2;
        this.ss_red_start = 0;
        this.ss_red_end = 255;
        this.ss_green_start = 255;
        this.ss_green_end = 0;
        this.ss_blue_start = 0;
        this.ss_blue_end = 0;
        this.options = null;
    }
    
    public SimpleSpectrum(final int[] _size, final int _speed) {
        super("Simple Spectrum;Bottom;150;0;250;250;0;0;0", _size, _speed);
        this.ss_position = "Bottom";
        this.ss_gain = 2;
        this.ss_red_start = 0;
        this.ss_red_end = 255;
        this.ss_green_start = 255;
        this.ss_green_end = 0;
        this.ss_blue_start = 0;
        this.ss_blue_end = 0;
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new SimpleSpectrumOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.ss_position = this.parameter_array[1].toString();
            this.ss_gain = Integer.parseInt(this.parameter_array[2]);
            this.ss_red_start = Integer.parseInt(this.parameter_array[3]);
            this.ss_red_end = Integer.parseInt(this.parameter_array[4]);
            this.ss_green_start = Integer.parseInt(this.parameter_array[5]);
            this.ss_green_end = Integer.parseInt(this.parameter_array[6]);
            this.ss_blue_start = Integer.parseInt(this.parameter_array[7]);
            this.ss_blue_end = Integer.parseInt(this.parameter_array[8]);
            this.parameter_changed = false;
            this.options = null;
        }
        if (this.ss_position.equals("Bottom")) {
            SimpleSpectrum.audio_properties.fft_binns = (int)(this.size[0] * 1.5f);
            for (int x = 0; x < this.size[0]; ++x) {
                int h = Math.round(SimpleSpectrum.audio_properties.fft[x] * this.ss_gain);
                if (h > this.size[1] - 1) {
                    h = this.size[1] - 1;
                }
                final int h2 = this.size[1] - h - 1;
                for (int y = 0; y < this.size[1]; ++y) {
                    int r;
                    int g;
                    int b;
                    if (y < h2 | h == 0) {
                        r = 0;
                        g = 0;
                        b = 0;
                    }
                    else {
                        r = (int)(this.ss_red_start + (this.size[1] - 1 - y) / (float)this.size[1] * (this.ss_red_end - this.ss_red_start));
                        g = (int)(this.ss_green_start + (this.size[1] - 1 - y) / (float)this.size[1] * (this.ss_green_end - this.ss_green_start));
                        b = (int)(this.ss_blue_start + (this.size[1] - 1 - y) / (float)this.size[1] * (this.ss_blue_end - this.ss_blue_start));
                    }
                    image[y * this.size[0] + x] = new Color(r, g, b);
                }
            }
        }
        if (this.ss_position.equals("Top")) {
            SimpleSpectrum.audio_properties.fft_binns = (int)(this.size[0] * 1.5f);
            for (int x = 0; x < this.size[0]; ++x) {
                int h = Math.round(SimpleSpectrum.audio_properties.fft[x] * this.ss_gain);
                if (h > this.size[1] - 1) {
                    h = this.size[1] - 1;
                }
                final int h2 = h;
                for (int y = 0; y < this.size[1]; ++y) {
                    int r;
                    int g;
                    int b;
                    if (y > h2 | h == 0) {
                        r = 0;
                        g = 0;
                        b = 0;
                    }
                    else {
                        r = (int)(this.ss_red_start + y / (float)this.size[1] * (this.ss_red_end - this.ss_red_start));
                        g = (int)(this.ss_green_start + y / (float)this.size[1] * (this.ss_green_end - this.ss_green_start));
                        b = (int)(this.ss_blue_start + y / (float)this.size[1] * (this.ss_blue_end - this.ss_blue_start));
                    }
                    image[y * this.size[0] + x] = new Color(r, g, b);
                }
            }
        }
        if (this.ss_position.equals("Right")) {
            SimpleSpectrum.audio_properties.fft_binns = (int)(this.size[1] * 1.5f);
            for (int y2 = 0; y2 < this.size[1]; ++y2) {
                int h = Math.round(SimpleSpectrum.audio_properties.fft[y2] * this.ss_gain);
                if (h > this.size[0] - 1) {
                    h = this.size[0] - 1;
                }
                final int h2 = this.size[0] - h - 1;
                for (int x2 = 0; x2 < this.size[0]; ++x2) {
                    int r;
                    int g;
                    int b;
                    if (x2 < h2 | h == 0) {
                        r = 0;
                        g = 0;
                        b = 0;
                    }
                    else {
                        r = (int)(this.ss_red_start + (this.size[0] - 1 - x2) / (float)this.size[0] * (this.ss_red_end - this.ss_red_start));
                        g = (int)(this.ss_green_start + (this.size[0] - 1 - x2) / (float)this.size[0] * (this.ss_green_end - this.ss_green_start));
                        b = (int)(this.ss_blue_start + (this.size[0] - 1 - x2) / (float)this.size[0] * (this.ss_blue_end - this.ss_blue_start));
                    }
                    image[y2 * this.size[0] + x2] = new Color(r, g, b);
                }
            }
        }
        if (this.ss_position.equals("Left")) {
            SimpleSpectrum.audio_properties.fft_binns = (int)(this.size[1] * 1.5f);
            for (int y2 = 0; y2 < this.size[1]; ++y2) {
                int h = Math.round(SimpleSpectrum.audio_properties.fft[y2] * this.ss_gain);
                if (h > this.size[0] - 1) {
                    h = this.size[0] - 1;
                }
                final int h2 = h;
                for (int x2 = 0; x2 < this.size[0]; ++x2) {
                    int r;
                    int g;
                    int b;
                    if (x2 > h2) {
                        r = 0;
                        g = 0;
                        b = 0;
                    }
                    else {
                        r = (int)(this.ss_red_start + x2 / (float)this.size[0] * (this.ss_red_end - this.ss_red_start));
                        g = (int)(this.ss_green_start + x2 / (float)this.size[0] * (this.ss_green_end - this.ss_green_start));
                        b = (int)(this.ss_blue_start + x2 / (float)this.size[0] * (this.ss_blue_end - this.ss_blue_start));
                    }
                    image[y2 * this.size[0] + x2] = new Color(r, g, b);
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
