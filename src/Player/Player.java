// 
// Decompiled by Procyon v0.5.36
// 

package Player;

import java.net.DatagramPacket;
import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Rectangle;
import java.util.TimerTask;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.net.SocketAddress;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.Timer;
import java.net.DatagramSocket;
import Main.GlediatorModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Player extends JFrame
{
    private static boolean is_recording;
    private static boolean is_playing;
    private static boolean file_loaded;
    private static int sx;
    private static int sy;
    private static long file_size;
    private static long frame;
    private static long num_frames;
    private static long curr_pos;
    private static byte[] buffer;
    private JFileChooser file_chooser;
    private static File file;
    private static FileOutputStream fop;
    private static FileInputStream fis;
    GlediatorModel glediator_model;
    private static final int INPUT_BUFFER_LENGTH = 100;
    private static final int RECEIVE_TIME_OUT = 20;
    private static final int UIB_PORT = 50200;
    private static final int MY_PORT = 50201;
    private static final int TRANSFER_BLOCK_SIZE = 1000;
    private static DatagramSocket socket;
    private static int loc_port;
    private static int rem_port;
    private static byte[] input_buffer;
    private static boolean socket_open;
    private static String sender;
    private Timer udp_receive_timer;
    private static JComboBox cb_slot;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private static JLabel lbl_a;
    private static JLabel lbl_b;
    private static JLabel lbl_sd_status;
    static JButton pb_connect;
    static JButton pb_load;
    static JButton pb_play;
    static JButton pb_rec;
    static JButton pb_stopp;
    static JButton pb_upload;
    private static JProgressBar pbar_sd_transfer;
    private static JSlider sl_progress;
    private static JTextField tf_uib_ip;
    
    public Player(final GlediatorModel glediator_model) {
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.glediator_model = glediator_model;
        this.file_chooser = new JFileChooser();
        Player.file = null;
        Player.fop = null;
        Player.is_recording = false;
        Player.is_playing = false;
        Player.sx = 32;
        Player.sy = 16;
        Player.curr_pos = 0L;
        Player.buffer = new byte[3];
        Player.sender = "";
        Player.input_buffer = new byte[100];
        Player.loc_port = 50201;
        Player.rem_port = 50200;
        try {
            (Player.socket = new DatagramSocket((SocketAddress)null)).setReuseAddress(true);
            Player.socket.setBroadcast(true);
            Player.socket.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), Player.loc_port));
            Player.socket_open = true;
        }
        catch (Exception ex) {
            System.out.println(ex);
            Player.socket_open = false;
        }
        (this.udp_receive_timer = new Timer()).schedule(new UDP_Receive_Timer_Task(), 100L, 20L);
    }
    
    private void initComponents() {
        this.jPanel1 = new JPanel();
        Player.lbl_a = new JLabel();
        Player.lbl_b = new JLabel();
        Player.sl_progress = new JSlider();
        Player.pb_play = new JButton();
        Player.pb_stopp = new JButton();
        Player.pb_rec = new JButton();
        Player.pb_load = new JButton();
        this.jPanel2 = new JPanel();
        this.jLabel6 = new JLabel();
        Player.tf_uib_ip = new JTextField();
        this.jLabel7 = new JLabel();
        Player.cb_slot = new JComboBox();
        Player.pbar_sd_transfer = new JProgressBar();
        Player.pb_upload = new JButton();
        Player.lbl_sd_status = new JLabel();
        Player.pb_connect = new JButton();
        this.setTitle("Animation Player / Recorder");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(final ComponentEvent evt) {
                Player.this.formComponentShown(evt);
            }
            
            @Override
            public void componentHidden(final ComponentEvent evt) {
                Player.this.formComponentHidden(evt);
            }
        });
        this.getContentPane().setLayout(new GridBagLayout());
        this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel1.setLayout(new GridBagLayout());
        Player.lbl_a.setText("---");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel1.add(Player.lbl_a, gridBagConstraints);
        Player.lbl_b.setText("---");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel1.add(Player.lbl_b, gridBagConstraints);
        Player.sl_progress.setPreferredSize(new Dimension(300, 29));
        Player.sl_progress.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent evt) {
                Player.this.sl_progressMousePressed(evt);
            }
            
            @Override
            public void mouseReleased(final MouseEvent evt) {
                Player.this.sl_progressMouseReleased(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel1.add(Player.sl_progress, gridBagConstraints);
        Player.pb_play.setText("Play/Pause");
        Player.pb_play.setActionCommand("Play");
        Player.pb_play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Player.this.pb_playActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel1.add(Player.pb_play, gridBagConstraints);
        Player.pb_stopp.setText("Stop");
        Player.pb_stopp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Player.this.pb_stoppActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel1.add(Player.pb_stopp, gridBagConstraints);
        Player.pb_rec.setText("Rec");
        Player.pb_rec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Player.this.pb_recActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel1.add(Player.pb_rec, gridBagConstraints);
        Player.pb_load.setText("Load");
        Player.pb_load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Player.this.pb_loadActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel1.add(Player.pb_load, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 256;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel1, gridBagConstraints);
        this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel2.setLayout(new GridBagLayout());
        this.jLabel6.setText("UIB IP-Adresse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        this.jPanel2.add(this.jLabel6, gridBagConstraints);
        Player.tf_uib_ip.setText("192.168.5.25");
        Player.tf_uib_ip.setMinimumSize(new Dimension(40, 28));
        Player.tf_uib_ip.setPreferredSize(new Dimension(40, 28));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel2.add(Player.tf_uib_ip, gridBagConstraints);
        this.jLabel7.setText("Slot on SD card");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        this.jPanel2.add(this.jLabel7, gridBagConstraints);
        Player.cb_slot.setModel(new DefaultComboBoxModel<String>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel2.add(Player.cb_slot, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel2.add(Player.pbar_sd_transfer, gridBagConstraints);
        Player.pb_upload.setText("Start Upload");
        Player.pb_upload.setEnabled(false);
        Player.pb_upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Player.this.pb_uploadActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel2.add(Player.pb_upload, gridBagConstraints);
        Player.lbl_sd_status.setText("Not connected ...");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        this.jPanel2.add(Player.lbl_sd_status, gridBagConstraints);
        Player.pb_connect.setText("Connect");
        Player.pb_connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Player.this.pb_connectActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.jPanel2.add(Player.pb_connect, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 23;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel2, gridBagConstraints);
        this.pack();
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        (this.udp_receive_timer = new Timer()).schedule(new UDP_Receive_Timer_Task(), 100L, 20L);
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.udp_receive_timer.cancel();
    }
    
    private void pb_recActionPerformed(final ActionEvent evt) {
        final int return_val = this.file_chooser.showSaveDialog(this);
        if (return_val == 0) {
            try {
                if (Player.fis != null) {
                    Player.fis.close();
                }
                Player.file = this.file_chooser.getSelectedFile();
                if (!Player.file.exists()) {
                    Player.file.createNewFile();
                }
                Player.fop = new FileOutputStream(Player.file);
                Player.sx = this.glediator_model.getFrameSize()[0];
                Player.sy = this.glediator_model.getFrameSize()[1];
                Player.pb_play.setEnabled(false);
                Player.pb_rec.setEnabled(false);
                Player.pb_load.setEnabled(false);
                Player.pb_connect.setEnabled(false);
                Player.pb_upload.setEnabled(false);
                Player.cb_slot.setEnabled(false);
                Player.sl_progress.setEnabled(false);
                Player.lbl_a.setText("Recording ...");
                this.glediator_model.setRecordingState(true);
                Player.is_recording = true;
            }
            catch (Exception ex) {
                System.out.println(ex);
                this.glediator_model.setRecordingState(false);
                Player.is_recording = false;
                Player.pb_play.setEnabled(true);
                Player.pb_rec.setEnabled(true);
                Player.pb_load.setEnabled(true);
                Player.pb_connect.setEnabled(true);
                Player.pb_upload.setEnabled(true);
                Player.cb_slot.setEnabled(true);
                Player.sl_progress.setEnabled(true);
                Player.lbl_a.setText("No file loaded.");
                Player.lbl_b.setText("---");
            }
        }
        Player.file_size = 0L;
        Player.frame = 0L;
    }
    
    private void pb_stoppActionPerformed(final ActionEvent evt) {
        if (Player.is_recording) {
            try {
                Player.fop.flush();
                Player.fop.close();
                Player.file = this.file_chooser.getSelectedFile();
                Player.fis = new FileInputStream(Player.file);
                Player.lbl_a.setText("File open: '" + Player.file.getName() + "'");
                Player.num_frames = Player.file.length() / (3 * Player.sx * Player.sy);
                Player.lbl_b.setText("" + Player.num_frames + " Frames (" + Player.file.length() / 1000L + "kB)");
                Player.file_loaded = true;
            }
            catch (Exception ex) {
                System.out.println(ex);
                Player.file_loaded = false;
                Player.lbl_a.setText("No file loaded.");
                Player.lbl_b.setText("---");
            }
            this.glediator_model.setRecordingState(false);
            Player.is_recording = false;
        }
        if (Player.is_playing || this.glediator_model.getPlayingState()) {
            Player.is_playing = false;
            this.glediator_model.setPlayingState(false);
            Player.sl_progress.setValue(0);
            try {
                Player.fis.close();
                Player.fis = new FileInputStream(Player.file);
                Player.lbl_a.setText("File open: '" + Player.file.getName() + "'");
                Player.lbl_b.setText("" + Player.num_frames + " Frames (" + Player.file.length() / 1000L + "kB)");
                Player.file_loaded = true;
            }
            catch (Exception ex) {
                System.out.println(ex);
                Player.lbl_a.setText("No file loaded.");
                Player.lbl_a.setText("---");
                Player.file_loaded = false;
            }
        }
        Player.pb_play.setEnabled(true);
        Player.pb_rec.setEnabled(true);
        Player.pb_load.setEnabled(true);
        Player.pb_connect.setEnabled(true);
        Player.cb_slot.setEnabled(true);
        Player.sl_progress.setEnabled(true);
        Player.sl_progress.setValue(0);
        Player.frame = 0L;
    }
    
    private void pb_playActionPerformed(final ActionEvent evt) {
        if (Player.file_loaded) {
            Player.sx = this.glediator_model.getFrameSize()[0];
            Player.sy = this.glediator_model.getFrameSize()[1];
            if (Player.is_playing) {
                Player.is_playing = false;
            }
            else {
                Player.is_playing = true;
                this.glediator_model.setPlayingState(true);
            }
            Player.pb_load.setEnabled(false);
            Player.pb_rec.setEnabled(false);
            Player.pb_connect.setEnabled(false);
            Player.pb_upload.setEnabled(false);
            Player.cb_slot.setEnabled(false);
        }
    }
    
    private void pb_loadActionPerformed(final ActionEvent evt) {
        final int return_val = this.file_chooser.showOpenDialog(this);
        Player.sx = this.glediator_model.getFrameSize()[0];
        Player.sy = this.glediator_model.getFrameSize()[1];
        if (return_val == 0) {
            try {
                Player.file = this.file_chooser.getSelectedFile();
                Player.fis = new FileInputStream(Player.file);
                Player.lbl_a.setText("File open: '" + Player.file.getName() + "'");
                Player.file_size = Player.file.length();
                Player.num_frames = Player.file.length() / (3 * Player.sx * Player.sy);
                Player.lbl_b.setText("" + Player.num_frames + " Frames (" + Player.file.length() / 1000L + "kB)");
                Player.file_loaded = true;
            }
            catch (Exception ex) {
                System.out.println(ex);
                Player.lbl_a.setText("Stoped ...");
                Player.lbl_a.setText("---");
                Player.file_loaded = false;
            }
            Player.sl_progress.setValue(0);
            Player.frame = 0L;
            Player.curr_pos = 0L;
        }
    }
    
    private void sl_progressMouseReleased(final MouseEvent evt) {
        if (Player.file_loaded && this.glediator_model.getPlayingState()) {
            Player.is_playing = false;
            try {
                Player.fis.close();
                Player.fis = new FileInputStream(Player.file);
                final int pos = Player.sl_progress.getValue();
                final long frames_to_skip = Player.frame = (int)(Player.num_frames * pos / 100L);
                final long bytes_to_skip = frames_to_skip * 3L * Player.sx * Player.sy;
                Player.fis.skip(bytes_to_skip);
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
            Player.is_playing = true;
        }
    }
    
    private void sl_progressMousePressed(final MouseEvent evt) {
        Player.is_playing = false;
    }
    
    private void pb_connectActionPerformed(final ActionEvent evt) {
        request_current_para();
    }
    
    private void pb_uploadActionPerformed(final ActionEvent evt) {
        Player.pb_load.setEnabled(false);
        Player.pb_rec.setEnabled(false);
        Player.pb_play.setEnabled(false);
        Player.pb_stopp.setEnabled(false);
        Player.pb_connect.setEnabled(false);
        Player.pb_upload.setEnabled(false);
        Player.cb_slot.setEnabled(false);
        Player.is_playing = false;
        this.glediator_model.setPlayingState(false);
        prepare_file_transfer();
    }
    
    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
    
    private static void request_current_para() {
        System.out.println();
        System.out.println();
        System.out.println("Sending Main Para Request");
        final byte[] buf = { -100, -64, 0, 4, 0, 1, 64, 0, 54 };
        Player.sender = "PARA_REQUEST";
        Player.lbl_sd_status.setText("Connecting to UIB ...");
        send_udp(buf, Player.tf_uib_ip.getText(), Player.rem_port);
    }
    
    private static void prepare_file_transfer() {
        System.out.println();
        System.out.println();
        System.out.println("Sending Prepare File Request");
        final byte[] buf = { -100, -64, 0, 5, 0, 1, 64, 3, (byte)Player.cb_slot.getSelectedIndex(), 54 };
        Player.sender = "PREPARE_FILE_TRANSFER";
        Player.lbl_sd_status.setText("Preparing File Transfer ...");
        send_udp(buf, Player.tf_uib_ip.getText(), Player.rem_port);
    }
    
    private static void send_data_block() {
        final byte[] buf = new byte[1009];
        buf[0] = -100;
        buf[1] = -64;
        buf[4] = 0;
        buf[5] = 1;
        buf[6] = -64;
        buf[7] = 6;
        int result = -1;
        int block_size = 1000;
        if (Player.file_size - Player.curr_pos < 1000L) {
            block_size = (int)(Player.file_size - Player.curr_pos);
        }
        try {
            result = Player.fis.read(buf, 8, block_size);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        final int fs = block_size;
        buf[2] = (byte)(fs >> 8 & 0xFF);
        buf[3] = (byte)(fs & 0xFF);
        buf[1008] = 54;
        if (result != -1) {
            Player.curr_pos += block_size;
            Player.pbar_sd_transfer.setValue((int)(Player.curr_pos * 100L / Player.file_size));
            Player.sender = "SEND_DATA_BLOCK";
            Player.lbl_sd_status.setText("Sending Data to UIB");
            send_udp(buf, Player.tf_uib_ip.getText(), Player.rem_port);
        }
        else {
            Player.curr_pos = 0L;
            Player.pbar_sd_transfer.setValue(0);
            Player.lbl_sd_status.setText("Error reading file");
            try {
                Player.fis.close();
                Player.fis = new FileInputStream(Player.file);
            }
            catch (Exception ex2) {
                System.out.println(ex2);
            }
        }
    }
    
    private static void finish_file_transfer() {
        System.out.println();
        System.out.println();
        System.out.println("Sending Finish File Transfer Request");
        final byte[] buf = { -100, -64, 0, 5, 0, 1, 64, 5, 54 };
        Player.sender = "FINISH_FILE_TRANSFER";
        Player.lbl_sd_status.setText("Finishing File Transfer ...");
        send_udp(buf, Player.tf_uib_ip.getText(), Player.rem_port);
    }
    
    private static void send_udp(final byte[] _buf, final String _ip, final int _dest_port) {
        try {
            final InetAddress address = InetAddress.getByName(_ip);
            final DatagramPacket packet = new DatagramPacket(_buf, _buf.length, address, _dest_port);
            Player.socket.send(packet);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void receive_udp() {
        if (Player.socket_open) {
            try {
                final DatagramPacket packet = new DatagramPacket(Player.input_buffer, Player.input_buffer.length);
                Player.socket.setSoTimeout(10);
                Player.socket.receive(packet);
                if (packet.getLength() != 0) {
                    System.out.println("Received UDP packet at system time " + System.currentTimeMillis() + " (Millis).");
                    System.out.println("Packet length: " + packet.getLength());
                    if (Player.input_buffer[0] == -100) {
                        System.out.println("It's a TMP2.Net packtet.");
                        if (Player.input_buffer[1] == -83) {
                            System.out.println("It's an answer containing data.");
                            if (Player.sender.equals("PARA_REQUEST")) {
                                System.out.println("It's the answer to a Parameter request.");
                                final int index = 54;
                                final int sd_init_succesfull = Player.input_buffer[index] & 0xFF;
                                if (sd_init_succesfull == 0) {
                                    Player.lbl_sd_status.setText("SD card on UIB not initialized.");
                                    Player.pb_upload.setEnabled(false);
                                }
                                else {
                                    Player.lbl_sd_status.setText("SD card on UIB OK");
                                    if (Player.file_loaded) {
                                        Player.pb_upload.setEnabled(true);
                                    }
                                    Player.pbar_sd_transfer.setValue(0);
                                }
                                return;
                            }
                        }
                        if (Player.input_buffer[1] == -84) {
                            System.out.println("It's an answer without data.");
                            if (Player.sender.equals("PREPARE_FILE_TRANSFER")) {
                                System.out.println("It's the answer to a PREPARE_FILE_TRANSFER request.");
                                Player.lbl_sd_status.setText("File Transfer initialized ...");
                                send_data_block();
                                return;
                            }
                            if (Player.sender.equals("SEND_DATA_BLOCK")) {
                                System.out.println("It's the answer to a SEND_DATA_BLOCK.");
                                Player.lbl_sd_status.setText("Frame was received ...");
                                if (Player.curr_pos < Player.file_size) {
                                    send_data_block();
                                }
                                else {
                                    finish_file_transfer();
                                }
                                return;
                            }
                            if (Player.sender.equals("FINISH_FILE_TRANSFER")) {
                                System.out.println("It's the answer to a FINISH_FILE_TRANSFER request.");
                                Player.lbl_sd_status.setText("File transfer finished.");
                                Player.pb_load.setEnabled(true);
                                Player.pb_rec.setEnabled(true);
                                Player.pb_play.setEnabled(true);
                                Player.pb_stopp.setEnabled(true);
                                Player.pb_connect.setEnabled(true);
                                Player.pb_upload.setEnabled(true);
                                Player.cb_slot.setEnabled(true);
                                Player.fis.close();
                                Player.fis = new FileInputStream(Player.file);
                                Player.frame = 0L;
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {
                if (!ex.getMessage().equals("Receive timed out")) {
                    System.out.println(ex);
                }
            }
        }
    }
    
    public void saveFrame(final Color[] image) {
        try {
            for (int y = 0; y < Player.sy; ++y) {
                for (int x = 0; x < Player.sx; ++x) {
                    final int index = y * Player.sx + x;
                    Player.buffer[0] = (byte)image[index].getRed();
                    Player.buffer[1] = (byte)image[index].getGreen();
                    Player.buffer[2] = (byte)image[index].getBlue();
                    Player.fop.write(Player.buffer);
                }
            }
            Player.file_size += Player.sx * Player.sy * 3;
            ++Player.frame;
            Player.lbl_b.setText("Frame: " + Player.frame + ", File size: " + Player.file_size / 1000L + " kB");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void readFrame(final Color[] image) {
        if (Player.is_playing) {
            try {
                int result = -1;
                for (int y = 0; y < Player.sy; ++y) {
                    for (int x = 0; x < Player.sx; ++x) {
                        final int index = y * Player.sx + x;
                        result = Player.fis.read(Player.buffer);
                        if (result == -1) {
                            break;
                        }
                        image[index] = new Color(Player.buffer[0] & 0xFF, Player.buffer[1] & 0xFF, Player.buffer[2] & 0xFF);
                    }
                    if (result == -1) {
                        Player.fis.close();
                        Player.fis = new FileInputStream(Player.file);
                        Player.frame = 0L;
                        break;
                    }
                }
                ++Player.frame;
                Player.lbl_b.setText("Frame " + Player.frame + " of " + Player.num_frames);
                Player.sl_progress.setValue((int)(Player.frame * 100L / Player.num_frames));
            }
            catch (Exception ex) {
                System.out.println(ex);
                Player.is_playing = false;
                this.glediator_model.setPlayingState(false);
                Player.sl_progress.setValue(0);
                Player.frame = 0L;
            }
        }
    }
}
