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
 * PropertytypeMaster generated by hbm2java
 */
@Entity
@Table(name = "propertytype_master", schema = "public")
public class PropertytypeMaster implements java.io.Serializable {

	private int propertyTypeMasterId;
	private String propertyTypeId;
	private String propertyTypeName;
	private String status;
	private String createdBy;
	private Date createdOn;
	private Date modifiedOn;

	public PropertytypeMaster() {
	}

	public PropertytypeMaster(int propertyTypeMasterId) {
		this.propertyTypeMasterId = propertyTypeMasterId;
	}

	public PropertytypeMaster(int propertyTypeMasterId, String propertyTypeId,
			String propertyTypeName, String status, String createdBy,
			Date createdOn, Date modifiedOn) {
		this.propertyTypeMasterId = propertyTypeMasterId;
		this.propertyTypeId = propertyTypeId;
		this.propertyTypeName = propertyTypeName;
		this.status = status;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}

	@Id
	@Column(name = "property_type_master_id", unique = true, nullable = false)
	public int getPropertyTypeMasterId() {
		return this.propertyTypeMasterId;
	}

	public void setPropertyTypeMasterId(int propertyTypeMasterId) {
		this.propertyTypeMasterId = propertyTypeMasterId;
	}

	@Column(name = "property_type_id")
	public String getPropertyTypeId() {
		return this.propertyTypeId;
	}

	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	@Column(name = "property_type_name")
	public String getPropertyTypeName() {
		return this.propertyTypeName;
	}

	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
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