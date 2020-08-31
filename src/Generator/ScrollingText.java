// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;

public class ScrollingText extends SuperGenerator
{
    String st_text;
    String st_font;
    String st_style;
    int st_style_int;
    int st_size;
    int st_vert_pos;
    int st_hor_pos;
    int st_width;
    int st_dir;
    int st_red;
    int st_green;
    int st_blue;
    ScrollingTextOptions options;
    
    public ScrollingText(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.st_text = "Hey Dude, were is my car?";
        this.st_font = "Sans Serif";
        this.st_style = "Normal";
        this.st_style_int = 1;
        this.st_size = 14;
        this.st_vert_pos = 50;
        this.st_hor_pos = 0;
        this.st_width = 40;
        this.st_dir = 1;
        this.st_red = 0;
        this.st_green = 255;
        this.st_blue = 127;
        this.options = null;
    }
    
    public ScrollingText(final int[] _size, final int _speed) {
        super("Scrolling Text;Hello!;Sans Serif;14;Bold;20;127;127;255", _size, _speed);
        this.st_text = "Hey Dude, were is my car?";
        this.st_font = "Sans Serif";
        this.st_style = "Normal";
        this.st_style_int = 1;
        this.st_size = 14;
        this.st_vert_pos = 50;
        this.st_hor_pos = 0;
        this.st_width = 40;
        this.st_dir = 1;
        this.st_red = 0;
        this.st_green = 255;
        this.st_blue = 127;
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new ScrollingTextOptions(this)).setVisible(true);
    }
    
    @Override
    void generateImage(final Color[] image) {
        final Random rnd = new Random();
        if (this.parameter_changed) {
            this.st_text = this.parameter_array[1];
            this.st_font = this.parameter_array[2];
            this.st_size = Integer.parseInt(this.parameter_array[3]);
            this.st_style = this.parameter_array[4];
            this.st_vert_pos = Integer.parseInt(this.parameter_array[5]);
            this.st_red = Integer.parseInt(this.parameter_array[6]);
            this.st_green = Integer.parseInt(this.parameter_array[7]);
            this.st_blue = Integer.parseInt(this.parameter_array[8]);
            this.parameter_changed = false;
            this.options = null;
        }
        final BufferedImage tempImage = new BufferedImage(this.size[0], this.size[1], 2);
        final Graphics2D gr = tempImage.createGraphics();
        if (this.st_font.equals("Sans Serif")) {
            this.st_font = "SansSerif";
        }
        if (this.st_font.equals("Dialog")) {
            this.st_font = "Dialog";
        }
        if (this.st_font.equals("Mono Spaced")) {
            this.st_font = "Monospaced";
        }
        if (this.st_style.equals("Normal")) {
            this.st_style_int = 0;
        }
        if (this.st_style.equals("Bold")) {
            this.st_style_int = 1;
        }
        if (this.st_style.equals("Italic")) {
            this.st_style_int = 2;
        }
        final Font fo = new Font(this.st_font, this.st_style_int, this.st_size);
        gr.setFont(fo);
        gr.setColor(new Color(this.st_red, this.st_green, this.st_blue));
        gr.drawString(this.st_text, this.st_hor_pos, (int)((float)((100 - this.st_vert_pos) * this.size[1]) / 100.0));
        final FontRenderContext frc = gr.getFontRenderContext();
        final Rectangle2D rect = fo.getStringBounds(this.st_text, frc);
        this.st_width = (int)rect.getWidth();
        gr.dispose();
        if (this.st_dir == 1) {
            ++this.st_hor_pos;
        }
        else {
            --this.st_hor_pos;
        }
        if (this.st_hor_pos >= this.size[0]) {
            this.st_dir = 0;
        }
        if (this.st_hor_pos <= 0 - this.st_width) {
            this.st_dir = 1;
        }
        for (int x = 0; x < this.size[0]; ++x) {
            for (int y = 0; y < this.size[1]; ++y) {
                final Color temp_color = new Color(tempImage.getRGB(x, y));
                image[y * this.size[0] + x] = temp_color;
            }
        }
    }
    
    @Override
    public void closeConfigWindow() {
        if (this.options != null) {
            this.options.setVisible(false);
            this.options = null;
        }
    }
}
