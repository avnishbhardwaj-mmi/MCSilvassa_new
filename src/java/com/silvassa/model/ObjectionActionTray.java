/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "obj_action_tray")
public class ObjectionActionTray {

    @Id
    @SequenceGenerator(name = "obj_action_tray_sno_seq", sequenceName = "obj_action_tray_sno_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obj_action_tray_sno_seq")
    @Column(name = "sno")
    private Integer sno;
    @Column(name = "objection_id")
    private String objectionId;
    @Column(name = "property_id")
    private String propertyId;
    @Column(name = "tray_owner")
    private String trayOwner;
    @Column(name = "tray_owner_name")
    private String trayOwnerName;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getObjectionId() {
        return objectionId;
    }

    public void setObjectionId(String objectionId) {
        this.objectionId = objectionId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getTrayOwner() {
        return trayOwner;
    }

    public void setTrayOwner(String trayOwner) {
        this.trayOwner = trayOwner;
    }

    public String getTrayOwnerName() {
        return trayOwnerName;
    }

    public void setTrayOwnerName(String trayOwnerName) {
        this.trayOwnerName = trayOwnerName;
    }

    @Override
    public String toString() {
        return "ObjectionActionTray{" + "sno=" + sno + ", objectionId=" + objectionId + ", propertyId=" + propertyId + ", trayOwner=" + trayOwner + ", trayOwnerName=" + trayOwnerName + '}';
    }

}
