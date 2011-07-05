/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tomsk.ariadna.items.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
@Entity
@Table(name = "item_return")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemReturn.findAll", query = "SELECT i FROM ItemReturn i"),
    @NamedQuery(name = "ItemReturn.findByDeliveryPacketId", query = "SELECT i FROM ItemReturn i WHERE i.itemReturnPK.deliveryPacketId = :deliveryPacketId"),
    @NamedQuery(name = "ItemReturn.findByItemId", query = "SELECT i FROM ItemReturn i WHERE i.itemReturnPK.itemId = :itemId"),
    @NamedQuery(name = "ItemReturn.findByReturnDate", query = "SELECT i FROM ItemReturn i WHERE i.returnDate = :returnDate")})
public class ItemReturn implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ItemReturnPK itemReturnPK;

    @Basic(optional = false)
    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    @JoinColumns({
        @JoinColumn(name = "delivery_packet_id", referencedColumnName = "delivery_packet_id", insertable = false, updatable = false),
        @JoinColumn(name = "item_id", referencedColumnName = "item_id", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Delivery delivery;

    public ItemReturn() {
        //Автоматически созданный конструктор
    }

    public ItemReturn(ItemReturnPK itemReturnPK) {
        this.itemReturnPK = itemReturnPK;
    }

    public ItemReturn(ItemReturnPK itemReturnPK, Date returnDate) {
        this.itemReturnPK = itemReturnPK;
        this.returnDate = returnDate;
    }

    public ItemReturn(int deliveryPacketId, int itemId) {
        this.itemReturnPK = new ItemReturnPK(deliveryPacketId, itemId);
    }

    public ItemReturnPK getItemReturnPK() {
        return itemReturnPK;
    }

    public void setItemReturnPK(ItemReturnPK itemReturnPK) {
        this.itemReturnPK = itemReturnPK;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemReturnPK != null ? itemReturnPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemReturn)) {
            return false;
        }
        ItemReturn other = (ItemReturn) object;
        if ((this.itemReturnPK == null && other.itemReturnPK != null) || (this.itemReturnPK != null && !this.itemReturnPK.equals(other.itemReturnPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.ItemReturn[ itemReturnPK=" + itemReturnPK + " ]";
    }
}
