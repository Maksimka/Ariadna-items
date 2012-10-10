package ru.tomsk.ariadna.items.delivery.packet;

import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
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
public class DeliveryPackeTable extends JTable {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryPackeTable.class);

    public DeliveryPackeTable(final ItemDeliveryTable itemDeliveryTable) {
        super();
        setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = getSelectedRow();
                if (selectedRow >= 0) {
                    DeliveryPacketTableModel model = (DeliveryPacketTableModel) getModel();
                    DeliveryPacket deliveryPacket = model.getDeliveryPacket(selectedRow);
                    List<Delivery> deliveries = deliveryPacket.getItemDeliveries();
                    itemDeliveryTable.setModel(new ItemDeliveryTableModel(deliveries));
                } else {
                    //Элемент не выбран
                }
            }
        });
        DeliveryPacketTableModel tableModel = getDeliveryPacketTableModel();
        setModel(tableModel);
        TableRowSorter<DeliveryPacketTableModel> sorter =
                new TableRowSorter<DeliveryPacketTableModel>(tableModel);
        setRowSorter(sorter);
        initRaser();
        addMouseListener(new DeliveryPacketTableMouseListener());
        getColumn("Выдано").
                setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
        getColumn("Возвратить").
                setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
    }

    private void init() {
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
        TableColumn id =
                getColumnModel().getColumn(DeliveryPacketTableModel.DELIVERY_PACKET_ID);
        id.setMinWidth(40);
        id.setMaxWidth(70);
        id.setPreferredWidth(40);

        TableColumn name =
                getColumnModel().getColumn(DeliveryPacketTableModel.MEMBER_NAME);
        name.setMinWidth(200);
        name.setPreferredWidth(250);

        TableColumn alias =
                getColumnModel().getColumn(DeliveryPacketTableModel.EVENT);
        alias.setMinWidth(150);
        alias.setPreferredWidth(250);

        TableColumn enrolmentYear =
                getColumnModel().getColumn(DeliveryPacketTableModel.DELIVERY_DATE);
        enrolmentYear.setMinWidth(100);
        enrolmentYear.setMaxWidth(130);
        enrolmentYear.setPreferredWidth(100);

        TableColumn birthday =
                getColumnModel().getColumn(DeliveryPacketTableModel.EXPECTED_RETURN_DATE);
        birthday.setMinWidth(100);
        birthday.setMaxWidth(130);
        birthday.setPreferredWidth(100);
    }
}
