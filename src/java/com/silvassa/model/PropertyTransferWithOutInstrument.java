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
@Table(name="property_transfer_without_instrument")
public class PropertyTransferWithOutInstrument {
    @Id
    @Column(name = "pf_transfer_id_withot_instrumnent")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_of_notice")
    private String dateOfNotice;
    @Column(name = "entryInChiefOficerBook")
    private String entryInChiefOficerBook;
    @Column(name = "transferedPerson")
    private String transferedPerson;
    @Column(name = "consistSituation")
    private String consistSituation;
    @Column(name = "assessment_book_no")
    private String assessmentBookNo;
    @Column(name = "chief_officer_no")
    private String chiefOfficerNo;
    @Column(name = "dimension_of_land")
    private String dimensionOfLand;
    @Column(name = "boudries")
    private String boundries;
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
     * @return the entryInChiefOficerBook
     */
    public String getEntryInChiefOficerBook() {
        return entryInChiefOficerBook;
    }

    /**
     * @param entryInChiefOficerBook the entryInChiefOficerBook to set
     */
    public void setEntryInChiefOficerBook(String entryInChiefOficerBook) {
        this.entryInChiefOficerBook = entryInChiefOficerBook;
    }

    /**
     * @return the transferedPerson
     */
    public String getTransferedPerson() {
        return transferedPerson;
    }

    /**
     * @param transferedPerson the transferedPerson to set
     */
    public void setTransferedPerson(String transferedPerson) {
        this.transferedPerson = transferedPerson;
    }

    /**
     * @return the consistSituation
     */
    public String getConsistSituation() {
        return consistSituation;
    }

    /**
     * @param consistSituation the consistSituation to set
     */
    public void setConsistSituation(String consistSituation) {
        this.consistSituation = consistSituation;
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
