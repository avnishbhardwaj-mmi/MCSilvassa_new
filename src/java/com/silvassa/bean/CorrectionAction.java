/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.bean;

/**
 *
 * @author CEINFO
 */
public class CorrectionAction {
    String correctionRemarks;
    String correctionStatus;
    String propertyId;
    String applicationNo;

    public String getCorrectionRemarks() {
        return correctionRemarks;
    }

    public void setCorrectionRemarks(String correctionRemarks) {
        this.correctionRemarks = correctionRemarks;
    }

    public String getCorrectionStatus() {
        return correctionStatus;
    }

    public void setCorrectionStatus(String correctionStatus) {
        this.correctionStatus = correctionStatus;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    @Override
    public String toString() {
        return "CorrectionAction{" + "correctionRemarks=" + correctionRemarks + ", correctionStatus=" + correctionStatus + ", propertyId=" + propertyId + ", applicationNo=" + applicationNo + '}';
    }
    
    
}
