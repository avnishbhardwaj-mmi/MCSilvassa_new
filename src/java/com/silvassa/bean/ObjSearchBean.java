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
public class ObjSearchBean {
    
     private String propID;
     private String occupier;
     private String owner;
     private String fyear;
     private String zone;
     private String ward;
     
     private String fromDate;
     private String toDate;
     private String objId;
     private String objStatus;

    /**
     * @return the propID
     */
    public String getPropID() {
        return propID;
    }

    /**
     * @param propID the propID to set
     */
    public void setPropID(String propID) {
        this.propID = propID;
    }

    /**
     * @return the occupier
     */
    public String getOccupier() {
        return occupier;
    }

    /**
     * @param occupier the occupier to set
     */
    public void setOccupier(String occupier) {
        this.occupier = occupier;
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

    /**
     * @return the fyear
     */
    public String getFyear() {
        return fyear;
    }

    /**
     * @param fyear the fyear to set
     */
    public void setFyear(String fyear) {
        this.fyear = fyear;
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
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the objId
     */
    public String getObjId() {
        return objId;
    }

    /**
     * @param objId the objId to set
     */
    public void setObjId(String objId) {
        this.objId = objId;
    }

    /**
     * @return the objStatus
     */
    public String getObjStatus() {
        return objStatus;
    }

    /**
     * @param objStatus the objStatus to set
     */
    public void setObjStatus(String objStatus) {
        this.objStatus = objStatus;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    
    
    @Override
    public String toString() {
        return "ObjSearchBean{" + "propID=" + propID + ", occupier=" + occupier + ", owner=" + owner + ", fyear=" + fyear + ", zone=" + zone + ", fromDate=" + fromDate + ", toDate=" + toDate + ", objId=" + objId + ", objStatus=" + objStatus + '}';
    }
    
    
 
}
