// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class Objects3D extends SuperGenerator
{
    int o3d_size;
    int o3d_rot_speed;
    int o3d_projection_angle_xz;
    int o3d_projection_angle_yz;
    int o3d_vx;
    int o3d_vy;
    int o3d_vz;
    int o3d_offset_x;
    int o3d_offset_y;
    int o3d_offset_z;
    int o3d_red;
    int o3d_green;
    int o3d_blue;
    String o3d_shape;
    int o3d_num_corners;
    int o3d_angle;
    int[] o3d_coord_x;
    int[] o3d_coord_y;
    int[] o3d_coord_z;
    int[] o3d_coord_xx;
    int[] o3d_coord_yy;
    float vx_f;
    float vy_f;
    float vz_f;
    Color temp_color;
    Objects3DOptions options;
    
    public Objects3D(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.o3d_size = 12;
        this.o3d_rot_speed = 15;
        this.o3d_projection_angle_xz = 135;
        this.o3d_projection_angle_yz = 90;
        this.o3d_vx = 90;
        this.o3d_vy = 50;
        this.o3d_vz = 90;
        this.o3d_offset_x = 0;
        this.o3d_offset_y = 0;
        this.o3d_offset_z = 5;
        this.o3d_red = 255;
        this.o3d_green = 255;
        this.o3d_blue = 255;
        this.o3d_shape = "Pyramide-4";
        this.o3d_num_corners = 5;
        this.o3d_angle = 0;
        this.o3d_coord_x = new int[this.o3d_num_corners];
        this.o3d_coord_y = new int[this.o3d_num_corners];
        this.o3d_coord_z = new int[this.o3d_num_corners];
        this.o3d_coord_xx = new int[this.o3d_num_corners];
        this.o3d_coord_yy = new int[this.o3d_num_corners];
        this.vx_f = this.o3d_vx / 100.0f;
        this.vy_f = this.o3d_vy / 100.0f;
        this.vz_f = this.o3d_vz / 100.0f;
        this.temp_color = Color.BLACK;
        this.options = null;
    }
    
    public Objects3D(final int[] _size, final int _speed) {
        super("Objects3D;12;15;135;90;90;50;90;0;0;3;0;0;255;Pyramide-4", _size, _speed);
        this.o3d_size = 12;
        this.o3d_rot_speed = 15;
        this.o3d_projection_angle_xz = 135;
        this.o3d_projection_angle_yz = 90;
        this.o3d_vx = 90;
        this.o3d_vy = 50;
        this.o3d_vz = 90;
        this.o3d_offset_x = 0;
        this.o3d_offset_y = 0;
        this.o3d_offset_z = 5;
        this.o3d_red = 255;
        this.o3d_green = 255;
        this.o3d_blue = 255;
        this.o3d_shape = "Pyramide-4";
        this.o3d_num_corners = 5;
        this.o3d_angle = 0;
        this.o3d_coord_x = new int[this.o3d_num_corners];
        this.o3d_coord_y = new int[this.o3d_num_corners];
        this.o3d_coord_z = new int[this.o3d_num_corners];
        this.o3d_coord_xx = new int[this.o3d_num_corners];
        this.o3d_coord_yy = new int[this.o3d_num_corners];
        this.vx_f = this.o3d_vx / 100.0f;
        this.vy_f = this.o3d_vy / 100.0f;
        this.vz_f = this.o3d_vz / 100.0f;
        this.temp_color = Color.BLACK;
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new Objects3DOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.o3d_size = Integer.parseInt(this.parameter_array[1]);
            this.o3d_rot_speed = Integer.parseInt(this.parameter_array[2]);
            this.o3d_projection_angle_xz = Integer.parseInt(this.parameter_array[3]);
            this.o3d_projection_angle_yz = Integer.parseInt(this.parameter_array[4]);
            this.o3d_vx = Integer.parseInt(this.parameter_array[5]);
            this.o3d_vy = Integer.parseInt(this.parameter_array[6]);
            this.o3d_vz = Integer.parseInt(this.parameter_array[7]);
            this.o3d_offset_x = Integer.parseInt(this.parameter_array[8]);
            this.o3d_offset_y = Integer.parseInt(this.parameter_array[9]);
            this.o3d_offset_z = Integer.parseInt(this.parameter_array[10]);
            this.o3d_red = Integer.parseInt(this.parameter_array[11]);
            this.o3d_green = Integer.parseInt(this.parameter_array[12]);
            this.o3d_blue = Integer.parseInt(this.parameter_array[13]);
            this.o3d_shape = this.parameter_array[14];
            if (this.o3d_shape.equals("Cube")) {
                this.o3d_num_corners = 8;
            }
            if (this.o3d_shape.equals("Pyramide-3")) {
                this.o3d_num_corners = 4;
            }
            if (this.o3d_shape.equals("Pyramide-4")) {
                this.o3d_num_corners = 5;
            }
            this.o3d_coord_x = new int[this.o3d_num_corners];
            this.o3d_coord_y = new int[this.o3d_num_corners];
            this.o3d_coord_z = new int[this.o3d_num_corners];
            this.o3d_coord_xx = new int[this.o3d_num_corners];
            this.o3d_coord_yy = new int[this.o3d_num_corners];
            this.vx_f = this.o3d_vx / 100.0f;
            this.vy_f = this.o3d_vy / 100.0f;
            this.vz_f = this.o3d_vz / 100.0f;
            this.parameter_changed = false;
            this.options = null;
        }
        if (this.o3d_shape.equals("Cube")) {
            this.o3d_coord_x[0] = -1 * (this.o3d_size / 2);
            this.o3d_coord_y[0] = -1 * (this.o3d_size / 2);
            this.o3d_coord_z[0] = -1 * (this.o3d_size / 2);
            this.o3d_coord_x[1] = 1 * (this.o3d_size / 2);
            this.o3d_coord_y[1] = -1 * (this.o3d_size / 2);
            this.o3d_coord_z[1] = -1 * (this.o3d_size / 2);
            this.o3d_coord_x[2] = 1 * (this.o3d_size / 2);
            this.o3d_coord_y[2] = 1 * (this.o3d_size / 2);
            this.o3d_coord_z[2] = -1 * (this.o3d_size / 2);
            this.o3d_coord_x[3] = -1 * (this.o3d_size / 2);
            this.o3d_coord_y[3] = 1 * (this.o3d_size / 2);
            this.o3d_coord_z[3] = -1 * (this.o3d_size / 2);
            this.o3d_coord_x[4] = -1 * (this.o3d_size / 2);
            this.o3d_coord_y[4] = -1 * (this.o3d_size / 2);
            this.o3d_coord_z[4] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[5] = 1 * (this.o3d_size / 2);
            this.o3d_coord_y[5] = -1 * (this.o3d_size / 2);
            this.o3d_coord_z[5] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[6] = 1 * (this.o3d_size / 2);
            this.o3d_coord_y[6] = 1 * (this.o3d_size / 2);
            this.o3d_coord_z[6] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[7] = -1 * (this.o3d_size / 2);
            this.o3d_coord_y[7] = 1 * (this.o3d_size / 2);
            this.o3d_coord_z[7] = 1 * (this.o3d_size / 2);
        }
        if (this.o3d_shape.equals("Pyramide-3")) {
            this.o3d_coord_x[0] = -1 * (this.o3d_size / 2);
            this.o3d_coord_y[0] = 1 * (this.o3d_size / 4);
            this.o3d_coord_z[0] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[1] = 1 * (this.o3d_size / 2);
            this.o3d_coord_y[1] = 1 * (this.o3d_size / 4);
            this.o3d_coord_z[1] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[2] = 0 * (this.o3d_size / 1);
            this.o3d_coord_y[2] = -1 * (this.o3d_size / 2);
            this.o3d_coord_z[2] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[3] = 0;
            this.o3d_coord_y[3] = 0;
            this.o3d_coord_z[3] = -1 * (this.o3d_size / 2);
        }
        if (this.o3d_shape.equals("Pyramide-4")) {
            this.o3d_coord_x[0] = -1 * (this.o3d_size / 2);
            this.o3d_coord_y[0] = -1 * (this.o3d_size / 2);
            this.o3d_coord_z[0] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[1] = 1 * (this.o3d_size / 2);
            this.o3d_coord_y[1] = -1 * (this.o3d_size / 2);
            this.o3d_coord_z[1] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[2] = 1 * (this.o3d_size / 2);
            this.o3d_coord_y[2] = 1 * (this.o3d_size / 2);
            this.o3d_coord_z[2] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[3] = -1 * (this.o3d_size / 2);
            this.o3d_coord_y[3] = 1 * (this.o3d_size / 2);
            this.o3d_coord_z[3] = 1 * (this.o3d_size / 2);
            this.o3d_coord_x[4] = 0;
            this.o3d_coord_y[4] = 0;
            this.o3d_coord_z[4] = -1 * (this.o3d_size / 2);
        }
        this.o3d_angle += this.o3d_rot_speed;
        final float alpha = this.o3d_angle / 360.0f * 3.1415f;
        for (int i = 0; i < this.o3d_num_corners; ++i) {
            final int temp = this.o3d_coord_x[i];
            this.o3d_coord_x[i] = (int)((float)this.o3d_coord_x[i] * Math.cos(alpha) + (float)this.o3d_coord_y[i] * Math.sin(alpha));
            this.o3d_coord_y[i] = (int)((float)this.o3d_coord_y[i] * Math.cos(alpha) - (float)temp * Math.sin(alpha));
            this.o3d_coord_x[i] = this.o3d_coord_x[i] + this.size[0] / 2 + this.o3d_offset_x;
            this.o3d_coord_y[i] = this.o3d_coord_y[i] + this.size[1] / 2 + this.o3d_offset_y;
            this.o3d_coord_z[i] += this.o3d_offset_z;
        }
        for (int i = 0; i < this.o3d_num_corners; ++i) {
            this.o3d_coord_xx[i] = (int)(this.o3d_coord_x[i] * this.vx_f + Math.cos(this.o3d_projection_angle_xz / 360.0f * 3.1415f) * (float)this.o3d_coord_y[i] * this.vy_f);
            this.o3d_coord_yy[i] = (int)(this.o3d_coord_z[i] * this.vz_f + Math.sin(this.o3d_projection_angle_yz / 360.0f * 3.1415f) * (float)this.o3d_coord_y[i] * this.vy_f);
        }
        final BufferedImage tempImage = new BufferedImage(this.size[0], this.size[1], 2);
        final Graphics2D gr = tempImage.createGraphics();
        gr.setColor(new Color(this.o3d_red, this.o3d_green, this.o3d_blue));
        if (this.o3d_shape.equals("Cube")) {
            gr.drawLine(this.o3d_coord_xx[0], this.o3d_coord_yy[0], this.o3d_coord_xx[1], this.o3d_coord_yy[1]);
            gr.drawLine(this.o3d_coord_xx[1], this.o3d_coord_yy[1], this.o3d_coord_xx[2], this.o3d_coord_yy[2]);
            gr.drawLine(this.o3d_coord_xx[2], this.o3d_coord_yy[2], this.o3d_coord_xx[3], this.o3d_coord_yy[3]);
            gr.drawLine(this.o3d_coord_xx[3], this.o3d_coord_yy[3], this.o3d_coord_xx[0], this.o3d_coord_yy[0]);
            gr.drawLine(this.o3d_coord_xx[4], this.o3d_coord_yy[4], this.o3d_coord_xx[5], this.o3d_coord_yy[5]);
            gr.drawLine(this.o3d_coord_xx[5], this.o3d_coord_yy[5], this.o3d_coord_xx[6], this.o3d_coord_yy[6]);
            gr.drawLine(this.o3d_coord_xx[6], this.o3d_coord_yy[6], this.o3d_coord_xx[7], this.o3d_coord_yy[7]);
            gr.drawLine(this.o3d_coord_xx[7], this.o3d_coord_yy[7], this.o3d_coord_xx[4], this.o3d_coord_yy[4]);
            gr.drawLine(this.o3d_coord_xx[0], this.o3d_coord_yy[0], this.o3d_coord_xx[4], this.o3d_coord_yy[4]);
            gr.drawLine(this.o3d_coord_xx[1], this.o3d_coord_yy[1], this.o3d_coord_xx[5], this.o3d_coord_yy[5]);
            gr.drawLine(this.o3d_coord_xx[2], this.o3d_coord_yy[2], this.o3d_coord_xx[6], this.o3d_coord_yy[6]);
            gr.drawLine(this.o3d_coord_xx[3], this.o3d_coord_yy[3], this.o3d_coord_xx[7], this.o3d_coord_yy[7]);
        }
        if (this.o3d_shape.equals("Pyramide-3")) {
            gr.drawLine(this.o3d_coord_xx[0], this.o3d_coord_yy[0], this.o3d_coord_xx[1], this.o3d_coord_yy[1]);
            gr.drawLine(this.o3d_coord_xx[1], this.o3d_coord_yy[1], this.o3d_coord_xx[2], this.o3d_coord_yy[2]);
            gr.drawLine(this.o3d_coord_xx[2], this.o3d_coord_yy[2], this.o3d_coord_xx[0], this.o3d_coord_yy[0]);
            gr.drawLine(this.o3d_coord_xx[0], this.o3d_coord_yy[0], this.o3d_coord_xx[3], this.o3d_coord_yy[3]);
            gr.drawLine(this.o3d_coord_xx[1], this.o3d_coord_yy[1], this.o3d_coord_xx[3], this.o3d_coord_yy[3]);
            gr.drawLine(this.o3d_coord_xx[2], this.o3d_coord_yy[2], this.o3d_coord_xx[3], this.o3d_coord_yy[3]);
        }
        if (this.o3d_shape.equals("Pyramide-4")) {
            gr.drawLine(this.o3d_coord_xx[0], this.o3d_coord_yy[0], this.o3d_coord_xx[1], this.o3d_coord_yy[1]);
            gr.drawLine(this.o3d_coord_xx[1], this.o3d_coord_yy[1], this.o3d_coord_xx[2], this.o3d_coord_yy[2]);
            gr.drawLine(this.o3d_coord_xx[2], this.o3d_coord_yy[2], this.o3d_coord_xx[3], this.o3d_coord_yy[3]);
            gr.drawLine(this.o3d_coord_xx[3], this.o3d_coord_yy[3], this.o3d_coord_xx[0], this.o3d_coord_yy[0]);
            gr.drawLine(this.o3d_coord_xx[0], this.o3d_coord_yy[0], this.o3d_coord_xx[4], this.o3d_coord_yy[4]);
            gr.drawLine(this.o3d_coord_xx[1], this.o3d_coord_yy[1], this.o3d_coord_xx[4], this.o3d_coord_yy[4]);
            gr.drawLine(this.o3d_coord_xx[2], this.o3d_coord_yy[2], this.o3d_coord_xx[4], this.o3d_coord_yy[4]);
            gr.drawLine(this.o3d_coord_xx[3], this.o3d_coord_yy[3], this.o3d_coord_xx[4], this.o3d_coord_yy[4]);
        }
        gr.dispose();
        for (int x = 0; x < this.size[0]; ++x) {
            for (int y = 0; y < this.size[1]; ++y) {
                this.temp_color = new Color(tempImage.getRGB(x, y));
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
