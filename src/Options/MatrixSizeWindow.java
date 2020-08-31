// 
// Decompiled by Procyon v0.5.36
// 

package Options;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import Main.GlediatorModel;
import javax.swing.JFrame;

public class MatrixSizeWindow extends JFrame
{
    GlediatorModel glediator_model;
    int sx;
    int sy;
    int gap;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JTabbedPane jTabbedPane2;
    private JTabbedPane jTabbedPane3;
    private JButton pb_apply_changes;
    private JButton pb_chancel;
    private JButton pb_done;
    public static JTextField tf_size_x;
    public static JTextField tf_size_y;
    public static JTextField tf_space;
    
    public MatrixSizeWindow(final GlediatorModel glediator_model) {
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.glediator_model = glediator_model;
    }
    
    private void initComponents() {
        this.jTabbedPane2 = new JTabbedPane();
        this.jTabbedPane3 = new JTabbedPane();
        this.pb_apply_changes = new JButton();
        this.pb_done = new JButton();
        this.pb_chancel = new JButton();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        MatrixSizeWindow.tf_size_x = new JTextField();
        MatrixSizeWindow.tf_size_y = new JTextField();
        this.jLabel5 = new JLabel();
        MatrixSizeWindow.tf_space = new JTextField();
        this.jLabel1 = new JLabel();
        this.jTabbedPane2.addTab("tab1", this.jTabbedPane3);
        this.setTitle("Matrix Size");
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(final ComponentEvent evt) {
                MatrixSizeWindow.this.formComponentShown(evt);
            }
        });
        this.getContentPane().setLayout(new GridBagLayout());
        this.pb_apply_changes.setText("Apply Changes");
        this.pb_apply_changes.setMaximumSize(new Dimension(140, 20));
        this.pb_apply_changes.setMinimumSize(new Dimension(140, 20));
        this.pb_apply_changes.setPreferredSize(new Dimension(140, 20));
        this.pb_apply_changes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MatrixSizeWindow.this.pb_apply_changesActionPerformed(evt);
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_apply_changes, gridBagConstraints);
        this.pb_done.setText("Done");
        this.pb_done.setMaximumSize(new Dimension(140, 20));
        this.pb_done.setMinimumSize(new Dimension(140, 20));
        this.pb_done.setPreferredSize(new Dimension(140, 20));
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MatrixSizeWindow.this.pb_doneActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_done, gridBagConstraints);
        this.pb_chancel.setText("Cancel");
        this.pb_chancel.setMaximumSize(new Dimension(140, 20));
        this.pb_chancel.setMinimumSize(new Dimension(140, 20));
        this.pb_chancel.setPreferredSize(new Dimension(140, 20));
        this.pb_chancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MatrixSizeWindow.this.pb_chancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.pb_chancel, gridBagConstraints);
        this.jLabel3.setText("Size_X");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jLabel3, gridBagConstraints);
        this.jLabel4.setText("Size_Y");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jLabel4, gridBagConstraints);
        MatrixSizeWindow.tf_size_x.setText("32");
        MatrixSizeWindow.tf_size_x.setCursor(new Cursor(2));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(MatrixSizeWindow.tf_size_x, gridBagConstraints);
        MatrixSizeWindow.tf_size_y.setText("16");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(MatrixSizeWindow.tf_size_y, gridBagConstraints);
        this.jLabel5.setText("Space");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jLabel5, gridBagConstraints);
        MatrixSizeWindow.tf_space.setText("1");
        MatrixSizeWindow.tf_space.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MatrixSizeWindow.this.tf_spaceActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(MatrixSizeWindow.tf_space, gridBagConstraints);
        this.jLabel1.setText("---");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jLabel1, gridBagConstraints);
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.apply_changes();
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
    }
    
    private void pb_apply_changesActionPerformed(final ActionEvent evt) {
        this.apply_changes();
    }
    
    private void tf_spaceActionPerformed(final ActionEvent evt) {
    }
    
    private void pb_chancelActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void apply_changes() {
        try {
            this.sx = Integer.parseInt(MatrixSizeWindow.tf_size_x.getText());
            this.sy = Integer.parseInt(MatrixSizeWindow.tf_size_y.getText());
            this.gap = Integer.parseInt(MatrixSizeWindow.tf_space.getText());
            if (this.sx > 0 && this.sy > 0 && this.gap >= 0) {
                this.glediator_model.triggerFrameSizeChange();
            }
            else {
                System.out.println("MatrixSizeOptions: Wrong matrix size parameter(s).");
            }
        }
        catch (Exception ex) {
            System.out.println("MatrixSizeOptions: Failed to parse matrix size parameter(s).");
        }
    }
    
    public void setEditable(final boolean status) {
        MatrixSizeWindow.tf_size_x.setEnabled(status);
        MatrixSizeWindow.tf_size_y.setEnabled(status);
        MatrixSizeWindow.tf_space.setEnabled(status);
        this.pb_apply_changes.setEnabled(status);
        this.pb_done.setEnabled(status);
    }
    
    public int getSx() {
        return this.sx;
    }
    
    public void setSx(final int size_x) {
        this.sx = size_x;
        MatrixSizeWindow.tf_size_x.setText("" + size_x);
    }
    
    public int getSy() {
        return this.sy;
    }
    
    public void setSy(final int size_y) {
        this.sy = size_y;
        MatrixSizeWindow.tf_size_y.setText("" + size_y);
    }
    
    public int getGap() {
        return this.gap;
    }
    
    public void setGap(final int gap) {
        this.gap = gap;
        MatrixSizeWindow.tf_space.setText("" + gap);
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
            Logger.getLogger(MatrixSizeWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(MatrixSizeWindow.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(MatrixSizeWindow.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(MatrixSizeWindow.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
