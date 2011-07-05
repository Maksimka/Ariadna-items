/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ItemReturnPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "delivery_packet_id")
    private int deliveryPacketId;

    @Basic(optional = false)
    @Column(name = "item_id")
    private int itemId;

    public ItemReturnPK() {
        //Автоматически созданный конструктор
    }

    public ItemReturnPK(int deliveryPacketId, int itemId) {
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
        if (!(object instanceof ItemReturnPK)) {
            return false;
        }
        ItemReturnPK other = (ItemReturnPK) object;
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
        return "ru.tomsk.ariadna.items.data.ItemReturnPK[ deliveryPacketId=" + deliveryPacketId + ", itemId=" + itemId + " ]";
    }
}
