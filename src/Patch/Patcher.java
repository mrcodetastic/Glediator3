// 
// Decompiled by Procyon v0.5.36
// 

package Patch;

import Main.GlediatorModel;

public class Patcher
{
    int size_x;
    int size_y;
    int[] pos;
    int[] pix_geom;
    int[][] unis;
    int[][][] map;
    GlediatorModel glediator_model;
    
    public Patcher(final GlediatorModel glediator_model) {
        this.pos = new int[] { 0, 0, 0, 0, 0, 0 };
        this.pix_geom = new int[] { 100, 50, 4, 80, 50 };
        this.unis = new int[][] { { 255, 255, 255, 255, 0, 0, 1, 512 } };
        this.glediator_model = glediator_model;
        final int[] size = { 32, 16 };
        this.set_size(size);
    }
    
    public void get_para(final int[][] _unis, final int[][][] _map) {
        for (int num_unis = this.unis.length, i = 0; i < num_unis; ++i) {
            System.arraycopy(this.unis[i], 0, _unis[i], 0, 8);
        }
        for (int i = 0; i < this.size_x; ++i) {
            for (int j = 0; j < this.size_y; ++j) {
                System.arraycopy(this.map[i][j], 0, _map[i][j], 0, 4);
            }
        }
    }
    
    public void set_para(final int[][] _unis, final int[][][] _map) {
        final int num_unis = _unis.length;
        this.unis = new int[num_unis][8];
        this.map = new int[this.size_x][this.size_y][4];
        for (int i = 0; i < num_unis; ++i) {
            System.arraycopy(_unis[i], 0, this.unis[i], 0, 8);
        }
        for (int i = 0; i < this.size_x; ++i) {
            for (int j = 0; j < this.size_y; ++j) {
                System.arraycopy(_map[i][j], 0, this.map[i][j], 0, 4);
            }
        }
    }
    
    public int get_num_unis() {
        return this.unis.length;
    }
    
    public void get_pos(final int[] _pos) {
        System.arraycopy(this.pos, 0, _pos, 0, 6);
    }
    
    public void set_pos(final int[] _pos) {
        System.arraycopy(_pos, 0, this.pos, 0, 6);
    }
    
    public int[] get_pix_geom() {
        final int[] result = new int[5];
        System.arraycopy(this.pix_geom, 0, result, 0, 5);
        return result;
    }
    
    public boolean check_para() {
        Boolean error = false;
        final int num_unis = this.unis.length;
        if (num_unis < 1) {
            error = true;
        }
        for (int i = 0; i < num_unis; ++i) {
            if (error) {
                break;
            }
            if (this.unis[i][0] > 255) {
                error = true;
                break;
            }
            if (this.unis[i][1] > 255) {
                error = true;
                break;
            }
            if (this.unis[i][2] > 255) {
                error = true;
                break;
            }
            if (this.unis[i][3] > 255) {
                error = true;
                break;
            }
            if (this.unis[i][4] > 255) {
                error = true;
                break;
            }
            if (this.unis[i][5] > 255) {
                error = true;
                break;
            }
            if (this.unis[i][6] > 255) {
                error = true;
                break;
            }
            if (this.unis[i][7] <= 0) {
                error = true;
                break;
            }
        }
        for (int x = 0; x < this.size_x && !error; ++x) {
            for (int y = 0; y < this.size_y; ++y) {
                if (this.map[x][y][0] >= num_unis) {
                    error = true;
                    break;
                }
                final int max_ch = this.unis[this.map[x][y][0]][7];
                if (this.map[x][y][1] >= max_ch) {
                    error = true;
                    break;
                }
                if (this.map[x][y][2] >= max_ch) {
                    error = true;
                    break;
                }
                if (this.map[x][y][3] >= max_ch) {
                    error = true;
                    break;
                }
            }
        }
        return error;
    }
    
    public void apply_para_to_output() {
        this.glediator_model.output.setArtnetParameters(this.unis, this.map);
        this.glediator_model.output.setTPM2netParameters(this.unis, this.map);
    }
    
    public final void set_size(final int[] _size) {
        final boolean size_changed = this.size_x != _size[0] || this.size_y != _size[1];
        if (size_changed) {
            this.size_x = _size[0];
            this.size_y = _size[1];
            for (int i = 0; i < 6; ++i) {
                this.pos[i] = 0;
            }
            this.map = new int[this.size_x][this.size_y][4];
            for (int i = 0; i < this.size_x; ++i) {
                for (int j = 0; j < this.size_y; ++j) {
                    for (int k = 0; k < 4; ++k) {
                        this.map[i][j][k] = 0;
                    }
                }
            }
        }
    }
    
    public int[] get_size() {
        final int[] result = { this.size_x, this.size_y };
        return result;
    }
}
