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
@Table(name = "software_advance_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SoftwareAdvanceDetails.findAll", query = "SELECT s FROM SoftwareAdvanceDetails s"),
    @NamedQuery(name = "SoftwareAdvanceDetails.findById", query = "SELECT s FROM SoftwareAdvanceDetails s WHERE s.id = :id"),
    @NamedQuery(name = "SoftwareAdvanceDetails.findByFirstPayment", query = "SELECT s FROM SoftwareAdvanceDetails s WHERE s.firstPayment = :firstPayment"),
    @NamedQuery(name = "SoftwareAdvanceDetails.findBySecondPayment", query = "SELECT s FROM SoftwareAdvanceDetails s WHERE s.secondPayment = :secondPayment"),
    @NamedQuery(name = "SoftwareAdvanceDetails.findByThirdPayment", query = "SELECT s FROM SoftwareAdvanceDetails s WHERE s.thirdPayment = :thirdPayment"),
    @NamedQuery(name = "SoftwareAdvanceDetails.findByInstallment", query = "SELECT s FROM SoftwareAdvanceDetails s WHERE s.installment = :installment"),
    @NamedQuery(name = "SoftwareAdvanceDetails.findByMaintenace", query = "SELECT s FROM SoftwareAdvanceDetails s WHERE s.maintenace = :maintenace"),
    @NamedQuery(name = "SoftwareAdvanceDetails.findByPlusMaintenace", query = "SELECT s FROM SoftwareAdvanceDetails s WHERE s.plusMaintenace = :plusMaintenace"),
    @NamedQuery(name = "SoftwareAdvanceDetails.findByPreparedBy", query = "SELECT s FROM SoftwareAdvanceDetails s WHERE s.preparedBy = :preparedBy"),
    @NamedQuery(name = "SoftwareAdvanceDetails.findByDesignatioa", query = "SELECT s FROM SoftwareAdvanceDetails s WHERE s.designatioa = :designatioa")})
public class SoftwareAdvanceDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "first_payment")
    private Double firstPayment;
    @Column(name = "second_payment")
    private Double secondPayment;
    @Column(name = "third_payment")
    private Double thirdPayment;
    @Column(name = "installment")
    private Double installment;
    @Column(name = "maintenace")
    private Double maintenace;
    @Column(name = "plus_maintenace")
    private Double plusMaintenace;
    @Size(max = 45)
    @Column(name = "prepared_by")
    private String preparedBy;
    @Size(max = 45)
    @Column(name = "designatioa")
    private String designatioa;
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Quotation quotationId;

    public SoftwareAdvanceDetails() {
    }

    public SoftwareAdvanceDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(Double firstPayment) {
        this.firstPayment = firstPayment;
    }

    public Double getSecondPayment() {
        return secondPayment;
    }

    public void setSecondPayment(Double secondPayment) {
        this.secondPayment = secondPayment;
    }

    public Double getThirdPayment() {
        return thirdPayment;
    }

    public void setThirdPayment(Double thirdPayment) {
        this.thirdPayment = thirdPayment;
    }

    public Double getInstallment() {
        return installment;
    }

    public void setInstallment(Double installment) {
        this.installment = installment;
    }

    public Double getMaintenace() {
        return maintenace;
    }

    public void setMaintenace(Double maintenace) {
        this.maintenace = maintenace;
    }

    public Double getPlusMaintenace() {
        return plusMaintenace;
    }

    public void setPlusMaintenace(Double plusMaintenace) {
        this.plusMaintenace = plusMaintenace;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getDesignatioa() {
        return designatioa;
    }

    public void setDesignatioa(String designatioa) {
        this.designatioa = designatioa;
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
        if (!(object instanceof SoftwareAdvanceDetails)) {
            return false;
        }
        SoftwareAdvanceDetails other = (SoftwareAdvanceDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.SoftwareAdvanceDetails[ id=" + id + " ]";
    }
    
}
