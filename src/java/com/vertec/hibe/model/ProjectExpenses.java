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
@Table(name = "project_expenses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectExpenses.findAll", query = "SELECT p FROM ProjectExpenses p"),
    @NamedQuery(name = "ProjectExpenses.findById", query = "SELECT p FROM ProjectExpenses p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectExpenses.findByDescription", query = "SELECT p FROM ProjectExpenses p WHERE p.description = :description"),
    @NamedQuery(name = "ProjectExpenses.findByAmount", query = "SELECT p FROM ProjectExpenses p WHERE p.amount = :amount")})
public class ProjectExpenses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @JoinColumn(name = "project_details_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProjectDetails projectDetailsId;

    public ProjectExpenses() {
    }

    public ProjectExpenses(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ProjectDetails getProjectDetailsId() {
        return projectDetailsId;
    }

    public void setProjectDetailsId(ProjectDetails projectDetailsId) {
        this.projectDetailsId = projectDetailsId;
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
        if (!(object instanceof ProjectExpenses)) {
            return false;
        }
        ProjectExpenses other = (ProjectExpenses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.ProjectExpenses[ id=" + id + " ]";
    }
    
}
