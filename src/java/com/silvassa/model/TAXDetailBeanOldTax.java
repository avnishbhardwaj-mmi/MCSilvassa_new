package com.silvassa.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "property_tax")
public class TAXDetailBeanOldTax {

    @Id
    @Column(name = "tax_no")
    private String taxNo;

    @Column(name = "property_unique_id")
    private String propertyId;

    @Column(name = "financial_year")
    private String financialYear;

    @Column(name = "oldtax")
    private String oldtaxAmount;

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getOldtaxAmount() {
        return oldtaxAmount;
    }

    public void setOldtaxAmount(String oldtaxAmount) {
        this.oldtaxAmount = oldtaxAmount;
    }

    @Override
    public String toString() {
        return "TAXDetailBeanOldTax{" + "taxNo=" + taxNo + ", propertyId=" + propertyId + ", financialYear=" + financialYear + ", oldtaxAmount=" + oldtaxAmount + '}';
    }

    
}
