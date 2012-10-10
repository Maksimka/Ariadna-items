package ru.tomsk.ariadna.items.delivery.item;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.data.Delivery;
import ru.tomsk.ariadna.items.data.Item;
import ru.tomsk.ariadna.items.data.ItemReturn;

/**
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class ItemDeliveryTableModel extends AbstractTableModel {

    private static final Logger logger = LoggerFactory.getLogger(ItemDeliveryTableModel.class);

    public static final int NUMBER = 0;

    public static final int TYPE = 1;

    public static final int MODEL = 2;

    public static final int RETURN_DATE = 3;

    private List<Delivery> deliveries;

    public ItemDeliveryTableModel(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public int getRowCount() {
        if (deliveries == null) {
            return 0;
        } else {
            return deliveries.size();
        }
    }

    /**
     * Возвращяет общие количество свойств выдачи.
     *
     * @return общие количество свойств выдачи
     */
    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == NUMBER) {
            return "№";
        } else if (columnIndex == TYPE) {
            return "Тип";
        } else if (columnIndex == MODEL) {
            return "Модель";
        } else if (columnIndex == RETURN_DATE) {
            return "Возврат";
        } else {
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
        Delivery delivery = deliveries.get(rowIndex);
        Item item = delivery.getItem();
        if (columnIndex == NUMBER) {
            return item.getNumber();
        } else if (columnIndex == TYPE) {
            return item.getModel().getModelPK().getType();
        } else if (columnIndex == MODEL) {
            String modelName = item.getModel().getModelPK().getName();
            String vendorName = item.getModel().getModelPK().getVendor();
            StringBuilder fullModelName = new StringBuilder();
            fullModelName.append(modelName);
            fullModelName.append(" (").append(vendorName).append(')');
            return fullModelName.toString();
        } else if (columnIndex == RETURN_DATE) {
            ItemReturn itemReturn = delivery.getItemReturn();
            if (itemReturn == null) {
                return null;
            } else {
                return itemReturn.getReturnDate();
            }
        } else {
            logger.warn("Попытка получить значение которое не существет из списка выдач. "
                    + "Строка: " + rowIndex + ", столбец: " + columnIndex);
            return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == NUMBER) {
            return Integer.class;
        } else if (columnIndex == TYPE) {
            return String.class;
        } else if (columnIndex == MODEL) {
            return String.class;
        } else if (columnIndex == RETURN_DATE) {
            return Date.class;
        } else {
            return Object.class;
        }
    }
}
