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
 * @author vertec-r
 */
@Entity
@Table(name = "sys_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysUser.findAll", query = "SELECT s FROM SysUser s"),
    @NamedQuery(name = "SysUser.findBySysuserId", query = "SELECT s FROM SysUser s WHERE s.sysuserId = :sysuserId"),
    @NamedQuery(name = "SysUser.findByUsername", query = "SELECT s FROM SysUser s WHERE s.username = :username"),
    @NamedQuery(name = "SysUser.findByPassword", query = "SELECT s FROM SysUser s WHERE s.password = :password"),
    @NamedQuery(name = "SysUser.findByFirstName", query = "SELECT s FROM SysUser s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "SysUser.findByLastName", query = "SELECT s FROM SysUser s WHERE s.lastName = :lastName"),
    @NamedQuery(name = "SysUser.findByEmailAdd", query = "SELECT s FROM SysUser s WHERE s.emailAdd = :emailAdd"),
    @NamedQuery(name = "SysUser.findByContactNo", query = "SELECT s FROM SysUser s WHERE s.contactNo = :contactNo"),
    @NamedQuery(name = "SysUser.findByNicNo", query = "SELECT s FROM SysUser s WHERE s.nicNo = :nicNo"),
    @NamedQuery(name = "SysUser.findByDob", query = "SELECT s FROM SysUser s WHERE s.dob = :dob"),
    @NamedQuery(name = "SysUser.findByAddedDate", query = "SELECT s FROM SysUser s WHERE s.addedDate = :addedDate"),
    @NamedQuery(name = "SysUser.findByAddedBy", query = "SELECT s FROM SysUser s WHERE s.addedBy = :addedBy"),
    @NamedQuery(name = "SysUser.findByLastUpdatedDate", query = "SELECT s FROM SysUser s WHERE s.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "SysUser.findByLastUpdatedBy", query = "SELECT s FROM SysUser s WHERE s.lastUpdatedBy = :lastUpdatedBy"),
    @NamedQuery(name = "SysUser.findByIsActive", query = "SELECT s FROM SysUser s WHERE s.isActive = :isActive")})
public class SysUser implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sysUserSysuserId")
    private Collection<CctvQuotationInfo> cctvQuotationInfoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addedBy")
    private Collection<Invoice> invoiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<Quotation> quotationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addedBy")
    private Collection<Agreement> agreementCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sysuser_id")
    private Integer sysuserId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_add")
    private String emailAdd;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "nic_no")
    private String nicNo;
    @Column(name = "dob")
    private String dob;
    @Column(name = "added_date")
    @Temporal(TemporalType.DATE)
    private Date addedDate;
    @Column(name = "added_by")
    private Integer addedBy;
    @Column(name = "last_updated_date")
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;
    @Column(name = "last_updated_by")
    private Integer lastUpdatedBy;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addedBy")
    private Collection<ProjectProcess> projectProcessCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addedBy")
    private Collection<Issues> issuesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calculatedBy")
    private Collection<EstimateCost> estimateCostCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addedBy")
    private Collection<ProjectDetails> projectDetailsCollection;
    @JoinColumn(name = "user_group_id", referencedColumnName = "user_group_id")
    @ManyToOne(optional = false)
    private UserGroup userGroupId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receivedBy")
    private Collection<Payment> paymentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addedBy")
    private Collection<Customer> customerCollection;

    private transient String loginStatus;
    
    public SysUser() {
    }
public String getLoginStatus() {
        return loginStatus;
    }

 public SysUser(Integer sysuserId, String password) {
        this.sysuserId = sysuserId;
        this.password = password;
    }
    public SysUser(String username, String password, boolean isActive, String firstName, String lastName, String emailAdd, String contactNo, String nicNo, String dob, Date addedDate, Integer addedBy, Date lastUpdatedDate, Integer lastUpdatedBy, UserGroup userGroupId) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
        this.contactNo = contactNo;
        this.nicNo = nicNo;
        this.dob = dob;
        this.addedDate = addedDate;
        this.addedBy = addedBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.userGroupId = userGroupId;
    }
