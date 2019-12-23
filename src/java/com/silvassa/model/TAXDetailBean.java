package com.silvassa.model;

import com.silvassa.util.MMIUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.annotations.Formula;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "property_tax")
public class TAXDetailBean {

    @Id
    @Column(name = "tax_no")
    private String taxNo;

    @Column(name = "property_unique_id")
    private String propertyId;

    @Column(name = "financial_year")
    private String financialYear;

    @Column(name = "property_tax")
    private String propertyTax;

    @Column(name = "water_tax")
    private String waterTax;

    @Column(name = "conservancy_tax")
    private String conservancyTax;

    @Column(name = "water_sewerage_charge")
    private String waterSewerageCharge;

    @Column(name = "water_meter_bill_amount")
    private String waterMeterBillAmount;

    @Column(name = "arrear_amount")
    private String arrearAmount;

    @Column(name = "advance_paid_amount")
    private String advancePaidAmount;

    @Column(name = "rebate_amount")
    private String rebateAmount;

    @Column(name = "adjustment_amount")
    private String adjustmentAmount;

    @Column(name = "total_property_tax")
    private String totalPropertyTax;

    @Column(name = "service_tax")
    private String serviceTax;

    @Column(name = "other_tax")
    private String otherTax;

    @Column(name = "grand_total")
    private String grandTotal;

    @Column(name = "delay_payment_charges")
    private String delayPaymentCharges;

    @Column(name = "payable_amount")
    private String payableAmount;

