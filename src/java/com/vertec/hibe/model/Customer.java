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
 * @author vertec-r
 */
@Entity
@Table(name = "customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id"),
    @NamedQuery(name = "Customer.findByFirstName", query = "SELECT c FROM Customer c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Customer.findByLastName", query = "SELECT c FROM Customer c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Customer.findByCompanyName", query = "SELECT c FROM Customer c WHERE c.companyName = :companyName"),
    @NamedQuery(name = "Customer.findByMobileNo", query = "SELECT c FROM Customer c WHERE c.mobileNo = :mobileNo"),
    @NamedQuery(name = "Customer.findByOffiiceNo", query = "SELECT c FROM Customer c WHERE c.offiiceNo = :offiiceNo"),
    @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM Customer c WHERE c.address = :address"),
    @NamedQuery(name = "Customer.findByFax", query = "SELECT c FROM Customer c WHERE c.fax = :fax"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByIsValid", query = "SELECT c FROM Customer c WHERE c.isValid = :isValid")})
public class Customer implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<CctvQuotationInfo> cctvQuotationInfoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<HardwareQuotation> hardwareQuotationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Invoice> invoiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<ProjectProposal> projectProposalCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "offiice_no")
    private String offiiceNo;
    @Column(name = "address")
    private String address;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;
    @Column(name = "is_valid")
    private Boolean isValid;
    @JoinColumn(name = "added_by", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser addedBy;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getOffiiceNo() {
        return offiiceNo;
    }

    public void setOffiiceNo(String offiiceNo) {
        this.offiiceNo = offiiceNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public SysUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(SysUser addedBy) {
        this.addedBy = addedBy;
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.Customer[ id=" + id + " ]";
    }


    @XmlTransient
    public Collection<ProjectProposal> getProjectProposalCollection() {
        return projectProposalCollection;
    }

    public void setProjectProposalCollection(Collection<ProjectProposal> projectProposalCollection) {
        this.projectProposalCollection = projectProposalCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @XmlTransient
    public Collection<HardwareQuotation> getHardwareQuotationCollection() {
        return hardwareQuotationCollection;
    }

    public void setHardwareQuotationCollection(Collection<HardwareQuotation> hardwareQuotationCollection) {
        this.hardwareQuotationCollection = hardwareQuotationCollection;
    }

    @XmlTransient
    public Collection<CctvQuotationInfo> getCctvQuotationInfoCollection() {
        return cctvQuotationInfoCollection;
    }

    public void setCctvQuotationInfoCollection(Collection<CctvQuotationInfo> cctvQuotationInfoCollection) {
        this.cctvQuotationInfoCollection = cctvQuotationInfoCollection;
    }
    
}
