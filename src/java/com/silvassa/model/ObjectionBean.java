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
import javax.persistence.Transient;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "cm_objection")
public class ObjectionBean {

    @Id
    @SequenceGenerator(name = "objection_sno_seq", sequenceName = "cm_objection_sno_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objection_sno_seq")
    @Column(name = "sno")
    private Long sno;

    @Column(name = "objectionid")
    private String objectionId;
    @Column(name = "propertyid")
    private String propertyId;
    @Transient
    private String cat;
    @Transient
    private String catName;
    @Column(name = "attr")
    private String attr;
    @Column(name = "attrname")
    private String attrName;
    @Column(name = "iscomplex")
    private String isComplex;
    @Column(name = "floor")
    private String floor;
    @Column(name = "currentvalue")
    private String value;
    @Column(name = "newvalue")
    private String newValue;

    /**
     * @return the objectionId
     */
    public String getObjectionId() {
        return objectionId;
    }

    /**
     * @param objectionId the objectionId to set
     */
    public void setObjectionId(String objectionId) {
        this.objectionId = objectionId;
    }

    /**
     * @return the propertyId
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * @param propertyId the propertyId to set
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * @return the cat
     */
    public String getCat() {
        return cat;
    }

    /**
     * @param cat the cat to set
     */
    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     * @return the catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param catName the catName to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * @return the attr
     */
    public String getAttr() {
        return attr;
    }

    /**
     * @param attr the attr to set
     */
    public void setAttr(String attr) {
        this.attr = attr;
    }

    /**
     * @return the attrName
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * @param attrName the attrName to set
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    /**
     * @return the isComplex
     */
    public String getIsComplex() {
        return isComplex;
    }

    /**
     * @param isComplex the isComplex to set
     */
    public void setIsComplex(String isComplex) {
        this.isComplex = isComplex;
    }

    /**
     * @return the floor
     */
    public String getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the newValue
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * @param newValue the newValue to set
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    /**
     * @return the sno
     */
    public Long getSno() {
        return sno;
    }

    /**
     * @param sno the sno to set
     */
    public void setSno(Long sno) {
        this.sno = sno;
    }

    @Override
    public String toString() {
        return "ObjectionBean{" + "sno=" + sno + ", objectionId=" + objectionId + ", propertyId=" + propertyId + ", cat=" + cat + ", catName=" + catName + ", attr=" + attr + ", attrName=" + attrName + ", isComplex=" + isComplex + ", floor=" + floor + ", value=" + value + ", newValue=" + newValue + '}';
    }



    

}