//public SysUser(String username, String password, boolean isActive, String firstName, String lastName, String emailAdd, String contactNo, String nicNo, String dob, Date addedDate, Integer addedBy, Date lastUpdatedDate, Integer lastUpdatedBy, UserGroup userGroupId) {
//        this.username = username;
//        this.password = password;
//        this.isActive = isActive;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.emailAdd = emailAdd;
//        this.contactNo = contactNo;
//        this.nicNo = nicNo;
//        this.dob = dob;
//        this.addedDate = addedDate;
//        this.addedBy = addedBy;
//        this.lastUpdatedDate = lastUpdatedDate;
//        this.lastUpdatedBy = lastUpdatedBy;
//        this.userGroupId = userGroupId;
//    }
    public SysUser(Integer sysuserId, String firstName, String lastName, String emailAdd, String contactNo, String nicNo, String dob, Date lastUpdatedDate, Integer lastUpdatedBy) {
        this.sysuserId = sysuserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
        this.contactNo = contactNo;
        this.nicNo = nicNo;
        this.dob = dob;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    public SysUser(Integer sysuserId, String firstName, String lastName, String emailAdd, String contactNo, String nicNo, String dob, Date lastUpdatedDate, Integer lastUpdatedBy,UserGroup ug) {
        this.sysuserId = sysuserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
        this.contactNo = contactNo;
        this.nicNo = nicNo;
        this.dob = dob;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.userGroupId=ug;
    }
    

    public SysUser(Integer sysuserId, String username, String password, boolean isActive, String nicNo) {
        this.sysuserId = sysuserId;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.nicNo = nicNo;
    }
    /**
     * @param loginStatus the loginStatus to set
     */
    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
    public SysUser(Integer sysuserId) {
        this.sysuserId = sysuserId;
    }

    public Integer getSysuserId() {
        return sysuserId;
    }

    public void setSysuserId(Integer sysuserId) {
        this.sysuserId = sysuserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Integer getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<ProjectProcess> getProjectProcessCollection() {
        return projectProcessCollection;
    }

    public void setProjectProcessCollection(Collection<ProjectProcess> projectProcessCollection) {
        this.projectProcessCollection = projectProcessCollection;
    }

    @XmlTransient
    public Collection<Issues> getIssuesCollection() {
        return issuesCollection;
    }

    public void setIssuesCollection(Collection<Issues> issuesCollection) {
        this.issuesCollection = issuesCollection;
    }

    @XmlTransient
    public Collection<EstimateCost> getEstimateCostCollection() {
        return estimateCostCollection;
    }

    public void setEstimateCostCollection(Collection<EstimateCost> estimateCostCollection) {
        this.estimateCostCollection = estimateCostCollection;
    }

    @XmlTransient
    public Collection<ProjectDetails> getProjectDetailsCollection() {
        return projectDetailsCollection;
    }

    public void setProjectDetailsCollection(Collection<ProjectDetails> projectDetailsCollection) {
        this.projectDetailsCollection = projectDetailsCollection;
    }

    public UserGroup getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(UserGroup userGroupId) {
        this.userGroupId = userGroupId;
    }

    @XmlTransient
    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }


    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sysuserId != null ? sysuserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SysUser)) {
            return false;
        }
        SysUser other = (SysUser) object;
        if ((this.sysuserId == null && other.sysuserId != null) || (this.sysuserId != null && !this.sysuserId.equals(other.sysuserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.SysUser[ sysuserId=" + sysuserId + " ]";
    }

    @XmlTransient
    public Collection<Agreement> getAgreementCollection() {
        return agreementCollection;
    }

    public void setAgreementCollection(Collection<Agreement> agreementCollection) {
        this.agreementCollection = agreementCollection;
    }

    @XmlTransient
    public Collection<Quotation> getQuotationCollection() {
        return quotationCollection;
    }

    public void setQuotationCollection(Collection<Quotation> quotationCollection) {
        this.quotationCollection = quotationCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @XmlTransient
    public Collection<CctvQuotationInfo> getCctvQuotationInfoCollection() {
        return cctvQuotationInfoCollection;
    }

    public void setCctvQuotationInfoCollection(Collection<CctvQuotationInfo> cctvQuotationInfoCollection) {
        this.cctvQuotationInfoCollection = cctvQuotationInfoCollection;
    }
    
}
