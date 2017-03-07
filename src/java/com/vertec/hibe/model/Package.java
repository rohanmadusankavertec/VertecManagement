/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.hibe.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Java-Dev-Ruchira
 */
@Entity
@Table(name = "package")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Package.findAll", query = "SELECT p FROM Package p"),
    @NamedQuery(name = "Package.findById", query = "SELECT p FROM Package p WHERE p.id = :id"),
    @NamedQuery(name = "Package.findByName", query = "SELECT p FROM Package p WHERE p.name = :name"),
    @NamedQuery(name = "Package.findByPrice", query = "SELECT p FROM Package p WHERE p.price = :price"),
    @NamedQuery(name = "Package.findByIsValid", query = "SELECT p FROM Package p WHERE p.isValid = :isValid")})
public class Package implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packageId")
    private Collection<PackageHasFeatures> packageHasFeaturesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packageId")
    private Collection<Quotation> quotationCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "is_valid")
    private Boolean isValid;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Service serviceId;

    public Package() {
    }

    public Package(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
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
        if (!(object instanceof Package)) {
            return false;
        }
        Package other = (Package) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.Package[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Quotation> getQuotationCollection() {
        return quotationCollection;
    }

    public void setQuotationCollection(Collection<Quotation> quotationCollection) {
        this.quotationCollection = quotationCollection;
    }

    @XmlTransient
    public Collection<PackageHasFeatures> getPackageHasFeaturesCollection() {
        return packageHasFeaturesCollection;
    }

    public void setPackageHasFeaturesCollection(Collection<PackageHasFeatures> packageHasFeaturesCollection) {
        this.packageHasFeaturesCollection = packageHasFeaturesCollection;
    }
    
}
