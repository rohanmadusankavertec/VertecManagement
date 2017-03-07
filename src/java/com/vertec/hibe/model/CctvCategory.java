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
@Table(name = "cctv_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CctvCategory.findAll", query = "SELECT c FROM CctvCategory c"),
    @NamedQuery(name = "CctvCategory.findById", query = "SELECT c FROM CctvCategory c WHERE c.id = :id"),
    @NamedQuery(name = "CctvCategory.findByCategory", query = "SELECT c FROM CctvCategory c WHERE c.category = :category")})
public class CctvCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "category")
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cctvCategoryId")
    private Collection<CctvItems> cctvItemsCollection;

    public CctvCategory() {
    }

    public CctvCategory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlTransient
    public Collection<CctvItems> getCctvItemsCollection() {
        return cctvItemsCollection;
    }

    public void setCctvItemsCollection(Collection<CctvItems> cctvItemsCollection) {
        this.cctvItemsCollection = cctvItemsCollection;
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
        if (!(object instanceof CctvCategory)) {
            return false;
        }
        CctvCategory other = (CctvCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.CctvCategory[ id=" + id + " ]";
    }
    
}
