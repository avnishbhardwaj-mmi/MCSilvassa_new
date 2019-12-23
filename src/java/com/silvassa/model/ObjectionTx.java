/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "tx_objection")
public class ObjectionTx {

    @Id
    @Column(name = "objectionId")
    private String objectionId;
    @Column(name = "propertyId")
    private String propertyId;
    @Column(name = "effectingTAX")
    private String effectingTAX;
    @Column(name = "appliedBy")
    private String appliedBy;
    @Column(name = "appliedByIdType")
    private String appliedByIdType;
    @Column(name = "appliedById")
    private String appliedById;
    @Column(name = "raisedBy")
    private String raisedBy;
    @Column(name = "raisedOn")
    private String raisedOn;
    @Column(name = "raisedByremarks")
    private String raisedByremarks;
    @Column(name = "resolvedBy")
    private String resolvedBy;
    @Column(name = "resolvedOn")
    private String resolvedOn;
    @Column(name = "resolvedByremarks")
    private String resolvedByremarks;
    @Column(name = "status")
    private String status;
    @Column(name = "entrydatetime")
    private Date entrydatetime;

    //Added By Jay
    @Column(name = "relationifother")
    private String relationifother;
    @Column(name = "relation")
    private String relation;
    //Added By Jay End

    @Transient
    private List<ObjectionBean> objectionBeans;

    /**
     * @return the objectionId
     */
    public String getObjectionId() {
        return objectionId;
    }

    /**
     * @param objectionId the objectionId to set
     */
    public void setObjectionId(String objectionId) {
        this.objectionId = objectionId;
    }

    /**
     * @return the propertyId
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * @param propertyId the propertyId to set
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * @return the effectingTAX
     */
    public String getEffectingTAX() {
        return effectingTAX;
    }

    /**
     * @param effectingTAX the effectingTAX to set
     */
    public void setEffectingTAX(String effectingTAX) {
        this.effectingTAX = effectingTAX;
    }

    /**
     * @return the appliedBy
     */
    public String getAppliedBy() {
        return appliedBy;
    }

    /**
     * @param appliedBy the appliedBy to set
     */
    public void setAppliedBy(String appliedBy) {
        this.appliedBy = appliedBy;
    }

    /**
     * @return the appliedByIdType
     */
    public String getAppliedByIdType() {
        return appliedByIdType;
    }

    /**
     * @param appliedByIdType the appliedByIdType to set
     */
    public void setAppliedByIdType(String appliedByIdType) {
        this.appliedByIdType = appliedByIdType;
    }

    /**
     * @return the appliedById
     */
    public String getAppliedById() {
        return appliedById;
    }

    /**
     * @param appliedById the appliedById to set
     */
    public void setAppliedById(String appliedById) {
        this.appliedById = appliedById;
    }

    /**
     * @return the raisedBy
     */
    public String getRaisedBy() {
        return raisedBy;
    }

    /**
     * @param raisedBy the raisedBy to set
     */
    public void setRaisedBy(String raisedBy) {
        this.raisedBy = raisedBy;
    }

    /**
     * @return the raisedOn
     */
    public String getRaisedOn() {
        return raisedOn;
    }

    /**
     * @param raisedOn the raisedOn to set
     */
    public void setRaisedOn(String raisedOn) {
        this.raisedOn = raisedOn;
    }

    /**
     * @return the raisedByremarks
     */
    public String getRaisedByremarks() {
        return raisedByremarks;
    }

    /**
     * @param raisedByremarks the raisedByremarks to set
     */
    public void setRaisedByremarks(String raisedByremarks) {
        this.raisedByremarks = raisedByremarks;
    }

    /**
     * @return the resolvedBy
     */
    public String getResolvedBy() {
        return resolvedBy;
    }

    /**
     * @param resolvedBy the resolvedBy to set
     */
    public void setResolvedBy(String resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    /**
     * @return the resolvedOn
     */
    public String getResolvedOn() {
        return resolvedOn;
    }

    /**
     * @param resolvedOn the resolvedOn to set
     */
    public void setResolvedOn(String resolvedOn) {
        this.resolvedOn = resolvedOn;
    }

    /**
     * @return the resolvedByremarks
     */
    public String getResolvedByremarks() {
        return resolvedByremarks;
    }

    /**
     * @param resolvedByremarks the resolvedByremarks to set
     */
    public void setResolvedByremarks(String resolvedByremarks) {
        this.resolvedByremarks = resolvedByremarks;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the objectionBeans
     */
    public List<ObjectionBean> getObjectionBeans() {
        return objectionBeans;
    }

    /**
     * @param objectionBeans the objectionBeans to set
     */
    public void setObjectionBeans(List<ObjectionBean> objectionBeans) {
        this.objectionBeans = objectionBeans;
    }

    public Date getEntrydatetime() {
        return entrydatetime;
    }

    public void setEntrydatetime(Date entrydatetime) {
        this.entrydatetime = entrydatetime;
    }

    //Added By Jay 
    public String getRelationifother() {
        return relationifother;
    }

    public void setRelationifother(String relationifother) {
        this.relationifother = relationifother;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    //Added By Jay End
    @Override
    public String toString() {
        return "ObjectionTx{" + "objectionId=" + objectionId + ", propertyId=" + propertyId + ", effectingTAX=" + effectingTAX + ", appliedBy=" + appliedBy + ", appliedByIdType=" + appliedByIdType + ", appliedById=" + appliedById + ", raisedBy=" + raisedBy + ", raisedOn=" + raisedOn + ", raisedByremarks=" + raisedByremarks + ", resolvedBy=" + resolvedBy + ", resolvedOn=" + resolvedOn + ", resolvedByremarks=" + resolvedByremarks + ", status=" + status + ", entrydatetime=" + entrydatetime + ", objectionBeans=" + objectionBeans + '}';
    }

}
