// 
// Decompiled by Procyon v0.5.36
// 

package Filter;

import java.awt.Color;

public class Filter
{
    private FilterMode filter_mode;
    
    public Filter() {
        this.filter_mode = FilterMode.NONE;
    }
    
    public void setFilterMode(final FilterMode filter_mode) {
        this.filter_mode = filter_mode;
    }
    
    public FilterMode getFilterMode() {
        return this.filter_mode;
    }
    
    public void doFiltering(final Color[] image_in, final Color[] image_out) {
        switch (this.filter_mode) {
            case NONE: {
                this.filterNone(image_in, image_out);
                break;
            }
            case INVERT: {
                this.filterInvert(image_in, image_out);
                break;
            }
            case MONOCHROME: {
                this.filterMonochrome(image_in, image_out);
                break;
            }
            case THRESHOLD_1: {
                this.filterTH1(image_in, image_out);
                break;
            }
            case THRESHOLD_2: {
                this.filterTH2(image_in, image_out);
                break;
            }
        }
    }
    
    private void filterNone(final Color[] image_in, final Color[] image_out) {
        final int frame_size = image_in.length;
        System.arraycopy(image_in, 0, image_out, 0, frame_size);
    }
    
    private void filterInvert(final Color[] image_in, final Color[] image_out) {
        for (int frame_size = image_in.length, i = 0; i < frame_size; ++i) {
            final int r = 255 - image_in[i].getRed();
            final int g = 255 - image_in[i].getGreen();
            final int b = 255 - image_in[i].getBlue();
            final Color color = new Color(r, g, b);
            image_out[i] = color;
        }
    }
    
    private void filterMonochrome(final Color[] image_in, final Color[] image_out) {
        for (int frame_size = image_in.length, i = 0; i < frame_size; ++i) {
            final int r = image_in[i].getRed();
            final int g = image_in[i].getGreen();
            final int b = image_in[i].getBlue();
            final int gr = (int)Math.floor(b * 0.10999999940395355 + g * 0.5899999737739563 + r * 0.30000001192092896);
            final Color color = new Color(gr, gr, gr);
            image_out[i] = color;
        }
    }
    
    private void filterTH1(final Color[] image_in, final Color[] image_out) {
        final int frame_size = image_in.length;
        final int th = 200;
        for (int i = 0; i < frame_size; ++i) {
            int r = image_in[i].getRed();
            int g = image_in[i].getGreen();
            int b = image_in[i].getBlue();
            if (r > th) {
                r = 0;
            }
            if (g > th) {
                g = 0;
            }
            if (b > th) {
                b = 0;
            }
            final Color temp_color = new Color(r, g, b);
            image_out[i] = temp_color;
        }
    }
    
    private void filterTH2(final Color[] image_in, final Color[] image_out) {
        final int frame_size = image_in.length;
        final int th = 200;
        for (int i = 0; i < frame_size; ++i) {
            int r = image_in[i].getRed();
            int g = image_in[i].getGreen();
            int b = image_in[i].getBlue();
            if (r < th) {
                r = 0;
            }
            if (g < th) {
                g = 0;
            }
            if (b < th) {
                b = 0;
            }
            final Color temp_color = new Color(r, g, b);
            image_out[i] = temp_color;
        }
    }
    
    public enum FilterMode
    {
        NONE("None"), 
        INVERT("Invert"), 
        MONOCHROME("Monochrome"), 
        THRESHOLD_1("Threshold_1"), 
        THRESHOLD_2("Threshold_2");
        
        private final String displayed_text;
        
        private FilterMode(final String s) {
            this.displayed_text = s;
        }
        
        @Override
        public String toString() {
            return this.displayed_text;
        }
    }
}
