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
 * @author vertec-r
 */
@Entity
@Table(name = "payment_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentDetails.findAll", query = "SELECT p FROM PaymentDetails p"),
    @NamedQuery(name = "PaymentDetails.findById", query = "SELECT p FROM PaymentDetails p WHERE p.id = :id"),
    @NamedQuery(name = "PaymentDetails.findByBankName", query = "SELECT p FROM PaymentDetails p WHERE p.bankName = :bankName"),
    @NamedQuery(name = "PaymentDetails.findByChequeNo", query = "SELECT p FROM PaymentDetails p WHERE p.chequeNo = :chequeNo"),
    @NamedQuery(name = "PaymentDetails.findByChequeDate", query = "SELECT p FROM PaymentDetails p WHERE p.chequeDate = :chequeDate"),
    @NamedQuery(name = "PaymentDetails.findByIsValid", query = "SELECT p FROM PaymentDetails p WHERE p.isValid = :isValid")})
public class PaymentDetails implements Serializable {
    @OneToMany(mappedBy = "paymentDetailsId")
    private Collection<Payment> paymentCollection;
    @Column(name = "account_no")
    private String accountNo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "cheque_no")
    private String chequeNo;
    @Column(name = "cheque_date")
    @Temporal(TemporalType.DATE)
    private Date chequeDate;
    @Column(name = "is_valid")
    private Boolean isValid;

    public PaymentDetails() {
    }

    public PaymentDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
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
        if (!(object instanceof PaymentDetails)) {
            return false;
        }
        PaymentDetails other = (PaymentDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.PaymentDetails[ id=" + id + " ]";
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @XmlTransient
    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }
    
}
