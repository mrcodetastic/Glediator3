// 
// Decompiled by Procyon v0.5.36
// 

package Patch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import Main.GlediatorModel;
import javax.swing.JPanel;

public class DrawPatchPanel extends JPanel
{
    int[] pos;
    int[][] unis;
    int[][][] map;
    int rect_x;
    int rect_y;
    int space;
    int pix_off_x;
    int pix_off_y;
    int[] pix_geom;
    GlediatorModel glediator_model;
    
    public DrawPatchPanel() {
        this.pix_geom = new int[] { 0, 0, 0, 0, 0 };
    }
    
    public void setGlediatorModel(final GlediatorModel glediator_model) {
        this.glediator_model = glediator_model;
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D)g;
        final int[] frame_size = this.glediator_model.getFrameSize();
        this.unis = new int[this.glediator_model.patcher.get_num_unis()][8];
        this.map = new int[this.glediator_model.getFrameSize()[0]][this.glediator_model.getFrameSize()[1]][4];
        this.pos = new int[6];
        this.glediator_model.patcher.get_para(this.unis, this.map);
        this.glediator_model.patcher.get_pos(this.pos);
        this.pix_geom = this.glediator_model.patcher.get_pix_geom();
        this.rect_x = this.pix_geom[0];
        this.rect_y = this.pix_geom[1];
        this.space = this.pix_geom[2];
        this.pix_off_x = this.pix_geom[3];
        this.pix_off_y = this.pix_geom[4];
        Font fo = new Font("SansSerif", 1, 12);
        g2d.setFont(fo);
        g2d.setColor(new Color(220, 220, 220));
        for (int i = this.pos[0]; i < frame_size[0]; ++i) {
            g2d.drawString(i + 1 + "", (this.rect_x + this.space) * (i - this.pos[0]) + this.pix_off_x, 20);
        }
        for (int j = this.pos[1]; j < frame_size[1]; ++j) {
            g2d.drawString(j + 1 + "", 10, (this.rect_y + this.space) * (j - this.pos[1]) + this.pix_off_y);
        }
        for (int i = this.pos[0]; i < frame_size[0]; ++i) {
            for (int k = this.pos[1]; k < frame_size[1]; ++k) {
                if (i >= this.pos[2] && k >= this.pos[3] && i <= this.pos[4] && k <= this.pos[5]) {
                    g2d.setColor(new Color(0, 100, 0));
                }
                else {
                    g2d.setColor(new Color(80, 80, 80));
                }
                g2d.fillRect((this.rect_x + this.space) * (i - this.pos[0]) + this.pix_off_x - this.rect_x / 2, (this.rect_y + this.space) * (k - this.pos[1]) + this.pix_off_y - this.rect_y / 2, this.rect_x, this.rect_y);
            }
        }
        g2d.setColor(new Color(220, 220, 220));
        fo = new Font("SansSerif", 0, 10);
        g2d.setFont(fo);
        for (int i = this.pos[0]; i < frame_size[0]; ++i) {
            for (int k = this.pos[1]; k < frame_size[1]; ++k) {
                String temp = "";
                for (int l = 0; l < 3; ++l) {
                    temp = temp + this.unis[this.map[i][k][0]][l] + ".";
                }
                temp += this.unis[this.map[i][k][0]][3];
                int loc_x = (this.rect_x + this.space) * (i - this.pos[0]) + this.pix_off_x - this.rect_x / 2 + 5;
                int loc_y = (this.rect_y + this.space) * (k - this.pos[1]) + this.pix_off_y - this.rect_y / 2 + 15;
                g2d.drawString(temp, loc_x, loc_y);
                temp = "";
                for (int l = 4; l < 6; ++l) {
                    temp = temp + this.unis[this.map[i][k][0]][l] + "/";
                }
                temp += this.unis[this.map[i][k][0]][6];
                loc_x = (this.rect_x + this.space) * (i - this.pos[0]) + this.pix_off_x - this.rect_x / 2 + 5;
                loc_y = (this.rect_y + this.space) * (k - this.pos[1]) + this.pix_off_y - this.rect_y / 2 + 30;
                g2d.drawString(temp, loc_x, loc_y);
                temp = "";
                temp = temp + "R:" + this.map[i][k][1] + "/";
                temp = temp + "G:" + this.map[i][k][2] + "/";
                temp = temp + "B:" + this.map[i][k][3];
                loc_x = (this.rect_x + this.space) * (i - this.pos[0]) + this.pix_off_x - this.rect_x / 2 + 5;
                loc_y = (this.rect_y + this.space) * (k - this.pos[1]) + this.pix_off_y - this.rect_y / 2 + 45;
                g2d.drawString(temp, loc_x, loc_y);
            }
        }
    }
}
