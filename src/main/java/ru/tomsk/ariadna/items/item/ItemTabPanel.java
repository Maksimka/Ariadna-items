package ru.tomsk.ariadna.items.item;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class ItemTabPanel extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(ItemTabPanel.class);

    private final JComponent typeListPanel;

    private final ItemTable itemTable;

    public ItemTabPanel() {
        this(new BorderLayout());
    }

    public ItemTabPanel(LayoutManager layout) {
        super(layout);
        itemTable = new ItemTable();
        typeListPanel = new TypeListBox(itemTable);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                typeListPanel,
                new JScrollPane(itemTable));
        add(splitPane, BorderLayout.CENTER);
    }
}
