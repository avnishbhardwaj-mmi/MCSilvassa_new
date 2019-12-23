package com.silvassa.bean;

public class PropTaxCalculationBean {

	  
    private String floor, builtArea, propertySubCat, propertyCat, mFactor, rentableValue, taxAmount;
    private String objfloor_bultup_area;
    private String objfloor_property_cat;

    
    
    
    /**
	 * @return the objfloor_bultup_area
	 */
	public String getObjfloor_bultup_area() {
		return objfloor_bultup_area;
	}

	/**
	 * @param objfloor_bultup_area the objfloor_bultup_area to set
	 */
	public void setObjfloor_bultup_area(String objfloor_bultup_area) {
		this.objfloor_bultup_area = objfloor_bultup_area;
	}

	/**
	 * @return the objfloor_property_cat
	 */
	public String getObjfloor_property_cat() {
		return objfloor_property_cat;
	}

	/**
	 * @param objfloor_property_cat the objfloor_property_cat to set
	 */
	public void setObjfloor_property_cat(String objfloor_property_cat) {
		this.objfloor_property_cat = objfloor_property_cat;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PropTaxCalculationBean [floor=" + floor + ", builtArea="
				+ builtArea + ", propertySubCat=" + propertySubCat
				+ ", propertyCat=" + propertyCat + ", mFactor=" + mFactor
				+ ", rentableValue=" + rentableValue + ", taxAmount="
				+ taxAmount + ", objfloor_bultup_area=" + objfloor_bultup_area
				+ ", objfloor_property_cat=" + objfloor_property_cat + "]";
	}

   
    
}
