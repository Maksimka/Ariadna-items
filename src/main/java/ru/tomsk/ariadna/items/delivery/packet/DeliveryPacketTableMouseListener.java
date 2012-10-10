package ru.tomsk.ariadna.items.delivery.packet;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 * @see http://www.rgagnon.com/javadetails/java-0336.html
 * @see http://javaswing.wordpress.com/2009/09/16/jtable_right_button_selection/
 */
public class DeliveryPacketTableMouseListener extends MouseAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryPacketTableMouseListener.class);

    public DeliveryPacketTableMouseListener() {
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 2) {
            logger.debug("Двойной клик левой кнопкой мыши");
            DeliveryPackeTable deliveryPacketTable = (DeliveryPackeTable) event.getSource();
            int selectedRow = deliveryPacketTable.getSelectedRow();
            if (selectedRow >= 0) {
                DeliveryPacketTableModel model = (DeliveryPacketTableModel) deliveryPacketTable.getModel();
                DeliveryPacket deliveryPacket = model.getDeliveryPacket(selectedRow);
                logger.debug(deliveryPacket.toString());
                CreatePackeDeliveryDialog packetDialog = new CreatePackeDeliveryDialog(deliveryPacket);
                packetDialog.setVisible(true);
            } else {
                //Элемент не выбран
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON3 && event.getClickCount() == 1) {
            logger.debug("Нажатие правой кнопкой мыши");
            DeliveryPackeTable deliveryPacketTable = (DeliveryPackeTable) event.getSource();
            int selectedRow = selectRow(deliveryPacketTable, event.getPoint());
            DeliveryPacketTableModel model = (DeliveryPacketTableModel) deliveryPacketTable.getModel();
            if (selectedRow >= 0) {
                DeliveryPacket deliveryPacket = model.getDeliveryPacket(selectedRow);
                logger.debug(deliveryPacket.toString());
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
        int selectedColumn = table.columnAtPoint(point);
        table.setColumnSelectionInterval(selectedColumn, selectedColumn);
        int selectedRow = table.rowAtPoint(point);
        table.setRowSelectionInterval(selectedRow, selectedRow);
        return selectedRow;
    }
}
