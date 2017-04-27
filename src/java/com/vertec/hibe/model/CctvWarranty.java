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
@Table(name = "cctv_warranty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CctvWarranty.findAll", query = "SELECT c FROM CctvWarranty c"),
    @NamedQuery(name = "CctvWarranty.findById", query = "SELECT c FROM CctvWarranty c WHERE c.id = :id"),
    @NamedQuery(name = "CctvWarranty.findByCamera", query = "SELECT c FROM CctvWarranty c WHERE c.camera = :camera"),
    @NamedQuery(name = "CctvWarranty.findByDvr", query = "SELECT c FROM CctvWarranty c WHERE c.dvr = :dvr"),
    @NamedQuery(name = "CctvWarranty.findByHardDisk", query = "SELECT c FROM CctvWarranty c WHERE c.hardDisk = :hardDisk"),
    @NamedQuery(name = "CctvWarranty.findByMonitor", query = "SELECT c FROM CctvWarranty c WHERE c.monitor = :monitor"),
    @NamedQuery(name = "CctvWarranty.findByCable", query = "SELECT c FROM CctvWarranty c WHERE c.cable = :cable"),
    @NamedQuery(name = "CctvWarranty.findByInstallation", query = "SELECT c FROM CctvWarranty c WHERE c.installation = :installation"),
    @NamedQuery(name = "CctvWarranty.findByStPayment", query = "SELECT c FROM CctvWarranty c WHERE c.stPayment = :stPayment"),
    @NamedQuery(name = "CctvWarranty.findByNdPayment", query = "SELECT c FROM CctvWarranty c WHERE c.ndPayment = :ndPayment"),
    @NamedQuery(name = "CctvWarranty.findByRdPayment", query = "SELECT c FROM CctvWarranty c WHERE c.rdPayment = :rdPayment"),
    @NamedQuery(name = "CctvWarranty.findByPreparedBy", query = "SELECT c FROM CctvWarranty c WHERE c.preparedBy = :preparedBy"),
    @NamedQuery(name = "CctvWarranty.findByDesignation", query = "SELECT c FROM CctvWarranty c WHERE c.designation = :designation")})
public class CctvWarranty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "camera")
    private Double camera;
    @Column(name = "dvr")
    private Double dvr;
    @Column(name = "hard_disk")
    private Double hardDisk;
    @Column(name = "monitor")
    private Double monitor;
    @Column(name = "cable")
    private Double cable;
    @Column(name = "installation")
    private Double installation;
    @Column(name = "1st_payment")
    private Double stPayment;
    @Column(name = "2nd_payment")
    private Double ndPayment;
    @Column(name = "3rd_payment")
    private Double rdPayment;
    @Size(max = 200)
    @Column(name = "prepared_by")
    private String preparedBy;
    @Size(max = 200)
    @Column(name = "designation")
    private String designation;
    @JoinColumn(name = "cctv_quotation_info_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CctvQuotationInfo cctvQuotationInfoId;

    public CctvWarranty() {
    }

    public CctvWarranty(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCamera() {
        return camera;
    }

    public void setCamera(Double camera) {
        this.camera = camera;
    }

    public Double getDvr() {
        return dvr;
    }

    public void setDvr(Double dvr) {
        this.dvr = dvr;
    }

    public Double getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(Double hardDisk) {
        this.hardDisk = hardDisk;
    }

    public Double getMonitor() {
        return monitor;
    }

    public void setMonitor(Double monitor) {
        this.monitor = monitor;
    }

    public Double getCable() {
        return cable;
    }

    public void setCable(Double cable) {
        this.cable = cable;
    }

    public Double getInstallation() {
        return installation;
    }

    public void setInstallation(Double installation) {
        this.installation = installation;
    }

    public Double getStPayment() {
        return stPayment;
    }

    public void setStPayment(Double stPayment) {
        this.stPayment = stPayment;
    }

    public Double getNdPayment() {
        return ndPayment;
    }

    public void setNdPayment(Double ndPayment) {
        this.ndPayment = ndPayment;
    }

    public Double getRdPayment() {
        return rdPayment;
    }

    public void setRdPayment(Double rdPayment) {
        this.rdPayment = rdPayment;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public CctvQuotationInfo getCctvQuotationInfoId() {
        return cctvQuotationInfoId;
    }

    public void setCctvQuotationInfoId(CctvQuotationInfo cctvQuotationInfoId) {
        this.cctvQuotationInfoId = cctvQuotationInfoId;
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
        if (!(object instanceof CctvWarranty)) {
            return false;
        }
        CctvWarranty other = (CctvWarranty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.CctvWarranty[ id=" + id + " ]";
    }
    
}
