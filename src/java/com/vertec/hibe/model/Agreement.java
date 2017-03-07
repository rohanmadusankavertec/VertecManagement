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
 * @author vertec-r
 */
@Entity
@Table(name = "agreement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agreement.findAll", query = "SELECT a FROM Agreement a"),
    @NamedQuery(name = "Agreement.findById", query = "SELECT a FROM Agreement a WHERE a.id = :id"),
    @NamedQuery(name = "Agreement.findByFromDate", query = "SELECT a FROM Agreement a WHERE a.fromDate = :fromDate"),
    @NamedQuery(name = "Agreement.findByToDate", query = "SELECT a FROM Agreement a WHERE a.toDate = :toDate"),
    @NamedQuery(name = "Agreement.findByRequirement", query = "SELECT a FROM Agreement a WHERE a.requirement = :requirement"),
    @NamedQuery(name = "Agreement.findByAmount", query = "SELECT a FROM Agreement a WHERE a.amount = :amount"),
    @NamedQuery(name = "Agreement.findByCreatedDate", query = "SELECT a FROM Agreement a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "Agreement.findBySignedDate", query = "SELECT a FROM Agreement a WHERE a.signedDate = :signedDate")})
public class Agreement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Column(name = "requirement")
    private String requirement;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "signed_date")
    @Temporal(TemporalType.DATE)
    private Date signedDate;
    @JoinColumn(name = "project_proposal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProjectProposal projectProposalId;
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Quotation quotationId;
    @JoinColumn(name = "added_by", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser addedBy;

    public Agreement() {
    }

    public Agreement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
    }

    public ProjectProposal getProjectProposalId() {
        return projectProposalId;
    }

    public void setProjectProposalId(ProjectProposal projectProposalId) {
        this.projectProposalId = projectProposalId;
    }

    public Quotation getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Quotation quotationId) {
        this.quotationId = quotationId;
    }

    public SysUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(SysUser addedBy) {
        this.addedBy = addedBy;
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
        if (!(object instanceof Agreement)) {
            return false;
        }
        Agreement other = (Agreement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.Agreement[ id=" + id + " ]";
    }
    
}
