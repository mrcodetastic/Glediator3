// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Color;

public class Plasma extends SuperGenerator
{
    int plasma_step_width;
    int plasma_cell_size_x;
    int plasma_cell_size_y;
    int[][] plasma_lut;
    float plasma_counter;
    PlasmaOptions options;
    
    public Plasma(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.plasma_step_width = 1;
        this.plasma_cell_size_x = 3;
        this.plasma_cell_size_y = 3;
        this.plasma_lut = new int[1537][3];
        this.plasma_counter = 0.0f;
        this.options = null;
    }
    
    public Plasma(final int[] _size, final int _speed) {
        super("Plasma;10;4;4", _size, _speed);
        this.plasma_step_width = 1;
        this.plasma_cell_size_x = 3;
        this.plasma_cell_size_y = 3;
        this.plasma_lut = new int[1537][3];
        this.plasma_counter = 0.0f;
        this.options = null;
    }
    
    void Generate_Plasma_LUT() {
        for (int i = 0; i < 256; ++i) {
            this.plasma_lut[i + 0][0] = 255;
            this.plasma_lut[i + 0][1] = i;
            this.plasma_lut[i + 0][2] = 0;
        }
        for (int i = 0; i < 256; ++i) {
            this.plasma_lut[i + 256][0] = 255 - i;
            this.plasma_lut[i + 256][1] = 255;
            this.plasma_lut[i + 256][2] = 0;
        }
        for (int i = 0; i < 256; ++i) {
            this.plasma_lut[i + 512][0] = 0;
            this.plasma_lut[i + 512][1] = 255;
            this.plasma_lut[i + 512][2] = i;
        }
        for (int i = 0; i < 256; ++i) {
            this.plasma_lut[i + 768][0] = 0;
            this.plasma_lut[i + 768][1] = 255 - i;
            this.plasma_lut[i + 768][2] = 255;
        }
        for (int i = 0; i < 256; ++i) {
            this.plasma_lut[i + 1024][0] = i;
            this.plasma_lut[i + 1024][1] = 0;
            this.plasma_lut[i + 1024][2] = 255;
        }
        for (int i = 0; i < 256; ++i) {
            this.plasma_lut[i + 1280][0] = 255;
            this.plasma_lut[i + 1280][1] = 0;
            this.plasma_lut[i + 1280][2] = 255 - i;
        }
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new PlasmaOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.plasma_step_width = Integer.parseInt(this.parameter_array[1]);
            this.plasma_cell_size_x = Integer.parseInt(this.parameter_array[2]);
            this.plasma_cell_size_y = Integer.parseInt(this.parameter_array[3]);
            this.Generate_Plasma_LUT();
            this.parameter_changed = false;
            this.options = null;
        }
        this.plasma_counter += this.plasma_step_width / 10.0f;
        final double calc1 = Math.sin(this.plasma_counter * 0.006f);
        final double calc2 = Math.sin(this.plasma_counter * -0.06f);
        double xc = 25.0;
        for (int x = 0; x < this.size[0]; ++x) {
            xc += this.plasma_cell_size_x / 10.0f;
            double yc = 25.0;
            final double s1 = 768.0 + 768.0 * Math.sin(xc) * calc1;
            for (int y = 0; y < this.size[1]; ++y) {
                yc += this.plasma_cell_size_y / 10.0f;
                final double s2 = 768.0 + 768.0 * Math.sin(yc) * calc2;
                final double s3 = 768.0 + 768.0 * Math.sin((xc + yc + this.plasma_counter / 10.0f) / 2.0);
                final int pixel = (int)((s1 + s2 + s3) / 3.0);
                final int r = this.plasma_lut[pixel][0];
                final int g = this.plasma_lut[pixel][1];
                final int b = this.plasma_lut[pixel][2];
                image[y * this.size[0] + x] = new Color(r, g, b);
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
