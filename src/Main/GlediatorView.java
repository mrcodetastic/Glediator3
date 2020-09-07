// 
// Decompiled by Procyon v0.5.36
// 

package Main;

import SceneList.SceneParameter;
import PlayList.PlayListItem;
import javax.swing.Icon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.GridBagLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Image;
import java.util.List;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.ListCellRenderer;
import SceneList.ThumbnailListRenderer;
import java.util.Observable;
import java.awt.Dimension;
import java.awt.Toolkit;
import Fader.Fader;
import Effect.Effect;
import Mixer.Mixer;
import Filter.Filter;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import Generator.SuperGenerator;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import Draw.DrawPanel;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.util.Observer;
import java.util.Properties;

import javax.swing.JFrame;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel.*;
import com.fazecast.jSerialComm.*;


public class GlediatorView extends JFrame implements Observer
{
    static final String glediator_version = "3.0.0";
    GlediatorModel glediator_model;
    private static Color DEFAULT_BTN_COLOR;
    private static Color DEFAULT_FORE_COLOR;
    private static final Color HIGHLIGHT_COLOR;
    private int scene_index;
    private boolean block_events;
    private JComboBox cb_audio_sources;
    private JComboBox cb_effect_dir_left;
    private JComboBox cb_effect_dir_right;
    private JComboBox cb_effect_methode_left;
    private JComboBox cb_effect_methode_right;
    private JComboBox cb_effect_mode_left;
    private JComboBox cb_effect_mode_right;
    private JComboBox cb_fader_mode;
    private JComboBox cb_filter_mode_left;
    private JComboBox cb_filter_mode_right;
    private JComboBox cb_left_generator_type;
    private JComboBox cb_mixer_mode_left;
    private JComboBox cb_mixer_mode_right;
    private JComboBox cb_play_list_fader_mode;
    private JComboBox cb_right_generator_type;
    private JComboBox jComboBox6;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel18;
    private JLabel jLabel19;
    private JLabel jLabel2;
    private JLabel jLabel20;
    private JLabel jLabel21;
    private JLabel jLabel22;
    private JLabel jLabel23;
    private JLabel jLabel26;
    private JLabel jLabel3;
    private JLabel jLabel30;
    private JLabel jLabel31;
    private JLabel jLabel32;
    private JLabel jLabel33;
    private JLabel jLabel34;
    private JLabel jLabel35;
    private JLabel jLabel36;
    private JLabel jLabel37;
    private JLabel jLabel38;
    private JLabel jLabel39;
    private JLabel jLabel4;
    private JLabel jLabel40;
    private JLabel jLabel41;
    private JLabel jLabel42;
    private JLabel jLabel43;
    private JLabel jLabel44;
    private JLabel jLabel45;
    private JLabel jLabel46;
    private JLabel jLabel47;
    private JLabel jLabel48;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenu jMenu4;
    private JMenu jMenu5;
    private JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private JPanel jPanel10;
    private JPanel jPanel11;
    private JPanel jPanel12;
    private JPanel jPanel13;
    private JPanel jPanel14;
    private JPanel jPanel15;
    private JPanel jPanel16;
    private JPanel jPanel17;
    private JPanel jPanel18;
    private JPanel jPanel19;
    private JPanel jPanel20;
    private JPanel jPanel21;
    private JPanel jPanel22;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel9;
    private JProgressBar jProgressBar1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JSlider jSlider2;
    private JList jl_play_list;
    private JList jl_scenes;
    private JLabel lbl_fps;
    private JMenuItem mi_about;
    private JMenuItem mi_exit;
    private JMenuItem mi_load;
    private JMenuItem mi_matrix_size;
    private JMenuItem mi_output;
    private JMenuItem mi_recorder;
    private JMenuItem mi_remote_control;
    private JMenuItem mi_save;
    DrawPanel panel_left;
    DrawPanel panel_main1;
    DrawPanel panel_right;
    private JButton pb_add_left_scene_to_list;
    private JButton pb_add_right_scene_to_list;
    private JButton pb_add_to_play_list;
    private JButton pb_audio_trigger;
    private JButton pb_auto_fade;
    private JButton pb_black_out;
    private JButton pb_clear_scene_list;
    private JButton pb_configure_generator_left;
    private JButton pb_configure_generator_right;
    private JButton pb_left_effect_activated;
    private JButton pb_load_scene_list;
    private JToggleButton pb_logo;
    private JButton pb_master_strobe;
    private JButton pb_move_scenlist_item_down;
    private JButton pb_move_scenlist_item_up;
    private JButton pb_play_list_clear;
    private JButton pb_play_list_down;
    private JButton pb_play_list_load;
    private JButton pb_play_list_play;
    private JButton pb_play_list_remove_item;
    private JButton pb_play_list_replace;
    private JButton pb_play_list_save;
    private JButton pb_play_list_up;
    private JButton pb_remove_secene_list_item;
    private JButton pb_replace_scenlist_item_left;
    private JButton pb_replace_scenlist_item_right;
    private JButton pb_right_effect_activated;
    private JButton pb_save_scene_list;
    private JButton pb_select_generator_left_1;
    private JButton pb_select_generator_left_2;
    private JButton pb_select_generator_left_3;
    private JButton pb_select_generator_left_4;
    private JButton pb_select_generator_left_5;
    private JButton pb_select_generator_right_1;
    private JButton pb_select_generator_right_2;
    private JButton pb_select_generator_right_3;
    private JButton pb_select_generator_right_4;
    private JButton pb_select_generator_right_5;
    private JButton pb_set_fader_center;
    private JButton pb_set_fader_left;
    private JButton pb_set_fader_right;
    private JButton pb_set_scene_left;
    private JButton pb_set_scene_right;
    private JButton pb_start_audio_capturing;
    private JButton pb_stop_audio_capturing;
    private JProgressBar prb_left_audio_level;
    private JProgressBar prb_right_audio_level;
    private JSlider sl_audio_gain;
    private JSlider sl_effect_lower_value_left;
    private JSlider sl_effect_lower_value_right;
    private JSlider sl_effect_timer_value_left;
    private JSlider sl_effect_timer_value_right;
    private JSlider sl_effect_upper_value_left;
    private JSlider sl_effect_upper_value_right;
    private JSlider sl_effect_value_left;
    private JSlider sl_effect_value_right;
    private JSlider sl_generator_level_left;
    private JSlider sl_generator_level_right;
    private JSlider sl_left_level;
    private JSlider sl_main_fader;
    private JSlider sl_main_fader_time;
    private JSlider sl_master_level;
    private JSlider sl_master_strobe_duration;
    private JSlider sl_master_strobe_interval;
    private JSlider sl_right_level;
    private JSlider sl_speed_left;
    private JSlider sl_speed_right;
    private JSlider sl_trigger_level;
    private JTextField tf_play_list_fader_time;
    private JTextField tf_play_list_scene_time;
    
    public GlediatorView() {
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.scene_index = -1;
        this.block_events = false;
        this.cb_left_generator_type.setModel(new DefaultComboBoxModel<SuperGenerator.GeneratorTypes>(SuperGenerator.GeneratorTypes.values()));
        this.cb_right_generator_type.setModel(new DefaultComboBoxModel<SuperGenerator.GeneratorTypes>(SuperGenerator.GeneratorTypes.values()));
        this.cb_filter_mode_left.setModel(new DefaultComboBoxModel<Filter.FilterMode>(Filter.FilterMode.values()));
        this.cb_filter_mode_right.setModel(new DefaultComboBoxModel<Filter.FilterMode>(Filter.FilterMode.values()));
        this.cb_mixer_mode_left.setModel(new DefaultComboBoxModel<Mixer.MixerMode>(Mixer.MixerMode.values()));
        this.cb_mixer_mode_right.setModel(new DefaultComboBoxModel<Mixer.MixerMode>(Mixer.MixerMode.values()));
        this.cb_effect_mode_left.setModel(new DefaultComboBoxModel<Effect.EffectMode>(Effect.EffectMode.values()));
        this.cb_effect_mode_right.setModel(new DefaultComboBoxModel<Effect.EffectMode>(Effect.EffectMode.values()));
        this.cb_effect_dir_left.setModel(new DefaultComboBoxModel<Effect.EffectDirection>(Effect.EffectDirection.values()));
        this.cb_effect_dir_right.setModel(new DefaultComboBoxModel<Effect.EffectDirection>(Effect.EffectDirection.values()));
        this.cb_effect_methode_left.setModel(new DefaultComboBoxModel<Effect.EffectMethode>(Effect.EffectMethode.values()));
        this.cb_effect_methode_right.setModel(new DefaultComboBoxModel<Effect.EffectMethode>(Effect.EffectMethode.values()));
        this.cb_fader_mode.setModel(new DefaultComboBoxModel<Fader.FaderMode>(Fader.FaderMode.values()));
        this.cb_play_list_fader_mode.setModel(new DefaultComboBoxModel<Fader.FaderMode>(Fader.FaderMode.values()));
        this.pb_select_generator_left_1.setBackground(GlediatorView.HIGHLIGHT_COLOR);
        this.pb_select_generator_right_1.setBackground(GlediatorView.HIGHLIGHT_COLOR);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        this.glediator_model = new GlediatorModel(this);
        this.sl_main_fader_time.setMaximum(1);
        this.sl_main_fader_time.setMaximum(100);
        this.sl_main_fader.setValue(5);
        this.panel_left.setFrameSize(this.glediator_model.getFrameSize());
        this.panel_left.setSpace(1);
        this.panel_right.setFrameSize(this.glediator_model.getFrameSize());
        this.panel_right.setSpace(1);
        this.panel_main1.setFrameSize(this.glediator_model.getFrameSize());
        this.panel_main1.setSpace(1);
        GlediatorView.DEFAULT_BTN_COLOR = this.pb_configure_generator_left.getBackground();
        GlediatorView.DEFAULT_FORE_COLOR = this.jl_scenes.getForeground();
        final String[] audio_sources = this.glediator_model.getAudioSources();
        if (audio_sources.length == 0) {
            this.cb_audio_sources.removeAllItems();
            this.cb_audio_sources.setEnabled(false);
            this.pb_start_audio_capturing.setEnabled(false);
        }
        else {
            this.cb_audio_sources.removeAllItems();
            for (int i = 0; i < audio_sources.length; ++i) {
                this.cb_audio_sources.addItem(audio_sources[i]);
            }
        }
        this.prb_left_audio_level.setForeground(GlediatorView.HIGHLIGHT_COLOR);
        this.prb_right_audio_level.setForeground(GlediatorView.HIGHLIGHT_COLOR);
        this.updateLeftControlls();
        this.updateRightControlls();
        this.updateMainControlls();
        this.glediator_model.autoLoad();
        this.glediator_model.start();
    }
    
