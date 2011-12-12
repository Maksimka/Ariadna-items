package ru.tomsk.ariadna.items.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ŝajmardanov Maksim Maratoviĉ <maximax@ms.tusur.ru>
 */
@Embeddable
public class ModelPropertyValuePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "type")
    private String type;

    @Basic(optional = false)
    @Column(name = "model_name")
    private String modelName;

    @Basic(optional = false)
    @Column(name = "vendor")
    private String vendor;

    public ModelPropertyValuePK() {
        //Автоматически созданный конструктор
    }

    public ModelPropertyValuePK(String name, String type, String modelName, String vendor) {
        this.name = name;
        this.type = type;
        this.modelName = modelName;
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        hash += (type != null ? type.hashCode() : 0);
        hash += (modelName != null ? modelName.hashCode() : 0);
        hash += (vendor != null ? vendor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelPropertyValuePK)) {
            return false;
        }
        ModelPropertyValuePK other = (ModelPropertyValuePK) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        if ((this.type == null && other.type != null) || (this.type != null && !this.type.equals(other.type))) {
            return false;
        }
        if ((this.modelName == null && other.modelName != null) || (this.modelName != null && !this.modelName.equals(other.modelName))) {
            return false;
        }
        if ((this.vendor == null && other.vendor != null) || (this.vendor != null && !this.vendor.equals(other.vendor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.ModelPropertyValuePK[ name=" + name + ", type=" + type + ", modelName=" + modelName + ", vendor=" + vendor + " ]";
    }
}
