package ru.tomsk.ariadna.items.item;

import javax.swing.JLabel;
import javax.swing.JPanel;
import ru.tomsk.ariadna.items.data.DeliveryPacket;
import ru.tomsk.ariadna.items.data.Item;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
public class ItemForm extends JPanel {

  private final Item item;

  public ItemForm(Item item) {
    this.item = item;
    add(new JLabel(item.toString()));
  }
}
