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
@Table(name = "project_process")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectProcess.findAll", query = "SELECT p FROM ProjectProcess p"),
    @NamedQuery(name = "ProjectProcess.findById", query = "SELECT p FROM ProjectProcess p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectProcess.findByProcess", query = "SELECT p FROM ProjectProcess p WHERE p.process = :process"),
    @NamedQuery(name = "ProjectProcess.findByDescription", query = "SELECT p FROM ProjectProcess p WHERE p.description = :description"),
    @NamedQuery(name = "ProjectProcess.findByDuration", query = "SELECT p FROM ProjectProcess p WHERE p.duration = :duration"),
    @NamedQuery(name = "ProjectProcess.findByIsComplete", query = "SELECT p FROM ProjectProcess p WHERE p.isComplete = :isComplete")})
public class ProjectProcess implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "process")
    private String process;
    @Column(name = "description")
    private String description;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "is_complete")
    private Boolean isComplete;
    @JoinColumn(name = "project_details_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProjectDetails projectDetailsId;
    @JoinColumn(name = "added_by", referencedColumnName = "sysuser_id")
    @ManyToOne(optional = false)
    private SysUser addedBy;

    public ProjectProcess() {
    }

    public ProjectProcess(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
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
        if (!(object instanceof ProjectProcess)) {
            return false;
        }
        ProjectProcess other = (ProjectProcess) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertec.hibe.model.ProjectProcess[ id=" + id + " ]";
    }
    
}
