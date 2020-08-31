// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;

public class Grid extends SuperGenerator
{
    int gr_pos_x;
    int gr_pos_y;
    int gr_step_x;
    int gr_step_y;
    int gr_size_x;
    int gr_size_y;
    int gr_angle;
    int gr_rot_speed;
    int gr_zoom;
    int gr_min_zoom;
    int gr_max_zoom;
    int gr_zoom_step;
    int gr_zoom_dir;
    int gr_red;
    int gr_green;
    int gr_blue;
    int gr_stroke;
    boolean gr_rotate;
    boolean gr_pulse;
    boolean gr_random;
    String gr_shape;
    GridOptions options;
    
    public Grid(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.gr_pos_x = 0;
        this.gr_pos_y = 0;
        this.gr_step_x = 10;
        this.gr_step_y = 14;
        this.gr_size_x = 6;
        this.gr_size_y = 12;
        this.gr_angle = 45;
        this.gr_rot_speed = 3;
        this.gr_zoom = 100;
        this.gr_min_zoom = 30;
        this.gr_max_zoom = 100;
        this.gr_zoom_step = 4;
        this.gr_zoom_dir = 1;
        this.gr_red = 255;
        this.gr_green = 0;
        this.gr_blue = 120;
        this.gr_stroke = 1;
        this.gr_rotate = true;
        this.gr_pulse = true;
        this.gr_random = true;
        this.gr_shape = "Filled Circle";
        this.options = null;
    }
    
    public Grid(final int[] _size, final int _speed) {
        super("Grid;12;12;6;6;0;4;80;170;3;2;255;0;128;true;true;true;Open Oval", _size, _speed);
        this.gr_pos_x = 0;
        this.gr_pos_y = 0;
        this.gr_step_x = 10;
        this.gr_step_y = 14;
        this.gr_size_x = 6;
        this.gr_size_y = 12;
        this.gr_angle = 45;
        this.gr_rot_speed = 3;
        this.gr_zoom = 100;
        this.gr_min_zoom = 30;
        this.gr_max_zoom = 100;
        this.gr_zoom_step = 4;
        this.gr_zoom_dir = 1;
        this.gr_red = 255;
        this.gr_green = 0;
        this.gr_blue = 120;
        this.gr_stroke = 1;
        this.gr_rotate = true;
        this.gr_pulse = true;
        this.gr_random = true;
        this.gr_shape = "Filled Circle";
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new GridOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        final Random rnd = new Random();
        if (this.parameter_changed) {
            this.gr_step_x = Integer.parseInt(this.parameter_array[1]);
            this.gr_step_y = Integer.parseInt(this.parameter_array[2]);
            this.gr_size_x = Integer.parseInt(this.parameter_array[3]);
            this.gr_size_y = Integer.parseInt(this.parameter_array[4]);
            this.gr_angle = Integer.parseInt(this.parameter_array[5]);
            this.gr_rot_speed = Integer.parseInt(this.parameter_array[6]);
            this.gr_min_zoom = Integer.parseInt(this.parameter_array[7]);
            this.gr_max_zoom = Integer.parseInt(this.parameter_array[8]);
            this.gr_zoom_step = Integer.parseInt(this.parameter_array[9]);
            this.gr_stroke = Integer.parseInt(this.parameter_array[10]);
            this.gr_red = Integer.parseInt(this.parameter_array[11]);
            this.gr_green = Integer.parseInt(this.parameter_array[12]);
            this.gr_blue = Integer.parseInt(this.parameter_array[13]);
            this.gr_rotate = Boolean.parseBoolean(this.parameter_array[14]);
            this.gr_pulse = Boolean.parseBoolean(this.parameter_array[15]);
            this.gr_random = Boolean.parseBoolean(this.parameter_array[16]);
            this.gr_shape = this.parameter_array[17];
            this.parameter_changed = false;
            this.options = null;
        }
        final BufferedImage tempImage = new BufferedImage(this.size[0], this.size[1], 2);
        final Graphics2D gr = tempImage.createGraphics();
        if (this.gr_rotate) {
            this.gr_angle = this.speed_counter % 360;
        }
        gr.rotate(this.gr_rot_speed * (float)this.gr_angle / 180.0f * 3.1415, this.size[0] / 2, this.size[1] / 2);
        this.gr_pos_x = 0 - this.size[0];
        final float zoom = this.gr_zoom / 100.0f;
        gr.setStroke(new BasicStroke((float)this.gr_stroke));
        if (this.gr_random && rnd.nextInt(100) < 3) {
            this.gr_red = rnd.nextInt(256);
            this.gr_green = rnd.nextInt(256);
            this.gr_blue = rnd.nextInt(256);
        }
        gr.setColor(new Color(this.gr_red, this.gr_green, this.gr_blue));
        while (this.gr_pos_x <= 2 * this.size[0]) {
            this.gr_pos_y = 0 - this.size[1];
            while (this.gr_pos_y <= 2 * this.size[1]) {
                if (this.gr_shape.equals("Open Oval")) {
                    gr.drawOval(this.gr_pos_x - (int)(this.gr_size_x * zoom) / 2, this.gr_pos_y - (int)(this.gr_size_x * zoom) / 2, (int)(this.gr_size_x * zoom), (int)(this.gr_size_y * zoom));
                }
                if (this.gr_shape.equals("Filled Oval")) {
                    gr.fillOval(this.gr_pos_x - (int)(this.gr_size_x * zoom) / 2, this.gr_pos_y - (int)(this.gr_size_x * zoom) / 2, (int)(this.gr_size_x * zoom), (int)(this.gr_size_y * zoom));
                }
                if (this.gr_shape.equals("Open Rect")) {
                    gr.drawRect(this.gr_pos_x - (int)(this.gr_size_x * zoom) / 2, this.gr_pos_y - (int)(this.gr_size_x * zoom) / 2, (int)(this.gr_size_x * zoom), (int)(this.gr_size_y * zoom));
                }
                if (this.gr_shape.equals("Filled Rect")) {
                    gr.fillRect(this.gr_pos_x - (int)(this.gr_size_x * zoom) / 2, this.gr_pos_y - (int)(this.gr_size_x * zoom) / 2, (int)(this.gr_size_x * zoom), (int)(this.gr_size_y * zoom));
                }
                this.gr_pos_y += this.gr_step_y;
            }
            this.gr_pos_x += this.gr_step_x;
        }
        if (this.gr_pulse) {
            if (this.gr_zoom_dir == 1) {
                this.gr_zoom += this.gr_zoom_step;
            }
            else {
                this.gr_zoom -= this.gr_zoom_step;
            }
            if (this.gr_zoom >= this.gr_max_zoom) {
                this.gr_zoom = this.gr_max_zoom;
                this.gr_zoom_dir = 0;
            }
            if (this.gr_zoom <= this.gr_min_zoom) {
                this.gr_zoom = this.gr_min_zoom;
                this.gr_zoom_dir = 1;
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
    
    @Override
    public void closeConfigWindow() {
        if (this.options != null) {
            this.options.setVisible(false);
            this.options = null;
        }
    }
}
