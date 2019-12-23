/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name="property_transfer_with_instrument")
public class PropertyTransferWithInstrument {
    @Id
    @Column(name = "pf_transfer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_of_notice")
    private String dateOfNotice;
    @Column(name = "date_of_instrument")
    private String dateOfInstrument;
    @Column(name = "vendor_name")
    private String vendorName;
    @Column(name = "purchaser_name")
    private String purchaserName;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "consist")
    private String consists;
    @Column(name = "situation")
    private String situations;
    @Column(name = "assessment_book_no")
    private String assessmentBookNo;
    @Column(name = "chief_officer_no")
    private String chiefOfficerNo;
    @Column(name = "dimension_of_land")
    private String dimensionOfLand;
    @Column(name = "boudries")
    private String boundries;
    @Column(name = "registation_date")
    private String dateOfRegistration;
    @Column(name = "remarks")
    private String remarks;
    @Column(name="request_date")
    private String requestDate;
    @Column(name="property_unique_id")
    private String propertyUniqueId;
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dateOfNotice
     */
    public String getDateOfNotice() {
        return dateOfNotice;
    }

    /**
     * @param dateOfNotice the dateOfNotice to set
     */
    public void setDateOfNotice(String dateOfNotice) {
        this.dateOfNotice = dateOfNotice;
    }

    /**
     * @return the dateOfInstrument
     */
    public String getDateOfInstrument() {
        return dateOfInstrument;
    }

    /**
     * @param dateOfInstrument the dateOfInstrument to set
     */
    public void setDateOfInstrument(String dateOfInstrument) {
        this.dateOfInstrument = dateOfInstrument;
    }

    /**
     * @return the vendorName
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * @param vendorName the vendorName to set
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * @return the purchaserName
     */
    public String getPurchaserName() {
        return purchaserName;
    }

    /**
     * @param purchaserName the purchaserName to set
     */
    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    /**
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return the consists
     */
    public String getConsists() {
        return consists;
    }

    /**
     * @param consists the consists to set
     */
    public void setConsists(String consists) {
        this.consists = consists;
    }

    /**
     * @return the situations
     */
    public String getSituations() {
        return situations;
    }

    /**
     * @param situations the situations to set
     */
    public void setSituations(String situations) {
        this.situations = situations;
    }

    /**
     * @return the assessmentBookNo
     */
    public String getAssessmentBookNo() {
        return assessmentBookNo;
    }

    /**
     * @param assessmentBookNo the assessmentBookNo to set
     */
    public void setAssessmentBookNo(String assessmentBookNo) {
        this.assessmentBookNo = assessmentBookNo;
    }

    /**
     * @return the chiefOfficerNo
     */
    public String getChiefOfficerNo() {
        return chiefOfficerNo;
    }

    /**
     * @param chiefOfficerNo the chiefOfficerNo to set
     */
    public void setChiefOfficerNo(String chiefOfficerNo) {
        this.chiefOfficerNo = chiefOfficerNo;
    }

    /**
     * @return the dimensionOfLand
     */
    public String getDimensionOfLand() {
        return dimensionOfLand;
    }

    /**
     * @param dimensionOfLand the dimensionOfLand to set
     */
    public void setDimensionOfLand(String dimensionOfLand) {
        this.dimensionOfLand = dimensionOfLand;
    }

    /**
     * @return the boundries
     */
    public String getBoundries() {
        return boundries;
    }

    /**
     * @param boundries the boundries to set
     */
    public void setBoundries(String boundries) {
        this.boundries = boundries;
    }

    /**
     * @return the dateOfRegistration
     */
    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    /**
     * @param dateOfRegistration the dateOfRegistration to set
     */
    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
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
     * @return the requestDate
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the propertyUniqueId
     */
    public String getPropertyUniqueId() {
        return propertyUniqueId;
    }

    /**
     * @param propertyUniqueId the propertyUniqueId to set
     */
    public void setPropertyUniqueId(String propertyUniqueId) {
        this.propertyUniqueId = propertyUniqueId;
    }
}
