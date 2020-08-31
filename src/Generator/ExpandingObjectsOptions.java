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

public class ExpandingObjectsOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JCheckBox cb_random;
    private JComboBox cbox_shape;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JButton pb_done;
    private JSlider sl_blue_value;
    private JSlider sl_count;
    private JSlider sl_green_value;
    private JSlider sl_growth;
    private JSlider sl_red_value;
    private JSlider sl_size;
    
    public ExpandingObjectsOptions(final SuperGenerator generator) {
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
        this.sl_count = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_size = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_growth = new JSlider();
        this.jLabel5 = new JLabel();
        this.sl_red_value = new JSlider();
        this.jLabel7 = new JLabel();
        this.cb_random = new JCheckBox();
        this.jLabel8 = new JLabel();
        this.sl_green_value = new JSlider();
        this.jLabel9 = new JLabel();
        this.sl_blue_value = new JSlider();
        this.setTitle("Options for \"Expanding Objects\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                ExpandingObjectsOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                ExpandingObjectsOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ExpandingObjectsOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.cbox_shape.setModel(new DefaultComboBoxModel<String>(new String[] { "Open Circle", "Filled Circle", "Open Rect", "Filled Rect", "Smiley" }));
        this.cbox_shape.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ExpandingObjectsOptions.this.cbox_shapeActionPerformed(evt);
            }
        });
        this.jLabel2.setText("Shape");
        this.sl_count.setMaximum(50);
        this.sl_count.setMinimum(1);
        this.sl_count.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ExpandingObjectsOptions.this.sl_countStateChanged(evt);
            }
        });
        this.jLabel3.setText("Count");
        this.sl_size.setMaximum(25);
        this.sl_size.setMinimum(1);
        this.sl_size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ExpandingObjectsOptions.this.sl_sizeStateChanged(evt);
            }
        });
        this.jLabel4.setText("Size");
        this.sl_growth.setMaximum(35);
        this.sl_growth.setMinimum(1);
        this.sl_growth.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ExpandingObjectsOptions.this.sl_growthStateChanged(evt);
            }
        });
        this.jLabel5.setText("Growth");
        this.sl_red_value.setMaximum(255);
        this.sl_red_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ExpandingObjectsOptions.this.sl_red_valueStateChanged(evt);
            }
        });
        this.jLabel7.setText("Red Value");
        this.cb_random.setText("Random Color");
        this.cb_random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ExpandingObjectsOptions.this.cb_randomActionPerformed(evt);
            }
        });
        this.jLabel8.setText("Green Value");
        this.sl_green_value.setMaximum(255);
        this.sl_green_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ExpandingObjectsOptions.this.sl_green_valueStateChanged(evt);
            }
        });
        this.jLabel9.setText("Blue Value");
        this.sl_blue_value.setMaximum(255);
        this.sl_blue_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ExpandingObjectsOptions.this.sl_blue_valueStateChanged(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel8).addComponent(this.jLabel9).addComponent(this.cb_random).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.pb_done, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_blue_value, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_green_value, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_red_value, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jLabel2, GroupLayout.Alignment.LEADING).addComponent(this.jLabel3, GroupLayout.Alignment.LEADING).addComponent(this.jLabel4, GroupLayout.Alignment.LEADING).addComponent(this.jLabel5, GroupLayout.Alignment.LEADING).addComponent(this.sl_count, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_size, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_growth, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.cbox_shape, GroupLayout.Alignment.LEADING, 0, -1, 32767).addComponent(this.jLabel7, GroupLayout.Alignment.LEADING))).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_shape, -2, -1, -2).addGap(17, 17, 17).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_count, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_size, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_growth, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_red_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel8).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_green_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_blue_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.cb_random).addGap(18, 18, 18).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.cbox_shape.setSelectedItem(parameter_array[1]);
        this.sl_red_value.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_green_value.setValue(Integer.parseInt(parameter_array[3]));
        this.sl_blue_value.setValue(Integer.parseInt(parameter_array[4]));
        this.cb_random.setSelected(Boolean.parseBoolean(parameter_array[5]));
        this.sl_count.setValue(Integer.parseInt(parameter_array[6]));
        this.sl_size.setValue(Integer.parseInt(parameter_array[7]));
        this.sl_growth.setValue(Integer.parseInt(parameter_array[8]));
        this.global_flag = true;
    }
    
    private void cb_randomActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void cbox_shapeActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_countStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_sizeStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_growthStateChanged(final ChangeEvent evt) {
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
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Expanding Objects;" + this.cbox_shape.getSelectedItem().toString() + ";" + this.sl_red_value.getValue() + ";" + this.sl_green_value.getValue() + ";" + this.sl_blue_value.getValue() + ";" + new Boolean(this.cb_random.isSelected()).toString() + ";" + this.sl_count.getValue() + ";" + this.sl_size.getValue() + ";" + this.sl_growth.getValue();
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
            Logger.getLogger(ExpandingObjectsOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(ExpandingObjectsOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(ExpandingObjectsOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(ExpandingObjectsOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
