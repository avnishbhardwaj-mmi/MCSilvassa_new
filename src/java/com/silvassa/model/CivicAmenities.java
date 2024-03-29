package com.silvassa.model;
// default package
// Generated Dec 15, 2016 5:54:25 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CivicAmenities generated by hbm2java
 */
@Entity
@Table(name = "civic_amenities", schema = "public")
public class CivicAmenities implements java.io.Serializable {

	private int civicAmenitiesId;
	private String amenitiesId;
	private String propertyId;
	private String sanitationTypeId;
	private String amenitiesWaterCon;
	private String amenitiesWaterConNum;
	private String amenitiesSewerCon;
	private String amenitiesSewerConNum;
	private String amenitiesElectricCon;
	private String amenitiesElectricMeterNum;
	private String amenitiesElectricConNum;
	private String amenitiesCcCamreaFixed;
	private String amenitiesFireEuipment;
	private String amenitiesLiftAvailable;
	private String amenitiesNumOfBorewells;
	private String amenitiesRainWaterHarvest;
	private String amenitiesHordingAvail;
	private String amenitiesMobileTowerAvail;
	private String status;
	private String createdBy;
	private Date createdOn;
	private Date modifiedOn;

	public CivicAmenities() {
	}

	public CivicAmenities(int civicAmenitiesId) {
		this.civicAmenitiesId = civicAmenitiesId;
	}

	public CivicAmenities(int civicAmenitiesId, String amenitiesId,
			String propertyId, String sanitationTypeId,
			String amenitiesWaterCon, String amenitiesWaterConNum,
			String amenitiesSewerCon, String amenitiesSewerConNum,
			String amenitiesElectricCon, String amenitiesElectricMeterNum,
			String amenitiesElectricConNum, String amenitiesCcCamreaFixed,
			String amenitiesFireEuipment, String amenitiesLiftAvailable,
			String amenitiesNumOfBorewells, String amenitiesRainWaterHarvest,
			String amenitiesHordingAvail, String amenitiesMobileTowerAvail,
			String status, String createdBy, Date createdOn, Date modifiedOn) {
		this.civicAmenitiesId = civicAmenitiesId;
		this.amenitiesId = amenitiesId;
		this.propertyId = propertyId;
		this.sanitationTypeId = sanitationTypeId;
		this.amenitiesWaterCon = amenitiesWaterCon;
		this.amenitiesWaterConNum = amenitiesWaterConNum;
		this.amenitiesSewerCon = amenitiesSewerCon;
		this.amenitiesSewerConNum = amenitiesSewerConNum;
		this.amenitiesElectricCon = amenitiesElectricCon;
		this.amenitiesElectricMeterNum = amenitiesElectricMeterNum;
		this.amenitiesElectricConNum = amenitiesElectricConNum;
		this.amenitiesCcCamreaFixed = amenitiesCcCamreaFixed;
		this.amenitiesFireEuipment = amenitiesFireEuipment;
		this.amenitiesLiftAvailable = amenitiesLiftAvailable;
		this.amenitiesNumOfBorewells = amenitiesNumOfBorewells;
		this.amenitiesRainWaterHarvest = amenitiesRainWaterHarvest;
		this.amenitiesHordingAvail = amenitiesHordingAvail;
		this.amenitiesMobileTowerAvail = amenitiesMobileTowerAvail;
		this.status = status;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}

	@Id
	@Column(name = "civic_amenities_id", unique = true, nullable = false)
	public int getCivicAmenitiesId() {
		return this.civicAmenitiesId;
	}

	public void setCivicAmenitiesId(int civicAmenitiesId) {
		this.civicAmenitiesId = civicAmenitiesId;
	}

	@Column(name = "amenities_id")
	public String getAmenitiesId() {
		return this.amenitiesId;
	}

	public void setAmenitiesId(String amenitiesId) {
		this.amenitiesId = amenitiesId;
	}

	@Column(name = "property_id")
	public String getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	@Column(name = "sanitation_type_id")
	public String getSanitationTypeId() {
		return this.sanitationTypeId;
	}

	public void setSanitationTypeId(String sanitationTypeId) {
		this.sanitationTypeId = sanitationTypeId;
	}

	@Column(name = "amenities_water_con")
	public String getAmenitiesWaterCon() {
		return this.amenitiesWaterCon;
	}

