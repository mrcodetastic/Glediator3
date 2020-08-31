// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.io.FilenameFilter;
import java.io.File;
import javax.swing.LayoutStyle;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.AbstractListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Rectangle;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ListModel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

public class AnimatedGifOptions extends JFrame
{
    boolean global_flag;
    String active_generator;
    String path_to_gif_folder;
    String path_to_gif;
    DefaultListModel model;
    JFileChooser file_chooser;
    boolean folder_selected;
    int length;
    int selected_index;
    SuperGenerator generator;
    private JComboBox jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel7;
    private JScrollPane jScrollPane1;
    private JList jl_gifs;
    private JLabel lbl_gif;
    private JButton pb_done;
    private JButton pb_select_folder;
    private JSlider sl_speed;
    
    public AnimatedGifOptions(final SuperGenerator generator) {
        this.global_flag = false;
        this.active_generator = "";
        this.path_to_gif_folder = "";
        this.path_to_gif = "";
        this.model = new DefaultListModel();
        this.file_chooser = new JFileChooser();
        this.folder_selected = false;
        this.length = 0;
        this.selected_index = 0;
        this.initComponents();
        this.generator = generator;
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Icons/Logo.png")).getImage());
        this.file_chooser.setFileSelectionMode(1);
        this.jl_gifs.setSelectionMode(0);
        this.jl_gifs.setModel(this.model);
        this.lbl_gif.setText("");
        this.lbl_gif.setIcon(new ImageIcon("/Icons/G!.gif"));
    }
    
    private void initComponents() {
        this.jComboBox1 = new JComboBox();
        this.pb_done = new JButton();
        this.jLabel1 = new JLabel();
        this.jLabel7 = new JLabel();
        this.sl_speed = new JSlider();
        this.jScrollPane1 = new JScrollPane();
        this.jl_gifs = new JList();
        this.pb_select_folder = new JButton();
        this.lbl_gif = new JLabel();
        this.jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        this.setTitle("Options for \"Animated Gif\"");
        this.setBounds(new Rectangle(100, 100, 0, 0));
        this.setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent evt) {
                AnimatedGifOptions.this.formComponentHidden(evt);
            }
            
            @Override
            public void componentShown(final ComponentEvent evt) {
                AnimatedGifOptions.this.formComponentShown(evt);
            }
        });
        this.pb_done.setText("Done");
        this.pb_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AnimatedGifOptions.this.pb_doneActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Gif's found:                                                                ");
        this.jLabel7.setText("Speed");
        this.sl_speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent evt) {
                AnimatedGifOptions.this.sl_speedStateChanged(evt);
            }
        });
        this.jl_gifs.setModel(new AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            
            @Override
            public int getSize() {
                return this.strings.length;
            }
            
            @Override
            public Object getElementAt(final int i) {
                return this.strings[i];
            }
        });
        this.jl_gifs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent evt) {
                AnimatedGifOptions.this.jl_gifsMouseReleased(evt);
            }
        });
        this.jl_gifs.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                AnimatedGifOptions.this.jl_gifsKeyReleased(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.jl_gifs);
        this.pb_select_folder.setText("Select Folder");
        this.pb_select_folder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AnimatedGifOptions.this.pb_select_folderActionPerformed(evt);
            }
        });
        this.lbl_gif.setIcon(new ImageIcon(this.getClass().getResource("/pictures/brd_hor_lines_bottom_left.jpg")));
        this.lbl_gif.setText("jLabel2");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, -1, -1, 32767).addComponent(this.jScrollPane1).addComponent(this.sl_speed, -1, -1, 32767).addComponent(this.pb_select_folder, -1, -1, 32767).addComponent(this.pb_done, -1, -1, 32767).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel7).addComponent(this.lbl_gif, -2, 290, -2)).addGap(39, 39, 39))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.lbl_gif, -2, 218, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sl_speed, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pb_select_folder).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pb_done).addContainerGap(-1, 32767)));
        this.pack();
    }
    
    private void pb_doneActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void formComponentShown(final ComponentEvent evt) {
        final String[] parameter_array = this.generator.getParameterArray();
        this.path_to_gif_folder = parameter_array[1];
        this.update_gif_list(false);
        this.jl_gifs.setSelectedIndex(Integer.parseInt(parameter_array[3]));
        this.change_gif();
        this.sl_speed.setValue(Integer.parseInt(parameter_array[4]));
        this.global_flag = true;
    }
    
    private void formComponentHidden(final ComponentEvent evt) {
        this.global_flag = false;
    }
    
    private void sl_speedStateChanged(final ChangeEvent evt) {
        this.parameter_changed();
    }
    
    private void pb_select_folderActionPerformed(final ActionEvent evt) {
        this.update_gif_list(true);
        this.parameter_changed();
    }
    
    private void jl_gifsMouseReleased(final MouseEvent evt) {
        this.change_gif();
        this.parameter_changed();
    }
    
    private void jl_gifsKeyReleased(final KeyEvent evt) {
        this.change_gif();
        this.parameter_changed();
    }
    
    private void parameter_changed() {
        if (this.global_flag) {
            final String paramter_string = "Animated Gif;" + this.path_to_gif_folder + ";" + this.path_to_gif + ";" + this.jl_gifs.getSelectedIndex() + ";" + this.sl_speed.getValue() + ";" + "False" + ";";
            this.generator.setParameters(paramter_string);
        }
    }
    
    private void update_gif_list(final boolean dialog) {
        int returnVal = 0;
        if (dialog) {
            returnVal = this.file_chooser.showOpenDialog(this);
        }
        if (returnVal == 0) {
            this.model.removeAllElements();
            try {
                if (dialog) {
                    this.path_to_gif_folder = this.file_chooser.getSelectedFile().getAbsolutePath();
                }
                this.folder_selected = true;
                final String[] fileList = new File(this.path_to_gif_folder).list(new FilenameFilter() {
                    @Override
                    public boolean accept(final File d, final String name) {
                        return name.endsWith(".gif");
                    }
                });
                this.length = fileList.length;
                this.model.removeAllElements();
                for (int i = 0; i < fileList.length; ++i) {
                    this.model.add(i, fileList[i]);
                }
                this.jl_gifs.setModel(this.model);
                if (this.length > 0) {
                    this.jl_gifs.setSelectedIndex(0);
                }
                else {
                    this.path_to_gif = "";
                }
                this.change_gif();
            }
            catch (Exception ex) {
                this.length = 0;
                this.model.removeAllElements();
                this.jl_gifs.setModel(this.model);
                this.folder_selected = false;
                this.path_to_gif = "";
            }
        }
    }
    
    private void change_gif() {
        if (this.length > 0 && this.jl_gifs.getSelectedIndex() > -1) {
            try {
                this.path_to_gif = this.path_to_gif_folder + "/" + this.model.getElementAt(this.jl_gifs.getSelectedIndex()).toString();
                this.lbl_gif.setIcon(new ImageIcon(this.path_to_gif));
            }
            catch (Exception ex) {}
        }
        else {
            this.lbl_gif.setIcon(new ImageIcon("/Icons/G!.gif"));
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
            Logger.getLogger(AnimatedGifOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(AnimatedGifOptions.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(AnimatedGifOptions.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(AnimatedGifOptions.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
