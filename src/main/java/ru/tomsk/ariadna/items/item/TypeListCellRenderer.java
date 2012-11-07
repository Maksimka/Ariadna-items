package ru.tomsk.ariadna.items.item;

import java.awt.Component;
import java.io.Serializable;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import ru.tomsk.ariadna.items.data.Type;

/**
 *
 * @author Maksim
 */
public class TypeListCellRenderer extends JLabel implements ListCellRenderer<Type>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a default renderer object for an item in a list.
     */
    public TypeListCellRenderer() {
        super();
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Type> list,
            Type type,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        URL resource = getClass().getClassLoader().getResource("icon/mark.png");
        setIcon(new ImageIcon(resource));
        if (type.getCacheItemCount() == -1) {
            setText(type.getName());
        } else {
            setText(type.getName() + " (" + type.getCacheItemCount() + ')');
        }
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
}