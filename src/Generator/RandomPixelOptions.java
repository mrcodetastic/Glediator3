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
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class RandomPixelOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JCheckBox cb_blue;
    private JCheckBox cb_green;
    private JCheckBox cb_red;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JButton pb_done;
    private JSlider sl_blue_value;
    private JSlider sl_green_value;
    private JSlider sl_red_value;
    private JSlider sl_speed;
    
    public RandomPixelOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.sl_speed = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_red_value = new JSlider();
        this.jLabel5 = new JLabel();
        this.sl_green_value = new JSlider();
        this.jLabel6 = new JLabel();
        this.sl_blue_value = new JSlider();
        this.jLabel7 = new JLabel();
        this.cb_red = new JCheckBox();
        this.cb_green = new JCheckBox();
        this.cb_blue = new JCheckBox();
        this.setTitle("Options for \"Random Pixel\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                RandomPixelOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                RandomPixelOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RandomPixelOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.sl_speed.setMaximum(20);
        this.sl_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                RandomPixelOptions.this.sl_speedStateChanged(evt);
            }
        });
        this.jLabel4.setText("Speed");
        this.sl_red_value.setMaximum(255);
        this.sl_red_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                RandomPixelOptions.this.sl_red_valueStateChanged(evt);
            }
        });
        this.jLabel5.setText("Red Value");
        this.sl_green_value.setMaximum(255);
        this.sl_green_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                RandomPixelOptions.this.sl_green_valueStateChanged(evt);
            }
        });
        this.jLabel6.setText("Green Value");
        this.sl_blue_value.setMaximum(255);
        this.sl_blue_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                RandomPixelOptions.this.sl_blue_valueStateChanged(evt);
            }
        });
        this.jLabel7.setText("Blue Value");
        this.cb_red.setText("Random Red");
        this.cb_red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RandomPixelOptions.this.cb_redActionPerformed(evt);
            }
        });
        this.cb_green.setText("Random Green");
        this.cb_green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RandomPixelOptions.this.cb_greenActionPerformed(evt);
            }
        });
        this.cb_blue.setText("Random Blue");
        this.cb_blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RandomPixelOptions.this.cb_blueActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.cb_blue).addComponent(this.cb_green).addComponent(this.cb_red).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabel4).addComponent(this.jLabel5).addComponent(this.jLabel6).addComponent(this.sl_speed, -1, -1, 32767).addComponent(this.sl_red_value, -1, -1, 32767).addComponent(this.sl_green_value, -1, -1, 32767).addComponent(this.jLabel7).addComponent(this.sl_blue_value, -2, 243, -2)).addComponent(this.pb_done, GroupLayout.Alignment.TRAILING, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_speed, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_red_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_green_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_blue_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.cb_red).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cb_green).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cb_blue).addGap(18, 18, 18).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.cb_red.setSelected(Boolean.parseBoolean(parameter_array[1]));
        this.cb_green.setSelected(Boolean.parseBoolean(parameter_array[2]));
        this.cb_blue.setSelected(Boolean.parseBoolean(parameter_array[3]));
        this.sl_red_value.setValue(Integer.parseInt(parameter_array[4]));
        this.sl_green_value.setValue(Integer.parseInt(parameter_array[5]));
        this.sl_blue_value.setValue(Integer.parseInt(parameter_array[6]));
        this.sl_speed.setValue(Integer.parseInt(parameter_array[7]));
        this.global_flag = true;
    }
    
    private void cb_redActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void cb_greenActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void cb_blueActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
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
    
    private void sl_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Random Pixel;" + new Boolean(this.cb_red.isSelected()).toString() + ";" + new Boolean(this.cb_green.isSelected()).toString() + ";" + new Boolean(this.cb_blue.isSelected()).toString() + ";" + this.sl_red_value.getValue() + ";" + this.sl_green_value.getValue() + ";" + this.sl_blue_value.getValue() + ";" + this.sl_speed.getValue();
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
            Logger.getLogger(RandomPixelOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(RandomPixelOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(RandomPixelOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(RandomPixelOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
