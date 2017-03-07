/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.hibe.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vertec-r
 */
@Entity
@Table(name = "project_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectDetails.findAll", query = "SELECT p FROM ProjectDetails p"),
    @NamedQuery(name = "ProjectDetails.findById", query = "SELECT p FROM ProjectDetails p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectDetails.findByFromDate", query = "SELECT p FROM ProjectDetails p WHERE p.fromDate = :fromDate"),
    @NamedQuery(name = "ProjectDetails.findByToDate", query = "SELECT p FROM ProjectDetails p WHERE p.toDate = :toDate")})
public class ProjectDetails implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectDetailsId")
    private Collection<ProjectExpenses> projectExpensesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectDetailsId")
    private Collection<ProjectHasEmployee> projectHasEmployeeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectDetailsId")
    private Collection<Issues> issuesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectDetailsId")
    private Collection<ProjectProcess> projectProcessCollection;
    @Column(name = "remark")
    private String remark;
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
    @JoinColumn(name = "project_proposal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProjectProposal projectProposalId;
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status statusId;
    @JoinColumn(name = "added_by", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser addedBy;

    public ProjectDetails() {
    }

    public ProjectDetails(Integer id) {
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

    public ProjectProposal getProjectProposalId() {
        return projectProposalId;
    }

    public void setProjectProposalId(ProjectProposal projectProposalId) {
        this.projectProposalId = projectProposalId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
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
        if (!(object instanceof ProjectDetails)) {
            return false;
        }
        ProjectDetails other = (ProjectDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.ProjectDetails[ id=" + id + " ]";
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @XmlTransient
    public Collection<ProjectProcess> getProjectProcessCollection() {
        return projectProcessCollection;
    }

    public void setProjectProcessCollection(Collection<ProjectProcess> projectProcessCollection) {
        this.projectProcessCollection = projectProcessCollection;
    }

    @XmlTransient
    public Collection<ProjectExpenses> getProjectExpensesCollection() {
        return projectExpensesCollection;
    }

    public void setProjectExpensesCollection(Collection<ProjectExpenses> projectExpensesCollection) {
        this.projectExpensesCollection = projectExpensesCollection;
    }

    @XmlTransient
    public Collection<ProjectHasEmployee> getProjectHasEmployeeCollection() {
        return projectHasEmployeeCollection;
    }

    public void setProjectHasEmployeeCollection(Collection<ProjectHasEmployee> projectHasEmployeeCollection) {
        this.projectHasEmployeeCollection = projectHasEmployeeCollection;
    }

    @XmlTransient
    public Collection<Issues> getIssuesCollection() {
        return issuesCollection;
    }

    public void setIssuesCollection(Collection<Issues> issuesCollection) {
        this.issuesCollection = issuesCollection;
    }
    
}
