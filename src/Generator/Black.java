// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Color;

public class Black extends SuperGenerator
{
    public Black(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
    }
    
    public Black(final int[] _size, final int _speed) {
        super("Black;x;y;z;", _size, _speed);
    }
    
    @Override
    void generateImage(final Color[] image) {
        for (int i = 0; i < this.size[0] * this.size[1]; ++i) {
            image[i] = Color.BLACK;
        }
    }
    
    @Override
    public void Show_Config_Window() {
    }
    
    @Override
    public void closeConfigWindow() {
    }
}
