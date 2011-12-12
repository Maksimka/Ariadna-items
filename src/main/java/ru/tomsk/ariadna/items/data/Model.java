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
@Table(name = "model")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Model.findAll", query = "SELECT m FROM Model m"),
    @NamedQuery(name = "Model.findByName", query = "SELECT m FROM Model m WHERE m.modelPK.name = :name"),
    @NamedQuery(name = "Model.findByVendor", query = "SELECT m FROM Model m WHERE m.modelPK.vendor = :vendor"),
    @NamedQuery(name = "Model.findByType", query = "SELECT m FROM Model m WHERE m.modelPK.type = :type"),
    @NamedQuery(name = "Model.findByDescription", query = "SELECT m FROM Model m WHERE m.description = :description")})
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ModelPK modelPK;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "type", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Type type1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    private Collection<Item> itemCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    private Collection<ModelPropertyValue> modelPropertyValueCollection;

    public Model() {
        //Автоматически созданный конструктор
    }

    public Model(ModelPK modelPK) {
        this.modelPK = modelPK;
    }

    public Model(ModelPK modelPK, String description) {
        this.modelPK = modelPK;
        this.description = description;
    }

    public Model(String name, String vendor, String type) {
        this.modelPK = new ModelPK(name, vendor, type);
    }

    public ModelPK getModelPK() {
        return modelPK;
    }

    public void setModelPK(ModelPK modelPK) {
        this.modelPK = modelPK;
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
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
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
        hash += (modelPK != null ? modelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Model)) {
            return false;
        }
        Model other = (Model) object;
        if ((this.modelPK == null && other.modelPK != null) || (this.modelPK != null && !this.modelPK.equals(other.modelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.Model[ modelPK=" + modelPK + " ]";
    }
}
