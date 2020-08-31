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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

public class FallingObjectsOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JCheckBox cb_rand;
    private JComboBox cbox_direction;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JButton pb_done;
    private JSlider sl_blue_value;
    private JSlider sl_green_value;
    private JSlider sl_length;
    private JSlider sl_number;
    private JSlider sl_rand;
    private JSlider sl_red_value;
    
    public FallingObjectsOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.sl_length = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_number = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_red_value = new JSlider();
        this.jLabel5 = new JLabel();
        this.sl_green_value = new JSlider();
        this.jLabel6 = new JLabel();
        this.sl_blue_value = new JSlider();
        this.jLabel7 = new JLabel();
        this.cb_rand = new JCheckBox();
        this.jLabel8 = new JLabel();
        this.sl_rand = new JSlider();
        this.cbox_direction = new JComboBox();
        this.jLabel9 = new JLabel();
        this.setTitle("Options for \"Falling Objects\"");
        this.setAlwaysOnTop(true);
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                FallingObjectsOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                FallingObjectsOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FallingObjectsOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.sl_length.setMaximum(50);
        this.sl_length.setMinimum(1);
        this.sl_length.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FallingObjectsOptions.this.sl_lengthStateChanged(evt);
            }
        });
        this.jLabel3.setText("Lenght");
        this.sl_number.setMinimum(1);
        this.sl_number.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FallingObjectsOptions.this.sl_numberStateChanged(evt);
            }
        });
        this.jLabel4.setText("Number");
        this.sl_red_value.setMaximum(255);
        this.sl_red_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FallingObjectsOptions.this.sl_red_valueStateChanged(evt);
            }
        });
        this.jLabel5.setText("Red Value");
        this.sl_green_value.setMaximum(255);
        this.sl_green_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FallingObjectsOptions.this.sl_green_valueStateChanged(evt);
            }
        });
        this.jLabel6.setText("Green Value");
        this.sl_blue_value.setMaximum(255);
        this.sl_blue_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FallingObjectsOptions.this.sl_blue_valueStateChanged(evt);
            }
        });
        this.jLabel7.setText("Blue Value");
        this.cb_rand.setText("Random Color");
        this.cb_rand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FallingObjectsOptions.this.cb_randActionPerformed(evt);
            }
        });
        this.jLabel8.setText("Random Color Intervall");
        this.sl_rand.setMaximum(60);
        this.sl_rand.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FallingObjectsOptions.this.sl_randStateChanged(evt);
            }
        });
        this.cbox_direction.setModel(new DefaultComboBoxModel<String>(new String[] { "Top", "Bottom", "Left", "Right", " " }));
        this.cbox_direction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FallingObjectsOptions.this.cbox_directionActionPerformed(evt);
            }
        });
        this.cbox_direction.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                FallingObjectsOptions.this.cbox_directionPropertyChange(evt);
            }
        });
        this.jLabel9.setText("Direction");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(this.sl_length, -1, 243, 32767).addComponent(this.jLabel4).addComponent(this.sl_number, -1, 243, 32767).addComponent(this.jLabel9).addComponent(this.cbox_direction, 0, 243, 32767).addComponent(this.cb_rand).addComponent(this.jLabel5).addComponent(this.sl_red_value, -1, 243, 32767).addComponent(this.jLabel6).addComponent(this.sl_green_value, -1, 243, 32767).addComponent(this.jLabel7).addComponent(this.sl_blue_value, -1, 243, 32767).addComponent(this.jLabel8).addComponent(this.sl_rand, -1, 243, 32767).addComponent(this.pb_done, -1, 243, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_length, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_number, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_direction, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_red_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_green_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_blue_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.cb_rand).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel8).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_rand, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.sl_red_value.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_green_value.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_blue_value.setValue(Integer.parseInt(parameter_array[3]));
        this.sl_length.setValue(Integer.parseInt(parameter_array[4]));
        this.sl_number.setValue(Integer.parseInt(parameter_array[5]));
        this.cb_rand.setSelected(Boolean.parseBoolean(parameter_array[7]));
        this.cbox_direction.setSelectedItem(parameter_array[8]);
        this.sl_rand.setValue(Integer.parseInt(parameter_array[9]));
        this.global_flag = true;
    }
    
    private void cb_randActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void sl_lengthStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_numberStateChanged(final ChangeEvent evt) {
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
    
    private void sl_randStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void cbox_directionPropertyChange(final PropertyChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void cbox_directionActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Falling Objects;" + this.sl_red_value.getValue() + ";" + this.sl_green_value.getValue() + ";" + this.sl_blue_value.getValue() + ";" + this.sl_length.getValue() + ";" + this.sl_number.getValue() + ";" + "reserved" + ";" + new Boolean(this.cb_rand.isSelected()).toString() + ";" + this.cbox_direction.getSelectedItem() + ";" + this.sl_rand.getValue();
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
            Logger.getLogger(FallingObjectsOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(FallingObjectsOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(FallingObjectsOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(FallingObjectsOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
