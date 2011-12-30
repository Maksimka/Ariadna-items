package ru.tomsk.ariadna.items.item;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import ru.tomsk.ariadna.items.data.Item;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
public class ItemForm extends JPanel {

    private final Item item;

    private static final int STRUT = 10;

    public ItemForm(Item item) {
        super();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        this.item = item;

        Box mainInformation = new Box(BoxLayout.X_AXIS);
        add(Box.createVerticalStrut(5));
        add(mainInformation);
        add(Box.createVerticalStrut(5));

        mainInformation.add(Box.createHorizontalStrut(STRUT));
        JLabel numberLabel = new JLabel("№");
        JSpinner numberInput = new JSpinner();
        int minimumHeight = numberInput.getMinimumSize().height;
        int preferredHeight = numberInput.getPreferredSize().height;
        numberInput.setMinimumSize(new Dimension(30, minimumHeight));
        numberInput.setPreferredSize(new Dimension(40, preferredHeight));
        mainInformation.add(createColumn(numberLabel, numberInput));

        mainInformation.add(Box.createHorizontalStrut(STRUT));
        JLabel typeLabel = new JLabel("Тип");
        JComboBox typeInput = new JComboBox();
        mainInformation.add(createColumn(typeLabel, typeInput));

        mainInformation.add(Box.createHorizontalStrut(STRUT));
        JLabel vendorLabel = new JLabel("Производитель");
        JComboBox vendorInput = new JComboBox();
        mainInformation.add(createColumn(vendorLabel, vendorInput));

        mainInformation.add(Box.createHorizontalStrut(STRUT));
        JLabel modelLabel = new JLabel("Модель");
        JComboBox modelInput = new JComboBox();
        mainInformation.add(createColumn(modelLabel, modelInput));

        mainInformation.add(Box.createHorizontalStrut(STRUT));
        JLabel receiptDateLabel = new JLabel("Добавленно");
        JTextField receiptDateInput = new JTextField();
        mainInformation.add(createColumn(receiptDateLabel, receiptDateInput));
        mainInformation.add(Box.createHorizontalStrut(3));
    }

    private Box createColumn(JLabel label, JComponent component) {
        Box column = new Box(BoxLayout.Y_AXIS);
        column.add(label);
        Dimension maximumSize = component.getMaximumSize();
        maximumSize.height = component.getMinimumSize().height;
        component.setMaximumSize(maximumSize);
        component.setAlignmentX(0f);
        column.add(component);
        return column;
    }
}
