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
 * @author Rohan Madusanka @Contact 071 - 9085504 @E-mail
 * rohanmadusanka72@gmail.com
 */
@Entity
@Table(name = "cost_center")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CostCenter.findAll", query = "SELECT c FROM CostCenter c"),
    @NamedQuery(name = "CostCenter.findById", query = "SELECT c FROM CostCenter c WHERE c.id = :id"),
    @NamedQuery(name = "CostCenter.findByName", query = "SELECT c FROM CostCenter c WHERE c.name = :name"),
    @NamedQuery(name = "CostCenter.findByIsvalid", query = "SELECT c FROM CostCenter c WHERE c.isvalid = :isvalid"),
    @NamedQuery(name = "CostCenter.findByCode", query = "SELECT c FROM CostCenter c WHERE c.code = :code")})
public class CostCenter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "isvalid")
    private Boolean isvalid;
    @Column(name = "code")
    private String code;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "costCenterId")
    private Collection<ActualCost> actualCostCollection;
    @JoinColumn(name = "function_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FunctionData functionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "costCenterId")
    private Collection<BudgetPlan> budgetPlanCollection;

    public CostCenter() {
    }

    public CostCenter(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Boolean isvalid) {
        this.isvalid = isvalid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Collection<ActualCost> getActualCostCollection() {
        return actualCostCollection;
    }

    public void setActualCostCollection(Collection<ActualCost> actualCostCollection) {
        this.actualCostCollection = actualCostCollection;
    }

    public FunctionData getFunctionId() {
        return functionId;
    }

    public void setFunctionId(FunctionData functionId) {
        this.functionId = functionId;
    }

    @XmlTransient
    public Collection<BudgetPlan> getBudgetPlanCollection() {
        return budgetPlanCollection;
    }

    public void setBudgetPlanCollection(Collection<BudgetPlan> budgetPlanCollection) {
        this.budgetPlanCollection = budgetPlanCollection;
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
        if (!(object instanceof CostCenter)) {
            return false;
        }
        CostCenter other = (CostCenter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.CostCenter[ id=" + id + " ]";
    }
    
}
