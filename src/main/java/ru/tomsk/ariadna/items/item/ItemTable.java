package ru.tomsk.ariadna.items.item;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.TableDateRender;
import ru.tomsk.ariadna.items.data.Item;

/**
 * Панель содержащая таблицу снаряжения.
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class ItemTable extends JTable {

    private static final Logger logger = LoggerFactory.getLogger(ItemTable.class);

    public ItemTable() {
        super(new ItemTableModel());
        makeSettingColumn();
        setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addMouseListener(new ItemTableMouseListener());
        setDragEnabled(true);
        setTransferHandler(new TransferHandler() {
            @Override
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY_OR_MOVE;
            }

            @Override
            protected Transferable createTransferable(JComponent component) {
                ItemTable table = (ItemTable) component;
                int[] selectedRows = table.getSelectedRows();
                ItemTableModel model = table.getModel();
                StringBuilder buffer = new StringBuilder();
                for (int i = 0; i < selectedRows.length; i++) {
                    Item item = model.getItem(selectedRows[i]);
                    buffer.append(item.getId());
                    if (i != selectedRows.length - 1) {
                        buffer.append("\n");
                    }
                }
                return new StringSelection(buffer.toString());
            }

            /**
             * Remove the items moved from the list.
             */
            @Override
            protected void exportDone(JComponent component, Transferable data, int action) {
                if (action == TransferHandler.MOVE) {
                    ItemTable table = (ItemTable) component;
                    int[] selectedRows = table.getSelectedRows();
                    for (int i = 0; i < selectedRows.length; i++) {
                        selectedRows[i] = convertRowIndexToModel(selectedRows[i]);
                    }
                    ItemTableModel model = table.getModel();
                    model.removeItems(selectedRows);
                }
            }
        });
    }

    @Override
    public ItemTableModel getModel() {
        return (ItemTableModel) super.getModel();
    }

    private void makeSettingColumn() {
        TableColumnModel model = getColumnModel();
        TableColumn number = model.getColumn(ItemTableModel.NUMBER);
        number.setMinWidth(30);
        number.setMaxWidth(70);
        number.setPreferredWidth(30);

        TableColumn type = model.getColumn(ItemTableModel.VENDOR);
        type.setMinWidth(100);
        type.setMaxWidth(180);
        type.setPreferredWidth(130);

        TableColumn modelName = model.getColumn(ItemTableModel.MODEL);
        modelName.setMinWidth(200);
        modelName.setMaxWidth(350);
        modelName.setPreferredWidth(290);

        TableColumn note = model.getColumn(ItemTableModel.NOTE);
        note.setMinWidth(110);
        note.setPreferredWidth(120);

        TableColumn receiptDate = model.getColumn(ItemTableModel.RECEIPT_DATE);
        receiptDate.setCellRenderer(new TableDateRender("yyyy-MM-dd", JLabel.CENTER));
        receiptDate.setMinWidth(80);
        receiptDate.setMaxWidth(100);
        receiptDate.setPreferredWidth(90);
    }
}
