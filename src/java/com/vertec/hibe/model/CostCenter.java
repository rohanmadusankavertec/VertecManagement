/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertec.hibe.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruchira
 */
@Entity
@Table(name = "cost_center")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CostCenter.findAll", query = "SELECT c FROM CostCenter c"),
    @NamedQuery(name = "CostCenter.findById", query = "SELECT c FROM CostCenter c WHERE c.id = :id"),
    @NamedQuery(name = "CostCenter.findByName", query = "SELECT c FROM CostCenter c WHERE c.name = :name"),
    @NamedQuery(name = "CostCenter.findByIsvalid", query = "SELECT c FROM CostCenter c WHERE c.isvalid = :isvalid")})
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
    private String isvalid;
    @JoinColumn(name = "function_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FunctionData functionId;

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

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public FunctionData getFunctionId() {
        return functionId;
    }

    public void setFunctionId(FunctionData functionId) {
        this.functionId = functionId;
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
