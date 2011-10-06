package ru.tomsk.ariadna.items;

import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 * Панель содержащая таблицу пакета выдач.
 * @author Шаймарданов Максим Маратович
 */
public class DeliveryPacketTable extends JTable {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryPacketTable.class);

    public DeliveryPacketTable() {
        super();
        init();
    }

    private void init() {
        setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
        DeliveryPacketTableModel deliveryPacket = getDeliveryPacketTableModel();
        setModel(deliveryPacket);
        TableRowSorter<DeliveryPacketTableModel> sorter = new TableRowSorter<DeliveryPacketTableModel>(deliveryPacket);
        setRowSorter(sorter);
        initRaser();
        //addMouseListener(new MemberMouseListener());
        getColumn("Дата выдачи").setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
        getColumn("Дата возврата").setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
    }

    @Override
    public TableRowSorter<DeliveryPacketTableModel> getRowSorter() {
        return (TableRowSorter<DeliveryPacketTableModel>) super.getRowSorter();
    }

    private DeliveryPacketTableModel getDeliveryPacketTableModel() {
        DeliveryPacketTableModel tableModel = new DeliveryPacketTableModel();
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<DeliveryPacket> packets = entityManager.createQuery(
                "SELECT dp FROM DeliveryPacket dp ORDER BY dp.deliveryDate DESC").getResultList();
        tableModel.setDelivertPackets(packets);
        return tableModel;
    }

    private void initRaser() {
        TableColumn idColumn =
                getColumnModel().getColumn(DeliveryPacketTableModel.DELIVERY_PACKET_ID);
        idColumn.setMinWidth(30);
        idColumn.setMaxWidth(70);
        idColumn.setPreferredWidth(40);
        TableColumn nameColumn =
                getColumnModel().getColumn(DeliveryPacketTableModel.MEMBER_NAME);
        nameColumn.setMinWidth(200);
        nameColumn.setPreferredWidth(250);
        TableColumn aliasColumn =
                getColumnModel().getColumn(DeliveryPacketTableModel.EVENT);
        aliasColumn.setMinWidth(150);
        aliasColumn.setPreferredWidth(250);
        TableColumn enrolmentYearColumn =
                getColumnModel().getColumn(DeliveryPacketTableModel.DELIVERY_DATE);
        enrolmentYearColumn.setMinWidth(100);
        enrolmentYearColumn.setMaxWidth(140);
        enrolmentYearColumn.setPreferredWidth(110);
        TableColumn birthdayColumn =
                getColumnModel().getColumn(DeliveryPacketTableModel.EXPECTED_RETURN_DATE);
        birthdayColumn.setMinWidth(110);
        birthdayColumn.setMaxWidth(140);
        birthdayColumn.setPreferredWidth(120);
    }
}
