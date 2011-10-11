package ru.tomsk.ariadna.items;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.regex.PatternSyntaxException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Шаймарданов Максим Маратович
 */
public class RootFrame extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(RootFrame.class);

    private static final String PREFERENCES_X_CORDINATE = "x-coordinate";

    private static final String PREFERENCES_Y_CORDINATE = "y-coordinate";

    private static final String PREFERENCES_WIDNOW_WIDTH = "width";

    private static final String PREFERENCES_HEIGHT_WIDTH = "height";

    /**
     * Ширина окна по умолчаню.
     */
    private static final int DEFAULT_WIDTH = 800;

    /**
     * Высота окна по умолчанию.
     */
    private static final int DEFAULT_HEIGHT = 600;

    private final DeliveryTable deliveryPacketTable;

    public RootFrame() {
        setTitle(Main.TITLE);
        loadPreferences();
        add(createToolBar(), BorderLayout.NORTH);
        JPanel body = new JPanel(new BorderLayout());
        deliveryPacketTable = new DeliveryTable();
        body.add(new JScrollPane(deliveryPacketTable), BorderLayout.CENTER);
        add(body, BorderLayout.CENTER);
        addWindowListener(getClosing());
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
        JButton create = new JButton(new AbstractAction("Выдать снаряжение") {

            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDeliveryDialog packetDialog = new CreateDeliveryDialog();
                packetDialog.setVisible(true);
            }
        });
        tools.add(create);
        JButton delete = new JButton(new AbstractAction("Принять снаряжение") {

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

    private void loadPreferences() {
        Preferences preferences = Preferences.userNodeForPackage(RootFrame.class);
        int width = preferences.getInt(PREFERENCES_WIDNOW_WIDTH, DEFAULT_WIDTH);
        int height = preferences.getInt(PREFERENCES_HEIGHT_WIDTH, DEFAULT_HEIGHT);
        setSize(width, height);
        int x = preferences.getInt(PREFERENCES_X_CORDINATE, 0);
        int y = preferences.getInt(PREFERENCES_Y_CORDINATE, 0);
        if (x == 0 && y == 0) {
            setLocationRelativeTo(null); //Установить окно по центру
        } else {
            setLocation(x, y);
        }
    }

    private void savePreferences() {
        Preferences preferences = Preferences.userNodeForPackage(RootFrame.class);
        preferences.putInt(PREFERENCES_X_CORDINATE, getX());
        preferences.putInt(PREFERENCES_Y_CORDINATE, getY());
        preferences.putInt(PREFERENCES_WIDNOW_WIDTH, getWidth());
        preferences.putInt(PREFERENCES_HEIGHT_WIDTH, getHeight());
        try {
            preferences.flush();
        } catch (BackingStoreException e) {
            logger.warn("Неудалось сохранить параметры окна", e);
        }
    }

    private WindowAdapter getClosing() {
        return new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                savePreferences();
            }
        };
    }
}
