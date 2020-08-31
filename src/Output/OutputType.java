// 
// Decompiled by Procyon v0.5.36
// 

package Output;

public enum OutputType
{
    NO_DATA_TRANSMISSION("No_Data_Transmission"), 
    ARTNET("Artnet"), 
    TPM2_NET("TPM2_Net"), 
    MINI_DMX("Mini_DMX"), 
    TPM2("TPM2"),
    TPM2_LITE("TPM2_Lite_16bit"),     
    GLEDIATOR_PROTOCOL("Glediator_Protocol");
    
    private final String displayed_text;
    
    private OutputType(final String s) {
        this.displayed_text = s;
    }
    
    @Override
    public String toString() {
        return this.displayed_text;
    }
}
