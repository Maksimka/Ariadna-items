package ru.tomsk.ariadna.items.delivery;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.delivery.item.ItemDeliveryTable;
import ru.tomsk.ariadna.items.delivery.packet.PacketPanel;

/**
 * Панел выдачи.
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class DeliveriesPanel extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(DeliveriesPanel.class);

    /**
     * Выдачи.
     */
    private final PacketPanel deliveryPacketPanel;

    /**
     * Список снаряжения для выдачи.
     */
    private final ItemDeliveryTable itemDeliveryTable;

    public DeliveriesPanel() {
        this(new BorderLayout());
    }

    public DeliveriesPanel(LayoutManager layout) {
        super(layout);
        itemDeliveryTable = new ItemDeliveryTable();
        deliveryPacketPanel = new PacketPanel(itemDeliveryTable);
        add(createHorizontalSplitPane(), BorderLayout.CENTER);
    }

    private JSplitPane createHorizontalSplitPane() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(0.75);
        splitPane.setResizeWeight(0.75);
        splitPane.setLeftComponent(deliveryPacketPanel);
        splitPane.setRightComponent(new JScrollPane(itemDeliveryTable));
        return splitPane;
    }
}
