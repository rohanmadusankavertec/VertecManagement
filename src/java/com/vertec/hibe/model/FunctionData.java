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
@Table(name = "function_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FunctionData.findAll", query = "SELECT f FROM FunctionData f"),
    @NamedQuery(name = "FunctionData.findById", query = "SELECT f FROM FunctionData f WHERE f.id = :id"),
    @NamedQuery(name = "FunctionData.findByName", query = "SELECT f FROM FunctionData f WHERE f.name = :name"),
    @NamedQuery(name = "FunctionData.findByIsvalid", query = "SELECT f FROM FunctionData f WHERE f.isvalid = :isvalid")})
public class FunctionData implements Serializable {

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
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State stateId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "functionId")
    private Collection<CostCenter> costCenterCollection;

    public FunctionData() {
    }

    public FunctionData(Integer id) {
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

    public State getStateId() {
        return stateId;
    }

    public void setStateId(State stateId) {
        this.stateId = stateId;
    }

    @XmlTransient
    public Collection<CostCenter> getCostCenterCollection() {
        return costCenterCollection;
    }

    public void setCostCenterCollection(Collection<CostCenter> costCenterCollection) {
        this.costCenterCollection = costCenterCollection;
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
        if (!(object instanceof FunctionData)) {
            return false;
        }
        FunctionData other = (FunctionData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.FunctionData[ id=" + id + " ]";
    }
    
}
