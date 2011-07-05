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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "model_property")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModelProperty.findAll", query = "SELECT m FROM ModelProperty m"),
    @NamedQuery(name = "ModelProperty.findByName", query = "SELECT m FROM ModelProperty m WHERE m.modelPropertyPK.name = :name"),
    @NamedQuery(name = "ModelProperty.findByType", query = "SELECT m FROM ModelProperty m WHERE m.modelPropertyPK.type = :type"),
    @NamedQuery(name = "ModelProperty.findByDescription", query = "SELECT m FROM ModelProperty m WHERE m.description = :description")})
public class ModelProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ModelPropertyPK modelPropertyPK;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "type", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Type type1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modelProperty")
    private Collection<ModelPropertyValue> modelPropertyValueCollection;

    public ModelProperty() {
        //Автоматически созданный конструктор
    }

    public ModelProperty(ModelPropertyPK modelPropertyPK) {
        this.modelPropertyPK = modelPropertyPK;
    }

    public ModelProperty(ModelPropertyPK modelPropertyPK, String description) {
        this.modelPropertyPK = modelPropertyPK;
        this.description = description;
    }

    public ModelProperty(String name, String type) {
        this.modelPropertyPK = new ModelPropertyPK(name, type);
    }

    public ModelPropertyPK getModelPropertyPK() {
        return modelPropertyPK;
    }

    public void setModelPropertyPK(ModelPropertyPK modelPropertyPK) {
        this.modelPropertyPK = modelPropertyPK;
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
    public Collection<ModelPropertyValue> getModelPropertyValueCollection() {
        return modelPropertyValueCollection;
    }

    public void setModelPropertyValueCollection(Collection<ModelPropertyValue> modelPropertyValueCollection) {
        this.modelPropertyValueCollection = modelPropertyValueCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelPropertyPK != null ? modelPropertyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelProperty)) {
            return false;
        }
        ModelProperty other = (ModelProperty) object;
        if ((this.modelPropertyPK == null && other.modelPropertyPK != null) || (this.modelPropertyPK != null && !this.modelPropertyPK.equals(other.modelPropertyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.ModelProperty[ modelPropertyPK=" + modelPropertyPK + " ]";
    }
}
