// 
// Decompiled by Procyon v0.5.36
// 

package Effect;

import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImageOp;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public final class Effect
{
    private boolean effect_active;
    private boolean trigger;
    private EffectMode effect_mode;
    private EffectDirection effect_direction;
    private EffectMethode effect_methode;
    private int value;
    private int internal_value;
    private int lower_limit;
    private int upper_limit;
    private int timer_value;
    private int sound_level;
    private int dir;
    private Timer timer;
    private int[] matrix_size;
    ActionListener timer_task;
    
    public Effect(final int[] matrix_size) {
        this.timer_task = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                switch (Effect.this.effect_direction) {
                    case LEFT: {
                        Effect.this.internal_value--;
                        if (Effect.this.internal_value <= Effect.this.lower_limit) {
                            Effect.this.internal_value = Effect.this.upper_limit;
                            break;
                        }
                        break;
                    }
                    case RIGHT: {
                        Effect.this.internal_value++;
                        if (Effect.this.internal_value >= Effect.this.upper_limit) {
                            Effect.this.internal_value = Effect.this.lower_limit;
                            break;
                        }
                        break;
                    }
                    case TOGGLE: {
                        if (Effect.this.dir == 0) {
                            Effect.this.internal_value--;
                            if (Effect.this.internal_value <= Effect.this.lower_limit) {
                                Effect.this.internal_value = Effect.this.lower_limit;
                                Effect.this.dir = 1;
                                break;
                            }
                            break;
                        }
                        else {
                            Effect.this.internal_value++;
                            if (Effect.this.internal_value >= Effect.this.upper_limit) {
                                Effect.this.internal_value = Effect.this.upper_limit;
                                Effect.this.dir = 0;
                                break;
                            }
                            break;
                        }
            
                    }
                }
            }
        };
        this.effect_active = false;
        this.trigger = false;
        this.setMatrixSize(matrix_size);
        this.effect_mode = EffectMode.LEVEL;
        this.effect_direction = EffectDirection.TOGGLE;
        this.effect_methode = EffectMethode.MANUAL;
        this.value = 50;
        this.dir = 1;
        this.lower_limit = 0;
        this.upper_limit = 100;
        this.timer_value = 100;
        (this.timer = new Timer(this.timer_value, this.timer_task)).setRepeats(false);
        this.timer.stop();
    }
    
    public void setMatrixSize(final int[] matrix_size) {
        if (matrix_size[0] > 0 && matrix_size[1] > 0) {
            (this.matrix_size = new int[2])[0] = matrix_size[0];
            this.matrix_size[1] = matrix_size[1];
        }
        else {
            System.out.println("Effect: Wrong matrix size value(s).");
        }
    }
    
    public void setActiveState(final Boolean effect_active) {
        this.effect_active = effect_active;
    }
    
    public boolean getActiveState() {
        return this.effect_active;
    }
    
    public void setEffectMode(final EffectMode effect_mode) {
        this.effect_mode = effect_mode;
    }
    
    public EffectMode getEffectMode() {
        return this.effect_mode;
    }
    
    public void setEffectDirection(final EffectDirection effect_direction) {
        this.effect_direction = effect_direction;
    }
    
    public EffectDirection getEffectDirection() {
        return this.effect_direction;
    }
    
    public void setEffectMethode(final EffectMethode effect_methode) {
        this.effect_methode = effect_methode;
        if (effect_methode.equals(EffectMethode.TIMER)) {
            this.timer.setDelay(this.timer_value);
            this.timer.setRepeats(true);
            this.timer.start();
        }
        else {
            this.timer.setRepeats(false);
            this.timer.stop();
        }
    }
    
    public EffectMethode getEffectMethode() {
        return this.effect_methode;
    }
    
    public void setValue(final int value) {
        if (value >= 0 && value <= 100) {
            this.value = value;
        }
        else {
            System.out.println("Effect: Wrong Value.");
        }
    }
    
    public int getValue() {
        return this.value;
    }
    
    public int setLowerLimit(final int lower_limit) {
        int result = this.lower_limit;
        if (lower_limit >= 0 && lower_limit <= 100 && lower_limit < this.upper_limit) {
            this.lower_limit = lower_limit;
            result = lower_limit;
        }
        else {
            System.out.println("Effect: Wrong lower limit value.");
        }
        return result;
    }
    
    public int getLowerLimit() {
        return this.lower_limit;
    }
    
    public int setUpperLimit(final int upper_limit) {
        int result = this.upper_limit;
        if (upper_limit >= 0 && upper_limit <= 100 && upper_limit > this.lower_limit) {
            this.upper_limit = upper_limit;
            result = upper_limit;
        }
        else {
            System.out.println("Effect: Wrong upper limit value.");
        }
        return result;
    }
    
    public int getUpperLimit() {
        return this.upper_limit;
    }
    
    public void setTimerValue(int timer_value) {
        if (timer_value > 0) {
            this.timer_value = timer_value;
            this.timer.setDelay(timer_value);
        }
        else {
            timer_value = 1;
            System.out.println("Effect: Wrong timer value.");
        }
    }
    
    public int getTimerValue() {
        return this.timer_value;
    }
    
    public void setTrigger() {
        this.trigger = true;
    }
    
    public void setSoundLevel(final int sound_level) {
        if (sound_level >= 0 && sound_level <= 100) {
            this.sound_level = sound_level;
        }
        else {
            System.out.println("Effect: Sound level exceeds limits.");
        }
    }
    
    public void makeEffect(final Color[] image_in, final Color[] image_out) {
        switch (this.effect_methode) {
            case MANUAL: {
                this.internal_value = this.value;
                break;
            }
            case SOUND_LEVEL: {
                this.internal_value = this.sound_level;
                break;
            }
            case BEAT: {
                if (this.trigger) {
                    this.internal_value = this.upper_limit;
                    this.trigger = false;
                    break;
                }
                this.internal_value = this.lower_limit;
                break;
            }
        }
        if (this.effect_active) {
            switch (this.effect_mode) {
                case LEVEL: {
                    this.makeLevelEffect(image_in, image_out);
                    break;
                }
                case ROTATE: {
                    this.makeRotateEffect(image_in, image_out);
                    break;
                }
                case ZOOM: {
                    this.makeZoomEffect(image_in, image_out);
                    break;
                }
                case ROTO_ZOOM: {
                    this.makeRotoZoomEffect(image_in, image_out);
                    break;
                }
                case MOVE_HOR: {
                    this.makeMoveHorEffect(image_in, image_out);
                    break;
                }
                case MOVE_VERT: {
                    this.makeMoveVertEffect(image_in, image_out);
                    break;
                }
                case BLUR: {
                    this.makeBlurEffect(image_in, image_out);
                    break;
                }
            }
        }
        else {
            final int frame_size = image_in.length;
            System.arraycopy(image_in, 0, image_out, 0, frame_size);
        }
    }
    
    private void makeLevelEffect(final Color[] image_in, final Color[] image_out) {
        final int frame_size = image_in.length;
        final float scaling_value = (this.lower_limit + (this.upper_limit - this.lower_limit) / 100.0f * this.internal_value) / 100.0f;
        for (int i = 0; i < frame_size; ++i) {
            final int r = (int)(image_in[i].getRed() * scaling_value);
            final int g = (int)(image_in[i].getGreen() * scaling_value);
            final int b = (int)(image_in[i].getBlue() * scaling_value);
            final Color color = new Color(r, g, b);
            image_out[i] = color;
        }
    }
    
    private void makeRotateEffect(final Color[] image_in, final Color[] image_out) {
        final BufferedImage original_image = new BufferedImage(this.matrix_size[0], this.matrix_size[1], 2);
        final BufferedImage rotated_image = new BufferedImage(this.matrix_size[0], this.matrix_size[1], 2);
        for (int x = 0; x < this.matrix_size[0]; ++x) {
            for (int y = 0; y < this.matrix_size[1]; ++y) {
                final int rgb = image_in[y * this.matrix_size[0] + x].getRGB();
                original_image.setRGB(x, y, rgb);
            }
        }
        final Graphics2D g2 = rotated_image.createGraphics();
        g2.rotate(Math.toRadians(this.internal_value * 3.6f), this.matrix_size[0] / 2, this.matrix_size[1] / 2);
        g2.drawImage(original_image, null, 0, 0);
        for (int x2 = 0; x2 < this.matrix_size[0]; ++x2) {
            for (int y2 = 0; y2 < this.matrix_size[1]; ++y2) {
                final int rgb = rotated_image.getRGB(x2, y2);
                final Color color = new Color(rgb);
                image_out[y2 * this.matrix_size[0] + x2] = color;
            }
        }
    }
    
    private void makeZoomEffect(final Color[] image_in, final Color[] image_out) {
        final BufferedImage original_image = new BufferedImage(this.matrix_size[0], this.matrix_size[1], 2);
        for (int x = 0; x < this.matrix_size[0]; ++x) {
            for (int y = 0; y < this.matrix_size[1]; ++y) {
                final int rgb = image_in[y * this.matrix_size[0] + x].getRGB();
                original_image.setRGB(x, y, rgb);
            }
        }
        final int zoom_factor = (int)(this.internal_value / 20.0f) + 1;
        final int new_x = this.matrix_size[0] * zoom_factor;
        final int new_y = this.matrix_size[1] * zoom_factor;
        final BufferedImage scaled_image = new BufferedImage(new_x, new_y, 2);
        final Graphics2D g2 = scaled_image.createGraphics();
        g2.translate((this.matrix_size[0] - new_x) / 2, (this.matrix_size[1] - new_y) / 2);
        g2.drawImage(original_image, 0, 0, new_x, new_y, null);
        for (int x2 = 0; x2 < this.matrix_size[0]; ++x2) {
            for (int y2 = 0; y2 < this.matrix_size[1]; ++y2) {
                final int rgb = scaled_image.getRGB(x2, y2);
                final Color color = new Color(rgb);
                image_out[y2 * this.matrix_size[0] + x2] = color;
            }
        }
    }
    
    private void makeRotoZoomEffect(final Color[] image_in, final Color[] image_out) {
        final BufferedImage original_image = new BufferedImage(this.matrix_size[0], this.matrix_size[1], 2);
        for (int x = 0; x < this.matrix_size[0]; ++x) {
            for (int y = 0; y < this.matrix_size[1]; ++y) {
                final int rgb = image_in[y * this.matrix_size[0] + x].getRGB();
                original_image.setRGB(x, y, rgb);
            }
        }
        final int zoom_factor = (int)(this.internal_value / 20.0f) + 1;
        final int new_x = this.matrix_size[0] * zoom_factor;
        final int new_y = this.matrix_size[1] * zoom_factor;
        final BufferedImage scaled_image = new BufferedImage(new_x, new_y, 2);
        final Graphics2D g2 = scaled_image.createGraphics();
        g2.rotate(Math.toRadians(this.internal_value * 3.6f), this.matrix_size[0] / 2, this.matrix_size[1] / 2);
        g2.translate((this.matrix_size[0] - new_x) / 2, (this.matrix_size[1] - new_y) / 2);
        g2.drawImage(original_image, 0, 0, new_x, new_y, null);
        for (int x2 = 0; x2 < this.matrix_size[0]; ++x2) {
            for (int y2 = 0; y2 < this.matrix_size[1]; ++y2) {
                final int rgb = scaled_image.getRGB(x2, y2);
                final Color color = new Color(rgb);
                image_out[y2 * this.matrix_size[0] + x2] = color;
            }
        }
    }
    
    private void makeMoveVertEffect(final Color[] image_in, final Color[] image_out) {
        final BufferedImage original_image = new BufferedImage(this.matrix_size[0], this.matrix_size[1], 2);
        final BufferedImage translated_image = new BufferedImage(this.matrix_size[0], this.matrix_size[1], 2);
        for (int x = 0; x < this.matrix_size[0]; ++x) {
            for (int y = 0; y < this.matrix_size[1]; ++y) {
                final int rgb = image_in[y * this.matrix_size[0] + x].getRGB();
                original_image.setRGB(x, y, rgb);
            }
        }
        final int tranlation_value = this.matrix_size[1] - (int)(2.0f * this.matrix_size[1] * this.internal_value / 100.0f);
        final Graphics2D g2 = translated_image.createGraphics();
        g2.translate(0, tranlation_value);
        g2.drawImage(original_image, null, 0, 0);
        for (int x2 = 0; x2 < this.matrix_size[0]; ++x2) {
            for (int y2 = 0; y2 < this.matrix_size[1]; ++y2) {
                final int rgb = translated_image.getRGB(x2, y2);
                final Color color = new Color(rgb);
                image_out[y2 * this.matrix_size[0] + x2] = color;
            }
        }
    }
    
    private void makeMoveHorEffect(final Color[] image_in, final Color[] image_out) {
        final BufferedImage original_image = new BufferedImage(this.matrix_size[0], this.matrix_size[1], 2);
        final BufferedImage translated_image = new BufferedImage(this.matrix_size[0], this.matrix_size[1], 2);
        for (int x = 0; x < this.matrix_size[0]; ++x) {
            for (int y = 0; y < this.matrix_size[1]; ++y) {
                final int rgb = image_in[y * this.matrix_size[0] + x].getRGB();
                original_image.setRGB(x, y, rgb);
            }
        }
        final int tranlation_value = this.matrix_size[0] - (int)(2.0f * this.matrix_size[0] * this.internal_value / 100.0f);
        final Graphics2D g2 = translated_image.createGraphics();
        g2.translate(tranlation_value, 0);
        g2.drawImage(original_image, null, 0, 0);
        for (int x2 = 0; x2 < this.matrix_size[0]; ++x2) {
            for (int y2 = 0; y2 < this.matrix_size[1]; ++y2) {
                final int rgb = translated_image.getRGB(x2, y2);
                final Color color = new Color(rgb);
                image_out[y2 * this.matrix_size[0] + x2] = color;
            }
        }
    }
    
    private void makeBlurEffect(final Color[] image_in, final Color[] image_out) {
        final int max_blur_size = 8;
        final int border = max_blur_size / 2;
        int blur_size = (int)((float)this.internal_value * ((float)max_blur_size / 2.0) / 100.0);
        if (blur_size == 0) {
            blur_size = 1;
        }
        final BufferedImage original_image = new BufferedImage(this.matrix_size[0] + max_blur_size, this.matrix_size[1] + max_blur_size, 2);
        BufferedImage blured_image = new BufferedImage(this.matrix_size[0] + max_blur_size, this.matrix_size[1] + max_blur_size, 2);
        for (int x = 0; x < this.matrix_size[0]; ++x) {
            for (int y = 0; y < this.matrix_size[1]; ++y) {
                final int rgb = image_in[y * this.matrix_size[0] + x].getRGB();
                original_image.setRGB(x + border, y + border, rgb);
            }
        }
        final Graphics2D g2 = blured_image.createGraphics();
        g2.drawImage(original_image, null, 0, 0);
        final float[] blur_kernel = new float[blur_size * blur_size];
        for (int i = 0; i < blur_size * blur_size; ++i) {
            blur_kernel[i] = 1.0f / blur_size;
        }
        final BufferedImageOp blur = new ConvolveOp(new Kernel(blur_size, blur_size, blur_kernel));
        blured_image = blur.filter(blured_image, new BufferedImage(this.matrix_size[0] + max_blur_size, this.matrix_size[1] + max_blur_size, 2));
        for (int x2 = 0; x2 < this.matrix_size[0]; ++x2) {
            for (int y2 = 0; y2 < this.matrix_size[1]; ++y2) {
                final int rgb = blured_image.getRGB(x2 + border, y2 + border);
                final Color color = new Color(rgb);
                image_out[y2 * this.matrix_size[0] + x2] = color;
            }
        }
    }
    
    public enum EffectMode
    {
        LEVEL("Level"), 
        ROTATE("Rotate"), 
        ZOOM("Zoom"), 
        ROTO_ZOOM("Roto_Zoom"), 
        BLUR("Blur"), 
        MOVE_HOR("Move_Hor"), 
        MOVE_VERT("Move_Vert");
        
        private final String displayed_text;
        
        private EffectMode(final String s) {
            this.displayed_text = s;
        }
        
        @Override
        public String toString() {
            return this.displayed_text;
        }
    }
    
    public enum EffectDirection
    {
        LEFT("LEFT"), 
        RIGHT("Right"), 
        TOGGLE("Toggle");
        
        private final String displayed_text;
        
        private EffectDirection(final String s) {
            this.displayed_text = s;
        }
        
        @Override
        public String toString() {
            return this.displayed_text;
        }
    }
    
    public enum EffectMethode
    {
        MANUAL("Manual"), 
        TIMER("Timer"), 
        SOUND_LEVEL("Sound_Level"), 
        BEAT("Beat");
        
        private final String displayed_text;
        
        private EffectMethode(final String s) {
            this.displayed_text = s;
        }
        
        @Override
        public String toString() {
            return this.displayed_text;
        }
    }
}
