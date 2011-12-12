package ru.tomsk.ariadna.items;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
public class TableDateRender extends DefaultTableCellRenderer {

    private SimpleDateFormat formatter;

    public TableDateRender(String pattern) {
        this(new SimpleDateFormat(pattern));
    }

    public TableDateRender(String pattern, int alignment) {
        this(new SimpleDateFormat(pattern), alignment);
    }

    public TableDateRender(SimpleDateFormat formatter) {
        this(formatter, JLabel.LEFT);
    }

    public TableDateRender(SimpleDateFormat formatter, int alignment) {
        this.formatter = formatter;
        setHorizontalAlignment(alignment);

    }

    @Override
    public void setValue(Object value) {
        if (value == null) {
            setText("");
        } else {
            Date date = (Date) value;
            setText(formatter.format(date));
        }
    }
}
