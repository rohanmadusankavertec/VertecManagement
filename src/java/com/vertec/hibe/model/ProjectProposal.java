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
 * @author vertec-r
 */
@Entity
@Table(name = "project_proposal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectProposal.findAll", query = "SELECT p FROM ProjectProposal p"),
    @NamedQuery(name = "ProjectProposal.findById", query = "SELECT p FROM ProjectProposal p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectProposal.findByProposalName", query = "SELECT p FROM ProjectProposal p WHERE p.proposalName = :proposalName"),
    @NamedQuery(name = "ProjectProposal.findByIsValid", query = "SELECT p FROM ProjectProposal p WHERE p.isValid = :isValid")})
public class ProjectProposal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "proposal_name")
    private String proposalName;
    @Column(name = "is_valid")
    private Boolean isValid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectProposalId")
    private Collection<ProjectDetails> projectDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectProposalId")
    private Collection<Agreement> agreementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectProposalId")
    private Collection<Quotation> quotationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectProposalId")
    private Collection<EstimateCost> estimateCostCollection;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Service serviceId;

    public ProjectProposal() {
    }

    public ProjectProposal(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProposalName() {
        return proposalName;
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @XmlTransient
    public Collection<ProjectDetails> getProjectDetailsCollection() {
        return projectDetailsCollection;
    }

    public void setProjectDetailsCollection(Collection<ProjectDetails> projectDetailsCollection) {
        this.projectDetailsCollection = projectDetailsCollection;
    }

    @XmlTransient
    public Collection<Agreement> getAgreementCollection() {
        return agreementCollection;
    }

    public void setAgreementCollection(Collection<Agreement> agreementCollection) {
        this.agreementCollection = agreementCollection;
    }

    @XmlTransient
    public Collection<Quotation> getQuotationCollection() {
        return quotationCollection;
    }

    public void setQuotationCollection(Collection<Quotation> quotationCollection) {
        this.quotationCollection = quotationCollection;
    }

    @XmlTransient
    public Collection<EstimateCost> getEstimateCostCollection() {
        return estimateCostCollection;
    }

    public void setEstimateCostCollection(Collection<EstimateCost> estimateCostCollection) {
        this.estimateCostCollection = estimateCostCollection;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof ProjectProposal)) {
            return false;
        }
        ProjectProposal other = (ProjectProposal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.ProjectProposal[ id=" + id + " ]";
    }
    
}
