/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.hibe.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruchira
 */
@Entity
@Table(name = "quotation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quotation.findAll", query = "SELECT q FROM Quotation q"),
    @NamedQuery(name = "Quotation.findById", query = "SELECT q FROM Quotation q WHERE q.id = :id"),
    @NamedQuery(name = "Quotation.findByDate", query = "SELECT q FROM Quotation q WHERE q.date = :date"),
    @NamedQuery(name = "Quotation.findByAmount", query = "SELECT q FROM Quotation q WHERE q.amount = :amount")})
public class Quotation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @JoinColumn(name = "quotation_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private QuotationStatus quotationStatusId;
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Package packageId;
    @JoinColumn(name = "project_proposal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProjectProposal projectProposalId;
    @JoinColumn(name = "created_by", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser createdBy;

    public Quotation() {
    }

    public Quotation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public QuotationStatus getQuotationStatusId() {
        return quotationStatusId;
    }

    public void setQuotationStatusId(QuotationStatus quotationStatusId) {
        this.quotationStatusId = quotationStatusId;
    }

    public Package getPackageId() {
        return packageId;
    }

    public void setPackageId(Package packageId) {
        this.packageId = packageId;
    }

    public ProjectProposal getProjectProposalId() {
        return projectProposalId;
    }

    public void setProjectProposalId(ProjectProposal projectProposalId) {
        this.projectProposalId = projectProposalId;
    }

    public SysUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SysUser createdBy) {
        this.createdBy = createdBy;
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
        if (!(object instanceof Quotation)) {
            return false;
        }
        Quotation other = (Quotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.Quotation[ id=" + id + " ]";
    }
    
}
