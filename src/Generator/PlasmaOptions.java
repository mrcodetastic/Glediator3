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

public class PlasmaOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JButton pb_done;
    private JSlider sl_cell_size_x;
    private JSlider sl_cell_size_y;
    private JSlider sl_stepwidth;
    
    public PlasmaOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_done = new JButton();
        this.sl_stepwidth = new JSlider();
        this.jLabel4 = new JLabel();
        this.sl_cell_size_x = new JSlider();
        this.jLabel5 = new JLabel();
        this.sl_cell_size_y = new JSlider();
        this.jLabel7 = new JLabel();
        this.setTitle("Options for \"Plasma\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                PlasmaOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                PlasmaOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                PlasmaOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.sl_stepwidth.setMaximum(150);
        this.sl_stepwidth.setMinimum(1);
        this.sl_stepwidth.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                PlasmaOptions.this.sl_stepwidthStateChanged(evt);
            }
        });
        this.jLabel4.setText("Stepwidth");
        this.sl_cell_size_x.setMaximum(20);
        this.sl_cell_size_x.setMinimum(1);
        this.sl_cell_size_x.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                PlasmaOptions.this.sl_cell_size_xStateChanged(evt);
            }
        });
        this.jLabel5.setText("Cell Size X");
        this.sl_cell_size_y.setMaximum(20);
        this.sl_cell_size_y.setMinimum(1);
        this.sl_cell_size_y.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                PlasmaOptions.this.sl_cell_size_yStateChanged(evt);
            }
        });
        this.jLabel7.setText("Cell Size Y");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.sl_stepwidth, -1, -1, 32767).addComponent(this.jLabel5).addComponent(this.sl_cell_size_x, -1, -1, 32767).addComponent(this.jLabel7).addComponent(this.sl_cell_size_y, -2, 243, -2).addComponent(this.pb_done, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_stepwidth, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_cell_size_x, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_cell_size_y, -2, -1, -2).addGap(18, 18, 18).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.sl_stepwidth.setValue(Integer.parseInt(parameter_array[1]));
        this.sl_cell_size_x.setValue(Integer.parseInt(parameter_array[2]));
        this.sl_cell_size_y.setValue(Integer.parseInt(parameter_array[3]));
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void sl_stepwidthStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_cell_size_xStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void sl_cell_size_yStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Plasma;" + this.sl_stepwidth.getValue() + ";" + this.sl_cell_size_x.getValue() + ";" + this.sl_cell_size_y.getValue();
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
            Logger.getLogger(PlasmaOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(PlasmaOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(PlasmaOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(PlasmaOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
