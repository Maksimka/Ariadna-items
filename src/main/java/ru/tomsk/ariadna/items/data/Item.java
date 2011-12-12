package ru.tomsk.ariadna.items.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id"),
    @NamedQuery(name = "Item.findByNumber", query = "SELECT i FROM Item i WHERE i.number = :number"),
    @NamedQuery(name = "Item.findByReceiptDate", query = "SELECT i FROM Item i WHERE i.receiptDate = :receiptDate"),
    @NamedQuery(name = "Item.findByNote", query = "SELECT i FROM Item i WHERE i.note = :note")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "number")
    private int number;

    @Basic(optional = false)
    @Column(name = "receipt_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiptDate;

    @Basic(optional = false)
    @Column(name = "note")
    private String note;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<ItemPropertyValue> itemPropertyValueCollection;

    @JoinColumns({
        @JoinColumn(name = "model_name", referencedColumnName = "name"),
        @JoinColumn(name = "vendor", referencedColumnName = "vendor"),
        @JoinColumn(name = "type", referencedColumnName = "type")})
    @ManyToOne(optional = false)
    private Model model;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<Delivery> deliveryCollection;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item")
    private Discarded discarded;

    public Item() {
        //Автоматически созданный конструктор
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Item(Integer id, int number, Date receiptDate, String note) {
        this.id = id;
        this.number = number;
        this.receiptDate = receiptDate;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @XmlTransient
    public Collection<ItemPropertyValue> getItemPropertyValueCollection() {
        return itemPropertyValueCollection;
    }

    public void setItemPropertyValueCollection(Collection<ItemPropertyValue> itemPropertyValueCollection) {
        this.itemPropertyValueCollection = itemPropertyValueCollection;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @XmlTransient
    public Collection<Delivery> getDeliveryCollection() {
        return deliveryCollection;
    }

    public void setDeliveryCollection(Collection<Delivery> deliveryCollection) {
        this.deliveryCollection = deliveryCollection;
    }

    public Discarded getDiscarded() {
        return discarded;
    }

    public void setDiscarded(Discarded discarded) {
        this.discarded = discarded;
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.Item[ id=" + id + " ]";
    }
}
