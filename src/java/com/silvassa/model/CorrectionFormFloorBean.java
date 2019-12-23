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
import javax.persistence.Transient;


/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "property_correction_form_floor")
public class CorrectionFormFloorBean {
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name = "uniqueid")
private String uniqueId;
@Column(name = "property_floor_id")
private String propertyFloorId;  
@Column(name = "floortype")
private String floorType;
@Column(name = "carpetarea")
private String carpetArea;
@Column(name = "propertyuse")
private String propertyUse;

@Column(name = "propertysubtype")
private String propertySubType;
@Column(name = "constructiontype")
private String ConstructionType;
@Column(name = "selfrent")
private String selfRent;
@Column(name = "rentedvalue")
private String rentedValue;
@Column(name = "editdata")
private String editData;
@Column(name = "deletedata")
private String deleteData;
@Column(name="permission_data")
private String permissionData;
@Column(name="application_no")
private String application_no;
@Column(name="status")
private String status;

@Transient
private String wardNo;
@Transient
private String noticeDate;
@Transient
private String applicantName;
@Transient
private String remarks;


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
     * @return the propertyFloorId
     */
    public String getPropertyFloorId() {
        return propertyFloorId;
    }

    /**
     * @param propertyFloorId the propertyFloorId to set
     */
    public void setPropertyFloorId(String propertyFloorId) {
        this.propertyFloorId = propertyFloorId;
    }

    /**
     * @return the floorType
     */
    public String getFloorType() {
        return floorType;
    }

    /**
     * @param floorType the floorType to set
     */
    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    /**
     * @return the carpetArea
     */
    public String getCarpetArea() {
        return carpetArea;
    }

    /**
     * @param carpetArea the carpetArea to set
     */
    public void setCarpetArea(String carpetArea) {
        this.carpetArea = carpetArea;
    }

    /**
     * @return the propertyUse
     */
    public String getPropertyUse() {
        return propertyUse;
    }

    /**
     * @param propertyUse the propertyUse to set
     */
    public void setPropertyUse(String propertyUse) {
        this.propertyUse = propertyUse;
    }

    /**
     * @return the propertySubType
     */
    public String getPropertySubType() {
        return propertySubType;
    }

    /**
     * @param propertySubType the propertySubType to set
     */
    public void setPropertySubType(String propertySubType) {
        this.propertySubType = propertySubType;
    }

    /**
     * @return the ConstructionType
     */
    public String getConstructionType() {
        return ConstructionType;
    }

    /**
     * @param ConstructionType the ConstructionType to set
     */
    public void setConstructionType(String ConstructionType) {
        this.ConstructionType = ConstructionType;
    }

    /**
     * @return the selfRent
     */
    public String getSelfRent() {
        return selfRent;
    }

    /**
     * @param selfRent the selfRent to set
     */
    public void setSelfRent(String selfRent) {
        this.selfRent = selfRent;
    }

    /**
     * @return the rentedValue
     */
    public String getRentedValue() {
        return rentedValue;
    }

    /**
     * @param rentedValue the rentedValue to set
     */
    public void setRentedValue(String rentedValue) {
        this.rentedValue = rentedValue;
    }

    /**
     * @return the editData
     */
    public String getEditData() {
        return editData;
    }

    /**
     * @param editData the editData to set
     */
    public void setEditData(String editData) {
        this.editData = editData;
    }

    /**
     * @return the deleteData
     */
    public String getDeleteData() {
        return deleteData;
    }

    /**
     * @param deleteData the deleteData to set
     */
    public void setDeleteData(String deleteData) {
        this.deleteData = deleteData;
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

    @Override
    public String toString() {
        return "CorrectionFormFloorBean{" + "id=" + id + ", uniqueId=" + uniqueId + ", propertyFloorId=" + propertyFloorId + ", floorType=" + floorType + ", carpetArea=" + carpetArea + ", propertyUse=" + propertyUse + ", propertySubType=" + propertySubType + ", ConstructionType=" + ConstructionType + ", selfRent=" + selfRent + ", rentedValue=" + rentedValue + ", editData=" + editData + ", deleteData=" + deleteData + ", permissionData=" + permissionData + '}';
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


}
