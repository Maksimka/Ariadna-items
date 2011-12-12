package ru.tomsk.ariadna.items.delivery;

import javax.swing.JLabel;
import javax.swing.JPanel;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
public class DeliveryForm extends JPanel {

  private final DeliveryPacket packet;

  public DeliveryForm(DeliveryPacket member) {
    this.packet = member;
    add(new JLabel(member.toString()));
  }
}
