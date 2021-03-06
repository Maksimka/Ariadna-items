package ru.tomsk.ariadna.items.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
@Entity
@Table(name = "delivery_packet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeliveryPacket.findAll",
    query = "SELECT d FROM DeliveryPacket d"),
    @NamedQuery(name = "DeliveryPacket.findByid",
    query = "SELECT d FROM DeliveryPacket d WHERE d.id = :id"),
    @NamedQuery(name = "DeliveryPacket.findByEvent",
    query = "SELECT d FROM DeliveryPacket d WHERE d.event = :event"),
    @NamedQuery(name = "DeliveryPacket.findByDeliveryDate",
    query = "SELECT d FROM DeliveryPacket d WHERE d.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "DeliveryPacket.findByExpectedReturnDate",
    query = "SELECT d FROM DeliveryPacket d WHERE d.expectedReturnDate = :expectedReturnDate")})
public class DeliveryPacket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "delivery_packet_id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "event")
    private String event;

    @Basic(optional = false)
    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Basic(optional = false)
    @Column(name = "expected_return_date")
    @Temporal(TemporalType.DATE)
    private Date expectedReturnDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deliveryPacket")
    private List<Delivery> itemDeliveries;

    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne(optional = false)
    private AriadnaMember member;

    public DeliveryPacket() {
        //Автоматически созданный конструктор
    }

    public DeliveryPacket(Integer id) {
        this.id = id;
    }

    public DeliveryPacket(Integer id, String event, Date deliveryDate, Date expectedReturnDate) {
        this.id = id;
        this.event = event;
        this.deliveryDate = deliveryDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    @XmlTransient
    public List<Delivery> getItemDeliveries() {
        return itemDeliveries;
    }

    public void setItemDeliveries(List<Delivery> itemDeliveries) {
        this.itemDeliveries = itemDeliveries;
    }

    public AriadnaMember getMember() {
        return member;
    }

    public void setMember(AriadnaMember member) {
        this.member = member;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryPacket)) {
            return false;
        }
        DeliveryPacket other = (DeliveryPacket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.DeliveryPacket[ deliveryPacketId=" + id + " ]";
    }
}
