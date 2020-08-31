// 
// Decompiled by Procyon v0.5.36
// 

package Output;

public enum ColorOrder
{
    RGB("RGB"), 
    RBG("RBG"), 
    GRB("GRB"), 
    GBR("GBR"), 
    BRG("BRG"), 
    BGR("BGR");
    
    private final String displayed_text;
    
    private ColorOrder(final String s) {
        this.displayed_text = s;
    }
    
    @Override
    public String toString() {
        return this.displayed_text;
    }
}
