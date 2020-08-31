// 
// Decompiled by Procyon v0.5.36
// 

package Draw;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
    Color[] image;
    int[] frame_size;
    int space;
    boolean block;
    
    public DrawPanel() {
        this.block = true;
    }
    
    public void setImage(final Color[] image) {
        this.image = image;
    }
    
    public void setFrameSize(final int[] frame_size) {
        this.frame_size = frame_size;
    }
    
    public void setSpace(final int space) {
        this.space = space;
    }
    
    public void free() {
        this.block = false;
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (!this.block) {
            final Graphics2D g2d = (Graphics2D)g;
            final int[] diameter = new int[2];
            final int width = this.getWidth();
            final int eff_width = width - (this.frame_size[0] + 1) * this.space;
            diameter[0] = eff_width / this.frame_size[0];
            final int height = this.getHeight();
            final int eff_height = height - (this.frame_size[1] + 1) * this.space;
            diameter[1] = eff_height / this.frame_size[1];
            final int offset_x = (width - (this.frame_size[0] + 1) * this.space - this.frame_size[0] * diameter[0]) / 2;
            final int offset_y = (height - (this.frame_size[1] + 1) * this.space - this.frame_size[1] * diameter[1]) / 2;
            for (int x = 0; x < this.frame_size[0]; ++x) {
                for (int y = 0; y < this.frame_size[1]; ++y) {
                    g2d.setColor(this.image[y * this.frame_size[0] + x]);
                    final double px_start = x * (diameter[0] + this.space) + this.space;
                    final double py_start = y * (diameter[1] + this.space) + this.space;
                    final double px_end = px_start + diameter[0];
                    final double py_end = py_start + diameter[1];
                    final int px_start_i = (int)Math.round(px_start);
                    final int py_start_i = (int)Math.round(py_start);
                    final int px_end_i = (int)Math.round(px_end);
                    final int py_end_i = (int)Math.round(py_end);
                    final int px = px_start_i;
                    final int py = py_start_i;
                    final int dx = px_end_i - px_start_i;
                    final int dy = py_end_i - py_start_i;
                    g2d.fillRect(px + offset_x, py + offset_y, dx, dy);
                }
            }
        }
    }
}
