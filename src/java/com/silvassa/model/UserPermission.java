package com.silvassa.model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "user_permission", schema = "public")
public class UserPermission implements java.io.Serializable {

	private int userPermissionId;
	private String userRoleId;
	private Integer permissionId;
	private String active;
	private String createdBy;
	private Date createdOn;
	private Date modifiedOn;



	@Id
	@Column(name = "user_permission_id", unique = true, nullable = false)
	public int getUserPermissionId() {
		return this.userPermissionId;
	}

	public void setUserPermissionId(int userPermissionId) {
		this.userPermissionId = userPermissionId;
	}

	@Column(name = "user_role_id")
	public String getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Column(name = "permission_id")
	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name = "active")
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on")
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_on")
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	@Override
	public String toString() {
		return "UserPermission [userPermissionId=" + userPermissionId
				+ ", userRoleId=" + userRoleId + ", permissionId="
				+ permissionId + ", active=" + active + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + "]";
	}

}
