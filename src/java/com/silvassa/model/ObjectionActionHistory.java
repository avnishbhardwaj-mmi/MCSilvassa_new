/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import java.util.Date;
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
@Table(name = "obj_action_history")
public class ObjectionActionHistory {

    @Id
    @SequenceGenerator(name = "obj_action_history_sno_seq", sequenceName = "obj_action_history_sno_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obj_action_history_sno_seq")
    @Column(name = "sno")
    private Integer sno;
    @Column(name = "objection_id")
    private String objectionId;
    @Column(name = "property_id")
    private String propertyId;
    @Column(name = "action_by")
    private String actionBy;
    @Column(name = "action_by_name")
    private String actionByName;
    @Column(name = "action_taken")
    private String actionTaken;
    @Column(name = "forward_to")
    private String forwardTo;
    @Column(name = "forward_to_name")
    private String forwardToName;
    @Column(name = "action_remarks")
    private String actionRemarks;
    @Column(name = "entrydatetime")
    private Date entrydatetime;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getObjectionId() {
        return objectionId;
    }

    public void setObjectionId(String objectionId) {
        this.objectionId = objectionId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy;
    }

    public String getActionByName() {
        return actionByName;
    }

    public void setActionByName(String actionByName) {
        this.actionByName = actionByName;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getForwardTo() {
        return forwardTo;
    }

    public void setForwardTo(String forwardTo) {
        this.forwardTo = forwardTo;
    }

    public String getForwardToName() {
        return forwardToName;
    }

    public void setForwardToName(String forwardToName) {
        this.forwardToName = forwardToName;
    }

    public String getActionRemarks() {
        return actionRemarks;
    }

    public void setActionRemarks(String actionRemarks) {
        this.actionRemarks = actionRemarks;
    }

    public Date getEntrydatetime() {
        return entrydatetime;
    }

    public void setEntrydatetime(Date entrydatetime) {
        this.entrydatetime = entrydatetime;
    }

    @Override
    public String toString() {
        return "ObjectionActionHistory{" + "sno=" + sno + ", objectionId=" + objectionId + ", propertyId=" + propertyId + ", actionBy=" + actionBy + ", actionByName=" + actionByName + ", actionTaken=" + actionTaken + ", forwardTo=" + forwardTo + ", forwardToName=" + forwardToName + ", actionRemarks=" + actionRemarks + ", entrydatetime=" + entrydatetime + '}';
    }

    
}
