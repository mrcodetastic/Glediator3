// 
// Decompiled by Procyon v0.5.36
// 

package Mapper;

public enum MappingType
{
    SINGLE_PIXELS("Single_Pixels"), 
    BOARDS_OF_PIXEL("Boards_of_Pixel");
    
    private final String displayed_text;
    
    private MappingType(final String s) {
        this.displayed_text = s;
    }
    
    @Override
    public String toString() {
        return this.displayed_text;
    }
}
