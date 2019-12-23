/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author CEINFO
 */


public class CorrectionFormReport {

//@GeneratedValue(strategy=GenerationType.IDENTITY)
private String uniqueId;
private String ownerName;
private String ownerFather;
private String ownerContact;
private String ownerEmail;
private String occupierName;
private String occupierFather;
private String occupierContact;
private String occupierEmail;
private String address;
private String electricServiceNo;
private String spouseName;
private String houeNo;
private String buildingName;
private String subLocality;
private String landMark;
private String city;
private String pincode;
private String plotNo;
private String locName;
private String roadName;
private String surveyNo;
private String plotSmc;
private String ownerAadharNo;
private String occupierAadharNo;
private String arrearAmount;
private String smcHoldingNo;
private String ownerSex;
private String occupierSex;
private String propertyOwnerAddress;
private String applicantName;
private String wardNo;
private String applicationNo;
private String noticeDate;
private String permissionData;



    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerFather() {
        return ownerFather;
    }

    public void setOwnerFather(String ownerFather) {
        this.ownerFather = ownerFather;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOccupierName() {
        return occupierName;
    }

    public void setOccupierName(String occupierName) {
        this.occupierName = occupierName;
    }

    public String getOccupierFather() {
        return occupierFather;
    }

    public void setOccupierFather(String occupierFather) {
        this.occupierFather = occupierFather;
    }

    public String getOccupierContact() {
        return occupierContact;
    }

    public void setOccupierContact(String occupierContact) {
        this.occupierContact = occupierContact;
    }

    public String getOccupierEmail() {
        return occupierEmail;
    }

    public void setOccupierEmail(String occupierEmail) {
        this.occupierEmail = occupierEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    

    /**
     * @return the electricServiceNo
     */
    public String getElectricServiceNo() {
        return electricServiceNo;
    }

    /**
     * @param electricServiceNo the electricServiceNo to set
     */
    public void setElectricServiceNo(String electricServiceNo) {
        this.electricServiceNo = electricServiceNo;
    }

    /**
     * @return the spouseName
     */
    public String getSpouseName() {
        return spouseName;
    }

    /**
     * @param spouseName the spouseName to set
     */
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    /**
     * @return the houeNo
     */
    public String getHoueNo() {
        return houeNo;
    }

    /**
     * @param houeNo the houeNo to set
     */
    public void setHoueNo(String houeNo) {
        this.houeNo = houeNo;
    }

    /**
     * @return the buildingName
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * @param buildingName the buildingName to set
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * @return the subLocality
     */
    public String getSubLocality() {
        return subLocality;
    }

    /**
     * @param subLocality the subLocality to set
     */
    public void setSubLocality(String subLocality) {
        this.subLocality = subLocality;
    }

    /**
     * @return the landMark
     */
    public String getLandMark() {
        return landMark;
    }

    /**
     * @param landMark the landMark to set
     */
    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the pincode
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * @param pincode the pincode to set
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    /**
     * @return the plotNo
     */
    public String getPlotNo() {
        return plotNo;
    }

    /**
     * @param plotNo the plotNo to set
     */
    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    /**
     * @return the locName
     */
    public String getLocName() {
        return locName;
    }

    /**
     * @param locName the locName to set
     */
    public void setLocName(String locName) {
        this.locName = locName;
    }

    /**
     * @return the roadName
     */
    public String getRoadName() {
        return roadName;
    }

    /**
     * @param roadName the roadName to set
     */
    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    

    /**
     * @return the surveyNo
     */
    public String getSurveyNo() {
        return surveyNo;
    }

    /**
     * @param surveyNo the surveyNo to set
     */
    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    /**
     * @return the plotSmc
     */
    public String getPlotSmc() {
        return plotSmc;
    }

    /**
     * @param plotSmc the plotSmc to set
     */
    public void setPlotSmc(String plotSmc) {
        this.plotSmc = plotSmc;
    }

    /**
     * @return the ownerAadharNo
     */
    public String getOwnerAadharNo() {
        return ownerAadharNo;
    }

    /**
     * @param ownerAadharNo the ownerAadharNo to set
     */
    public void setOwnerAadharNo(String ownerAadharNo) {
        this.ownerAadharNo = ownerAadharNo;
    }

    /**
     * @return the occupierAadharNo
     */
    public String getOccupierAadharNo() {
        return occupierAadharNo;
    }

    /**
     * @param occupierAadharNo the occupierAadharNo to set
     */
    public void setOccupierAadharNo(String occupierAadharNo) {
        this.occupierAadharNo = occupierAadharNo;
    }

    /**
     * @return the arrearAmount
     */
    public String getArrearAmount() {
        return arrearAmount;
    }

    /**
     * @param arrearAmount the arrearAmount to set
     */
    public void setArrearAmount(String arrearAmount) {
        this.arrearAmount = arrearAmount;
    }

    /**
     * @return the smcHoldingNo
     */
    public String getSmcHoldingNo() {
        return smcHoldingNo;
    }

    /**
     * @param smcHoldingNo the smcHoldingNo to set
     */
    public void setSmcHoldingNo(String smcHoldingNo) {
        this.smcHoldingNo = smcHoldingNo;
    }
@Override
    public String toString() {
        return "CorrectionFormSaveBean{" + "uniqueId=" + uniqueId + ", ownerName=" + ownerName + ", ownerFather=" + ownerFather + ", ownerContact=" + ownerContact + ", ownerEmail=" + ownerEmail + ", occupierName=" + occupierName + ", occupierFather=" + occupierFather + ", occupierContact=" + occupierContact + ", occupierEmail=" + occupierEmail + ", address=" + address + ", electricServiceNo=" + electricServiceNo + ", spouseName=" + spouseName + ", houeNo=" + houeNo + ", buildingName=" + buildingName + ", subLocality=" + subLocality + ", landMark=" + landMark + ", city=" + city + ", pincode=" + pincode + ", plotNo=" + plotNo + ", locName=" + locName + ", roadName=" + roadName + '}';
    }

    /**
     * @return the ownerSex
     */
    public String getOwnerSex() {
        return ownerSex;
    }

    /**
     * @param ownerSex the ownerSex to set
     */
    public void setOwnerSex(String ownerSex) {
        this.ownerSex = ownerSex;
    }

    /**
     * @return the occupierSex
     */
    public String getOccupierSex() {
        return occupierSex;
    }

    /**
     * @param occupierSex the occupierSex to set
     */
    public void setOccupierSex(String occupierSex) {
        this.occupierSex = occupierSex;
    }

    /**
     * @return the propertyOwnerAddress
     */
    public String getPropertyOwnerAddress() {
        return propertyOwnerAddress;
    }

    /**
     * @param propertyOwnerAddress the propertyOwnerAddress to set
     */
    public void setPropertyOwnerAddress(String propertyOwnerAddress) {
        this.propertyOwnerAddress = propertyOwnerAddress;
    }

    /**
     * @return the applicantName
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * @param applicantName the applicantName to set
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    /**
     * @return the wardNo
     */
    public String getWardNo() {
        return wardNo;
    }

    /**
     * @param wardNo the wardNo to set
     */
    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    /**
     * @return the applicationNo
     */
    public String getApplicationNo() {
        return applicationNo;
    }

    /**
     * @param applicationNo the applicationNo to set
     */
    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    /**
     * @return the noticeDate
     */
    public String getNoticeDate() {
        return noticeDate;
    }

    /**
     * @param noticeDate the noticeDate to set
     */
    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    /**
     * @return the permissionData
     */
    public String getPermissionData() {
        return permissionData;
    }

    /**
     * @param permissionData the permissionData to set
     */
    public void setPermissionData(String permissionData) {
        this.permissionData = permissionData;
    }
}
