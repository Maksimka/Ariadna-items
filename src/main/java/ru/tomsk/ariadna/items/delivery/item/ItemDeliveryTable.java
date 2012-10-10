package ru.tomsk.ariadna.items.delivery.item;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.TableDateRender;
import ru.tomsk.ariadna.items.delivery.packet.DeliveryPacketTableModel;

/**
 * Панель содержащая таблицу снаряжения.
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximaxsh@gmail.com>
 */
public class ItemDeliveryTable extends JTable {

    private static final Logger logger = LoggerFactory.getLogger(ItemDeliveryTable.class);

    public ItemDeliveryTable() {
        super();
        setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
    }

    public void setModel(ItemDeliveryTableModel dataModel) {
        super.setModel(dataModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dataModel);
        setRowSorter(sorter);
        getColumn("Возврат").setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
        initRaser();
    }

    @Override
    public TableRowSorter<DeliveryPacketTableModel> getRowSorter() {
        return (TableRowSorter<DeliveryPacketTableModel>) super.getRowSorter();
    }

    private void initRaser() {
        TableColumn number = getColumnModel().getColumn(ItemDeliveryTableModel.NUMBER);
        number.setMinWidth(30);
        number.setMaxWidth(70);
        number.setPreferredWidth(30);

        TableColumn type = getColumnModel().getColumn(ItemDeliveryTableModel.TYPE);
        type.setMinWidth(100);
        type.setMaxWidth(180);
        type.setPreferredWidth(130);

        TableColumn modelName = getColumnModel().getColumn(ItemDeliveryTableModel.MODEL);
        modelName.setMinWidth(200);
        modelName.setPreferredWidth(290);

        TableColumn receiptDate = getColumnModel().getColumn(ItemDeliveryTableModel.RETURN_DATE);
        receiptDate.setMinWidth(100);
        receiptDate.setMaxWidth(130);
        receiptDate.setPreferredWidth(100);
    }
}
