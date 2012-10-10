package ru.tomsk.ariadna.items.delivery.packet;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.delivery.item.ItemDeliveryTable;

/**
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class DeliveryPacketPanel extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryPacketPanel.class);

    private final DeliveryPackeTable deliveryPacketTable;

    public DeliveryPacketPanel(ItemDeliveryTable itemDeliveryTable) {
        this(new BorderLayout(), itemDeliveryTable);
    }

    public DeliveryPacketPanel(LayoutManager layout, ItemDeliveryTable itemDeliveryTable) {
        super(layout);
        deliveryPacketTable = new DeliveryPackeTable(itemDeliveryTable);
        add(createToolBar(), BorderLayout.NORTH);
        add(new JScrollPane(deliveryPacketTable), BorderLayout.CENTER);
    }

    private JToolBar createToolBar() {
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        addActionToToolBar(tools);
        tools.addSeparator();
        addFilterToToolBar(tools);
        return tools;
    }

    private void addFilterToToolBar(JToolBar tools) {
        tools.add(new JLabel("Поиск: "));
        final JTextField textField = new JTextField(16);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                //Быстрй поиск
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    RowFilter<DeliveryPacketTableModel, Integer> filter =
                            getFilter(textField.getText().trim());
                    deliveryPacketTable.getRowSorter().setRowFilter(filter);
                } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    textField.setText(null);
                    deliveryPacketTable.getRowSorter().setRowFilter(null);
                }
            }
        });
        tools.add(textField);
        JButton filterButton = new JButton("Найти");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                RowFilter<DeliveryPacketTableModel, Integer> filter =
                        getFilter(textField.getText().trim());
                deliveryPacketTable.getRowSorter().setRowFilter(filter);
            }
        });
        tools.add(filterButton);
    }

    private RowFilter<DeliveryPacketTableModel, Integer> getFilter(String filterText) {
        RowFilter<DeliveryPacketTableModel, Integer> filter = null;
        if (filterText.isEmpty() == false) {
            try {
                filter = RowFilter.regexFilter(filterText);
            } catch (PatternSyntaxException e) {
                logger.warn("Не корректная строка \"" + filterText + "\" для поиска");
            }
        }
        return filter;
    }

    private void addActionToToolBar(JToolBar tools) {
        JButton create = new JButton(new AbstractAction("Выдать") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreatePackeDeliveryDialog packetDialog = new CreatePackeDeliveryDialog();
                packetDialog.setVisible(true);
            }
        });
        tools.add(create);
        JButton delete = new JButton(new AbstractAction("Принять") {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        tools.add(delete);
        JButton change = new JButton(new AbstractAction("Изменить") {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        tools.add(change);
    }
}
