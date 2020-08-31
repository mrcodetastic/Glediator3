// 
// Decompiled by Procyon v0.5.36
// 

package Scene;

import SceneList.SceneParameter;
import SceneList.ThumbnailListEntry;
import Generator.Wave;
import Generator.Strobo;
import Generator.SimpleSpectrum;
import Generator.ScrollingText;
import Generator.RandomPixel;
import Generator.Plasma;
import Generator.Objects3D;
import Generator.MetaBalls;
import Generator.KnightRider;
import Generator.Grid;
import Generator.Fractal;
import Generator.Fire;
import Generator.FallingObjects;
import Generator.FadingPixel;
import Generator.FadeAndScroll;
import Generator.ExpandingObjects;
import Generator.Capture;
import Generator.AnimatedGif;
import Generator.Black;
import java.awt.Color;
import Effect.Effect;
import Mixer.Mixer;
import Dimmer.Dimmer;
import Filter.Filter;
import Generator.SuperGenerator;

public class Scene
{
    public static final int NUMBER_OF_GENERATORS = 5;
    public SuperGenerator[] generators;
    public Filter[] filters;
    public Dimmer[] dimmers;
    public Mixer[] mixers;
    public Effect effect;
    private String scene_name;
    public Color[] preview_image;
    private int active_generator;
    private int[] matrix_size;
    private int[] speed_levels;
    private Color[][] generator_images;
    private Color[][] filtered_images;
    private Color[] mixed_image;
    private Color[] effected_image;
    
    public Scene(final int[] matrix_size, final int speed_level) {
        this.matrix_size = matrix_size;
        this.speed_levels = new int[5];
        this.initializeImages();
        this.generators = new SuperGenerator[5];
        this.filters = new Filter[5];
        this.dimmers = new Dimmer[5];
        this.mixers = new Mixer[5];
        for (int i = 0; i < 5; ++i) {
            this.speed_levels[i] = speed_level;
            (this.generators[i] = new Black(matrix_size, this.speed_levels[i])).setGeneratorType(SuperGenerator.GeneratorTypes.BLACK);
            this.filters[i] = new Filter();
            this.dimmers[i] = new Dimmer();
            this.mixers[i] = new Mixer();
        }
        this.effect = new Effect(matrix_size);
        this.active_generator = 0;
    }
    
    public void setMatrixSize(final int[] matrix_size) {
        if (matrix_size[0] > 0 && matrix_size[1] > 0) {
            this.matrix_size[0] = matrix_size[0];
            this.matrix_size[1] = matrix_size[1];
            this.initializeImages();
            for (int i = 0; i < 5; ++i) {
                this.generators[i].setSize(matrix_size);
            }
            this.effect.setMatrixSize(matrix_size);
        }
    }
    
    public int[] getMatrixSize() {
        return this.matrix_size;
    }
    
    public void setActiveGenerator(final int active_generator) {
        if (active_generator >= 0 && active_generator < 5) {
            this.active_generator = active_generator;
        }
        else {
            System.out.println("Scene: Wrong active generator value.");
        }
    }
    
    public int getActiveGenerator() {
        return this.active_generator;
    }
    
