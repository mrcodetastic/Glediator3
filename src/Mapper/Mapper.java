// 
// Decompiled by Procyon v0.5.36
// http://www.javadecompilers.com/result?currentfile=
// 

package Mapper;

import Options.Options;
import java.awt.Color;

public class Mapper
{
    int[] output_buffer_lut;
    Color[] temp_image;
    
    public Mapper() {
        this.output_buffer_lut = new int[512];
    }
    
    public void setMapping(final Options options) {
        final int sx = options.getMatrixSize()[0];
        final int sy = options.getMatrixSize()[1];
        final int size = sx * sy;
        this.output_buffer_lut = new int[size];
        this.temp_image = new Color[size];
        final MappingType mapping_type = options.getMappingType();
        switch (mapping_type) {
            case SINGLE_PIXELS: {
                final MappingOrder pixel_order = options.getPixelOrder();
                this.do_pixel_mapping(this.output_buffer_lut, pixel_order, sx, sy);
                break;
            }
            case BOARDS_OF_PIXEL: {
                this.do_board_and_pixel_mapping(options.getMatrixSize(), options.getBoardOrder(), options.getPixelOrder(), options.getBoardSize()[0], options.getBoardSize()[1], options.getNumberOfBoards()[0], options.getNumberOfBoards()[1]);
                break;
            }
        }
    }
    
    public void doMapping(final Color[] image_in, final Color[] image_out) {
        for (int frame_size = image_in.length, i = 0; i < frame_size; ++i) {
            image_out[i] = image_in[this.output_buffer_lut[i]];
        }
    }
    
    private void do_board_and_pixel_mapping(final int[] _frame_size, final MappingOrder _board_order, final MappingOrder _pixel_order, final int _pixels_per_board_x, final int _pixels_per_board_y, final int _num_pieces_x, final int _num_pieces_y) {
        final int[] board_pixel_lut = new int[_pixels_per_board_x * _pixels_per_board_y];
        final int[] matrix_board_lut = new int[_num_pieces_x * _num_pieces_y];
        final int[] actual_board = new int[_pixels_per_board_x * _pixels_per_board_y];
        for (int x = 0; x < _pixels_per_board_x; ++x) {
            for (int y = 0; y < _pixels_per_board_y; ++y) {
                board_pixel_lut[y * _pixels_per_board_x + x] = y * _pixels_per_board_x + x;
            }
        }
        for (int x = 0; x < _num_pieces_x; ++x) {
            for (int y = 0; y < _num_pieces_y; ++y) {
                matrix_board_lut[y * _num_pieces_x + x] = y * _num_pieces_x + x;
            }
        }
        this.do_pixel_mapping(board_pixel_lut, _pixel_order, _pixels_per_board_x, _pixels_per_board_y);
        this.do_pixel_mapping(matrix_board_lut, _board_order, _num_pieces_x, _num_pieces_y);
        for (int i = 0; i < _num_pieces_x * _num_pieces_y; ++i) {
            final int actual_board_index = matrix_board_lut[i];
            final int actual_y_pos_of_board = actual_board_index / _num_pieces_x;
            final int actual_x_pos_of_board = actual_board_index % _num_pieces_x;
            final int actual_offset_x = actual_x_pos_of_board * _pixels_per_board_x;
            final int actual_offset_y = actual_y_pos_of_board * _pixels_per_board_y;
            for (int x2 = 0; x2 < _pixels_per_board_x; ++x2) {
                for (int y2 = 0; y2 < _pixels_per_board_y; ++y2) {
                    actual_board[y2 * _pixels_per_board_x + x2] = (actual_offset_y + y2) * _frame_size[0] + actual_offset_x + x2;
                }
            }
            for (int j = 0; j < _pixels_per_board_x * _pixels_per_board_y; ++j) {
                this.output_buffer_lut[i * _pixels_per_board_x * _pixels_per_board_y + j] = actual_board[board_pixel_lut[j]];
            }
        }
    }
    
