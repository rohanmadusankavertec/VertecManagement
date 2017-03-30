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
 * @author Ruchira
 */
@Entity
@Table(name = "function")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Function1.findAll", query = "SELECT f FROM Function1 f"),
    @NamedQuery(name = "Function1.findById", query = "SELECT f FROM Function1 f WHERE f.id = :id"),
    @NamedQuery(name = "Function1.findByName", query = "SELECT f FROM Function1 f WHERE f.name = :name"),
    @NamedQuery(name = "Function1.findByIsvalid", query = "SELECT f FROM Function1 f WHERE f.isvalid = :isvalid")})
public class Function1 implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "functionId")
    private Collection<CostCenter> costCenterCollection;
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State stateId;

    public Function1() {
    }

    public Function1(Integer id) {
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

    @XmlTransient
    public Collection<CostCenter> getCostCenterCollection() {
        return costCenterCollection;
    }

    public void setCostCenterCollection(Collection<CostCenter> costCenterCollection) {
        this.costCenterCollection = costCenterCollection;
    }

    public State getStateId() {
        return stateId;
    }

    public void setStateId(State stateId) {
        this.stateId = stateId;
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
        if (!(object instanceof Function1)) {
            return false;
        }
        Function1 other = (Function1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.Function1[ id=" + id + " ]";
    }
    
}
