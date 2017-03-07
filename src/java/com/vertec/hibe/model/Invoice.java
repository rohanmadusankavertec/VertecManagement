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
 * @author Java-Dev-Ruchira
 */
@Entity
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id"),
    @NamedQuery(name = "Invoice.findByInvoiceNo", query = "SELECT i FROM Invoice i WHERE i.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "Invoice.findByDate", query = "SELECT i FROM Invoice i WHERE i.date = :date"),
    @NamedQuery(name = "Invoice.findByTotal", query = "SELECT i FROM Invoice i WHERE i.total = :total"),
    @NamedQuery(name = "Invoice.findByDiscount", query = "SELECT i FROM Invoice i WHERE i.discount = :discount"),
    @NamedQuery(name = "Invoice.findByIsAmount", query = "SELECT i FROM Invoice i WHERE i.isAmount = :isAmount"),
    @NamedQuery(name = "Invoice.findByTotAfterDiscount", query = "SELECT i FROM Invoice i WHERE i.totAfterDiscount = :totAfterDiscount"),
    @NamedQuery(name = "Invoice.findByOutstanding", query = "SELECT i FROM Invoice i WHERE i.outstanding = :outstanding")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "is_amount")
    private Boolean isAmount;
    @Column(name = "tot_after_discount")
    private Double totAfterDiscount;
    @Column(name = "outstanding")
    private Double outstanding;
    @JoinColumn(name = "added_by", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser addedBy;
    @JoinColumn(name = "installment_id", referencedColumnName = "id")
    @ManyToOne
    private Installment installmentId;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;
    @JoinColumn(name = "business_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Business businessId;

    public Invoice() {
    }

    public Invoice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getIsAmount() {
        return isAmount;
    }

    public void setIsAmount(Boolean isAmount) {
        this.isAmount = isAmount;
    }

    public Double getTotAfterDiscount() {
        return totAfterDiscount;
    }

    public void setTotAfterDiscount(Double totAfterDiscount) {
        this.totAfterDiscount = totAfterDiscount;
    }

    public Double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Double outstanding) {
        this.outstanding = outstanding;
    }

    public SysUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(SysUser addedBy) {
        this.addedBy = addedBy;
    }

    public Installment getInstallmentId() {
        return installmentId;
    }

    public void setInstallmentId(Installment installmentId) {
        this.installmentId = installmentId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Business getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Business businessId) {
        this.businessId = businessId;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.Invoice[ id=" + id + " ]";
    }
    
}
