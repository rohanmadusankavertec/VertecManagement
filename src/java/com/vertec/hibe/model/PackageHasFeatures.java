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
@Table(name = "package_has_features")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PackageHasFeatures.findAll", query = "SELECT p FROM PackageHasFeatures p"),
    @NamedQuery(name = "PackageHasFeatures.findById", query = "SELECT p FROM PackageHasFeatures p WHERE p.id = :id")})
public class PackageHasFeatures implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packageHasFeaturesId")
    private Collection<QuotationHasPackages> quotationHasPackagesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Package packageId;
    @JoinColumn(name = "features_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Features featuresId;

    public PackageHasFeatures() {
    }

    public PackageHasFeatures(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Package getPackageId() {
        return packageId;
    }

    public void setPackageId(Package packageId) {
        this.packageId = packageId;
    }

    public Features getFeaturesId() {
        return featuresId;
    }

    public void setFeaturesId(Features featuresId) {
        this.featuresId = featuresId;
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
        if (!(object instanceof PackageHasFeatures)) {
            return false;
        }
        PackageHasFeatures other = (PackageHasFeatures) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.PackageHasFeatures[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<QuotationHasPackages> getQuotationHasPackagesCollection() {
        return quotationHasPackagesCollection;
    }

    public void setQuotationHasPackagesCollection(Collection<QuotationHasPackages> quotationHasPackagesCollection) {
        this.quotationHasPackagesCollection = quotationHasPackagesCollection;
    }
    
}
