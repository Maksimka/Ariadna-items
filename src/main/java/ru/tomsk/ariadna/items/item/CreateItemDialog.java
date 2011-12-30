package ru.tomsk.ariadna.items.item;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
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

    private final JComponent buttons;

    private final static String NEW_ITEM = "Новое снаряжение";

    private final static String SEPARATOR = " - ";

    private final static String DIALOG_TITLE = "Редактор";

    private boolean isSetSize;

    private final int minimumWidth;

    private final int minimumHeight;

    private final int preferredWidth;

    private final int preferredHeight;

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

        isSetSize = false;
        Dimension formMinimumSize = form.getMinimumSize();
        Dimension buttonsMinimumSize = buttons.getMaximumSize();
        minimumWidth = formMinimumSize.width;
        minimumHeight = formMinimumSize.height + buttonsMinimumSize.height;

        Dimension formPreferredSize = form.getPreferredSize();
        Dimension buttonsPreferredSize = buttons.getPreferredSize();
        preferredWidth = formPreferredSize.width;
        preferredHeight = formPreferredSize.height + buttonsPreferredSize.height;

    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b && !isSetSize) {
            Insets insets = getParent().getInsets();
            int dialogMinimumWidth = minimumWidth + insets.left + insets.right;
            int dialogMinimumHeight = minimumHeight + insets.bottom + insets.top;
            setMinimumSize(new Dimension(dialogMinimumWidth, dialogMinimumHeight));

            int dialogPreferredWidth = preferredWidth + insets.left + insets.right;
            int dialogPreferredHeight = preferredHeight + insets.bottom + insets.top;
            setSize(dialogPreferredWidth, dialogPreferredHeight);

            //Установить окно по центру
            setLocationRelativeTo(null);
            isSetSize = true;
        }
    }

    private JComponent createButtons() {
        Box buttonPanel = new Box(BoxLayout.X_AXIS);
        buttonPanel.add(Box.createHorizontalGlue());
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
