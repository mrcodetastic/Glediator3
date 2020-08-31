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

public class SimpleSpectrumOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JComboBox cbox_position;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JButton pb_done;
    private JSlider sl_blue_end;
    private JSlider sl_blue_start;
    private JSlider sl_gain;
    private JSlider sl_green_end;
    private JSlider sl_green_start;
    private JSlider sl_red_end;
    private JSlider sl_red_start;
    
    public SimpleSpectrumOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.cbox_position = new JComboBox();
        this.jLabel2 = new JLabel();
        this.sl_gain = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_red_start = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_red_end = new JSlider();
        this.jLabel5 = new JLabel();
        this.sl_green_start = new JSlider();
        this.jLabel6 = new JLabel();
        this.sl_green_end = new JSlider();
        this.jLabel7 = new JLabel();
        this.sl_blue_end = new JSlider();
        this.jLabel8 = new JLabel();
        this.sl_blue_start = new JSlider();
        this.jLabel9 = new JLabel();
        this.setTitle("Options for \"Spectrum\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                SimpleSpectrumOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                SimpleSpectrumOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                SimpleSpectrumOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.cbox_position.setModel(new DefaultComboBoxModel<String>(new String[] { "Top", "Bottom", "Left", "Right" }));
        this.cbox_position.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                SimpleSpectrumOptions.this.cbox_positionActionPerformed(evt);
            }
        });
        this.jLabel2.setText("Position");
        this.sl_gain.setMaximum(300);
        this.sl_gain.setMinimum(1);
        this.sl_gain.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                SimpleSpectrumOptions.this.sl_gainStateChanged(evt);
            }
        });
        this.jLabel3.setText("Gain");
        this.sl_red_start.setMaximum(255);
        this.sl_red_start.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                SimpleSpectrumOptions.this.sl_red_startStateChanged(evt);
            }
        });
        this.jLabel4.setText("Red_Start");
        this.sl_red_end.setMaximum(255);
        this.sl_red_end.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                SimpleSpectrumOptions.this.sl_red_endStateChanged(evt);
            }
        });
        this.jLabel5.setText("Red_End");
        this.sl_green_start.setMaximum(255);
        this.sl_green_start.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                SimpleSpectrumOptions.this.sl_green_startStateChanged(evt);
            }
        });
        this.jLabel6.setText("Green_Start");
        this.sl_green_end.setMaximum(255);
        this.sl_green_end.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                SimpleSpectrumOptions.this.sl_green_endStateChanged(evt);
            }
        });
        this.jLabel7.setText("Green_End");
        this.sl_blue_end.setMaximum(255);
        this.sl_blue_end.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                SimpleSpectrumOptions.this.sl_blue_endStateChanged(evt);
            }
        });
        this.jLabel8.setText("Blue_End");
        this.sl_blue_start.setMaximum(255);
        this.sl_blue_start.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                SimpleSpectrumOptions.this.sl_blue_startStateChanged(evt);
            }
        });
        this.jLabel9.setText("Blue_Start");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabel2).addComponent(this.jLabel3).addComponent(this.jLabel4).addComponent(this.jLabel5).addComponent(this.jLabel6).addComponent(this.sl_gain, -1, -1, 32767).addComponent(this.sl_red_start, -1, -1, 32767).addComponent(this.sl_red_end, -1, -1, 32767).addComponent(this.sl_green_start, -1, -1, 32767).addComponent(this.sl_green_end, -1, 243, 32767).addComponent(this.cbox_position, 0, -1, 32767)).addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabel9).addComponent(this.sl_blue_start, -1, -1, 32767).addComponent(this.jLabel8).addComponent(this.sl_blue_end, -2, 243, -2)).addComponent(this.jLabel7)).addComponent(this.pb_done, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_position, -2, -1, -2).addGap(17, 17, 17).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_gain, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_red_start, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_red_end, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_green_start, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_green_end, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_blue_start, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel8).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_blue_end, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.cbox_position.setSelectedItem(parameter_array[1]);
        this.sl_gain.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_red_start.setValue(Integer.parseInt(parameter_array[3]));
        this.sl_red_end.setValue(Integer.parseInt(parameter_array[4]));
        this.sl_green_start.setValue(Integer.parseInt(parameter_array[5]));
        this.sl_green_end.setValue(Integer.parseInt(parameter_array[6]));
        this.sl_blue_start.setValue(Integer.parseInt(parameter_array[7]));
        this.sl_blue_end.setValue(Integer.parseInt(parameter_array[8]));
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void cbox_positionActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_gainStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_red_startStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_red_endStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_green_startStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_green_endStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_blue_endStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_blue_startStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Simple Spectrum;" + this.cbox_position.getSelectedItem().toString() + ";" + this.sl_gain.getValue() + ";" + this.sl_red_start.getValue() + ";" + this.sl_red_end.getValue() + ";" + this.sl_green_start.getValue() + ";" + this.sl_green_end.getValue() + ";" + this.sl_blue_start.getValue() + ";" + this.sl_blue_end.getValue();
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
            Logger.getLogger(SimpleSpectrumOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(SimpleSpectrumOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(SimpleSpectrumOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(SimpleSpectrumOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
