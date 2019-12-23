/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author CEINFO
 */
public class CorrectionRequestBean {

    private String uniqueId;
    private int id;
    private String wardNo;
    private String noticeNo;
    private String noticeDate;
    private String ownerName;
    private String ownerFather;
    private String ownerContact;
    private String ownerEmail;
    private String occupierName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String occupierFather;
    private String occupierContact;
    private String occupierEmail;
    private String address;
    private String locationClass;
    private String electricSericeConnectionNo;
    private String applicantName;
    private String applicantMobileNo;
    private String permissionData;
    private String ipAddress;
    private String imageFileName;
    private MultipartFile images;
    private String documentType;
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
    private String checkOwnerName;
    private String checkOwnerFather;
    private String checkSpouseName;
    private String checkOwnerContact;
    private String checkOwnerEmail;
    private String checkOccupierName;
    private String checkOccupierFather;
    private String checkOccupierContact;
    private String checkOccupierEmail;
    private String checkPlotNo;
    private String checkHouseNo;
    private String checkBuildingName;
    private String checkRoadName;
    private String checkSubLocality;
    private String checkLocName;
    private String checkLandMark;
    private String checkElectricSericeConnectionNo;
    
    private String surveyNo;
    private String checkSurveyNo;
    private String plotSmc;
    private String checkPlotSmc;
    private String ownerAadharNo;
    private String checkOwnerAadharNo;
    private String occupierAadharNo;
    private String checkOccupierAadharNo;
    private String arrearAmount;
    private String checkArrear;
    private String smcHoldingNo;
    private String checkSmcHoldingNo;
    private String ownerSex;
    private String occupierSex;
    private String checkOwnerSex;
    private String checkOccupierSex;
    private String checkPropertyOwnerAddress;
    private String propertyOwnerAddress;
            
    public String getUniqueId() {
        return uniqueId;
    }

