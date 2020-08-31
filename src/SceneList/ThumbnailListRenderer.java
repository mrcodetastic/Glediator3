// 
// Decompiled by Procyon v0.5.36
// 

package SceneList;

import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;

public class ThumbnailListRenderer extends JLabel implements ListCellRenderer
{
    private Color back_color;
    private Color fore_color;
    
    public ThumbnailListRenderer(final Color fore_color) {
        this.fore_color = fore_color;
        this.setOpaque(true);
        this.setIconTextGap(12);
    }
    
    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        final ThumbnailListEntry entry = (ThumbnailListEntry)value;
        this.setText(entry.getTitle());
        this.setIcon(entry.getImage());
        if (isSelected) {
            this.setForeground(Color.white);
        }
        else {
            this.setForeground(this.fore_color);
        }
        return this;
    }
}
