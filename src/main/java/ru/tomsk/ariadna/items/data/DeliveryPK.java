package ru.tomsk.ariadna.items.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
@Embeddable
public class DeliveryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "delivery_packet_id")
    private int deliveryPacketId;

    @Basic(optional = false)
    @Column(name = "item_id")
    private int itemId;

    public DeliveryPK() {
        //Автоматически созданный конструктор
    }

    public DeliveryPK(int deliveryPacketId, int itemId) {
        this.deliveryPacketId = deliveryPacketId;
        this.itemId = itemId;
    }

    public int getDeliveryPacketId() {
        return deliveryPacketId;
    }

    public void setDeliveryPacketId(int deliveryPacketId) {
        this.deliveryPacketId = deliveryPacketId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) deliveryPacketId;
        hash += (int) itemId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryPK)) {
            return false;
        }
        DeliveryPK other = (DeliveryPK) object;
        if (this.deliveryPacketId != other.deliveryPacketId) {
            return false;
        }
        if (this.itemId != other.itemId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.DeliveryPK[ deliveryPacketId=" + deliveryPacketId + ", itemId=" + itemId + " ]";
    }
}
