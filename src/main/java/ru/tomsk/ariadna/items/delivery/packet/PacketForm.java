package ru.tomsk.ariadna.items.delivery.packet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
public class PacketForm extends JPanel {

  private final DeliveryPacket packet;

  public PacketForm(DeliveryPacket packet) {
    this.packet = packet;
    add(new JLabel(packet.toString()));
  }
}
