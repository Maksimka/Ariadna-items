package ru.tomsk.ariadna.items.delivery.item;

import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.TableDateRender;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 * Панель содержащая таблицу снаряжения выдачи.
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class ItemDeliveryTable extends JTable {

    private static final Logger logger = LoggerFactory.getLogger(ItemDeliveryTable.class);

    private ItemDeliveryTableTransferHandler transferHandler;

    public ItemDeliveryTable() {
        super(new ItemDeliveryTableModel());
        setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
        setDropMode(DropMode.INSERT_ROWS);
        makeSettingColumn();
        transferHandler = new ItemDeliveryTableTransferHandler();
        setTransferHandler(transferHandler);
    }

    @Override
    public ItemDeliveryTableModel getModel() {
        return (ItemDeliveryTableModel) super.getModel();
    }

    public void setDeliveryPacket(DeliveryPacket deliveryPacket) {
        transferHandler.setDeliveryPacket(deliveryPacket);
    }

    private void makeSettingColumn() {
        TableColumnModel model = getColumnModel();
        TableColumn number = model.getColumn(ItemDeliveryTableModel.NUMBER);
        number.setMinWidth(30);
        number.setMaxWidth(70);
        number.setPreferredWidth(30);

        TableColumn type = model.getColumn(ItemDeliveryTableModel.TYPE);
        type.setMinWidth(100);
        type.setMaxWidth(180);
        type.setPreferredWidth(130);

        TableColumn modelName = model.getColumn(ItemDeliveryTableModel.MODEL);
        modelName.setMinWidth(200);
        modelName.setPreferredWidth(290);

        TableColumn receiptDate = model.getColumn(ItemDeliveryTableModel.RETURN_DATE);
        receiptDate.setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
        receiptDate.setMinWidth(80);
        receiptDate.setMaxWidth(100);
        receiptDate.setPreferredWidth(90);
    }
}
