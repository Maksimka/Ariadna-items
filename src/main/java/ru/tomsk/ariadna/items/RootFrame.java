package ru.tomsk.ariadna.items;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.delivery.DeliveriesPanel;
import ru.tomsk.ariadna.items.item.ItemPanel;

/**
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
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

    private final DeliveriesPanel deliveriesPanel;

    private final ItemPanel itemsPanel;

    public RootFrame() {
        setTitle(Main.TITLE);
        loadPreferences();
        deliveriesPanel = new DeliveriesPanel();
        itemsPanel = new ItemPanel();
        add(createVerticalSplitPane(), BorderLayout.CENTER);
        addWindowListener(getClosing());
    }

    private JSplitPane createVerticalSplitPane() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(deliveriesPanel);
        splitPane.setBottomComponent(itemsPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0.6);
        splitPane.setResizeWeight(0.6);
        return splitPane;
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
