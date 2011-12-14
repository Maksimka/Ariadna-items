package ru.tomsk.ariadna.items.delivery;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class DeliveryTabPanel extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryTabPanel.class);

    private final DeliveryTable deliveryPacketTable;

    public DeliveryTabPanel() {
        this(new BorderLayout());
    }

    public DeliveryTabPanel(LayoutManager layout) {
        super(layout);
        deliveryPacketTable = new DeliveryTable();
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
                    RowFilter<DeliveryTableModel, Integer> filter =
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
                RowFilter<DeliveryTableModel, Integer> filter =
                        getFilter(textField.getText().trim());
                deliveryPacketTable.getRowSorter().setRowFilter(filter);
            }
        });
        tools.add(filterButton);
    }

    private RowFilter<DeliveryTableModel, Integer> getFilter(String filterText) {
        RowFilter<DeliveryTableModel, Integer> filter = null;
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
                CreateDeliveryDialog packetDialog = new CreateDeliveryDialog();
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
