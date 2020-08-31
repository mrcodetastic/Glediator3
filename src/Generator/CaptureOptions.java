// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CaptureOptions extends JFrame
{
    boolean set_size;
    boolean global_flag;
    String active_generator;
    SuperGenerator generator;
    private JButton pb_close;
    
    public CaptureOptions(final SuperGenerator generator) {
        this.set_size = false;
        this.global_flag = false;
        this.active_generator = "";
        this.initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.addMouseListener(new MouseAdapter() {});
        this.generator = generator;
    }
    
    private void initComponents() {
        this.pb_close = new JButton();
        this.setTitle("Capture region");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                CaptureOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentMoved(final ComponentEvent evt) {
                CaptureOptions.this.formComponentMoved(evt);
            }
            
            @Override
            public void componentResized(final ComponentEvent evt) {
                CaptureOptions.this.formComponentResized(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                CaptureOptions.this.formComponentShown(evt);
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(final MouseEvent evt) {
                CaptureOptions.this.formMouseDragged(evt);
            }
        });
        this.pb_close.setText("Hide");
        this.pb_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CaptureOptions.this.pb_closeActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(257, 32767).addComponent(this.pb_close).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(136, 32767).addComponent(this.pb_close).addContainerGap()));
        this.pack();
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        final int pos_x = Integer.parseInt(parameter_array[1]);
        final int pos_y = Integer.parseInt(parameter_array[2]);
        final int width = Integer.parseInt(parameter_array[3]);
        final int height = Integer.parseInt(parameter_array[4]);
        this.setSize(width, height);
        this.setLocation(pos_x, pos_y);
        this.global_flag = true;
    }
    
    private void formMouseDragged(final MouseEvent evt) {
    }
    
    private void pb_closeActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void formComponentMoved(final ComponentEvent evt) {
        final int w = this.getWidth();
        final int h = this.getHeight();
        final int x = this.getX();
        final int y = this.getY();
        this.parameter_changed(x, y, w, h);
    }
    
    private void formComponentResized(final ComponentEvent evt) {
        final int w = this.getWidth();
        final int h = this.getHeight();
        final int x = this.getX();
        final int y = this.getY();
        this.parameter_changed(x, y, w, h);
    }
    
    private void parameter_changed(final int x, final int y, final int w, final int h) {
        if (this.global_flag) {
            final String paramter_string = "Capture;" + x + ";" + y + ";" + w + ";" + h;
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
            Logger.getLogger(CaptureOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(CaptureOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(CaptureOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(CaptureOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
