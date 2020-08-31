// 
// Decompiled by Procyon v0.5.36
// 

package Options;

import Output.BaudRate;
import Output.ColorOrder;
import Mapper.MappingOrder;
import Mapper.MappingType;
import Output.OutputType;

public class Options
{
    public static final int[] DEFAULT_BOARD_SIZE;
    public static final int[] DEFAULT_NUMBER_OF_BOARDS;
    public static final OutputType DEFAULT_OUTPUT_TYPE;
    public static final MappingType DEFAULT_MAPPING_TYPE;
    public static final MappingOrder DEFAULT_BOARD_ORDER;
    public static final MappingOrder DEFAULT_PIXEL_ORDER;
    public static final ColorOrder DEFAULT_COLOR_ORDER;
    public static final BaudRate DEFAULT_BAUD_RATE;
    private int[] matrix_size;
    private int[] board_size;
    private int[] number_of_boards;
    private OutputType output_type;
    private MappingType mapping_type;
    private MappingOrder board_order;
    private MappingOrder pixel_order;
    private ColorOrder color_order;
    private BaudRate baud_rate;
    
    public Options() {
        this.matrix_size = new int[2];
        (this.board_size = new int[2])[0] = Options.DEFAULT_BOARD_SIZE[0];
        this.board_size[1] = Options.DEFAULT_BOARD_SIZE[1];
        (this.number_of_boards = new int[2])[0] = Options.DEFAULT_NUMBER_OF_BOARDS[0];
        this.number_of_boards[1] = Options.DEFAULT_NUMBER_OF_BOARDS[1];
        this.output_type = Options.DEFAULT_OUTPUT_TYPE;
        this.mapping_type = Options.DEFAULT_MAPPING_TYPE;
        this.board_order = Options.DEFAULT_BOARD_ORDER;
        this.pixel_order = Options.DEFAULT_PIXEL_ORDER;
        this.color_order = Options.DEFAULT_COLOR_ORDER;
        this.baud_rate = Options.DEFAULT_BAUD_RATE;
    }
    
    public void setMappingType(final MappingType mapping_type) {
        this.mapping_type = mapping_type;
    }
    
    public MappingType getMappingType() {
        return this.mapping_type;
    }
    
    public void setOutputType(final OutputType output_type) {
        this.output_type = output_type;
    }
    
    public OutputType getOutputType() {
        return this.output_type;
    }
    
    public void setMatrixSize(final int[] matrix_size) {
        if (matrix_size[0] > 0 && matrix_size[1] > 0) {
            this.matrix_size[0] = matrix_size[0];
            this.matrix_size[1] = matrix_size[1];
            this.number_of_boards[0] = matrix_size[0] / this.board_size[0];
            this.number_of_boards[1] = matrix_size[1] / this.board_size[1];
        }
        else {
            System.out.println("Options: Wrong matrix size.");
        }
    }
    
    public int[] getMatrixSize() {
        return this.matrix_size;
    }
    
    public void setBoardOrder(final MappingOrder board_order) {
        this.board_order = board_order;
    }
    
    public MappingOrder getBoardOrder() {
        return this.board_order;
    }
    
    public void setPixelOrder(final MappingOrder pixel_order) {
        this.pixel_order = pixel_order;
    }
    
    public MappingOrder getPixelOrder() {
        return this.pixel_order;
    }
    
    public void setColorOrder(final ColorOrder color_order) {
        this.color_order = color_order;
    }
    
    public ColorOrder getColorOrder() {
        return this.color_order;
    }
    
    public void setBaudRate(final BaudRate baud_rate) {
        this.baud_rate = baud_rate;
    }
    
    public BaudRate getBaudRate() {
        return this.baud_rate;
    }
    
    public void setBoardSize(final int[] board_size) {
        if (board_size[0] > 0 && board_size[1] > 0) {
            this.board_size[0] = board_size[0];
            this.board_size[1] = board_size[1];
        }
        else {
            System.out.println("Options: Wrong board size.");
        }
    }
    
    public int[] getBoardSize() {
        return this.board_size;
    }
    
    public void setNumberOfBoards(final int[] number_of_boards) {
        if (number_of_boards[0] > 0 && number_of_boards[1] > 0) {
            this.number_of_boards[0] = number_of_boards[0];
            this.number_of_boards[1] = number_of_boards[1];
        }
        else {
            System.out.println("Options: Wrong number_of_boards size.");
        }
    }
    
    public int[] getNumberOfBoards() {
        return this.number_of_boards;
    }
    
    static {
        DEFAULT_BOARD_SIZE = new int[] { 16, 8 };
        DEFAULT_NUMBER_OF_BOARDS = new int[] { 2, 2 };
        DEFAULT_OUTPUT_TYPE = OutputType.NO_DATA_TRANSMISSION;
        DEFAULT_MAPPING_TYPE = MappingType.SINGLE_PIXELS;
        DEFAULT_BOARD_ORDER = MappingOrder.HS_TL;
        DEFAULT_PIXEL_ORDER = MappingOrder.HS_TL;
        DEFAULT_COLOR_ORDER = ColorOrder.RGB;
        DEFAULT_BAUD_RATE = BaudRate.B_1000000;
    }
}
