/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "sl_payment_status_change")
public class PaymentStatus {


    @Id
    @Column(name = "payrefid")
    private String payrefid;
    @Column(name = "taxno")
    private String taxNo;
    @Column(name = "property_id")
    private String propertyId;
    @Column(name = "existing_status")
    private String existingStatus;
    @Column(name = "after_update_status")
    private String afterUpdateStatus;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "is_complete")
    private String isComplete;
    @Column(name = "entrydatetime")
    private Date entrydatetime;


    /**
     * @return the payrefid
     */
    public String getPayrefid() {
        return payrefid;
    }

    /**
     * @param payrefid the payrefid to set
     */
    public void setPayrefid(String payrefid) {
        this.payrefid = payrefid;
    }

    /**
     * @return the taxNo
     */
    public String getTaxNo() {
        return taxNo;
    }

    /**
     * @param taxNo the taxNo to set
     */
    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
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
     * @return the existingStatus
     */
    public String getExistingStatus() {
        return existingStatus;
    }

    /**
     * @param existingStatus the existingStatus to set
     */
    public void setExistingStatus(String existingStatus) {
        this.existingStatus = existingStatus;
    }

    /**
     * @return the afterUpdateStatus
     */
    public String getAfterUpdateStatus() {
        return afterUpdateStatus;
    }

    /**
     * @param afterUpdateStatus the afterUpdateStatus to set
     */
    public void setAfterUpdateStatus(String afterUpdateStatus) {
        this.afterUpdateStatus = afterUpdateStatus;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the isComplete
     */
    public String getIsComplete() {
        return isComplete;
    }

    /**
     * @param isComplete the isComplete to set
     */
    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * @return the entrydatetime
     */
    public Date getEntrydatetime() {
        return entrydatetime;
    }

    /**
     * @param entrydatetime the entrydatetime to set
     */
    public void setEntrydatetime(Date entrydatetime) {
        this.entrydatetime = entrydatetime;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" + "payrefid=" + payrefid + ", taxNo=" + taxNo + ", propertyId=" + propertyId + ", existingStatus=" + existingStatus + ", afterUpdateStatus=" + afterUpdateStatus + ", userId=" + userId + ", ipAddress=" + ipAddress + ", remarks=" + remarks + ", isComplete=" + isComplete + ", entrydatetime=" + entrydatetime + '}';
    }
    
    
    

}
