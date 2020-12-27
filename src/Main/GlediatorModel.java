// 
// Decompiled by Procyon v0.5.36
// 

package Main;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import SceneList.SceneParameter;
import Output.BaudRate;
import Output.ColorOrder;
import Mapper.MappingOrder;
import Mapper.MappingType;
import Output.OutputType;
import java.util.Properties;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Component;
import javax.swing.JFrame;
import Generator.SuperGenerator;
import javax.sound.sampled.Mixer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import Player.Player;
import Audio.Audio;
import Remote.RemoteControl;
import Options.MatrixSizeWindow;
import Options.MainOptionWindow;
import PlayList.PlayList;
import SceneList.SceneList;
import Options.Options;
import Mapper.Mapper;
import Patch.PatchWindow;
import Patch.Patcher;
import Output.Output;
import Fader.Fader;
import Scene.Scene;
import java.awt.Color;
import java.util.Observer;
import java.util.Observable;


public final class GlediatorModel extends Observable implements Observer
{
    public static final int[] DEFAULT_MATRIX_SIZE;
    public static final int DEFAULT_GAP = 1;
    public static final int DEFAULT_SPEED_VALUE = 20;
    public static final int DEFAULT_TIMER_DELAY = 40;
    public static final int DEFAULT_FADER_TIME = 1000;
    public static final String FILE_TYPE = "Glediator_Project_File";
    public Color[] left_image;
    public Color[] right_image;
    public Color[] main_image;
    public Color[] mapped_image;
    public Scene left_scene;
    public Scene right_scene;
    public Fader fader;
    public Output output;
    public Patcher patcher;
    public PatchWindow patch_gui;
    public Mapper mapper;
    public Options options;
    public SceneList scene_list;
    public PlayList play_list;
    private int frames_per_second;
    private int strobe_duration;
    private int strobe_interval;
    private int play_list_counter;
    private int fader_dir;
    private int[] frame_size;
    private int[] temp_size;
    private int[] audio_level;
    private long last_millis;
    private boolean audio_trigger;
    private boolean strobe;
    private boolean strobe_active;
    private boolean play_list_playing;
    private boolean frame_size_changed;
    private boolean options_changed;
    private boolean is_playing;
    private boolean is_recording;
    private MainOptionWindow option_window;
    private MatrixSizeWindow matrix_size_option_window;
    private RemoteControl remote_control;
    private Audio audio;
    private Player player;
    private Banner banner;
    private Timer frame_timer;
    private Timer fader_timer;
    private Timer black_out_timer;
    private Timer strobe_timer;
    private Timer play_list_timer;
    ActionListener frame_timer_task;
    ActionListener fader_timer_task;
    ActionListener black_out_timer_task;
    ActionListener strobe_timer_task;
    ActionListener play_list_timer_task;
    
