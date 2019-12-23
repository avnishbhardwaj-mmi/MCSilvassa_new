package com.silvassa.bean;

import java.util.Arrays;

public class FilterBean {

    String zoneId;
    String ward;
    String zoneName;
    String propertyId;
    String roleId;
    String[] permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String[] getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String[] permissionId) {
        this.permissionId = permissionId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    @Override
    public String toString() {
        return "FilterBean{" + "zoneId=" + zoneId + ", ward=" + ward + ", zoneName=" + zoneName + ", propertyId=" + propertyId + ", roleId=" + roleId + ", permissionId=" + permissionId + '}';
    }

    
    
}
