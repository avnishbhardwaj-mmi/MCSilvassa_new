/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author CEINFO
 */
public class CorrectionFormFloorReport {
private int id;
private String uniqueId;
private String propertyFloorId;  
private String floorType;
private String carpetArea;
private String propertyUse;
private String propertySubType;
private String ConstructionType;
private String selfRent;
private String rentedValue;
private String editData;
private String deleteData;
private String permissionData;
private String application_no;
private String status; 
private String owner;

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
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
