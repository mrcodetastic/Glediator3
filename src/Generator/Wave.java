// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Color;

public class Wave extends SuperGenerator
{
    int wave_step_width;
    String wave_dir;
    boolean wave_change_r;
    boolean wave_change_g;
    boolean wave_change_b;
    int wave_wave_lenght;
    int wave_value_r;
    int wave_value_g;
    int wave_value_b;
    int wave_counter;
    WaveOptions options;
    
    public Wave(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.wave_step_width = 1;
        this.wave_dir = "Top";
        this.wave_change_r = true;
        this.wave_change_g = false;
        this.wave_change_b = false;
        this.wave_wave_lenght = 24;
        this.wave_value_r = 255;
        this.wave_value_g = 0;
        this.wave_value_b = 0;
        this.wave_counter = 0;
        this.options = null;
    }
    
    public Wave(final int[] _size, final int _speed) {
        super("Wave;1;Right;false;true;false;150;0;0;0", _size, _speed);
        this.wave_step_width = 1;
        this.wave_dir = "Top";
        this.wave_change_r = true;
        this.wave_change_g = false;
        this.wave_change_b = false;
        this.wave_wave_lenght = 24;
        this.wave_value_r = 255;
        this.wave_value_g = 0;
        this.wave_value_b = 0;
        this.wave_counter = 0;
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new WaveOptions(this)).setVisible(true);
    }
    
    @Override
    public void closeConfigWindow() {
        if (this.options != null) {
            this.options.setVisible(false);
            this.options = null;
        }
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.wave_step_width = Integer.parseInt(this.parameter_array[1]);
            this.wave_dir = this.parameter_array[2];
            this.wave_change_r = Boolean.parseBoolean(this.parameter_array[3]);
            this.wave_change_g = Boolean.parseBoolean(this.parameter_array[4]);
            this.wave_change_b = Boolean.parseBoolean(this.parameter_array[5]);
            this.wave_wave_lenght = Integer.parseInt(this.parameter_array[6]);
            this.wave_value_r = Integer.parseInt(this.parameter_array[7]);
            this.wave_value_g = Integer.parseInt(this.parameter_array[8]);
            this.wave_value_b = Integer.parseInt(this.parameter_array[9]);
            this.parameter_changed = false;
            this.options = null;
        }
        int ce = 0;
        this.wave_counter += this.wave_step_width;
        for (int x = 0; x < this.size[0]; ++x) {
            for (int y = 0; y < this.size[1]; ++y) {
                if (this.wave_dir.equals("Top Left")) {
                    ce = x + y + this.wave_counter;
                }
                if (this.wave_dir.equals("Top Right")) {
                    ce = x - y - this.wave_counter;
                }
                if (this.wave_dir.equals("Bottom Left")) {
                    ce = x - y + this.wave_counter;
                }
                if (this.wave_dir.equals("Bottom Right")) {
                    ce = x + y - this.wave_counter;
                }
                if (this.wave_dir.equals("Top")) {
                    ce = y + this.wave_counter;
                }
                if (this.wave_dir.equals("Bottom")) {
                    ce = y - this.wave_counter;
                }
                if (this.wave_dir.equals("Right")) {
                    ce = x - this.wave_counter;
                }
                if (this.wave_dir.equals("Left")) {
                    ce = x + this.wave_counter;
                }
                int r;
                if (this.wave_change_r) {
                    r = (int)Math.ceil(255.0 - Math.abs(Math.sin(this.wave_wave_lenght / 200.0 * ce / 31.0 * 3.1415)) * 255.0);
                }
                else {
                    r = this.wave_value_r;
                }
                int g;
                if (this.wave_change_g) {
                    g = (int)Math.ceil(255.0 - Math.abs(Math.sin(this.wave_wave_lenght / 200.0 * ce / 31.0 * 3.1415)) * 255.0);
                }
                else {
                    g = this.wave_value_g;
                }
                int b;
                if (this.wave_change_b) {
                    b = (int)Math.ceil(255.0 - Math.abs(Math.sin(this.wave_wave_lenght / 200.0 * ce / 31.0 * 3.1415)) * 255.0);
                }
                else {
                    b = this.wave_value_b;
                }
                image[y * this.size[0] + x] = new Color(r, g, b);
            }
        }
    }
}
