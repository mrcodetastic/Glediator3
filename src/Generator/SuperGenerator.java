// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Color;
import Audio.AudioPropertiers;

public abstract class SuperGenerator
{
    public static AudioPropertiers audio_properties;
    protected int[] size;
    protected String parameter_string;
    protected String[] parameter_array;
    protected boolean parameter_changed;
    protected int speed;
    protected int speed_counter;
    protected GeneratorTypes generator_type;
    
    public SuperGenerator(final String parameter_string, final int[] size, final int speed) {
        this.speed_counter = 0;
        this.size = new int[2];
        this.setSize(size);
        this.setSpeed(speed);
        this.setParameters(this.parameter_string = parameter_string);
    }
    
    public final void setSize(final int[] size) {
        if (size[0] > 0 && size[1] > 0) {
            this.size[0] = size[0];
            this.size[1] = size[1];
            SuperGenerator.audio_properties.fft_binns = (int)(1.5f * Math.max(size[0], size[1]));
            SuperGenerator.audio_properties.fft = new float[SuperGenerator.audio_properties.fft_binns];
            this.parameter_changed = true;
        }
        else {
            System.out.println("Generator: Wrong size value.");
        }
    }
    
    public final void setSpeed(final int speed) {
        if (speed > 0 && speed <= 20) {
            this.speed = 21 - speed;
        }
        else {
            System.out.println("Generator: Wrong speed value.");
        }
    }
    
    public final int getSpeed() {
        return 21 - this.speed;
    }
    
    public final void setGeneratorType(final GeneratorTypes generator_type) {
        this.generator_type = generator_type;
    }
    
    public final GeneratorTypes getGeneratorType() {
        return this.generator_type;
    }
    
    public final void setParameters(final String parameter_string) {
        if (parameter_string != null && parameter_string.length() != 0) {
            this.parameter_string = parameter_string;
            this.parameter_array = parameter_string.split(";");
            this.parameter_changed = true;
        }
        else {
            System.out.println("Generator: Wrong parameter(s).");
        }
    }
    
    public final String getParameterString() {
        return this.parameter_string;
    }
    
    public final String[] getParameterArray() {
        return this.parameter_array;
    }
    
    public final String getGeratorType() {
        return this.parameter_array[0];
    }
    
    public final void getNextImage(final Color[] image) {
        if (this.speed_counter % this.speed == 0) {
            this.generateImage(image);
        }
        ++this.speed_counter;
    }
    
    abstract void generateImage(final Color[] p0);
    
    public abstract void Show_Config_Window();
    
    public abstract void closeConfigWindow();
    
    static {
        SuperGenerator.audio_properties = new AudioPropertiers();
    }
    
    public enum GeneratorTypes
    {
        BLACK("Black"), 
        ANIMATED_GIF("Animated_GIF"), 
        CAPTURE("Capture"), 
        EXPANDING_OBJECTS("Expanding_Objects"), 
        FADE_AND_SCROLL("Fade_and_Scroll"), 
        FADING_PIXELS("Fading_Pixels"), 
        FALLING_OBJECTS("Falling_Objects"), 
        FIRE("Fire"), 
        FRACTAL("Fractal"), 
        GRID("Grid"), 
        KNIGHT_RIDER("Knight_Rider"), 
        META_BALLS("Meta_Balls"), 
        OBJECTS_3D("Objects_3D"), 
        PLASMA("Plasma"), 
        RANDOM_PIXEL("Random_Pixel"), 
        SCROLLING_TEXT("Scrolling_Text"), 
        SIMPLE_SPECTRUM("Simple_Spectrum"), 
        STROBO("Strobo"), 
        WAVE("Wave");
        
        private final String displayed_text;
        
        private GeneratorTypes(final String s) {
            this.displayed_text = s;
        }
        
        @Override
        public String toString() {
            return this.displayed_text;
        }
    }
}
