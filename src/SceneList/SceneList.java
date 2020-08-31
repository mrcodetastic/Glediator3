// 
// Decompiled by Procyon v0.5.36
// 

package SceneList;

import java.awt.Color;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.JFileChooser;
import java.awt.Component;
import Main.NotificationMessage;
import Scene.Scene;
import java.util.Observable;

public class SceneList extends Observable
{
    public static final int MAX_NUM_SCENES = 300;
    public static final String FILE_TYPE = "Scene_List_File";
    SceneParameter[] scene_parameters;
    int index;
    int selected_index;
    int number_of_entries;
    SceneNameGetter scene_name_getter;
    
    public SceneList() {
        this.scene_parameters = new SceneParameter[300];
        for (int i = 0; i < 300; ++i) {
            this.scene_parameters[i] = new SceneParameter();
        }
        this.scene_name_getter = new SceneNameGetter(this);
        this.number_of_entries = 0;
    }
    
    public void addScene(final Scene scene, final int index) {
        this.selected_index = index;
        if (index < 299) {
            if (this.number_of_entries > 0) {
                for (int i = this.number_of_entries; i > index; --i) {
                    this.copySceneParameters(this.scene_parameters[i - 1], this.scene_parameters[i]);
                }
            }
            ++this.number_of_entries;
            this.index = index;
            scene.generatePreviewImage();
            this.extractSceneParameters(scene, this.scene_parameters[index + 1]);
            this.scene_name_getter.setSceneName("New Scene");
            this.scene_name_getter.setVisible(true);
            this.selected_index = index + 1;
        }
        else {
            System.out.println("SceneList: MAX_NUM_SCENES reached.");
        }
    }
    
    public void replaceScene(final Scene scene, final int index) {
        this.selected_index = index;
        if (index >= 0 && index < 299) {
            scene.generatePreviewImage();
            final String old_scene_name = this.scene_parameters[index].scene_name;
            this.extractSceneParameters(scene, this.scene_parameters[index]);
            this.index = index - 1;
            this.scene_name_getter.setSceneName(old_scene_name);
            this.scene_name_getter.setVisible(true);
        }
        else {
            System.out.println("SceneList: MAX_NUM_SCENES reached.");
        }
    }
    
