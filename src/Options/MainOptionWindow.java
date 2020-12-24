// 
// Decompiled by Procyon v0.5.36
// 

package Options;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.Dimension;
import javax.swing.BorderFactory;
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
import Output.BaudRate;
import Output.ColorOrder;
import Mapper.MappingOrder;
import Mapper.MappingType;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import Output.OutputType;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import Main.GlediatorModel;
import javax.swing.JFrame;

public class MainOptionWindow extends JFrame
{
    GlediatorModel glediator_model;
    int old_bord_size_x;
    int old_bord_size_y;
    private JComboBox cbox_baud_rate;
    private JComboBox cbox_board_order;
    private JComboBox cbox_color_order;
    private JComboBox cbox_com_ports;
    private JComboBox cbox_mapping_mode;
    private JComboBox cbox_pixel_order;
    private JComboBox cbox_protocol;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel17;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel8;
    private JTabbedPane jTabbedPane2;
    private JTabbedPane jTabbedPane3;
    private JLabel lbl_124;
    private JLabel lbl_artnet_staus;
    public static JLabel lbl_pixel_order;
    private JLabel lbl_serial_status;
    private JButton pb_apply_changes;
    private JButton pb_artnet_socket_close;
    private JButton pb_artnet_socket_open;
    private JButton pb_chancel;
    private JButton pb_done;
    private JButton pb_patching;
    private JButton pb_serial_close;
    private JButton pb_serial_open;
    private JTextField tf_boards_x;
    private JTextField tf_boards_y;
    private JTextField tf_pixel_per_board_x;
    private JTextField tf_pixel_per_board_y;
    
    public MainOptionWindow(final GlediatorModel glediator_model) {
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.glediator_model = glediator_model;
        this.cbox_protocol.setModel(new DefaultComboBoxModel<OutputType>(OutputType.values()));
        this.cbox_mapping_mode.setModel(new DefaultComboBoxModel<MappingType>(MappingType.values()));
        this.cbox_pixel_order.setModel(new DefaultComboBoxModel<MappingOrder>(MappingOrder.values()));
        this.cbox_board_order.setModel(new DefaultComboBoxModel<MappingOrder>(MappingOrder.values()));
        this.cbox_color_order.setModel(new DefaultComboBoxModel<ColorOrder>(ColorOrder.values()));
        this.cbox_baud_rate.setModel(new DefaultComboBoxModel<BaudRate>(BaudRate.values()));
        final String[] com_ports = glediator_model.output.getComPorts();
        this.cbox_com_ports.removeAllItems();
        for (int i = 0; i < com_ports.length; ++i) {
            this.cbox_com_ports.addItem(com_ports[i]);
        }
        this.updateControls(glediator_model.options);
        this.old_bord_size_x = Integer.parseInt(this.tf_pixel_per_board_x.getText());
        this.old_bord_size_y = Integer.parseInt(this.tf_pixel_per_board_y.getText());
    }
    