    public String getWardNo() {
        return wardNo;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerFather() {
        return ownerFather;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getOccupierName() {
        return occupierName;
    }

    public String getOccupierFather() {
        return occupierFather;
    }

    public String getOccupierContact() {
        return occupierContact;
    }

    public String getOccupierEmail() {
        return occupierEmail;
    }

    public String getAddress() {
        return address;
    }

    public String getLocationClass() {
        return locationClass;
    }

    public String getElectricSericeConnectionNo() {
        return electricSericeConnectionNo;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getApplicantMobileNo() {
        return applicantMobileNo;
    }

    public String getPermissionData() {
        return permissionData;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOwnerFather(String ownerFather) {
        this.ownerFather = ownerFather;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setOccupierName(String occupierName) {
        this.occupierName = occupierName;
    }

    public void setOccupierFather(String occupierFather) {
        this.occupierFather = occupierFather;
    }

    public void setOccupierContact(String occupierContact) {
        this.occupierContact = occupierContact;
    }

    public void setOccupierEmail(String occupierEmail) {
        this.occupierEmail = occupierEmail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocationClass(String locationClass) {
        this.locationClass = locationClass;
    }

    public void setElectricSericeConnectionNo(String electricSericeConnectionNo) {
        this.electricSericeConnectionNo = electricSericeConnectionNo;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setApplicantMobileNo(String applicantMobileNo) {
        this.applicantMobileNo = applicantMobileNo;
    }

    public void setPermissionData(String permissionData) {
        this.permissionData = permissionData;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setImages(MultipartFile images) {
        this.images = images;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public MultipartFile getImages() {
        return images;
    }

    
    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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
     * @return the checkOwnerName
     */
    public String getCheckOwnerName() {
        return checkOwnerName;
    }

    /**
     * @param checkOwnerName the checkOwnerName to set
     */
    public void setCheckOwnerName(String checkOwnerName) {
        this.checkOwnerName = checkOwnerName;
    }

    /**
     * @return the checkOwnerFather
     */
    public String getCheckOwnerFather() {
        return checkOwnerFather;
    }

    /**
     * @param checkOwnerFather the checkOwnerFather to set
     */
    public void setCheckOwnerFather(String checkOwnerFather) {
        this.checkOwnerFather = checkOwnerFather;
    }

    /**
     * @return the checkSpouseName
     */
    public String getCheckSpouseName() {
        return checkSpouseName;
    }

    /**
     * @param checkSpouseName the checkSpouseName to set
     */
    public void setCheckSpouseName(String checkSpouseName) {
        this.checkSpouseName = checkSpouseName;
    }

    /**
     * @return the checkOwnerContact
     */
    public String getCheckOwnerContact() {
        return checkOwnerContact;
    }

    /**
     * @param checkOwnerContact the checkOwnerContact to set
     */
    public void setCheckOwnerContact(String checkOwnerContact) {
        this.checkOwnerContact = checkOwnerContact;
    }

    /**
     * @return the checkOwnerEmail
     */
    public String getCheckOwnerEmail() {
        return checkOwnerEmail;
    }

    /**
     * @param checkOwnerEmail the checkOwnerEmail to set
     */
    public void setCheckOwnerEmail(String checkOwnerEmail) {
        this.checkOwnerEmail = checkOwnerEmail;
    }

    /**
     * @return the checkOccupierName
     */
    public String getCheckOccupierName() {
        return checkOccupierName;
    }

    /**
     * @param checkOccupierName the checkOccupierName to set
     */
    public void setCheckOccupierName(String checkOccupierName) {
        this.checkOccupierName = checkOccupierName;
    }

    /**
     * @return the checkOccupierFather
     */
    public String getCheckOccupierFather() {
        return checkOccupierFather;
    }

    /**
     * @param checkOccupierFather the checkOccupierFather to set
     */
    public void setCheckOccupierFather(String checkOccupierFather) {
        this.checkOccupierFather = checkOccupierFather;
    }

    /**
     * @return the checkOccupierContact
     */
    public String getCheckOccupierContact() {
        return checkOccupierContact;
    }

    /**
     * @param checkOccupierContact the checkOccupierContact to set
     */
    public void setCheckOccupierContact(String checkOccupierContact) {
        this.checkOccupierContact = checkOccupierContact;
    }

    /**
     * @return the checkOccupierEmail
     */
    public String getCheckOccupierEmail() {
        return checkOccupierEmail;
    }

    /**
     * @param checkOccupierEmail the checkOccupierEmail to set
     */
    public void setCheckOccupierEmail(String checkOccupierEmail) {
        this.checkOccupierEmail = checkOccupierEmail;
    }

    /**
     * @return the checkPlotNo
     */
    public String getCheckPlotNo() {
        return checkPlotNo;
    }

    /**
     * @param checkPlotNo the checkPlotNo to set
     */
    public void setCheckPlotNo(String checkPlotNo) {
        this.checkPlotNo = checkPlotNo;
    }

    /**
     * @return the checkHouseNo
     */
    public String getCheckHouseNo() {
        return checkHouseNo;
    }

    /**
     * @param checkHouseNo the checkHouseNo to set
     */
    public void setCheckHouseNo(String checkHouseNo) {
        this.checkHouseNo = checkHouseNo;
    }

    /**
     * @return the checkBuildingName
     */
    public String getCheckBuildingName() {
        return checkBuildingName;
    }

    /**
     * @param checkBuildingName the checkBuildingName to set
     */
    public void setCheckBuildingName(String checkBuildingName) {
        this.checkBuildingName = checkBuildingName;
    }

    /**
     * @return the checkRoadName
     */
    public String getCheckRoadName() {
        return checkRoadName;
    }

    /**
     * @param checkRoadName the checkRoadName to set
     */
    public void setCheckRoadName(String checkRoadName) {
        this.checkRoadName = checkRoadName;
    }

    /**
     * @return the checkSubLocality
     */
    public String getCheckSubLocality() {
        return checkSubLocality;
    }

    /**
     * @param checkSubLocality the checkSubLocality to set
     */
    public void setCheckSubLocality(String checkSubLocality) {
        this.checkSubLocality = checkSubLocality;
    }

    /**
     * @return the checkLocName
     */
    public String getCheckLocName() {
        return checkLocName;
    }

    /**
     * @param checkLocName the checkLocName to set
     */
    public void setCheckLocName(String checkLocName) {
        this.checkLocName = checkLocName;
    }

    /**
     * @return the checkLandMark
     */
    public String getCheckLandMark() {
        return checkLandMark;
    }

    /**
     * @param checkLandMark the checkLandMark to set
     */
    public void setCheckLandMark(String checkLandMark) {
        this.checkLandMark = checkLandMark;
    }

    /**
     * @return the checkElectricSericeConnectionNo
     */
    public String getCheckElectricSericeConnectionNo() {
        return checkElectricSericeConnectionNo;
    }

    /**
     * @param checkElectricSericeConnectionNo the checkElectricSericeConnectionNo to set
     */
    public void setCheckElectricSericeConnectionNo(String checkElectricSericeConnectionNo) {
        this.checkElectricSericeConnectionNo = checkElectricSericeConnectionNo;
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
     * @return the checkSurveyNo
     */
    public String getCheckSurveyNo() {
        return checkSurveyNo;
    }

    /**
     * @param checkSurveyNo the checkSurveyNo to set
     */
    public void setCheckSurveyNo(String checkSurveyNo) {
        this.checkSurveyNo = checkSurveyNo;
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
     * @return the checkPlotSmc
     */
    public String getCheckPlotSmc() {
        return checkPlotSmc;
    }

    /**
     * @param checkPlotSmc the checkPlotSmc to set
     */
    public void setCheckPlotSmc(String checkPlotSmc) {
        this.checkPlotSmc = checkPlotSmc;
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
     * @return the checkOwnerAadharNo
     */
    public String getCheckOwnerAadharNo() {
        return checkOwnerAadharNo;
    }

    /**
     * @param checkOwnerAadharNo the checkOwnerAadharNo to set
     */
    public void setCheckOwnerAadharNo(String checkOwnerAadharNo) {
        this.checkOwnerAadharNo = checkOwnerAadharNo;
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
     * @return the checkOccupierAadharNo
     */
    public String getCheckOccupierAadharNo() {
        return checkOccupierAadharNo;
    }

    /**
     * @param checkOccupierAadharNo the checkOccupierAadharNo to set
     */
    public void setCheckOccupierAadharNo(String checkOccupierAadharNo) {
        this.checkOccupierAadharNo = checkOccupierAadharNo;
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
     * @return the checkArrear
     */
    public String getCheckArrear() {
        return checkArrear;
    }

    /**
     * @param checkArrear the checkArrear to set
     */
    public void setCheckArrear(String checkArrear) {
        this.checkArrear = checkArrear;
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

    /**
     * @return the checkSmcHoldingNo
     */
    public String getCheckSmcHoldingNo() {
        return checkSmcHoldingNo;
    }

    /**
     * @param checkSmcHoldingNo the checkSmcHoldingNo to set
     */
    public void setCheckSmcHoldingNo(String checkSmcHoldingNo) {
        this.checkSmcHoldingNo = checkSmcHoldingNo;
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
     * @return the checkOwnerSex
     */
    public String getCheckOwnerSex() {
        return checkOwnerSex;
    }

    /**
     * @param checkOwnerSex the checkOwnerSex to set
     */
    public void setCheckOwnerSex(String checkOwnerSex) {
        this.checkOwnerSex = checkOwnerSex;
    }

    /**
     * @return the checkOccupierSex
     */
    public String getCheckOccupierSex() {
        return checkOccupierSex;
    }

    /**
     * @param checkOccupierSex the checkOccupierSex to set
     */
    public void setCheckOccupierSex(String checkOccupierSex) {
        this.checkOccupierSex = checkOccupierSex;
    }

    @Override
    public String toString() {
        return "CorrectionRequestBean{" + "uniqueId=" + uniqueId + ", id=" + id + ", wardNo=" + wardNo + ", noticeNo=" + noticeNo + ", noticeDate=" + noticeDate + ", ownerName=" + ownerName + ", ownerFather=" + ownerFather + ", ownerContact=" + ownerContact + ", ownerEmail=" + ownerEmail + ", occupierName=" + occupierName + ", occupierFather=" + occupierFather + ", occupierContact=" + occupierContact + ", occupierEmail=" + occupierEmail + ", address=" + address + ", locationClass=" + locationClass + ", electricSericeConnectionNo=" + electricSericeConnectionNo + ", applicantName=" + applicantName + ", applicantMobileNo=" + applicantMobileNo + ", permissionData=" + permissionData + ", ipAddress=" + ipAddress + ", imageFileName=" + imageFileName + ", images=" + images + ", documentType=" + documentType + ", spouseName=" + spouseName + ", houeNo=" + houeNo + ", buildingName=" + buildingName + ", subLocality=" + subLocality + ", landMark=" + landMark + ", city=" + city + ", pincode=" + pincode + ", plotNo=" + plotNo + ", locName=" + locName + ", roadName=" + roadName + ", checkOwnerName=" + checkOwnerName + ", checkOwnerFather=" + checkOwnerFather + ", checkSpouseName=" + checkSpouseName + ", checkOwnerContact=" + checkOwnerContact + ", checkOwnerEmail=" + checkOwnerEmail + ", checkOccupierName=" + checkOccupierName + ", checkOccupierFather=" + checkOccupierFather + ", checkOccupierContact=" + checkOccupierContact + ", checkOccupierEmail=" + checkOccupierEmail + ", checkPlotNo=" + checkPlotNo + ", checkHouseNo=" + checkHouseNo + ", checkBuildingName=" + checkBuildingName + ", checkRoadName=" + checkRoadName + ", checkSubLocality=" + checkSubLocality + ", checkLocName=" + checkLocName + ", checkLandMark=" + checkLandMark + ", checkElectricSericeConnectionNo=" + checkElectricSericeConnectionNo + ", surveyNo=" + surveyNo + ", checkSurveyNo=" + checkSurveyNo + ", plotSmc=" + plotSmc + ", checkPlotSmc=" + checkPlotSmc + ", ownerAadharNo=" + ownerAadharNo + ", checkOwnerAadharNo=" + checkOwnerAadharNo + ", occupierAadharNo=" + occupierAadharNo + ", checkOccupierAadharNo=" + checkOccupierAadharNo + ", arrearAmount=" + arrearAmount + ", checkArrear=" + checkArrear + ", smcHoldingNo=" + smcHoldingNo + ", checkSmcHoldingNo=" + checkSmcHoldingNo + ", ownerSex=" + ownerSex + ", occupierSex=" + occupierSex + ", checkOwnerSex=" + checkOwnerSex + ", checkOccupierSex=" + checkOccupierSex + '}';
    }

    /**
     * @return the checkPropertyOwnerAddress
     */
    public String getCheckPropertyOwnerAddress() {
        return checkPropertyOwnerAddress;
    }

    /**
     * @param checkPropertyOwnerAddress the checkPropertyOwnerAddress to set
     */
    public void setCheckPropertyOwnerAddress(String checkPropertyOwnerAddress) {
        this.checkPropertyOwnerAddress = checkPropertyOwnerAddress;
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

    
    
    
}
