// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.util.Iterator;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import javax.imageio.ImageReader;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.HeadlessException;
import java.awt.image.ImageObserver;
import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class AnimatedGif extends SuperGenerator
{
    int gif_speed;
    boolean keep_aspect_ratio;
    String path_to_gif;
    int slected_index;
    int ani_pos;
    int gif_speed_counter;
    BufferedImage[] animation;
    int animation_length;
    AnimatedGifOptions options;
    
    public AnimatedGif(final String _parameter_string, final int[] _size, final int _speed) {
        super(_parameter_string, _size, _speed);
        this.gif_speed = 1;
        this.keep_aspect_ratio = false;
        this.path_to_gif = "";
        this.slected_index = 0;
        this.ani_pos = 0;
        this.gif_speed_counter = 0;
        this.animation = null;
        this.animation_length = 0;
        this.options = null;
    }
    
    public AnimatedGif(final int[] _size, final int _speed) {
        super("Animated Gif;xxx;xxx;0;85;false;", _size, _speed);
        this.gif_speed = 1;
        this.keep_aspect_ratio = false;
        this.path_to_gif = "";
        this.slected_index = 0;
        this.ani_pos = 0;
        this.gif_speed_counter = 0;
        this.animation = null;
        this.animation_length = 0;
        this.options = null;
    }
    
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
        image = new ImageIcon(image).getImage();
        final boolean hasAlpha = false;
        BufferedImage bimage = null;
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = 1;
            if (hasAlpha) {
                transparency = 2;
            }
            final GraphicsDevice gs = ge.getDefaultScreenDevice();
            final GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        }
        catch (HeadlessException ex) {}
        if (bimage == null) {
            int type = 1;
            if (hasAlpha) {
                type = 2;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        final Graphics g = bimage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }
    
    @Override
    void generateImage(final Color[] image) {
        if (this.parameter_changed) {
            this.path_to_gif = this.parameter_array[2];
            this.gif_speed = Integer.parseInt(this.parameter_array[4]);
            this.keep_aspect_ratio = Boolean.parseBoolean(this.parameter_array[5]);
            try {
                final Object input = new FileInputStream(this.path_to_gif);
                final ImageInputStream stream = ImageIO.createImageInputStream(input);
                final Iterator readers = ImageIO.getImageReaders(stream);
                if (!readers.hasNext()) {
                    throw new RuntimeException("No image reader found!");
                }
                final ImageReader reader = (ImageReader) readers.next();
                reader.setInput(stream);
                this.animation_length = reader.getNumImages(true);
                this.animation = new BufferedImage[this.animation_length];
                for (int i = 0; i < this.animation_length; ++i) {
                    this.animation[i] = toBufferedImage(reader.read(i).getScaledInstance(this.size[0], this.size[1], 1));
                }
                stream.close();
            }
            catch (IOException ex) {
                this.animation_length = 0;
            }
            this.parameter_changed = false;
            this.options = null;
        }
        if (this.animation_length > 0) {
            this.ani_pos = this.speed_counter % (this.animation_length + (100 - this.gif_speed));
            this.ani_pos = (int)(this.ani_pos / (float)(this.animation_length + (100 - this.gif_speed)) * this.animation_length);
            if (this.animation[this.ani_pos] != null) {
                final int hh = this.animation[this.ani_pos].getHeight();
                final int ww = this.animation[this.ani_pos].getWidth();
                for (int xx = 0; xx < this.size[0]; ++xx) {
                    for (int yy = 0; yy < this.size[1]; ++yy) {
                        Color temp_color = Color.BLACK;
                        if (yy < hh & xx < ww) {
                            temp_color = new Color(this.animation[this.ani_pos].getRGB(xx, yy));
                        }
                        image[yy * this.size[0] + xx] = temp_color;
                    }
                }
            }
        }
        else {
            for (int x = 0; x < this.size[0]; ++x) {
                for (int y = 0; y < this.size[1]; ++y) {
                    image[y * this.size[0] + x] = Color.black;
                }
            }
        }
    }
    
    @Override
    public void Show_Config_Window() {
        (this.options = new AnimatedGifOptions(this)).setVisible(true);
    }
    
    @Override
    public void closeConfigWindow() {
        if (this.options != null) {
            this.options.setVisible(false);
            this.options = null;
        }
    }
}
