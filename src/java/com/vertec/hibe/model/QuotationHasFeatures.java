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
@Table(name = "quotation_has_features")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuotationHasFeatures.findAll", query = "SELECT q FROM QuotationHasFeatures q"),
    @NamedQuery(name = "QuotationHasFeatures.findById", query = "SELECT q FROM QuotationHasFeatures q WHERE q.id = :id"),
    @NamedQuery(name = "QuotationHasFeatures.findByDescription", query = "SELECT q FROM QuotationHasFeatures q WHERE q.description = :description")})
public class QuotationHasFeatures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Quotation quotationId;
    @JoinColumn(name = "features_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Features featuresId;

    public QuotationHasFeatures() {
    }

    public QuotationHasFeatures(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Quotation getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Quotation quotationId) {
        this.quotationId = quotationId;
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
        if (!(object instanceof QuotationHasFeatures)) {
            return false;
        }
        QuotationHasFeatures other = (QuotationHasFeatures) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.QuotationHasFeatures[ id=" + id + " ]";
    }
    
}
