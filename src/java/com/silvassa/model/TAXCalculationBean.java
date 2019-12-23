package com.silvassa.model;

public class TAXCalculationBean {
    
    private String floor, builtArea, propertySubCat, propertyCat, mFactor, rentableValue, taxAmount;

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuiltArea() {
        return builtArea;
    }

    public void setBuiltArea(String builtArea) {
        this.builtArea = builtArea;
    }

    public String getPropertySubCat() {
        return propertySubCat;
    }

    public void setPropertySubCat(String propertySubCat) {
        this.propertySubCat = propertySubCat;
    }

    public String getPropertyCat() {
        return propertyCat;
    }

    public void setPropertyCat(String propertyCat) {
        this.propertyCat = propertyCat;
    }

    public String getmFactor() {
        return mFactor;
    }

    public void setmFactor(String mFactor) {
        this.mFactor = mFactor;
    }

    public String getRentableValue() {
        return rentableValue;
    }

    public void setRentableValue(String rentableValue) {
        this.rentableValue = rentableValue;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    @Override
    public String toString() {
	return "TAXCalculationBean [floor=" + floor + ", builtArea="
		+ builtArea + ", propertySubCat=" + propertySubCat
		+ ", propertyCat=" + propertyCat + ", mFactor=" + mFactor
		+ ", rentableValue=" + rentableValue + ", taxAmount="
		+ taxAmount + "]";
    }
    
    
}
