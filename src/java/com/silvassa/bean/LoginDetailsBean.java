package com.silvassa.bean;

import java.util.Map;

public class LoginDetailsBean {
	private String userName;
	private String userPassword;
	private String userPermissionList;
	private Map<String,String> userPermissionMap;

	private String userId;
	private String roleId;
	public Map<String, String> getUserPermissionMap() {
		return userPermissionMap;
	}
	public void setUserPermissionMap(Map<String, String> userPermissionMap) {
		this.userPermissionMap = userPermissionMap;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPermissionList() {
		return userPermissionList;
	}
	public void setUserPermissionList(String userPermissionList) {
		this.userPermissionList = userPermissionList;
	}

    @Override
    public String toString() {
        return "LoginDetailsBean{" + "userName=" + userName + ", userPassword=" + userPassword + ", userPermissionList=" + userPermissionList + ", userPermissionMap=" + userPermissionMap + ", userId=" + userId + ", roleId=" + roleId + '}';
    }
	

}
