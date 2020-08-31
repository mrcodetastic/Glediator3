// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;

public class ExpandingObjects extends SuperGenerator
{
    String eo_shape;
    int eo_red;
    int eo_green;
    int eo_blue;
    boolean eo_random;
    int eo_count;
    int eo_size;
    int eo_growth;
    int[] eo_objects_pos_x;
    int[] eo_objects_pos_y;
    int[] eo_objects_expand;
    int[] eo_objects_fade;
    Color[] eo_objects_col;
    ExpandingObjectsOptions options;
    
    public ExpandingObjects(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.eo_shape = "Open Circle";
        this.eo_red = 255;
        this.eo_green = 0;
        this.eo_blue = 100;
        this.eo_random = false;
        this.eo_count = 5;
        this.eo_size = 10;
        this.eo_growth = 5;
        this.eo_objects_pos_x = new int[this.eo_count];
        this.eo_objects_pos_y = new int[this.eo_count];
        this.eo_objects_expand = new int[this.eo_count];
        this.eo_objects_fade = new int[this.eo_count];
        this.eo_objects_col = new Color[this.eo_count];
        this.options = null;
    }
    
    public ExpandingObjects(final int[] _size, final int _speed) {
        super("Expanding Objects;Open Circle;0;0;255;False;5;5;20", _size, _speed);
        this.eo_shape = "Open Circle";
        this.eo_red = 255;
        this.eo_green = 0;
        this.eo_blue = 100;
        this.eo_random = false;
        this.eo_count = 5;
        this.eo_size = 10;
        this.eo_growth = 5;
        this.eo_objects_pos_x = new int[this.eo_count];
        this.eo_objects_pos_y = new int[this.eo_count];
        this.eo_objects_expand = new int[this.eo_count];
        this.eo_objects_fade = new int[this.eo_count];
        this.eo_objects_col = new Color[this.eo_count];
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new ExpandingObjectsOptions(this)).setVisible(true);
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
        final Random rnd = new Random();
        if (this.parameter_changed) {
            this.eo_shape = this.parameter_array[1];
            this.eo_red = Integer.parseInt(this.parameter_array[2]);
            this.eo_green = Integer.parseInt(this.parameter_array[3]);
            this.eo_blue = Integer.parseInt(this.parameter_array[4]);
            this.eo_random = Boolean.parseBoolean(this.parameter_array[5]);
            this.eo_count = Integer.parseInt(this.parameter_array[6]);
            this.eo_size = Integer.parseInt(this.parameter_array[7]);
            this.eo_growth = Integer.parseInt(this.parameter_array[8]);
            this.eo_objects_pos_x = new int[this.eo_count];
            this.eo_objects_pos_y = new int[this.eo_count];
            this.eo_objects_expand = new int[this.eo_count];
            this.eo_objects_fade = new int[this.eo_count];
            this.eo_objects_col = new Color[this.eo_count];
            for (int i = 0; i < this.eo_count; ++i) {
                this.eo_objects_pos_x[i] = rnd.nextInt(this.size[0]);
                this.eo_objects_pos_y[i] = rnd.nextInt(this.size[1]);
                this.eo_objects_expand[i] = rnd.nextInt(this.eo_growth);
                this.eo_objects_fade[i] = 0;
                if (this.eo_random) {
                    this.eo_objects_col[i] = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                }
                else {
                    this.eo_objects_col[i] = new Color(this.eo_red, this.eo_green, this.eo_blue);
                }
            }
            this.parameter_changed = false;
            this.options = null;
        }
        final BufferedImage tempImage = new BufferedImage(this.size[0], this.size[1], 2);
        final Graphics2D gr = tempImage.createGraphics();
        for (int j = 0; j < this.eo_count; ++j) {
            final int r = (int)(this.eo_objects_col[j].getRed() * (1.0f - this.eo_objects_fade[j] / (float)this.eo_growth));
            final int g = (int)(this.eo_objects_col[j].getGreen() * (1.0f - this.eo_objects_fade[j] / (float)this.eo_growth));
            final int b = (int)(this.eo_objects_col[j].getBlue() * (1.0f - this.eo_objects_fade[j] / (float)this.eo_growth));
            final Color temp_color = new Color(r, g, b);
            final int diameter = this.eo_size + this.eo_objects_expand[j];
            if (this.eo_objects_expand[j] < this.eo_growth) {
                gr.setColor(temp_color);
                final int offset = diameter / 2;
                if (this.eo_shape.equals("Open Circle")) {
                    gr.drawOval(this.eo_objects_pos_x[j] - offset, this.eo_objects_pos_y[j] - offset, diameter, diameter);
                }
                if (this.eo_shape.equals("Filled Circle")) {
                    gr.fillOval(this.eo_objects_pos_x[j] - offset, this.eo_objects_pos_y[j] - offset, diameter, diameter);
                }
                if (this.eo_shape.equals("Open Rect")) {
                    gr.drawRect(this.eo_objects_pos_x[j] - offset, this.eo_objects_pos_y[j] - offset, diameter, diameter);
                }
                if (this.eo_shape.equals("Filled Rect")) {
                    gr.fillRect(this.eo_objects_pos_x[j] - offset, this.eo_objects_pos_y[j] - offset, diameter, diameter);
                }
                if (this.eo_shape.equals("Smiley")) {
                    gr.drawOval(this.eo_objects_pos_x[j] - offset, this.eo_objects_pos_y[j] - offset, diameter, diameter);
                    gr.drawOval(this.eo_objects_pos_x[j] - offset + diameter / 4 - diameter / 8, this.eo_objects_pos_y[j] - offset + diameter / 3, diameter / 4, diameter / 4);
                    gr.drawOval(this.eo_objects_pos_x[j] - offset + (int)(diameter * 0.75f) - diameter / 8, this.eo_objects_pos_y[j] - offset + diameter / 3, diameter / 4, diameter / 4);
                    gr.drawOval(this.eo_objects_pos_x[j] - offset + diameter / 4, this.eo_objects_pos_y[j] - offset + diameter / 3, diameter / 8, diameter / 8);
                    gr.drawOval(this.eo_objects_pos_x[j] - offset + (int)(diameter * 0.75f), this.eo_objects_pos_y[j] - offset + diameter / 3, diameter / 8, diameter / 8);
                    gr.drawArc(this.eo_objects_pos_x[j] - offset + diameter / 8, this.eo_objects_pos_y[j] - offset + diameter / 8, (int)(diameter * 0.8f), (int)(diameter * 0.8f), -20, -140);
                }
            }
            this.eo_objects_expand[j] += 2;
            this.eo_objects_fade[j] += 2;
            if (this.eo_objects_fade[j] >= this.eo_growth) {
                this.eo_objects_fade[j] = this.eo_growth;
            }
            if (this.eo_objects_expand[j] >= this.eo_growth && rnd.nextInt(100) < 10) {
                this.eo_objects_pos_x[j] = rnd.nextInt(this.size[0]);
                this.eo_objects_pos_y[j] = rnd.nextInt(this.size[1]);
                this.eo_objects_expand[j] = 0;
                this.eo_objects_fade[j] = 0;
            }
        }
        gr.dispose();
        for (int x = 0; x < this.size[0]; ++x) {
            for (int y = 0; y < this.size[1]; ++y) {
                final Color temp_color = new Color(tempImage.getRGB(x, y));
                image[y * this.size[0] + x] = temp_color;
            }
        }
    }
}
