package ru.tomsk.ariadna.items;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.delivery.DeliveryTabPanel;
import ru.tomsk.ariadna.items.item.ItemTabPanel;

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

    public RootFrame() {
        setTitle(Main.TITLE);
        loadPreferences();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Выдачи", new DeliveryTabPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("Снаряжение", new ItemTabPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);

        add(tabbedPane, BorderLayout.CENTER);
        addWindowListener(getClosing());
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
