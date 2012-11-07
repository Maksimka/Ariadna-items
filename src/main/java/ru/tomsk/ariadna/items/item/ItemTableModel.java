package ru.tomsk.ariadna.items.item;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.data.Item;

/**
 * Модель таблицы описывающая снаряжения выдачи.
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class ItemTableModel extends AbstractTableModel {

    private static final Logger logger = LoggerFactory.getLogger(ItemTableModel.class);

    public static final int NUMBER = 0;

    public static final int VENDOR = 1;

    public static final int MODEL = 2;

    public static final int NOTE = 3;

    public static final int RECEIPT_DATE = 4;

    private List<Item> items;

    public ItemTableModel() {
        this.items = Collections.emptyList();
    }

    public ItemTableModel(List<Item> items) {
        this.items = items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        fireTableDataChanged();
    }

    public void removeItem(int row) {
        items.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void removeItems(int[] rows) {
        Arrays.sort(rows);
        if (rows.length == 1) {
            removeItem(rows[0]);
        } else {
            int[] mutableRows = new int[rows.length];
            System.arraycopy(rows, 0, mutableRows, 0, rows.length);
            int firstRow = rows[0];
            int count = 1;
            for (int i = 0; i < rows.length; i++) {
                int mutableRow = mutableRows[i];
                items.remove(mutableRow);
                int nextItem = i + 1;
                changeRows(mutableRow, i, mutableRows, 1);
                int currentRow = rows[i];
                boolean hasNextRow = nextItem < rows.length;
                if (hasNextRow) {
                    boolean isOrder = (currentRow + 1) == rows[nextItem];
                    if (isOrder) {
                        count++;
                    } else {
                        fireTableRowsDeleted(firstRow, currentRow);
                        changeRows(currentRow, i, rows, count);
                        count = 1;
                        firstRow = rows[nextItem];
                    }
                } else {
                    fireTableRowsDeleted(firstRow, currentRow);
                }
            }
        }
    }

    private void changeRows(int removedRow, int startPosition, int[] mutableRows, int count) {
        for (int i = startPosition; i < mutableRows.length; i++) {
            int mutableRow = mutableRows[i];
            if (mutableRow > removedRow) {
                mutableRows[i] = mutableRow - count;
            }
        }
    }

    @Override
    public int getRowCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    /**
     * Возвращяет общие количество свойств выдачи.
     *
     * @return общие количество свойств выдачи
     */
    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case VENDOR:
                return "Производитель";
            case NUMBER:
                return "№";
            case MODEL:
                return "Модель";
            case NOTE:
                return "Примечание";
            case RECEIPT_DATE:
                return "Добавлено";
            default:
                logger.warn("Попытка получить значение для несуществующего стообца. "
                        + "Столбец: " + columnIndex);
                return "Столбец №" + columnIndex;
        }
    }

    /**
     * Возвращяет свойство пакета выдачи.
     *
     * @param rowIndex номер пакета выдачи.
     * @param columnIndex номер столбца ячейки
     * @return свойство пакета выдачи
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = items.get(rowIndex);
        switch (columnIndex) {
            case VENDOR:
                return item.getModel().getModelPK().getVendor();
            case NUMBER:
                return item.getNumber();
            case MODEL:
                return item.getModel().getModelPK().getName();
            case NOTE:
                return item.getNote();
            case RECEIPT_DATE:
                return item.getReceiptDate();
            default:
                logger.warn("Попытка получить значение которое не существет из списка выдач. "
                        + "Строка: " + rowIndex + ", столбец: " + columnIndex);
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case VENDOR:
                return String.class;
            case NUMBER:
                return Integer.class;
            case MODEL:
                return String.class;
            case NOTE:
                return String.class;
            case RECEIPT_DATE:
                return Date.class;
            default:
                return Object.class;
        }
    }

    public Item getItem(int rowIndex) {
        return items.get(rowIndex);
    }
}
