package ru.tomsk.ariadna.items;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        final JFrame rootFrame = new JFrame();
        rootFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rootFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                rootFrame.setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        rootFrame.setVisible(true);
    }
}
