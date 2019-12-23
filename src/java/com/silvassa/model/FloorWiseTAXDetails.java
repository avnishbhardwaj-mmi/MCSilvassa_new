package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "property_tax_details")
public class FloorWiseTAXDetails {

    @Id
    @Column(name = "property_tax_details_id")
    private String sno;

    @Column(name = "tax_no")
    private String taxNo;

    @Column(name = "floor_id")
    private Integer floorId;

    @Column(name = "floor_name")
    private String floorName;

    @Column(name = "property_rentable_id")
    private String propertyRentableId;

    @Column(name = "floor_tax_amount")
    private String floorTaxAmount;

    @Column(name = "other_tax_amount")
    private String otherTAX;

    @Column(name = "total_tax_annual")
    private String totalTaxAmount;

    /**
     * @return the sno
     */
    public String getSno() {
        return sno;
    }

    /**
     * @param sno the sno to set
     */
    public void setSno(String sno) {
        this.sno = sno;
    }

    /**
     * @return the taxNo
     */
    public String getTaxNo() {
        return taxNo;
    }

    /**
     * @param taxNo the taxNo to set
     */
    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    /**
     * @return the floorId
     */
    public Integer getFloorId() {
        return floorId;
    }

    /**
     * @param floorId the floorId to set
     */
    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    /**
     * @return the floorName
     */
    public String getFloorName() {
        return floorName;
    }

    /**
     * @param floorName the floorName to set
     */
    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    /**
     * @return the propertyRentableId
     */
    public String getPropertyRentableId() {
        return propertyRentableId;
    }

    /**
     * @param propertyRentableId the propertyRentableId to set
     */
    public void setPropertyRentableId(String propertyRentableId) {
        this.propertyRentableId = propertyRentableId;
    }

    /**
     * @return the floorTaxAmount
     */
    public String getFloorTaxAmount() {
        return floorTaxAmount;
    }

    /**
     * @param floorTaxAmount the floorTaxAmount to set
     */
    public void setFloorTaxAmount(String floorTaxAmount) {
        this.floorTaxAmount = floorTaxAmount;
    }

    /**
     * @return the otherTAX
     */
    public String getOtherTAX() {
        return otherTAX;
    }

    /**
     * @param otherTAX the otherTAX to set
     */
    public void setOtherTAX(String otherTAX) {
        this.otherTAX = otherTAX;
    }

    /**
     * @return the totalTaxAmount
     */
    public String getTotalTaxAmount() {
        return totalTaxAmount;
    }

    /**
     * @param totalTaxAmount the totalTaxAmount to set
     */
    public void setTotalTaxAmount(String totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    @Override
    public String toString() {
        return "FloorWiseTAXDetails{" + "sno=" + sno + ", taxNo=" + taxNo + ", floorId=" + floorId + ", floorName=" + floorName + ", propertyRentableId=" + propertyRentableId + ", floorTaxAmount=" + floorTaxAmount + ", otherTAX=" + otherTAX + ", totalTaxAmount=" + totalTaxAmount + '}';
    }

}