    @Override
    public void update(final Observable o, final Object o1) {
        final NotificationMessage notify_message = (NotificationMessage)o1;
        switch (notify_message) {
            case TIMER_UPDATE_NOTIFICATION: {
                this.lbl_fps.setText("Running @ " + this.glediator_model.getFPS() + " FPS");
                final int[] audio_level = this.glediator_model.getAudioLevel();
                this.prb_left_audio_level.setValue(audio_level[0]);
                this.prb_right_audio_level.setValue(audio_level[1]);
                if (this.glediator_model.getAudioTriggerState()) {
                    this.pb_audio_trigger.setBackground(GlediatorView.HIGHLIGHT_COLOR);
                }
                else {
                    this.pb_audio_trigger.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
                }
                this.panel_left.setImage(this.glediator_model.left_image);
                this.panel_left.free();
                this.panel_right.setImage(this.glediator_model.right_image);
                this.panel_right.free();
                this.panel_main1.setImage(this.glediator_model.main_image);
                this.panel_main1.free();
                this.panel_left.repaint();
                this.panel_right.repaint();
                this.panel_main1.repaint();
                break;
            }
            case SCENE_CHANGED_NOTIFICATION: {
                this.updateLeftControlls();
                this.updateRightControlls();
                break;
            }
            case AUTO_FADER_ACTIVATED_NOTIFICATION: {
                this.cb_fader_mode.setSelectedItem(this.glediator_model.fader.getFaderMode());
                this.pb_auto_fade.setBackground(GlediatorView.HIGHLIGHT_COLOR);
                this.sl_main_fader.setEnabled(false);
                this.pb_auto_fade.setEnabled(false);
                break;
            }
            case SIZE_CHANGED_NOTIFICATION: {
                this.panel_left.setFrameSize(this.glediator_model.getFrameSize());
                this.panel_right.setFrameSize(this.glediator_model.getFrameSize());
                this.panel_main1.setFrameSize(this.glediator_model.getFrameSize());
                this.panel_left.setSpace(this.glediator_model.getGap());
                this.panel_right.setSpace(this.glediator_model.getGap());
                this.panel_main1.setSpace(this.glediator_model.getGap());
                break;
            }
            case UPDATE_GUI_NOTIFICATION: {
                this.block_events = true;
                this.cb_fader_mode.setSelectedItem(this.glediator_model.fader.getFaderMode());
                this.sl_main_fader.setValue(this.glediator_model.fader.getFaderValue());
                this.sl_main_fader_time.setValue(this.glediator_model.fader.getFaderTime());
                this.sl_left_level.setValue(this.glediator_model.fader.getLeftIntensity());
                this.sl_right_level.setValue(this.glediator_model.fader.getRightIntensity());
                this.sl_master_level.setValue(this.glediator_model.fader.getMasterIntensity());
                this.sl_master_strobe_interval.setValue(this.glediator_model.getStrobeInterval());
                this.sl_master_strobe_duration.setValue(this.glediator_model.getStrobeDuration());
                if (this.glediator_model.getStrobeStatus()) {
                    this.pb_master_strobe.setBackground(GlediatorView.HIGHLIGHT_COLOR);
                }
                else {
                    this.pb_master_strobe.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
                }
                this.sl_audio_gain.setValue(SuperGenerator.audio_properties.gain);
                this.sl_trigger_level.setValue(SuperGenerator.audio_properties.audio_trigger_level);
                if (this.glediator_model.isPlayListPlaying()) {
                    if (this.glediator_model.play_list.getNumberOfEntries() > 0) {
                        this.pb_play_list_clear.setEnabled(false);
                        this.pb_play_list_load.setEnabled(false);
                        this.pb_play_list_play.setBackground(GlediatorView.HIGHLIGHT_COLOR);
                        this.pb_play_list_play.setText("Stop playing");
                    }
                    else {
                        this.pb_play_list_clear.setEnabled(true);
                        this.pb_play_list_load.setEnabled(true);
                        this.pb_play_list_play.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
                        this.pb_play_list_play.setText("Start playing");
                    }
                }
                this.block_events = false;
                break;
            }
            case SCENE_LIST_CHANGED_NOTIFICATION: {
                this.jl_scenes.setListData(this.glediator_model.scene_list.getSceneListEntries());
                this.jl_scenes.setCellRenderer(new ThumbnailListRenderer(GlediatorView.DEFAULT_FORE_COLOR));
                this.jl_scenes.setVisibleRowCount(this.glediator_model.scene_list.getNumberOfEntries());
                this.jl_scenes.setSelectedIndex(this.glediator_model.scene_list.getSelectedIndex());
                break;
            }
            case PLAY_LIST_CHANGED_NOTIFICATION: {
                this.jl_play_list.setListData(this.glediator_model.play_list.getPlayListEntries());
                this.jl_play_list.setCellRenderer(new ThumbnailListRenderer(GlediatorView.DEFAULT_FORE_COLOR));
                this.jl_play_list.setVisibleRowCount(this.glediator_model.play_list.getNumberOfEntries());
                this.jl_play_list.setSelectedIndex(this.glediator_model.play_list.getSelectedIndex());
                break;
            }
            case FADER_VALUE_UPDATE_NOTIFICATION: {
                this.block_events = true;
                this.sl_main_fader.setValue(this.glediator_model.fader.getFaderValue());
                this.block_events = false;
                break;
            }
            case FADER_REACHED_ENDPOSITION_NOTIFICATION: {
                this.block_events = true;
                this.sl_main_fader.setEnabled(true);
                this.pb_auto_fade.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
                this.pb_auto_fade.setEnabled(true);
                this.block_events = false;
                break;
            }
            case MASTER_DIMMER_CHANGED_NOTIFICATION: {
                this.block_events = true;
                this.sl_master_level.setValue(this.glediator_model.fader.getMasterIntensity());
                this.block_events = false;
                break;
            }
            case BLACK_OUT_FINSHED_NOTIFICATION: {
                this.block_events = true;
                this.sl_master_level.setEnabled(true);
                this.pb_black_out.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
                this.pb_black_out.setEnabled(true);
                this.block_events = false;
                break;
            }
        }
    }
    
