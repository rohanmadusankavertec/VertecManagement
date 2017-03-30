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
@Table(name = "update_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UpdateLog.findAll", query = "SELECT u FROM UpdateLog u"),
    @NamedQuery(name = "UpdateLog.findById", query = "SELECT u FROM UpdateLog u WHERE u.id = :id"),
    @NamedQuery(name = "UpdateLog.findByDate", query = "SELECT u FROM UpdateLog u WHERE u.date = :date"),
    @NamedQuery(name = "UpdateLog.findByBeforeAmount", query = "SELECT u FROM UpdateLog u WHERE u.beforeAmount = :beforeAmount"),
    @NamedQuery(name = "UpdateLog.findByDescription", query = "SELECT u FROM UpdateLog u WHERE u.description = :description"),
    @NamedQuery(name = "UpdateLog.findByAfterAmount", query = "SELECT u FROM UpdateLog u WHERE u.afterAmount = :afterAmount")})
public class UpdateLog implements Serializable {

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
    @Column(name = "before_amount")
    private Double beforeAmount;
    @Column(name = "description")
    private String description;
    @Column(name = "after_amount")
    private Double afterAmount;
    @JoinColumn(name = "before_user", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser beforeUser;
    @JoinColumn(name = "after_user", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser afterUser;
    @JoinColumn(name = "budget_plan_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BudgetPlan budgetPlanId;

    public UpdateLog() {
    }

    public UpdateLog(Integer id) {
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

    public Double getBeforeAmount() {
        return beforeAmount;
    }

    public void setBeforeAmount(Double beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAfterAmount() {
        return afterAmount;
    }

    public void setAfterAmount(Double afterAmount) {
        this.afterAmount = afterAmount;
    }

    public SysUser getBeforeUser() {
        return beforeUser;
    }

    public void setBeforeUser(SysUser beforeUser) {
        this.beforeUser = beforeUser;
    }

    public SysUser getAfterUser() {
        return afterUser;
    }

    public void setAfterUser(SysUser afterUser) {
        this.afterUser = afterUser;
    }

    public BudgetPlan getBudgetPlanId() {
        return budgetPlanId;
    }

    public void setBudgetPlanId(BudgetPlan budgetPlanId) {
        this.budgetPlanId = budgetPlanId;
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
        if (!(object instanceof UpdateLog)) {
            return false;
        }
        UpdateLog other = (UpdateLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.UpdateLog[ id=" + id + " ]";
    }
    
}