	public void setAmenitiesWaterCon(String amenitiesWaterCon) {
		this.amenitiesWaterCon = amenitiesWaterCon;
	}

	@Column(name = "amenities_water_con_num")
	public String getAmenitiesWaterConNum() {
		return this.amenitiesWaterConNum;
	}

	public void setAmenitiesWaterConNum(String amenitiesWaterConNum) {
		this.amenitiesWaterConNum = amenitiesWaterConNum;
	}

	@Column(name = "amenities_sewer_con")
	public String getAmenitiesSewerCon() {
		return this.amenitiesSewerCon;
	}

	public void setAmenitiesSewerCon(String amenitiesSewerCon) {
		this.amenitiesSewerCon = amenitiesSewerCon;
	}

	@Column(name = "amenities_sewer_con_num")
	public String getAmenitiesSewerConNum() {
		return this.amenitiesSewerConNum;
	}

	public void setAmenitiesSewerConNum(String amenitiesSewerConNum) {
		this.amenitiesSewerConNum = amenitiesSewerConNum;
	}

	@Column(name = "amenities_electric_con")
	public String getAmenitiesElectricCon() {
		return this.amenitiesElectricCon;
	}

	public void setAmenitiesElectricCon(String amenitiesElectricCon) {
		this.amenitiesElectricCon = amenitiesElectricCon;
	}

	@Column(name = "amenities_electric_meter_num")
	public String getAmenitiesElectricMeterNum() {
		return this.amenitiesElectricMeterNum;
	}

	public void setAmenitiesElectricMeterNum(String amenitiesElectricMeterNum) {
		this.amenitiesElectricMeterNum = amenitiesElectricMeterNum;
	}

	@Column(name = "amenities_electric_con_num")
	public String getAmenitiesElectricConNum() {
		return this.amenitiesElectricConNum;
	}

	public void setAmenitiesElectricConNum(String amenitiesElectricConNum) {
		this.amenitiesElectricConNum = amenitiesElectricConNum;
	}

	@Column(name = "amenities_cc_camrea_fixed")
	public String getAmenitiesCcCamreaFixed() {
		return this.amenitiesCcCamreaFixed;
	}

	public void setAmenitiesCcCamreaFixed(String amenitiesCcCamreaFixed) {
		this.amenitiesCcCamreaFixed = amenitiesCcCamreaFixed;
	}

	@Column(name = "amenities_fire_euipment")
	public String getAmenitiesFireEuipment() {
		return this.amenitiesFireEuipment;
	}

	public void setAmenitiesFireEuipment(String amenitiesFireEuipment) {
		this.amenitiesFireEuipment = amenitiesFireEuipment;
	}

	@Column(name = "amenities_lift_available")
	public String getAmenitiesLiftAvailable() {
		return this.amenitiesLiftAvailable;
	}

	public void setAmenitiesLiftAvailable(String amenitiesLiftAvailable) {
		this.amenitiesLiftAvailable = amenitiesLiftAvailable;
	}

	@Column(name = "amenities_num_of_borewells")
	public String getAmenitiesNumOfBorewells() {
		return this.amenitiesNumOfBorewells;
	}

	public void setAmenitiesNumOfBorewells(String amenitiesNumOfBorewells) {
		this.amenitiesNumOfBorewells = amenitiesNumOfBorewells;
	}

	@Column(name = "amenities_rain_water_harvest")
	public String getAmenitiesRainWaterHarvest() {
		return this.amenitiesRainWaterHarvest;
	}

	public void setAmenitiesRainWaterHarvest(String amenitiesRainWaterHarvest) {
		this.amenitiesRainWaterHarvest = amenitiesRainWaterHarvest;
	}

	@Column(name = "amenities_hording_avail")
	public String getAmenitiesHordingAvail() {
		return this.amenitiesHordingAvail;
	}

	public void setAmenitiesHordingAvail(String amenitiesHordingAvail) {
		this.amenitiesHordingAvail = amenitiesHordingAvail;
	}

	@Column(name = "amenities_mobile_tower_avail")
	public String getAmenitiesMobileTowerAvail() {
		return this.amenitiesMobileTowerAvail;
	}

	public void setAmenitiesMobileTowerAvail(String amenitiesMobileTowerAvail) {
		this.amenitiesMobileTowerAvail = amenitiesMobileTowerAvail;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "created_on")
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "modified_on")
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

}
