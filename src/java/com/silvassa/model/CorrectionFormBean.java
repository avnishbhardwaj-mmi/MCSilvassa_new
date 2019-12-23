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
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "property_correction_form")
public class CorrectionFormBean {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "unique_id")
    private String uniqueId;
    @Column(name = "ward_no")
    private String wardNo;
    @Column(name = "notice_no")
    private String noticeNo;
    @Column(name = "notice_date")
    private String noticeDate;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "owner_father")
    private String ownerFather;
    @Column(name = "owner_contact")
    private String ownerContact;
    @Column(name = "owner_email")
    private String ownerEmail;
    @Column(name = "occupier_name")
    private String occupierName;
    @Column(name = "occupier_father")
    private String occupierFather;
    @Column(name = "occupier_contact")
    private String occupierContact;
    @Column(name = "occupier_email")
    private String occupierEmail;
    @Column(name = "address")
    private String address;
    @Column(name = "location_class")
    private String locationClass;
    @Column(name = "electric_service_connection_no")
    private String electricSericeConnectionNo;
    @Column(name = "applicant_name")
    private String applicantName;
    @Column(name = "applicant_mobile_no")
    private String applicantMobileNo;
    @Column(name = "permission_data")
    private String permissionData;
    @Column(name = "ipaddress")
    private String ipAddress;
    @Column(name = "imagefilename")
    private String imageFileName;
    @Column(name = "documenttype")
    private String documentType;
    @Column(name = "image")
    private byte[] image_byte;
    @Column(name = "spouse_name")
    private String spouseName;
    @Column(name = "house_no")
    private String houseNo;
    @Column(name = "building_name")
    private String buildingName;
    @Column(name = "sublocality")
    private String subLocality;
    @Column(name = "landmark")
    private String landMark;
    @Column(name = "city")
    private String city;
    @Column(name = "pincode")
    private String pincode;
    @Column(name = "plotno")
    private String plotNo;
    @Column(name = "locality_name")
    private String locName;
    @Column(name = "road_name")
    private String roadName;

    @Column(name = "check_owner_name")
    private String checkOwnerName;
    @Column(name = "check_owner_father")
    private String checkOwnerFather;
    @Column(name = "check_spouse_name")
    private String checkSpouseName;
    @Column(name = "check_owner_contact")
    private String checkOwnerContact;
    @Column(name = "check_owner_email")
    private String checkOwnerEmail;
    @Column(name = "check_occupier_name")
    private String checkOccupierName;
    @Column(name = "check_occupier_father")
    private String checkOccupierFather;
    @Column(name = "check_occupier_contact")
    private String checkOccupierContact;
    @Column(name = "check_occupier_email")
    private String checkOccupierEmail;
    @Column(name = "check_plot_no")
    private String checkPlotNo;
    @Column(name = "check_house_no")
    private String checkHouseNo;
    @Column(name = "check_building_name")
    private String checkBuildingName;
    @Column(name = "check_road_name")
    private String checkRoadName;
    @Column(name = "check_sublocality")
    private String checkSubLocality;
    @Column(name = "check_loc_name")
    private String checkLocName;
    @Column(name = "check_land_mark")
    private String checkLandMark;
    @Column(name = "check_electric_serice_connection_no")
    private String checkElectricSericeConnectionNo;
    @Column(name = "documenttypeoccupier")
    private String documentTypeOccupier;
    @Column(name = "documenttypeaddress")
    private String documentTypeAddress;
    @Column(name = "documenttypeelectric")
    private String documentTypeElectric;

    @Column(name = "image_occupier")
    private byte[] image_occupier;
    @Column(name = "image_address")
    private byte[] image_addressr;
    @Column(name = "image_electric")
    private byte[] image_electric;

    @Column(name = "image_owner2")
    private byte[] image_owner2;
    @Column(name = "image_occupier2")
    private byte[] image_occupier2;
    @Column(name = "image_address2")
    private byte[] image_address2;
    @Column(name = "image_covered")
    private byte[] image_covered;
    @Column(name = "image_property_use")
    private byte[] image_property_use;
    @Column(name = "image_arrear")
    private byte[] image_arrear;

    @Column(name = "imagefilenameoccupier")
    private String imageFilenameOccupier;
    @Column(name = "imagefilenameaddress")
    private String imageFilenameAddress;
    @Column(name = "imagefilenmaeelectric")
    private String imageFilenmaeElectric;

    @Column(name = "imagefileowner2")
    private String imageFileOwner2;

    @Column(name = "imagefileoccupier2")
    private String imageFileOccupier2;

    @Column(name = "imagefileaddress2")
    private String imageFileAddress2;

    @Column(name = "imagefilecovered")
    private String imageFileNameCovered;

    @Column(name = "imagefilepropertyuse")
    private String imageFilePropertyUse;

    @Column(name = "imagefilearrear")
    private String imageFileNameArrear;

    @Transient
    private MultipartFile imageFile;
    @Transient
    private MultipartFile imageFile1;
    @Transient
    private MultipartFile imageFile2;
    @Transient
    private MultipartFile imageFile3;

    @Transient
    private MultipartFile imageFileOwner;
    @Transient
    private MultipartFile imageFileOccupier;
    @Transient
    private MultipartFile imageFileAddress;
    @Transient
    private MultipartFile imageFileCovered;
    @Transient
    private MultipartFile imagePropertyUse;
    @Transient
    private MultipartFile imageFileArrear;

    @Transient
    private MultipartFile imageFileOwner2Data;
    @Transient
    private MultipartFile imageFileOwner3Data;
    @Transient
    private MultipartFile imageFileOccupier1Data;
    @Transient
    private MultipartFile imageFileOccupier2Data;

    @Transient
    private MultipartFile imageFileAddress1Data;
    @Transient
    private MultipartFile imageFileAddress2Data;

    @Column(name = "documenttype1")
    private String documentType1;
    @Column(name = "documenttypeoccupier1")
    private String documentTypeOccupier1;
    @Column(name = "documenttypeaddress2")
    private String documentTypeAddress2;
    @Column(name = "documenttypecovered")
    private String documentTypeCovered;
    @Column(name = "documentrypepropertyuse")
    private String documentTypePropertyUse;
    @Column(name = "documenttypearrear")
    private String documentTypeArrear;
    @Column(name = "owneraadharno")
    private String ownerAadharNo;
    @Column(name = "checkowneraadharno")
    private String checkOwnerAadharNo;
    @Column(name = "occupieraadharno")
    private String occupierAadharNo;
    @Column(name = "checkoccupieraadharno")
    private String checkOccupierAadharNo;
    @Column(name = "surveyno")
    private String surveyNo;
    @Column(name = "checksurveyno")
    private String checkSurveyNo;
    @Column(name = "plotsmc")
    private String plotSmc;
    @Column(name = "checkplotsmc")
    private String checkPlotSmc;
    @Column(name = "application_no")
    private String application_no;
    @Column(name = "arrearamount")
    private String arrearAmount;
    @Column(name = "checkarrear")
    private String checkArrear;

    @Column(name = "owner_sex")
    private String ownerSex;
    @Column(name = "check_owner_sex")
    private String checkOwnerSex;
    @Column(name = "occupier_sex")
    private String occupierSex;
    @Column(name = "check_occupier_sex")
    private String checkOccupierSex;
    @Column(name = "property_owner_address")
    private String propertyOwnerAddress;
    @Column(name = "check_property_owner_address")
    private String checkPropertyOwnerAddress;
    @Column(name = "smc_holding_no")
    private String smcProperty;
    @Column(name = "check_smc_holding_no")
    private String checkSmcProperty;

    @Column(name = "documenttypeowner2")
    private String documentType2;
    @Column(name = "documenttypeowner3")
    private String documentType3;
    @Column(name = "imagefilenameowner2")
    private String imageFileOwnerData2;
    @Column(name = "imagefilenameowner3")
    private String imageFileOwnerData3;
    @Column(name = "image_owner3")
    private byte[] image_owner2Data;
    @Column(name = "image_owner4")
    private byte[] image_owner3Data;

    @Column(name = "documenttypeoccupier2")
    private String documentTypeOccupier2;
    @Column(name = "documenttypeoccupier3")
    private String documentTypeOccupier3;
    @Column(name = "imagefilenameoccupier2")
    private String imageFileNameccupier1Data;
    @Column(name = "imagefilenameoccupier3")
    private String imageFileNameOccupier2Data;
    @Column(name = "image_occupier3")
    private byte[] image_occupier2Data;
    @Column(name = "image_occupier4")
    private byte[] image_occupier3Data;

    @Column(name = "documenttypeaddress3")
    private String documentTypeAddress3;
    @Column(name = "documenttypeaddress4")
    private String documentTypeAddress4;
    @Column(name = "imagefilenameaddress2")
    private String imageFileNameAddress1;
    @Column(name = "imagefilenameaddress3")
    private String imageFileNameAddress2;
    @Column(name = "image_address3")
    private byte[] image_address3;
    @Column(name = "image_address4")
    private byte[] image_address4;

    @Column(name = "filloccupiername")
    private String fillOccupierName;

    @Column(name = "locnameother")
    private String locNameOther;
    @Column(name="remarks")
    private String remarks;
    
    
     @Transient
     private String complainNo;
     @Transient
     private String orginalPid;
     @Transient
     private String propertyType;
     

    @Transient
    private List<CorrectionFormFloorBean> floorDetails;

    public List<CorrectionFormFloorBean> getFloorDetails() {
        return floorDetails;
    }

    public void setFloorDetails(List<CorrectionFormFloorBean> floorDetails) {
        this.floorDetails = floorDetails;
    }

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
     * @return the uniqueId
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId the uniqueId to set
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
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
     * @return the ownerFather
     */
    public String getOwnerFather() {
        return ownerFather;
    }

    /**
     * @param ownerFather the ownerFather to set
     */
    public void setOwnerFather(String ownerFather) {
        this.ownerFather = ownerFather;
    }

    /**
     * @return the ownerContact
     */
    public String getOwnerContact() {
        return ownerContact;
    }

    /**
     * @param ownerContact the ownerContact to set
     */
    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    /**
     * @return the ownerEmail
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * @param ownerEmail the ownerEmail to set
     */
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
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

    /**
     * @return the occupierFather
     */
    public String getOccupierFather() {
        return occupierFather;
    }

    /**
     * @param occupierFather the occupierFather to set
     */
    public void setOccupierFather(String occupierFather) {
        this.occupierFather = occupierFather;
    }

    /**
     * @return the occupierContact
     */
    public String getOccupierContact() {
        return occupierContact;
    }

    /**
     * @param occupierContact the occupierContact to set
     */
    public void setOccupierContact(String occupierContact) {
        this.occupierContact = occupierContact;
    }

    /**
     * @return the occupierEmail
     */
    public String getOccupierEmail() {
        return occupierEmail;
    }

    /**
     * @param occupierEmail the occupierEmail to set
     */
    public void setOccupierEmail(String occupierEmail) {
        this.occupierEmail = occupierEmail;
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
     * @return the locationClass
     */
    public String getLocationClass() {
        return locationClass;
    }

    /**
     * @param locationClass the locationClass to set
     */
    public void setLocationClass(String locationClass) {
        this.locationClass = locationClass;
    }

    /**
     * @return the electricSericeConnectionNo
     */
    public String getElectricSericeConnectionNo() {
        return electricSericeConnectionNo;
    }

    /**
     * @param electricSericeConnectionNo the electricSericeConnectionNo to set
     */
    public void setElectricSericeConnectionNo(String electricSericeConnectionNo) {
        this.electricSericeConnectionNo = electricSericeConnectionNo;
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
     * @return the applicantMobileNo
     */
    public String getApplicantMobileNo() {
        return applicantMobileNo;
    }

    /**
     * @param applicantMobileNo the applicantMobileNo to set
     */
    public void setApplicantMobileNo(String applicantMobileNo) {
        this.applicantMobileNo = applicantMobileNo;
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
     * @return the imageFileName
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * @param imageFileName the imageFileName to set
     */
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
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
     * @return the image_byte
     */
    public byte[] getImage_byte() {
        return image_byte;
    }

    /**
     * @param image_byte the image_byte to set
     */
    public void setImage_byte(byte[] image_byte) {
        this.image_byte = image_byte;
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
     * @return the houseNo
     */
    public String getHouseNo() {
        return houseNo;
    }

    /**
     * @param houseNo the houseNo to set
     */
    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
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
     * @param checkElectricSericeConnectionNo the
     * checkElectricSericeConnectionNo to set
     */
    public void setCheckElectricSericeConnectionNo(String checkElectricSericeConnectionNo) {
        this.checkElectricSericeConnectionNo = checkElectricSericeConnectionNo;
    }

    /**
     * @return the documentTypeOccupier
     */
    public String getDocumentTypeOccupier() {
        return documentTypeOccupier;
    }

    /**
     * @param documentTypeOccupier the documentTypeOccupier to set
     */
    public void setDocumentTypeOccupier(String documentTypeOccupier) {
        this.documentTypeOccupier = documentTypeOccupier;
    }

    /**
     * @return the documentTypeAddress
     */
    public String getDocumentTypeAddress() {
        return documentTypeAddress;
    }

    /**
     * @param documentTypeAddress the documentTypeAddress to set
     */
    public void setDocumentTypeAddress(String documentTypeAddress) {
        this.documentTypeAddress = documentTypeAddress;
    }

    /**
     * @return the documentTypeElectric
     */
    public String getDocumentTypeElectric() {
        return documentTypeElectric;
    }

    /**
     * @param documentTypeElectric the documentTypeElectric to set
     */
    public void setDocumentTypeElectric(String documentTypeElectric) {
        this.documentTypeElectric = documentTypeElectric;
    }

    /**
     * @return the image_occupier
     */
    public byte[] getImage_occupier() {
        return image_occupier;
    }

    /**
     * @param image_occupier the image_occupier to set
     */
    public void setImage_occupier(byte[] image_occupier) {
        this.image_occupier = image_occupier;
    }

    /**
     * @return the image_addressr
     */
    public byte[] getImage_addressr() {
        return image_addressr;
    }

    /**
     * @param image_addressr the image_addressr to set
     */
    public void setImage_addressr(byte[] image_addressr) {
        this.image_addressr = image_addressr;
    }

    /**
     * @return the image_electric
     */
    public byte[] getImage_electric() {
        return image_electric;
    }

    /**
     * @param image_electric the image_electric to set
     */
    public void setImage_electric(byte[] image_electric) {
        this.image_electric = image_electric;
    }

    /**
     * @return the image_owner2
     */
    public byte[] getImage_owner2() {
        return image_owner2;
    }

    /**
     * @param image_owner2 the image_owner2 to set
     */
    public void setImage_owner2(byte[] image_owner2) {
        this.image_owner2 = image_owner2;
    }

    /**
     * @return the image_occupier2
     */
    public byte[] getImage_occupier2() {
        return image_occupier2;
    }

    /**
     * @param image_occupier2 the image_occupier2 to set
     */
    public void setImage_occupier2(byte[] image_occupier2) {
        this.image_occupier2 = image_occupier2;
    }

    /**
     * @return the image_address2
     */
    public byte[] getImage_address2() {
        return image_address2;
    }

    /**
     * @param image_address2 the image_address2 to set
     */
    public void setImage_address2(byte[] image_address2) {
        this.image_address2 = image_address2;
    }

    /**
     * @return the image_covered
     */
    public byte[] getImage_covered() {
        return image_covered;
    }

    /**
     * @param image_covered the image_covered to set
     */
    public void setImage_covered(byte[] image_covered) {
        this.image_covered = image_covered;
    }

    /**
     * @return the image_property_use
     */
    public byte[] getImage_property_use() {
        return image_property_use;
    }

    /**
     * @param image_property_use the image_property_use to set
     */
    public void setImage_property_use(byte[] image_property_use) {
        this.image_property_use = image_property_use;
    }

    /**
     * @return the iimage_arrear
     */
    public byte[] getImage_arrear() {
        return image_arrear;
    }

    /**
     * @param iimage_arrear the iimage_arrear to set
     */
    public void setImage_arrear(byte[] image_arrear) {
        this.image_arrear = image_arrear;
    }

    /**
     * @return the imageFilenameOccupier
     */
    public String getImageFilenameOccupier() {
        return imageFilenameOccupier;
    }

    /**
     * @param imageFilenameOccupier the imageFilenameOccupier to set
     */
    public void setImageFilenameOccupier(String imageFilenameOccupier) {
        this.imageFilenameOccupier = imageFilenameOccupier;
    }

    /**
     * @return the imageFilenameAddress
     */
    public String getImageFilenameAddress() {
        return imageFilenameAddress;
    }

    /**
     * @param imageFilenameAddress the imageFilenameAddress to set
     */
    public void setImageFilenameAddress(String imageFilenameAddress) {
        this.imageFilenameAddress = imageFilenameAddress;
    }

    /**
     * @return the imageFilenmaeElectric
     */
    public String getImageFilenmaeElectric() {
        return imageFilenmaeElectric;
    }

    /**
     * @param imageFilenmaeElectric the imageFilenmaeElectric to set
     */
    public void setImageFilenmaeElectric(String imageFilenmaeElectric) {
        this.imageFilenmaeElectric = imageFilenmaeElectric;
    }

    /**
     * @return the imageFileOwner2
     */
    public String getImageFileOwner2() {
        return imageFileOwner2;
    }

    /**
     * @param imageFileOwner2 the imageFileOwner2 to set
     */
    public void setImageFileOwner2(String imageFileOwner2) {
        this.imageFileOwner2 = imageFileOwner2;
    }

    /**
     * @return the imageFileOccupier2
     */
    public String getImageFileOccupier2() {
        return imageFileOccupier2;
    }

    /**
     * @param imageFileOccupier2 the imageFileOccupier2 to set
     */
    public void setImageFileOccupier2(String imageFileOccupier2) {
        this.imageFileOccupier2 = imageFileOccupier2;
    }

    /**
     * @return the imageFileAddress2
     */
    public String getImageFileAddress2() {
        return imageFileAddress2;
    }

    /**
     * @param imageFileAddress2 the imageFileAddress2 to set
     */
    public void setImageFileAddress2(String imageFileAddress2) {
        this.imageFileAddress2 = imageFileAddress2;
    }

    /**
     * @return the imageFileNameCovered
     */
    public String getImageFileNameCovered() {
        return imageFileNameCovered;
    }

    /**
     * @param imageFileNameCovered the imageFileNameCovered to set
     */
    public void setImageFileNameCovered(String imageFileNameCovered) {
        this.imageFileNameCovered = imageFileNameCovered;
    }

    /**
     * @return the imageFilePropertyUse
     */
    public String getImageFilePropertyUse() {
        return imageFilePropertyUse;
    }

    /**
     * @param imageFilePropertyUse the imageFilePropertyUse to set
     */
    public void setImageFilePropertyUse(String imageFilePropertyUse) {
        this.imageFilePropertyUse = imageFilePropertyUse;
    }

    /**
     * @return the imageFileNameArrear
     */
    public String getImageFileNameArrear() {
        return imageFileNameArrear;
    }

    /**
     * @param imageFileNameArrear the imageFileNameArrear to set
     */
    public void setImageFileNameArrear(String imageFileNameArrear) {
        this.imageFileNameArrear = imageFileNameArrear;
    }

    /**
     * @return the imageFile
     */
    public MultipartFile getImageFile() {
        return imageFile;
    }

    /**
     * @param imageFile the imageFile to set
     */
    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * @return the imageFile1
     */
    public MultipartFile getImageFile1() {
        return imageFile1;
    }

    /**
     * @param imageFile1 the imageFile1 to set
     */
    public void setImageFile1(MultipartFile imageFile1) {
        this.imageFile1 = imageFile1;
    }

    /**
     * @return the imageFile2
     */
    public MultipartFile getImageFile2() {
        return imageFile2;
    }

    /**
     * @param imageFile2 the imageFile2 to set
     */
    public void setImageFile2(MultipartFile imageFile2) {
        this.imageFile2 = imageFile2;
    }

    /**
     * @return the imageFile3
     */
    public MultipartFile getImageFile3() {
        return imageFile3;
    }

    /**
     * @param imageFile3 the imageFile3 to set
     */
    public void setImageFile3(MultipartFile imageFile3) {
        this.imageFile3 = imageFile3;
    }

    /**
     * @return the imageFileOwner
     */
    public MultipartFile getImageFileOwner() {
        return imageFileOwner;
    }

    /**
     * @param imageFileOwner the imageFileOwner to set
     */
    public void setImageFileOwner(MultipartFile imageFileOwner) {
        this.imageFileOwner = imageFileOwner;
    }

    /**
     * @return the imageFileOccupier
     */
    public MultipartFile getImageFileOccupier() {
        return imageFileOccupier;
    }

    /**
     * @param imageFileOccupier the imageFileOccupier to set
     */
    public void setImageFileOccupier(MultipartFile imageFileOccupier) {
        this.imageFileOccupier = imageFileOccupier;
    }

    /**
     * @return the imageFileAddress
     */
    public MultipartFile getImageFileAddress() {
        return imageFileAddress;
    }

    /**
     * @param imageFileAddress the imageFileAddress to set
     */
    public void setImageFileAddress(MultipartFile imageFileAddress) {
        this.imageFileAddress = imageFileAddress;
    }

    /**
     * @return the imageFileCovered
     */
    public MultipartFile getImageFileCovered() {
        return imageFileCovered;
    }

    /**
     * @param imageFileCovered the imageFileCovered to set
     */
    public void setImageFileCovered(MultipartFile imageFileCovered) {
        this.imageFileCovered = imageFileCovered;
    }

    /**
     * @return the imagePropertyUse
     */
    public MultipartFile getImagePropertyUse() {
        return imagePropertyUse;
    }

    /**
     * @param imagePropertyUse the imagePropertyUse to set
     */
    public void setImagePropertyUse(MultipartFile imagePropertyUse) {
        this.imagePropertyUse = imagePropertyUse;
    }

    /**
     * @return the imageFileArrear
     */
    public MultipartFile getImageFileArrear() {
        return imageFileArrear;
    }

    /**
     * @param imageFileArrear the imageFileArrear to set
     */
    public void setImageFileArrear(MultipartFile imageFileArrear) {
        this.imageFileArrear = imageFileArrear;
    }

    /**
     * @return the documentType1
     */
    public String getDocumentType1() {
        return documentType1;
    }

    /**
     * @param documentType1 the documentType1 to set
     */
    public void setDocumentType1(String documentType1) {
        this.documentType1 = documentType1;
    }

    /**
     * @return the documentTypeOccupier1
     */
    public String getDocumentTypeOccupier1() {
        return documentTypeOccupier1;
    }

    /**
     * @param documentTypeOccupier1 the documentTypeOccupier1 to set
     */
    public void setDocumentTypeOccupier1(String documentTypeOccupier1) {
        this.documentTypeOccupier1 = documentTypeOccupier1;
    }

    /**
     * @return the documentTypeAddress2
     */
    public String getDocumentTypeAddress2() {
        return documentTypeAddress2;
    }

    /**
     * @param documentTypeAddress2 the documentTypeAddress2 to set
     */
    public void setDocumentTypeAddress2(String documentTypeAddress2) {
        this.documentTypeAddress2 = documentTypeAddress2;
    }

    /**
     * @return the documentTypeCovered
     */
    public String getDocumentTypeCovered() {
        return documentTypeCovered;
    }

    /**
     * @param documentTypeCovered the documentTypeCovered to set
     */
    public void setDocumentTypeCovered(String documentTypeCovered) {
        this.documentTypeCovered = documentTypeCovered;
    }

    /**
     * @return the documentTypePropertyUse
     */
    public String getDocumentTypePropertyUse() {
        return documentTypePropertyUse;
    }

    /**
     * @param documentTypePropertyUse the documentTypePropertyUse to set
     */
    public void setDocumentTypePropertyUse(String documentTypePropertyUse) {
        this.documentTypePropertyUse = documentTypePropertyUse;
    }

    /**
     * @return the documentTypeArrear
     */
    public String getDocumentTypeArrear() {
        return documentTypeArrear;
    }

    /**
     * @param documentTypeArrear the documentTypeArrear to set
     */
    public void setDocumentTypeArrear(String documentTypeArrear) {
        this.documentTypeArrear = documentTypeArrear;
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
     * @return the application_no
     */
    public String getApplication_no() {
        return application_no;
    }

    /**
     * @param application_no the application_no to set
     */
    public void setApplication_no(String application_no) {
        this.application_no = application_no;
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
     * @return the smcProperty
     */
    public String getSmcProperty() {
        return smcProperty;
    }

    /**
     * @param smcProperty the smcProperty to set
     */
    public void setSmcProperty(String smcProperty) {
        this.smcProperty = smcProperty;
    }

    /**
     * @return the checkSmcProperty
     */
    public String getCheckSmcProperty() {
        return checkSmcProperty;
    }

    /**
     * @param checkSmcProperty the checkSmcProperty to set
     */
    public void setCheckSmcProperty(String checkSmcProperty) {
        this.checkSmcProperty = checkSmcProperty;
    }

    /**
     * @return the fillOccupierName
     */
    public String getFillOccupierName() {
        return fillOccupierName;
    }

    /**
     * @param fillOccupierName the fillOccupierName to set
     */
    public void setFillOccupierName(String fillOccupierName) {
        this.fillOccupierName = fillOccupierName;
    }

    /**
     * @return the imageFileOwner2Data
     */
    public MultipartFile getImageFileOwner2Data() {
        return imageFileOwner2Data;
    }

    /**
     * @param imageFileOwner2Data the imageFileOwner2Data to set
     */
    public void setImageFileOwner2Data(MultipartFile imageFileOwner2Data) {
        this.imageFileOwner2Data = imageFileOwner2Data;
    }

    /**
     * @return the imageFileOwner3Data
     */
    public MultipartFile getImageFileOwner3Data() {
        return imageFileOwner3Data;
    }

    /**
     * @param imageFileOwner3Data the imageFileOwner3Data to set
     */
    public void setImageFileOwner3Data(MultipartFile imageFileOwner3Data) {
        this.imageFileOwner3Data = imageFileOwner3Data;
    }

    /**
     * @return the imageFileOccupier1Data
     */
    public MultipartFile getImageFileOccupier1Data() {
        return imageFileOccupier1Data;
    }

    /**
     * @param imageFileOccupier1Data the imageFileOccupier1Data to set
     */
    public void setImageFileOccupier1Data(MultipartFile imageFileOccupier1Data) {
        this.imageFileOccupier1Data = imageFileOccupier1Data;
    }

    /**
     * @return the imageFileOccupier2Data
     */
    public MultipartFile getImageFileOccupier2Data() {
        return imageFileOccupier2Data;
    }

    /**
     * @param imageFileOccupier2Data the imageFileOccupier2Data to set
     */
    public void setImageFileOccupier2Data(MultipartFile imageFileOccupier2Data) {
        this.imageFileOccupier2Data = imageFileOccupier2Data;
    }

    /**
     * @return the imageFileAddress1Data
     */
    public MultipartFile getImageFileAddress1Data() {
        return imageFileAddress1Data;
    }

    /**
     * @param imageFileAddress1Data the imageFileAddress1Data to set
     */
    public void setImageFileAddress1Data(MultipartFile imageFileAddress1Data) {
        this.imageFileAddress1Data = imageFileAddress1Data;
    }

    /**
     * @return the imageFileAddress2Data
     */
    public MultipartFile getImageFileAddress2Data() {
        return imageFileAddress2Data;
    }

    /**
     * @param imageFileAddress2Data the imageFileAddress2Data to set
     */
    public void setImageFileAddress2Data(MultipartFile imageFileAddress2Data) {
        this.imageFileAddress2Data = imageFileAddress2Data;
    }

    /**
     * @return the documentType2
     */
    public String getDocumentType2() {
        return documentType2;
    }

    /**
     * @param documentType2 the documentType2 to set
     */
    public void setDocumentType2(String documentType2) {
        this.documentType2 = documentType2;
    }

    /**
     * @return the documentType3
     */
    public String getDocumentType3() {
        return documentType3;
    }

    /**
     * @param documentType3 the documentType3 to set
     */
    public void setDocumentType3(String documentType3) {
        this.documentType3 = documentType3;
    }

    /**
     * @return the imageFileOwnerData2
     */
    public String getImageFileOwnerData2() {
        return imageFileOwnerData2;
    }

    /**
     * @param imageFileOwnerData2 the imageFileOwnerData2 to set
     */
    public void setImageFileOwnerData2(String imageFileOwnerData2) {
        this.imageFileOwnerData2 = imageFileOwnerData2;
    }

    /**
     * @return the imageFileOwnerData3
     */
    public String getImageFileOwnerData3() {
        return imageFileOwnerData3;
    }

    /**
     * @param imageFileOwnerData3 the imageFileOwnerData3 to set
     */
    public void setImageFileOwnerData3(String imageFileOwnerData3) {
        this.imageFileOwnerData3 = imageFileOwnerData3;
    }

    /**
     * @return the image_owner2Data
     */
    public byte[] getImage_owner2Data() {
        return image_owner2Data;
    }

    /**
     * @param image_owner2Data the image_owner2Data to set
     */
    public void setImage_owner2Data(byte[] image_owner2Data) {
        this.image_owner2Data = image_owner2Data;
    }

    /**
     * @return the image_owner3Data
     */
    public byte[] getImage_owner3Data() {
        return image_owner3Data;
    }

    /**
     * @param image_owner3Data the image_owner3Data to set
     */
    public void setImage_owner3Data(byte[] image_owner3Data) {
        this.image_owner3Data = image_owner3Data;
    }

    /**
     * @return the documentTypeOccupier2
     */
    public String getDocumentTypeOccupier2() {
        return documentTypeOccupier2;
    }

    /**
     * @param documentTypeOccupier2 the documentTypeOccupier2 to set
     */
    public void setDocumentTypeOccupier2(String documentTypeOccupier2) {
        this.documentTypeOccupier2 = documentTypeOccupier2;
    }

    /**
     * @return the documentTypeOccupier3
     */
    public String getDocumentTypeOccupier3() {
        return documentTypeOccupier3;
    }

    /**
     * @param documentTypeOccupier3 the documentTypeOccupier3 to set
     */
    public void setDocumentTypeOccupier3(String documentTypeOccupier3) {
        this.documentTypeOccupier3 = documentTypeOccupier3;
    }

    /**
     * @return the imageFileNameccupier1Data
     */
    public String getImageFileNameccupier1Data() {
        return imageFileNameccupier1Data;
    }

    /**
     * @param imageFileNameccupier1Data the imageFileNameccupier1Data to set
     */
    public void setImageFileNameccupier1Data(String imageFileNameccupier1Data) {
        this.imageFileNameccupier1Data = imageFileNameccupier1Data;
    }

    /**
     * @return the imageFileNameOccupier2Data
     */
    public String getImageFileNameOccupier2Data() {
        return imageFileNameOccupier2Data;
    }

    /**
     * @param imageFileNameOccupier2Data the imageFileNameOccupier2Data to set
     */
    public void setImageFileNameOccupier2Data(String imageFileNameOccupier2Data) {
        this.imageFileNameOccupier2Data = imageFileNameOccupier2Data;
    }

    /**
     * @return the image_occupier2Data
     */
    public byte[] getImage_occupier2Data() {
        return image_occupier2Data;
    }

    /**
     * @param image_occupier2Data the image_occupier2Data to set
     */
    public void setImage_occupier2Data(byte[] image_occupier2Data) {
        this.image_occupier2Data = image_occupier2Data;
    }

    /**
     * @return the image_occupier3Data
     */
    public byte[] getImage_occupier3Data() {
        return image_occupier3Data;
    }

    /**
     * @param image_occupier3Data the image_occupier3Data to set
     */
    public void setImage_occupier3Data(byte[] image_occupier3Data) {
        this.image_occupier3Data = image_occupier3Data;
    }

    /**
     * @return the documentTypeAddress3
     */
    public String getDocumentTypeAddress3() {
        return documentTypeAddress3;
    }

    /**
     * @param documentTypeAddress3 the documentTypeAddress3 to set
     */
    public void setDocumentTypeAddress3(String documentTypeAddress3) {
        this.documentTypeAddress3 = documentTypeAddress3;
    }

    /**
     * @return the documentTypeAddress4
     */
    public String getDocumentTypeAddress4() {
        return documentTypeAddress4;
    }

    /**
     * @param documentTypeAddress4 the documentTypeAddress4 to set
     */
    public void setDocumentTypeAddress4(String documentTypeAddress4) {
        this.documentTypeAddress4 = documentTypeAddress4;
    }

    /**
     * @return the imageFileNameAddress1
     */
    public String getImageFileNameAddress1() {
        return imageFileNameAddress1;
    }

    /**
     * @param imageFileNameAddress1 the imageFileNameAddress1 to set
     */
    public void setImageFileNameAddress1(String imageFileNameAddress1) {
        this.imageFileNameAddress1 = imageFileNameAddress1;
    }

    /**
     * @return the imageFileNameAddress2
     */
    public String getImageFileNameAddress2() {
        return imageFileNameAddress2;
    }

    /**
     * @param imageFileNameAddress2 the imageFileNameAddress2 to set
     */
    public void setImageFileNameAddress2(String imageFileNameAddress2) {
        this.imageFileNameAddress2 = imageFileNameAddress2;
    }

    /**
     * @return the image_address3
     */
    public byte[] getImage_address3() {
        return image_address3;
    }

    /**
     * @param image_address3 the image_address3 to set
     */
    public void setImage_address3(byte[] image_address3) {
        this.image_address3 = image_address3;
    }

    /**
     * @return the image_address4
     */
    public byte[] getImage_address4() {
        return image_address4;
    }

    /**
     * @param image_address4 the image_address4 to set
     */
    public void setImage_address4(byte[] image_address4) {
        this.image_address4 = image_address4;
    }

    /**
     * @return the locNameOther
     */
    public String getLocNameOther() {
        return locNameOther;
    }

    /**
     * @param locNameOther the locNameOther to set
     */
    public void setLocNameOther(String locNameOther) {
        this.locNameOther = locNameOther;
    }

    /**
     * @return the complainNo
     */
    public String getComplainNo() {
        return complainNo;
    }

    /**
     * @param complainNo the complainNo to set
     */
    public void setComplainNo(String complainNo) {
        this.complainNo = complainNo;
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
     * @return the orginalPid
     */
    public String getOrginalPid() {
        return orginalPid;
    }

    /**
     * @param orginalPid the orginalPid to set
     */
    public void setOrginalPid(String orginalPid) {
        this.orginalPid = orginalPid;
    }

    /**
     * @return the propertyType
     */
    public String getPropertyType() {
        return propertyType;
    }

    /**
     * @param propertyType the propertyType to set
     */
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

}
