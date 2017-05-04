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
 * @author Ruchira
 */
@Entity
@Table(name = "quotation_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuotationStatus.findAll", query = "SELECT q FROM QuotationStatus q"),
    @NamedQuery(name = "QuotationStatus.findById", query = "SELECT q FROM QuotationStatus q WHERE q.id = :id"),
    @NamedQuery(name = "QuotationStatus.findByStatus", query = "SELECT q FROM QuotationStatus q WHERE q.status = :status")})
public class QuotationStatus implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quotationStatusId")
    private Collection<ProjectProposal> projectProposalCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quotationStatusId")
    private Collection<HardwareQuotation> hardwareQuotationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quotationStatusId")
    private Collection<CctvQuotationInfo> cctvQuotationInfoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quotationStatusId")
    private Collection<Quotation> quotationCollection;

    public QuotationStatus() {
    }

    public QuotationStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<HardwareQuotation> getHardwareQuotationCollection() {
        return hardwareQuotationCollection;
    }

    public void setHardwareQuotationCollection(Collection<HardwareQuotation> hardwareQuotationCollection) {
        this.hardwareQuotationCollection = hardwareQuotationCollection;
    }

    @XmlTransient
    public Collection<CctvQuotationInfo> getCctvQuotationInfoCollection() {
        return cctvQuotationInfoCollection;
    }

    public void setCctvQuotationInfoCollection(Collection<CctvQuotationInfo> cctvQuotationInfoCollection) {
        this.cctvQuotationInfoCollection = cctvQuotationInfoCollection;
    }

    @XmlTransient
    public Collection<Quotation> getQuotationCollection() {
        return quotationCollection;
    }

    public void setQuotationCollection(Collection<Quotation> quotationCollection) {
        this.quotationCollection = quotationCollection;
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
        if (!(object instanceof QuotationStatus)) {
            return false;
        }
        QuotationStatus other = (QuotationStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.QuotationStatus[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ProjectProposal> getProjectProposalCollection() {
        return projectProposalCollection;
    }

    public void setProjectProposalCollection(Collection<ProjectProposal> projectProposalCollection) {
        this.projectProposalCollection = projectProposalCollection;
    }
    
}
