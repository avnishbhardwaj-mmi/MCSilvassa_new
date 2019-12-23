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
 * FloorTypeMaster generated by hbm2java
 */
@Entity
@Table(name = "floor_type_master", schema = "public")
public class FloorTypeMaster implements java.io.Serializable {

	private int floorTypeMasterId;
	private String floorTypeId;
	private String floorTypeDesc;
	private String status;
	private String createdBy;
	private Date createdOn;
	private Date modifiedOn;

	public FloorTypeMaster() {
	}

	public FloorTypeMaster(int floorTypeMasterId) {
		this.floorTypeMasterId = floorTypeMasterId;
	}

	public FloorTypeMaster(int floorTypeMasterId, String floorTypeId,
			String floorTypeDesc, String status, String createdBy,
			Date createdOn, Date modifiedOn) {
		this.floorTypeMasterId = floorTypeMasterId;
		this.floorTypeId = floorTypeId;
		this.floorTypeDesc = floorTypeDesc;
		this.status = status;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}

	@Id
	@Column(name = "floor_type_master_id", unique = true, nullable = false)
	public int getFloorTypeMasterId() {
		return this.floorTypeMasterId;
	}

	public void setFloorTypeMasterId(int floorTypeMasterId) {
		this.floorTypeMasterId = floorTypeMasterId;
	}

	@Column(name = "floor_type_id")
	public String getFloorTypeId() {
		return this.floorTypeId;
	}

	public void setFloorTypeId(String floorTypeId) {
		this.floorTypeId = floorTypeId;
	}

	@Column(name = "floor_type_desc")
	public String getFloorTypeDesc() {
		return this.floorTypeDesc;
	}

	public void setFloorTypeDesc(String floorTypeDesc) {
		this.floorTypeDesc = floorTypeDesc;
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