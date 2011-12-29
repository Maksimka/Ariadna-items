package ru.tomsk.ariadna.items.item;

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
import ru.tomsk.ariadna.items.data.Item;

/**
 * Окно создания выдачи.
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class CreateItemDialog extends JDialog {

    private static final Logger logger = LoggerFactory.getLogger(CreateItemDialog.class);

    private final ItemForm form;

    private final JPanel buttons;

    private final static String NEW_ITEM = "Новое снаряжение";

    private final static String SEPARATOR = " - ";

    private final static String DIALOG_TITLE = "Редактор";

    private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);

    public CreateItemDialog() {
        this(new Item());
    }

    public CreateItemDialog(Item item) {
        super();
        if (item.getId() == null) {
            setTitle(NEW_ITEM + SEPARATOR + DIALOG_TITLE);
        } else {
            setTitle(item.getModel() + SEPARATOR + DIALOG_TITLE);
        }
        form = new ItemForm(item);
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
                //Сохранить снаряжение
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
