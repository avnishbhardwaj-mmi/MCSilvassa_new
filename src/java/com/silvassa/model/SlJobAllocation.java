/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "sl_job_allocation")

public class SlJobAllocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sl_job_allocation_job_allo_id_seq",
            sequenceName = "sl_job_allocation_job_allo_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sl_job_allocation_job_allo_id_seq")
    @Column(name = "job_allo_id", nullable = false)
    private Integer jobAlloId;
    @Column(name = "allocate_to")
    private String allocateTo;
    @Column(name = "allocate_by")
    private String allocateBy;
    @Column(name = "zone_id")
    private String zoneId;
    @Column(name = "ward_no")
    private String wardNo;
    @Column(name = "active")
    private String active;

    @Column(name = "id_count")
    private String counts;

    @Column(name = "job_id")
    String jobID;

    @Column(name = "properties_ids")
    private String propertyIds;

    public SlJobAllocation() {
    }

    public SlJobAllocation(Integer jobAlloId) {
        this.jobAlloId = jobAlloId;
    }

    public SlJobAllocation(Integer jobAlloId, String allocateTo) {
        this.jobAlloId = jobAlloId;
        this.allocateTo = allocateTo;
    }

    public Integer getJobAlloId() {
        return jobAlloId;
    }

    public void setJobAlloId(Integer jobAlloId) {
        this.jobAlloId = jobAlloId;
    }

    public String getAllocateTo() {
        return allocateTo;
    }

    public void setAllocateTo(String allocateTo) {
        this.allocateTo = allocateTo;
    }

    public String getAllocateBy() {
        return allocateBy;
    }

    public void setAllocateBy(String allocateBy) {
        this.allocateBy = allocateBy;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getPropertyIds() {
        return propertyIds;
    }

    public void setPropertyIds(String propertyIds) {
        this.propertyIds = propertyIds;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    @Override
    public String toString() {
        return "SlJobAllocation{" + "jobAlloId=" + jobAlloId + ", allocateTo=" + allocateTo + ", allocateBy=" + allocateBy + ", zoneId=" + zoneId + ", wardNo=" + wardNo + ", active=" + active + ", counts=" + counts + ", jobID=" + jobID + ", propertyIds=" + propertyIds + '}';
    }

}
