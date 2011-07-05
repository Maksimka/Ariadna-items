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
@Table(name = "model_property_value")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModelPropertyValue.findAll", query = "SELECT m FROM ModelPropertyValue m"),
    @NamedQuery(name = "ModelPropertyValue.findByName", query = "SELECT m FROM ModelPropertyValue m WHERE m.modelPropertyValuePK.name = :name"),
    @NamedQuery(name = "ModelPropertyValue.findByType", query = "SELECT m FROM ModelPropertyValue m WHERE m.modelPropertyValuePK.type = :type"),
    @NamedQuery(name = "ModelPropertyValue.findByModelName", query = "SELECT m FROM ModelPropertyValue m WHERE m.modelPropertyValuePK.modelName = :modelName"),
    @NamedQuery(name = "ModelPropertyValue.findByVendor", query = "SELECT m FROM ModelPropertyValue m WHERE m.modelPropertyValuePK.vendor = :vendor"),
    @NamedQuery(name = "ModelPropertyValue.findByValue", query = "SELECT m FROM ModelPropertyValue m WHERE m.value = :value"),
    @NamedQuery(name = "ModelPropertyValue.findByChangeDate", query = "SELECT m FROM ModelPropertyValue m WHERE m.changeDate = :changeDate")})
public class ModelPropertyValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ModelPropertyValuePK modelPropertyValuePK;

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
    private ModelProperty modelProperty;

    @JoinColumns({
        @JoinColumn(name = "model_name", referencedColumnName = "name", insertable = false, updatable = false),
        @JoinColumn(name = "vendor", referencedColumnName = "vendor", insertable = false, updatable = false),
        @JoinColumn(name = "type", referencedColumnName = "type", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Model model;

    public ModelPropertyValue() {
        //Автоматически созданный конструктор
    }

    public ModelPropertyValue(ModelPropertyValuePK modelPropertyValuePK) {
        this.modelPropertyValuePK = modelPropertyValuePK;
    }

    public ModelPropertyValue(ModelPropertyValuePK modelPropertyValuePK, String value, Date changeDate) {
        this.modelPropertyValuePK = modelPropertyValuePK;
        this.value = value;
        this.changeDate = changeDate;
    }

    public ModelPropertyValue(String name, String type, String modelName, String vendor) {
        this.modelPropertyValuePK = new ModelPropertyValuePK(name, type, modelName, vendor);
    }

    public ModelPropertyValuePK getModelPropertyValuePK() {
        return modelPropertyValuePK;
    }

    public void setModelPropertyValuePK(ModelPropertyValuePK modelPropertyValuePK) {
        this.modelPropertyValuePK = modelPropertyValuePK;
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

    public ModelProperty getModelProperty() {
        return modelProperty;
    }

    public void setModelProperty(ModelProperty modelProperty) {
        this.modelProperty = modelProperty;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelPropertyValuePK != null ? modelPropertyValuePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelPropertyValue)) {
            return false;
        }
        ModelPropertyValue other = (ModelPropertyValue) object;
        if ((this.modelPropertyValuePK == null && other.modelPropertyValuePK != null) || (this.modelPropertyValuePK != null && !this.modelPropertyValuePK.equals(other.modelPropertyValuePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.tomsk.ariadna.items.data.ModelPropertyValue[ modelPropertyValuePK=" + modelPropertyValuePK + " ]";
    }
}
