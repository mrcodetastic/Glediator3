// 
// Decompiled by Procyon v0.5.36
// 

package Output;

public enum BaudRate
{
    B_115200("B_115200"), 
    B_230400("B_230400"), 
    B_250000("B_250000"), 
    B_500000("B_500000"), 
    B_921600("B_921600"), 
    B_1000000("B_1000000"), 
    B_1250000("B_1250000");
    
    private final String displayed_text;
    
    private BaudRate(final String s) {
        this.displayed_text = s;
    }
    
    @Override
    public String toString() {
        return this.displayed_text;
    }
}
