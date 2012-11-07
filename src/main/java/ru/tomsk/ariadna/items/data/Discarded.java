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
@Table(name = "discarded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discarded.findAll", query = "SELECT d FROM Discarded d"),
    @NamedQuery(name = "Discarded.findByItemId", query = "SELECT d FROM Discarded d WHERE d.itemId = :itemId"),
    @NamedQuery(name = "Discarded.findByDiscardedDate", query = "SELECT d FROM Discarded d WHERE d.discardedDate = :discardedDate"),
    @NamedQuery(name = "Discarded.findByCause", query = "SELECT d FROM Discarded d WHERE d.cause = :cause")})
public class Discarded implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;

    @Basic(optional = false)
    @Column(name = "discarded_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date discardedDate;

    @Basic(optional = false)
    @Column(name = "cause")
    private String cause;

    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Item item;

    public Discarded() {
        //Автоматически созданный конструктор
    }

    public Discarded(Integer itemId) {
        this.itemId = itemId;
    }

    public Discarded(Integer itemId, Date discardedDate, String cause) {
        this.itemId = itemId;
        this.discardedDate = discardedDate;
        this.cause = cause;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Date getDiscardedDate() {
        return discardedDate;
    }

    public void setDiscardedDate(Date discardedDate) {
        this.discardedDate = discardedDate;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discarded)) {
            return false;
        }
        Discarded other = (Discarded) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.Discarded[ itemId=" + itemId + " ]";
    }
}
