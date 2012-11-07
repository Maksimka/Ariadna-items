package ru.tomsk.ariadna.items.delivery.item;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.action.GiveItemsAction;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class ItemDeliveryTableTransferHandler extends TransferHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(ItemDeliveryTableTransferHandler.class);

    private DeliveryPacket deliveryPacket;

    public DeliveryPacket getDeliveryPacket() {
        return deliveryPacket;
    }

    public void setDeliveryPacket(DeliveryPacket deliveryPacket) {
        this.deliveryPacket = deliveryPacket;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        logger.info("canImport");
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        if (support.isDrop()) {
            boolean copySupported = (MOVE & support.getSourceDropActions()) == MOVE;
            if (copySupported) {
                support.setDropAction(MOVE);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean importData(TransferSupport support) {
        logger.info("importData");
        if (!canImport(support)) {
            return false;
        }
        JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
        int row = dl.getRow();
        logger.info("Row number {}", row);
        try {
            String data = (String) support.getTransferable().
                    getTransferData(DataFlavor.stringFlavor);
            String[] itemsAsString = data.split("\n");
            int[] items = new int[itemsAsString.length];
            for (int i = 0; i < itemsAsString.length; i++) {
                items[i] = Integer.parseInt(itemsAsString[i]);
            }
            GiveItemsAction giveItemsAction = new GiveItemsAction(deliveryPacket.getId(), items);
            try {
                giveItemsAction.redo();
            } catch (RuntimeException ex) {
                logger.error("", ex);
            }
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