    @Column(name = "duedate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dueDate;

    @Column(name = "notice_generated")
    private String noticeGenerated;

    @Column(name = "objection_status")
    private String objectionStatus;

    @Column(name = "generated_on")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date generatedOn;

    @Column(name = "generated_by")
    private String generatedBy;

    @Transient
    private List<FloorWiseTAXDetails> floorWiseTAXDetails;

    @Formula("cast(grand_total as NUMERIC(10,2))")
    private BigDecimal grandTotalAsInt;

    
    @Transient
    private String oldtaxAmount;

    public String getOldtaxAmount() {
        return oldtaxAmount;
    }

    public void setOldtaxAmount(String oldtaxAmount) {
        this.oldtaxAmount = oldtaxAmount;
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
     * @return the financialYear
     */
    public String getFinancialYear() {
        return financialYear;
    }

    /**
     * @param financialYear the financialYear to set
     */
    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    /**
     * @return the propertyTax
     */
    public String getPropertyTax() {
        return propertyTax;
    }

    /**
     * @param propertyTax the propertyTax to set
     */
    public void setPropertyTax(String propertyTax) {
        this.propertyTax = propertyTax;
    }

    /**
     * @return the waterTax
     */
    public String getWaterTax() {
        return waterTax;
    }

    /**
     * @param waterTax the waterTax to set
     */
    public void setWaterTax(String waterTax) {
        this.waterTax = waterTax;
    }

    /**
     * @return the conservancyTax
     */
    public String getConservancyTax() {
        return conservancyTax;
    }

    /**
     * @param conservancyTax the conservancyTax to set
     */
    public void setConservancyTax(String conservancyTax) {
        this.conservancyTax = conservancyTax;
    }

    /**
     * @return the waterSewerageCharge
     */
    public String getWaterSewerageCharge() {
        return waterSewerageCharge;
    }

    /**
     * @param waterSewerageCharge the waterSewerageCharge to set
     */
    public void setWaterSewerageCharge(String waterSewerageCharge) {
        this.waterSewerageCharge = waterSewerageCharge;
    }

    /**
     * @return the waterMeterBillAmount
     */
    public String getWaterMeterBillAmount() {
        return waterMeterBillAmount;
    }

    /**
     * @param waterMeterBillAmount the waterMeterBillAmount to set
     */
    public void setWaterMeterBillAmount(String waterMeterBillAmount) {
        this.waterMeterBillAmount = waterMeterBillAmount;
    }

    /**
     * @return the arrearAmount
     */
    public String getArrearAmount() {
        return arrearAmount;
    }

    /**
     * @param arrearAmount the arrearAmount to set
     */
    public void setArrearAmount(String arrearAmount) {
        this.arrearAmount = arrearAmount;
    }

    /**
     * @return the advancePaidAmount
     */
    public String getAdvancePaidAmount() {
        return advancePaidAmount;
    }

    /**
     * @param advancePaidAmount the advancePaidAmount to set
     */
    public void setAdvancePaidAmount(String advancePaidAmount) {
        this.advancePaidAmount = advancePaidAmount;
    }

    /**
     * @return the rebateAmount
     */
    public String getRebateAmount() {
        return rebateAmount;
    }

    /**
     * @param rebateAmount the rebateAmount to set
     */
    public void setRebateAmount(String rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    /**
     * @return the adjustmentAmount
     */
    public String getAdjustmentAmount() {
        return adjustmentAmount;
    }

    /**
     * @param adjustmentAmount the adjustmentAmount to set
     */
    public void setAdjustmentAmount(String adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    /**
     * @return the totalPropertyTax
     */
    public String getTotalPropertyTax() {
        return totalPropertyTax;
    }

    /**
     * @param totalPropertyTax the totalPropertyTax to set
     */
    public void setTotalPropertyTax(String totalPropertyTax) {
        this.totalPropertyTax = totalPropertyTax;
    }

    /**
     * @return the serviceTax
     */
    public String getServiceTax() {
        return serviceTax;
    }

    /**
     * @param serviceTax the serviceTax to set
     */
    public void setServiceTax(String serviceTax) {
        this.serviceTax = serviceTax;
    }

    /**
     * @return the otherTax
     */
    public String getOtherTax() {
        return otherTax;
    }

    /**
     * @param otherTax the otherTax to set
     */
    public void setOtherTax(String otherTax) {
        this.otherTax = otherTax;
    }

    /**
     * @return the grandTotal
     */
    public String getGrandTotal() {
        return grandTotal;
    }

    /**
     * @param grandTotal the grandTotal to set
     */
    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * @return the delayPaymentCharges
     */
    public String getDelayPaymentCharges() {
        return delayPaymentCharges;
    }

    /**
     * @param delayPaymentCharges the delayPaymentCharges to set
     */
    public void setDelayPaymentCharges(String delayPaymentCharges) {
        this.delayPaymentCharges = delayPaymentCharges;
    }

    /**
     * @return the payableAmount
     */
    public String getPayableAmount() {
        return payableAmount;
    }

    /**
     * @param payableAmount the payableAmount to set
     */
    public void setPayableAmount(String payableAmount) {
        this.payableAmount = payableAmount;
    }

    /**
     * @return the floorWiseTAXDetails
     */
    public List<FloorWiseTAXDetails> getFloorWiseTAXDetails() {
        return floorWiseTAXDetails;
    }

    /**
     * @param floorWiseTAXDetails the floorWiseTAXDetails to set
     */
    public void setFloorWiseTAXDetails(List<FloorWiseTAXDetails> floorWiseTAXDetails) {
        this.floorWiseTAXDetails = floorWiseTAXDetails;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {

        Date d = null;
        try {
            d = new SimpleDateFormat("mm-dd-yyyy").parse(MMIUtil.formateDate(dueDate));
        } catch (Exception e) {
            d = dueDate;
        }

        this.dueDate = d;
    }

    public String getNoticeGenerated() {
        return noticeGenerated;
    }

    public void setNoticeGenerated(String noticeGenerated) {
        this.noticeGenerated = noticeGenerated;
    }

    public String getObjectionStatus() {
        return objectionStatus;
    }

    public void setObjectionStatus(String objectionStatus) {
        this.objectionStatus = objectionStatus;
    }

    public Date getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(Date generatedOn) {
        Date d = null;
        try {
            d = new SimpleDateFormat("mm-dd-yyyy").parse(MMIUtil.formateDate(generatedOn));
        } catch (Exception e) {
            d = generatedOn;
        }

        this.generatedOn = d;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    @Override
    public String toString() {
        return "TAXDetailBean{" + "taxNo=" + taxNo + ", propertyId=" + propertyId + ", financialYear=" + financialYear + ", propertyTax=" + propertyTax + ", waterTax=" + waterTax + ", conservancyTax=" + conservancyTax + ", waterSewerageCharge=" + waterSewerageCharge + ", waterMeterBillAmount=" + waterMeterBillAmount + ", arrearAmount=" + arrearAmount + ", advancePaidAmount=" + advancePaidAmount + ", rebateAmount=" + rebateAmount + ", adjustmentAmount=" + adjustmentAmount + ", totalPropertyTax=" + totalPropertyTax + ", serviceTax=" + serviceTax + ", otherTax=" + otherTax + ", grandTotal=" + grandTotal + ", delayPaymentCharges=" + delayPaymentCharges + ", payableAmount=" + payableAmount + ", dueDate=" + dueDate + ", noticeGenerated=" + noticeGenerated + ", objectionStatus=" + objectionStatus + ", generatedOn=" + generatedOn + ", generatedBy=" + generatedBy + ", floorWiseTAXDetails=" + floorWiseTAXDetails + '}';
    }

    /**
     * @return the grandTotalAsInt
     */
    public BigDecimal getGrandTotalAsInt() {
        return grandTotalAsInt;
    }

    /**
     * @param grandTotalAsInt the grandTotalAsInt to set
     */
    public void setGrandTotalAsInt(BigDecimal grandTotalAsInt) {
        this.grandTotalAsInt = grandTotalAsInt;
    }

}
