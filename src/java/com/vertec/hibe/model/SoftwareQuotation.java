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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruchira
 */
@Entity
@Table(name = "software_quotation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SoftwareQuotation.findAll", query = "SELECT s FROM SoftwareQuotation s"),
    @NamedQuery(name = "SoftwareQuotation.findById", query = "SELECT s FROM SoftwareQuotation s WHERE s.id = :id"),
    @NamedQuery(name = "SoftwareQuotation.findByDescription", query = "SELECT s FROM SoftwareQuotation s WHERE s.description = :description"),
    @NamedQuery(name = "SoftwareQuotation.findByQty", query = "SELECT s FROM SoftwareQuotation s WHERE s.qty = :qty"),
    @NamedQuery(name = "SoftwareQuotation.findByPrice", query = "SELECT s FROM SoftwareQuotation s WHERE s.price = :price")})
public class SoftwareQuotation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @Column(name = "qty")
    private Integer qty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Quotation quotationId;

    public SoftwareQuotation() {
    }

    public SoftwareQuotation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Quotation getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Quotation quotationId) {
        this.quotationId = quotationId;
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
        if (!(object instanceof SoftwareQuotation)) {
            return false;
        }
        SoftwareQuotation other = (SoftwareQuotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.SoftwareQuotation[ id=" + id + " ]";
    }
    
}