    private void do_pixel_mapping(final int[] LUT, final MappingOrder mapping_order, final int size_x, final int size_y) {
        byte dir_x = 0;
        byte dir_y = 0;
        int x = 0;
        int y = 0;
        Order order = Order.SNAKE;
        Orientation orientation = Orientation.HORIZONTAL;
        switch (mapping_order) {
            case HS_TL: {
                dir_x = 1;
                dir_y = 1;
                x = 0;
                y = 0;
                order = Order.SNAKE;
                orientation = Orientation.HORIZONTAL;
                break;
            }
            case HS_TR: {
                dir_x = 0;
                dir_y = 1;
                x = size_x - 1;
                y = 0;
                order = Order.SNAKE;
                orientation = Orientation.HORIZONTAL;
                break;
            }
            case HS_BL: {
                dir_x = 1;
                dir_y = 0;
                x = 0;
                y = size_y - 1;
                order = Order.SNAKE;
                orientation = Orientation.HORIZONTAL;
                break;
            }
            case HS_BR: {
                dir_x = 0;
                dir_y = 0;
                x = size_x - 1;
                y = size_y - 1;
                order = Order.SNAKE;
                orientation = Orientation.HORIZONTAL;
                break;
            }
            case VS_TL: {
                dir_x = 1;
                dir_y = 1;
                x = 0;
                y = 0;
                order = Order.SNAKE;
                orientation = Orientation.VERTICAL;
                break;
            }
            case VS_TR: {
                dir_x = 0;
                dir_y = 1;
                x = size_x - 1;
                y = 0;
                order = Order.SNAKE;
                orientation = Orientation.VERTICAL;
                break;
            }
            case VS_BL: {
                dir_x = 1;
                dir_y = 0;
                x = 0;
                y = size_y - 1;
                order = Order.SNAKE;
                orientation = Orientation.VERTICAL;
                break;
            }
            case VS_BR: {
                dir_x = 0;
                dir_y = 0;
                x = size_x - 1;
                y = size_y - 1;
                order = Order.SNAKE;
                orientation = Orientation.VERTICAL;
                break;
            }
            case HL_TL: {
                dir_x = 1;
                dir_y = 1;
                x = 0;
                y = 0;
                order = Order.LINE_WISE;
                orientation = Orientation.HORIZONTAL;
                break;
            }
            case HL_TR: {
                dir_x = 0;
                dir_y = 1;
                x = size_x - 1;
                y = 0;
                order = Order.LINE_WISE;
                orientation = Orientation.HORIZONTAL;
                break;
            }
            case HL_BL: {
                dir_x = 1;
                dir_y = 0;
                x = 0;
                y = size_y - 1;
                order = Order.LINE_WISE;
                orientation = Orientation.HORIZONTAL;
                break;
            }
            case HL_BR: {
                dir_x = 0;
                dir_y = 0;
                x = size_x - 1;
                y = size_y - 1;
                order = Order.LINE_WISE;
                orientation = Orientation.HORIZONTAL;
                break;
            }
            case VL_TL: {
                dir_x = 1;
                dir_y = 1;
                x = 0;
                y = 0;
                order = Order.LINE_WISE;
                orientation = Orientation.VERTICAL;
                break;
            }
            case VL_TR: {
                dir_x = 0;
                dir_y = 1;
                x = size_x - 1;
                y = 0;
                order = Order.LINE_WISE;
                orientation = Orientation.VERTICAL;
                break;
            }
            case VL_BL: {
                dir_x = 1;
                dir_y = 0;
                x = 0;
                y = size_y - 1;
                order = Order.LINE_WISE;
                orientation = Orientation.VERTICAL;
                break;
            }
            case VL_BR: {
                dir_x = 0;
                dir_y = 0;
                x = size_x - 1;
                y = size_y - 1;
                order = Order.LINE_WISE;
                orientation = Orientation.VERTICAL;
                break;
            }
        }
        for (int i = 0; i < size_x * size_y; ++i) {
            LUT[i] = y * size_x + x;
            switch (orientation) {
                case HORIZONTAL: {
                    if (dir_x == 1) {
                        ++x;
                        break;
                    }
                    --x;
                    break;
                }
                case VERTICAL: {
                    if (dir_y == 1) {
                        ++y;
                        break;
                    }
                    --y;
                    break;
                }
            }
            switch (order) {
                case SNAKE: {
                    switch (orientation) {
                        case HORIZONTAL: {
                            if (x == size_x) {
                                dir_x = 0;
                                x = size_x - 1;
                                if (dir_y == 1) {
                                    ++y;
                                }
                                else {
                                    --y;
                                }
                            }
                            if (x != -1) {
                                break;
                            }
                            dir_x = 1;
                            x = 0;
                            if (dir_y == 1) {
                                ++y;
                                break;
                            }
                            --y;
                            break;
                        }
                        case VERTICAL: {
                            if (y == size_y) {
                                dir_y = 0;
                                y = size_y - 1;
                                if (dir_x == 1) {
                                    ++x;
                                }
                                else {
                                    --x;
                                }
                            }
                            if (y != -1) {
                                break;
                            }
                            dir_y = 1;
                            y = 0;
                            if (dir_x == 1) {
                                ++x;
                                break;
                            }
                            --x;
                            break;
                        }
                    }
                    break;
                }
                case LINE_WISE: {
                    switch (orientation) {
                        case HORIZONTAL: {
                            if (x == size_x) {
                                x = 0;
                                if (dir_y == 1) {
                                    ++y;
                                }
                                else {
                                    --y;
                                }
                            }
                            if (x != -1) {
                                continue;
                            }
                            x = size_x - 1;
                            if (dir_y == 1) {
                                ++y;
                                continue;
                            }
                            --y;
                            continue;
                        }
                        case VERTICAL: {
                            if (y == size_y) {
                                y = 0;
                                if (dir_x == 1) {
                                    ++x;
                                }
                                else {
                                    --x;
                                }
                            }
                            if (y != -1) {
                                continue;
                            }
                            y = size_y - 1;
                            if (dir_x == 1) {
                                ++x;
                                continue;
                            }
                            --x;
                            continue;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    enum Order
    {
        SNAKE, 
        LINE_WISE;
    }
    
    enum Orientation
    {
        HORIZONTAL, 
        VERTICAL;
    }
}
