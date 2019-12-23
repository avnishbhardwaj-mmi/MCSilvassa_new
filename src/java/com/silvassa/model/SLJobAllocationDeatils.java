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
@Table(name = "sl_job_allocation_cm")
public class SLJobAllocationDeatils implements Serializable {

//    job_id character varying(100) NOT NULL,
//  property_id character varying(100) NOT NULL,
//  visited_flag character varying(10) NOT NULL default 'N',
//  v_remarks character varying(2000),
    @Id
    @SequenceGenerator(name = "sl_job_allocation_cm_id_seq",
            sequenceName = "sl_job_allocation_cm_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sl_job_allocation_cm_id_seq")
    @Column(name = "id")
    private Integer Id;
    @Column(name = "job_id")
    String jobId;
    @Column(name = "property_id")
    String propertyId;
    @Column(name = "visited_flag")
    String visitedFlag;
    @Column(name = "v_remarks")
    String vRemarks;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getVisitedFlag() {
        return visitedFlag;
    }

    public void setVisitedFlag(String visitedFlag) {
        this.visitedFlag = visitedFlag;
    }

    public String getvRemarks() {
        return vRemarks;
    }

    public void setvRemarks(String vRemarks) {
        this.vRemarks = vRemarks;
    }

    @Override
    public String toString() {
        return "SLJobAllocationDeatils{" + "Id=" + Id + ", jobId=" + jobId + ", propertyId=" + propertyId + ", visitedFlag=" + visitedFlag + ", vRemarks=" + vRemarks + '}';
    }
}
