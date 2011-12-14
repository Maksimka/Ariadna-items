package ru.tomsk.ariadna.items.item;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.data.Item;

/**
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class ItemTableModel extends AbstractTableModel {

    private static final Logger logger = LoggerFactory.getLogger(ItemTableModel.class);

    public static final int TYPE = 1;

    public static final int NUMBER = 0;

    public static final int MODEL = 2;

    public static final int NOTE = 3;

    public static final int RECEIPT_DATE = 4;

    private List<Item> items;

    public ItemTableModel(List<Item> items) {
        this.items = items;
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
        if (columnIndex == TYPE) {
            return "Тип";
        } else if (columnIndex == NUMBER) {
            return "№";
        } else if (columnIndex == MODEL) {
            return "Модель";
        } else if (columnIndex == NOTE) {
            return "Примечание";
        } else if (columnIndex == RECEIPT_DATE) {
            return "Добавлено";
        }else {
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
        if (columnIndex == TYPE) {
            return item.getModel().getModelPK().getType();
        } else if (columnIndex == NUMBER) {
            return item.getNumber();
        } else if (columnIndex == MODEL) {
            return item.getModel().toString();
        } else if (columnIndex == NOTE) {
            return item.getNote();
        } else if (columnIndex == RECEIPT_DATE) {
            return item.getReceiptDate();
        } else {
            logger.warn("Попытка получить значение которое не существет из списка выдач. "
                    + "Строка: " + rowIndex + ", столбец: " + columnIndex);
            return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == TYPE) {
            return String.class;
        } else if (columnIndex == NUMBER) {
            return Integer.class;
        } else if (columnIndex == MODEL) {
            return String.class;
        } else if (columnIndex == NOTE) {
            return String.class;
        } else if (columnIndex == RECEIPT_DATE) {
            return Date.class;
        } else {
            return Object.class;
        }
    }
}