    public static void main(final String[] args) {
        try {
         //   UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");	
	        	Properties props = new Properties();
	        	//props.put("logoString", "my company");
	        	props.put("logoString", " ");
	        	AluminiumLookAndFeel.setCurrentTheme(props);
	        	//UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
        	    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GlediatorView().setVisible(true);
            }
        });
    }
    
    private void initComponents() {
        this.jPanel7 = new JPanel();
        this.jComboBox6 = new JComboBox();
        this.jLabel9 = new JLabel();
        this.jSlider2 = new JSlider();
        this.jLabel10 = new JLabel();
        this.jMenu1 = new JMenu();
        this.panel_left = new DrawPanel();
        this.panel_right = new DrawPanel();
        this.jPanel3 = new JPanel();
        this.sl_effect_lower_value_right = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_effect_timer_value_right = new JSlider();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.cb_effect_mode_right = new JComboBox();
        this.jLabel13 = new JLabel();
        this.cb_effect_methode_right = new JComboBox();
        this.jLabel19 = new JLabel();
        this.sl_effect_value_right = new JSlider();
        this.jLabel20 = new JLabel();
        this.sl_effect_upper_value_right = new JSlider();
        this.jLabel23 = new JLabel();
        this.cb_effect_dir_right = new JComboBox();
        this.pb_right_effect_activated = new JButton();
        this.jPanel4 = new JPanel();
        this.jPanel9 = new JPanel();
        this.pb_select_generator_right_1 = new JButton();
        this.pb_select_generator_right_2 = new JButton();
        this.pb_select_generator_right_3 = new JButton();
        this.pb_select_generator_right_4 = new JButton();
        this.pb_select_generator_right_5 = new JButton();
        this.jPanel6 = new JPanel();
        this.jPanel10 = new JPanel();
        this.pb_select_generator_left_1 = new JButton();
        this.pb_select_generator_left_2 = new JButton();
        this.pb_select_generator_left_3 = new JButton();
        this.pb_select_generator_left_4 = new JButton();
        this.pb_select_generator_left_5 = new JButton();
        this.jPanel11 = new JPanel();
        this.cb_left_generator_type = new JComboBox();
        this.sl_generator_level_left = new JSlider();
        this.jLabel26 = new JLabel();
        this.sl_speed_left = new JSlider();
        this.jLabel30 = new JLabel();
        this.cb_mixer_mode_left = new JComboBox();
        this.jLabel31 = new JLabel();
        this.pb_configure_generator_left = new JButton();
        this.jLabel32 = new JLabel();
        this.jLabel33 = new JLabel();
        this.cb_filter_mode_left = new JComboBox();
        this.jPanel12 = new JPanel();
        this.pb_logo = new JToggleButton();
        this.lbl_fps = new JLabel();
        this.jLabel7 = new JLabel();
        this.jLabel8 = new JLabel();
        this.jPanel1 = new JPanel();
        this.jLabel21 = new JLabel();
        this.jLabel22 = new JLabel();
        this.jPanel14 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.jl_play_list = new JList();
        this.panel_main1 = new DrawPanel();
        this.jPanel16 = new JPanel();
        this.jLabel41 = new JLabel();
        this.jLabel42 = new JLabel();
        this.sl_left_level = new JSlider();
        this.sl_master_level = new JSlider();
        this.sl_main_fader = new JSlider();
        this.cb_fader_mode = new JComboBox();
        this.pb_black_out = new JButton();
        this.pb_auto_fade = new JButton();
        this.pb_set_fader_left = new JButton();
        this.pb_set_fader_right = new JButton();
        this.pb_set_fader_center = new JButton();
        this.jLabel43 = new JLabel();
        this.sl_main_fader_time = new JSlider();
        this.sl_right_level = new JSlider();
        this.jLabel45 = new JLabel();
        this.jPanel15 = new JPanel();
        this.sl_trigger_level = new JSlider();
        this.cb_audio_sources = new JComboBox();
        this.pb_start_audio_capturing = new JButton();
        this.pb_audio_trigger = new JButton();
        this.jLabel44 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel14 = new JLabel();
        this.sl_audio_gain = new JSlider();
        this.pb_stop_audio_capturing = new JButton();
        this.jProgressBar1 = new JProgressBar();
        this.prb_right_audio_level = new JProgressBar();
        this.prb_left_audio_level = new JProgressBar();
        this.jPanel17 = new JPanel();
        this.jScrollPane2 = new JScrollPane();
        this.jl_scenes = new JList();
        this.jPanel13 = new JPanel();
        this.sl_effect_lower_value_left = new JSlider();
        this.jLabel11 = new JLabel();
        this.sl_effect_timer_value_left = new JSlider();
        this.jLabel12 = new JLabel();
        this.jLabel18 = new JLabel();
        this.cb_effect_mode_left = new JComboBox();
        this.jLabel35 = new JLabel();
        this.cb_effect_methode_left = new JComboBox();
        this.jLabel36 = new JLabel();
        this.sl_effect_value_left = new JSlider();
        this.jLabel37 = new JLabel();
        this.sl_effect_upper_value_left = new JSlider();
        this.jLabel38 = new JLabel();
        this.cb_effect_dir_left = new JComboBox();
        this.pb_left_effect_activated = new JButton();
        this.jPanel18 = new JPanel();
        this.pb_add_left_scene_to_list = new JButton();
        this.pb_add_right_scene_to_list = new JButton();
        this.pb_set_scene_left = new JButton();
        this.pb_set_scene_right = new JButton();
        this.pb_move_scenlist_item_down = new JButton();
        this.pb_move_scenlist_item_up = new JButton();
        this.pb_remove_secene_list_item = new JButton();
        this.pb_save_scene_list = new JButton();
        this.pb_clear_scene_list = new JButton();
        this.pb_replace_scenlist_item_left = new JButton();
        this.pb_replace_scenlist_item_right = new JButton();
        this.pb_load_scene_list = new JButton();
        this.jPanel19 = new JPanel();
        this.tf_play_list_fader_time = new JTextField();
        this.jLabel2 = new JLabel();
        this.cb_play_list_fader_mode = new JComboBox();
        this.jLabel15 = new JLabel();
        this.tf_play_list_scene_time = new JTextField();
        this.jLabel16 = new JLabel();
        this.pb_add_to_play_list = new JButton();
        this.jPanel20 = new JPanel();
        this.pb_play_list_down = new JButton();
        this.pb_play_list_up = new JButton();
        this.pb_play_list_save = new JButton();
        this.pb_play_list_load = new JButton();
        this.pb_play_list_replace = new JButton();
        this.pb_play_list_clear = new JButton();
        this.pb_play_list_remove_item = new JButton();
        this.pb_play_list_play = new JButton();
        this.jPanel21 = new JPanel();
        this.cb_right_generator_type = new JComboBox();
        this.sl_generator_level_right = new JSlider();
        this.jLabel34 = new JLabel();
        this.sl_speed_right = new JSlider();
        this.jLabel39 = new JLabel();
        this.cb_mixer_mode_right = new JComboBox();
        this.jLabel40 = new JLabel();
        this.pb_configure_generator_right = new JButton();
        this.jLabel46 = new JLabel();
        this.jLabel47 = new JLabel();
        this.cb_filter_mode_right = new JComboBox();
        this.jPanel22 = new JPanel();
        this.sl_master_strobe_duration = new JSlider();
        this.pb_master_strobe = new JButton();
        this.jLabel48 = new JLabel();
        this.jLabel17 = new JLabel();
        this.sl_master_strobe_interval = new JSlider();
        this.jMenuBar1 = new JMenuBar();
        this.jMenu2 = new JMenu();
        this.mi_load = new JMenuItem();
        this.mi_save = new JMenuItem();
        this.mi_exit = new JMenuItem();
        this.jMenu3 = new JMenu();
        this.mi_matrix_size = new JMenuItem();
        this.mi_output = new JMenuItem();
        this.jMenu5 = new JMenu();
        this.mi_recorder = new JMenuItem();
        this.mi_remote_control = new JMenuItem();
        this.jMenu4 = new JMenu();
        this.mi_about = new JMenuItem();
        this.jPanel7.setBorder(BorderFactory.createTitledBorder("Left Mixer"));
        this.jComboBox6.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        this.jLabel9.setText("A");
        this.jLabel10.setText("B");
        final GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
        this.jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addContainerGap().addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jComboBox6, 0, 244, 32767).addGroup(jPanel7Layout.createSequentialGroup().addComponent(this.jLabel9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSlider2, -1, 223, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel10))).addContainerGap()));
        jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addContainerGap().addComponent(this.jComboBox6, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel9).addComponent(this.jLabel10).addComponent(this.jSlider2, -2, -1, -2)).addContainerGap(-1, 32767)));
        this.jMenu1.setText("jMenu1");
        this.setDefaultCloseOperation(3);
        this.setTitle("GLEDIATOR3 - Graphical LED Installation AnimaTOR (Unofficial)");
        this.setIconImages(null);
        this.setMaximumSize(new Dimension(4000, 2000));
        this.setMinimumSize(new Dimension(1350, 750));
        this.setPreferredSize(new Dimension(1350, 750));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent evt) {
                GlediatorView.this.formWindowClosing(evt);
            }
        });
        this.getContentPane().setLayout(new GridBagLayout());
        this.panel_left.setBackground(new Color(0, 0, 0));
        this.panel_left.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.panel_left.setMinimumSize(new Dimension(200, 150));
        this.panel_left.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                GlediatorView.this.panel_leftMouseClicked(evt);
            }
        });
        final GroupLayout panel_leftLayout = new GroupLayout(this.panel_left);
        this.panel_left.setLayout(panel_leftLayout);
        panel_leftLayout.setHorizontalGroup(panel_leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 268, 32767));
        panel_leftLayout.setVerticalGroup(panel_leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 150, 32767));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.panel_left, gridBagConstraints);
        this.panel_right.setBackground(new Color(0, 0, 0));
        this.panel_right.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.panel_right.setMinimumSize(new Dimension(200, 100));
        this.panel_right.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                GlediatorView.this.panel_rightMouseClicked(evt);
            }
        });
        final GroupLayout panel_rightLayout = new GroupLayout(this.panel_right);
        this.panel_right.setLayout(panel_rightLayout);
        panel_rightLayout.setHorizontalGroup(panel_rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 268, 32767));
        panel_rightLayout.setVerticalGroup(panel_rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 150, 32767));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.panel_right, gridBagConstraints);
        this.jPanel3.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel3.setLayout(new GridBagLayout());
        this.sl_effect_lower_value_right.setMaximumSize(new Dimension(100, 25));
        this.sl_effect_lower_value_right.setMinimumSize(new Dimension(100, 25));
        this.sl_effect_lower_value_right.setPreferredSize(new Dimension(100, 25));
        this.sl_effect_lower_value_right.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_effect_lower_value_rightStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.sl_effect_lower_value_right, gridBagConstraints);
        this.jLabel4.setText("Value");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.jLabel4, gridBagConstraints);
        this.sl_effect_timer_value_right.setMaximumSize(new Dimension(100, 25));
        this.sl_effect_timer_value_right.setMinimumSize(new Dimension(100, 25));
        this.sl_effect_timer_value_right.setPreferredSize(new Dimension(100, 25));
        this.sl_effect_timer_value_right.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_effect_timer_value_rightStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.sl_effect_timer_value_right, gridBagConstraints);
        this.jLabel5.setText("Lower Limit");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.jLabel5, gridBagConstraints);
        this.jLabel6.setText("Effect");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.jLabel6, gridBagConstraints);
        this.cb_effect_mode_right.setModel(new DefaultComboBoxModel<String>(new String[] { "Strobe", "Rotate", "Blur", "Dynamic Blur", "Move hor.", "Move vert." }));
        this.cb_effect_mode_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_effect_mode_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.cb_effect_mode_right, gridBagConstraints);
        this.jLabel13.setText("Methode");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.jLabel13, gridBagConstraints);
        this.cb_effect_methode_right.setModel(new DefaultComboBoxModel<String>(new String[] { "Manual", "Time", "Sound Level", "Beat" }));
        this.cb_effect_methode_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_effect_methode_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.cb_effect_methode_right, gridBagConstraints);
        this.jLabel19.setText("Upper Limit");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.jLabel19, gridBagConstraints);
        this.sl_effect_value_right.setMaximumSize(new Dimension(100, 25));
        this.sl_effect_value_right.setMinimumSize(new Dimension(100, 25));
        this.sl_effect_value_right.setPreferredSize(new Dimension(100, 25));
        this.sl_effect_value_right.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_effect_value_rightStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.sl_effect_value_right, gridBagConstraints);
        this.jLabel20.setText("Timer Value");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.jLabel20, gridBagConstraints);
        this.sl_effect_upper_value_right.setMaximumSize(new Dimension(100, 25));
        this.sl_effect_upper_value_right.setMinimumSize(new Dimension(100, 25));
        this.sl_effect_upper_value_right.setPreferredSize(new Dimension(100, 25));
        this.sl_effect_upper_value_right.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_effect_upper_value_rightStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.sl_effect_upper_value_right, gridBagConstraints);
        this.jLabel23.setText("Direction");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.jLabel23, gridBagConstraints);
        this.cb_effect_dir_right.setModel(new DefaultComboBoxModel<String>(new String[] { "Right", "Left", "Toggle" }));
        this.cb_effect_dir_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_effect_dir_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.cb_effect_dir_right, gridBagConstraints);
        this.pb_right_effect_activated.setText("Turn on");
        this.pb_right_effect_activated.setMaximumSize(new Dimension(60, 20));
        this.pb_right_effect_activated.setMinimumSize(new Dimension(60, 20));
        this.pb_right_effect_activated.setPreferredSize(new Dimension(60, 20));
        this.pb_right_effect_activated.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_right_effect_activatedActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.pb_right_effect_activated, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel3, gridBagConstraints);
        this.jPanel4.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel4.setLayout(new GridBagLayout());
        this.jPanel9.setLayout(new GridBagLayout());
        this.pb_select_generator_right_1.setText("1");
        this.pb_select_generator_right_1.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_right_1.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_right_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_right_1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel9.add(this.pb_select_generator_right_1, gridBagConstraints);
        this.pb_select_generator_right_2.setText("2");
        this.pb_select_generator_right_2.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_right_2.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_right_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_right_2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel9.add(this.pb_select_generator_right_2, gridBagConstraints);
        this.pb_select_generator_right_3.setText("3");
        this.pb_select_generator_right_3.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_right_3.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_right_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_right_3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel9.add(this.pb_select_generator_right_3, gridBagConstraints);
        this.pb_select_generator_right_4.setText("4");
        this.pb_select_generator_right_4.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_right_4.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_right_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_right_4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel9.add(this.pb_select_generator_right_4, gridBagConstraints);
        this.pb_select_generator_right_5.setText("5");
        this.pb_select_generator_right_5.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_right_5.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_right_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_right_5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel9.add(this.pb_select_generator_right_5, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        this.jPanel4.add(this.jPanel9, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel4, gridBagConstraints);
        this.jPanel6.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel6.setLayout(new GridBagLayout());
        this.jPanel10.setLayout(new GridBagLayout());
        this.pb_select_generator_left_1.setText("1");
        this.pb_select_generator_left_1.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_left_1.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_left_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_left_1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel10.add(this.pb_select_generator_left_1, gridBagConstraints);
        this.pb_select_generator_left_2.setText("2");
        this.pb_select_generator_left_2.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_left_2.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_left_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_left_2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel10.add(this.pb_select_generator_left_2, gridBagConstraints);
        this.pb_select_generator_left_3.setText("3");
        this.pb_select_generator_left_3.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_left_3.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_left_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_left_3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel10.add(this.pb_select_generator_left_3, gridBagConstraints);
        this.pb_select_generator_left_4.setText("4");
        this.pb_select_generator_left_4.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_left_4.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_left_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_left_4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel10.add(this.pb_select_generator_left_4, gridBagConstraints);
        this.pb_select_generator_left_5.setText("5");
        this.pb_select_generator_left_5.setMaximumSize(new Dimension(30, 20));
        this.pb_select_generator_left_5.setMinimumSize(new Dimension(30, 20));
        this.pb_select_generator_left_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_select_generator_left_5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel10.add(this.pb_select_generator_left_5, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        this.jPanel6.add(this.jPanel10, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel6, gridBagConstraints);
        this.jPanel11.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel11.setLayout(new GridBagLayout());
        this.cb_left_generator_type.setModel(new DefaultComboBoxModel<String>(new String[] { "None", "Fading Objects", "Falling Objects", "Simple Spectrum", "Knight Rider", "Plasma" }));
        this.cb_left_generator_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_left_generator_typeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.cb_left_generator_type, gridBagConstraints);
        this.sl_generator_level_left.setMaximumSize(new Dimension(100, 25));
        this.sl_generator_level_left.setMinimumSize(new Dimension(100, 25));
        this.sl_generator_level_left.setPreferredSize(new Dimension(100, 25));
        this.sl_generator_level_left.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_generator_level_leftStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.sl_generator_level_left, gridBagConstraints);
        this.jLabel26.setText("Level");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.jLabel26, gridBagConstraints);
        this.sl_speed_left.setMaximum(20);
        this.sl_speed_left.setValue(1);
        this.sl_speed_left.setMaximumSize(new Dimension(100, 25));
        this.sl_speed_left.setMinimumSize(new Dimension(100, 25));
        this.sl_speed_left.setPreferredSize(new Dimension(100, 25));
        this.sl_speed_left.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_speed_leftStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.sl_speed_left, gridBagConstraints);
        this.jLabel30.setText("Speed");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.jLabel30, gridBagConstraints);
        this.cb_mixer_mode_left.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        this.cb_mixer_mode_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_mixer_mode_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.cb_mixer_mode_left, gridBagConstraints);
        this.jLabel31.setText("Mixer");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.jLabel31, gridBagConstraints);
        this.pb_configure_generator_left.setText("Configure generator");
        this.pb_configure_generator_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_configure_generator_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.pb_configure_generator_left, gridBagConstraints);
        this.jLabel32.setText("Type");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.jLabel32, gridBagConstraints);
        this.jLabel33.setText("Filter");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.jLabel33, gridBagConstraints);
        this.cb_filter_mode_left.setModel(new DefaultComboBoxModel<String>(new String[] { "None", "Red", "Green", "Blue", "Greyscale", "Invert", "Thresh. 1", "Thresh. 2" }));
        this.cb_filter_mode_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_filter_mode_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel11.add(this.cb_filter_mode_left, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel11, gridBagConstraints);
        this.jPanel12.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel12.setLayout(new GridBagLayout());
        this.pb_logo.setIcon(new ImageIcon(this.getClass().getResource("/pictures/Banner_Small.gif")));
        this.pb_logo.setMaximumSize(new Dimension(220, 60));
        this.pb_logo.setMinimumSize(new Dimension(200, 60));
        this.pb_logo.setPreferredSize(new Dimension(220, 60));
        this.pb_logo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_logoActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel12.add(this.pb_logo, gridBagConstraints);
        this.lbl_fps.setText("...");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.jPanel12.add(this.lbl_fps, gridBagConstraints);
        this.jLabel7.setText("Unofficial Version");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        this.jPanel12.add(this.jLabel7, gridBagConstraints);
        this.jLabel8.setText("(c) 2014 by R. Heller");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.jPanel12.add(this.jLabel8, gridBagConstraints);
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
        this.jPanel12.add(this.jPanel1, new GridBagConstraints());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel12, gridBagConstraints);
        this.jPanel14.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel14.setLayout(new GridBagLayout());
        this.jl_play_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent evt) {
                GlediatorView.this.jl_play_listMousePressed(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.jl_play_list);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel14.add(this.jScrollPane1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel14, gridBagConstraints);
        this.panel_main1.setBackground(new Color(0, 0, 0));
        this.panel_main1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.panel_main1.setMinimumSize(new Dimension(250, 150));
        final GroupLayout panel_main1Layout = new GroupLayout(this.panel_main1);
        this.panel_main1.setLayout(panel_main1Layout);
        panel_main1Layout.setHorizontalGroup(panel_main1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 295, 32767));
        panel_main1Layout.setVerticalGroup(panel_main1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 150, 32767));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.panel_main1, gridBagConstraints);
        this.jPanel16.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel16.setLayout(new GridBagLayout());
        this.jLabel41.setText("Left");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.jLabel41, gridBagConstraints);
        this.jLabel42.setText("Master");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.jLabel42, gridBagConstraints);
        this.sl_left_level.setValue(20);
        this.sl_left_level.setMaximumSize(new Dimension(100, 25));
        this.sl_left_level.setMinimumSize(new Dimension(100, 25));
        this.sl_left_level.setPreferredSize(new Dimension(100, 25));
        this.sl_left_level.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_left_levelStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.sl_left_level, gridBagConstraints);
        this.sl_master_level.setMaximumSize(new Dimension(100, 25));
        this.sl_master_level.setMinimumSize(new Dimension(100, 25));
        this.sl_master_level.setPreferredSize(new Dimension(100, 25));
        this.sl_master_level.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_master_levelStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.sl_master_level, gridBagConstraints);
        this.sl_main_fader.setMaximumSize(new Dimension(100, 25));
        this.sl_main_fader.setMinimumSize(new Dimension(100, 25));
        this.sl_main_fader.setPreferredSize(new Dimension(100, 25));
        this.sl_main_fader.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_main_faderStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.sl_main_fader, gridBagConstraints);
        this.cb_fader_mode.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        this.cb_fader_mode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_fader_modeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.cb_fader_mode, gridBagConstraints);
        this.pb_black_out.setLabel("Black");
        this.pb_black_out.setMinimumSize(new Dimension(30, 20));
        this.pb_black_out.setPreferredSize(new Dimension(40, 20));
        this.pb_black_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_black_outActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.pb_black_out, gridBagConstraints);
        this.pb_auto_fade.setText("Fade");
        this.pb_auto_fade.setMinimumSize(new Dimension(40, 20));
        this.pb_auto_fade.setPreferredSize(new Dimension(45, 20));
        this.pb_auto_fade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_auto_fadeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.pb_auto_fade, gridBagConstraints);
        this.pb_set_fader_left.setText("L");
        this.pb_set_fader_left.setMinimumSize(new Dimension(30, 20));
        this.pb_set_fader_left.setPreferredSize(new Dimension(30, 20));
        this.pb_set_fader_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_set_fader_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.pb_set_fader_left, gridBagConstraints);
        this.pb_set_fader_right.setText("R");
        this.pb_set_fader_right.setMinimumSize(new Dimension(30, 20));
        this.pb_set_fader_right.setPreferredSize(new Dimension(30, 20));
        this.pb_set_fader_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_set_fader_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.pb_set_fader_right, gridBagConstraints);
        this.pb_set_fader_center.setText("C");
        this.pb_set_fader_center.setMinimumSize(new Dimension(30, 20));
        this.pb_set_fader_center.setPreferredSize(new Dimension(30, 20));
        this.pb_set_fader_center.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_set_fader_centerActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.pb_set_fader_center, gridBagConstraints);
        this.jLabel43.setText("Fade Time");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.jLabel43, gridBagConstraints);
        this.sl_main_fader_time.setMinimum(1);
        this.sl_main_fader_time.setMaximumSize(new Dimension(100, 25));
        this.sl_main_fader_time.setMinimumSize(new Dimension(100, 25));
        this.sl_main_fader_time.setPreferredSize(new Dimension(100, 25));
        this.sl_main_fader_time.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_main_fader_timeStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.sl_main_fader_time, gridBagConstraints);
        this.sl_right_level.setMaximumSize(new Dimension(100, 25));
        this.sl_right_level.setMinimumSize(new Dimension(100, 25));
        this.sl_right_level.setPreferredSize(new Dimension(100, 25));
        this.sl_right_level.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_right_levelStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.sl_right_level, gridBagConstraints);
        this.jLabel45.setText("Right");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel16.add(this.jLabel45, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel16, gridBagConstraints);
        this.jPanel15.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel15.setLayout(new GridBagLayout());
        this.sl_trigger_level.setMaximumSize(new Dimension(100, 25));
        this.sl_trigger_level.setMinimumSize(new Dimension(100, 25));
        this.sl_trigger_level.setPreferredSize(new Dimension(100, 25));
        this.sl_trigger_level.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_trigger_levelStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel15.add(this.sl_trigger_level, gridBagConstraints);
        this.cb_audio_sources.setModel(new DefaultComboBoxModel<String>(new String[] { "Hallo das ist ein langer Eintrag Hallo das ist ein langer Eintrag", "Kutu", " " }));
        this.cb_audio_sources.setMaximumSize(new Dimension(200, 32767));
        this.cb_audio_sources.setMinimumSize(new Dimension(100, 27));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel15.add(this.cb_audio_sources, gridBagConstraints);
        this.pb_start_audio_capturing.setText("Start");
        this.pb_start_audio_capturing.setMaximumSize(new Dimension(60, 20));
        this.pb_start_audio_capturing.setMinimumSize(new Dimension(60, 20));
        this.pb_start_audio_capturing.setPreferredSize(new Dimension(60, 20));
        this.pb_start_audio_capturing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_start_audio_capturingActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel15.add(this.pb_start_audio_capturing, gridBagConstraints);
        this.pb_audio_trigger.setText("T");
        this.pb_audio_trigger.setMaximumSize(new Dimension(40, 20));
        this.pb_audio_trigger.setMinimumSize(new Dimension(40, 20));
        this.pb_audio_trigger.setPreferredSize(new Dimension(40, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel15.add(this.pb_audio_trigger, gridBagConstraints);
        this.jLabel44.setText("Gain");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel15.add(this.jLabel44, gridBagConstraints);
        this.jLabel3.setText("Audio Source");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel15.add(this.jLabel3, gridBagConstraints);
        this.jLabel14.setText("Trigger Level");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel15.add(this.jLabel14, gridBagConstraints);
        this.sl_audio_gain.setMaximumSize(new Dimension(100, 25));
        this.sl_audio_gain.setMinimumSize(new Dimension(100, 25));
        this.sl_audio_gain.setPreferredSize(new Dimension(100, 25));
        this.sl_audio_gain.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_audio_gainStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel15.add(this.sl_audio_gain, gridBagConstraints);
        this.pb_stop_audio_capturing.setText("Stop");
        this.pb_stop_audio_capturing.setMaximumSize(new Dimension(40, 20));
        this.pb_stop_audio_capturing.setMinimumSize(new Dimension(40, 20));
        this.pb_stop_audio_capturing.setPreferredSize(new Dimension(40, 20));
        this.pb_stop_audio_capturing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_stop_audio_capturingActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel15.add(this.pb_stop_audio_capturing, gridBagConstraints);
        this.jProgressBar1.setValue(45);
        this.jPanel15.add(this.jProgressBar1, new GridBagConstraints());
        this.prb_right_audio_level.setValue(45);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        this.jPanel15.add(this.prb_right_audio_level, gridBagConstraints);
        this.prb_left_audio_level.setValue(45);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        this.jPanel15.add(this.prb_left_audio_level, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel15, gridBagConstraints);
        this.jPanel17.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel17.setLayout(new GridBagLayout());
        this.jl_scenes.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent evt) {
                GlediatorView.this.jl_scenesMousePressed(evt);
            }
        });
        this.jScrollPane2.setViewportView(this.jl_scenes);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel17.add(this.jScrollPane2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel17, gridBagConstraints);
        this.jPanel13.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel13.setLayout(new GridBagLayout());
        this.sl_effect_lower_value_left.setMaximum(99);
        this.sl_effect_lower_value_left.setMaximumSize(new Dimension(100, 25));
        this.sl_effect_lower_value_left.setMinimumSize(new Dimension(100, 25));
        this.sl_effect_lower_value_left.setPreferredSize(new Dimension(100, 25));
        this.sl_effect_lower_value_left.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_effect_lower_value_leftStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.sl_effect_lower_value_left, gridBagConstraints);
        this.jLabel11.setText("Value");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.jLabel11, gridBagConstraints);
        this.sl_effect_timer_value_left.setMinimum(10);
        this.sl_effect_timer_value_left.setMaximumSize(new Dimension(100, 25));
        this.sl_effect_timer_value_left.setMinimumSize(new Dimension(100, 25));
        this.sl_effect_timer_value_left.setPreferredSize(new Dimension(100, 25));
        this.sl_effect_timer_value_left.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_effect_timer_value_leftStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.sl_effect_timer_value_left, gridBagConstraints);
        this.jLabel12.setText("Lower Limit");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.jLabel12, gridBagConstraints);
        this.jLabel18.setText("Effect");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.jLabel18, gridBagConstraints);
        this.cb_effect_mode_left.setModel(new DefaultComboBoxModel<String>(new String[] { "Strobe", "Rotate", "Blur", "Dynamic Blur", "Move hor.", "Move vert." }));
        this.cb_effect_mode_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_effect_mode_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.cb_effect_mode_left, gridBagConstraints);
        this.jLabel35.setText("Methode");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.jLabel35, gridBagConstraints);
        this.cb_effect_methode_left.setModel(new DefaultComboBoxModel<String>(new String[] { "Manual", "Time", "Sound Level", "Beat" }));
        this.cb_effect_methode_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_effect_methode_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.cb_effect_methode_left, gridBagConstraints);
        this.jLabel36.setText("Upper Limit");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.jLabel36, gridBagConstraints);
        this.sl_effect_value_left.setMaximumSize(new Dimension(100, 25));
        this.sl_effect_value_left.setMinimumSize(new Dimension(100, 25));
        this.sl_effect_value_left.setPreferredSize(new Dimension(100, 25));
        this.sl_effect_value_left.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_effect_value_leftStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.sl_effect_value_left, gridBagConstraints);
        this.jLabel37.setText("Timer Value");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.jLabel37, gridBagConstraints);
        this.sl_effect_upper_value_left.setMinimum(1);
        this.sl_effect_upper_value_left.setMaximumSize(new Dimension(100, 25));
        this.sl_effect_upper_value_left.setMinimumSize(new Dimension(100, 25));
        this.sl_effect_upper_value_left.setPreferredSize(new Dimension(100, 25));
        this.sl_effect_upper_value_left.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_effect_upper_value_leftStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.sl_effect_upper_value_left, gridBagConstraints);
        this.jLabel38.setText("Direction");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.jLabel38, gridBagConstraints);
        this.cb_effect_dir_left.setModel(new DefaultComboBoxModel<String>(new String[] { "Right", "Left", "Toggle" }));
        this.cb_effect_dir_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_effect_dir_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.cb_effect_dir_left, gridBagConstraints);
        this.pb_left_effect_activated.setText("Turn on");
        this.pb_left_effect_activated.setMaximumSize(new Dimension(60, 20));
        this.pb_left_effect_activated.setMinimumSize(new Dimension(60, 20));
        this.pb_left_effect_activated.setPreferredSize(new Dimension(60, 20));
        this.pb_left_effect_activated.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_left_effect_activatedActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel13.add(this.pb_left_effect_activated, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel13, gridBagConstraints);
        this.jPanel18.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel18.setLayout(new GridBagLayout());
        this.pb_add_left_scene_to_list.setText("Add L");
        this.pb_add_left_scene_to_list.setMaximumSize(new Dimension(50, 20));
        this.pb_add_left_scene_to_list.setMinimumSize(new Dimension(50, 20));
        this.pb_add_left_scene_to_list.setPreferredSize(new Dimension(50, 20));
        this.pb_add_left_scene_to_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_add_left_scene_to_listActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_add_left_scene_to_list, gridBagConstraints);
        this.pb_add_right_scene_to_list.setText("Add R");
        this.pb_add_right_scene_to_list.setMaximumSize(new Dimension(50, 20));
        this.pb_add_right_scene_to_list.setMinimumSize(new Dimension(50, 20));
        this.pb_add_right_scene_to_list.setPreferredSize(new Dimension(50, 20));
        this.pb_add_right_scene_to_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_add_right_scene_to_listActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_add_right_scene_to_list, gridBagConstraints);
        this.pb_set_scene_left.setText("Set L");
        this.pb_set_scene_left.setMaximumSize(new Dimension(50, 20));
        this.pb_set_scene_left.setMinimumSize(new Dimension(50, 20));
        this.pb_set_scene_left.setPreferredSize(new Dimension(50, 20));
        this.pb_set_scene_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_set_scene_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_set_scene_left, gridBagConstraints);
        this.pb_set_scene_right.setText("Set R");
        this.pb_set_scene_right.setMaximumSize(new Dimension(50, 20));
        this.pb_set_scene_right.setMinimumSize(new Dimension(50, 20));
        this.pb_set_scene_right.setPreferredSize(new Dimension(50, 20));
        this.pb_set_scene_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_set_scene_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_set_scene_right, gridBagConstraints);
        this.pb_move_scenlist_item_down.setText("Dwn");
        this.pb_move_scenlist_item_down.setMaximumSize(new Dimension(50, 20));
        this.pb_move_scenlist_item_down.setMinimumSize(new Dimension(50, 20));
        this.pb_move_scenlist_item_down.setPreferredSize(new Dimension(50, 20));
        this.pb_move_scenlist_item_down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_move_scenlist_item_downActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_move_scenlist_item_down, gridBagConstraints);
        this.pb_move_scenlist_item_up.setText("Up");
        this.pb_move_scenlist_item_up.setMaximumSize(new Dimension(50, 20));
        this.pb_move_scenlist_item_up.setMinimumSize(new Dimension(50, 20));
        this.pb_move_scenlist_item_up.setPreferredSize(new Dimension(50, 20));
        this.pb_move_scenlist_item_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_move_scenlist_item_upActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_move_scenlist_item_up, gridBagConstraints);
        this.pb_remove_secene_list_item.setText("Rem");
        this.pb_remove_secene_list_item.setMaximumSize(new Dimension(50, 20));
        this.pb_remove_secene_list_item.setMinimumSize(new Dimension(50, 20));
        this.pb_remove_secene_list_item.setPreferredSize(new Dimension(50, 20));
        this.pb_remove_secene_list_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_remove_secene_list_itemActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_remove_secene_list_item, gridBagConstraints);
        this.pb_save_scene_list.setText("Save");
        this.pb_save_scene_list.setMaximumSize(new Dimension(50, 20));
        this.pb_save_scene_list.setMinimumSize(new Dimension(50, 20));
        this.pb_save_scene_list.setPreferredSize(new Dimension(50, 20));
        this.pb_save_scene_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_save_scene_listActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_save_scene_list, gridBagConstraints);
        this.pb_clear_scene_list.setText("Clear");
        this.pb_clear_scene_list.setMaximumSize(new Dimension(50, 20));
        this.pb_clear_scene_list.setMinimumSize(new Dimension(50, 20));
        this.pb_clear_scene_list.setPreferredSize(new Dimension(50, 20));
        this.pb_clear_scene_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_clear_scene_listActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_clear_scene_list, gridBagConstraints);
        this.pb_replace_scenlist_item_left.setText("Rep L");
        this.pb_replace_scenlist_item_left.setMaximumSize(new Dimension(50, 20));
        this.pb_replace_scenlist_item_left.setMinimumSize(new Dimension(50, 20));
        this.pb_replace_scenlist_item_left.setPreferredSize(new Dimension(50, 20));
        this.pb_replace_scenlist_item_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_replace_scenlist_item_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_replace_scenlist_item_left, gridBagConstraints);
        this.pb_replace_scenlist_item_right.setText("Rep R");
        this.pb_replace_scenlist_item_right.setMaximumSize(new Dimension(50, 20));
        this.pb_replace_scenlist_item_right.setMinimumSize(new Dimension(50, 20));
        this.pb_replace_scenlist_item_right.setPreferredSize(new Dimension(50, 20));
        this.pb_replace_scenlist_item_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_replace_scenlist_item_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_replace_scenlist_item_right, gridBagConstraints);
        this.pb_load_scene_list.setText("Load");
        this.pb_load_scene_list.setMaximumSize(new Dimension(50, 20));
        this.pb_load_scene_list.setMinimumSize(new Dimension(50, 20));
        this.pb_load_scene_list.setPreferredSize(new Dimension(50, 20));
        this.pb_load_scene_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_load_scene_listActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel18.add(this.pb_load_scene_list, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel18, gridBagConstraints);
        this.jPanel19.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel19.setLayout(new GridBagLayout());
        this.tf_play_list_fader_time.setText("4000");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel19.add(this.tf_play_list_fader_time, gridBagConstraints);
        this.jLabel2.setText("Scene Time (ms)");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel19.add(this.jLabel2, gridBagConstraints);
        this.cb_play_list_fader_mode.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel19.add(this.cb_play_list_fader_mode, gridBagConstraints);
        this.jLabel15.setText("Fade Time (ms)");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel19.add(this.jLabel15, gridBagConstraints);
        this.tf_play_list_scene_time.setText("4000");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel19.add(this.tf_play_list_scene_time, gridBagConstraints);
        this.jLabel16.setText("Fader");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel19.add(this.jLabel16, gridBagConstraints);
        this.pb_add_to_play_list.setText("Add scene to play list");
        this.pb_add_to_play_list.setActionCommand("Replace");
        this.pb_add_to_play_list.setMaximumSize(new Dimension(50, 20));
        this.pb_add_to_play_list.setMinimumSize(new Dimension(50, 20));
        this.pb_add_to_play_list.setPreferredSize(new Dimension(50, 20));
        this.pb_add_to_play_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_add_to_play_listActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel19.add(this.pb_add_to_play_list, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel19, gridBagConstraints);
        this.jPanel20.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel20.setLayout(new GridBagLayout());
        this.pb_play_list_down.setText("Dwn");
        this.pb_play_list_down.setMaximumSize(new Dimension(50, 20));
        this.pb_play_list_down.setMinimumSize(new Dimension(50, 20));
        this.pb_play_list_down.setPreferredSize(new Dimension(50, 20));
        this.pb_play_list_down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_play_list_downActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel20.add(this.pb_play_list_down, gridBagConstraints);
        this.pb_play_list_up.setText("Up");
        this.pb_play_list_up.setMaximumSize(new Dimension(50, 20));
        this.pb_play_list_up.setMinimumSize(new Dimension(50, 20));
        this.pb_play_list_up.setPreferredSize(new Dimension(50, 20));
        this.pb_play_list_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_play_list_upActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel20.add(this.pb_play_list_up, gridBagConstraints);
        this.pb_play_list_save.setText("Save");
        this.pb_play_list_save.setMaximumSize(new Dimension(50, 20));
        this.pb_play_list_save.setMinimumSize(new Dimension(50, 20));
        this.pb_play_list_save.setPreferredSize(new Dimension(50, 20));
        this.pb_play_list_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_play_list_saveActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel20.add(this.pb_play_list_save, gridBagConstraints);
        this.pb_play_list_load.setText("Load");
        this.pb_play_list_load.setMaximumSize(new Dimension(50, 20));
        this.pb_play_list_load.setMinimumSize(new Dimension(50, 20));
        this.pb_play_list_load.setPreferredSize(new Dimension(50, 20));
        this.pb_play_list_load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_play_list_loadActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel20.add(this.pb_play_list_load, gridBagConstraints);
        this.pb_play_list_replace.setText("Rep");
        this.pb_play_list_replace.setActionCommand("Replace");
        this.pb_play_list_replace.setMaximumSize(new Dimension(50, 20));
        this.pb_play_list_replace.setMinimumSize(new Dimension(50, 20));
        this.pb_play_list_replace.setPreferredSize(new Dimension(50, 20));
        this.pb_play_list_replace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_play_list_replaceActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel20.add(this.pb_play_list_replace, gridBagConstraints);
        this.pb_play_list_clear.setActionCommand("Replace");
        this.pb_play_list_clear.setLabel("Clear");
        this.pb_play_list_clear.setMaximumSize(new Dimension(50, 20));
        this.pb_play_list_clear.setMinimumSize(new Dimension(50, 20));
        this.pb_play_list_clear.setPreferredSize(new Dimension(50, 20));
        this.pb_play_list_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_play_list_clearActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel20.add(this.pb_play_list_clear, gridBagConstraints);
        this.pb_play_list_remove_item.setText("Rem");
        this.pb_play_list_remove_item.setMaximumSize(new Dimension(50, 20));
        this.pb_play_list_remove_item.setMinimumSize(new Dimension(50, 20));
        this.pb_play_list_remove_item.setPreferredSize(new Dimension(50, 20));
        this.pb_play_list_remove_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_play_list_remove_itemActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel20.add(this.pb_play_list_remove_item, gridBagConstraints);
        this.pb_play_list_play.setText("Start playing");
        this.pb_play_list_play.setActionCommand("Replace");
        this.pb_play_list_play.setMaximumSize(new Dimension(50, 20));
        this.pb_play_list_play.setMinimumSize(new Dimension(50, 20));
        this.pb_play_list_play.setPreferredSize(new Dimension(50, 20));
        this.pb_play_list_play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_play_list_playActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel20.add(this.pb_play_list_play, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel20, gridBagConstraints);
        this.jPanel21.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel21.setLayout(new GridBagLayout());
        this.cb_right_generator_type.setModel(new DefaultComboBoxModel<String>(new String[] { "None", "Fading Objects", "Falling Objects", "Simple Spectrum", "Knight Rider", "Plasma" }));
        this.cb_right_generator_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_right_generator_typeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.cb_right_generator_type, gridBagConstraints);
        this.sl_generator_level_right.setMaximumSize(new Dimension(100, 25));
        this.sl_generator_level_right.setMinimumSize(new Dimension(100, 25));
        this.sl_generator_level_right.setPreferredSize(new Dimension(100, 25));
        this.sl_generator_level_right.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_generator_level_rightStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.sl_generator_level_right, gridBagConstraints);
        this.jLabel34.setText("Level");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.jLabel34, gridBagConstraints);
        this.sl_speed_right.setMaximum(20);
        this.sl_speed_right.setValue(1);
        this.sl_speed_right.setMaximumSize(new Dimension(100, 25));
        this.sl_speed_right.setMinimumSize(new Dimension(100, 25));
        this.sl_speed_right.setPreferredSize(new Dimension(100, 25));
        this.sl_speed_right.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_speed_rightStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.sl_speed_right, gridBagConstraints);
        this.jLabel39.setText("Speed");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.jLabel39, gridBagConstraints);
        this.cb_mixer_mode_right.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        this.cb_mixer_mode_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_mixer_mode_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.cb_mixer_mode_right, gridBagConstraints);
        this.jLabel40.setText("Mixer");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.jLabel40, gridBagConstraints);
        this.pb_configure_generator_right.setText("Configure generator");
        this.pb_configure_generator_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_configure_generator_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.pb_configure_generator_right, gridBagConstraints);
        this.jLabel46.setText("Type");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.jLabel46, gridBagConstraints);
        this.jLabel47.setText("Filter");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.jLabel47, gridBagConstraints);
        this.cb_filter_mode_right.setModel(new DefaultComboBoxModel<String>(new String[] { "None", "Red", "Green", "Blue", "Greyscale", "Invert", "Thresh. 1", "Thresh. 2" }));
        this.cb_filter_mode_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.cb_filter_mode_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel21.add(this.cb_filter_mode_right, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel21, gridBagConstraints);
        this.jPanel22.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel22.setLayout(new GridBagLayout());
        this.sl_master_strobe_duration.setMaximum(300);
        this.sl_master_strobe_duration.setMinimum(30);
        this.sl_master_strobe_duration.setMaximumSize(new Dimension(100, 25));
        this.sl_master_strobe_duration.setMinimumSize(new Dimension(100, 25));
        this.sl_master_strobe_duration.setPreferredSize(new Dimension(100, 25));
        this.sl_master_strobe_duration.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_master_strobe_durationStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel22.add(this.sl_master_strobe_duration, gridBagConstraints);
        this.pb_master_strobe.setText("Strobe");
        this.pb_master_strobe.setMaximumSize(new Dimension(60, 20));
        this.pb_master_strobe.setMinimumSize(new Dimension(60, 20));
        this.pb_master_strobe.setPreferredSize(new Dimension(60, 20));
        this.pb_master_strobe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.pb_master_strobeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel22.add(this.pb_master_strobe, gridBagConstraints);
        this.jLabel48.setText("Interval");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel22.add(this.jLabel48, gridBagConstraints);
        this.jLabel17.setText("Duration");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel22.add(this.jLabel17, gridBagConstraints);
        this.sl_master_strobe_interval.setMaximum(300);
        this.sl_master_strobe_interval.setMinimum(30);
        this.sl_master_strobe_interval.setMaximumSize(new Dimension(100, 25));
        this.sl_master_strobe_interval.setMinimumSize(new Dimension(100, 25));
        this.sl_master_strobe_interval.setPreferredSize(new Dimension(100, 25));
        this.sl_master_strobe_interval.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GlediatorView.this.sl_master_strobe_intervalStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel22.add(this.sl_master_strobe_interval, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel22, gridBagConstraints);
        this.jMenu2.setText("File");
        this.mi_load.setText("Load Project");
        this.mi_load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.mi_loadActionPerformed(evt);
            }
        });
        this.jMenu2.add(this.mi_load);
        this.mi_save.setText("Save Project");
        this.mi_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.mi_saveActionPerformed(evt);
            }
        });
        this.jMenu2.add(this.mi_save);
        this.mi_exit.setText("Exit");
        this.mi_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.mi_exitActionPerformed(evt);
            }
        });
        this.jMenu2.add(this.mi_exit);
        this.jMenuBar1.add(this.jMenu2);
        this.jMenu3.setText("Options");
        this.mi_matrix_size.setText("Matrix Size");
        this.mi_matrix_size.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.mi_matrix_sizeActionPerformed(evt);
            }
        });
        this.jMenu3.add(this.mi_matrix_size);
        this.mi_output.setText("Output");
        this.mi_output.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.mi_outputActionPerformed(evt);
            }
        });
        this.jMenu3.add(this.mi_output);
        this.jMenuBar1.add(this.jMenu3);
        this.jMenu5.setText("Extras");
        this.mi_recorder.setText("Recorder");
        this.mi_recorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.mi_recorderActionPerformed(evt);
            }
        });
        this.jMenu5.add(this.mi_recorder);
        this.mi_remote_control.setText("Remote Control");
        this.mi_remote_control.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.mi_remote_controlActionPerformed(evt);
            }
        });
        this.jMenu5.add(this.mi_remote_control);
        this.jMenuBar1.add(this.jMenu5);
        this.jMenu4.setText("About");
        this.mi_about.setText("About Glediator");
        this.mi_about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GlediatorView.this.mi_aboutActionPerformed(evt);
            }
        });
        this.jMenu4.add(this.mi_about);
        this.jMenuBar1.add(this.jMenu4);
        this.setJMenuBar(this.jMenuBar1);
        this.pack();
    }
    
    private void pb_select_generator_right_1ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 0;
            this.block_events = true;
            this.pb_select_generator_right_1.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_right_2.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_3.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_4.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_5.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.right_scene.setActiveGenerator(index);
            this.cb_right_generator_type.setSelectedItem(this.glediator_model.right_scene.getGeneratorType(index));
            this.cb_filter_mode_right.setSelectedItem(this.glediator_model.right_scene.getFilterMode(index));
            this.cb_mixer_mode_right.setSelectedItem(this.glediator_model.right_scene.getMixerMode(index));
            this.sl_generator_level_right.setValue(this.glediator_model.right_scene.getLevel(index));
            this.sl_speed_right.setValue(this.glediator_model.right_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void pb_select_generator_left_1ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 0;
            this.block_events = true;
            this.pb_select_generator_left_1.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_left_2.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_3.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_4.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_5.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.left_scene.setActiveGenerator(index);
            this.cb_left_generator_type.setSelectedItem(this.glediator_model.left_scene.getGeneratorType(index));
            this.cb_filter_mode_left.setSelectedItem(this.glediator_model.left_scene.getFilterMode(index));
            this.cb_mixer_mode_left.setSelectedItem(this.glediator_model.left_scene.getMixerMode(index));
            this.sl_generator_level_left.setValue(this.glediator_model.left_scene.getLevel(index));
            this.sl_speed_left.setValue(this.glediator_model.left_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void pb_configure_generator_leftActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.showGeneratorConfigurationeWindow();
        }
    }
    
    private void pb_add_right_scene_to_listActionPerformed(final ActionEvent evt) {
        final int index = this.jl_scenes.getSelectedIndex();
        this.glediator_model.scene_list.addScene(this.glediator_model.right_scene, index);
    }
    
    private void pb_set_scene_rightActionPerformed(final ActionEvent evt) {
        final int index = this.jl_scenes.getSelectedIndex();
        if (index >= 0) {
            this.glediator_model.right_scene.setScene(this.glediator_model.scene_list.getSceneParameter(index));
            this.updateRightControlls();
        }
    }
    
    private void pb_move_scenlist_item_downActionPerformed(final ActionEvent evt) {
        final int index = this.jl_scenes.getSelectedIndex();
        this.glediator_model.scene_list.moveDown(index);
    }
    
    private void pb_move_scenlist_item_upActionPerformed(final ActionEvent evt) {
        final int index = this.jl_scenes.getSelectedIndex();
        this.glediator_model.scene_list.moveUp(index);
    }
    
    private void pb_remove_secene_list_itemActionPerformed(final ActionEvent evt) {
        final int index = this.jl_scenes.getSelectedIndex();
        this.glediator_model.scene_list.removeListeEntry(index);
    }
    
    private void pb_save_scene_listActionPerformed(final ActionEvent evt) {
        this.glediator_model.scene_list.saveSceneList(this);
    }
    
    private void pb_clear_scene_listActionPerformed(final ActionEvent evt) {
        this.glediator_model.scene_list.clearList();
        this.scene_index = -1;
    }
    
    private void pb_play_list_downActionPerformed(final ActionEvent evt) {
        final int index = this.jl_play_list.getSelectedIndex();
        this.glediator_model.play_list.moveDown(index);
    }
    
    private void pb_play_list_upActionPerformed(final ActionEvent evt) {
        final int index = this.jl_play_list.getSelectedIndex();
        this.glediator_model.play_list.moveUp(index);
    }
    
    private void pb_play_list_saveActionPerformed(final ActionEvent evt) {
        this.glediator_model.play_list.savePlayList(this);
    }
    
    private void pb_play_list_loadActionPerformed(final ActionEvent evt) {
        this.glediator_model.play_list.loadPlayList(this);
    }
    
    private void pb_load_scene_listActionPerformed(final ActionEvent evt) {
        this.glediator_model.scene_list.loadSceneList(this);
        if (this.glediator_model.scene_list.getNumberOfEntries() > 0) {
            this.jl_scenes.setSelectedIndex(0);
        }
        else {
            this.jl_scenes.setSelectedIndex(-1);
        }
    }
    
    private void pb_select_generator_right_4ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 3;
            this.block_events = true;
            this.pb_select_generator_right_4.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_right_2.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_3.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_1.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_5.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.right_scene.setActiveGenerator(index);
            this.cb_right_generator_type.setSelectedItem(this.glediator_model.right_scene.getGeneratorType(index));
            this.cb_filter_mode_right.setSelectedItem(this.glediator_model.right_scene.getFilterMode(index));
            this.cb_mixer_mode_right.setSelectedItem(this.glediator_model.right_scene.getMixerMode(index));
            this.sl_generator_level_right.setValue(this.glediator_model.right_scene.getLevel(index));
            this.sl_speed_right.setValue(this.glediator_model.right_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void pb_configure_generator_rightActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.showGeneratorConfigurationeWindow();
        }
    }
    
    private void cb_mixer_mode_leftActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.setMixerMode((Mixer.MixerMode)this.cb_mixer_mode_left.getSelectedItem());
        }
    }
    
    private void cb_effect_mode_leftActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.effect.setEffectMode((Effect.EffectMode)this.cb_effect_mode_left.getSelectedItem());
        }
    }
    
    private void cb_left_generator_typeActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.setGeneratorType((SuperGenerator.GeneratorTypes)this.cb_left_generator_type.getSelectedItem());
        }
    }
    
    private void cb_right_generator_typeActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.setGeneratorType((SuperGenerator.GeneratorTypes)this.cb_right_generator_type.getSelectedItem());
        }
    }
    
    private void pb_select_generator_left_3ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 2;
            this.block_events = true;
            this.pb_select_generator_left_3.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_left_2.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_1.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_4.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_5.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.left_scene.setActiveGenerator(index);
            this.cb_left_generator_type.setSelectedItem(this.glediator_model.left_scene.getGeneratorType(index));
            this.cb_filter_mode_left.setSelectedItem(this.glediator_model.left_scene.getFilterMode(index));
            this.cb_mixer_mode_left.setSelectedItem(this.glediator_model.left_scene.getMixerMode(index));
            this.sl_generator_level_left.setValue(this.glediator_model.left_scene.getLevel(index));
            this.sl_speed_left.setValue(this.glediator_model.left_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void pb_select_generator_left_4ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 3;
            this.block_events = true;
            this.pb_select_generator_left_4.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_left_2.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_3.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_1.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_5.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.left_scene.setActiveGenerator(index);
            this.cb_left_generator_type.setSelectedItem(this.glediator_model.left_scene.getGeneratorType(index));
            this.cb_filter_mode_left.setSelectedItem(this.glediator_model.left_scene.getFilterMode(index));
            this.cb_mixer_mode_left.setSelectedItem(this.glediator_model.left_scene.getMixerMode(index));
            this.sl_generator_level_left.setValue(this.glediator_model.left_scene.getLevel(index));
            this.sl_speed_left.setValue(this.glediator_model.left_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void pb_select_generator_right_3ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 2;
            this.block_events = true;
            this.pb_select_generator_right_3.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_right_2.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_1.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_4.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_5.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.right_scene.setActiveGenerator(index);
            this.cb_right_generator_type.setSelectedItem(this.glediator_model.right_scene.getGeneratorType(index));
            this.cb_filter_mode_right.setSelectedItem(this.glediator_model.right_scene.getFilterMode(index));
            this.cb_mixer_mode_right.setSelectedItem(this.glediator_model.right_scene.getMixerMode(index));
            this.sl_generator_level_right.setValue(this.glediator_model.right_scene.getLevel(index));
            this.sl_speed_right.setValue(this.glediator_model.right_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void pb_select_generator_right_5ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 4;
            this.block_events = true;
            this.pb_select_generator_right_5.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_right_2.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_3.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_4.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_1.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.right_scene.setActiveGenerator(index);
            this.cb_right_generator_type.setSelectedItem(this.glediator_model.right_scene.getGeneratorType(index));
            this.cb_filter_mode_right.setSelectedItem(this.glediator_model.right_scene.getFilterMode(index));
            this.cb_mixer_mode_right.setSelectedItem(this.glediator_model.right_scene.getMixerMode(index));
            this.sl_generator_level_right.setValue(this.glediator_model.right_scene.getLevel(index));
            this.sl_speed_right.setValue(this.glediator_model.right_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void pb_select_generator_left_2ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 1;
            this.block_events = true;
            this.pb_select_generator_left_2.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_left_1.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_3.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_4.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_5.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.left_scene.setActiveGenerator(index);
            this.cb_left_generator_type.setSelectedItem(this.glediator_model.left_scene.getGeneratorType(index));
            this.cb_filter_mode_left.setSelectedItem(this.glediator_model.left_scene.getFilterMode(index));
            this.cb_mixer_mode_left.setSelectedItem(this.glediator_model.left_scene.getMixerMode(index));
            this.sl_generator_level_left.setValue(this.glediator_model.left_scene.getLevel(index));
            this.sl_speed_left.setValue(this.glediator_model.left_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void pb_select_generator_left_5ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 4;
            this.block_events = true;
            this.pb_select_generator_left_5.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_left_2.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_3.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_4.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_left_1.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.left_scene.setActiveGenerator(index);
            this.cb_left_generator_type.setSelectedItem(this.glediator_model.left_scene.getGeneratorType(index));
            this.cb_filter_mode_left.setSelectedItem(this.glediator_model.left_scene.getFilterMode(index));
            this.cb_mixer_mode_left.setSelectedItem(this.glediator_model.left_scene.getMixerMode(index));
            this.sl_generator_level_left.setValue(this.glediator_model.left_scene.getLevel(index));
            this.sl_speed_left.setValue(this.glediator_model.left_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void pb_select_generator_right_2ActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            final int index = 1;
            this.block_events = true;
            this.pb_select_generator_right_2.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.pb_select_generator_right_1.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_3.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_4.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_select_generator_right_5.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.glediator_model.right_scene.setActiveGenerator(index);
            this.cb_right_generator_type.setSelectedItem(this.glediator_model.right_scene.getGeneratorType(index));
            this.cb_filter_mode_right.setSelectedItem(this.glediator_model.right_scene.getFilterMode(index));
            this.cb_mixer_mode_right.setSelectedItem(this.glediator_model.right_scene.getMixerMode(index));
            this.sl_generator_level_right.setValue(this.glediator_model.right_scene.getLevel(index));
            this.sl_speed_right.setValue(this.glediator_model.right_scene.getGeneratorSpeed(index));
            this.block_events = false;
        }
    }
    
    private void cb_filter_mode_leftActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.setFilterMode((Filter.FilterMode)this.cb_filter_mode_left.getSelectedItem());
        }
    }
    
    private void cb_filter_mode_rightActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.setFilterMode((Filter.FilterMode)this.cb_filter_mode_right.getSelectedItem());
        }
    }
    
    private void cb_mixer_mode_rightActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.setMixerMode((Mixer.MixerMode)this.cb_mixer_mode_right.getSelectedItem());
        }
    }
    
    private void sl_generator_level_leftStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.setLevel(this.sl_generator_level_left.getValue());
        }
    }
    
    private void sl_generator_level_rightStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.setLevel(this.sl_generator_level_right.getValue());
        }
    }
    
    private void sl_speed_leftStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.setGeneratorSpeed(this.sl_speed_left.getValue());
        }
    }
    
    private void sl_speed_rightStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.setGeneratorSpeed(this.sl_speed_right.getValue());
        }
    }
    
    private void cb_effect_dir_leftActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.effect.setEffectDirection((Effect.EffectDirection)this.cb_effect_dir_left.getSelectedItem());
        }
    }
    
    private void cb_effect_dir_rightActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.effect.setEffectDirection((Effect.EffectDirection)this.cb_effect_dir_right.getSelectedItem());
        }
    }
    
    private void cb_effect_methode_leftActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.effect.setEffectMethode((Effect.EffectMethode)this.cb_effect_methode_left.getSelectedItem());
        }
    }
    
    private void cb_effect_methode_rightActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.effect.setEffectMethode((Effect.EffectMethode)this.cb_effect_methode_right.getSelectedItem());
        }
    }
    
    private void sl_effect_value_leftStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.effect.setValue(this.sl_effect_value_left.getValue());
        }
    }
    
    private void sl_effect_value_rightStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.effect.setValue(this.sl_effect_value_right.getValue());
        }
    }
    
    private void sl_effect_lower_value_leftStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            if (this.sl_effect_lower_value_left.getValue() < this.sl_effect_upper_value_left.getValue()) {
                this.glediator_model.left_scene.effect.setLowerLimit(this.sl_effect_lower_value_left.getValue());
            }
            else {
                this.sl_effect_upper_value_left.setValue(this.sl_effect_lower_value_left.getValue() + 1);
            }
        }
    }
    
    private void sl_effect_lower_value_rightStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            if (this.sl_effect_lower_value_right.getValue() < this.sl_effect_upper_value_right.getValue()) {
                this.glediator_model.right_scene.effect.setLowerLimit(this.sl_effect_lower_value_right.getValue());
            }
            else {
                this.sl_effect_upper_value_right.setValue(this.sl_effect_lower_value_right.getValue() + 1);
            }
        }
    }
    
    private void sl_effect_upper_value_leftStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            if (this.sl_effect_lower_value_left.getValue() < this.sl_effect_upper_value_left.getValue()) {
                this.glediator_model.left_scene.effect.setUpperLimit(this.sl_effect_upper_value_left.getValue());
            }
            else {
                this.sl_effect_lower_value_left.setValue(this.sl_effect_upper_value_left.getValue() - 1);
            }
        }
    }
    
    private void sl_effect_upper_value_rightStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            if (this.sl_effect_lower_value_right.getValue() < this.sl_effect_upper_value_right.getValue()) {
                this.glediator_model.right_scene.effect.setUpperLimit(this.sl_effect_upper_value_right.getValue());
            }
            else {
                this.sl_effect_lower_value_right.setValue(this.sl_effect_upper_value_right.getValue() - 1);
            }
        }
    }
    
    private void sl_effect_timer_value_leftStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.left_scene.effect.setTimerValue(this.sl_effect_timer_value_left.getValue());
        }
    }
    
    private void sl_effect_timer_value_rightStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.effect.setTimerValue(this.sl_effect_timer_value_right.getValue());
        }
    }
    
    private void sl_main_faderStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.fader.setFaderValue(this.sl_main_fader.getValue());
        }
    }
    
    private void cb_fader_modeActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.fader.setFaderMode((Fader.FaderMode)this.cb_fader_mode.getSelectedItem());
        }
    }
    
    private void sl_main_fader_timeStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.fader.setFadeTime(this.sl_main_fader_time.getValue());
        }
    }
    
    private void sl_left_levelStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.fader.setLeftIntensity(this.sl_left_level.getValue());
        }
    }
    
    private void sl_right_levelStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.fader.setRightIntensity(this.sl_right_level.getValue());
        }
    }
    
    private void sl_master_levelStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.fader.setMasterIntensity(this.sl_master_level.getValue());
        }
    }
    
    private void pb_set_fader_leftActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.sl_main_fader.setValue(0);
        }
    }
    
    private void pb_set_fader_centerActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.sl_main_fader.setValue(50);
        }
    }
    
    private void pb_set_fader_rightActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.sl_main_fader.setValue(100);
        }
    }
    
    private void pb_set_scene_leftActionPerformed(final ActionEvent evt) {
        final int index = this.jl_scenes.getSelectedIndex();
        if (index >= 0) {
            this.glediator_model.left_scene.setScene(this.glediator_model.scene_list.getSceneParameter(index));
            this.updateLeftControlls();
        }
    }
    
    private void pb_add_left_scene_to_listActionPerformed(final ActionEvent evt) {
        final int index = this.jl_scenes.getSelectedIndex();
        this.glediator_model.scene_list.addScene(this.glediator_model.left_scene, index);
    }
    
    private void pb_replace_scenlist_item_leftActionPerformed(final ActionEvent evt) {
        final int index = this.jl_scenes.getSelectedIndex();
        this.glediator_model.scene_list.replaceScene(this.glediator_model.left_scene, index);
    }
    
    private void pb_replace_scenlist_item_rightActionPerformed(final ActionEvent evt) {
        final int index = this.jl_scenes.getSelectedIndex();
        this.glediator_model.scene_list.replaceScene(this.glediator_model.right_scene, index);
    }
    
    private void pb_auto_fadeActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.startFading();
            this.pb_auto_fade.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.sl_main_fader.setEnabled(false);
            this.pb_auto_fade.setEnabled(false);
        }
    }
    
    private void jl_scenesMousePressed(final MouseEvent evt) {
        this.scene_index = this.jl_scenes.getSelectedIndex();
    }
    
    private void panel_leftMouseClicked(final MouseEvent evt) {
        if (this.scene_index != -1) {
            this.glediator_model.left_scene.setScene(this.glediator_model.scene_list.getSceneParameter(this.scene_index));
            this.updateLeftControlls();
        }
    }
    
    private void panel_rightMouseClicked(final MouseEvent evt) {
        if (this.scene_index != -1) {
            this.glediator_model.right_scene.setScene(this.glediator_model.scene_list.getSceneParameter(this.scene_index));
            this.updateRightControlls();
        }
    }
    
    private void cb_effect_mode_rightActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.right_scene.effect.setEffectMode((Effect.EffectMode)this.cb_effect_mode_right.getSelectedItem());
        }
    }
    
    private void pb_left_effect_activatedActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            if (this.pb_left_effect_activated.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
                this.glediator_model.left_scene.effect.setActiveState(false);
                this.pb_left_effect_activated.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            }
            else {
                this.glediator_model.left_scene.effect.setActiveState(true);
                this.pb_left_effect_activated.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            }
        }
    }
    
    private void pb_black_outActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.startBlackOut();
            this.pb_black_out.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            this.sl_master_level.setEnabled(false);
            this.pb_black_out.setEnabled(false);
        }
    }
    
    private void pb_start_audio_capturingActionPerformed(final ActionEvent evt) {
        this.glediator_model.startAudioCapture(this.cb_audio_sources.getSelectedIndex());
    }
    
    private void pb_stop_audio_capturingActionPerformed(final ActionEvent evt) {
        this.glediator_model.stopAudioCapture();
    }
    
    private void sl_audio_gainStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.setAudioGain(this.sl_audio_gain.getValue());
        }
    }
    
    private void sl_trigger_levelStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.setAudioTriggerLevel(this.sl_trigger_level.getValue());
        }
    }
    
    private void pb_master_strobeActionPerformed(final ActionEvent evt) {
        if (this.pb_master_strobe.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            this.glediator_model.deactivateStrobe();
            this.pb_master_strobe.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
        }
        else {
            this.glediator_model.activateStrobe();
            this.pb_master_strobe.setBackground(GlediatorView.HIGHLIGHT_COLOR);
        }
    }
    
    private void sl_master_strobe_intervalStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.setStrobeInterval(this.sl_master_strobe_interval.getValue());
        }
    }
    
    private void sl_master_strobe_durationStateChanged(final ChangeEvent evt) {
        if (!this.block_events) {
            this.glediator_model.setStrobeDuration(this.sl_master_strobe_duration.getValue());
        }
    }
    
    private void pb_add_to_play_listActionPerformed(final ActionEvent evt) {
        int scene_time = 5000;
        int fade_time = 200;
        try {
            scene_time = Integer.parseInt(this.tf_play_list_scene_time.getText());
            fade_time = Integer.parseInt(this.tf_play_list_fader_time.getText());
        }
        catch (Exception ex) {
            System.out.println("GlediatorView: Error parsing play list parameters.");
        }
        final Fader.FaderMode fader_mode = (Fader.FaderMode)this.cb_play_list_fader_mode.getSelectedItem();
        final int index = this.jl_play_list.getSelectedIndex();
        final int scene_list_index = this.jl_scenes.getSelectedIndex();
        fade_time /= 100;
        if (scene_list_index >= 0 && scene_time > 0 && fade_time >= 1 && fade_time <= 100) {
            final SceneParameter scene_paramter = this.glediator_model.scene_list.getSceneParameter(scene_list_index);
            final PlayListItem play_list_item = new PlayListItem();
            play_list_item.setSceneParameter(scene_paramter);
            play_list_item.setSceneTime(scene_time);
            play_list_item.setFadeTime(fade_time);
            play_list_item.setFaderMode(fader_mode);
            this.glediator_model.play_list.addEntry(play_list_item, index);
        }
        else {
            System.out.println("GlediatorView: Wrong play list entry value(s).");
        }
    }
    
    private void pb_play_list_replaceActionPerformed(final ActionEvent evt) {
        int scene_time = 5000;
        int fade_time = 200;
        try {
            scene_time = Integer.parseInt(this.tf_play_list_scene_time.getText());
            fade_time = Integer.parseInt(this.tf_play_list_fader_time.getText());
        }
        catch (Exception ex) {
            System.out.println("GlediatorView: Error parsing play list parameters.");
        }
        final Fader.FaderMode fader_mode = (Fader.FaderMode)this.cb_play_list_fader_mode.getSelectedItem();
        final int index = this.jl_play_list.getSelectedIndex();
        final int scene_list_index = this.jl_scenes.getSelectedIndex();
        if (scene_list_index >= 0 && scene_time > 0 && fade_time >= 1 && fade_time <= 100) {
            final SceneParameter scene_paramter = this.glediator_model.scene_list.getSceneParameter(scene_list_index);
            final PlayListItem play_list_item = new PlayListItem();
            play_list_item.setSceneParameter(scene_paramter);
            play_list_item.setSceneTime(scene_time);
            play_list_item.setFadeTime(fade_time);
            play_list_item.setFaderMode(fader_mode);
            this.glediator_model.play_list.replaceEntry(play_list_item, index);
        }
        else {
            System.out.println("GlediatorView: Wrong play list entry value(s).");
        }
    }
    
    private void pb_play_list_remove_itemActionPerformed(final ActionEvent evt) {
        final int index = this.jl_play_list.getSelectedIndex();
        this.glediator_model.play_list.removeListeEntry(index);
        if (this.glediator_model.play_list.getNumberOfEntries() == 0 && this.pb_play_list_play.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            this.pb_play_list_clear.setEnabled(true);
            this.pb_play_list_load.setEnabled(true);
            this.pb_play_list_play.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_play_list_play.setText("Start playing");
            this.glediator_model.stopPlayList();
        }
    }
    
    private void pb_play_list_clearActionPerformed(final ActionEvent evt) {
        this.glediator_model.play_list.clearList();
    }
    
    private void pb_play_list_playActionPerformed(final ActionEvent evt) {
        if (this.pb_play_list_play.getBackground().equals(GlediatorView.DEFAULT_BTN_COLOR)) {
            if (this.glediator_model.play_list.getNumberOfEntries() > 0) {
                this.pb_play_list_clear.setEnabled(false);
                this.pb_play_list_load.setEnabled(false);
                this.pb_play_list_play.setBackground(GlediatorView.HIGHLIGHT_COLOR);
                this.pb_play_list_play.setText("Stop playing");
                this.glediator_model.startPlayList();
            }
        }
        else {
            this.pb_play_list_clear.setEnabled(true);
            this.pb_play_list_load.setEnabled(true);
            this.pb_play_list_play.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            this.pb_play_list_play.setText("Start playing");
            this.glediator_model.stopPlayList();
        }
    }
    
    private void jl_play_listMousePressed(final MouseEvent evt) {
        final int index = this.jl_play_list.getSelectedIndex();
        if (index != -1) {
            this.block_events = true;
            this.tf_play_list_fader_time.setText("" + this.glediator_model.play_list.getEntry(index).getFadeTime() * 100);
            this.tf_play_list_scene_time.setText("" + this.glediator_model.play_list.getEntry(index).getSceneTime());
            this.cb_play_list_fader_mode.setSelectedItem(this.glediator_model.play_list.getEntry(index).getFaderMode());
            this.block_events = false;
        }
    }
    
    private void mi_loadActionPerformed(final ActionEvent evt) {
        this.glediator_model.loadAll(this);
    }
    
    private void mi_exitActionPerformed(final ActionEvent evt) {
        this.glediator_model.autoSave();
        System.exit(0);
    }
    
    private void mi_matrix_sizeActionPerformed(final ActionEvent evt) {
        this.glediator_model.showMatrixSizeWindow(this);
    }
    
    private void mi_outputActionPerformed(final ActionEvent evt) {
        this.glediator_model.showOptionWindow(this);
    }
    
    private void mi_saveActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            this.glediator_model.saveAllAs(this);
        }
    }
    
    private void mi_recorderActionPerformed(final ActionEvent evt) {
        this.glediator_model.showPlayer(this);
    }
    
    private void mi_aboutActionPerformed(final ActionEvent evt) {
        this.glediator_model.showBanner(this);
    }
    
    private void pb_logoActionPerformed(final ActionEvent evt) {
        this.glediator_model.showBanner(this);
    }
    
    private void formWindowClosing(final WindowEvent evt) {
        this.glediator_model.autoSave();
    }
    
    private void mi_remote_controlActionPerformed(final ActionEvent evt) {
        this.glediator_model.showRemoteControl(this);
    }
    
    private void pb_right_effect_activatedActionPerformed(final ActionEvent evt) {
        if (!this.block_events) {
            if (this.pb_right_effect_activated.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
                this.glediator_model.right_scene.effect.setActiveState(false);
                this.pb_right_effect_activated.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
            }
            else {
                this.glediator_model.right_scene.effect.setActiveState(true);
                this.pb_right_effect_activated.setBackground(GlediatorView.HIGHLIGHT_COLOR);
            }
        }
    }
    
    private int getSelectedGeneratorLeft() {
        int result = 0;
        if (this.pb_select_generator_left_1.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 0;
        }
        if (this.pb_select_generator_left_2.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 1;
        }
        if (this.pb_select_generator_left_3.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 2;
        }
        if (this.pb_select_generator_left_4.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 3;
        }
        if (this.pb_select_generator_left_5.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 4;
        }
        return result;
    }
    
    private int getSelectedGeneratorRight() {
        int result = 0;
        if (this.pb_select_generator_right_1.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 0;
        }
        if (this.pb_select_generator_right_2.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 1;
        }
        if (this.pb_select_generator_right_3.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 2;
        }
        if (this.pb_select_generator_right_4.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 3;
        }
        if (this.pb_select_generator_right_5.getBackground().equals(GlediatorView.HIGHLIGHT_COLOR)) {
            result = 4;
        }
        return result;
    }
    
    private void updateLeftControlls() {
        final int index = this.getSelectedGeneratorLeft();
        this.block_events = true;
        this.cb_left_generator_type.setSelectedItem(this.glediator_model.left_scene.getGeneratorType(index));
        this.cb_filter_mode_left.setSelectedItem(this.glediator_model.left_scene.getFilterMode(index));
        this.cb_mixer_mode_left.setSelectedItem(this.glediator_model.left_scene.getMixerMode(index));
        this.sl_generator_level_left.setValue(this.glediator_model.left_scene.getLevel(index));
        this.sl_speed_left.setValue(this.glediator_model.left_scene.getGeneratorSpeed(index));
        this.cb_effect_mode_left.setSelectedItem(this.glediator_model.left_scene.effect.getEffectMode());
        this.cb_effect_dir_left.setSelectedItem(this.glediator_model.left_scene.effect.getEffectDirection());
        this.cb_effect_methode_left.setSelectedItem(this.glediator_model.left_scene.effect.getEffectMethode());
        this.sl_effect_value_left.setValue(this.glediator_model.left_scene.effect.getValue());
        this.sl_effect_lower_value_left.setValue(this.glediator_model.left_scene.effect.getLowerLimit());
        this.sl_effect_upper_value_left.setValue(this.glediator_model.left_scene.effect.getUpperLimit());
        this.sl_effect_timer_value_left.setValue(this.glediator_model.left_scene.effect.getTimerValue());
        if (this.glediator_model.left_scene.effect.getActiveState()) {
            this.pb_left_effect_activated.setBackground(GlediatorView.HIGHLIGHT_COLOR);
        }
        else {
            this.pb_left_effect_activated.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
        }
        this.block_events = false;
    }
    
    private void updateRightControlls() {
        final int index = this.getSelectedGeneratorRight();
        this.block_events = true;
        this.cb_right_generator_type.setSelectedItem(this.glediator_model.right_scene.getGeneratorType(index));
        this.cb_filter_mode_right.setSelectedItem(this.glediator_model.right_scene.getFilterMode(index));
        this.cb_mixer_mode_right.setSelectedItem(this.glediator_model.right_scene.getMixerMode(index));
        this.sl_generator_level_right.setValue(this.glediator_model.right_scene.getLevel(index));
        this.sl_speed_right.setValue(this.glediator_model.right_scene.getGeneratorSpeed(index));
        this.cb_effect_mode_right.setSelectedItem(this.glediator_model.right_scene.effect.getEffectMode());
        this.cb_effect_dir_right.setSelectedItem(this.glediator_model.right_scene.effect.getEffectDirection());
        this.cb_effect_methode_right.setSelectedItem(this.glediator_model.right_scene.effect.getEffectMethode());
        this.sl_effect_value_right.setValue(this.glediator_model.right_scene.effect.getValue());
        this.sl_effect_lower_value_right.setValue(this.glediator_model.right_scene.effect.getLowerLimit());
        this.sl_effect_upper_value_right.setValue(this.glediator_model.right_scene.effect.getUpperLimit());
        this.sl_effect_timer_value_right.setValue(this.glediator_model.right_scene.effect.getTimerValue());
        if (this.glediator_model.right_scene.effect.getActiveState()) {
            this.pb_right_effect_activated.setBackground(GlediatorView.HIGHLIGHT_COLOR);
        }
        else {
            this.pb_right_effect_activated.setBackground(GlediatorView.DEFAULT_BTN_COLOR);
        }
        this.block_events = false;
    }
    
    private void updateMainControlls() {
        this.block_events = true;
        this.cb_fader_mode.setSelectedItem(this.glediator_model.fader.getFaderMode());
        this.sl_main_fader.setValue(this.glediator_model.fader.getFaderValue());
        this.sl_main_fader_time.setValue(this.glediator_model.fader.getFaderTime());
        this.sl_left_level.setValue(this.glediator_model.fader.getLeftIntensity());
        this.sl_right_level.setValue(this.glediator_model.fader.getRightIntensity());
        this.sl_master_level.setValue(this.glediator_model.fader.getMasterIntensity());
        this.sl_master_strobe_interval.setValue(this.glediator_model.getStrobeInterval());
        this.sl_master_strobe_duration.setValue(this.glediator_model.getStrobeDuration());
        this.block_events = false;
    }
    
    static {
        HIGHLIGHT_COLOR = Color.LIGHT_GRAY;
    }
}
