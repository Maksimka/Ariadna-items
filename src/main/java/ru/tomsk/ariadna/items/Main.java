package ru.tomsk.ariadna.items;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
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

    public static void main(String[] args) {
        logger.info("Старт \"" + TITLE + "\"");
        final JFrame rootFrame = new RootFrame();
        rootFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rootFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                logger.info("Закрыть \"" + TITLE + "\"");
                rootFrame.setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                PersistenceUtil.close();
                logger.info("Закрыто.");
                System.exit(0);
            }
        });
        rootFrame.setVisible(true);
    }
}
