// 
// Decompiled by Procyon v0.5.36
// 

package PlayList;

import Fader.Fader;
import java.awt.Color;
import Effect.Effect;
import Mixer.Mixer;
import Filter.Filter;
import Generator.SuperGenerator;
import SceneList.SceneParameter;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.JFileChooser;
import java.awt.Component;
import SceneList.ThumbnailListEntry;
import Main.NotificationMessage;
import java.util.Observable;

public class PlayList extends Observable
{
    public static final int MAX_NUM_ENTRIES = 300;
    public static final String FILE_TYPE = "Play_List_File";
    PlayListItem[] play_list_items;
    int selected_index;
    int number_of_entries;
    
    public PlayList() {
        this.play_list_items = new PlayListItem[300];
        for (int i = 0; i < 300; ++i) {
            this.play_list_items[i] = new PlayListItem();
        }
        this.number_of_entries = 0;
    }
    
    public void addEntry(final PlayListItem play_list_item, final int index) {
        this.selected_index = index;
        if (index < 299) {
            if (this.number_of_entries > 0) {
                for (int i = this.number_of_entries; i > index; --i) {
                    this.copyPlayListEntry(this.play_list_items[i - 1], this.play_list_items[i]);
                }
            }
            ++this.number_of_entries;
            this.copyPlayListEntry(play_list_item, this.play_list_items[index + 1]);
            this.selected_index = index + 1;
            this.setChanged();
            this.notifyObservers(NotificationMessage.PLAY_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("PlayList: MAX_NUM_ENTRIES reached.");
        }
    }
    
    public void replaceEntry(final PlayListItem play_list_item, final int index) {
        this.selected_index = index;
        if (index >= 0 && index < 299) {
            this.copyPlayListEntry(play_list_item, this.play_list_items[index]);
            this.setChanged();
            this.notifyObservers(NotificationMessage.PLAY_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("PlayList: MAX_NUM_ITEMS reached.");
        }
    }
    
    public void moveUp(final int index) {
        this.selected_index = index;
        final PlayListItem old = new PlayListItem();
        if (index > 0 && index < this.number_of_entries) {
            this.copyPlayListEntry(this.play_list_items[index], old);
            this.copyPlayListEntry(this.play_list_items[index - 1], this.play_list_items[index]);
            this.copyPlayListEntry(old, this.play_list_items[index - 1]);
            this.selected_index = index - 1;
            this.setChanged();
            this.notifyObservers(NotificationMessage.PLAY_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("PlayList: Wrong index.");
        }
    }
    
    public void moveDown(final int index) {
        this.selected_index = index;
        final PlayListItem old = new PlayListItem();
        if (index > 0 && index < this.number_of_entries) {
            this.copyPlayListEntry(this.play_list_items[index], old);
            this.copyPlayListEntry(this.play_list_items[index + 1], this.play_list_items[index]);
            this.copyPlayListEntry(old, this.play_list_items[index + 1]);
            this.selected_index = index + 1;
            this.setChanged();
            this.notifyObservers(NotificationMessage.PLAY_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("PlayList: Wrong index.");
        }
    }
    
    public void removeListeEntry(final int index) {
        this.selected_index = index;
        if (index >= 0 && index < this.number_of_entries && this.number_of_entries > 0) {
            for (int i = index; i < this.number_of_entries; ++i) {
                this.copyPlayListEntry(this.play_list_items[i + 1], this.play_list_items[i]);
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
            this.notifyObservers(NotificationMessage.PLAY_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("PlayList: Wrong index or list already empty");
        }
    }
    
    public void clearList() {
        this.number_of_entries = 0;
        this.selected_index = -1;
        this.setChanged();
        this.notifyObservers(NotificationMessage.PLAY_LIST_CHANGED_NOTIFICATION);
    }
    
    public PlayListItem getEntry(final int index) {
        final PlayListItem result = new PlayListItem();
        if (index >= 0 && index < this.number_of_entries) {
            this.copyPlayListEntry(this.play_list_items[index], result);
        }
        else {
            System.out.println("PlayList: Wrong entry index.");
        }
        return result;
    }
    
    public ThumbnailListEntry[] getPlayListEntries() {
        final ThumbnailListEntry[] result = new ThumbnailListEntry[this.number_of_entries];
        for (int i = 0; i < this.number_of_entries; ++i) {
            result[i] = new ThumbnailListEntry(this.play_list_items[i].generateName(), this.play_list_items[i].getSceneParameter().preview_image, this.play_list_items[i].getSceneParameter().size);
        }
        return result;
    }
    
    public int getNumberOfEntries() {
        return this.number_of_entries;
    }
    
    public int getSelectedIndex() {
        return this.selected_index;
    }
    
    public void savePlayList(final Component cmpt) {
        final JFileChooser fc = new JFileChooser();
        final int return_val = fc.showSaveDialog(cmpt);
        if (return_val == 0) {
            try {
                final File file = fc.getSelectedFile();
                if (!file.exists()) {
                    file.createNewFile();
                }
                final Properties play_list_properties = new Properties();
                this.addPlayListProperties(play_list_properties);
                play_list_properties.store(new FileOutputStream(file), "Play_List_File");
            }
            catch (Exception ex) {
                System.out.println("PlayList: Error during saving play list.");
            }
        }
    }
    
    public void loadPlayList(final Component cmpt) {
        final JFileChooser fc = new JFileChooser();
        final int return_val = fc.showOpenDialog(cmpt);
        if (return_val == 0) {
            try {
                final File file = fc.getSelectedFile();
                if (!file.exists()) {
                    file.createNewFile();
                }
                final Properties play_list_properties = new Properties();
                play_list_properties.load(new FileInputStream(file));
                this.extractPlayListProperties(play_list_properties);
            }
            catch (Exception ex) {
                System.out.println("SceneList: Error during reading scene list: " + ex);
            }
        }
    }
    
    public void addPlayListProperties(final Properties play_list_properties) {
        String prop = "PL_File_Type";
        String val = "Play_List_File";
        play_list_properties.setProperty(prop, val);
        prop = "PL_Number_Of_Entries";
        val = "" + this.number_of_entries;
        play_list_properties.setProperty(prop, val);
        prop = "PL_Number_Of_Generators";
        val = "5";
        play_list_properties.setProperty(prop, val);
        for (int i = 0; i < this.number_of_entries; ++i) {
            final SceneParameter sp = this.play_list_items[i].getSceneParameter();
            sp.addSceneParameter("PL", play_list_properties, i);
            prop = "PL_Scene_(" + i + ")_Scene_Time";
            val = "" + this.play_list_items[i].getSceneTime();
            play_list_properties.setProperty(prop, val);
            prop = "PL_Scene_(" + i + ")_Fade_Time";
            val = "" + this.play_list_items[i].getFadeTime();
            play_list_properties.setProperty(prop, val);
            prop = "PL_Scene_(" + i + ")_Fade_Mode";
            val = "" + this.play_list_items[i].getFaderMode();
            play_list_properties.setProperty(prop, val);
        }
    }
    
    public void extractPlayListProperties(final Properties play_list_properties) {
        String prop = "PL_File_Type";
        String val = play_list_properties.getProperty(prop);
        if (val.equals("Play_List_File")) {
            prop = "PL_Number_Of_Entries";
            val = play_list_properties.getProperty(prop);
            this.number_of_entries = Integer.parseInt(val);
            for (int i = 0; i < this.number_of_entries; ++i) {
                final SceneParameter sp = new SceneParameter();
                prop = "PL_Scene_(" + i + ")_Size_X";
                val = play_list_properties.getProperty(prop);
                sp.size[0] = Integer.parseInt(val);
                prop = "PL_Scene_(" + i + ")_Size_Y";
                val = play_list_properties.getProperty(prop);
                sp.size[1] = Integer.parseInt(val);
                for (int j = 0; j < 5; ++j) {
                    prop = "PL_Scene_(" + i + ")_Generator_(" + j + ")_Type";
                    val = play_list_properties.getProperty(prop);
                    sp.genertator_types[j] = SuperGenerator.GeneratorTypes.valueOf(val.toUpperCase());
                    prop = "PL_Scene_(" + i + ")_Generator_(" + j + ")_Parameter";
                    val = play_list_properties.getProperty(prop);
                    sp.generator_parameters[j] = val;
                    prop = "PL_Scene_(" + i + ")_Generator_(" + j + ")_Level";
                    val = play_list_properties.getProperty(prop);
                    sp.level_values[j] = Integer.parseInt(val);
                    prop = "PL_Scene_(" + i + ")_Generator_(" + j + ")_Speed";
                    val = play_list_properties.getProperty(prop);
                    sp.speed_values[j] = Integer.parseInt(val);
                    prop = "PL_Scene_(" + i + ")_Generator_(" + j + ")_Filter";
                    val = play_list_properties.getProperty(prop);
                    sp.filter_modi[j] = Filter.FilterMode.valueOf(val.toUpperCase());
                    prop = "PL_Scene_(" + i + ")_Generator_(" + j + ")_Mixer";
                    val = play_list_properties.getProperty(prop);
                    sp.mixer_modi[j] = Mixer.MixerMode.valueOf(val.toUpperCase());
                }
                prop = "PL_Scene_(" + i + ")_Effect_Mode";
                val = play_list_properties.getProperty(prop);
                sp.effect_mode = Effect.EffectMode.valueOf(val.toUpperCase());
                prop = "PL_Scene_(" + i + ")_Effect_Direction";
                val = play_list_properties.getProperty(prop);
                sp.effect_direction = Effect.EffectDirection.valueOf(val.toUpperCase());
                prop = "PL_Scene_(" + i + ")_Effect_Methode";
                val = play_list_properties.getProperty(prop);
                sp.effect_methode = Effect.EffectMethode.valueOf(val.toUpperCase());
                prop = "PL_Scene_(" + i + ")_Effect_Value";
                val = play_list_properties.getProperty(prop);
                sp.effect_value = Integer.parseInt(val);
                prop = "PL_Scene_(" + i + ")_Effect_Lower_Limit";
                val = play_list_properties.getProperty(prop);
                sp.effect_lower_limit = Integer.parseInt(val);
                prop = "PL_Scene_(" + i + ")_Effect_Upper_Limit";
                val = play_list_properties.getProperty(prop);
                sp.effect_upper_limit = Integer.parseInt(val);
                prop = "PL_Scene_(" + i + ")_Effect_Timer_Value";
                val = play_list_properties.getProperty(prop);
                sp.effect_timer_value = Integer.parseInt(val);
                prop = "PL_Scene_(" + i + ")_Effect_Status";
                val = play_list_properties.getProperty(prop);
                sp.effect_is_active = Boolean.parseBoolean(val);
                prop = "PL_Scene_(" + i + ")_Preview_Image_Size";
                val = play_list_properties.getProperty(prop);
                final int preview_image_size = Integer.parseInt(val);
                sp.preview_image = new Color[preview_image_size];
                for (int k = 0; k < preview_image_size; ++k) {
                    prop = "PL_Scene_(" + i + ")_PreviewImage_(" + k + ")_RGB";
                    val = play_list_properties.getProperty(prop);
                    sp.preview_image[k] = new Color(Integer.parseInt(val));
                }
                prop = "PL_Scene_(" + i + ")_Name";
                val = play_list_properties.getProperty(prop);
                sp.scene_name = val;
                this.play_list_items[i].setSceneParameter(sp);
                prop = "PL_Scene_(" + i + ")_Scene_Time";
                val = play_list_properties.getProperty(prop, val);
                this.play_list_items[i].setSceneTime(Integer.parseInt(val));
                prop = "PL_Scene_(" + i + ")_Fade_Time";
                val = play_list_properties.getProperty(prop, val);
                this.play_list_items[i].setFadeTime(Integer.parseInt(val));
                prop = "PL_Scene_(" + i + ")_Fade_Mode";
                val = play_list_properties.getProperty(prop, val);
                this.play_list_items[i].setFaderMode(Fader.FaderMode.valueOf(val.toUpperCase()));
            }
            this.setChanged();
            this.notifyObservers(NotificationMessage.PLAY_LIST_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("SceneList: Wrong file type.");
        }
    }
    
    private void copyPlayListEntry(final PlayListItem sour, final PlayListItem dest) {
        dest.setSceneParameter(sour.getSceneParameter());
        dest.setSceneTime(sour.getSceneTime());
        dest.setFaderMode(sour.getFaderMode());
        dest.setFadeTime(sour.getFadeTime());
    }
}
