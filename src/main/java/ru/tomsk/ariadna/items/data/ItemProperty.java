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
@Table(name = "item_property")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemProperty.findAll", query = "SELECT i FROM ItemProperty i"),
    @NamedQuery(name = "ItemProperty.findByName", query = "SELECT i FROM ItemProperty i WHERE i.itemPropertyPK.name = :name"),
    @NamedQuery(name = "ItemProperty.findByType", query = "SELECT i FROM ItemProperty i WHERE i.itemPropertyPK.type = :type"),
    @NamedQuery(name = "ItemProperty.findByDescription", query = "SELECT i FROM ItemProperty i WHERE i.description = :description")})
public class ItemProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ItemPropertyPK itemPropertyPK;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "type", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Type type1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemProperty")
    private Collection<ItemPropertyValue> itemPropertyValueCollection;

    public ItemProperty() {
        //Автоматически созданный конструктор
    }

    public ItemProperty(ItemPropertyPK itemPropertyPK) {
        this.itemPropertyPK = itemPropertyPK;
    }

    public ItemProperty(ItemPropertyPK itemPropertyPK, String description) {
        this.itemPropertyPK = itemPropertyPK;
        this.description = description;
    }

    public ItemProperty(String name, String type) {
        this.itemPropertyPK = new ItemPropertyPK(name, type);
    }

    public ItemPropertyPK getItemPropertyPK() {
        return itemPropertyPK;
    }

    public void setItemPropertyPK(ItemPropertyPK itemPropertyPK) {
        this.itemPropertyPK = itemPropertyPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    @XmlTransient
    public Collection<ItemPropertyValue> getItemPropertyValueCollection() {
        return itemPropertyValueCollection;
    }

    public void setItemPropertyValueCollection(Collection<ItemPropertyValue> itemPropertyValueCollection) {
        this.itemPropertyValueCollection = itemPropertyValueCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemPropertyPK != null ? itemPropertyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemProperty)) {
            return false;
        }
        ItemProperty other = (ItemProperty) object;
        if ((this.itemPropertyPK == null && other.itemPropertyPK != null) || (this.itemPropertyPK != null && !this.itemPropertyPK.equals(other.itemPropertyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.ItemProperty[ itemPropertyPK=" + itemPropertyPK + " ]";
    }
}
