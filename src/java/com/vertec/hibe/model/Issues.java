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
@Table(name = "issues")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Issues.findAll", query = "SELECT i FROM Issues i"),
    @NamedQuery(name = "Issues.findById", query = "SELECT i FROM Issues i WHERE i.id = :id"),
    @NamedQuery(name = "Issues.findByIssue", query = "SELECT i FROM Issues i WHERE i.issue = :issue"),
    @NamedQuery(name = "Issues.findByIsFixed", query = "SELECT i FROM Issues i WHERE i.isFixed = :isFixed")})
public class Issues implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "issue")
    private String issue;
    @Column(name = "is_fixed")
    private Boolean isFixed;
    @JoinColumn(name = "project_details_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProjectDetails projectDetailsId;
    @JoinColumn(name = "added_by", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser addedBy;

    public Issues() {
    }

    public Issues(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Boolean getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Boolean isFixed) {
        this.isFixed = isFixed;
    }

    public ProjectDetails getProjectDetailsId() {
        return projectDetailsId;
    }

    public void setProjectDetailsId(ProjectDetails projectDetailsId) {
        this.projectDetailsId = projectDetailsId;
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
        if (!(object instanceof Issues)) {
            return false;
        }
        Issues other = (Issues) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.Issues[ id=" + id + " ]";
    }
    
}
