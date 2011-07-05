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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
@Entity
@Table(name = "item_property_value")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemPropertyValue.findAll", query = "SELECT i FROM ItemPropertyValue i"),
    @NamedQuery(name = "ItemPropertyValue.findByItemId", query = "SELECT i FROM ItemPropertyValue i WHERE i.itemPropertyValuePK.itemId = :itemId"),
    @NamedQuery(name = "ItemPropertyValue.findByName", query = "SELECT i FROM ItemPropertyValue i WHERE i.itemPropertyValuePK.name = :name"),
    @NamedQuery(name = "ItemPropertyValue.findByType", query = "SELECT i FROM ItemPropertyValue i WHERE i.itemPropertyValuePK.type = :type"),
    @NamedQuery(name = "ItemPropertyValue.findByValue", query = "SELECT i FROM ItemPropertyValue i WHERE i.value = :value"),
    @NamedQuery(name = "ItemPropertyValue.findByChangeDate", query = "SELECT i FROM ItemPropertyValue i WHERE i.changeDate = :changeDate")})
public class ItemPropertyValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ItemPropertyValuePK itemPropertyValuePK;

    @Basic(optional = false)
    @Column(name = "value")
    private String value;

    @Basic(optional = false)
    @Column(name = "change_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeDate;

    @JoinColumns({
        @JoinColumn(name = "name", referencedColumnName = "name", insertable = false, updatable = false),
        @JoinColumn(name = "type", referencedColumnName = "type", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ItemProperty itemProperty;

    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;

    public ItemPropertyValue() {
        //Автоматически созданный конструктор
    }

    public ItemPropertyValue(ItemPropertyValuePK itemPropertyValuePK) {
        this.itemPropertyValuePK = itemPropertyValuePK;
    }

    public ItemPropertyValue(ItemPropertyValuePK itemPropertyValuePK, String value, Date changeDate) {
        this.itemPropertyValuePK = itemPropertyValuePK;
        this.value = value;
        this.changeDate = changeDate;
    }

    public ItemPropertyValue(int itemId, String name, String type) {
        this.itemPropertyValuePK = new ItemPropertyValuePK(itemId, name, type);
    }

    public ItemPropertyValuePK getItemPropertyValuePK() {
        return itemPropertyValuePK;
    }

    public void setItemPropertyValuePK(ItemPropertyValuePK itemPropertyValuePK) {
        this.itemPropertyValuePK = itemPropertyValuePK;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public ItemProperty getItemProperty() {
        return itemProperty;
    }

    public void setItemProperty(ItemProperty itemProperty) {
        this.itemProperty = itemProperty;
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
        hash += (itemPropertyValuePK != null ? itemPropertyValuePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPropertyValue)) {
            return false;
        }
        ItemPropertyValue other = (ItemPropertyValue) object;
        if ((this.itemPropertyValuePK == null && other.itemPropertyValuePK != null) || (this.itemPropertyValuePK != null && !this.itemPropertyValuePK.equals(other.itemPropertyValuePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.ItemPropertyValue[ itemPropertyValuePK=" + itemPropertyValuePK + " ]";
    }
}
