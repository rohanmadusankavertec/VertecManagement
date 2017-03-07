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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ruchira
 */
@Entity
@Table(name = "cctv_quotation_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CctvQuotationInfo.findAll", query = "SELECT c FROM CctvQuotationInfo c"),
    @NamedQuery(name = "CctvQuotationInfo.findById", query = "SELECT c FROM CctvQuotationInfo c WHERE c.id = :id"),
    @NamedQuery(name = "CctvQuotationInfo.findByDate", query = "SELECT c FROM CctvQuotationInfo c WHERE c.date = :date"),
    @NamedQuery(name = "CctvQuotationInfo.findByTotal", query = "SELECT c FROM CctvQuotationInfo c WHERE c.total = :total")})
public class CctvQuotationInfo implements Serializable {

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cctvQuotationInfoId")
    private Collection<CctvQuotationItems> cctvQuotationItemsCollection;
    @JoinColumn(name = "sys_user_sysuser_id", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser sysUserSysuserId;

    public CctvQuotationInfo() {
    }

    public CctvQuotationInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @XmlTransient
    public Collection<CctvQuotationItems> getCctvQuotationItemsCollection() {
        return cctvQuotationItemsCollection;
    }

    public void setCctvQuotationItemsCollection(Collection<CctvQuotationItems> cctvQuotationItemsCollection) {
        this.cctvQuotationItemsCollection = cctvQuotationItemsCollection;
    }

    public SysUser getSysUserSysuserId() {
        return sysUserSysuserId;
    }

    public void setSysUserSysuserId(SysUser sysUserSysuserId) {
        this.sysUserSysuserId = sysUserSysuserId;
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
        if (!(object instanceof CctvQuotationInfo)) {
            return false;
        }
        CctvQuotationInfo other = (CctvQuotationInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.CctvQuotationInfo[ id=" + id + " ]";
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }
    
}
