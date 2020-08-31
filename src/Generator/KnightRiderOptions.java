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

public class KnightRiderOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JLabel jLabel10;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JButton pb_done;
    private JSlider sl_blue_value;
    private JSlider sl_green_value;
    private JSlider sl_length;
    private JSlider sl_red_value;
    private JSlider sl_seg_size;
    private JSlider sl_width;
    
    public KnightRiderOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.sl_width = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_length = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_seg_size = new JSlider();
        this.jLabel5 = new JLabel();
        this.sl_green_value = new JSlider();
        this.jLabel6 = new JLabel();
        this.sl_blue_value = new JSlider();
        this.jLabel7 = new JLabel();
        this.jLabel10 = new JLabel();
        this.sl_red_value = new JSlider();
        this.setTitle("Options for \"Knight Rider\"");
        this.setAlwaysOnTop(true);
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                KnightRiderOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                KnightRiderOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                KnightRiderOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.sl_width.setMinimum(1);
        this.sl_width.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                KnightRiderOptions.this.sl_widthStateChanged(evt);
            }
        });
        this.jLabel3.setText("Width                                                 ");
        this.sl_length.setMinimum(1);
        this.sl_length.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                KnightRiderOptions.this.sl_lengthStateChanged(evt);
            }
        });
        this.jLabel4.setText("Length");
        this.sl_seg_size.setMaximum(20);
        this.sl_seg_size.setMinimum(1);
        this.sl_seg_size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                KnightRiderOptions.this.sl_seg_sizeStateChanged(evt);
            }
        });
        this.jLabel5.setText("Red");
        this.sl_green_value.setMaximum(255);
        this.sl_green_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                KnightRiderOptions.this.sl_green_valueStateChanged(evt);
            }
        });
        this.jLabel6.setText("Green");
        this.sl_blue_value.setMaximum(255);
        this.sl_blue_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                KnightRiderOptions.this.sl_blue_valueStateChanged(evt);
            }
        });
        this.jLabel7.setText("Blue");
        this.jLabel10.setText("Segment Size");
        this.sl_red_value.setMaximum(255);
        this.sl_red_value.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                KnightRiderOptions.this.sl_red_valueStateChanged(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.pb_done, GroupLayout.Alignment.LEADING, -1, -1, 32767).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.sl_blue_value, -2, 239, -2).addComponent(this.sl_green_value, -2, 239, -2).addComponent(this.sl_red_value, -2, 239, -2).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.sl_seg_size, -2, 232, -2).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.sl_length, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_width, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jLabel3, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jLabel4, GroupLayout.Alignment.LEADING).addComponent(this.jLabel5, GroupLayout.Alignment.LEADING).addComponent(this.jLabel6, GroupLayout.Alignment.LEADING).addComponent(this.jLabel7, GroupLayout.Alignment.LEADING).addComponent(this.jLabel10, GroupLayout.Alignment.LEADING)))))).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_width, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_length, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel10).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_seg_size, -2, -1, -2).addGap(10, 10, 10).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_red_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_green_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_blue_value, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.sl_width.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_length.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_seg_size.setValue(Integer.parseInt(parameter_array[3]));
        this.sl_red_value.setValue(Integer.parseInt(parameter_array[4]));
        this.sl_green_value.setValue(Integer.parseInt(parameter_array[5]));
        this.sl_blue_value.setValue(Integer.parseInt(parameter_array[6]));
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void sl_widthStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_lengthStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_seg_sizeStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_green_valueStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_blue_valueStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_red_valueStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Knight Rider;" + this.sl_width.getValue() + ";" + this.sl_length.getValue() + ";" + this.sl_seg_size.getValue() + ";" + this.sl_red_value.getValue() + ";" + this.sl_green_value.getValue() + ";" + this.sl_blue_value.getValue();
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
            Logger.getLogger(KnightRiderOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(KnightRiderOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(KnightRiderOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(KnightRiderOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
