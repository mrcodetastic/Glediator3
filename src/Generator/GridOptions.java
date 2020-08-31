// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class GridOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JCheckBox cb_pulse;
    private JCheckBox cb_random_color;
    private JCheckBox cb_rotate;
    private JComboBox cbox_shape;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JButton pb_done;
    private JSlider sl_blue;
    private JSlider sl_green;
    private JSlider sl_grid_x;
    private JSlider sl_grid_y;
    private JSlider sl_max_zoom;
    private JSlider sl_min_zoom;
    private JSlider sl_pulse_speed;
    private JSlider sl_red;
    private JSlider sl_rot_angle;
    private JSlider sl_rot_speed;
    private JSlider sl_size_x;
    private JSlider sl_size_y;
    private JSlider sl_stroke;
    
    public GridOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.cbox_shape = new JComboBox();
        this.jLabel2 = new JLabel();
        this.sl_grid_x = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_grid_y = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_size_x = new JSlider();
        this.jLabel5 = new JLabel();
        this.sl_size_y = new JSlider();
        this.jLabel6 = new JLabel();
        this.sl_rot_angle = new JSlider();
        this.jLabel7 = new JLabel();
        this.cb_random_color = new JCheckBox();
        this.cb_rotate = new JCheckBox();
        this.cb_pulse = new JCheckBox();
        this.jLabel8 = new JLabel();
        this.sl_rot_speed = new JSlider();
        this.jLabel9 = new JLabel();
        this.sl_pulse_speed = new JSlider();
        this.sl_min_zoom = new JSlider();
        this.jLabel11 = new JLabel();
        this.sl_max_zoom = new JSlider();
        this.jLabel12 = new JLabel();
        this.sl_red = new JSlider();
        this.jLabel13 = new JLabel();
        this.sl_green = new JSlider();
        this.jLabel14 = new JLabel();
        this.sl_blue = new JSlider();
        this.jLabel15 = new JLabel();
        this.sl_stroke = new JSlider();
        this.jLabel16 = new JLabel();
        this.setTitle("Options for \"Grid\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                GridOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                GridOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GridOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.cbox_shape.setModel(new DefaultComboBoxModel<String>(new String[] { "Open Oval", "Filled Oval", "Open Rect", "Filled Rect" }));
        this.cbox_shape.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GridOptions.this.cbox_shapeActionPerformed(evt);
            }
        });
        this.jLabel2.setText("Shape");
        this.sl_grid_x.setMaximum(30);
        this.sl_grid_x.setMinimum(4);
        this.sl_grid_x.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_grid_xStateChanged(evt);
            }
        });
        this.jLabel3.setText("Grid X                                         ");
        this.sl_grid_y.setMaximum(30);
        this.sl_grid_y.setMinimum(4);
        this.sl_grid_y.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_grid_yStateChanged(evt);
            }
        });
        this.jLabel4.setText("Grid Y");
        this.sl_size_x.setMaximum(50);
        this.sl_size_x.setMinimum(4);
        this.sl_size_x.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_size_xStateChanged(evt);
            }
        });
        this.jLabel5.setText("Size X");
        this.sl_size_y.setMaximum(50);
        this.sl_size_y.setMinimum(4);
        this.sl_size_y.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_size_yStateChanged(evt);
            }
        });
        this.jLabel6.setText("Size Y");
        this.sl_rot_angle.setMaximum(360);
        this.sl_rot_angle.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_rot_angleStateChanged(evt);
            }
        });
        this.jLabel7.setText("Rotation Angle");
        this.cb_random_color.setText("Random Color");
        this.cb_random_color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GridOptions.this.cb_random_colorActionPerformed(evt);
            }
        });
        this.cb_rotate.setText("Rotate");
        this.cb_rotate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GridOptions.this.cb_rotateActionPerformed(evt);
            }
        });
        this.cb_pulse.setText("Pulse");
        this.cb_pulse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GridOptions.this.cb_pulseActionPerformed(evt);
            }
        });
        this.jLabel8.setText("Rotation Speed");
        this.sl_rot_speed.setMaximum(10);
        this.sl_rot_speed.setMinimum(1);
        this.sl_rot_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_rot_speedStateChanged(evt);
            }
        });
        this.jLabel9.setText("Pulse Speed                           ");
        this.sl_pulse_speed.setMaximum(20);
        this.sl_pulse_speed.setMinimum(1);
        this.sl_pulse_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_pulse_speedStateChanged(evt);
            }
        });
        this.sl_min_zoom.setMinimum(10);
        this.sl_min_zoom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_min_zoomStateChanged(evt);
            }
        });
        this.jLabel11.setText("Minimum Zoom");
        this.sl_max_zoom.setMaximum(400);
        this.sl_max_zoom.setMinimum(100);
        this.sl_max_zoom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_max_zoomStateChanged(evt);
            }
        });
        this.jLabel12.setText("Maximum Zoom");
        this.sl_red.setMaximum(255);
        this.sl_red.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_redStateChanged(evt);
            }
        });
        this.jLabel13.setText("Red");
        this.sl_green.setMaximum(255);
        this.sl_green.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_greenStateChanged(evt);
            }
        });
        this.jLabel14.setText("Green");
        this.sl_blue.setMaximum(255);
        this.sl_blue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_blueStateChanged(evt);
            }
        });
        this.jLabel15.setText("Blue");
        this.sl_stroke.setMaximum(15);
        this.sl_stroke.setMinimum(1);
        this.sl_stroke.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                GridOptions.this.sl_strokeStateChanged(evt);
            }
        });
        this.jLabel16.setText("Stroke");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.sl_pulse_speed, -2, 208, -2).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabel2).addComponent(this.jLabel4).addComponent(this.jLabel5).addComponent(this.jLabel6).addComponent(this.jLabel7).addComponent(this.jLabel8).addGroup(layout.createSequentialGroup().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel9, -2, 208, -2)).addComponent(this.cbox_shape, 0, -1, 32767).addComponent(this.pb_done, -2, 416, -2))).addContainerGap()).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.sl_rot_speed, GroupLayout.Alignment.LEADING, -1, 202, 32767).addComponent(this.sl_rot_angle, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_size_y, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_size_x, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_grid_y, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel11, GroupLayout.Alignment.LEADING).addComponent(this.jLabel15, GroupLayout.Alignment.LEADING).addComponent(this.jLabel14, GroupLayout.Alignment.LEADING).addComponent(this.jLabel13, GroupLayout.Alignment.LEADING).addComponent(this.jLabel12, GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.sl_blue, GroupLayout.Alignment.LEADING, -1, 208, 32767).addComponent(this.sl_green, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_red, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_max_zoom, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_min_zoom, -1, -1, 32767)).addContainerGap()))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel16).addComponent(this.sl_stroke, -2, 202, -2).addGroup(layout.createSequentialGroup().addComponent(this.cb_random_color).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cb_rotate).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cb_pulse)).addComponent(this.sl_grid_x, -2, 202, -2)).addGap(0, 0, 32767)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_shape, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jLabel9)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.sl_grid_x, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.jLabel11))).addComponent(this.sl_pulse_speed, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(this.sl_grid_y, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_size_x, -2, -1, -2)).addGroup(layout.createSequentialGroup().addComponent(this.sl_min_zoom, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel12).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.sl_max_zoom, -2, -1, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel6).addComponent(this.jLabel13)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.sl_size_y, -2, -1, -2).addComponent(this.sl_red, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel7).addComponent(this.jLabel14)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.sl_rot_angle, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel8)).addGroup(layout.createSequentialGroup().addComponent(this.sl_green, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel15))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.sl_rot_speed, -2, -1, -2).addComponent(this.sl_blue, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel16).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_stroke, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.cb_random_color).addComponent(this.cb_rotate).addComponent(this.cb_pulse)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.sl_grid_x.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_grid_y.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_size_x.setValue(Integer.parseInt(parameter_array[3]));
        this.sl_size_y.setValue(Integer.parseInt(parameter_array[4]));
        this.sl_rot_angle.setValue(Integer.parseInt(parameter_array[5]));
        this.sl_rot_speed.setValue(Integer.parseInt(parameter_array[6]));
        this.sl_min_zoom.setValue(Integer.parseInt(parameter_array[7]));
        this.sl_max_zoom.setValue(Integer.parseInt(parameter_array[8]));
        this.sl_pulse_speed.setValue(Integer.parseInt(parameter_array[9]));
        this.sl_stroke.setValue(Integer.parseInt(parameter_array[10]));
        this.sl_red.setValue(Integer.parseInt(parameter_array[11]));
        this.sl_green.setValue(Integer.parseInt(parameter_array[12]));
        this.sl_blue.setValue(Integer.parseInt(parameter_array[13]));
        this.cb_rotate.setSelected(Boolean.parseBoolean(parameter_array[14]));
        this.cb_pulse.setSelected(Boolean.parseBoolean(parameter_array[15]));
        this.cb_random_color.setSelected(Boolean.parseBoolean(parameter_array[16]));
        this.cbox_shape.setSelectedItem(parameter_array[17]);
        this.global_flag = true;
    }
    
    private void cb_random_colorActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void cb_rotateActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void cb_pulseActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void cbox_shapeActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_grid_xStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_grid_yStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_size_xStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_size_yStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_rot_angleStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_rot_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_pulse_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_min_zoomStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_max_zoomStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_redStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_greenStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_blueStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_strokeStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Grid;" + this.sl_grid_x.getValue() + ";" + this.sl_grid_y.getValue() + ";" + this.sl_size_x.getValue() + ";" + this.sl_size_y.getValue() + ";" + this.sl_rot_angle.getValue() + ";" + this.sl_rot_speed.getValue() + ";" + this.sl_min_zoom.getValue() + ";" + this.sl_max_zoom.getValue() + ";" + this.sl_pulse_speed.getValue() + ";" + this.sl_stroke.getValue() + ";" + this.sl_red.getValue() + ";" + this.sl_green.getValue() + ";" + this.sl_blue.getValue() + ";" + new Boolean(this.cb_rotate.isSelected()).toString() + ";" + new Boolean(this.cb_pulse.isSelected()).toString() + ";" + new Boolean(this.cb_random_color.isSelected()).toString() + ";" + this.cbox_shape.getSelectedItem().toString();
            this.generator.setParameters(paramter_string);
        }
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
            Logger.getLogger(GridOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(GridOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(GridOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(GridOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
