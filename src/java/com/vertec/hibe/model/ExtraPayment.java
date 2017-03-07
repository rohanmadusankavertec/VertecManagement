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
 * @author vertec-r
 */
@Entity
@Table(name = "extra_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtraPayment.findAll", query = "SELECT e FROM ExtraPayment e"),
    @NamedQuery(name = "ExtraPayment.findById", query = "SELECT e FROM ExtraPayment e WHERE e.id = :id"),
    @NamedQuery(name = "ExtraPayment.findByAmount", query = "SELECT e FROM ExtraPayment e WHERE e.amount = :amount"),
    @NamedQuery(name = "ExtraPayment.findByDescription", query = "SELECT e FROM ExtraPayment e WHERE e.description = :description")})
public class ExtraPayment implements Serializable {

    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Invoice invoiceId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "agreement_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Agreement agreementId;

    public ExtraPayment() {
    }

    public ExtraPayment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Agreement getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Agreement agreementId) {
        this.agreementId = agreementId;
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
        if (!(object instanceof ExtraPayment)) {
            return false;
        }
        ExtraPayment other = (ExtraPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.ExtraPayment[ id=" + id + " ]";
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }
    
}
