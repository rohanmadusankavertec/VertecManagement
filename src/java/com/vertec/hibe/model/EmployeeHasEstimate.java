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
@Table(name = "employee_has_estimate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeHasEstimate.findAll", query = "SELECT e FROM EmployeeHasEstimate e"),
    @NamedQuery(name = "EmployeeHasEstimate.findById", query = "SELECT e FROM EmployeeHasEstimate e WHERE e.id = :id"),
    @NamedQuery(name = "EmployeeHasEstimate.findByFromDate", query = "SELECT e FROM EmployeeHasEstimate e WHERE e.fromDate = :fromDate"),
    @NamedQuery(name = "EmployeeHasEstimate.findByToDate", query = "SELECT e FROM EmployeeHasEstimate e WHERE e.toDate = :toDate")})
public class EmployeeHasEstimate implements Serializable {
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
    @JoinColumn(name = "estimate_cost_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstimateCost estimateCostId;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee employeeId;

    public EmployeeHasEstimate() {
    }

    public EmployeeHasEstimate(Integer id) {
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

    public EstimateCost getEstimateCostId() {
        return estimateCostId;
    }

    public void setEstimateCostId(EstimateCost estimateCostId) {
        this.estimateCostId = estimateCostId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof EmployeeHasEstimate)) {
            return false;
        }
        EmployeeHasEstimate other = (EmployeeHasEstimate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.EmployeeHasEstimate[ id=" + id + " ]";
    }
    
}
