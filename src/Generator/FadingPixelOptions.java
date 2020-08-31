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
import javax.swing.JFrame;

public class FadingPixelOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JComboBox cbox_color;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel6;
    private JButton pb_done;
    private JSlider sl_number;
    private JSlider sl_speed;
    
    public FadingPixelOptions(final SuperGenerator generator) {
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
        this.sl_speed = new JSlider();
        this.jLabel4 = new JLabel();
        this.jLabel6 = new JLabel();
        this.cbox_color = new JComboBox();
        this.setTitle("Options for \"Fading Pixel\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                FadingPixelOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                FadingPixelOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FadingPixelOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.sl_number.setMaximum(50);
        this.sl_number.setMinimum(1);
        this.sl_number.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FadingPixelOptions.this.sl_numberStateChanged(evt);
            }
        });
        this.jLabel3.setText("Number");
        this.sl_speed.setMaximum(20);
        this.sl_speed.setMinimum(1);
        this.sl_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FadingPixelOptions.this.sl_speedStateChanged(evt);
            }
        });
        this.jLabel4.setText("Fade Lenght");
        this.jLabel6.setText("Color Palette                                                            ");
        this.cbox_color.setModel(new DefaultComboBoxModel<String>(new String[] { "White", "Red", "Green", "Blue", "Random" }));
        this.cbox_color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FadingPixelOptions.this.cbox_colorActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabel6, -1, -1, 32767).addComponent(this.cbox_color, -2, -1, -2).addComponent(this.jLabel3).addComponent(this.jLabel4).addComponent(this.sl_number, -1, -1, 32767).addComponent(this.sl_speed, -1, -1, 32767).addComponent(this.pb_done, -1, -1, 32767))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_color, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_number, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_speed, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.sl_number.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_speed.setValue(Integer.parseInt(parameter_array[2]));
        this.cbox_color.setSelectedItem(parameter_array[3]);
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void sl_numberStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void cbox_colorActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Fading Pixel;" + this.sl_number.getValue() + ";" + this.sl_speed.getValue() + ";" + this.cbox_color.getSelectedItem().toString();
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
            Logger.getLogger(FadingPixelOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(FadingPixelOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(FadingPixelOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(FadingPixelOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
