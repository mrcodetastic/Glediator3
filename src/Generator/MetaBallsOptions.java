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

public class MetaBallsOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JCheckBox cb_rand;
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
    private JSlider sl_number;
    private JSlider sl_rand;
    private JSlider sl_red_value;
    private JSlider sl_size;
    private JSlider sl_speed;
    
    public MetaBallsOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.sl_number = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_size = new JSlider();
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
        this.jLabel9 = new JLabel();
        this.sl_speed = new JSlider();
        this.setTitle("Options for \"Meta Balls\"");
        this.setAlwaysOnTop(true);
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                MetaBallsOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                MetaBallsOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MetaBallsOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.sl_number.setMaximum(50);
        this.sl_number.setMinimum(1);
        this.sl_number.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                MetaBallsOptions.this.sl_numberStateChanged(evt);
            }
        });
        this.jLabel3.setText("Number of Balls");
        this.sl_size.setMaximum(80);
        this.sl_size.setMinimum(1);
        this.sl_size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                MetaBallsOptions.this.sl_sizeStateChanged(evt);
            }
        });
        this.jLabel4.setText("Size of Balls");
        this.sl_red_value.setMaximum(255);
        this.sl_red_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                MetaBallsOptions.this.sl_red_valueStateChanged(evt);
            }
        });
        this.jLabel5.setText("Red Value");
        this.sl_green_value.setMaximum(255);
        this.sl_green_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                MetaBallsOptions.this.sl_green_valueStateChanged(evt);
            }
        });
        this.jLabel6.setText("Green Value");
        this.sl_blue_value.setMaximum(255);
        this.sl_blue_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                MetaBallsOptions.this.sl_blue_valueStateChanged(evt);
            }
        });
        this.jLabel7.setText("Blue Value");
        this.cb_rand.setText("Random Color");
        this.cb_rand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MetaBallsOptions.this.cb_randActionPerformed(evt);
            }
        });
        this.jLabel8.setText("Random Color Intervall");
        this.sl_rand.setMaximum(60);
        this.sl_rand.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                MetaBallsOptions.this.sl_randStateChanged(evt);
            }
        });
        this.jLabel9.setText("Speed of Balls");
        this.sl_speed.setMaximum(20);
        this.sl_speed.setMinimum(1);
        this.sl_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                MetaBallsOptions.this.sl_speedStateChanged(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(this.sl_number, -1, -1, 32767).addComponent(this.jLabel4).addComponent(this.sl_size, -1, -1, 32767).addComponent(this.jLabel9).addComponent(this.sl_speed, -1, -1, 32767).addComponent(this.cb_rand).addComponent(this.jLabel5).addComponent(this.sl_red_value, -1, -1, 32767).addComponent(this.jLabel6).addComponent(this.sl_green_value, -1, -1, 32767).addComponent(this.jLabel7).addComponent(this.sl_blue_value, -2, 243, -2).addComponent(this.jLabel8).addComponent(this.sl_rand, -2, 243, -2).addComponent(this.pb_done, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_number, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_size, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_speed, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_red_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_green_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_blue_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.cb_rand).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel8).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_rand, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.sl_number.setValue(Integer.parseInt(parameter_array[5]));
        this.cb_rand.setSelected(Boolean.parseBoolean(parameter_array[7]));
        this.sl_speed.setValue(Integer.parseInt(parameter_array[6]));
        this.sl_size.setValue(Integer.parseInt(parameter_array[4]));
        this.sl_red_value.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_green_value.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_blue_value.setValue(Integer.parseInt(parameter_array[3]));
        this.global_flag = true;
    }
    
    private void cb_randActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void sl_numberStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_sizeStateChanged(final ChangeEvent evt) {
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
    
    private void sl_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Meta Balls;" + this.sl_red_value.getValue() + ";" + this.sl_green_value.getValue() + ";" + this.sl_blue_value.getValue() + ";" + this.sl_size.getValue() + ";" + this.sl_number.getValue() + ";" + this.sl_speed.getValue() + ";" + new Boolean(this.cb_rand.isSelected()).toString() + ";" + this.sl_rand.getValue();
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
            Logger.getLogger(MetaBallsOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(MetaBallsOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(MetaBallsOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(MetaBallsOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
