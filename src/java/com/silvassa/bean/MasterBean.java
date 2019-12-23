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
public class MasterBean {

    private String propertyId;
    private String ownerName;
    private String occupierName;
    private String relation;
    private String address;
    private String zone;
    private String ward;
    private String taxGenerated;
    private String taxAmount;
    private String noticeGenerated;
    private String assessmentYear;
    private String taxNo;
    private String noticeNo;
    private String generatedBy;
    private String generatedOn;
    private String objectionNo;
    private String objectionStatus;
    private String objectionAppliedBy;
    private String objectionRaisedOn;
    private String amountDemand;
    private String amountPaid;
    
    

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
     * @return the ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName the ownerName to set
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**

    /**
     * @return the relation
     */
    public String getRelation() {
        return relation;
    }

    /**
     * @param relation the relation to set
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the zone
     */
    public String getZone() {
        return zone;
    }

    /**
     * @param zone the zone to set
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     * @return the taxGenerated
     */
    public String getTaxGenerated() {
        return taxGenerated;
    }

    /**
     * @param taxGenerated the taxGenerated to set
     */
    public void setTaxGenerated(String taxGenerated) {
        this.taxGenerated = taxGenerated;
    }

    /**
     * @return the taxAmount
     */
    public String getTaxAmount() {
        return taxAmount;
    }

    /**
     * @param taxAmount the taxAmount to set
     */
    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * @return the noticeGenerated
     */
    public String getNoticeGenerated() {
        return noticeGenerated;
    }

    /**
     * @param noticeGenerated the noticeGenerated to set
     */
    public void setNoticeGenerated(String noticeGenerated) {
        this.noticeGenerated = noticeGenerated;
    }

    /**
     * @return the assessmentYear
     */
    public String getAssessmentYear() {
        return assessmentYear;
    }

    /**
     * @param assessmentYear the assessmentYear to set
     */
    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
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
     * @return the noticeNo
     */
    public String getNoticeNo() {
        return noticeNo;
    }

    /**
     * @param noticeNo the noticeNo to set
     */
    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    /**
     * @return the generatedBy
     */
    public String getGeneratedBy() {
        return generatedBy;
    }

    /**
     * @param generatedBy the generatedBy to set
     */
    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    /**
     * @return the generatedOn
     */
    public String getGeneratedOn() {
        return generatedOn;
    }

    /**
     * @param generatedOn the generatedOn to set
     */
    public void setGeneratedOn(String generatedOn) {
        this.generatedOn = generatedOn;
    }
    
    /**
     * @return the occupierName
     */
    public String getOccupierName() {
        return occupierName;
    }

    /**
     * @param occupierName the occupierName to set
     */
    public void setOccupierName(String occupierName) {
        this.occupierName = occupierName;
    }

    public String getObjectionNo() {
        return objectionNo;
    }

    public void setObjectionNo(String objectionNo) {
        this.objectionNo = objectionNo;
    }

    public String getObjectionStatus() {
        return objectionStatus;
    }

    public void setObjectionStatus(String objectionStatus) {
        this.objectionStatus = objectionStatus;
    }

    public String getObjectionAppliedBy() {
        return objectionAppliedBy;
    }

    public void setObjectionAppliedBy(String objectionAppliedBy) {
        this.objectionAppliedBy = objectionAppliedBy;
    }

    public String getObjectionRaisedOn() {
        return objectionRaisedOn;
    }

    public void setObjectionRaisedOn(String objectionRaisedOn) {
        this.objectionRaisedOn = objectionRaisedOn;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAmountDemand() {
        return amountDemand;
    }

    public void setAmountDemand(String amountDemand) {
        this.amountDemand = amountDemand;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }
    
    

    @Override
    public String toString() {
        return "MasterBean{" + "propertyId=" + propertyId + ", ownerName=" + ownerName + ", occupierName=" + occupierName + ", relation=" + relation + ", address=" + address + ", zone=" + zone + ", taxGenerated=" + taxGenerated + ", taxAmount=" + taxAmount + ", noticeGenerated=" + noticeGenerated + ", assessmentYear=" + assessmentYear + ", taxNo=" + taxNo + ", noticeNo=" + noticeNo + ", generatedBy=" + generatedBy + ", generatedOn=" + generatedOn + ", objectionNo=" + objectionNo + ", objectionStatus=" + objectionStatus + ", objectionAppliedBy=" + objectionAppliedBy + ", objectionRaisedOn=" + objectionRaisedOn + '}';
    }
    

}
