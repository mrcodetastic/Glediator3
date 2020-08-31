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

public class StroboOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JComboBox cbox_mode;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JButton pb_done;
    private JSlider sl_blue;
    private JSlider sl_duration;
    private JSlider sl_green;
    private JSlider sl_red;
    private JSlider sl_speed;
    private JSlider sl_trigger_level;
    
    public StroboOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.cbox_mode = new JComboBox();
        this.jLabel2 = new JLabel();
        this.sl_red = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_green = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_blue = new JSlider();
        this.jLabel5 = new JLabel();
        this.sl_speed = new JSlider();
        this.jLabel6 = new JLabel();
        this.sl_duration = new JSlider();
        this.jLabel7 = new JLabel();
        this.sl_trigger_level = new JSlider();
        this.jLabel8 = new JLabel();
        this.setTitle("Options for \"Strobo\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                StroboOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                StroboOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                StroboOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.cbox_mode.setModel(new DefaultComboBoxModel<String>(new String[] { "None", "Trigger", "Adaptive Trigger" }));
        this.cbox_mode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                StroboOptions.this.cbox_modeActionPerformed(evt);
            }
        });
        this.jLabel2.setText("Trigger Mode");
        this.sl_red.setMaximum(255);
        this.sl_red.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                StroboOptions.this.sl_redStateChanged(evt);
            }
        });
        this.jLabel3.setText("Red");
        this.sl_green.setMaximum(255);
        this.sl_green.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                StroboOptions.this.sl_greenStateChanged(evt);
            }
        });
        this.jLabel4.setText("Green");
        this.sl_blue.setMaximum(255);
        this.sl_blue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                StroboOptions.this.sl_blueStateChanged(evt);
            }
        });
        this.jLabel5.setText("Blue");
        this.sl_speed.setMaximum(50);
        this.sl_speed.setMinimum(1);
        this.sl_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                StroboOptions.this.sl_speedStateChanged(evt);
            }
        });
        this.jLabel6.setText("Speed");
        this.sl_duration.setMaximum(50);
        this.sl_duration.setMinimum(1);
        this.sl_duration.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                StroboOptions.this.sl_durationStateChanged(evt);
            }
        });
        this.jLabel7.setText("Duration");
        this.sl_trigger_level.setMinimum(1);
        this.sl_trigger_level.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                StroboOptions.this.sl_trigger_levelStateChanged(evt);
            }
        });
        this.jLabel8.setText("Trigger Level");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabel2).addComponent(this.jLabel3).addComponent(this.jLabel4).addComponent(this.jLabel5).addComponent(this.jLabel6).addComponent(this.sl_red, -1, -1, 32767).addComponent(this.sl_green, -1, -1, 32767).addComponent(this.sl_blue, -1, -1, 32767).addComponent(this.sl_speed, -1, -1, 32767).addComponent(this.sl_duration, -1, 243, 32767).addComponent(this.cbox_mode, 0, -1, 32767)).addComponent(this.jLabel7).addComponent(this.jLabel8).addComponent(this.sl_trigger_level, -2, 243, -2).addComponent(this.pb_done, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_mode, -2, -1, -2).addGap(17, 17, 17).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_red, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_green, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_blue, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_speed, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_duration, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel8).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.sl_trigger_level, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.sl_red.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_green.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_blue.setValue(Integer.parseInt(parameter_array[3]));
        this.cbox_mode.setSelectedItem(parameter_array[4]);
        this.sl_speed.setValue(Integer.parseInt(parameter_array[5]));
        this.sl_duration.setValue(Integer.parseInt(parameter_array[6]));
        this.sl_trigger_level.setValue(Integer.parseInt(parameter_array[7]));
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void cbox_modeActionPerformed(final ActionEvent evt) {
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
    
    private void sl_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_durationStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_trigger_levelStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Strobo;" + this.sl_red.getValue() + ";" + this.sl_green.getValue() + ";" + this.sl_blue.getValue() + ";" + this.cbox_mode.getSelectedItem().toString() + ";" + this.sl_speed.getValue() + ";" + this.sl_duration.getValue() + ";" + this.sl_trigger_level.getValue();
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
            Logger.getLogger(StroboOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(StroboOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(StroboOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(StroboOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
