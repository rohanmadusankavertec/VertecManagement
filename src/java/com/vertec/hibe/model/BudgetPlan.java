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
 * @author Rohan Madusanka @Contact 071 - 9085504 @E-mail
 * rohanmadusanka72@gmail.com
 */
@Entity
@Table(name = "budget_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BudgetPlan.findAll", query = "SELECT b FROM BudgetPlan b"),
    @NamedQuery(name = "BudgetPlan.findById", query = "SELECT b FROM BudgetPlan b WHERE b.id = :id"),
    @NamedQuery(name = "BudgetPlan.findByYear", query = "SELECT b FROM BudgetPlan b WHERE b.year = :year"),
    @NamedQuery(name = "BudgetPlan.findByMonth", query = "SELECT b FROM BudgetPlan b WHERE b.month = :month"),
    @NamedQuery(name = "BudgetPlan.findByAmount", query = "SELECT b FROM BudgetPlan b WHERE b.amount = :amount"),
    @NamedQuery(name = "BudgetPlan.findByDate", query = "SELECT b FROM BudgetPlan b WHERE b.date = :date")})
public class BudgetPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "year")
    private String year;
    @Column(name = "month")
    private String month;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "cost_center_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CostCenter costCenterId;
    @JoinColumn(name = "nominal_code_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private NominalCode nominalCodeId;
    @JoinColumn(name = "sys_user_id", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser sysUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budgetPlanId")
    private Collection<UpdateLog> updateLogCollection;

    public BudgetPlan() {
    }

    public BudgetPlan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CostCenter getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(CostCenter costCenterId) {
        this.costCenterId = costCenterId;
    }

    public NominalCode getNominalCodeId() {
        return nominalCodeId;
    }

    public void setNominalCodeId(NominalCode nominalCodeId) {
        this.nominalCodeId = nominalCodeId;
    }

    public SysUser getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(SysUser sysUserId) {
        this.sysUserId = sysUserId;
    }

    @XmlTransient
    public Collection<UpdateLog> getUpdateLogCollection() {
        return updateLogCollection;
    }

    public void setUpdateLogCollection(Collection<UpdateLog> updateLogCollection) {
        this.updateLogCollection = updateLogCollection;
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
        if (!(object instanceof BudgetPlan)) {
            return false;
        }
        BudgetPlan other = (BudgetPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.BudgetPlan[ id=" + id + " ]";
    }
    
}
