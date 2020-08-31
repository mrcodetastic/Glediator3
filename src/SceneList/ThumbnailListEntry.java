// 
// Decompiled by Procyon v0.5.36
// 

package SceneList;

import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Image;

public class ThumbnailListEntry
{
    private final String title;
    private static final int ICON_SIZE_X = 50;
    private static final int ICON_SIZE_Y = 50;
    private Image resized_image;
    private ImageIcon image;
    
    public ThumbnailListEntry(final String title, final Color[] image_array, final int[] image_size) {
        this.title = title;
        final BufferedImage original_image = new BufferedImage(image_size[0], image_size[1], 2);
        for (int x = 0; x < image_size[0]; ++x) {
            for (int y = 0; y < image_size[1]; ++y) {
                final int rgb = image_array[y * image_size[0] + x].getRGB();
                original_image.setRGB(x, y, rgb);
            }
        }
        this.resized_image = original_image.getScaledInstance(50, 50, 4);
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public ImageIcon getImage() {
        if (this.image == null) {
            this.image = new ImageIcon(this.resized_image);
        }
        return this.image;
    }
    
    @Override
    public String toString() {
        return this.title;
    }
}
