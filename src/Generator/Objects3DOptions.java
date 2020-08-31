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
import javax.swing.JFrame;

public class Objects3DOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JComboBox cbox_shape;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JButton pb_done;
    private JSlider sl_angle_xz;
    private JSlider sl_angle_yz;
    private JSlider sl_blue_value;
    private JSlider sl_green_value;
    private JSlider sl_offset_x;
    private JSlider sl_offset_y;
    private JSlider sl_offset_z;
    private JSlider sl_red_value;
    private JSlider sl_rot_speed;
    private JSlider sl_size;
    private JSlider sl_zoom_x;
    private JSlider sl_zoom_y;
    private JSlider sl_zoom_z;
    
    public Objects3DOptions(final SuperGenerator generator) {
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
        this.jLabel5 = new JLabel();
        this.sl_red_value = new JSlider();
        this.jLabel6 = new JLabel();
        this.sl_green_value = new JSlider();
        this.jLabel10 = new JLabel();
        this.sl_blue_value = new JSlider();
        this.jLabel7 = new JLabel();
        this.sl_offset_x = new JSlider();
        this.jLabel8 = new JLabel();
        this.sl_offset_y = new JSlider();
        this.jLabel9 = new JLabel();
        this.sl_offset_z = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_size = new JSlider();
        this.sl_rot_speed = new JSlider();
        this.jLabel11 = new JLabel();
        this.sl_angle_xz = new JSlider();
        this.jLabel12 = new JLabel();
        this.sl_angle_yz = new JSlider();
        this.jLabel13 = new JLabel();
        this.sl_zoom_x = new JSlider();
        this.jLabel14 = new JLabel();
        this.sl_zoom_y = new JSlider();
        this.jLabel15 = new JLabel();
        this.sl_zoom_z = new JSlider();
        this.jLabel16 = new JLabel();
        this.setTitle("Options for \"Expanding Objects\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                Objects3DOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                Objects3DOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Objects3DOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.cbox_shape.setModel(new DefaultComboBoxModel<String>(new String[] { "Cube", "Pyramide-3", "Pyramide-4" }));
        this.cbox_shape.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Objects3DOptions.this.cbox_shapeActionPerformed(evt);
            }
        });
        this.jLabel2.setText("Shape                                               ");
        this.jLabel5.setText("Red");
        this.sl_red_value.setMaximum(255);
        this.sl_red_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_red_valueStateChanged(evt);
            }
        });
        this.jLabel6.setText("Green");
        this.sl_green_value.setMaximum(255);
        this.sl_green_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_green_valueStateChanged(evt);
            }
        });
        this.jLabel10.setText("Blue");
        this.sl_blue_value.setMaximum(255);
        this.sl_blue_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_blue_valueStateChanged(evt);
            }
        });
        this.jLabel7.setText("Offeset X");
        this.sl_offset_x.setMaximum(50);
        this.sl_offset_x.setMinimum(-50);
        this.sl_offset_x.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_offset_xStateChanged(evt);
            }
        });
        this.jLabel8.setText("Offest Y");
        this.sl_offset_y.setMaximum(50);
        this.sl_offset_y.setMinimum(-50);
        this.sl_offset_y.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_offset_yStateChanged(evt);
            }
        });
        this.jLabel9.setText("Offset Z");
        this.sl_offset_z.setMaximum(50);
        this.sl_offset_z.setMinimum(-50);
        this.sl_offset_z.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_offset_zStateChanged(evt);
            }
        });
        this.jLabel3.setText("Size                                               ");
        this.sl_size.setMinimum(5);
        this.sl_size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_sizeStateChanged(evt);
            }
        });
        this.sl_rot_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_rot_speedStateChanged(evt);
            }
        });
        this.jLabel11.setText("Rotation Speed");
        this.sl_angle_xz.setMaximum(140);
        this.sl_angle_xz.setMinimum(80);
        this.sl_angle_xz.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_angle_xzStateChanged(evt);
            }
        });
        this.jLabel12.setText("Projection Angle XZ");
        this.sl_angle_yz.setMaximum(140);
        this.sl_angle_yz.setMinimum(80);
        this.sl_angle_yz.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_angle_yzStateChanged(evt);
            }
        });
        this.jLabel13.setText("Projection Angle YZ");
        this.sl_zoom_x.setMaximum(200);
        this.sl_zoom_x.setMinimum(1);
        this.sl_zoom_x.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_zoom_xStateChanged(evt);
            }
        });
        this.jLabel14.setText("Zoom X");
        this.sl_zoom_y.setMaximum(200);
        this.sl_zoom_y.setMinimum(1);
        this.sl_zoom_y.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_zoom_yStateChanged(evt);
            }
        });
        this.jLabel15.setText("Zoom Y");
        this.sl_zoom_z.setMaximum(200);
        this.sl_zoom_z.setMinimum(1);
        this.sl_zoom_z.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                Objects3DOptions.this.sl_zoom_zStateChanged(evt);
            }
        });
        this.jLabel16.setText("Zoom Z");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.sl_offset_y, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_offset_x, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_blue_value, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_green_value, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jLabel2, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_red_value, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.cbox_shape, GroupLayout.Alignment.LEADING, 0, -1, 32767).addComponent(this.sl_offset_z, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addComponent(this.jLabel5).addComponent(this.jLabel6).addComponent(this.jLabel10).addComponent(this.jLabel7).addComponent(this.jLabel8).addComponent(this.jLabel9)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel11).addComponent(this.jLabel12).addComponent(this.jLabel13).addComponent(this.jLabel14).addComponent(this.jLabel15)).addGap(0, 0, 32767)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.sl_zoom_z, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_zoom_y, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_zoom_x, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_angle_yz, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_angle_xz, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_rot_speed, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_size, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jLabel16, GroupLayout.Alignment.LEADING).addComponent(this.jLabel3, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addContainerGap(12, 32767)))).addComponent(this.pb_done, -2, 450, -2))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jLabel3)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.cbox_shape, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.jLabel11))).addComponent(this.sl_size, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.sl_red_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6)).addGroup(layout.createSequentialGroup().addComponent(this.sl_rot_speed, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel12))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.sl_green_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel10)).addGroup(layout.createSequentialGroup().addComponent(this.sl_angle_xz, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel13))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(this.sl_blue_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_offset_x, -2, -1, -2)).addGroup(layout.createSequentialGroup().addComponent(this.sl_angle_yz, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel14).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.sl_zoom_x, -2, -1, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel8).addComponent(this.jLabel15)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.sl_offset_y, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_offset_z, -2, -1, -2)).addGroup(layout.createSequentialGroup().addComponent(this.sl_zoom_y, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel16).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_zoom_z, -2, -1, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.sl_size.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_rot_speed.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_angle_xz.setValue(Integer.parseInt(parameter_array[3]));
        this.sl_angle_yz.setValue(Integer.parseInt(parameter_array[4]));
        this.sl_zoom_x.setValue(Integer.parseInt(parameter_array[5]));
        this.sl_zoom_y.setValue(Integer.parseInt(parameter_array[6]));
        this.sl_zoom_z.setValue(Integer.parseInt(parameter_array[7]));
        this.sl_offset_x.setValue(Integer.parseInt(parameter_array[8]));
        this.sl_offset_y.setValue(Integer.parseInt(parameter_array[9]));
        this.sl_offset_z.setValue(Integer.parseInt(parameter_array[10]));
        this.sl_red_value.setValue(Integer.parseInt(parameter_array[11]));
        this.sl_green_value.setValue(Integer.parseInt(parameter_array[12]));
        this.sl_blue_value.setValue(Integer.parseInt(parameter_array[13]));
        this.cbox_shape.setSelectedItem(parameter_array[14]);
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void cbox_shapeActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_red_valueStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_green_valueStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_blue_valueStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_offset_xStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_offset_yStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_offset_zStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_sizeStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_rot_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_angle_xzStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_angle_yzStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_zoom_xStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_zoom_yStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_zoom_zStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Objects3D;" + this.sl_size.getValue() + ";" + this.sl_rot_speed.getValue() + ";" + this.sl_angle_xz.getValue() + ";" + this.sl_angle_yz.getValue() + ";" + this.sl_zoom_x.getValue() + ";" + this.sl_zoom_y.getValue() + ";" + this.sl_zoom_z.getValue() + ";" + this.sl_offset_x.getValue() + ";" + this.sl_offset_y.getValue() + ";" + this.sl_offset_z.getValue() + ";" + this.sl_red_value.getValue() + ";" + this.sl_green_value.getValue() + ";" + this.sl_blue_value.getValue() + ";" + this.cbox_shape.getSelectedItem().toString();
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
            Logger.getLogger(Objects3DOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(Objects3DOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(Objects3DOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(Objects3DOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
