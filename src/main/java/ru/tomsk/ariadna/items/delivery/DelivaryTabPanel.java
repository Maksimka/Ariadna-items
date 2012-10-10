package ru.tomsk.ariadna.items.delivery;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.delivery.item.ItemDeliveryTable;
import ru.tomsk.ariadna.items.delivery.packet.DeliveryPacketPanel;

/**
 *
 * @author Maksim
 */
public class DelivaryTabPanel extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(DelivaryTabPanel.class);

    private final ItemDeliveryTable itemDeliveryTable;

    private final DeliveryPacketPanel deliveryPacketPanel;

    public DelivaryTabPanel() {
        this(new BorderLayout());
    }

    public DelivaryTabPanel(LayoutManager layout) {
        super(layout);
        itemDeliveryTable = new ItemDeliveryTable();
        deliveryPacketPanel = new DeliveryPacketPanel(itemDeliveryTable);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(0.75);
        splitPane.setResizeWeight(0.75);
        splitPane.setLeftComponent(deliveryPacketPanel);
        splitPane.setRightComponent(new JScrollPane(itemDeliveryTable));
        add(splitPane, BorderLayout.CENTER);
    }
}
