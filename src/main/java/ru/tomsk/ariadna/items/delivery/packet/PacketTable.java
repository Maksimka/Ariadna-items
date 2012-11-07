package ru.tomsk.ariadna.items.delivery.packet;

import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.PersistenceUtil;
import ru.tomsk.ariadna.items.TableDateRender;
import ru.tomsk.ariadna.items.data.Delivery;
import ru.tomsk.ariadna.items.data.DeliveryPacket;
import ru.tomsk.ariadna.items.delivery.item.ItemDeliveryTable;
import ru.tomsk.ariadna.items.delivery.item.ItemDeliveryTableModel;

/**
 * Панель содержащая таблицу пакета выдач.
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class PacketTable extends JTable {

    private static final Logger logger = LoggerFactory.getLogger(PacketTable.class);

    public PacketTable(final ItemDeliveryTable itemDeliveryTable) {
        super();
        setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = getSelectedRow();
                if (selectedRow >= 0) {
                    PacketTableModel model = (PacketTableModel) getModel();
                    DeliveryPacket deliveryPacket = model.getDeliveryPacket(selectedRow);
                    List<Delivery> deliveries = deliveryPacket.getItemDeliveries();
                    itemDeliveryTable.setDeliveryPacket(deliveryPacket);
                    itemDeliveryTable.getModel().setDeliveries(deliveries);
                } else {
                    //Элемент не выбран
                }
            }
        });
        setModel(getDeliveryPacketTableModel());
        makeSettingColumn();
        addMouseListener(new PacketTableMouseListener());
    }

    @Override
    public TableRowSorter<PacketTableModel> getRowSorter() {
        return (TableRowSorter<PacketTableModel>) super.getRowSorter();
    }

    private PacketTableModel getDeliveryPacketTableModel() {
        PacketTableModel tableModel = new PacketTableModel();
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<DeliveryPacket> packets = entityManager.createQuery(
                "SELECT dp FROM DeliveryPacket dp ORDER BY dp.deliveryDate DESC").
                getResultList();
        tableModel.setDeliveryPackets(packets);
        return tableModel;
    }

    private void makeSettingColumn() {
        TableColumnModel model = getColumnModel();
        TableColumn id = model.getColumn(PacketTableModel.DELIVERY_PACKET_ID);
        id.setMinWidth(40);
        id.setMaxWidth(70);
        id.setPreferredWidth(40);

        TableColumn name = model.getColumn(PacketTableModel.MEMBER_NAME);
        name.setMinWidth(200);
        name.setPreferredWidth(250);

        TableColumn alias = model.getColumn(PacketTableModel.EVENT);
        alias.setMinWidth(150);
        alias.setPreferredWidth(250);

        TableColumn deliveryDate = model.getColumn(PacketTableModel.DELIVERY_DATE);
        deliveryDate.setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
        deliveryDate.setMinWidth(80);
        deliveryDate.setMaxWidth(100);
        deliveryDate.setPreferredWidth(90);

        TableColumn expectedReturnDate = model.getColumn(
                PacketTableModel.EXPECTED_RETURN_DATE);
        expectedReturnDate.setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
        expectedReturnDate.setMinWidth(80);
        expectedReturnDate.setMaxWidth(100);
        expectedReturnDate.setPreferredWidth(90);
    }
}
