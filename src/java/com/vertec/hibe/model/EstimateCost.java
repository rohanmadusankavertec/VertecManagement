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
@Table(name = "estimate_cost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstimateCost.findAll", query = "SELECT e FROM EstimateCost e"),
    @NamedQuery(name = "EstimateCost.findById", query = "SELECT e FROM EstimateCost e WHERE e.id = :id"),
    @NamedQuery(name = "EstimateCost.findByElectricityCost", query = "SELECT e FROM EstimateCost e WHERE e.electricityCost = :electricityCost"),
    @NamedQuery(name = "EstimateCost.findByManagementCost", query = "SELECT e FROM EstimateCost e WHERE e.managementCost = :managementCost"),
    @NamedQuery(name = "EstimateCost.findByOtherCost", query = "SELECT e FROM EstimateCost e WHERE e.otherCost = :otherCost"),
    @NamedQuery(name = "EstimateCost.findByEstimateCost", query = "SELECT e FROM EstimateCost e WHERE e.estimateCost = :estimateCost"),
    @NamedQuery(name = "EstimateCost.findByDeveloperCost", query = "SELECT e FROM EstimateCost e WHERE e.developerCost = :developerCost")})
public class EstimateCost implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estimateCostId")
    private Collection<EmployeeHasEstimate> employeeHasEstimateCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "electricity_cost")
    private Double electricityCost;
    @Column(name = "management_cost")
    private Double managementCost;
    @Column(name = "other_cost")
    private Double otherCost;
    @Column(name = "estimate_cost")
    private Double estimateCost;
    @Column(name = "developer_cost")
    private Double developerCost;
    @JoinColumn(name = "project_proposal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProjectProposal projectProposalId;
    @JoinColumn(name = "calculated_by", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser calculatedBy;

    public EstimateCost() {
    }

    public EstimateCost(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(Double electricityCost) {
        this.electricityCost = electricityCost;
    }

    public Double getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(Double managementCost) {
        this.managementCost = managementCost;
    }

    public Double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    public Double getEstimateCost() {
        return estimateCost;
    }

    public void setEstimateCost(Double estimateCost) {
        this.estimateCost = estimateCost;
    }

    public Double getDeveloperCost() {
        return developerCost;
    }

    public void setDeveloperCost(Double developerCost) {
        this.developerCost = developerCost;
    }

    public ProjectProposal getProjectProposalId() {
        return projectProposalId;
    }

    public void setProjectProposalId(ProjectProposal projectProposalId) {
        this.projectProposalId = projectProposalId;
    }

    public SysUser getCalculatedBy() {
        return calculatedBy;
    }

    public void setCalculatedBy(SysUser calculatedBy) {
        this.calculatedBy = calculatedBy;
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
        if (!(object instanceof EstimateCost)) {
            return false;
        }
        EstimateCost other = (EstimateCost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.EstimateCost[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<EmployeeHasEstimate> getEmployeeHasEstimateCollection() {
        return employeeHasEstimateCollection;
    }

    public void setEmployeeHasEstimateCollection(Collection<EmployeeHasEstimate> employeeHasEstimateCollection) {
        this.employeeHasEstimateCollection = employeeHasEstimateCollection;
    }
    
}