    public void moveUp(final int index) {
        this.selected_index = index;
        final SceneParameter old = new SceneParameter();
        if (index > 0 && index < this.number_of_entries) {
            this.copySceneParameters(this.scene_parameters[index], old);
            this.copySceneParameters(this.scene_parameters[index - 1], this.scene_parameters[index]);
            this.copySceneParameters(old, this.scene_parameters[index - 1]);
            this.selected_index = index - 1;
            this.setChanged();
            this.notifyObservers(NotificationMessage.SCENE_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("SceneList: Wrong index.");
        }
    }
    
    public void moveDown(final int index) {
        this.selected_index = index;
        final SceneParameter old = new SceneParameter();
        if (index >= 0 && index < this.number_of_entries - 1) {
            this.copySceneParameters(this.scene_parameters[index], old);
            this.copySceneParameters(this.scene_parameters[index + 1], this.scene_parameters[index]);
            this.copySceneParameters(old, this.scene_parameters[index + 1]);
            this.selected_index = index + 1;
            this.setChanged();
            this.notifyObservers(NotificationMessage.SCENE_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("SceneList: Wrong index.");
        }
    }
    
    public void removeListeEntry(final int index) {
        this.selected_index = index;
        if (index >= 0 && index < this.number_of_entries && this.number_of_entries > 0) {
            for (int i = index; i < this.number_of_entries; ++i) {
                this.copySceneParameters(this.scene_parameters[i + 1], this.scene_parameters[i]);
            }
            --this.number_of_entries;
            if (this.number_of_entries == 0) {
                this.selected_index = index - 1;
            }
            else if (index == 0) {
                this.selected_index = 0;
            }
            else {
                this.selected_index = index - 1;
            }
            this.setChanged();
            this.notifyObservers(NotificationMessage.SCENE_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("SceneList: Wrong index or list already empty");
        }
    }
    
    public void clearList() {
        this.number_of_entries = 0;
        this.selected_index = -1;
        this.setChanged();
        this.notifyObservers(NotificationMessage.SCENE_LIST_CHANGED_NOTIFICATION);
    }
    
    public void setSceneName(final String scene_name) {
        this.scene_parameters[this.index + 1].scene_name = scene_name;
        this.setChanged();
        this.notifyObservers(NotificationMessage.SCENE_LIST_CHANGED_NOTIFICATION);
    }
    
    public SceneParameter getSceneParameter(final int index) {
        if (index >= 0 && index < 300) {
            return this.scene_parameters[index];
        }
        System.out.println("SceneList: Wrong scene index.");
        return new SceneParameter();
    }
    
    public ThumbnailListEntry[] getSceneListEntries() {
        final ThumbnailListEntry[] result = new ThumbnailListEntry[this.number_of_entries];
        for (int i = 0; i < this.number_of_entries; ++i) {
            result[i] = new ThumbnailListEntry(this.scene_parameters[i].scene_name, this.scene_parameters[i].preview_image, this.scene_parameters[i].size);
        }
        return result;
    }
    
    public String[] getSceneListEntrieNames() {
        final String[] result = new String[this.number_of_entries];
        for (int i = 0; i < this.number_of_entries; ++i) {
            result[i] = this.scene_parameters[i].scene_name;
        }
        return result;
    }
    
    public int getNumberOfEntries() {
        return this.number_of_entries;
    }
    
    public int getSelectedIndex() {
        return this.selected_index;
    }
    
    public void saveSceneList(final Component cmpt) {
        final JFileChooser fc = new JFileChooser();
        final int return_val = fc.showSaveDialog(cmpt);
        if (return_val == 0) {
            try {
                final File file = fc.getSelectedFile();
                if (!file.exists()) {
                    file.createNewFile();
                }
                final Properties scene_list_properties = new Properties();
                this.addSceneListProperties(scene_list_properties);
                scene_list_properties.store(new FileOutputStream(file), "Glediator Scene List File");
            }
            catch (Exception ex) {
                System.out.println("SceneList: Error during saving scene list.");
            }
        }
    }
    
    public void loadSceneList(final Component cmpt) {
        final JFileChooser fc = new JFileChooser();
        final int return_val = fc.showOpenDialog(cmpt);
        if (return_val == 0) {
            try {
                final File file = fc.getSelectedFile();
                if (!file.exists()) {
                    file.createNewFile();
                }
                final Properties scene_list_properties = new Properties();
                scene_list_properties.load(new FileInputStream(file));
                this.extractSceneListProperties(scene_list_properties);
            }
            catch (Exception ex) {
                System.out.println("SceneList: Error during reading scene list.");
            }
        }
    }
    
    public void addSceneListProperties(final Properties scene_list_properties) {
        String prop = "SL_File_Type";
        String val = "Scene_List_File";
        scene_list_properties.setProperty(prop, val);
        prop = "SL_Number_Of_Entries";
        val = "" + this.number_of_entries;
        scene_list_properties.setProperty(prop, val);
        prop = "SL_Number_Of_Generators";
        val = "5";
        scene_list_properties.setProperty(prop, val);
        for (int i = 0; i < this.number_of_entries; ++i) {
            this.scene_parameters[i].addSceneParameter("SL", scene_list_properties, i);
        }
    }
    
    public void extractSceneListProperties(final Properties scene_list_properties) {
        String prop = "SL_File_Type";
        String val = scene_list_properties.getProperty(prop);
        if (val.equals("Scene_List_File")) {
            prop = "SL_Number_Of_Entries";
            val = scene_list_properties.getProperty(prop);
            this.number_of_entries = Integer.parseInt(val);
            for (int i = 0; i < this.number_of_entries; ++i) {
                this.scene_parameters[i].extractSceneParameter("SL", i, scene_list_properties);
            }
            this.setChanged();
            this.notifyObservers(NotificationMessage.SCENE_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("SceneList: Wrong file type.");
        }
    }
    
    public void extractSceneParameters(final Scene scene, final SceneParameter scene_parameter) {
        scene_parameter.size[0] = scene.getMatrixSize()[0];
        scene_parameter.size[1] = scene.getMatrixSize()[1];
        for (int i = 0; i < 5; ++i) {
            scene_parameter.genertator_types[i] = scene.generators[i].getGeneratorType();
            scene_parameter.generator_parameters[i] = scene.generators[i].getParameterString();
            scene_parameter.level_values[i] = scene.getLevel(i);
            scene_parameter.speed_values[i] = scene.getGeneratorSpeed(i);
            scene_parameter.filter_modi[i] = scene.filters[i].getFilterMode();
            scene_parameter.mixer_modi[i] = scene.getMixerMode(i);
        }
        scene_parameter.effect_mode = scene.effect.getEffectMode();
        scene_parameter.effect_direction = scene.effect.getEffectDirection();
        scene_parameter.effect_methode = scene.effect.getEffectMethode();
        scene_parameter.effect_value = scene.effect.getValue();
        scene_parameter.effect_lower_limit = scene.effect.getLowerLimit();
        scene_parameter.effect_upper_limit = scene.effect.getUpperLimit();
        scene_parameter.effect_timer_value = scene.effect.getTimerValue();
        scene_parameter.effect_is_active = scene.effect.getActiveState();
        final int size = scene_parameter.size[0] * scene_parameter.size[1];
        scene_parameter.preview_image = new Color[size];
        for (int j = 0; j < size; ++j) {
            scene_parameter.preview_image[j] = new Color(scene.preview_image[j].getRGB());
        }
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
}
