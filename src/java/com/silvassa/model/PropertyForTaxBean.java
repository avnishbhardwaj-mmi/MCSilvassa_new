package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="property_details")
public class PropertyForTaxBean {

	@Id
	@Column(name="property_id")
	private String propertyID;
	
	@Column(name="property_details_id")
	private Integer propertyToOwnerID;
	
	public Integer getPropertyToOwnerID() {
	    return propertyToOwnerID;
	}
	public void setPropertyToOwnerID(Integer propertyToOwnerID) {
	    this.propertyToOwnerID = propertyToOwnerID;
	}
	@Column(name="property_type_id")
	private String propertyType;
	
	
	@Transient
	private String buildUpArea;
	
	@Column(name="ward_id")
	private String ward;
	
	@Column(name="zone_id")
	private String zone;
	
	@Column(name="locality_id")
	private String locality;
	
	@Column(name="property_plot_num")
	private String plot_num; 
	
	@Column(name="property_house_no")
	private String house_no;
	
	@Column(name="property_sublocality")
	private String sublocality;
	
	@Column(name="property_landmark")
	private String landmark;
	
	@Column(name="property_pincode")
	private String pincode;
	
	@Column(name="property_building_name")
	private String building;
	
	@Transient
	private String ownerName;
	
	@Transient
	private String sex;
	
	
	public String getPropertyID() {
		return propertyID;
	}
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getBuildUpArea() {
		return buildUpArea;
	}
	public void setBuildUpArea(String buildUpArea) {
		this.buildUpArea = buildUpArea;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getPlot_num() {
		return plot_num;
	}
	public void setPlot_num(String plot_num) {
		this.plot_num = plot_num;
	}
	public String getHouse_no() {
		return house_no;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	public String getSublocality() {
		return sublocality;
	}
	public void setSublocality(String sublocality) {
		this.sublocality = sublocality;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
