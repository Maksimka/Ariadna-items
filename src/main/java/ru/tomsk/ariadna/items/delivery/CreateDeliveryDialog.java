package ru.tomsk.ariadna.items.delivery;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 * Окно создания выдачи.
 * 
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class CreateDeliveryDialog extends JDialog {

    private static final Logger logger = LoggerFactory.getLogger(CreateDeliveryDialog.class);

    private final DeliveryForm form;

    private final JPanel buttons;

    private final static String NEW_PACKET = "Новая выдача";

    private final static String SEPARATOR = " - ";

    private final static String DIALOG_TITLE = "Редактор";

    private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);

    public CreateDeliveryDialog() {
        this(new DeliveryPacket());
    }

    public CreateDeliveryDialog(DeliveryPacket packet) {
        super();
        if (packet.getDeliveryPacketId() == null) {
            setTitle(NEW_PACKET + SEPARATOR + DIALOG_TITLE);
        } else {
            setTitle(packet.getEvent() + SEPARATOR + DIALOG_TITLE);
        }
        form = new DeliveryForm(packet);
        add(form, BorderLayout.CENTER);
        buttons = createButtons();
        add(buttons, BorderLayout.SOUTH);
        setSize(DEFAULT_SIZE);
        setMinimumSize(DEFAULT_SIZE);
        setLocationRelativeTo(null); //Установить окно по центру
    }

    private JPanel createButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton save = new JButton(new AbstractAction("Сохранить") {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Сохранить членка клуба
                setVisible(false);
            }
        });
        buttonPanel.add(save);
        JButton exit = new JButton(new AbstractAction("Закрыть") {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        buttonPanel.add(exit);
        return buttonPanel;
    }
}
