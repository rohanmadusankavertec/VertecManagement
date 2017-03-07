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
@Table(name = "features")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Features.findAll", query = "SELECT f FROM Features f"),
    @NamedQuery(name = "Features.findById", query = "SELECT f FROM Features f WHERE f.id = :id"),
    @NamedQuery(name = "Features.findByFeature", query = "SELECT f FROM Features f WHERE f.feature = :feature")})
public class Features implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "featuresId")
    private Collection<PackageHasFeatures> packageHasFeaturesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "feature")
    private String feature;

    public Features() {
    }

    public Features(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
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
        if (!(object instanceof Features)) {
            return false;
        }
        Features other = (Features) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.Features[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<PackageHasFeatures> getPackageHasFeaturesCollection() {
        return packageHasFeaturesCollection;
    }

    public void setPackageHasFeaturesCollection(Collection<PackageHasFeatures> packageHasFeaturesCollection) {
        this.packageHasFeaturesCollection = packageHasFeaturesCollection;
    }
    
}
