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

public class FadeAndScrollOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generaor;
    private JComboBox cbox_palette;
    private JComboBox cbox_scroll_dir;
    private JComboBox cbox_scroll_type;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JButton pb_done;
    private JSlider sl_speed;
    private JSlider sl_zoom;
    
    public FadeAndScrollOptions(final SuperGenerator generaor) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generaor = generaor;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.cbox_scroll_type = new JComboBox();
        this.jLabel2 = new JLabel();
        this.sl_speed = new JSlider();
        this.jLabel3 = new JLabel();
        this.sl_zoom = new JSlider();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.cbox_scroll_dir = new JComboBox();
        this.jLabel6 = new JLabel();
        this.cbox_palette = new JComboBox();
        this.setTitle("Options for \"Fade and Scroll\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                FadeAndScrollOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                FadeAndScrollOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FadeAndScrollOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.cbox_scroll_type.setModel(new DefaultComboBoxModel<String>(new String[] { "Flat", "Vertical", "Horizontal", "Diagonal Left", "Diagonal Right", "Horizontal Symetric", "Vertical Symetric", "Circle", "Hyperbel", "Diamond", "Plasma" }));
        this.cbox_scroll_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FadeAndScrollOptions.this.cbox_scroll_typeActionPerformed(evt);
            }
        });
        this.jLabel2.setText("Scroll Type");
        this.sl_speed.setMaximum(60);
        this.sl_speed.setMinimum(1);
        this.sl_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FadeAndScrollOptions.this.sl_speedStateChanged(evt);
            }
        });
        this.jLabel3.setText("Speed");
        this.sl_zoom.setMinimum(1);
        this.sl_zoom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                FadeAndScrollOptions.this.sl_zoomStateChanged(evt);
            }
        });
        this.jLabel4.setText("Zoom");
        this.jLabel5.setText("Scroll Direction");
        this.cbox_scroll_dir.setModel(new DefaultComboBoxModel<String>(new String[] { "Forward", "Backward" }));
        this.cbox_scroll_dir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FadeAndScrollOptions.this.cbox_scroll_dirActionPerformed(evt);
            }
        });
        this.jLabel6.setText("Color Palette");
        this.cbox_palette.setModel(new DefaultComboBoxModel<String>(new String[] { "RGB", "Red", "Green", "Blue", "White", "Half" }));
        this.cbox_palette.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                FadeAndScrollOptions.this.cbox_paletteActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.jLabel5).addComponent(this.cbox_scroll_dir, -2, -1, -2).addComponent(this.jLabel6).addComponent(this.cbox_palette, -2, -1, -2).addComponent(this.jLabel3).addComponent(this.jLabel4).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.pb_done, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.sl_zoom, GroupLayout.Alignment.LEADING, 0, 0, 32767).addComponent(this.sl_speed, GroupLayout.Alignment.LEADING, 0, 0, 32767).addComponent(this.cbox_scroll_type, GroupLayout.Alignment.LEADING, 0, -1, 32767))).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_scroll_type, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_scroll_dir, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbox_palette, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_speed, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_zoom, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generaor.getParameterArray();
        this.sl_speed.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_zoom.setValue(Integer.parseInt(parameter_array[2]));
        this.cbox_scroll_type.setSelectedItem(parameter_array[3]);
        this.cbox_scroll_dir.setSelectedItem(parameter_array[4]);
        this.cbox_palette.setSelectedItem(parameter_array[5]);
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void cbox_scroll_typeActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_zoomStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void cbox_scroll_dirActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void cbox_paletteActionPerformed(final ActionEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Fade and Scroll;" + this.sl_speed.getValue() + ";" + this.sl_zoom.getValue() + ";" + this.cbox_scroll_type.getSelectedItem().toString() + ";" + this.cbox_scroll_dir.getSelectedItem().toString() + ";" + this.cbox_palette.getSelectedItem().toString();
            this.generaor.setParameters(paramter_string);
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
            Logger.getLogger(FadeAndScrollOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(FadeAndScrollOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(FadeAndScrollOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(FadeAndScrollOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
