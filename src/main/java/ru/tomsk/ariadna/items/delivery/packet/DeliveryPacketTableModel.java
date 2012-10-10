package ru.tomsk.ariadna.items.delivery.packet;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class DeliveryPacketTableModel extends AbstractTableModel {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryPacketTableModel.class);

    public static final int DELIVERY_PACKET_ID = 0;

    public static final int MEMBER_NAME = 3;

    public static final int EVENT = 4;

    public static final int DELIVERY_DATE = 1;

    public static final int EXPECTED_RETURN_DATE = 2;

    private List<DeliveryPacket> delivertPackets;

    public void setDelivertPackets(List<DeliveryPacket> delivertPackets) {
        this.delivertPackets = delivertPackets;
    }

    @Override
    public int getRowCount() {
        if (delivertPackets == null) {
            return 0;
        } else {
            return delivertPackets.size();
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
        if (columnIndex == DELIVERY_PACKET_ID) {
            return "№";
        } else if (columnIndex == MEMBER_NAME) {
            return "Кому выдано";
        } else if (columnIndex == EVENT) {
            return "Мероприятие";
        } else if (columnIndex == DELIVERY_DATE) {
            return "Выдано";
        } else if (columnIndex == EXPECTED_RETURN_DATE) {
            return "Возвратить";
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
        DeliveryPacket packet = delivertPackets.get(rowIndex);
        if (columnIndex == DELIVERY_PACKET_ID) {
            return packet.getDeliveryPacketId();
        } else if (columnIndex == MEMBER_NAME) {
            return packet.getMember().getFullName();
        } else if (columnIndex == EVENT) {
            return packet.getEvent();
        } else if (columnIndex == DELIVERY_DATE) {
            return packet.getDeliveryDate();
        } else if (columnIndex == EXPECTED_RETURN_DATE) {
            return packet.getExpectedReturnDate();
        } else {
            logger.warn("Попытка получить значение которое не существет из списка выдач. "
                    + "Строка: " + rowIndex + ", столбец: " + columnIndex);
            return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == DELIVERY_PACKET_ID) {
            return Integer.class;
        } else if (columnIndex == MEMBER_NAME) {
            return String.class;
        } else if (columnIndex == EVENT) {
            return String.class;
        } else if (columnIndex == DELIVERY_DATE) {
            return Date.class;
        } else if (columnIndex == EXPECTED_RETURN_DATE) {
            return Date.class;
        } else {
            return Object.class;
        }
    }

    public DeliveryPacket getDeliveryPacket(int rowIndex) {
        return delivertPackets.get(rowIndex);
    }
}
