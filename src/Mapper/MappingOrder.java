// 
// Decompiled by Procyon v0.5.36
// 

package Mapper;

public enum MappingOrder
{
    HS_TL("HS_TL"), 
    HS_TR("HS_TR"), 
    HS_BL("HS_BL"), 
    HS_BR("HS_BR"), 
    HL_TL("HL_TL (MatrixPanel)"), 
    HL_TR("HL_TR"), 
    HL_BL("HL_BL"), 
    HL_BR("HL_BR"), 
    VS_TL("VS_TL"), 
    VS_TR("VS_TR"), 
    VS_BL("VS_BL"), 
    VS_BR("HS_BR"), 
    VL_TL("VL_TL"), 
    VL_TR("VL_TR"), 
    VL_BL("VL_BL"), 
    VL_BR("HL_BR");
    
    private final String displayed_text;
    
    private MappingOrder(final String s) {
        this.displayed_text = s;
    }
    
    @Override
    public String toString() {
        return this.displayed_text;
    }
}
