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
 * @author Ruchira
 */
@Entity
@Table(name = "actual_cost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActualCost.findAll", query = "SELECT a FROM ActualCost a"),
    @NamedQuery(name = "ActualCost.findById", query = "SELECT a FROM ActualCost a WHERE a.id = :id"),
    @NamedQuery(name = "ActualCost.findByYear", query = "SELECT a FROM ActualCost a WHERE a.year = :year"),
    @NamedQuery(name = "ActualCost.findByMonth", query = "SELECT a FROM ActualCost a WHERE a.month = :month"),
    @NamedQuery(name = "ActualCost.findByAmount", query = "SELECT a FROM ActualCost a WHERE a.amount = :amount"),
    @NamedQuery(name = "ActualCost.findByDate", query = "SELECT a FROM ActualCost a WHERE a.date = :date"),
    @NamedQuery(name = "ActualCost.findByReferenceNo", query = "SELECT a FROM ActualCost a WHERE a.referenceNo = :referenceNo"),
    @NamedQuery(name = "ActualCost.findByDescription", query = "SELECT a FROM ActualCost a WHERE a.description = :description")})
public class ActualCost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "year")
    private String year;
    @Column(name = "month")
    private String month;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "reference_no")
    private String referenceNo;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "sys_user_id", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser sysUserId;

    public ActualCost() {
    }

    public ActualCost(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SysUser getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(SysUser sysUserId) {
        this.sysUserId = sysUserId;
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
        if (!(object instanceof ActualCost)) {
            return false;
        }
        ActualCost other = (ActualCost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.ActualCost[ id=" + id + " ]";
    }
    
}
