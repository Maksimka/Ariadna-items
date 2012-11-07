package ru.tomsk.ariadna.items.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

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

    @JoinColumn(name = "item_id", referencedColumnName = "id",
    insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;

    @JoinColumn(name = "delivery_packet_id", referencedColumnName = "delivery_packet_id",
    insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DeliveryPacket deliveryPacket;

    @Basic(optional = false)
    @Column(name = "is_return")
    private boolean isReturn;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    public Delivery() {
        //Автоматически созданный конструктор
    }

    public Delivery(DeliveryPK deliveryPK) {
        this.deliveryPK = deliveryPK;
    }

    public Delivery(int deliveryPacketId, int itemId) {
        this.deliveryPK = new DeliveryPK(deliveryPacketId, itemId);
        this.isReturn = false;
        this.returnDate = null;
    }

    public DeliveryPK getDeliveryPK() {
        return deliveryPK;
    }

    public void setDeliveryPK(DeliveryPK deliveryPK) {
        this.deliveryPK = deliveryPK;
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

    public boolean isReturn() {
        return isReturn;
    }

    public void setReturn(boolean isReturn) {
        this.isReturn = isReturn;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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
