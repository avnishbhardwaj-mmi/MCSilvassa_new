// default package
// Generated Jan 27, 2017 4:07:12 PM by Hibernate Tools 4.0.0
package com.silvassa.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PropertyRentable generated by hbm2java
 */
@Entity
@Table(name = "property_rentable", schema = "public")
public class PropertyRentable implements java.io.Serializable {

    private String propertyRentableId;
    private int id;
    private String propertyCatCode;
    private String propertyCat;
    private String propertySubcatCode;
    private String propertySubCat;
    private BigDecimal rentableValue;
    private BigDecimal multiplicationFactor;

    @Id
    @Column(name = "property_rentable_id", unique = true, nullable = false)
    public String getPropertyRentableId() {
        return this.propertyRentableId;
    }

    public void setPropertyRentableId(String propertyRentableId) {
        this.propertyRentableId = propertyRentableId;
    }

    @Column(name = "id", nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "property_cat_code")
    public String getPropertyCatCode() {
        return this.propertyCatCode;
    }

    public void setPropertyCatCode(String propertyCatCode) {
        this.propertyCatCode = propertyCatCode;
    }

    @Column(name = "property_cat")
    public String getPropertyCat() {
        return this.propertyCat;
    }

    public void setPropertyCat(String propertyCat) {
        this.propertyCat = propertyCat;
    }

    @Column(name = "property_subcat_code")
    public String getPropertySubcatCode() {
        return this.propertySubcatCode;
    }

    public void setPropertySubcatCode(String propertySubcatCode) {
        this.propertySubcatCode = propertySubcatCode;
    }

    @Column(name = "property_sub_cat")
    public String getPropertySubCat() {
        return this.propertySubCat;
    }

    public void setPropertySubCat(String propertySubCat) {
        this.propertySubCat = propertySubCat;
    }

    @Column(name = "rentable_value", precision = 131089, scale = 0)
    public BigDecimal getRentableValue() {
        return this.rentableValue;
    }

    public void setRentableValue(BigDecimal rentableValue) {
        this.rentableValue = rentableValue;
    }

    @Column(name = "multiplication_factor", precision = 131089, scale = 0)
    public BigDecimal getMultiplicationFactor() {
        return this.multiplicationFactor;
    }

    public void setMultiplicationFactor(BigDecimal multiplicationFactor) {
        this.multiplicationFactor = multiplicationFactor;
    }

}
