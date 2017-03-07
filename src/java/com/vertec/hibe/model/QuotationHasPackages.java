/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.hibe.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Java-Dev-Ruchira
 */
@Entity
@Table(name = "quotation_has_packages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuotationHasPackages.findAll", query = "SELECT q FROM QuotationHasPackages q"),
    @NamedQuery(name = "QuotationHasPackages.findById", query = "SELECT q FROM QuotationHasPackages q WHERE q.id = :id")})
public class QuotationHasPackages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Quotation quotationId;
    @JoinColumn(name = "package_has_features_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PackageHasFeatures packageHasFeaturesId;

    public QuotationHasPackages() {
    }

    public QuotationHasPackages(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Quotation getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Quotation quotationId) {
        this.quotationId = quotationId;
    }

    public PackageHasFeatures getPackageHasFeaturesId() {
        return packageHasFeaturesId;
    }

    public void setPackageHasFeaturesId(PackageHasFeatures packageHasFeaturesId) {
        this.packageHasFeaturesId = packageHasFeaturesId;
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
        if (!(object instanceof QuotationHasPackages)) {
            return false;
        }
        QuotationHasPackages other = (QuotationHasPackages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.QuotationHasPackages[ id=" + id + " ]";
    }
    
}
