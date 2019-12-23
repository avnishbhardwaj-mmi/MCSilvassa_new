package com.silvassa.bean;

public class PermissionBean {
	private int userPermissionId;
	private String userRoleId;
	private int permissionId;
	private String menuId ;
	private String menuDescription;
	private String ischecked;
	public String getIschecked() {
		return ischecked;
	}
	public void setIschecked(String ischecked) {
		this.ischecked = ischecked;
	}
	public int getUserPermissionId() {
		return userPermissionId;
	}
	public void setUserPermissionId(int userPermissionId) {
		this.userPermissionId = userPermissionId;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuDescription() {
		return menuDescription;
	}
	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
	@Override
	public String toString() {
		return "PermissionBean [userPermissionId=" + userPermissionId
				+ ", userRoleId=" + userRoleId + ", permissionId="
				+ permissionId + ", menuId=" + menuId + ", menuDescription="
				+ menuDescription + ", ischecked=" + ischecked + "]";
	}
	
}


