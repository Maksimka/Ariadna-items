package ru.tomsk.ariadna.items.item;

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
import ru.tomsk.ariadna.items.data.Item;
import ru.tomsk.ariadna.items.delivery.DeliveryTableModel;

/**
 * Панель содержащая таблицу снаряжения.
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximaxsh@gmail.com>
 */
public class ItemTable extends JTable {

    private static final Logger logger = LoggerFactory.getLogger(ItemTable.class);

    public ItemTable() {
        super();
        init();
    }

    private void init() {
        setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
        ItemTableModel tableModel = getDeliveryPacketTableModel();
        setModel(tableModel);
        TableRowSorter<ItemTableModel> sorter =
                new TableRowSorter<ItemTableModel>(tableModel);
        setRowSorter(sorter);
        initRaser();
        getColumn("Добавлено").setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
    }

    @Override
    public TableRowSorter<DeliveryTableModel> getRowSorter() {
        return (TableRowSorter<DeliveryTableModel>) super.getRowSorter();
    }

    private ItemTableModel getDeliveryPacketTableModel() {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<Item> items = entityManager.createQuery(
                "SELECT i FROM Item i ORDER BY i.model.modelPK.type, i.number ").getResultList();
        return new ItemTableModel(items);
    }

    private void initRaser() {
        TableColumn number =
                getColumnModel().getColumn(ItemTableModel.NUMBER);
        number.setMinWidth(30);
        number.setMaxWidth(70);
        number.setPreferredWidth(30);

        TableColumn type =
                getColumnModel().getColumn(ItemTableModel.TYPE);
        type.setMinWidth(100);
        type.setMaxWidth(180);
        type.setPreferredWidth(130);

        TableColumn modelName =
                getColumnModel().getColumn(ItemTableModel.MODEL);
        modelName.setMinWidth(200);
        modelName.setMaxWidth(350);
        modelName.setPreferredWidth(290);

        TableColumn note =
                getColumnModel().getColumn(ItemTableModel.NOTE);
        note.setMinWidth(110);
        note.setPreferredWidth(120);

        TableColumn receiptDate =
                getColumnModel().getColumn(ItemTableModel.RECEIPT_DATE);
        receiptDate.setMinWidth(100);
        receiptDate.setMaxWidth(130);
        receiptDate.setPreferredWidth(100);
    }
}