    private void initComponents() {
        this.jTabbedPane2 = new JTabbedPane();
        this.jTabbedPane3 = new JTabbedPane();
        this.pb_apply_changes = new JButton();
        this.pb_done = new JButton();
        this.pb_chancel = new JButton();
        this.jPanel8 = new JPanel();
        this.lbl_124 = new JLabel();
        this.cbox_protocol = new JComboBox();
        this.cbox_board_order = new JComboBox();
        this.jLabel15 = new JLabel();
        this.jLabel17 = new JLabel();
        this.cbox_color_order = new JComboBox();
        this.cbox_mapping_mode = new JComboBox();
        this.jLabel14 = new JLabel();
        this.cbox_pixel_order = new JComboBox();
        MainOptionWindow.lbl_pixel_order = new JLabel();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jLabel7 = new JLabel();
        this.tf_pixel_per_board_x = new JTextField();
        this.tf_pixel_per_board_y = new JTextField();
        this.jLabel6 = new JLabel();
        this.tf_boards_x = new JTextField();
        this.jLabel8 = new JLabel();
        this.tf_boards_y = new JTextField();
        this.jPanel3 = new JPanel();
        this.jLabel9 = new JLabel();
        this.cbox_com_ports = new JComboBox();
        this.pb_serial_open = new JButton();
        this.lbl_serial_status = new JLabel();
        this.cbox_baud_rate = new JComboBox();
        this.pb_serial_close = new JButton();
        this.jLabel10 = new JLabel();
        this.jPanel4 = new JPanel();
        this.pb_artnet_socket_open = new JButton();
        this.lbl_artnet_staus = new JLabel();
        this.pb_artnet_socket_close = new JButton();
        this.jLabel11 = new JLabel();
        this.pb_patching = new JButton();
        this.jTabbedPane2.addTab("tab1", this.jTabbedPane3);
        this.setTitle("Output Options");
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(final ComponentEvent evt) {
                MainOptionWindow.this.formComponentShown(evt);
            }
        });
        this.getContentPane().setLayout(new GridBagLayout());
        this.pb_apply_changes.setText("Apply Changes");
        this.pb_apply_changes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.pb_apply_changesActionPerformed(evt);
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.pb_apply_changes, gridBagConstraints);
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.pb_doneActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.pb_done, gridBagConstraints);
        this.pb_chancel.setText("Cancel");
        this.pb_chancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.pb_chancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.pb_chancel, gridBagConstraints);
        this.jPanel8.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel8.setLayout(new GridBagLayout());
        this.lbl_124.setText("Output Mode");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(this.lbl_124, gridBagConstraints);
        this.cbox_protocol.setModel(new DefaultComboBoxModel<String>(new String[] { "No Data Transmission", "Glediator Protocol", "Mini-DMX", "TPM2", "Artnet", "TPM2.Net" }));
        this.cbox_protocol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.cbox_protocolActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(this.cbox_protocol, gridBagConstraints);
        this.cbox_board_order.setModel(new DefaultComboBoxModel<String>(new String[] { "horizontal snake - starting top left", "horizontal snake - starting top right", "horizontal snake - starting bottom left", "horizontal snake - starting bottom right", "horizontal line wise - starting top left", "horizontal line wise - starting top right", "horizontal line wise - starting bottom left", "horizontal line wise - starting bottom right", "vertical snake - starting top left", "vertical snake - starting top right", "vertical snake - starting bottom left", "vertical snake - starting bottom right", "vertical line wise - starting top left", "vertical line wise - starting top right", "vertical line wise - starting bottom left", "vertical line wise - starting bottom right" }));
        this.cbox_board_order.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(this.cbox_board_order, gridBagConstraints);
        this.jLabel15.setText("Color Order");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(this.jLabel15, gridBagConstraints);
        this.jLabel17.setText("Board Order");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(this.jLabel17, gridBagConstraints);
        this.cbox_color_order.setModel(new DefaultComboBoxModel<String>(new String[] { "RGB", "RBG", "GRB", "GBR", "BRG", "BGR" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(this.cbox_color_order, gridBagConstraints);
        this.cbox_mapping_mode.setModel(new DefaultComboBoxModel<String>(new String[] { "Single Pixels", "Boards of Pixels" }));
        this.cbox_mapping_mode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.cbox_mapping_modeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(this.cbox_mapping_mode, gridBagConstraints);
        this.jLabel14.setText("Mapping Mode");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(this.jLabel14, gridBagConstraints);
        this.cbox_pixel_order.setModel(new DefaultComboBoxModel<String>(new String[] { "horizontal snake - starting top left", "horizontal snake - starting top right", "horizontal snake - starting bottom left", "horizontal snake - starting bottom right", "horizontal line wise - starting top left", "horizontal line wise - starting top right", "horizontal line wise - starting bottom left", "horizontal line wise - starting bottom right", "vertical snake - starting top left", "vertical snake - starting top right", "vertical snake - starting bottom left", "vertical snake - starting bottom right", "vertical line wise - starting top left", "vertical line wise - starting top right", "vertical line wise - starting bottom left", "vertical line wise - starting bottom right" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(this.cbox_pixel_order, gridBagConstraints);
        MainOptionWindow.lbl_pixel_order.setText("Pixel Order");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel8.add(MainOptionWindow.lbl_pixel_order, gridBagConstraints);
        this.jPanel1.setLayout(new GridBagLayout());
        this.jLabel1.setText("Board_Size_X:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.jLabel1, gridBagConstraints);
        this.jLabel7.setText("Board_Size_Y:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.jLabel7, gridBagConstraints);
        this.tf_pixel_per_board_x.setText("16");
        this.tf_pixel_per_board_x.setEnabled(false);
        this.tf_pixel_per_board_x.setMaximumSize(new Dimension(50, 20));
        this.tf_pixel_per_board_x.setMinimumSize(new Dimension(50, 20));
        this.tf_pixel_per_board_x.setPreferredSize(new Dimension(50, 20));
        this.tf_pixel_per_board_x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.tf_pixel_per_board_xActionPerformed(evt);
            }
        });
        this.tf_pixel_per_board_x.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent evt) {
                MainOptionWindow.this.tf_pixel_per_board_xFocusLost(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.tf_pixel_per_board_x, gridBagConstraints);
        this.tf_pixel_per_board_y.setText("8");
        this.tf_pixel_per_board_y.setEnabled(false);
        this.tf_pixel_per_board_y.setMaximumSize(new Dimension(50, 20));
        this.tf_pixel_per_board_y.setMinimumSize(new Dimension(50, 20));
        this.tf_pixel_per_board_y.setPreferredSize(new Dimension(50, 20));
        this.tf_pixel_per_board_y.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.tf_pixel_per_board_yActionPerformed(evt);
            }
        });
        this.tf_pixel_per_board_y.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent evt) {
                MainOptionWindow.this.tf_pixel_per_board_yFocusLost(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.tf_pixel_per_board_y, gridBagConstraints);
        this.jLabel6.setText("Borads_X:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.jLabel6, gridBagConstraints);
        this.tf_boards_x.setText("2");
        this.tf_boards_x.setEnabled(false);
        this.tf_boards_x.setMaximumSize(new Dimension(50, 20));
        this.tf_boards_x.setMinimumSize(new Dimension(50, 20));
        this.tf_boards_x.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.tf_boards_x, gridBagConstraints);
        this.jLabel8.setText("Boards_Y:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.jLabel8, gridBagConstraints);
        this.tf_boards_y.setText("2");
        this.tf_boards_y.setEnabled(false);
        this.tf_boards_y.setMaximumSize(new Dimension(50, 20));
        this.tf_boards_y.setMinimumSize(new Dimension(50, 20));
        this.tf_boards_y.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel1.add(this.tf_boards_y, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = 1;
        this.jPanel8.add(this.jPanel1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel8, gridBagConstraints);
        this.jPanel3.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel3.setLayout(new GridBagLayout());
        this.jLabel9.setText("COM-Port");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.jLabel9, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.cbox_com_ports, gridBagConstraints);
        this.pb_serial_open.setText("Serial Open");
        this.pb_serial_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.pb_serial_openActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.pb_serial_open, gridBagConstraints);
        this.lbl_serial_status.setText("...");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.lbl_serial_status, gridBagConstraints);
        this.cbox_baud_rate.setModel(new DefaultComboBoxModel<String>(new String[] { "115200", "230400", "250000", "500000", "921600", "1000000", "1250000" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.cbox_baud_rate, gridBagConstraints);
        this.pb_serial_close.setText("Serial Close");
        this.pb_serial_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.pb_serial_closeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.pb_serial_close, gridBagConstraints);
        this.jLabel10.setText("Baud-Rate");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel3.add(this.jLabel10, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel3, gridBagConstraints);
        this.jPanel4.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        this.jPanel4.setLayout(new GridBagLayout());
        this.pb_artnet_socket_open.setText("Open Socket");
        this.pb_artnet_socket_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.pb_artnet_socket_openActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel4.add(this.pb_artnet_socket_open, gridBagConstraints);
        this.lbl_artnet_staus.setText("...");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel4.add(this.lbl_artnet_staus, gridBagConstraints);
        this.pb_artnet_socket_close.setText("Close Socket");
        this.pb_artnet_socket_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.pb_artnet_socket_closeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel4.add(this.pb_artnet_socket_close, gridBagConstraints);
        this.jLabel11.setText("Socket is bound to Ports 0x1936 / 0xFFE2");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel4.add(this.jLabel11, gridBagConstraints);
        this.pb_patching.setText("Patch Artnet/TPM2.Net");
        this.pb_patching.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MainOptionWindow.this.pb_patchingActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.jPanel4.add(this.pb_patching, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jPanel4, gridBagConstraints);
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.apply_changes();
        this.setVisible(false);
    }
    
    private void pb_apply_changesActionPerformed(final ActionEvent evt) {
        this.apply_changes();
    }
    
    private void pb_serial_openActionPerformed(final ActionEvent evt) {
        this.apply_changes();
        if (this.cbox_com_ports.getItemCount() > 0) {
            final String com_port = this.cbox_com_ports.getSelectedItem().toString();
            final BaudRate baud_rate = (BaudRate)this.cbox_baud_rate.getSelectedItem();
            final String status = this.glediator_model.output.startSerial(com_port, baud_rate);
            this.lbl_serial_status.setText(status);
            this.cbox_protocol.setEnabled(false);
            this.cbox_com_ports.setEnabled(false);
            this.cbox_baud_rate.setEnabled(false);
            this.setMappingEditable(false);
            this.pb_serial_open.setEnabled(false);
            this.pb_serial_close.setEnabled(true);
            this.pb_artnet_socket_open.setEnabled(false);
            this.pb_artnet_socket_close.setEnabled(false);
            this.glediator_model.setMatrixSizeEditable(false);
        }
    }
    
    private void pb_serial_closeActionPerformed(final ActionEvent evt) {
        if (this.cbox_com_ports.getItemCount() > 0) {
            final String com_port = this.cbox_com_ports.getSelectedItem().toString();
            final String status = this.glediator_model.output.stopSerial(com_port);
            this.lbl_serial_status.setText(status);
            this.cbox_protocol.setEnabled(true);
            this.cbox_com_ports.setEnabled(true);
            this.cbox_baud_rate.setEnabled(true);
            this.setMappingEditable(true);
            this.pb_serial_open.setEnabled(true);
            this.pb_serial_close.setEnabled(true);
            this.pb_artnet_socket_open.setEnabled(true);
            this.pb_artnet_socket_close.setEnabled(true);
            this.cbox_protocol.setSelectedIndex(this.cbox_protocol.getSelectedIndex());
            this.glediator_model.setMatrixSizeEditable(true);
        }
    }
    
    private void cbox_protocolActionPerformed(final ActionEvent evt) {
        final String type = this.cbox_protocol.getSelectedItem().toString();
        if (type.equals("No Data Transmission") || type.equals("Artnet") || type.equals("TPM2_Net")) {
            this.setMappingEditable(false);
        }
        else {
            this.setMappingEditable(true);
        }
    }
    
    private void cbox_mapping_modeActionPerformed(final ActionEvent evt) {
        this.setMappingEditable(true);
    }
    
    private void pb_chancelActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void pb_artnet_socket_openActionPerformed(final ActionEvent evt) {
        String status = "";
        final String type = this.cbox_protocol.getSelectedItem().toString();
        final boolean is_artnet = type.equals("Artnet");
        final boolean is_tpmnet = type.equals("TPM2_Net");
        if (is_artnet || is_tpmnet) {
            this.apply_changes();
            if (!this.glediator_model.patcher.check_para()) {
                if (is_artnet) {
                    status = this.glediator_model.output.startArtnet();
                }
                if (is_tpmnet) {
                    status = this.glediator_model.output.startTPM2net();
                }
                this.lbl_artnet_staus.setText(status);
                this.cbox_protocol.setEnabled(false);
                this.cbox_com_ports.setEnabled(false);
                this.cbox_baud_rate.setEnabled(false);
                this.setMappingEditable(false);
                this.pb_serial_open.setEnabled(false);
                this.pb_serial_close.setEnabled(false);
                this.pb_artnet_socket_open.setEnabled(false);
                this.glediator_model.patch_gui.setVisible(false);
                this.pb_patching.setEnabled(false);
                this.pb_artnet_socket_close.setEnabled(true);
                this.glediator_model.setMatrixSizeEditable(false);
            }
            else {
                this.lbl_artnet_staus.setText("Wrong patching!");
            }
        }
        else {
            this.lbl_artnet_staus.setText("Check protocol!");
        }
    }
    
    private void pb_artnet_socket_closeActionPerformed(final ActionEvent evt) {
        String status = "";
        final String type = this.cbox_protocol.getSelectedItem().toString();
        
        System.out.println(type.toString());
        if (type.equals("Artnet")) {
            status = this.glediator_model.output.stopArtnet();
        }
        if (type.equals("TPM2_Net")) {
            status = this.glediator_model.output.stopTPM2net();
        }
        this.lbl_artnet_staus.setText(status);
        this.cbox_protocol.setEnabled(true);
        this.cbox_com_ports.setEnabled(true);
        this.cbox_baud_rate.setEnabled(true);
        this.setMappingEditable(true);
        this.pb_serial_open.setEnabled(true);
        this.pb_serial_close.setEnabled(true);
        this.pb_artnet_socket_open.setEnabled(true);
        this.pb_patching.setEnabled(true);
        this.pb_artnet_socket_close.setEnabled(true);
        this.cbox_protocol.setSelectedIndex(this.cbox_protocol.getSelectedIndex());
        this.glediator_model.setMatrixSizeEditable(true);
    }
    
    private void pb_patchingActionPerformed(final ActionEvent evt) {
        this.glediator_model.showPatchWindow(this);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        this.updateControls(this.glediator_model.options);
    }
    
    private void tf_pixel_per_board_xActionPerformed(final ActionEvent evt) {
        this.recalcNumBoardsX();
    }
    
    private void tf_pixel_per_board_yActionPerformed(final ActionEvent evt) {
        this.recalcNumBoardsY();
    }
    
    private void tf_pixel_per_board_xFocusLost(final FocusEvent evt) {
        this.recalcNumBoardsX();
    }
    
    private void tf_pixel_per_board_yFocusLost(final FocusEvent evt) {
        this.recalcNumBoardsY();
    }
    
    private void recalcNumBoardsX() {
        try {
            final int temp = Integer.parseInt(this.tf_pixel_per_board_x.getText());
            if (temp <= this.glediator_model.getFrameSize()[0]) {
                this.tf_boards_x.setText("" + this.glediator_model.getFrameSize()[0] / temp);
            }
            else {
                this.tf_pixel_per_board_x.setText("" + this.old_bord_size_x);
                this.tf_boards_x.setText("" + this.glediator_model.getFrameSize()[0] / this.old_bord_size_x);
            }
        }
        catch (Exception ex) {
            this.tf_pixel_per_board_x.setText("" + this.old_bord_size_x);
            this.tf_boards_x.setText("" + this.glediator_model.getFrameSize()[0] / this.old_bord_size_x);
            System.out.println("MainOptionWondow: Failed to parse paramter (Borad size x).");
        }
    }
    
    private void recalcNumBoardsY() {
        try {
            final int temp = Integer.parseInt(this.tf_pixel_per_board_y.getText());
            if (temp <= this.glediator_model.getFrameSize()[1]) {
                this.tf_boards_y.setText("" + this.glediator_model.getFrameSize()[1] / temp);
            }
            else {
                this.tf_pixel_per_board_y.setText("" + this.old_bord_size_y);
                this.tf_boards_y.setText("" + this.glediator_model.getFrameSize()[1] / this.old_bord_size_y);
            }
        }
        catch (Exception ex) {
            this.tf_pixel_per_board_y.setText("" + this.old_bord_size_y);
            this.tf_boards_y.setText("" + this.glediator_model.getFrameSize()[1] / this.old_bord_size_y);
            System.out.println("MainOptionWondow: Failed to parse paramter (Borad size y).");
        }
    }
    
    private void apply_changes() {
        this.glediator_model.options.setMappingType((MappingType)this.cbox_mapping_mode.getSelectedItem());
        this.glediator_model.options.setBoardOrder((MappingOrder)this.cbox_board_order.getSelectedItem());
        this.glediator_model.options.setPixelOrder((MappingOrder)this.cbox_pixel_order.getSelectedItem());
        final int[] board_size = new int[2];
        final int[] num_boards = new int[2];
        try {
            board_size[0] = Integer.parseInt(this.tf_pixel_per_board_x.getText());
            board_size[1] = Integer.parseInt(this.tf_pixel_per_board_y.getText());
            num_boards[0] = Integer.parseInt(this.tf_boards_x.getText());
            num_boards[1] = Integer.parseInt(this.tf_boards_y.getText());
        }
        catch (Exception ex) {
            System.out.println("MainOptionWondow: Failed to parse paramter(s).");
        }
        this.glediator_model.options.setBoardSize(board_size);
        this.glediator_model.options.setNumberOfBoards(num_boards);
        this.glediator_model.options.setOutputType((OutputType)this.cbox_protocol.getSelectedItem());
        this.glediator_model.options.setColorOrder((ColorOrder)this.cbox_color_order.getSelectedItem());
        this.glediator_model.options.setBaudRate((BaudRate)this.cbox_baud_rate.getSelectedItem());
        this.glediator_model.triggerOptionChange();
    }
    
    private void setMappingEditable(final boolean status) {
        this.cbox_mapping_mode.setEnabled(status);
        this.cbox_color_order.setEnabled(status);
        this.cbox_board_order.setEnabled(status);
        this.cbox_pixel_order.setEnabled(status);
        this.tf_pixel_per_board_x.setEnabled(status);
        this.tf_pixel_per_board_y.setEnabled(status);
        final MappingType board_order = (MappingType)this.cbox_mapping_mode.getSelectedItem();
        if (board_order == MappingType.SINGLE_PIXELS) {
            this.setBoardOrderEditable(false);
        }
        if (board_order == MappingType.BOARDS_OF_PIXEL && status) {
            this.setBoardOrderEditable(true);
        }
    }
    
    private void setBoardOrderEditable(final boolean status) {
        this.cbox_board_order.setEnabled(status);
        this.tf_pixel_per_board_x.setEnabled(status);
        this.tf_pixel_per_board_y.setEnabled(status);
    }
    
    private void updateControls(final Options options) {
        this.cbox_protocol.setSelectedItem(this.glediator_model.options.getOutputType());
        this.cbox_mapping_mode.setSelectedItem(this.glediator_model.options.getMappingType());
        this.cbox_color_order.setSelectedItem(this.glediator_model.options.getColorOrder());
        this.cbox_pixel_order.setSelectedItem(this.glediator_model.options.getPixelOrder());
        this.cbox_board_order.setSelectedItem(this.glediator_model.options.getBoardOrder());
        this.cbox_baud_rate.setSelectedItem(this.glediator_model.options.getBaudRate());
        this.tf_pixel_per_board_x.setText("" + options.getBoardSize()[0]);
        this.tf_pixel_per_board_y.setText("" + options.getBoardSize()[1]);
        this.tf_boards_x.setText("" + options.getNumberOfBoards()[0]);
        this.tf_boards_y.setText("" + options.getNumberOfBoards()[1]);
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
            Logger.getLogger(MainOptionWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(MainOptionWindow.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(MainOptionWindow.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(MainOptionWindow.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
