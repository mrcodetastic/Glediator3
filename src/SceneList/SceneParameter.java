// 
// Decompiled by Procyon v0.5.36
// 

package SceneList;

import java.util.Properties;
import java.awt.Color;
import Effect.Effect;
import Mixer.Mixer;
import Filter.Filter;
import Generator.SuperGenerator;

public class SceneParameter
{
    public int[] size;
    public SuperGenerator.GeneratorTypes[] genertator_types;
    public String[] generator_parameters;
    public Filter.FilterMode[] filter_modi;
    public Mixer.MixerMode[] mixer_modi;
    public int[] level_values;
    public int[] speed_values;
    public Effect.EffectMode effect_mode;
    public Effect.EffectDirection effect_direction;
    public Effect.EffectMethode effect_methode;
    public int effect_value;
    public int effect_lower_limit;
    public int effect_upper_limit;
    public int effect_timer_value;
    public boolean effect_is_active;
    public Color[] preview_image;
    public String scene_name;
    
    public SceneParameter() {
        this.size = new int[2];
        this.genertator_types = new SuperGenerator.GeneratorTypes[5];
        this.generator_parameters = new String[5];
        this.filter_modi = new Filter.FilterMode[5];
        this.mixer_modi = new Mixer.MixerMode[5];
        this.level_values = new int[5];
        this.speed_values = new int[5];
        this.scene_name = "";
    }
    
    public void addSceneParameter(final String source, final Properties properties, final int index) {
        String prop = source + "_Scene_(" + index + ")_Size_X";
        String val = "" + this.size[0];
        properties.setProperty(prop, val);
        prop = source + "_Scene_(" + index + ")_Size_Y";
        val = "" + this.size[1];
        properties.setProperty(prop, val);
        for (int j = 0; j < 5; ++j) {
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Type";
            val = "" + this.genertator_types[j];
            properties.setProperty(prop, val);
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Parameter";
            val = "" + this.generator_parameters[j];
            properties.setProperty(prop, val);
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Level";
            val = "" + this.level_values[j];
            properties.setProperty(prop, val);
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Speed";
            val = "" + this.speed_values[j];
            properties.setProperty(prop, val);
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Filter";
            val = "" + this.filter_modi[j];
            properties.setProperty(prop, val);
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Mixer";
            val = "" + this.mixer_modi[j];
            properties.setProperty(prop, val);
        }
        prop = source + "_Scene_(" + index + ")_Effect_Mode";
        val = "" + this.effect_mode;
        properties.setProperty(prop, val);
        prop = source + "_Scene_(" + index + ")_Effect_Direction";
        val = "" + this.effect_direction;
        properties.setProperty(prop, val);
        prop = source + "_Scene_(" + index + ")_Effect_Methode";
        val = "" + this.effect_methode;
        properties.setProperty(prop, val);
        prop = source + "_Scene_(" + index + ")_Effect_Value";
        val = "" + this.effect_value;
        properties.setProperty(prop, val);
        prop = source + "_Scene_(" + index + ")_Effect_Lower_Limit";
        val = "" + this.effect_lower_limit;
        properties.setProperty(prop, val);
        prop = source + "_Scene_(" + index + ")_Effect_Upper_Limit";
        val = "" + this.effect_upper_limit;
        properties.setProperty(prop, val);
        prop = source + "_Scene_(" + index + ")_Effect_Timer_Value";
        val = "" + this.effect_timer_value;
        properties.setProperty(prop, val);
        prop = source + "_Scene_(" + index + ")_Effect_Status";
        val = "" + this.effect_is_active;
        properties.setProperty(prop, val);
        prop = source + "_Scene_(" + index + ")_Preview_Image_Size";
        val = "" + this.preview_image.length;
        properties.setProperty(prop, val);
        for (int j = 0; j < this.preview_image.length; ++j) {
            prop = source + "_Scene_(" + index + ")_PreviewImage_(" + j + ")_RGB";
            val = "" + this.preview_image[j].getRGB();
            properties.setProperty(prop, val);
        }
        prop = source + "_Scene_(" + index + ")_Name";
        val = "" + this.scene_name;
        properties.setProperty(prop, val);
    }
    
    public void extractSceneParameter(final String source, final int index, final Properties properties) {
        String prop = source + "_Scene_(" + index + ")_Size_X";
        String val = properties.getProperty(prop);
        this.size[0] = Integer.parseInt(val);
        prop = source + "_Scene_(" + index + ")_Size_Y";
        val = properties.getProperty(prop);
        this.size[1] = Integer.parseInt(val);
        for (int j = 0; j < 5; ++j) {
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Type";
            val = properties.getProperty(prop);
            this.genertator_types[j] = SuperGenerator.GeneratorTypes.valueOf(val.toUpperCase());
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Parameter";
            val = properties.getProperty(prop);
            this.generator_parameters[j] = val;
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Level";
            val = properties.getProperty(prop);
            this.level_values[j] = Integer.parseInt(val);
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Speed";
            val = properties.getProperty(prop);
            this.speed_values[j] = Integer.parseInt(val);
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Filter";
            val = properties.getProperty(prop);
            this.filter_modi[j] = Filter.FilterMode.valueOf(val.toUpperCase());
            prop = source + "_Scene_(" + index + ")_Generator_(" + j + ")_Mixer";
            val = properties.getProperty(prop);
            this.mixer_modi[j] = Mixer.MixerMode.valueOf(val.toUpperCase());
        }
        prop = source + "_Scene_(" + index + ")_Effect_Mode";
        val = properties.getProperty(prop);
        this.effect_mode = Effect.EffectMode.valueOf(val.toUpperCase());
        prop = source + "_Scene_(" + index + ")_Effect_Direction";
        val = properties.getProperty(prop);
        this.effect_direction = Effect.EffectDirection.valueOf(val.toUpperCase());
        prop = source + "_Scene_(" + index + ")_Effect_Methode";
        val = properties.getProperty(prop);
        this.effect_methode = Effect.EffectMethode.valueOf(val.toUpperCase());
        prop = source + "_Scene_(" + index + ")_Effect_Value";
        val = properties.getProperty(prop);
        this.effect_value = Integer.parseInt(val);
        prop = source + "_Scene_(" + index + ")_Effect_Lower_Limit";
        val = properties.getProperty(prop);
        this.effect_lower_limit = Integer.parseInt(val);
        prop = source + "_Scene_(" + index + ")_Effect_Upper_Limit";
        val = properties.getProperty(prop);
        this.effect_upper_limit = Integer.parseInt(val);
        prop = source + "_Scene_(" + index + ")_Effect_Timer_Value";
        val = properties.getProperty(prop);
        this.effect_timer_value = Integer.parseInt(val);
        prop = source + "_Scene_(" + index + ")_Effect_Status";
        val = properties.getProperty(prop);
        this.effect_is_active = Boolean.parseBoolean(val);
        prop = source + "_Scene_(" + index + ")_Preview_Image_Size";
        val = properties.getProperty(prop);
        final int preview_image_size = Integer.parseInt(val);
        this.preview_image = new Color[preview_image_size];
        for (int i = 0; i < preview_image_size; ++i) {
            prop = source + "_Scene_(" + index + ")_PreviewImage_(" + i + ")_RGB";
            val = properties.getProperty(prop);
            this.preview_image[i] = new Color(Integer.parseInt(val));
        }
        prop = source + "_Scene_(" + index + ")_Name";
        val = properties.getProperty(prop);
        this.scene_name = val;
    }
}
