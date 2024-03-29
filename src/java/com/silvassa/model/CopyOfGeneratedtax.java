package com.silvassa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Generatedtax generated by hbm2java
 */
@Entity
@Table(name = "generatedtax", schema = "public")
public class CopyOfGeneratedtax implements java.io.Serializable {

	private int generatedtaxId;
	private String uniqueTaxId;
	private String propertyUniqueId;
	private String taxAmount;
	private String builtUpArea;
	private String rentableValue;
	private String generatedby;
	private Date generatedyear;
	private String prop_type;
	private String notice_flag;
//	private String generatedon;

	private String zonename; // ;character varying(10),
	private String remarks;// text
	//Sandeep added below on 10Jan
	private String modified_by;
	private Date modified_on;
	
	private String objstatus;

	public CopyOfGeneratedtax() {
	}

	public CopyOfGeneratedtax(int generatedtaxId) {
		this.generatedtaxId = generatedtaxId;
	}

		
	/**
	 * @param generatedtaxId
	 * @param uniqueTaxId
	 * @param propertyUniqueId
	 * @param taxAmount
	 * @param builtUpArea
	 * @param rentableValue
	 * @param generatedby
	 * @param generatedon
	 * @param zonename
	 * @param remarks
	 * @param modified_by
	 * @param modified_on
	 */
	public CopyOfGeneratedtax(int generatedtaxId, String uniqueTaxId,
			String propertyUniqueId, String taxAmount, String builtUpArea,
			String rentableValue, String generatedby,
			String zonename, String remarks, String modified_by,
			Date modified_on,String objstatus,Date 	generatedyear,String notice_flag) {
		super();
		this.generatedtaxId = generatedtaxId;
		this.uniqueTaxId = uniqueTaxId;
		this.propertyUniqueId = propertyUniqueId;
		this.taxAmount = taxAmount;
		this.builtUpArea = builtUpArea;
		this.rentableValue = rentableValue;
		this.generatedby = generatedby;
//		this.generatedon = generatedon;
		this.zonename = zonename;
		this.remarks = remarks;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.objstatus = objstatus;
		this.generatedyear = generatedyear;
		this.notice_flag = notice_flag;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "generatedtax_id", unique = true, columnDefinition = "serial")
	public int getGeneratedtaxId() {
		return this.generatedtaxId;
	}

	public void setGeneratedtaxId(int generatedtaxId) {
		this.generatedtaxId = generatedtaxId;
	}

	@Column(name = "unique_tax_id")
	public String getUniqueTaxId() {
		return this.uniqueTaxId;
	}

	public void setUniqueTaxId(String uniqueTaxId) {
		this.uniqueTaxId = uniqueTaxId;
	}

	@Column(name = "property_unique_id")
	public String getPropertyUniqueId() {
		return this.propertyUniqueId;
	}

	public void setPropertyUniqueId(String propertyUniqueId) {
		this.propertyUniqueId = propertyUniqueId;
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

//	@Column(name = "generatedon")
//	public String getGeneratedon() {
//		return this.generatedon;
//	}
//
//	public void setGeneratedon(String generatedon) {
//		this.generatedon = generatedon;
//	}

//	@Column(name = "generatedon")
//	public String getGeneratedon() {
//		return this.generatedon;
//	}
//
//	public void setGeneratedon(String generatedon) {
//		this.generatedon = generatedon;
//	}
//


	/**
	 * @return the zonename
	 */
	@Column(name = "zonename")
	public String getZonename() {
		return zonename;
	}

	/**
	 * @param zonename the zonename to set
	 */
	public void setZonename(String zonename) {
		this.zonename = zonename;
	}

	/**
	 * @return the remarks
	 */
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "modified_by")
	
	/**
	 * @return the modified_by
	 */
	public String getModified_by() {
		return modified_by;
	}

	/**
	 * @param modified_by the modified_by to set
	 */
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_on")
	/**
	 * @return the modified_on
	 */
	public Date getModified_on() {
		return modified_on;
	}

	/**
	 * @param modified_on the modified_on to set
	 */
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	/**
	 * @return the objstatus
	 */
	
	@Column(name = "objstatus")
	public String getObjstatus() {
		return objstatus;
	}

	/**
	 * @param objstatus the objstatus to set
	 */
	public void setObjstatus(String objstatus) {
		this.objstatus = objstatus;
	}

	
	
	/**
	 * @return the generatedyear
	 */
	@Column(name = "generatedyear")
	public Date getGeneratedyear() {
		return generatedyear;
	}

	/**
	 * @param generatedyear the generatedyear to set
	 */
	public void setGeneratedyear(Date generatedyear) {
		this.generatedyear = generatedyear;
	}

	/**
	 * @return the prop_type
	 */
@Column(name = "prop_type")
	public String getProp_type() {
		return prop_type;
	}

	/**
	 * @param prop_type the prop_type to set
	 */
	public void setProp_type(String prop_type) {
		this.prop_type = prop_type;
	}

	/**
	 * @return the notice_flag
	 */
	@Column(name = "notice_flag")
	public String getNotice_flag() {
		return notice_flag;
	}

	/**
	 * @param notice_flag the notice_flag to set
	 */
	public void setNotice_flag(String notice_flag) {
		this.notice_flag = notice_flag;
	}
	
	
	
}
