// 
// Decompiled by Procyon v0.5.36
// 

package Remote;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.SocketException;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.net.DatagramSocket;
import Main.GlediatorModel;
import javax.swing.JFrame;

public class RemoteControl extends JFrame
{
    GlediatorModel glediator_model;
    private boolean connected;
    private DatagramSocket verbindung;
    private Timer udp_timer;
    ActionListener udp_timer_task;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private static JLabel lbl_status;
    private static JButton pb_connect_to_GRC;
    private JButton pb_done;
    private static JTextField tf_GRC_IP;
    private static JTextField tf_GRC_Port;
    private JTextField tf_my_port;
    
    public RemoteControl(final GlediatorModel glediator_model) {
        this.udp_timer_task = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    RemoteControl.this.receive_udp();
                }
                catch (IOException ex) {}
            }
        };
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.glediator_model = glediator_model;
        this.connected = false;
        this.verbindung = null;
        (this.udp_timer = new Timer(100, this.udp_timer_task)).setRepeats(true);
        this.udp_timer.setDelay(100);
        this.udp_timer.start();
    }
    
    private void initComponents() {
        this.jLabel1 = new JLabel();
        RemoteControl.tf_GRC_IP = new JTextField();
        this.jLabel2 = new JLabel();
        RemoteControl.tf_GRC_Port = new JTextField();
        RemoteControl.lbl_status = new JLabel();
        RemoteControl.pb_connect_to_GRC = new JButton();
        this.pb_done = new JButton();
        this.jLabel3 = new JLabel();
        this.tf_my_port = new JTextField();
        this.setTitle("G.R.C. - Setup");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.getContentPane().setLayout(new GridBagLayout());
        this.jLabel1.setText("G.R.C. - IP                                                    ");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jLabel1, gridBagConstraints);
        RemoteControl.tf_GRC_IP.setText("localhost");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(RemoteControl.tf_GRC_IP, gridBagConstraints);
        this.jLabel2.setText("G.R.C.- Port");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jLabel2, gridBagConstraints);
        RemoteControl.tf_GRC_Port.setText("12500");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(RemoteControl.tf_GRC_Port, gridBagConstraints);
        RemoteControl.lbl_status.setText("---");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(RemoteControl.lbl_status, gridBagConstraints);
        RemoteControl.pb_connect_to_GRC.setText("Open Connection to G.R.C.");
        RemoteControl.pb_connect_to_GRC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RemoteControl.this.pb_connect_to_GRCActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(RemoteControl.pb_connect_to_GRC, gridBagConstraints);
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RemoteControl.this.pb_doneActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_done, gridBagConstraints);
        this.jLabel3.setText("My-Port");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jLabel3, gridBagConstraints);
        this.tf_my_port.setText("15200");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.tf_my_port, gridBagConstraints);
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void pb_connect_to_GRCActionPerformed(final ActionEvent evt) {
        if (!this.connected) {
            try {
                this.verbindung = new DatagramSocket(Integer.parseInt(this.tf_my_port.getText()));
                RemoteControl.lbl_status.setText("Data socked opened succesfully.");
                RemoteControl.pb_connect_to_GRC.setText("Disconnect Data Socket");
                this.connected = true;
                RemoteControl.tf_GRC_IP.setEnabled(false);
                RemoteControl.tf_GRC_Port.setEnabled(false);
                this.tf_my_port.setEnabled(false);
                this.send_scene_list();
            }
            catch (SocketException ex) {
                RemoteControl.lbl_status.setText(ex.toString());
                this.connected = false;
                RemoteControl.tf_GRC_IP.setEnabled(true);
                RemoteControl.tf_GRC_Port.setEnabled(true);
                this.tf_my_port.setEnabled(true);
            }
        }
        else {
            try {
                this.verbindung.close();
                RemoteControl.lbl_status.setText("Data socked closed.");
                RemoteControl.pb_connect_to_GRC.setText("Open Connection to G.R.C.");
                this.connected = false;
                RemoteControl.tf_GRC_IP.setEnabled(true);
                RemoteControl.tf_GRC_Port.setEnabled(true);
                this.tf_my_port.setEnabled(true);
            }
            catch (Exception ex2) {
                Logger.getLogger(RemoteControl.class.getName()).log(Level.SEVERE, null, ex2);
                RemoteControl.lbl_status.setText("Error closing data socket!");
                this.connected = true;
                RemoteControl.tf_GRC_IP.setEnabled(false);
                RemoteControl.tf_GRC_Port.setEnabled(false);
                this.tf_my_port.setEnabled(false);
            }
        }
    }
    
    public void send_scene_list() {
        if (this.connected) {
            try {
                this.send_udp("GLEDIATOR;CLEAR_LIST;DUMMY;");
            }
            catch (IOException ex) {
                System.out.println(ex);
            }
        }
        final String[] scene_list = this.glediator_model.scene_list.getSceneListEntrieNames();
        for (int scene_count = this.glediator_model.scene_list.getNumberOfEntries(), j = 0; j < scene_count; ++j) {
            if (this.connected) {
                try {
                    this.send_udp("GLEDIATOR;ADD_SCENE;" + scene_list[j] + ";");
                }
                catch (IOException ex2) {
                    System.out.println(ex2);
                }
            }
        }
        if (this.connected) {
            try {
                final int index = this.glediator_model.scene_list.getSelectedIndex();
                this.send_udp("GLEDIATOR;SET_SELECTED_INDEX;" + index + ";");
            }
            catch (IOException ex3) {
                Logger.getLogger(RemoteControl.class.getName()).log(Level.SEVERE, null, ex3);
            }
        }
    }
    
    public void receive_udp() throws IOException {
        final byte[] buf = new byte[100];
        final DatagramPacket packet = new DatagramPacket(buf, buf.length);
        if (this.connected) {
            this.verbindung.setSoTimeout(25);
            this.verbindung.receive(packet);
            if (packet.getLength() != 0) {
                final String data = new String(packet.getData());
                final String[] param = data.split(";");
                RemoteControl.lbl_status.setText(param[0] + ";" + param[1] + ";" + param[2]);
                if (param[0].equals("GLEDIATOR") && param[1].equals("SET_SCENE")) {
                    final int scene_index = Integer.parseInt(param[2]);
                    this.glediator_model.left_scene.setScene(this.glediator_model.scene_list.getSceneParameter(scene_index));
                    this.glediator_model.triggerGuiUpdate();
                }
                if (param[0].equals("GLEDIATOR") && param[1].equals("SET_SCENE_LEFT")) {
                    final int scene_index = Integer.parseInt(param[2]);
                    this.glediator_model.left_scene.setScene(this.glediator_model.scene_list.getSceneParameter(scene_index));
                    this.glediator_model.triggerGuiUpdate();
                }
                if (param[0].equals("GLEDIATOR") && param[1].equals("SET_SCENE_RIGHT")) {
                    final int scene_index = Integer.parseInt(param[2]);
                    this.glediator_model.right_scene.setScene(this.glediator_model.scene_list.getSceneParameter(scene_index));
                    this.glediator_model.triggerGuiUpdate();
                }
                if (param[0].equals("GLEDIATOR") && param[1].equals("SET_FADER_LEVEL")) {
                    final int fader_value = Integer.parseInt(param[2]);
                    this.glediator_model.fader.setFaderValue(fader_value);
                    this.glediator_model.triggerGuiUpdate();
                }
                if (param[0].equals("GLEDIATOR") && param[1].equals("SET_STROBE")) {
                    final int strobe_active = Integer.parseInt(param[2]);
                    if (strobe_active == 1) {
                        this.glediator_model.activateStrobe();
                    }
                    else {
                        this.glediator_model.deactivateStrobe();
                    }
                    this.glediator_model.triggerGuiUpdate();
                }
                if (param[0].equals("GLEDIATOR") && param[1].equals("GET_REQUEST")) {
                    this.send_scene_list();
                }
            }
        }
    }
    
    private void send_udp(final String befehl) throws IOException {
        final InetAddress address = InetAddress.getByName(RemoteControl.tf_GRC_IP.getText());
        final byte[] buf = befehl.getBytes();
        final DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Integer.parseInt(RemoteControl.tf_GRC_Port.getText()));
        this.verbindung.send(packet);
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
            Logger.getLogger(RemoteControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(RemoteControl.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(RemoteControl.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(RemoteControl.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
