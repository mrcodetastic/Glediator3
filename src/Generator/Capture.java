// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Color;

public class Capture extends SuperGenerator
{
    int cap_x;
    int cap_y;
    int cap_w;
    int cap_h;
    CaptureThumbnail cap_thumb;
    CaptureOptions options;
    
    public Capture(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.cap_x = 100;
        this.cap_y = 100;
        this.cap_w = 300;
        this.cap_h = 150;
        this.cap_thumb = new CaptureThumbnail();
        this.options = null;
    }
    
    public Capture(final int[] _size, final int _speed) {
        super("Capture;100;100;600;300", _size, _speed);
        this.cap_x = 100;
        this.cap_y = 100;
        this.cap_w = 300;
        this.cap_h = 150;
        this.cap_thumb = new CaptureThumbnail();
        this.options = null;
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new CaptureOptions(this)).setVisible(true);
    }
    
    @Override
    public void closeConfigWindow() {
        if (this.options != null) {
            this.options.setVisible(false);
            this.options = null;
        }
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.cap_x = Integer.parseInt(this.parameter_array[1]);
            this.cap_y = Integer.parseInt(this.parameter_array[2]);
            this.cap_w = Integer.parseInt(this.parameter_array[3]);
            this.cap_h = Integer.parseInt(this.parameter_array[4]);
            this.parameter_changed = false;
            this.options = null;
        }
        try {
            final Robot robot = new Robot();
            final Rectangle captureSize = new Rectangle(this.cap_x, this.cap_y, this.cap_w, this.cap_h);
            final BufferedImage capt_image = robot.createScreenCapture(captureSize);
            final BufferedImage resizedImage = this.cap_thumb.createThumbnail(capt_image, this.size[0]);
            if (resizedImage != null) {
                final int hh = resizedImage.getHeight();
                final int ww = resizedImage.getWidth();
                for (int xx = 0; xx < this.size[0]; ++xx) {
                    for (int yy = 0; yy < this.size[1]; ++yy) {
                        Color temp_color = Color.BLACK;
                        if (yy < hh & xx < ww) {
                            temp_color = new Color(resizedImage.getRGB(xx, yy));
                        }
                        image[yy * this.size[0] + xx] = temp_color;
                    }
                }
            }
        }
        catch (AWTException e) {
            System.out.println("Generator_Capture: Error while caturing.");
        }
    }
}
