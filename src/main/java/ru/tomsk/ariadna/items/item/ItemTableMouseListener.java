package ru.tomsk.ariadna.items.item;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.data.Item;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 * @see http://www.rgagnon.com/javadetails/java-0336.html
 * @see http://javaswing.wordpress.com/2009/09/16/jtable_right_button_selection/
 */
public class ItemTableMouseListener extends MouseAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ItemTableMouseListener.class);

    public ItemTableMouseListener() {
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 2) {
            logger.debug("Двойной клик левой кнопкой мыши");
            ItemTable itemTable = (ItemTable) event.getSource();
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow >= 0) {
                ItemTableModel model = (ItemTableModel) itemTable.getModel();
                Item item = model.getItem(selectedRow);
                logger.debug(item.toString());
                CreateItemDialog itemDialog = new CreateItemDialog(item);
                itemDialog.setVisible(true);
            } else {
                //Элемент не выбран
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON3 && event.getClickCount() == 1) {
            logger.debug("Нажатие правой кнопкой мыши");
            ItemTable itemTable = (ItemTable) event.getSource();
            int selectedRow = selectRow(itemTable, event.getPoint());
                ItemTableModel model = (ItemTableModel) itemTable.getModel();
            if (selectedRow >= 0) {
                Item item = model.getItem(selectedRow);
                logger.debug(item.toString());
            } else {
                //Элемент не выбран
            }
        }
    }

    /**
     * Выделить строку и получить её индекс.
     *
     * @param table таблица в которой надо выделить строку
     * @param point место расположение выделяемой строки
     * @return выбранная строка
     */
    private int selectRow(JTable table, Point point) {
        //Выделение ячейки сделано для красоты
        int selectedColumn = table.columnAtPoint(point);
        table.setColumnSelectionInterval(selectedColumn, selectedColumn);
        int selectedRow = table.rowAtPoint(point);
        table.setRowSelectionInterval(selectedRow, selectedRow);
        return selectedRow;
    }
}
