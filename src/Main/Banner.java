// 
// Decompiled by Procyon v0.5.36
// 

package Main;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class Banner extends JFrame
{
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JButton pb_close;
    
    public Banner() {
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
    }
    
    private void initComponents() {
        this.pb_close = new JButton();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.setTitle("Hit banner to close this window");
        this.setAlwaysOnTop(true);
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.getContentPane().setLayout(new GridBagLayout());
        this.pb_close.setIcon(new ImageIcon(this.getClass().getResource("/pictures/Banner.gif")));
        pb_close.setBorder(null);
        this.pb_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Banner.this.pb_closeActionPerformed(evt);
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.pb_close, gridBagConstraints);
        this.jLabel1.setText("Glediator (C) 2014 - All rights reserved by Ren\u00e9 Heller");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(this.jLabel1, gridBagConstraints);
        

        this.pack();
    }
    
    private void pb_closeActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
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
            Logger.getLogger(Banner.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(Banner.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(Banner.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(Banner.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Banner().setVisible(true);
            }
        });
    }
}
