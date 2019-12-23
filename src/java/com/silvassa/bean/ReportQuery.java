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
public class ReportQuery {
    
    private String zone;
    private String propId;
    private String PAN;
    private String ADHAAR;
    private String propType;
    private String paymentStatus;
    private String arrear;
    private String minTaxAmount;
    private String maxTaxAmount;
    private String objectionFrom;
    private String objectionTo;
    private String objectionStatus;
    private String noticeStatus;
    private String ownerId;
    private String occupierId;
    private String objectionId;
   

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
     * @return the propId
     */
    public String getPropId() {
        return propId;
    }

    /**
     * @param propId the propId to set
     */
    public void setPropId(String propId) {
        this.propId = propId;
    }

    /**
     * @return the PAN
     */
    public String getPAN() {
        return PAN;
    }

    /**
     * @param PAN the PAN to set
     */
    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    /**
     * @return the ADHAAR
     */
    public String getADHAAR() {
        return ADHAAR;
    }

    /**
     * @param ADHAAR the ADHAAR to set
     */
    public void setADHAAR(String ADHAAR) {
        this.ADHAAR = ADHAAR;
    }

    /**
     * @return the propType
     */
    public String getPropType() {
        return propType;
    }

    /**
     * @param propType the propType to set
     */
    public void setPropType(String propType) {
        this.propType = propType;
    }

    /**
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * @return the arrear
     */
    public String getArrear() {
        return arrear;
    }

    /**
     * @param arrear the arrear to set
     */
    public void setArrear(String arrear) {
        this.arrear = arrear;
    }

    /**
     * @return the minTaxAmount
     */
    public String getMinTaxAmount() {
        return minTaxAmount;
    }

    /**
     * @param minTaxAmount the minTaxAmount to set
     */
    public void setMinTaxAmount(String minTaxAmount) {
        this.minTaxAmount = minTaxAmount;
    }

    /**
     * @return the maxTaxAmount
     */
    public String getMaxTaxAmount() {
        return maxTaxAmount;
    }

    /**
     * @param maxTaxAmount the maxTaxAmount to set
     */
    public void setMaxTaxAmount(String maxTaxAmount) {
        this.maxTaxAmount = maxTaxAmount;
    }

    /**
     * @return the objectionFrom
     */
    public String getObjectionFrom() {
        return objectionFrom;
    }

    /**
     * @param objectionFrom the objectionFrom to set
     */
    public void setObjectionFrom(String objectionFrom) {
        this.objectionFrom = objectionFrom;
    }

    /**
     * @return the objectionTo
     */
    public String getObjectionTo() {
        return objectionTo;
    }

    /**
     * @param objectionTo the objectionTo to set
     */
    public void setObjectionTo(String objectionTo) {
        this.objectionTo = objectionTo;
    }

    /**
     * @return the objectionStatus
     */
    public String getObjectionStatus() {
        return objectionStatus;
    }

    /**
     * @param objectionStatus the objectionStatus to set
     */
    public void setObjectionStatus(String objectionStatus) {
        this.objectionStatus = objectionStatus;
    }

    /**
     * @return the noticeStatus
     */
    public String getNoticeStatus() {
        return noticeStatus;
    }

    /**
     * @param noticeStatus the noticeStatus to set
     */
    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOccupierId() {
        return occupierId;
    }

    public void setOccupierId(String occupierId) {
        this.occupierId = occupierId;
    }

    public String getObjectionId() {
        return objectionId;
    }

    public void setObjectionId(String objectionId) {
        this.objectionId = objectionId;
    }

    @Override
    public String toString() {
        return "ReportQuery{" + "zone=" + zone + ", propId=" + propId + ", PAN=" + PAN + ", ADHAAR=" + ADHAAR + ", propType=" + propType + ", paymentStatus=" + paymentStatus + ", arrear=" + arrear + ", minTaxAmount=" + minTaxAmount + ", maxTaxAmount=" + maxTaxAmount + ", objectionFrom=" + objectionFrom + ", objectionTo=" + objectionTo + ", objectionStatus=" + objectionStatus + ", noticeStatus=" + noticeStatus + ", ownerId=" + ownerId + ", occupierId=" + occupierId + ", objectionId=" + objectionId + '}';
    }

   

}
