/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "editable_attr_master")
public class PropertyEditableFields implements Serializable {

    @Column(name = "category")
    private String category;
    @Id
    @Column(name = "editable_attr_master_id")
    private String key;
    @Column(name = "field_associated")
    private String fieldAssociated;
    @Column(name = "editable")
    private String editable;
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "column_name")
    private String columnName;
    @Column(name = "effecting_tax")
    private String effectingTax;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "model_attr")
    private String modelAttr;
    @Column(name = "iscomplex")
    private String isComplex;

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the fieldAssociated
     */
    public String getFieldAssociated() {
        return fieldAssociated;
    }

    /**
     * @param fieldAssociated the fieldAssociated to set
     */
    public void setFieldAssociated(String fieldAssociated) {
        this.fieldAssociated = fieldAssociated;
    }

    /**
     * @return the editable
     */
    public String getEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(String editable) {
        this.editable = editable;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return the effectingTax
     */
    public String getEffectingTax() {
        return effectingTax;
    }

    /**
     * @param effectingTax the effectingTax to set
     */
    public void setEffectingTax(String effectingTax) {
        this.effectingTax = effectingTax;
    }

    @Override
    public String toString() {
        return "ObjectionMaster{" + "category=" + category + ", fieldAssociated=" + fieldAssociated + ", editable=" + editable + ", tableName=" + tableName + ", columnName=" + columnName + ", effectingTax=" + effectingTax + '}';
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the modelAttr
     */
    public String getModelAttr() {
        return modelAttr;
    }

    /**
     * @param modelAttr the modelAttr to set
     */
    public void setModelAttr(String modelAttr) {
        this.modelAttr = modelAttr;
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

}
