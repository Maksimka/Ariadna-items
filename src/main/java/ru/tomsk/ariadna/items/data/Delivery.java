/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tomsk.ariadna.items.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
@Entity
@Table(name = "delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d"),
    @NamedQuery(name = "Delivery.findByDeliveryPacketId", query = "SELECT d FROM Delivery d WHERE d.deliveryPK.deliveryPacketId = :deliveryPacketId"),
    @NamedQuery(name = "Delivery.findByItemId", query = "SELECT d FROM Delivery d WHERE d.deliveryPK.itemId = :itemId")})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DeliveryPK deliveryPK;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "delivery")
    private ItemReturn itemReturn;

    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;

    @JoinColumn(name = "delivery_packet_id", referencedColumnName = "delivery_packet_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DeliveryPacket deliveryPacket;

    @OneToMany(mappedBy = "deliveryPacketId")
    private Collection<Discarded> discardedCollection;

    public Delivery() {
        //Автоматически созданный конструктор
    }

    public Delivery(DeliveryPK deliveryPK) {
        this.deliveryPK = deliveryPK;
    }

    public Delivery(int deliveryPacketId, int itemId) {
        this.deliveryPK = new DeliveryPK(deliveryPacketId, itemId);
    }

    public DeliveryPK getDeliveryPK() {
        return deliveryPK;
    }

    public void setDeliveryPK(DeliveryPK deliveryPK) {
        this.deliveryPK = deliveryPK;
    }

    public ItemReturn getItemReturn() {
        return itemReturn;
    }

    public void setItemReturn(ItemReturn itemReturn) {
        this.itemReturn = itemReturn;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public DeliveryPacket getDeliveryPacket() {
        return deliveryPacket;
    }

    public void setDeliveryPacket(DeliveryPacket deliveryPacket) {
        this.deliveryPacket = deliveryPacket;
    }

    @XmlTransient
    public Collection<Discarded> getDiscardedCollection() {
        return discardedCollection;
    }

    public void setDiscardedCollection(Collection<Discarded> discardedCollection) {
        this.discardedCollection = discardedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryPK != null ? deliveryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.deliveryPK == null && other.deliveryPK != null) || (this.deliveryPK != null && !this.deliveryPK.equals(other.deliveryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.Delivery[ deliveryPK=" + deliveryPK + " ]";
    }
}
