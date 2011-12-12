package ru.tomsk.ariadna.items.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
@Entity
@Table(name = "member")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AriadnaMember.findAll", query = "SELECT m FROM AriadnaMember m"),
    @NamedQuery(name = "AriadnaMember.findByMemberId", query = "SELECT m FROM AriadnaMember m WHERE m.memberId = :memberId"),
    @NamedQuery(name = "AriadnaMember.findByFirstname", query = "SELECT m FROM AriadnaMember m WHERE m.firstname = :firstname"),
    @NamedQuery(name = "AriadnaMember.findByLastname", query = "SELECT m FROM AriadnaMember m WHERE m.lastname = :lastname")})
public class AriadnaMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "member_id")
    private Integer memberId;

    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;

    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private Collection<DeliveryPacket> deliveryPacketCollection;

    public AriadnaMember() {
        //Автоматически созданный конструктор
    }

    public AriadnaMember(Integer memberId) {
        this.memberId = memberId;
    }

    public AriadnaMember(Integer memberId, String firstname, String lastname) {
        this.memberId = memberId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getFullName() {
        return this.firstname + " " + lastname;
    }

    @XmlTransient
    public Collection<DeliveryPacket> getDeliveryPacketCollection() {
        return deliveryPacketCollection;
    }

    public void setDeliveryPacketCollection(Collection<DeliveryPacket> deliveryPacketCollection) {
        this.deliveryPacketCollection = deliveryPacketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AriadnaMember)) {
            return false;
        }
        AriadnaMember other = (AriadnaMember) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.AriadnaMember[ memberId=" + memberId + " ]";
    }
}
