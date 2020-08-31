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
import javax.swing.JFrame;

public class FireOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel9;
    private JButton pb_done;
    private JSlider sl_max_int;
    private JSlider sl_rnd_fraction;
    private JSlider sl_speed;
    private JSlider sl_stepwidth;
    
    public FireOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.sl_stepwidth = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_rnd_fraction = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_max_int = new JSlider();
        this.jLabel5 = new JLabel();
        this.jLabel9 = new JLabel();
        this.sl_speed = new JSlider();
        this.setTitle("Options for \"Fire\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                FireOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                FireOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FireOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.sl_stepwidth.setMaximum(8);
        this.sl_stepwidth.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FireOptions.this.sl_stepwidthStateChanged(evt);
            }
        });
        this.jLabel3.setText("Stepwidth");
        this.sl_rnd_fraction.setMaximum(15);
        this.sl_rnd_fraction.setMinimum(1);
        this.sl_rnd_fraction.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FireOptions.this.sl_rnd_fractionStateChanged(evt);
            }
        });
        this.jLabel4.setText("Random fraction");
        this.sl_max_int.setMaximum(99);
        this.sl_max_int.setMinimum(50);
        this.sl_max_int.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FireOptions.this.sl_max_intStateChanged(evt);
            }
        });
        this.jLabel5.setText("Max start intensity");
        this.jLabel9.setText("Speed of change");
        this.sl_speed.setMaximum(50);
        this.sl_speed.setMinimum(1);
        this.sl_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FireOptions.this.sl_speedStateChanged(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.pb_done, -2, 195, -2).addComponent(this.jLabel3).addComponent(this.sl_stepwidth, -1, -1, 32767).addComponent(this.jLabel4).addComponent(this.sl_rnd_fraction, -1, -1, 32767).addComponent(this.jLabel9).addComponent(this.sl_speed, -1, -1, 32767).addComponent(this.jLabel5).addComponent(this.sl_max_int, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_stepwidth, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_rnd_fraction, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_speed, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_max_int, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.sl_stepwidth.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_rnd_fraction.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_speed.setValue(Integer.parseInt(parameter_array[3]));
        this.sl_max_int.setValue(Integer.parseInt(parameter_array[4]));
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void sl_stepwidthStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_rnd_fractionStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_max_intStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Fire;" + this.sl_stepwidth.getValue() + ";" + this.sl_rnd_fraction.getValue() + ";" + this.sl_speed.getValue() + ";" + this.sl_max_int.getValue();
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
            Logger.getLogger(FireOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(FireOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(FireOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(FireOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
