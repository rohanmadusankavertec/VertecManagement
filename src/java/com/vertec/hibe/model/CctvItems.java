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
@Table(name = "cctv_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CctvItems.findAll", query = "SELECT c FROM CctvItems c"),
    @NamedQuery(name = "CctvItems.findById", query = "SELECT c FROM CctvItems c WHERE c.id = :id"),
    @NamedQuery(name = "CctvItems.findByName", query = "SELECT c FROM CctvItems c WHERE c.name = :name"),
    @NamedQuery(name = "CctvItems.findByPrice", query = "SELECT c FROM CctvItems c WHERE c.price = :price")})
public class CctvItems implements Serializable {

    @Column(name = "is_valid")
    private Boolean isValid;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cctvItemsId")
    private Collection<CctvQuotationItems> cctvQuotationItemsCollection;
    @JoinColumn(name = "cctv_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CctvCategory cctvCategoryId;

    public CctvItems() {
    }

    public CctvItems(Integer id) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<CctvQuotationItems> getCctvQuotationItemsCollection() {
        return cctvQuotationItemsCollection;
    }

    public void setCctvQuotationItemsCollection(Collection<CctvQuotationItems> cctvQuotationItemsCollection) {
        this.cctvQuotationItemsCollection = cctvQuotationItemsCollection;
    }

    public CctvCategory getCctvCategoryId() {
        return cctvCategoryId;
    }

    public void setCctvCategoryId(CctvCategory cctvCategoryId) {
        this.cctvCategoryId = cctvCategoryId;
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
        if (!(object instanceof CctvItems)) {
            return false;
        }
        CctvItems other = (CctvItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.CctvItems[ id=" + id + " ]";
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
    
}
