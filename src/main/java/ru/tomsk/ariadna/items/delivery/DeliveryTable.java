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
 * 
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
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
        getColumn("Выдано").
                setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
        getColumn("Возвратить").
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
        TableColumn id =
                getColumnModel().getColumn(DeliveryTableModel.DELIVERY_PACKET_ID);
        id.setMinWidth(40);
        id.setMaxWidth(70);
        id.setPreferredWidth(40);
        
        TableColumn name =
                getColumnModel().getColumn(DeliveryTableModel.MEMBER_NAME);
        name.setMinWidth(200);
        name.setPreferredWidth(250);
        
        TableColumn alias =
                getColumnModel().getColumn(DeliveryTableModel.EVENT);
        alias.setMinWidth(150);
        alias.setPreferredWidth(250);
        
        TableColumn enrolmentYear =
                getColumnModel().getColumn(DeliveryTableModel.DELIVERY_DATE);
        enrolmentYear.setMinWidth(100);
        enrolmentYear.setMaxWidth(130);
        enrolmentYear.setPreferredWidth(100);
        
        TableColumn birthday =
                getColumnModel().getColumn(DeliveryTableModel.EXPECTED_RETURN_DATE);
        birthday.setMinWidth(100);
        birthday.setMaxWidth(130);
        birthday.setPreferredWidth(100);
    }
}