    public GlediatorModel(final GlediatorView glediator_view) {
        this.frame_timer_task = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                final long current_millis = System.currentTimeMillis();
                final long passed_millis = current_millis - GlediatorModel.this.last_millis;
                GlediatorModel.this.last_millis = current_millis;
                GlediatorModel.this.frames_per_second = 0;
                if (passed_millis > 0L) {
                    GlediatorModel.this.frames_per_second = (int)Math.ceil((double)(1000L / passed_millis));
                }
                if (GlediatorModel.this.frame_size_changed) {
                    GlediatorModel.this.frame_size[0] = GlediatorModel.this.matrix_size_option_window.getSx();
                    GlediatorModel.this.frame_size[1] = GlediatorModel.this.matrix_size_option_window.getSy();
                    GlediatorModel.this.setFrameSize(GlediatorModel.this.frame_size);
                    GlediatorModel.this.frame_size_changed = false;
                }
                if (GlediatorModel.this.options_changed) {
                    GlediatorModel.this.applyNewOptions();
                    GlediatorModel.this.options_changed = false;
                }
                GlediatorModel.this.generateNextImages();
               
               // Observable.this.setChanged();
                GlediatorModel.super.setChanged();
                GlediatorModel.this.notifyObservers(NotificationMessage.TIMER_UPDATE_NOTIFICATION);
            }
        };
        this.fader_timer_task = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                int fader_value = GlediatorModel.this.fader.getFaderValue();
                if (GlediatorModel.this.fader_dir == 0) {
                    --fader_value;
                    GlediatorModel.this.fader.setFaderValue(fader_value);
                   // Observable.this.setChanged();
                    GlediatorModel.super.setChanged();
                    GlediatorModel.this.notifyObservers(NotificationMessage.FADER_VALUE_UPDATE_NOTIFICATION);
                    if (fader_value <= 0) {
                        GlediatorModel.this.fader_timer.setRepeats(false);
                        GlediatorModel.this.fader_timer.stop();
                       // Observable.this.setChanged();
                        GlediatorModel.super.setChanged();
                        GlediatorModel.this.notifyObservers(NotificationMessage.FADER_REACHED_ENDPOSITION_NOTIFICATION);
                        if (GlediatorModel.this.play_list_playing) {
                            GlediatorModel.this.play_list_timer.setRepeats(false);
                            GlediatorModel.this.play_list_timer.stop();
                            if (GlediatorModel.this.play_list_counter < GlediatorModel.this.play_list.getNumberOfEntries() - 1) {
                                GlediatorModel.this.play_list_counter++;
                            }
                            else {
                                GlediatorModel.this.play_list_counter = 0;
                            }
                            GlediatorModel.this.play_list_timer.setInitialDelay(GlediatorModel.this.play_list.getEntry(GlediatorModel.this.play_list_counter).getSceneTime());
                            if (GlediatorModel.this.play_list_counter < GlediatorModel.this.play_list.getNumberOfEntries() - 1) {
                                GlediatorModel.this.right_scene.setScene(GlediatorModel.this.play_list.getEntry(GlediatorModel.this.play_list_counter + 1).getSceneParameter());
                            }
                            else {
                                GlediatorModel.this.right_scene.setScene(GlediatorModel.this.play_list.getEntry(0).getSceneParameter());
                            }
                          //  Observable.this.setChanged();
                            GlediatorModel.super.setChanged();
                            GlediatorModel.this.notifyObservers(NotificationMessage.SCENE_CHANGED_NOTIFICATION);
                            GlediatorModel.this.play_list_timer.setRepeats(false);
                            GlediatorModel.this.play_list_timer.start();
                        }
                    }
                }
                if (GlediatorModel.this.fader_dir == 1) {
                    ++fader_value;
                    GlediatorModel.this.fader.setFaderValue(fader_value);
                  //  Observable.this.setChanged();
                    GlediatorModel.super.setChanged();
                    GlediatorModel.this.notifyObservers(NotificationMessage.FADER_VALUE_UPDATE_NOTIFICATION);
                    if (fader_value >= 100) {
                        GlediatorModel.this.fader_timer.setRepeats(false);
                        GlediatorModel.this.fader_timer.stop();
                      //  Observable.this.setChanged();
                        GlediatorModel.super.setChanged();
                        GlediatorModel.this.notifyObservers(NotificationMessage.FADER_REACHED_ENDPOSITION_NOTIFICATION);
                        if (GlediatorModel.this.play_list_playing) {
                            GlediatorModel.this.play_list_timer.setRepeats(false);
                            GlediatorModel.this.play_list_timer.stop();
                            if (GlediatorModel.this.play_list_counter < GlediatorModel.this.play_list.getNumberOfEntries() - 1) {
                                GlediatorModel.this.play_list_counter++;
                            }
                            else {
                                GlediatorModel.this.play_list_counter = 0;
                            }
                            GlediatorModel.this.play_list_timer.setInitialDelay(GlediatorModel.this.play_list.getEntry(GlediatorModel.this.play_list_counter).getSceneTime());
                            if (GlediatorModel.this.play_list_counter < GlediatorModel.this.play_list.getNumberOfEntries() - 1) {
                                GlediatorModel.this.left_scene.setScene(GlediatorModel.this.play_list.getEntry(GlediatorModel.this.play_list_counter + 1).getSceneParameter());
                            }
                            else {
                                GlediatorModel.this.left_scene.setScene(GlediatorModel.this.play_list.getEntry(0).getSceneParameter());
                            }
                           // Observable.this.setChanged();
                            GlediatorModel.super.setChanged();
                            GlediatorModel.this.notifyObservers(NotificationMessage.SCENE_CHANGED_NOTIFICATION);
                            GlediatorModel.this.play_list_timer.setRepeats(false);
                            GlediatorModel.this.play_list_timer.start();
                        }
                    }
                }
            }
        };
        this.black_out_timer_task = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                int main_level_value = GlediatorModel.this.fader.getMasterIntensity();
                if (--main_level_value >= 0) {
                    GlediatorModel.this.fader.setMasterIntensity(main_level_value);
                  //  Observable.this.setChanged();
                    GlediatorModel.super.setChanged();
                    GlediatorModel.this.notifyObservers(NotificationMessage.MASTER_DIMMER_CHANGED_NOTIFICATION);
                }
                else {
                    main_level_value = 0;
                    GlediatorModel.this.fader.setMasterIntensity(0);
                    GlediatorModel.this.black_out_timer.setRepeats(false);
                    GlediatorModel.this.black_out_timer.stop();
                  //  Observable.this.setChanged();
                    GlediatorModel.super.setChanged();
                    GlediatorModel.this.notifyObservers(NotificationMessage.BLACK_OUT_FINSHED_NOTIFICATION);
                }
            }
        };
        this.strobe_timer_task = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorModel.this.strobe = !GlediatorModel.this.strobe;
                if (!GlediatorModel.this.strobe) {
                    GlediatorModel.this.strobe_timer.setDelay(GlediatorModel.this.strobe_duration);
                }
                else {
                    GlediatorModel.this.strobe_timer.setDelay(GlediatorModel.this.strobe_interval);
                }
            }
        };
        this.play_list_timer_task = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                final int fade_time = GlediatorModel.this.play_list.getEntry(GlediatorModel.this.play_list_counter).getFadeTime();
                GlediatorModel.this.fader.setFadeTime(fade_time);
                GlediatorModel.this.fader.setFaderMode(GlediatorModel.this.play_list.getEntry(GlediatorModel.this.play_list_counter).getFaderMode());
                GlediatorModel.this.startFading();
               // Observable.this.setChanged();
                GlediatorModel.super.setChanged();                
                GlediatorModel.this.notifyObservers(NotificationMessage.AUTO_FADER_ACTIVATED_NOTIFICATION);
            }
        };
        this.left_scene = new Scene(GlediatorModel.DEFAULT_MATRIX_SIZE, 20);
        this.right_scene = new Scene(GlediatorModel.DEFAULT_MATRIX_SIZE, 20);
        this.fader = new Fader();
        this.scene_list = new SceneList();
        this.play_list = new PlayList();
        this.audio = new Audio();
        this.mapper = new Mapper();
        this.output = new Output();
        this.patcher = new Patcher(this);
        this.patch_gui = new PatchWindow(this);
        this.matrix_size_option_window = new MatrixSizeWindow(this);
        this.options = new Options();
        this.option_window = new MainOptionWindow(this);
        this.player = new Player(this);
        this.is_playing = false;
        this.is_recording = false;
        this.banner = new Banner();
        this.remote_control = new RemoteControl(this);
        this.frame_size = GlediatorModel.DEFAULT_MATRIX_SIZE;
        this.temp_size = new int[2];
        this.setFrameSize(this.frame_size);
        this.scene_list.addObserver(this);
        this.play_list.addObserver(this);
        this.strobe_active = false;
        this.strobe = true;
        (this.strobe_timer = new Timer(1, this.frame_timer_task)).setRepeats(false);
        this.strobe_timer.stop();
        (this.play_list_timer = new Timer(1, this.play_list_timer_task)).setRepeats(false);
        this.play_list_timer.stop();
        this.addObserver(glediator_view);
    }
    
    public void triggerFrameSizeChange() {
        this.frame_size_changed = true;
    }
    
    public void triggerOptionChange() {
        this.options_changed = true;
    }
    
    public int[] getFrameSize() {
        return this.frame_size;
    }
    
    public int getGap() {
        return this.matrix_size_option_window.getGap();
    }
    
    public int getFPS() {
        return this.frames_per_second;
    }
    
    public int[] getAudioLevel() {
        final int[] result = { this.audio_level[0], this.audio_level[1] };
        return result;
    }
    
    public boolean getAudioTriggerState() {
        return this.audio_trigger;
    }
    
    public String[] getAudioSources() {
        final Mixer.Info[] mixerInfo = this.audio.GetMixerInfo();
        String[] result;
        if (mixerInfo.length <= 0) {
            result = new String[0];
        }
        else {
            result = new String[mixerInfo.length];
            for (int i = 0; i < mixerInfo.length; ++i) {
                result[i] = mixerInfo[i].getName();
            }
        }
        return result;
    }
    
    public void startAudioCapture(final int index) {
        this.audio.SetAudioFormat();
        this.audio.Set_and_Start_Mixer(index);
    }
    
    public void stopAudioCapture() {
        this.audio.Stop_Mixer();
    }
    
    public void setAudioTriggerLevel(final int trigger_level) {
        SuperGenerator.audio_properties.audio_trigger_level = trigger_level;
    }
    
    public void setAudioGain(final int gain) {
        SuperGenerator.audio_properties.gain = gain;
    }
    
    public void start() {
    	// Set speed of interface
        (this.frame_timer = new Timer(30, this.frame_timer_task)).setRepeats(true);
        this.frame_timer.start();
        //this.frame_timer.start();
    }
    
    public void startFading() {
        final int fader_value = this.fader.getFaderValue();
        if (fader_value < 50) {
            this.fader_dir = 1;
        }
        else {
            this.fader_dir = 0;
        }
        (this.fader_timer = new Timer(this.fader.getFaderTime(), this.fader_timer_task)).setRepeats(true);
        this.fader_timer.start();
    }
    
    public void startBlackOut() {
        (this.black_out_timer = new Timer(this.fader.getFaderTime(), this.black_out_timer_task)).setRepeats(true);
        this.black_out_timer.start();
    }
    
    public void activateStrobe() {
        (this.strobe_timer = new Timer(this.strobe_interval, this.strobe_timer_task)).setRepeats(true);
        this.strobe_timer.start();
        this.strobe_active = true;
    }
    
    public void deactivateStrobe() {
        this.strobe_timer.setRepeats(false);
        this.strobe_timer.stop();
        this.strobe = true;
        this.strobe_active = false;
    }
    
    public void startPlayList() {
        this.play_list_counter = 0;
        this.fader.setFaderValue(0);
        this.left_scene.setScene(this.play_list.getEntry(this.play_list_counter).getSceneParameter());
        this.right_scene.setScene(this.play_list.getEntry(this.play_list_counter + 1).getSceneParameter());
        this.setChanged();
        this.notifyObservers(NotificationMessage.SCENE_CHANGED_NOTIFICATION);
        this.play_list_playing = true;
        (this.play_list_timer = new Timer(this.play_list.getEntry(0).getSceneTime(), this.play_list_timer_task)).setRepeats(false);
        this.play_list_timer.start();
    }
    
    public void stopPlayList() {
        this.play_list_timer.setRepeats(false);
        this.play_list_timer.stop();
        this.play_list_playing = false;
    }
    
    public boolean isPlayListPlaying() {
        return this.play_list_playing;
    }
    
    public void setStrobeDuration(final int strobe_duration) {
        if (strobe_duration > 0) {
            this.strobe_duration = strobe_duration;
        }
    }
    
    public int getStrobeDuration() {
        return this.strobe_duration;
    }
    
    public void setStrobeInterval(final int strobe_interval) {
        if (strobe_interval > 0) {
            this.strobe_interval = strobe_interval;
        }
    }
    
    public int getStrobeInterval() {
        return this.strobe_interval;
    }
    
    public boolean getStrobeStatus() {
        return this.strobe_active;
    }
    
    public void setRecordingState(final Boolean recording_state) {
        this.is_recording = recording_state;
    }
    
    public void setPlayingState(final Boolean playing_state) {
        this.is_playing = playing_state;
    }
    
    public boolean getPlayingState() {
        return this.is_playing;
    }
    
    public void showPlayer(final JFrame frame) {
        final int x = (2 * frame.getX() + frame.getWidth()) / 2 - this.player.getWidth() / 2;
        final int y = (2 * frame.getY() + frame.getHeight()) / 2 - this.player.getHeight() / 2;
        this.player.setLocation(x, y);
        this.player.setVisible(true);
    }
    
    public void showMatrixSizeWindow(final JFrame frame) {
        final int x = (2 * frame.getX() + frame.getWidth()) / 2 - this.matrix_size_option_window.getWidth() / 2;
        final int y = (2 * frame.getY() + frame.getHeight()) / 2 - this.matrix_size_option_window.getHeight() / 2;
        this.matrix_size_option_window.setLocation(x, y);
        this.matrix_size_option_window.setVisible(true);
    }
    
    public void showRemoteControl(final JFrame frame) {
        final int x = (2 * frame.getX() + frame.getWidth()) / 2 - this.matrix_size_option_window.getWidth() / 2;
        final int y = (2 * frame.getY() + frame.getHeight()) / 2 - this.matrix_size_option_window.getHeight() / 2;
        this.remote_control.setLocation(x, y);
        this.remote_control.setVisible(true);
    }
    
    public void setMatrixSizeEditable(final boolean value) {
        this.matrix_size_option_window.setEditable(value);
    }
    
    public void showOptionWindow(final JFrame frame) {
        final int x = (2 * frame.getX() + frame.getWidth()) / 2 - this.option_window.getWidth() / 2;
        final int y = (2 * frame.getY() + frame.getHeight()) / 2 - this.option_window.getHeight() / 2;
        this.option_window.setLocation(x, y);
        this.option_window.setVisible(true);
    }
    
    public void showPatchWindow(final JFrame frame) {
        final int x = (2 * frame.getX() + frame.getWidth()) / 2 - this.patch_gui.getWidth() / 2;
        final int y = (2 * frame.getY() + frame.getHeight()) / 2 - this.patch_gui.getHeight() / 2;
        this.patch_gui.setLocation(x, y);
        this.patch_gui.setParameter();
        this.patch_gui.setVisible(true);
    }
    
    public void showBanner(final JFrame frame) {
        final int x = (2 * frame.getX() + frame.getWidth()) / 2 - this.banner.getWidth() / 2;
        final int y = (2 * frame.getY() + frame.getHeight()) / 2 - this.banner.getHeight() / 2;
        this.banner.setLocation(x, y);
        this.banner.setVisible(true);
    }
    
    public void saveAllAs(final Component cmpt) {
        final JFileChooser fc = new JFileChooser();
        final int return_val = fc.showSaveDialog(cmpt);
        if (return_val == 0) {
            try {
                final File file = fc.getSelectedFile();
                if (!file.exists()) {
                    file.createNewFile();
                }
                this.parseAndSaveProject(file);
            }
            catch (Exception ex) {
                System.out.println("GlediatorModel: Error during saving project.");
            }
        }
    }
    
    public void autoSave() {
        final File file = new File("autoSave.gled");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            this.parseAndSaveProject(file);
        }
        catch (Exception ex) {
            System.out.println("GlediatorModel: Error during auto-saving project.");
        }
    }
    
    public void autoLoad() {
        final File file = new File("autoSave.gled");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            this.readAndApplyProjcetSettings(file);
        }
        catch (Exception ex) {
            System.out.println("GlediatorModel: Error during auto-loading project.");
            ex.printStackTrace();
        }
    }
    
    public void loadAll(final Component cmpt) {
        final JFileChooser fc = new JFileChooser();
        final int return_val = fc.showOpenDialog(cmpt);
        if (return_val == 0) {
            try {
                final File file = fc.getSelectedFile();
                if (!file.exists()) {
                    file.createNewFile();
                }
                this.readAndApplyProjcetSettings(file);
            }
            catch (Exception ex) {
                System.out.println("GlediatorModel: Error during reading project file.");
            }
        }
    }
    
    public void triggerGuiUpdate() {
        this.setChanged();
        this.notifyObservers(NotificationMessage.UPDATE_GUI_NOTIFICATION);
        this.setChanged();
        this.notifyObservers(NotificationMessage.SCENE_CHANGED_NOTIFICATION);
    }
    
    @Override
    public void update(final Observable o, final Object o1) {
        this.setChanged();
        this.notifyObservers(o1);
    }
    
    private void generateNextImages() {
        this.audio_level = this.audio.processAudio(SuperGenerator.audio_properties);
        this.audio_trigger = (SuperGenerator.audio_properties.stereo_audio_level > SuperGenerator.audio_properties.audio_trigger_level);
        this.left_scene.effect.setSoundLevel((this.audio_level[0] + this.audio_level[1]) / 2);
        this.right_scene.effect.setSoundLevel((this.audio_level[0] + this.audio_level[1]) / 2);
        if (this.audio_trigger) {
            this.left_scene.effect.setTrigger();
            this.right_scene.effect.setTrigger();
        }
        this.left_scene.getNextImage(this.left_image);
        this.right_scene.getNextImage(this.right_image);
        if (this.strobe) {
            if (!this.is_playing) {
                this.fader.doFading(this.left_image, this.right_image, this.main_image);
            }
            else {
                this.player.readFrame(this.main_image);
            }
        }
        else {
            for (int i = 0; i < this.main_image.length; ++i) {
                this.main_image[i] = Color.BLACK;
            }
        }
        final boolean is_artnet = this.output.getArtnetStatus();
        final boolean is_tpm2_net = this.output.getTPM2netStatus();
        if (is_artnet || is_tpm2_net) {
            this.output.doOutput(this.main_image);
        }
        else {
            this.mapper.doMapping(this.main_image, this.mapped_image);
            this.output.doOutput(this.mapped_image);
        }
        if (this.is_recording) {
            this.player.saveFrame(this.main_image);
        }
    }
    
    private void setFrameSize(final int[] frame_size) {
        final int size_x = frame_size[0];
        final int size_y = frame_size[1];
        if (size_x > 0 && size_y > 0) {
            (this.frame_size = new int[2])[0] = size_x;
            this.frame_size[1] = size_y;
            final int size = size_x * size_y;
            this.left_image = new Color[size];
            this.right_image = new Color[size];
            this.main_image = new Color[size];
            this.mapped_image = new Color[size];
            for (int i = 0; i < size; ++i) {
                this.left_image[i] = Color.BLACK;
                this.right_image[i] = Color.BLACK;
                this.main_image[i] = Color.BLACK;
                this.mapped_image[i] = Color.BLACK;
            }
            this.left_scene.setMatrixSize(this.frame_size);
            this.right_scene.setMatrixSize(this.frame_size);
            this.fader.setMatrixSize(this.frame_size);
            this.options.setMatrixSize(this.frame_size);
            this.mapper.setMapping(this.options);
            this.output.configureOutput(this.options);
            this.patcher.set_size(this.frame_size);
            this.patch_gui.setParameter();
            this.setChanged();
            this.notifyObservers(NotificationMessage.SIZE_CHANGED_NOTIFICATION);
        }
        else {
            System.out.println("GlediatorModel: Wrong matrix size value(s).");
        }
    }
    
    private void applyNewOptions() {
        this.mapper.setMapping(this.options);
        this.output.configureOutput(this.options);
    }
    
    private void addMainProperties(final Properties main_properties) {
        String prop = "MO_File_Type";
        String val = "Glediator_Project_File";
        main_properties.setProperty(prop, val);
        prop = "MO_Master_Fader_Type";
        val = this.fader.getFaderMode().toString();
        main_properties.setProperty(prop, val);
        prop = "MO_Master_Fader_Value";
        val = "" + this.fader.getFaderValue();
        main_properties.setProperty(prop, val);
        prop = "MO_Master_Fader_Time";
        val = "" + this.fader.getFaderTime();
        main_properties.setProperty(prop, val);
        prop = "MO_Master_Fader_Left_Intensity";
        val = "" + this.fader.getLeftIntensity();
        main_properties.setProperty(prop, val);
        prop = "MO_Master_Fader_Right_Intensity";
        val = "" + this.fader.getRightIntensity();
        main_properties.setProperty(prop, val);
        prop = "MO_Master_Fader_Master_Intensity";
        val = "" + this.fader.getMasterIntensity();
        main_properties.setProperty(prop, val);
        prop = "MO_Master_Strobe_Status";
        val = "" + this.strobe_timer.isRunning();
        main_properties.setProperty(prop, val);
        prop = "MO_Master_Strobe_Interval";
        val = "" + this.strobe_interval;
        main_properties.setProperty(prop, val);
        prop = "MO_Master_Strobe_Duration";
        val = "" + this.strobe_duration;
        main_properties.setProperty(prop, val);
        prop = "MO_Audio_Gain";
        val = "" + SuperGenerator.audio_properties.gain;
        main_properties.setProperty(prop, val);
        prop = "MO_Audio_Trigger_Level";
        val = "" + SuperGenerator.audio_properties.audio_trigger_level;
        main_properties.setProperty(prop, val);
        prop = "MO_Playlist_Playing";
        val = "" + this.play_list_playing;
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Protocol_Type";
        val = "" + this.options.getOutputType().toString();
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Mapping_Type";
        val = "" + this.options.getMappingType().toString();
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Pixel_Order";
        val = "" + this.options.getPixelOrder().toString();
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Board_Order";
        val = "" + this.options.getBoardOrder().toString();
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Color_Order";
        val = "" + this.options.getColorOrder().toString();
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Baud_Rate";
        val = "" + this.options.getBaudRate().toString();
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Board_Size_X";
        val = "" + this.options.getBoardSize()[0];
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Board_Size_Y";
        val = "" + this.options.getBoardSize()[1];
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Num_Boards_X";
        val = "" + this.options.getNumberOfBoards()[0];
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Num_Boards_Y";
        val = "" + this.options.getNumberOfBoards()[1];
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Matrix_Size_X";
        val = "" + this.options.getMatrixSize()[0];
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Matrix_Size_Y";
        val = "" + this.options.getMatrixSize()[1];
        main_properties.setProperty(prop, val);
        prop = "MO_Output_Matrix_Gap";
        val = "" + this.matrix_size_option_window.getGap();
        main_properties.setProperty(prop, val);
    }
    
    private void extractMainProperties(final Properties main_properties) {
        String prop = "MO_Master_Fader_Type";
        String val = main_properties.getProperty(prop);
        this.fader.setFaderMode(Fader.FaderMode.valueOf(val.toUpperCase()));
        prop = "MO_Master_Fader_Value";
        val = main_properties.getProperty(prop);
        this.fader.setFaderValue(Integer.parseInt(val));
        prop = "MO_Master_Fader_Time";
        val = main_properties.getProperty(prop);
        this.fader.setFadeTime(Integer.parseInt(val));
        prop = "MO_Master_Fader_Left_Intensity";
        val = main_properties.getProperty(prop);
        this.fader.setLeftIntensity(Integer.parseInt(val));
        prop = "MO_Master_Fader_Right_Intensity";
        val = main_properties.getProperty(prop);
        this.fader.setRightIntensity(Integer.parseInt(val));
        prop = "MO_Master_Fader_Master_Intensity";
        val = main_properties.getProperty(prop);
        this.fader.setMasterIntensity(Integer.parseInt(val));
        prop = "MO_Master_Strobe_Interval";
        val = main_properties.getProperty(prop);
        this.strobe_interval = Integer.parseInt(val);
        prop = "MO_Master_Strobe_Duration";
        val = main_properties.getProperty(prop);
        this.strobe_duration = Integer.parseInt(val);
        prop = "MO_Master_Strobe_Status";
        val = main_properties.getProperty(prop);
        if (Boolean.parseBoolean(val)) {
            this.activateStrobe();
        }
        else {
            this.deactivateStrobe();
        }
        prop = "MO_Audio_Gain";
        val = main_properties.getProperty(prop);
        SuperGenerator.audio_properties.gain = Integer.parseInt(val);
        prop = "MO_Audio_Trigger_Level";
        val = main_properties.getProperty(prop);
        SuperGenerator.audio_properties.audio_trigger_level = Integer.parseInt(val);
        prop = "MO_Playlist_Playing";
        val = main_properties.getProperty(prop);
        final Boolean playing = Boolean.parseBoolean(val);
        if (playing) {
            this.startPlayList();
        }
        else {
            this.stopPlayList();
        }
        prop = "MO_Output_Protocol_Type";
        val = main_properties.getProperty(prop);
        this.options.setOutputType(OutputType.valueOf(val.toUpperCase()));
        prop = "MO_Output_Mapping_Type";
        val = main_properties.getProperty(prop);
        this.options.setMappingType(MappingType.valueOf(val.toUpperCase()));
        prop = "MO_Output_Pixel_Order";
        val = main_properties.getProperty(prop);
        this.options.setPixelOrder(MappingOrder.valueOf(val.toUpperCase()));
        prop = "MO_Output_Board_Order";
        val = main_properties.getProperty(prop);
        this.options.setBoardOrder(MappingOrder.valueOf(val.toUpperCase()));
        prop = "MO_Output_Color_Order";
        val = main_properties.getProperty(prop);
        this.options.setColorOrder(ColorOrder.valueOf(val.toUpperCase()));
        prop = "MO_Output_Baud_Rate";
        val = main_properties.getProperty(prop);
        this.options.setBaudRate(BaudRate.valueOf(val.toUpperCase()));
        final int[] board_size = new int[2];
        prop = "MO_Output_Board_Size_X";
        val = main_properties.getProperty(prop);
        board_size[0] = Integer.parseInt(val);
        prop = "MO_Output_Board_Size_Y";
        val = main_properties.getProperty(prop);
        board_size[1] = Integer.parseInt(val);
        this.options.setBoardSize(board_size);
        final int[] num_boards = new int[2];
        prop = "MO_Output_Num_Boards_X";
        val = main_properties.getProperty(prop);
        num_boards[0] = Integer.parseInt(val);
        prop = "MO_Output_Num_Boards_Y";
        val = main_properties.getProperty(prop);
        num_boards[1] = Integer.parseInt(val);
        this.options.setNumberOfBoards(num_boards);
        prop = "MO_Output_Matrix_Size_X";
        val = main_properties.getProperty(prop);
        this.temp_size[0] = Integer.parseInt(val);
        this.matrix_size_option_window.setSx(this.temp_size[0]);
        prop = "MO_Output_Matrix_Size_Y";
        val = main_properties.getProperty(prop);
        this.temp_size[1] = Integer.parseInt(val);
        this.matrix_size_option_window.setSy(this.temp_size[1]);
        prop = "MO_Output_Matrix_Gap";
        val = main_properties.getProperty(prop);
        this.matrix_size_option_window.setGap(Integer.parseInt(val));
    }
    
    private void parseAndSaveProject(final File file) throws FileNotFoundException, IOException {
        final Properties main_properties = new Properties();
        this.scene_list.addSceneListProperties(main_properties);
        this.play_list.addPlayListProperties(main_properties);
        this.left_scene.generatePreviewImage();
        this.right_scene.generatePreviewImage();
        final SceneParameter sp = new SceneParameter();
        this.scene_list.extractSceneParameters(this.left_scene, sp);
        sp.addSceneParameter("CU", main_properties, 0);
        this.scene_list.extractSceneParameters(this.right_scene, sp);
        sp.addSceneParameter("CU", main_properties, 1);
        this.addMainProperties(main_properties);
        this.patch_gui.addPatchProperties(main_properties);
        main_properties.store(new FileOutputStream(file), "Glediator Project File");
    }
    
    private void readAndApplyProjcetSettings(final File file) throws FileNotFoundException, IOException {
        final Properties project_properties = new Properties();
        project_properties.load(new FileInputStream(file));
        this.scene_list.extractSceneListProperties(project_properties);
        this.play_list.extractPlayListProperties(project_properties);
        this.extractMainProperties(project_properties);
        this.setChanged();
        this.notifyObservers(NotificationMessage.UPDATE_GUI_NOTIFICATION);
        this.patcher.set_size(this.temp_size);
        this.patch_gui.setParameter();
        this.patch_gui.extractPatchProperties(project_properties);
        final SceneParameter scene_parameter = new SceneParameter();
        scene_parameter.extractSceneParameter("CU", 0, project_properties);
        this.left_scene.setScene(scene_parameter);
        scene_parameter.extractSceneParameter("CU", 1, project_properties);
        this.right_scene.setScene(scene_parameter);
        this.options.setMatrixSize(this.temp_size);
        this.triggerOptionChange();
        this.setFrameSize(this.temp_size);
        this.setChanged();
        this.notifyObservers(NotificationMessage.SCENE_CHANGED_NOTIFICATION);
    }
    
    static {
        DEFAULT_MATRIX_SIZE = new int[] { 32, 16 };
    }
}
