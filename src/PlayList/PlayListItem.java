// 
// Decompiled by Procyon v0.5.36
// 

package PlayList;

import java.awt.Color;
import SceneList.SceneParameter;
import Fader.Fader;

public class PlayListItem
{
    public static final int DEFAULT_SCENE_TIME = 5000;
    public static final int DEFAULT_FADE_TIME = 200;
    public static final Fader.FaderMode DEFAULT_FADER_MODE;
    private SceneParameter scene_parameter;
    private int scene_time;
    private int fade_time;
    private Fader.FaderMode fader_mode;
    
    public PlayListItem() {
        this.scene_parameter = new SceneParameter();
        this.scene_time = 5000;
        this.fade_time = 200;
        this.fader_mode = PlayListItem.DEFAULT_FADER_MODE;
    }
    
    public void setSceneParameter(final SceneParameter scene_parameter) {
        this.copySceneParameters(scene_parameter, this.scene_parameter);
    }
    
    public SceneParameter getSceneParameter() {
        final SceneParameter result = new SceneParameter();
        this.copySceneParameters(this.scene_parameter, result);
        return result;
    }
    
    public void setSceneTime(final int scene_time) {
        if (scene_time > 0) {
            this.scene_time = scene_time;
        }
        else {
            System.out.println("PlayList: Wrong scene time value.");
        }
    }
    
    public int getSceneTime() {
        return this.scene_time;
    }
    
    public void setFadeTime(final int fade_time) {
        if (fade_time > 0) {
            this.fade_time = fade_time;
        }
        else {
            System.out.println("PlayList: Wrong fade time value.");
        }
    }
    
    public int getFadeTime() {
        return this.fade_time;
    }
    
    public void setFaderMode(final Fader.FaderMode fader_mode) {
        this.fader_mode = fader_mode;
    }
    
    public Fader.FaderMode getFaderMode() {
        return this.fader_mode;
    }
    
    public String generateName() {
        final String result = "<html>" + this.scene_parameter.scene_name + "<br>" + this.scene_time + "/" + this.fade_time * 100 + " ms" + "<br>" + this.fader_mode.toString() + "</html>";
        return result;
    }
    
    private void copySceneParameters(final SceneParameter sour, final SceneParameter dest) {
        dest.size[0] = sour.size[0];
        dest.size[1] = sour.size[1];
        for (int i = 0; i < 5; ++i) {
            dest.genertator_types[i] = sour.genertator_types[i];
            dest.generator_parameters[i] = sour.generator_parameters[i];
            dest.level_values[i] = sour.level_values[i];
            dest.speed_values[i] = sour.speed_values[i];
            dest.filter_modi[i] = sour.filter_modi[i];
            dest.mixer_modi[i] = sour.mixer_modi[i];
        }
        dest.effect_mode = sour.effect_mode;
        dest.effect_direction = sour.effect_direction;
        dest.effect_methode = sour.effect_methode;
        dest.effect_value = sour.effect_value;
        dest.effect_lower_limit = sour.effect_lower_limit;
        dest.effect_upper_limit = sour.effect_upper_limit;
        dest.effect_timer_value = sour.effect_timer_value;
        dest.effect_is_active = sour.effect_is_active;
        final int size = dest.size[0] * dest.size[1];
        dest.preview_image = new Color[size];
        for (int j = 0; j < size; ++j) {
            dest.preview_image[j] = new Color(sour.preview_image[j].getRGB());
        }
        dest.scene_name = sour.scene_name;
    }
    
    static {
        DEFAULT_FADER_MODE = Fader.FaderMode.PROGRESSIVE;
    }
}
