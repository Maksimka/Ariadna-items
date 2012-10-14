package ru.tomsk.ariadna.items.item;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Панель общего списка снаряжения.
 * 
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class ItemsPanel extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(ItemsPanel.class);

    /**
     * Список типов снаряжения.
     */
    private final TypeList typesPanel;

    /**
     * Таблица экземпляров снаряжения.
     */
    private final ItemTable itemTable;

    public ItemsPanel() {
        this(new BorderLayout());
    }

    public ItemsPanel(LayoutManager layout) {
        super(layout);
        itemTable = new ItemTable();
        typesPanel = new TypeList(itemTable);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(220);
        splitPane.setLeftComponent(typesPanel);
        splitPane.setRightComponent(new JScrollPane(itemTable));
        add(splitPane, BorderLayout.CENTER);
    }
}
