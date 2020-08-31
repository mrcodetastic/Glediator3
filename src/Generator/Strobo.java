// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Color;

public class Strobo extends SuperGenerator
{
    int strobo_r;
    int strobo_g;
    int strobo_b;
    String strobo_mode;
    int strobo_speed;
    int strobo_duration;
    int strobo_trigger_level;
    int strobo_counter;
    StroboOptions options;
    
    public Strobo(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.strobo_r = 0;
        this.strobo_g = 0;
        this.strobo_b = 255;
        this.strobo_mode = "None";
        this.strobo_speed = 5;
        this.strobo_duration = 5;
        this.strobo_trigger_level = 65;
        this.strobo_counter = 0;
        this.options = null;
    }
    
    public Strobo(final int[] _size, final int _speed) {
        super("Strobo;255;0;0;None;5;2;50", _size, _speed);
        this.strobo_r = 0;
        this.strobo_g = 0;
        this.strobo_b = 255;
        this.strobo_mode = "None";
        this.strobo_speed = 5;
        this.strobo_duration = 5;
        this.strobo_trigger_level = 65;
        this.strobo_counter = 0;
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new StroboOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.strobo_r = Integer.parseInt(this.parameter_array[1]);
            this.strobo_g = Integer.parseInt(this.parameter_array[2]);
            this.strobo_b = Integer.parseInt(this.parameter_array[3]);
            this.strobo_mode = this.parameter_array[4];
            this.strobo_speed = Integer.parseInt(this.parameter_array[5]);
            this.strobo_duration = Integer.parseInt(this.parameter_array[6]);
            this.strobo_trigger_level = Integer.parseInt(this.parameter_array[7]);
            this.parameter_changed = false;
            this.options = null;
        }
        Color temp_color = Color.BLACK;
        if (this.strobo_mode.equals("None")) {
            if (this.strobo_counter >= this.strobo_speed && this.strobo_counter < this.strobo_speed + this.strobo_duration) {
                temp_color = new Color(this.strobo_r, this.strobo_g, this.strobo_b);
            }
            else {
                temp_color = Color.BLACK;
            }
            ++this.strobo_counter;
            if (this.strobo_counter >= this.strobo_speed + this.strobo_duration) {
                this.strobo_counter = 0;
            }
        }
        if (this.strobo_mode.equals("Trigger")) {
            final int level = Strobo.audio_properties.stereo_audio_level;
            if (level >= this.strobo_trigger_level) {
                temp_color = new Color(this.strobo_r, this.strobo_g, this.strobo_b);
            }
            else {
                temp_color = Color.BLACK;
            }
        }
        if (this.strobo_mode.equals("Adaptive Trigger")) {
            final int level = Strobo.audio_properties.stereo_audio_level;
            final int avr_level = Strobo.audio_properties.average_audio_level;
            this.strobo_trigger_level = avr_level;
            if (level >= this.strobo_trigger_level) {
                temp_color = new Color(this.strobo_r, this.strobo_g, this.strobo_b);
            }
            else {
                temp_color = Color.BLACK;
            }
        }
        for (int x = 0; x < this.size[0]; ++x) {
            for (int y = 0; y < this.size[1]; ++y) {
                image[y * this.size[0] + x] = temp_color;
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
