package com.silvassa.model;

//default package
//Generated 25 Jan, 2017 4:37:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* Generatedtax generated by hbm2java
*/
@Entity
@Table(name = "generatedtax", schema = "public")
public class Generatedtax implements java.io.Serializable {

	private String uniqueTaxId;
	private String zone_id;
	private PropertyDetails propertyDetails;
	private int generatedtaxId;
	private String taxAmount;
	private String builtUpArea;
	private String rentableValue;
	private String generatedby;
	private Date generatedon;
	private String objstatus;
	private Date generatedyear;
	private String noticeFlag;
	private String remarks;
	private String modifiedBy;
	private Date modifiedOn;
	//private Set objectionTrxes = new HashSet(0);

	public Generatedtax() {
	}

	public Generatedtax(String uniqueTaxId, 
			PropertyDetails propertyDetails, int generatedtaxId) {
		this.uniqueTaxId = uniqueTaxId;
		this.propertyDetails = propertyDetails;
		this.generatedtaxId = generatedtaxId;
	}

	

	/**
	 * @param uniqueTaxId
	 * @param zone_id
	 * @param propertyDetails
	 * @param generatedtaxId
	 * @param taxAmount
	 * @param builtUpArea
	 * @param rentableValue
	 * @param generatedby
	 * @param generatedon
	 * @param objstatus
	 * @param generatedyear
	 * @param noticeFlag
	 * @param remarks
	 * @param modifiedBy
	 * @param modifiedOn
	 */
	public Generatedtax(String uniqueTaxId, String zone_id,
			PropertyDetails propertyDetails, int generatedtaxId,
			String taxAmount, String builtUpArea, String rentableValue,
			String generatedby, Date generatedon, String objstatus,
			Date generatedyear, String noticeFlag, String remarks,
			String modifiedBy, Date modifiedOn) {
		super();
		this.uniqueTaxId = uniqueTaxId;
		this.zone_id = zone_id;
		this.propertyDetails = propertyDetails;
		this.generatedtaxId = generatedtaxId;
		this.taxAmount = taxAmount;
		this.builtUpArea = builtUpArea;
		this.rentableValue = rentableValue;
		this.generatedby = generatedby;
		this.generatedon = generatedon;
		this.objstatus = objstatus;
		this.generatedyear = generatedyear;
		this.noticeFlag = noticeFlag;
		this.remarks = remarks;
		this.modifiedBy = modifiedBy;
		this.modifiedOn = modifiedOn;
	}

	@Id
	@Column(name = "unique_tax_id", unique = true, nullable = false)
	public String getUniqueTaxId() {
		return this.uniqueTaxId;
	}

	public void setUniqueTaxId(String uniqueTaxId) {
		this.uniqueTaxId = uniqueTaxId;
	}

	//@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name = "zone_id", nullable = false)
//	public Zonemaster getZonemaster() {
//		return this.zonemaster;
//	}
//
//	public void setZonemaster(Zonemaster zonemaster) {
//		this.zonemaster = zonemaster;
//	}

	
	
	//@OneToMany(cascade=CascadeType.ALL) 
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "property_unique_id", nullable = false)
	public PropertyDetails getPropertyDetails() {
		return this.propertyDetails;
	}

	/**
	 * @return the zone_id
	 */
	public String getZone_id() {
		return zone_id;
	}

	/**
	 * @param zone_id the zone_id to set
	 */
	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}

	public void setPropertyDetails(PropertyDetails propertyDetails) {
		this.propertyDetails = propertyDetails;
	}

	@Column(name = "generatedtax_id", nullable = false)
	public int getGeneratedtaxId() {
		return this.generatedtaxId;
	}

	public void setGeneratedtaxId(int generatedtaxId) {
		this.generatedtaxId = generatedtaxId;
	}

	@Column(name = "tax_amount")
	public String getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	@Column(name = "built_up_area")
	public String getBuiltUpArea() {
		return this.builtUpArea;
	}

	public void setBuiltUpArea(String builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	@Column(name = "rentable_value")
	public String getRentableValue() {
		return this.rentableValue;
	}

	public void setRentableValue(String rentableValue) {
		this.rentableValue = rentableValue;
	}

	@Column(name = "generatedby")
	public String getGeneratedby() {
		return this.generatedby;
	}

	public void setGeneratedby(String generatedby) {
		this.generatedby = generatedby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "generatedon")
	public Date getGeneratedon() {
		return this.generatedon;
	}

	public void setGeneratedon(Date generatedon) {
		this.generatedon = generatedon;
	}

	@Column(name = "objstatus")
	public String getObjstatus() {
		return this.objstatus;
	}

	public void setObjstatus(String objstatus) {
		this.objstatus = objstatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "generatedyear")
	public Date getGeneratedyear() {
		return this.generatedyear;
	}

	public void setGeneratedyear(Date generatedyear) {
		this.generatedyear = generatedyear;
	}

	@Column(name = "notice_flag")
	public String getNoticeFlag() {
		return this.noticeFlag;
	}

	public void setNoticeFlag(String noticeFlag) {
		this.noticeFlag = noticeFlag;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "modified_by")
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_on")
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Generatedtax [uniqueTaxId=" + uniqueTaxId + ", zone_id="
				+ zone_id + ", propertyDetails=" + propertyDetails
				+ ", generatedtaxId=" + generatedtaxId + ", taxAmount="
				+ taxAmount + ", builtUpArea=" + builtUpArea
				+ ", rentableValue=" + rentableValue + ", generatedby="
				+ generatedby + ", generatedon=" + generatedon + ", objstatus="
				+ objstatus + ", generatedyear=" + generatedyear
				+ ", noticeFlag=" + noticeFlag + ", remarks=" + remarks
				+ ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn
				+ "]";
	}

	

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "generatedtax")
//	public Set getObjectionTrxes() {
//		return this.objectionTrxes;
//	}
//
//	public void setObjectionTrxes(Set objectionTrxes) {
//		this.objectionTrxes = objectionTrxes;
//	}

	
	
	
}