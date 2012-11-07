package ru.tomsk.ariadna.items;

import com.jgoodies.looks.windows.WindowsLookAndFeel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author maksim
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Название пргораммы.
     */
    public static final String TITLE = "Кильдым";

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        logger.info("Откраваем «{}»", TITLE);
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
        final JFrame rootFrame = new RootFrame();
        rootFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rootFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logger.info("Закрываем «{}»", TITLE);
                rootFrame.setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                PersistenceUtil.close();
                logger.info("«{}» закрыт.", TITLE);
                System.exit(0);
            }
        });
        rootFrame.pack();
        rootFrame.setVisible(true);
    }
}
