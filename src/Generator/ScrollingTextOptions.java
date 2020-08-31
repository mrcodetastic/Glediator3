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
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ScrollingTextOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JComboBox cbox_font;
    private JComboBox cbox_style;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JButton pb_done;
    private JSlider sl_blue;
    private JSlider sl_green;
    private JSlider sl_position;
    private JSlider sl_red;
    private JSlider sl_size;
    private JTextField tf_text;
    
    public ScrollingTextOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.cbox_font = new JComboBox();
        this.jLabel2 = new JLabel();
        this.sl_position = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_red = new JSlider();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.cbox_style = new JComboBox();
        this.jLabel1 = new JLabel();
        this.tf_text = new JTextField();
        this.jLabel7 = new JLabel();
        this.sl_size = new JSlider();
        this.sl_green = new JSlider();
        this.jLabel8 = new JLabel();
        this.sl_blue = new JSlider();
        this.jLabel9 = new JLabel();
        this.setTitle("Options for \"Scrolling Text\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                ScrollingTextOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                ScrollingTextOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ScrollingTextOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.cbox_font.setModel(new DefaultComboBoxModel<String>(new String[] { "Sans Serif", "Dialog", "Mono Spaced" }));
        this.cbox_font.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ScrollingTextOptions.this.cbox_fontActionPerformed(evt);
            }
        });
        this.jLabel2.setText("Font");
        this.sl_position.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ScrollingTextOptions.this.sl_positionStateChanged(evt);
            }
        });
        this.jLabel3.setText("Position");
        this.sl_red.setMaximum(255);
        this.sl_red.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ScrollingTextOptions.this.sl_redStateChanged(evt);
            }
        });
        this.jLabel4.setText("Red");
        this.jLabel5.setText("Style");
        this.cbox_style.setModel(new DefaultComboBoxModel<String>(new String[] { "Normal", "Bold", "Italic" }));
        this.cbox_style.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ScrollingTextOptions.this.cbox_styleActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Text                                                    ");
        this.tf_text.setText("HELLO !!");
        this.tf_text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ScrollingTextOptions.this.tf_textActionPerformed(evt);
            }
        });
        this.jLabel7.setText("Size");
        this.sl_size.setMaximum(40);
        this.sl_size.setMinimum(8);
        this.sl_size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ScrollingTextOptions.this.sl_sizeStateChanged(evt);
            }
        });
        this.sl_green.setMaximum(255);
        this.sl_green.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ScrollingTextOptions.this.sl_greenStateChanged(evt);
            }
        });
        this.jLabel8.setText("Green");
        this.sl_blue.setMaximum(255);
        this.sl_blue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                ScrollingTextOptions.this.sl_blueStateChanged(evt);
            }
        });
        this.jLabel9.setText("Blue");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2).addGap(0, 0, 32767)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.tf_text).addComponent(this.jLabel1, -1, -1, 32767)).addContainerGap()))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel5).addComponent(this.jLabel7).addComponent(this.jLabel3).addComponent(this.jLabel4).addComponent(this.jLabel8).addComponent(this.jLabel9))).addComponent(this.sl_size, -1, 242, 32767).addComponent(this.sl_position, -1, -1, 32767).addComponent(this.sl_red, -1, -1, 32767).addComponent(this.sl_green, -1, -1, 32767).addComponent(this.sl_blue, -1, -1, 32767).addComponent(this.pb_done, -1, -1, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.cbox_font, -2, 242, -2).addComponent(this.cbox_style, -2, 242, -2)))).addGap(0, 0, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.tf_text, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_font, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_style, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_size, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_position, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_red, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel8).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_green, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_blue, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.tf_text.setText(parameter_array[1]);
        this.cbox_font.setSelectedItem(parameter_array[2]);
        this.sl_size.setValue(Integer.parseInt(parameter_array[3]));
        this.cbox_style.setSelectedItem(parameter_array[4]);
        this.sl_position.setValue(Integer.parseInt(parameter_array[5]));
        this.sl_red.setValue(Integer.parseInt(parameter_array[6]));
        this.sl_green.setValue(Integer.parseInt(parameter_array[7]));
        this.sl_blue.setValue(Integer.parseInt(parameter_array[8]));
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void cbox_fontActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_positionStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_redStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void cbox_styleActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_greenStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_blueStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void tf_textActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_sizeStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Scrolling Text;" + this.tf_text.getText() + ";" + this.cbox_font.getSelectedItem().toString() + ";" + this.sl_size.getValue() + ";" + this.cbox_style.getSelectedItem().toString() + ";" + this.sl_position.getValue() + ";" + this.sl_red.getValue() + ";" + this.sl_green.getValue() + ";" + this.sl_blue.getValue();
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
            Logger.getLogger(ScrollingTextOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(ScrollingTextOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(ScrollingTextOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(ScrollingTextOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
