/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tomsk.ariadna.items.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
@Entity
@Table(name = "type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"),
    @NamedQuery(name = "Type.findByName", query = "SELECT t FROM Type t WHERE t.name = :name")})
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type1")
    private Collection<Model> modelCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type1")
    private Collection<ItemProperty> itemPropertyCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type1")
    private Collection<ModelProperty> modelPropertyCollection;

    public Type() {
        //Автоматически созданный конструктор
    }

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Model> getModelCollection() {
        return modelCollection;
    }

    public void setModelCollection(Collection<Model> modelCollection) {
        this.modelCollection = modelCollection;
    }

    @XmlTransient
    public Collection<ItemProperty> getItemPropertyCollection() {
        return itemPropertyCollection;
    }

    public void setItemPropertyCollection(Collection<ItemProperty> itemPropertyCollection) {
        this.itemPropertyCollection = itemPropertyCollection;
    }

    @XmlTransient
    public Collection<ModelProperty> getModelPropertyCollection() {
        return modelPropertyCollection;
    }

    public void setModelPropertyCollection(Collection<ModelProperty> modelPropertyCollection) {
        this.modelPropertyCollection = modelPropertyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.Type[ name=" + name + " ]";
    }
}