    public void setGeneratorType(final SuperGenerator.GeneratorTypes generator_type) {
        this.generators[this.active_generator].closeConfigWindow();
        switch (generator_type) {
            case BLACK: {
                this.generators[this.active_generator] = new Black(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case ANIMATED_GIF: {
                this.generators[this.active_generator] = new AnimatedGif(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case CAPTURE: {
                this.generators[this.active_generator] = new Capture(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case EXPANDING_OBJECTS: {
                this.generators[this.active_generator] = new ExpandingObjects(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case FADE_AND_SCROLL: {
                this.generators[this.active_generator] = new FadeAndScroll(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case FADING_PIXELS: {
                this.generators[this.active_generator] = new FadingPixel(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case FALLING_OBJECTS: {
                this.generators[this.active_generator] = new FallingObjects(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case FIRE: {
                this.generators[this.active_generator] = new Fire(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case FRACTAL: {
                this.generators[this.active_generator] = new Fractal(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case GRID: {
                this.generators[this.active_generator] = new Grid(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case KNIGHT_RIDER: {
                this.generators[this.active_generator] = new KnightRider(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case META_BALLS: {
                this.generators[this.active_generator] = new MetaBalls(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case OBJECTS_3D: {
                this.generators[this.active_generator] = new Objects3D(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case PLASMA: {
                this.generators[this.active_generator] = new Plasma(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case RANDOM_PIXEL: {
                this.generators[this.active_generator] = new RandomPixel(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case SCROLLING_TEXT: {
                this.generators[this.active_generator] = new ScrollingText(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case SIMPLE_SPECTRUM: {
                this.generators[this.active_generator] = new SimpleSpectrum(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case STROBO: {
                this.generators[this.active_generator] = new Strobo(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
            case WAVE: {
                this.generators[this.active_generator] = new Wave(this.matrix_size, this.speed_levels[this.active_generator]);
                break;
            }
        }
        this.generators[this.active_generator].setGeneratorType(generator_type);
    }
    
    public SuperGenerator.GeneratorTypes getGeneratorType(final int index) {
        return this.generators[index].getGeneratorType();
    }
    
    public void setName(final String scene_name) {
        this.scene_name = scene_name;
    }
    
    public String getName() {
        return this.scene_name;
    }
    
    public void generatePreviewImage() {
        final int size = this.matrix_size[0] * this.matrix_size[1];
        this.getNextImage(this.preview_image = new Color[size]);
    }
    
    public ThumbnailListEntry getSceneListEntry() {
        return new ThumbnailListEntry(this.scene_name, this.preview_image, this.matrix_size);
    }
    
    public void setFilterMode(final Filter.FilterMode filter_mode) {
        this.filters[this.active_generator].setFilterMode(filter_mode);
    }
    
    public Filter.FilterMode getFilterMode(final int index) {
        return this.filters[index].getFilterMode();
    }
    
    public void setMixerMode(final Mixer.MixerMode mixer_mode) {
        this.mixers[this.active_generator].setMixerMode(mixer_mode);
    }
    
    public Mixer.MixerMode getMixerMode(final int index) {
        return this.mixers[index].getMixerMode();
    }
    
    public void setLevel(final int level) {
        this.dimmers[this.active_generator].setDimmerLevel(level);
    }
    
    public int getLevel(final int index) {
        return this.dimmers[index].getDimmerLevel();
    }
    
    public void setGeneratorSpeed(final int speed) {
        this.speed_levels[this.active_generator] = speed;
        this.generators[this.active_generator].setSpeed(speed);
    }
    
    public int getGeneratorSpeed(final int index) {
        return this.generators[index].getSpeed();
    }
    
    public void showGeneratorConfigurationeWindow() {
        this.generators[this.active_generator].Show_Config_Window();
    }
    
    public void getNextImage(final Color[] image) {
        for (int i = 0; i < 5; ++i) {
            this.generators[i].getNextImage(this.generator_images[i]);
            this.filters[i].doFiltering(this.generator_images[i], this.filtered_images[i]);
            this.dimmers[i].doDimming(this.filtered_images[i]);
        }
        this.mixers[1].do_mixing(this.filtered_images[0], this.filtered_images[1], this.mixed_image);
        for (int i = 2; i < 5; ++i) {
            this.mixers[i].do_mixing(this.mixed_image, this.filtered_images[i], this.mixed_image);
        }
        this.effect.makeEffect(this.mixed_image, image);
    }
    
    public void setScene(final SceneParameter scene_parameter) {
        final int old_active_generator = this.active_generator;
        for (int i = 0; i < 5; ++i) {
            this.active_generator = i;
            this.speed_levels[i] = scene_parameter.speed_values[i];
            this.setGeneratorType(scene_parameter.genertator_types[i]);
            this.generators[i].setParameters(scene_parameter.generator_parameters[i]);
            this.dimmers[i].setDimmerLevel(scene_parameter.level_values[i]);
            this.filters[i].setFilterMode(scene_parameter.filter_modi[i]);
            this.mixers[i].setMixerMode(scene_parameter.mixer_modi[i]);
        }
        this.effect.setEffectMode(scene_parameter.effect_mode);
        this.effect.setEffectDirection(scene_parameter.effect_direction);
        this.effect.setEffectMethode(scene_parameter.effect_methode);
        this.effect.setValue(scene_parameter.effect_value);
        this.effect.setLowerLimit(scene_parameter.effect_lower_limit);
        this.effect.setUpperLimit(scene_parameter.effect_upper_limit);
        this.effect.setTimerValue(scene_parameter.effect_timer_value);
        this.effect.setActiveState(scene_parameter.effect_is_active);
        this.active_generator = old_active_generator;
    }
    
    private void initializeImages() {
        this.generator_images = new Color[5][this.matrix_size[0] * this.matrix_size[1]];
        this.filtered_images = new Color[5][this.matrix_size[0] * this.matrix_size[1]];
        this.mixed_image = new Color[this.matrix_size[0] * this.matrix_size[1]];
        this.effected_image = new Color[this.matrix_size[0] * this.matrix_size[1]];
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < this.matrix_size[0] * this.matrix_size[1]; ++j) {
                this.generator_images[i][j] = Color.BLACK;
                this.filtered_images[i][j] = Color.BLACK;
            }
        }
        for (int i = 0; i < this.matrix_size[0] * this.matrix_size[1]; ++i) {
            this.mixed_image[i] = Color.BLACK;
            this.effected_image[i] = Color.BLACK;
        }
    }
}
