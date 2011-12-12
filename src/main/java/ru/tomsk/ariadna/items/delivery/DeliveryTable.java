package ru.tomsk.ariadna.items.delivery;

import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.PersistenceUtil;
import ru.tomsk.ariadna.items.TableDateRender;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 * Панель содержащая таблицу пакета выдач.
 * @author Шаймарданов Максим Маратович
 */
public class DeliveryTable extends JTable {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryTable.class);

    public DeliveryTable() {
        super();
        init();
    }

    private void init() {
        setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
        DeliveryTableModel tableModel = getDeliveryPacketTableModel();
        setModel(tableModel);
        TableRowSorter<DeliveryTableModel> sorter =
                new TableRowSorter<DeliveryTableModel>(tableModel);
        setRowSorter(sorter);
        initRaser();
        addMouseListener(new DeliveryTableMouseListener());
        getColumn("Дата выдачи").
                setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
        getColumn("Дата возврата").
                setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
    }

    @Override
    public TableRowSorter<DeliveryTableModel> getRowSorter() {
        return (TableRowSorter<DeliveryTableModel>) super.getRowSorter();
    }

    private DeliveryTableModel getDeliveryPacketTableModel() {
        DeliveryTableModel tableModel = new DeliveryTableModel();
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<DeliveryPacket> packets = entityManager.createQuery(
                "SELECT dp FROM DeliveryPacket dp ORDER BY dp.deliveryDate DESC").getResultList();
        tableModel.setDelivertPackets(packets);
        return tableModel;
    }

    private void initRaser() {
        TableColumn idColumn =
                getColumnModel().getColumn(DeliveryTableModel.DELIVERY_PACKET_ID);
        idColumn.setMinWidth(30);
        idColumn.setMaxWidth(70);
        idColumn.setPreferredWidth(40);
        TableColumn nameColumn =
                getColumnModel().getColumn(DeliveryTableModel.MEMBER_NAME);
        nameColumn.setMinWidth(200);
        nameColumn.setPreferredWidth(250);
        TableColumn aliasColumn =
                getColumnModel().getColumn(DeliveryTableModel.EVENT);
        aliasColumn.setMinWidth(150);
        aliasColumn.setPreferredWidth(250);
        TableColumn enrolmentYearColumn =
                getColumnModel().getColumn(DeliveryTableModel.DELIVERY_DATE);
        enrolmentYearColumn.setMinWidth(100);
        enrolmentYearColumn.setMaxWidth(140);
        enrolmentYearColumn.setPreferredWidth(110);
        TableColumn birthdayColumn =
                getColumnModel().getColumn(DeliveryTableModel.EXPECTED_RETURN_DATE);
        birthdayColumn.setMinWidth(110);
        birthdayColumn.setMaxWidth(140);
        birthdayColumn.setPreferredWidth(120);
    }
}
