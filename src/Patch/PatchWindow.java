// 
// Decompiled by Procyon v0.5.36
// 

package Patch;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import javax.swing.ListModel;
import javax.swing.AbstractListModel;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import Main.GlediatorModel;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class PatchWindow extends JFrame
{
    int size_x;
    int size_y;
    int number_of_unis;
    String[] array_of_unis;
    int[][] unis;
    int[][][] map;
    int[] pos;
    int[][] temp_unis;
    boolean fit_on_pressed;
    int temp_index_x;
    int temp_index_y;
    int[] auto_patch_lut;
    JFileChooser file_chooser;
    File file;
    GlediatorModel glediator_model;
    private JCheckBox cb_auto_inc;
    private JComboBox cbox_order;
    private JLabel jLabel1;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JTabbedPane jTabbedPane2;
    private JTabbedPane jTabbedPane3;
    private JList jl_unis;
    private DrawPatchPanel jp_schema;
    private JButton pb_add_uni;
    private JButton pb_done;
    private JButton pb_down;
    private JButton pb_left;
    private JButton pb_load;
    private JButton pb_patch;
    private JButton pb_remove;
    private JButton pb_right;
    private JButton pb_save;
    private JButton pb_up;
    private JTextField tf_ch_b;
    private JTextField tf_ch_g;
    private JTextField tf_ch_r;
    private JTextField tf_ip_1;
    private JTextField tf_ip_2;
    private JTextField tf_ip_3;
    private JTextField tf_ip_4;
    private JTextField tf_max_ch;
    private JTextField tf_net;
    private JTextField tf_spare;
    private JTextField tf_sub_net;
    private JTextField tf_uni;
    
    public PatchWindow(final GlediatorModel glediator_model) {
        this.file_chooser = new JFileChooser();
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.glediator_model = glediator_model;
        this.jp_schema.setGlediatorModel(this.glediator_model);
    }
    
    private void initComponents() {
        this.jTabbedPane2 = new JTabbedPane();
        this.jTabbedPane3 = new JTabbedPane();
        this.pb_done = new JButton();
        this.pb_load = new JButton();
        this.jp_schema = new DrawPatchPanel();
        this.pb_up = new JButton();
        this.pb_down = new JButton();
        this.pb_left = new JButton();
        this.pb_right = new JButton();
        this.jPanel2 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.jl_unis = new JList();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.tf_net = new JTextField();
        this.jLabel3 = new JLabel();
        this.tf_sub_net = new JTextField();
        this.jLabel4 = new JLabel();
        this.tf_uni = new JTextField();
        this.pb_remove = new JButton();
        this.pb_add_uni = new JButton();
        this.jLabel9 = new JLabel();
        this.tf_max_ch = new JTextField();
        this.jPanel3 = new JPanel();
        this.tf_ip_4 = new JTextField();
        this.tf_ip_3 = new JTextField();
        this.tf_ip_2 = new JTextField();
        this.tf_ip_1 = new JTextField();
        this.jPanel1 = new JPanel();
        this.jLabel6 = new JLabel();
        this.tf_ch_r = new JTextField();
        this.jLabel7 = new JLabel();
        this.tf_ch_g = new JTextField();
        this.jLabel8 = new JLabel();
        this.tf_ch_b = new JTextField();
        this.jLabel12 = new JLabel();
        this.cbox_order = new JComboBox();
        this.jLabel5 = new JLabel();
        this.tf_spare = new JTextField();
        this.pb_patch = new JButton();
        this.cb_auto_inc = new JCheckBox();
        this.pb_save = new JButton();
        this.jTabbedPane2.addTab("tab1", this.jTabbedPane3);
        this.setTitle("Artnet & TPM2.Net Patcher");
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(final ComponentEvent evt) {
                PatchWindow.this.formComponentShown(evt);
            }
        });
        this.getContentPane().setLayout(new GridBagLayout());
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_doneActionPerformed(evt);
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_done, gridBagConstraints);
        this.pb_load.setText("Load");
        this.pb_load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_loadActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_load, gridBagConstraints);
        this.jp_schema.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        this.jp_schema.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent evt) {
                PatchWindow.this.jp_schemaMousePressed(evt);
            }
            
            @Override
            public void mouseReleased(final MouseEvent evt) {
                PatchWindow.this.jp_schemaMouseReleased(evt);
            }
            
            @Override
            public void mouseClicked(final MouseEvent evt) {
                PatchWindow.this.jp_schemaMouseClicked(evt);
            }
        });
        this.jp_schema.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(final MouseEvent evt) {
                PatchWindow.this.jp_schemaMouseDragged(evt);
            }
        });
        final GroupLayout jp_schemaLayout = new GroupLayout(this.jp_schema);
        this.jp_schema.setLayout(jp_schemaLayout);
        jp_schemaLayout.setHorizontalGroup(jp_schemaLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 475, 32767));
        jp_schemaLayout.setVerticalGroup(jp_schemaLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 480, 32767));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jp_schema, gridBagConstraints);
        this.pb_up.setText("Up");
        this.pb_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_upActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_up, gridBagConstraints);
        this.pb_down.setText("Down");
        this.pb_down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_downActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_down, gridBagConstraints);
        this.pb_left.setText("Left");
        this.pb_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_leftActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_left, gridBagConstraints);
        this.pb_right.setText("Right");
        this.pb_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_rightActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_right, gridBagConstraints);
        this.jPanel2.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        this.jPanel2.setLayout(new GridBagLayout());
        this.jl_unis.setModel(new AbstractListModel() {
            String[] strings = { "192.168.123.124 / 1-1-1 / 3072", "192.168.123.124 / 2-1-1 / 3072", "192.168.123.124 / 3-1-1 / 3072", "192.168.123.125 / 1-0-1 / 3072", "192.168.123.126 / 2-0-1 / 3072" };
            
            @Override
            public int getSize() {
                return this.strings.length;
            }
            
            @Override
            public Object getElementAt(final int i) {
                return this.strings[i];
            }
        });
        this.jScrollPane1.setViewportView(this.jl_unis);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.jScrollPane1, gridBagConstraints);
        this.jLabel1.setText("IP-Adresse              ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.jLabel1, gridBagConstraints);
        this.jLabel2.setText("Net");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.jLabel2, gridBagConstraints);
        this.tf_net.setText("0");
        this.tf_net.setMaximumSize(new Dimension(50, 20));
        this.tf_net.setMinimumSize(new Dimension(50, 20));
        this.tf_net.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.tf_net, gridBagConstraints);
        this.jLabel3.setText("Subnet");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.jLabel3, gridBagConstraints);
        this.tf_sub_net.setText("0");
        this.tf_sub_net.setMaximumSize(new Dimension(50, 20));
        this.tf_sub_net.setMinimumSize(new Dimension(50, 20));
        this.tf_sub_net.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.tf_sub_net, gridBagConstraints);
        this.jLabel4.setText("Universe");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.jLabel4, gridBagConstraints);
        this.tf_uni.setText("1");
        this.tf_uni.setMaximumSize(new Dimension(50, 20));
        this.tf_uni.setMinimumSize(new Dimension(50, 20));
        this.tf_uni.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.tf_uni, gridBagConstraints);
        this.pb_remove.setText("Rem. Selected");
        this.pb_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_removeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.pb_remove, gridBagConstraints);
        this.pb_add_uni.setText("Add");
        this.pb_add_uni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_add_uniActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.pb_add_uni, gridBagConstraints);
        this.jLabel9.setText("max. channel");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.jLabel9, gridBagConstraints);
        this.tf_max_ch.setText("512");
        this.tf_max_ch.setMaximumSize(new Dimension(50, 20));
        this.tf_max_ch.setMinimumSize(new Dimension(50, 20));
        this.tf_max_ch.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel2.add(this.tf_max_ch, gridBagConstraints);
        this.jPanel3.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = 17;
        this.jPanel2.add(this.jPanel3, gridBagConstraints);
        this.tf_ip_4.setText("255");
        this.tf_ip_4.setMaximumSize(new Dimension(50, 20));
        this.tf_ip_4.setMinimumSize(new Dimension(50, 20));
        this.tf_ip_4.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 17;
        this.jPanel2.add(this.tf_ip_4, gridBagConstraints);
        this.tf_ip_3.setText("255");
        this.tf_ip_3.setMaximumSize(new Dimension(50, 20));
        this.tf_ip_3.setMinimumSize(new Dimension(50, 20));
        this.tf_ip_3.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 17;
        this.jPanel2.add(this.tf_ip_3, gridBagConstraints);
        this.tf_ip_2.setText("255");
        this.tf_ip_2.setMaximumSize(new Dimension(50, 20));
        this.tf_ip_2.setMinimumSize(new Dimension(50, 20));
        this.tf_ip_2.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 17;
        this.jPanel2.add(this.tf_ip_2, gridBagConstraints);
        this.tf_ip_1.setText("255");
        this.tf_ip_1.setMaximumSize(new Dimension(50, 20));
        this.tf_ip_1.setMinimumSize(new Dimension(50, 20));
        this.tf_ip_1.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 17;
        this.jPanel2.add(this.tf_ip_1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jPanel2, gridBagConstraints);
        this.jPanel1.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        this.jPanel1.setLayout(new GridBagLayout());
        this.jLabel6.setText("CH-R");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.jLabel6, gridBagConstraints);
        this.tf_ch_r.setText("0");
        this.tf_ch_r.setMaximumSize(new Dimension(50, 20));
        this.tf_ch_r.setMinimumSize(new Dimension(50, 20));
        this.tf_ch_r.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.tf_ch_r, gridBagConstraints);
        this.jLabel7.setText("CH-G");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.jLabel7, gridBagConstraints);
        this.tf_ch_g.setText("1");
        this.tf_ch_g.setMaximumSize(new Dimension(50, 20));
        this.tf_ch_g.setMinimumSize(new Dimension(50, 20));
        this.tf_ch_g.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.tf_ch_g, gridBagConstraints);
        this.jLabel8.setText("CH-B");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.jLabel8, gridBagConstraints);
        this.tf_ch_b.setText("2");
        this.tf_ch_b.setMaximumSize(new Dimension(50, 20));
        this.tf_ch_b.setMinimumSize(new Dimension(50, 20));
        this.tf_ch_b.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.tf_ch_b, gridBagConstraints);
        this.jLabel12.setText("Patch Order");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.jLabel12, gridBagConstraints);
        this.cbox_order.setModel(new DefaultComboBoxModel<String>(new String[] { "horizontal snake - starting top left", "horizontal snake - starting top right", "horizontal snake - starting bottom left", "horizontal snake - starting bottom right", "horizontal line wise - starting top left", "horizontal line wise - starting top right", "horizontal line wise - starting bottom left", "horizontal line wise - starting bottom right", "vertical snake - starting top left", "vertical snake - starting top right", "vertical snake - starting bottom left", "vertical snake - starting bottom right", "vertical line wise - starting top left", "vertical line wise - starting top right", "vertical line wise - starting bottom left", "vertical line wise - starting bottom right" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.cbox_order, gridBagConstraints);
        this.jLabel5.setText("Spare CH");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.jLabel5, gridBagConstraints);
        this.tf_spare.setText("0");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.tf_spare, gridBagConstraints);
        this.pb_patch.setText("Patch Selection");
        this.pb_patch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_patchActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.pb_patch, gridBagConstraints);
        this.cb_auto_inc.setText("Auto Inc.");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        this.jPanel1.add(this.cb_auto_inc, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jPanel1, gridBagConstraints);
        this.pb_save.setText("Save");
        this.pb_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PatchWindow.this.pb_saveActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_save, gridBagConstraints);
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.glediator_model.patcher.set_para(this.unis, this.map);
        final boolean check = this.glediator_model.patcher.check_para();
        if (!check) {
            this.glediator_model.patcher.apply_para_to_output();
            this.setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(this, "Your patching contains one or more errors and can not be applied. Fix patching and try again!");
        }
    }
    
    private void formComponentShown(final ComponentEvent evt) {
    }
    
    private void pb_loadActionPerformed(final ActionEvent evt) {
        final int returnVal = this.file_chooser.showOpenDialog(this);
        if (returnVal == 0) {
            this.file = this.file_chooser.getSelectedFile();
            final Properties patch_properties = new Properties();
            try {
                patch_properties.load(new FileInputStream(this.file));
                this.extractPatchProperties(patch_properties);
            }
            catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    private void pb_leftActionPerformed(final ActionEvent evt) {
        if (this.pos[0] > 0) {
            final int[] pos = this.pos;
            final int n = 0;
            --pos[n];
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
        }
    }
    
    private void pb_rightActionPerformed(final ActionEvent evt) {
        if (this.pos[0] < this.glediator_model.getFrameSize()[0] - 1) {
            final int[] pos = this.pos;
            final int n = 0;
            ++pos[n];
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
        }
    }
    
    private void pb_upActionPerformed(final ActionEvent evt) {
        if (this.pos[1] > 0) {
            final int[] pos = this.pos;
            final int n = 1;
            --pos[n];
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
        }
    }
    
    private void pb_downActionPerformed(final ActionEvent evt) {
        if (this.pos[1] < this.glediator_model.getFrameSize()[1] - 1) {
            final int[] pos = this.pos;
            final int n = 1;
            ++pos[n];
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
        }
    }
    
    private void pb_add_uniActionPerformed(final ActionEvent evt) {
        boolean error = false;
        this.number_of_unis = this.unis.length;
        this.temp_unis = new int[this.number_of_unis + 1][8];
        for (int i = 0; i < this.number_of_unis; ++i) {
            System.arraycopy(this.unis[i], 0, this.temp_unis[i], 0, 8);
        }
        try {
            this.temp_unis[this.number_of_unis][0] = Integer.parseInt(this.tf_ip_1.getText());
            this.temp_unis[this.number_of_unis][1] = Integer.parseInt(this.tf_ip_2.getText());
            this.temp_unis[this.number_of_unis][2] = Integer.parseInt(this.tf_ip_3.getText());
            this.temp_unis[this.number_of_unis][3] = Integer.parseInt(this.tf_ip_4.getText());
            this.temp_unis[this.number_of_unis][4] = Integer.parseInt(this.tf_net.getText());
            this.temp_unis[this.number_of_unis][5] = Integer.parseInt(this.tf_sub_net.getText());
            this.temp_unis[this.number_of_unis][6] = Integer.parseInt(this.tf_uni.getText());
            this.temp_unis[this.number_of_unis][7] = Integer.parseInt(this.tf_max_ch.getText());
            if (this.temp_unis[this.number_of_unis][0] < 0 || this.temp_unis[this.number_of_unis][0] > 255) {
                error = true;
            }
            if (this.temp_unis[this.number_of_unis][1] < 0 || this.temp_unis[this.number_of_unis][1] > 255) {
                error = true;
            }
            if (this.temp_unis[this.number_of_unis][2] < 0 || this.temp_unis[this.number_of_unis][2] > 255) {
                error = true;
            }
            if (this.temp_unis[this.number_of_unis][3] < 0 || this.temp_unis[this.number_of_unis][3] > 255) {
                error = true;
            }
            if (this.temp_unis[this.number_of_unis][4] < 0 || this.temp_unis[this.number_of_unis][4] > 255) {
                error = true;
            }
            if (this.temp_unis[this.number_of_unis][5] < 0 || this.temp_unis[this.number_of_unis][5] > 255) {
                error = true;
            }
            if (this.temp_unis[this.number_of_unis][6] < 0 || this.temp_unis[this.number_of_unis][6] > 255) {
                error = true;
            }
            if (this.temp_unis[this.number_of_unis][7] < 0) {
                error = true;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            error = true;
        }
        if (!error) {
            this.unis = new int[this.number_of_unis + 1][8];
            for (int i = 0; i < this.number_of_unis + 1; ++i) {
                System.arraycopy(this.temp_unis[i], 0, this.unis[i], 0, 8);
            }
            this.update_uni_list();
        }
        else {
            JOptionPane.showMessageDialog(this, "Universe parameters contain one or more error(s)! Please check and try again!");
        }
    }
    
    private void pb_removeActionPerformed(final ActionEvent evt) {
        this.number_of_unis = this.unis.length;
        final int index = this.jl_unis.getSelectedIndex();
        boolean in_use = false;
        for (int x = 0; x < this.size_x; ++x) {
            for (int y = 0; y < this.size_y; ++y) {
                if (this.map[x][y][0] == index) {
                    in_use = true;
                    break;
                }
            }
            if (in_use) {
                break;
            }
        }
        if (in_use) {
            JOptionPane.showMessageDialog(this, "This universe is still in use!");
        }
        if (this.number_of_unis > 0 && index >= 0 && !in_use) {
            this.temp_unis = new int[this.number_of_unis - 1][8];
            for (int i = 0; i < index; ++i) {
                System.arraycopy(this.unis[i], 0, this.temp_unis[i], 0, 8);
            }
            for (int i = index + 1; i < this.number_of_unis; ++i) {
                System.arraycopy(this.unis[i], 0, this.temp_unis[i - 1], 0, 8);
            }
            this.unis = new int[this.number_of_unis - 1][8];
            for (int i = 0; i < this.number_of_unis - 1; ++i) {
                System.arraycopy(this.temp_unis[i], 0, this.unis[i], 0, 8);
            }
            this.update_uni_list();
            this.number_of_unis = this.unis.length;
            if (index > 0) {
                this.jl_unis.setSelectedIndex(index - 1);
            }
            else {
                this.jl_unis.setSelectedIndex(0);
            }
            for (int x = 0; x < this.size_x; ++x) {
                for (int y = 0; y < this.size_y; ++y) {
                    if (this.map[x][y][0] > index) {
                        final int[] array = this.map[x][y];
                        final int n = 0;
                        --array[n];
                    }
                }
            }
        }
    }
    
    private void pb_patchActionPerformed(final ActionEvent evt) {
        boolean error = false;
        int spare = 0;
        int start_r = 0;
        int start_g = 0;
        int start_b = 0;
        final int uni_index = this.jl_unis.getSelectedIndex();
        if (uni_index >= 0) {
            final int sel_zize_x = this.pos[4] - this.pos[2] + 1;
            final int sel_zize_y = this.pos[5] - this.pos[3] + 1;
            try {
                spare = Integer.parseInt(this.tf_spare.getText());
                start_r = Integer.parseInt(this.tf_ch_r.getText());
                start_g = Integer.parseInt(this.tf_ch_g.getText());
                start_b = Integer.parseInt(this.tf_ch_b.getText());
            }
            catch (Exception ex) {
                System.out.println("Patch-Window: Failed to parse values for ch_r,ch_g, ch_b & spare.");
                error = true;
            }
            if (!error) {
                if (spare < 0) {
                    error = true;
                }
                if (start_r < 0 || start_r > this.unis[uni_index][7]) {
                    error = true;
                }
                if (start_g < 0 || start_g > this.unis[uni_index][7]) {
                    error = true;
                }
                if (start_b < 0 || start_b > this.unis[uni_index][7]) {
                    error = true;
                }
            }
            if (!error) {
                this.auto_patch_lut = new int[sel_zize_x * sel_zize_y];
                final String order = this.cbox_order.getSelectedItem().toString();
                this.do_pixel_mapping(this.auto_patch_lut, order, sel_zize_x, sel_zize_y);
                for (int i = 0; i < sel_zize_x * sel_zize_y; ++i) {
                    final int factor = this.auto_patch_lut[i];
                    final int x = factor % sel_zize_x;
                    final int y = factor / sel_zize_x;
                    final int ch_r = start_r + i * (3 + spare);
                    final int ch_g = start_g + i * (3 + spare);
                    final int ch_b = start_b + i * (3 + spare);
                    this.map[this.pos[2] + x][this.pos[3] + y][0] = uni_index;
                    this.map[this.pos[2] + x][this.pos[3] + y][1] = ch_r;
                    this.map[this.pos[2] + x][this.pos[3] + y][2] = ch_g;
                    this.map[this.pos[2] + x][this.pos[3] + y][3] = ch_b;
                }
                this.glediator_model.patcher.set_para(this.unis, this.map);
                if (this.cb_auto_inc.isSelected()) {
                    this.tf_ch_r.setText("" + (start_r + (3 + spare) * sel_zize_x * sel_zize_y));
                    this.tf_ch_g.setText("" + (start_g + (3 + spare) * sel_zize_x * sel_zize_y));
                    this.tf_ch_b.setText("" + (start_b + (3 + spare) * sel_zize_x * sel_zize_y));
                }
                this.jp_schema.repaint();
            }
            else {
                JOptionPane.showMessageDialog(this, "Patch parameters contain one or more error(s)! Please check and try again!");
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "No Universe selected!");
        }
    }
    
    private void jp_schemaMouseClicked(final MouseEvent evt) {
        final int[] fit = this.get_sel_indices(evt);
        if (fit[0] == 1) {
            this.pos[2] = fit[1];
            this.pos[3] = fit[2];
            this.pos[4] = fit[1];
            this.pos[5] = fit[2];
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
        }
    }
    
    private void jp_schemaMousePressed(final MouseEvent evt) {
        final int[] fit = this.get_sel_indices(evt);
        if (fit[0] == 1) {
            this.fit_on_pressed = true;
            this.temp_index_x = fit[1];
            this.temp_index_y = fit[2];
        }
        else {
            this.fit_on_pressed = false;
        }
    }
    
    private void jp_schemaMouseReleased(final MouseEvent evt) {
        final int[] fit = this.get_sel_indices(evt);
        if (fit[0] == 1 && this.fit_on_pressed) {
            if (fit[1] >= this.temp_index_x) {
                this.pos[2] = this.temp_index_x;
                this.pos[4] = fit[1];
            }
            else {
                this.pos[2] = fit[1];
                this.pos[4] = this.temp_index_x;
            }
            if (fit[2] >= this.temp_index_y) {
                this.pos[3] = this.temp_index_y;
                this.pos[5] = fit[2];
            }
            else {
                this.pos[3] = fit[2];
                this.pos[5] = this.temp_index_y;
            }
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
        }
    }
    
    private void jp_schemaMouseDragged(final MouseEvent evt) {
        final Point p = evt.getPoint();
        final int x = p.x;
        final int y = p.y;
        final int threshhold = 50;
        if (x > this.jp_schema.getWidth() - threshhold && this.pos[0] < this.size_x - 1) {
            final int[] pos = this.pos;
            final int n = 0;
            ++pos[n];
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        if (x < threshhold && this.pos[0] > 0) {
            final int[] pos2 = this.pos;
            final int n2 = 0;
            --pos2[n2];
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        if (y > this.jp_schema.getHeight() - threshhold && this.pos[1] < this.size_y - 1) {
            final int[] pos3 = this.pos;
            final int n3 = 1;
            ++pos3[n3];
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        if (y < threshhold && this.pos[1] > 0) {
            final int[] pos4 = this.pos;
            final int n4 = 1;
            --pos4[n4];
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        final int[] fit = this.get_sel_indices(evt);
        if (fit[0] == 1 && this.fit_on_pressed) {
            if (fit[1] >= this.temp_index_x) {
                this.pos[2] = this.temp_index_x;
                this.pos[4] = fit[1];
            }
            else {
                this.pos[2] = fit[1];
                this.pos[4] = this.temp_index_x;
            }
            if (fit[2] >= this.temp_index_y) {
                this.pos[3] = this.temp_index_y;
                this.pos[5] = fit[2];
            }
            else {
                this.pos[3] = fit[2];
                this.pos[5] = this.temp_index_y;
            }
            this.glediator_model.patcher.set_pos(this.pos);
            this.jp_schema.repaint();
        }
    }
    
    private void pb_saveActionPerformed(final ActionEvent evt) {
        final int returnVal = this.file_chooser.showSaveDialog(this);
        if (returnVal == 0) {
            this.file = this.file_chooser.getSelectedFile();
            final Properties patch_properties = new Properties();
            this.addPatchProperties(patch_properties);
            try {
                patch_properties.store(new FileOutputStream(this.file), "GLEDIATOR Patch File");
            }
            catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void addPatchProperties(final Properties patch_properties) {
        patch_properties.setProperty("Patch_Matrix_Size_X", "" + this.size_x);
        patch_properties.setProperty("Patch_Matrix_Size_Y", "" + this.size_y);
        patch_properties.setProperty("Patch_Num_Unis", "" + this.number_of_unis);
        for (int i = 0; i < this.number_of_unis; ++i) {
            patch_properties.setProperty("Patch_Uni_ID_" + i + "_IP1", "" + this.unis[i][0]);
            patch_properties.setProperty("Patch_Uni_ID_" + i + "_IP2", "" + this.unis[i][1]);
            patch_properties.setProperty("Patch_Uni_ID_" + i + "_IP3", "" + this.unis[i][2]);
            patch_properties.setProperty("Patch_Uni_ID_" + i + "_IP4", "" + this.unis[i][3]);
            patch_properties.setProperty("Patch_Uni_ID_" + i + "_Net_Nr", "" + this.unis[i][4]);
            patch_properties.setProperty("Patch_Uni_ID_" + i + "_Sub_Net_Nr", "" + this.unis[i][5]);
            patch_properties.setProperty("Patch_Uni_ID_" + i + "_Uni_Nr", "" + this.unis[i][6]);
            patch_properties.setProperty("Patch_Uni_ID_" + i + "_Num_Ch", "" + this.unis[i][7]);
        }
        for (int x = 0; x < this.size_x; ++x) {
            for (int y = 0; y < this.size_y; ++y) {
                patch_properties.setProperty("Patch_Pixel_X_" + x + "_Y_" + y + "_Uni_ID", "" + this.map[x][y][0]);
                patch_properties.setProperty("Patch_Pixel_X_" + x + "_Y_" + y + "_Ch_R", "" + this.map[x][y][1]);
                patch_properties.setProperty("Patch_Pixel_X_" + x + "_Y_" + y + "_Ch_G", "" + this.map[x][y][2]);
                patch_properties.setProperty("Patch_Pixel_X_" + x + "_Y_" + y + "_Ch_B", "" + this.map[x][y][3]);
            }
        }
    }
    
    public void extractPatchProperties(final Properties patch_properties) {
        final int sx = Integer.parseInt(patch_properties.getProperty("Patch_Matrix_Size_X"));
        final int sy = Integer.parseInt(patch_properties.getProperty("Patch_Matrix_Size_Y"));
        if (sx == this.size_x && sy == this.size_y) {
            this.number_of_unis = Integer.parseInt(patch_properties.getProperty("Patch_Num_Unis"));
            this.unis = new int[this.number_of_unis][8];
            for (int i = 0; i < this.number_of_unis; ++i) {
                this.unis[i][0] = Integer.parseInt(patch_properties.getProperty("Patch_Uni_ID_" + i + "_IP1"));
                this.unis[i][1] = Integer.parseInt(patch_properties.getProperty("Patch_Uni_ID_" + i + "_IP2"));
                this.unis[i][2] = Integer.parseInt(patch_properties.getProperty("Patch_Uni_ID_" + i + "_IP3"));
                this.unis[i][3] = Integer.parseInt(patch_properties.getProperty("Patch_Uni_ID_" + i + "_IP4"));
                this.unis[i][4] = Integer.parseInt(patch_properties.getProperty("Patch_Uni_ID_" + i + "_Net_Nr"));
                this.unis[i][5] = Integer.parseInt(patch_properties.getProperty("Patch_Uni_ID_" + i + "_Sub_Net_Nr"));
                this.unis[i][6] = Integer.parseInt(patch_properties.getProperty("Patch_Uni_ID_" + i + "_Uni_Nr"));
                this.unis[i][7] = Integer.parseInt(patch_properties.getProperty("Patch_Uni_ID_" + i + "_Num_Ch"));
            }
            for (int x = 0; x < this.size_x; ++x) {
                for (int y = 0; y < this.size_y; ++y) {
                    this.map[x][y][0] = Integer.parseInt(patch_properties.getProperty("Patch_Pixel_X_" + x + "_Y_" + y + "_Uni_ID"));
                    this.map[x][y][1] = Integer.parseInt(patch_properties.getProperty("Patch_Pixel_X_" + x + "_Y_" + y + "_Ch_R"));
                    this.map[x][y][2] = Integer.parseInt(patch_properties.getProperty("Patch_Pixel_X_" + x + "_Y_" + y + "_Ch_G"));
                    this.map[x][y][3] = Integer.parseInt(patch_properties.getProperty("Patch_Pixel_X_" + x + "_Y_" + y + "_Ch_B"));
                }
            }
            this.update_uni_list();
            this.jp_schema.repaint();
            this.glediator_model.patcher.set_para(this.unis, this.map);
            final boolean check = this.glediator_model.patcher.check_para();
            if (!check) {
                this.glediator_model.patcher.apply_para_to_output();
            }
            else {
                JOptionPane.showMessageDialog(this, "Your patching contains one or more errors and can not be applied. Fix patching and try again!");
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Current matrix size does not fit to matrix size found in selected file!");
        }
    }
    
    private void update_uni_list() {
        this.number_of_unis = this.unis.length;
        this.array_of_unis = new String[this.number_of_unis];
        for (int i = 0; i < this.number_of_unis; ++i) {
            String tmp = "";
            for (int j = 0; j < 3; ++j) {
                tmp = tmp + this.unis[i][j] + ".";
            }
            tmp += this.unis[i][3];
            for (int j = 4; j < 8; ++j) {
                tmp = tmp + "/" + this.unis[i][j];
            }
            this.array_of_unis[i] = tmp;
        }
        this.jl_unis.setListData(this.array_of_unis);
    }
    
    private int[] get_sel_indices(final MouseEvent evt) {
        final int[] result = { 0, 0, 0 };
        final Point p = evt.getPoint();
        final int x = p.x;
        final int y = p.y;
        final int[] pix_geom = this.glediator_model.patcher.get_pix_geom();
        final int rect_x = pix_geom[0];
        final int rect_y = pix_geom[1];
        final int space = pix_geom[2];
        final int pix_off_x = pix_geom[3];
        final int pix_off_y = pix_geom[4];
        final int n_x = (x + rect_x / 2 - pix_off_x) / (rect_x + space);
        final int x_min = pix_off_x + n_x * (rect_x + space) - rect_x / 2;
        final int x_max = pix_off_x + n_x * (rect_x + space) + rect_x / 2;
        final boolean fits_in_x = x >= x_min && x <= x_max;
        final int n_y = (y + rect_y / 2 - pix_off_y) / (rect_y + space);
        final int y_min = pix_off_y + n_y * (rect_y + space) - rect_y / 2;
        final int y_max = pix_off_y + n_y * (rect_y + space) + rect_y / 2;
        final boolean fits_in_y = y >= y_min && y <= y_max;
        if (fits_in_x && fits_in_y) {
            final int sel_index_x = n_x + this.pos[0];
            final int sel_index_y = n_y + this.pos[1];
            result[0] = 1;
            if (sel_index_x < this.size_x) {
                result[1] = sel_index_x;
            }
            else {
                result[1] = this.size_x - 1;
            }
            if (sel_index_y < this.size_y) {
                result[2] = sel_index_y;
            }
            else {
                result[2] = this.size_y - 1;
            }
        }
        else {
            result[0] = 0;
            result[2] = (result[1] = 0);
        }
        return result;
    }
    
    private void do_pixel_mapping(final int[] LUT, final String installation, final int size_x, final int size_y) {
        byte dir_x = 0;
        byte dir_y = 0;
        int x = 0;
        int y = 0;
        String order = "";
        String orientation = "";
        if (installation.equals("horizontal snake - starting top left")) {
            dir_x = 1;
            dir_y = 1;
            x = 0;
            y = 0;
            order = "snake";
            orientation = "hor";
        }
        if (installation.equals("horizontal snake - starting top right")) {
            dir_x = 0;
            dir_y = 1;
            x = size_x - 1;
            y = 0;
            order = "snake";
            orientation = "hor";
        }
        if (installation.equals("horizontal snake - starting bottom left")) {
            dir_x = 1;
            dir_y = 0;
            x = 0;
            y = size_y - 1;
            order = "snake";
            orientation = "hor";
        }
        if (installation.equals("horizontal snake - starting bottom right")) {
            dir_x = 0;
            dir_y = 0;
            x = size_x - 1;
            y = size_y - 1;
            order = "snake";
            orientation = "hor";
        }
        if (installation.equals("vertical snake - starting top left")) {
            dir_x = 1;
            dir_y = 1;
            x = 0;
            y = 0;
            order = "snake";
            orientation = "ver";
        }
        if (installation.equals("vertical snake - starting top right")) {
            dir_x = 0;
            dir_y = 1;
            x = size_x - 1;
            y = 0;
            order = "snake";
            orientation = "ver";
        }
        if (installation.equals("vertical snake - starting bottom left")) {
            dir_x = 1;
            dir_y = 0;
            x = 0;
            y = size_y - 1;
            order = "snake";
            orientation = "ver";
        }
        if (installation.equals("vertical snake - starting bottom right")) {
            dir_x = 0;
            dir_y = 0;
            x = size_x - 1;
            y = size_y - 1;
            order = "snake";
            orientation = "ver";
        }
        if (installation.equals("horizontal line wise - starting top left")) {
            dir_x = 1;
            dir_y = 1;
            x = 0;
            y = 0;
            order = "line";
            orientation = "hor";
        }
        if (installation.equals("horizontal line wise - starting top right")) {
            dir_x = 0;
            dir_y = 1;
            x = size_x - 1;
            y = 0;
            order = "line";
            orientation = "hor";
        }
        if (installation.equals("horizontal line wise - starting bottom left")) {
            dir_x = 1;
            dir_y = 0;
            x = 0;
            y = size_y - 1;
            order = "line";
            orientation = "hor";
        }
        if (installation.equals("horizontal line wise - starting bottom right")) {
            dir_x = 0;
            dir_y = 0;
            x = size_x - 1;
            y = size_y - 1;
            order = "line";
            orientation = "hor";
        }
        if (installation.equals("vertical line wise - starting top left")) {
            dir_x = 1;
            dir_y = 1;
            x = 0;
            y = 0;
            order = "line";
            orientation = "ver";
        }
        if (installation.equals("vertical line wise - starting top right")) {
            dir_x = 0;
            dir_y = 1;
            x = size_x - 1;
            y = 0;
            order = "line";
            orientation = "ver";
        }
        if (installation.equals("vertical line wise - starting bottom left")) {
            dir_x = 1;
            dir_y = 0;
            x = 0;
            y = size_y - 1;
            order = "line";
            orientation = "ver";
        }
        if (installation.equals("vertical line wise - starting bottom right")) {
            dir_x = 0;
            dir_y = 0;
            x = size_x - 1;
            y = size_y - 1;
            order = "line";
            orientation = "ver";
        }
        for (int i = 0; i < size_x * size_y; ++i) {
            LUT[i] = y * size_x + x;
            if (orientation.equals("hor")) {
                if (dir_x == 1) {
                    ++x;
                }
                else {
                    --x;
                }
            }
            if (orientation.equals("ver")) {
                if (dir_y == 1) {
                    ++y;
                }
                else {
                    --y;
                }
            }
            if (order.equals("snake")) {
                if (orientation.equals("hor")) {
                    if (x == size_x) {
                        dir_x = 0;
                        x = size_x - 1;
                        if (dir_y == 1) {
                            ++y;
                        }
                        else {
                            --y;
                        }
                    }
                    if (x == -1) {
                        dir_x = 1;
                        x = 0;
                        if (dir_y == 1) {
                            ++y;
                        }
                        else {
                            --y;
                        }
                    }
                }
                if (orientation.equals("ver")) {
                    if (y == size_y) {
                        dir_y = 0;
                        y = size_y - 1;
                        if (dir_x == 1) {
                            ++x;
                        }
                        else {
                            --x;
                        }
                    }
                    if (y == -1) {
                        dir_y = 1;
                        y = 0;
                        if (dir_x == 1) {
                            ++x;
                        }
                        else {
                            --x;
                        }
                    }
                }
            }
            if (order.equals("line")) {
                if (orientation.equals("hor")) {
                    if (x == size_x) {
                        x = 0;
                        if (dir_y == 1) {
                            ++y;
                        }
                        else {
                            --y;
                        }
                    }
                    if (x == -1) {
                        x = size_x - 1;
                        if (dir_y == 1) {
                            ++y;
                        }
                        else {
                            --y;
                        }
                    }
                }
                if (orientation.equals("ver")) {
                    if (y == size_y) {
                        y = 0;
                        if (dir_x == 1) {
                            ++x;
                        }
                        else {
                            --x;
                        }
                    }
                    if (y == -1) {
                        y = size_y - 1;
                        if (dir_x == 1) {
                            ++x;
                        }
                        else {
                            --x;
                        }
                    }
                }
            }
        }
    }
    
    public void setParameter() {
        final int[] size = this.glediator_model.patcher.get_size();
        this.size_x = size[0];
        this.size_y = size[1];
        this.unis = new int[this.glediator_model.patcher.get_num_unis()][8];
        this.map = new int[this.size_x][this.size_y][4];
        this.pos = new int[6];
        this.glediator_model.patcher.get_para(this.unis, this.map);
        this.glediator_model.patcher.get_pos(this.pos);
        this.update_uni_list();
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
            Logger.getLogger(PatchWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(PatchWindow.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(PatchWindow.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(PatchWindow.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
