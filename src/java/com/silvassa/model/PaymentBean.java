package com.silvassa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sl_payment")
public class PaymentBean {

    // Identification Details
    @Id
    @Column(name = "payrefid")
    private String payRefId;
    @Column(name = "propid")
    private String propId;
    @Column(name = "taxno")
    private String taxNo;

    // Amount Details
    @Column(name = "amopuntdemand")
    private String amountDemand;
    @Column(name = "amopuntpaid")
    private String amountPaid;
    @Column(name = "pendingamount")
    private String pendingAmount;
    @Column(name = "latepaymentstatus")
    private String latePaymentStatus;
    @Column(name = "latepaymentcharge")
    private String latePaymentCharge;
    @Column(name = "arrearstatus")
    private String arrearStatus;
    @Column(name = "arrear")
    private String arrear;
    @Column(name = "rebatestatus")
    private String rebateStatus;
    @Column(name = "rebateonlinepayment")
    private String rebateOnlinePayment;
    @Column(name = "rebateother")
    private String rebateOther;
    @Column(name = "partialpaymentstatus")
    private String partialPaymentStatus;
    @Column(name = "partialcollectedamount")
    private String partialCollectedAmount;

    // Gateway Response
    @Column(name = "status")
    private String status;
    @Column(name = "bankrefid")
    private String bankRefId;
    @Column(name = "bankstatus")
    private String bankStatus;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "paymentmode")
    private String paymentMode;
    @Column(name = "cheque_dd_num")
    private String chequeDDNum;
    @Column(name = "cheque_dd_date", nullable = true)
    private String chequeDDDate;
    @Column(name = "bankname")
    private String bankName;
    @Column(name = "bankbranch")
    private String bankBranch;

    @Column(name = "entrydatetime")
    private Date entryDateTime;

    @Column(name = "payment_app")
    private String paymentApp;

    @Transient
    private Date dueDate;

    @Transient
    private Date billDate;

    @Transient
    private List<FloorWiseTAXDetails> floorWiseTAXDetails;

    @Column(name = "financial_year")
    private String financialYear;
    @Column(name="payer_name")
    private String payerName;
    @Column(name="contactno")
    private String contactNo;
    @Column(name="paymentPeriod")
    private String paymentPeriod;
    @Column(name="ifsc_code")
    private String ifscCode;
    
    @Transient
    private String ReceiptDate;
    @Transient
    private String totalPayment;
    
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getPayRefId() {
        return payRefId;
    }

    public void setPayRefId(String payRefId) {
        this.payRefId = payRefId;
    }

    public String getPropId() {
        return propId;
    }

    public List<FloorWiseTAXDetails> getFloorWiseTAXDetails() {
        return floorWiseTAXDetails;
    }

    public void setFloorWiseTAXDetails(List<FloorWiseTAXDetails> floorWiseTAXDetails) {
        this.floorWiseTAXDetails = floorWiseTAXDetails;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }

    /**
     * @return the amountDemand
     */
    public String getAmountDemand() {
        return amountDemand;
    }

    /**
     * @param amountDemand the amountDemand to set
     */
    public void setAmountDemand(String amountDemand) {
        this.amountDemand = amountDemand;
    }

    /**
     * @return the amountPaid
     */
    public String getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(String pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public String getLatePaymentStatus() {
        return latePaymentStatus;
    }

    public void setLatePaymentStatus(String latePaymentStatus) {
        this.latePaymentStatus = latePaymentStatus;
    }

    public String getLatePaymentCharge() {
        return latePaymentCharge;
    }

    public void setLatePaymentCharge(String latePaymentCharge) {
        this.latePaymentCharge = latePaymentCharge;
    }

    public String getArrearStatus() {
        return arrearStatus;
    }

    public void setArrearStatus(String arrearStatus) {
        this.arrearStatus = arrearStatus;
    }

    public String getArrear() {
        return arrear;
    }

    public void setArrear(String arrear) {
        this.arrear = arrear;
    }

    public String getRebateStatus() {
        return rebateStatus;
    }

    public void setRebateStatus(String rebateStatus) {
        this.rebateStatus = rebateStatus;
    }

    public String getRebateOnlinePayment() {
        return rebateOnlinePayment;
    }

    public void setRebateOnlinePayment(String rebateOnlinePayment) {
        this.rebateOnlinePayment = rebateOnlinePayment;
    }

    public String getRebateOther() {
        return rebateOther;
    }

    public void setRebateOther(String rebateOther) {
        this.rebateOther = rebateOther;
    }

    public String getPartialPaymentStatus() {
        return partialPaymentStatus;
    }

    public void setPartialPaymentStatus(String partialPaymentStatus) {
        this.partialPaymentStatus = partialPaymentStatus;
    }

    public String getPartialCollectedAmount() {
        return partialCollectedAmount;
    }

    public void setPartialCollectedAmount(String partialCollectedAmount) {
        this.partialCollectedAmount = partialCollectedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankRefId() {
        return bankRefId;
    }

    public void setBankRefId(String bankRefId) {
        this.bankRefId = bankRefId;
    }

    public String getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(String bankStatus) {
        this.bankStatus = bankStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Date getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(Date entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getPaymentApp() {
        return paymentApp;
    }

    public void setPaymentApp(String paymentApp) {
        this.paymentApp = paymentApp;
    }

    public String getChequeDDNum() {
        return chequeDDNum;
    }

    public void setChequeDDNum(String chequeDDNum) {
        this.chequeDDNum = chequeDDNum;
    }

    public String getChequeDDDate() {
        return chequeDDDate;
    }

    public void setChequeDDDate(String chequeDDDate) {
        this.chequeDDDate = chequeDDDate;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    

    /**
     * @return the payerName
     */
    public String getPayerName() {
        return payerName;
    }

    /**
     * @param payerName the payerName to set
     */
    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * @return the ReceiptDate
     */
    public String getReceiptDate() {
        return ReceiptDate;
    }

    /**
     * @param ReceiptDate the ReceiptDate to set
     */
    public void setReceiptDate(String ReceiptDate) {
        this.ReceiptDate = ReceiptDate;
    }

    /**
     * @return the totalPayment
     */
    public String getTotalPayment() {
        return totalPayment;
    }

    /**
     * @param totalPayment the totalPayment to set
     */
    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    /**
     * @return the paymentPeriod
     */
    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    /**
     * @param paymentPeriod the paymentPeriod to set
     */
    public void setPaymentPeriod(String paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    @Override
    public String toString() {
        return "PaymentBean{" + "payRefId=" + payRefId + ", propId=" + propId + ", taxNo=" + taxNo + ", amountDemand=" + amountDemand + ", amountPaid=" + amountPaid + ", pendingAmount=" + pendingAmount + ", latePaymentStatus=" + latePaymentStatus + ", latePaymentCharge=" + latePaymentCharge + ", arrearStatus=" + arrearStatus + ", arrear=" + arrear + ", rebateStatus=" + rebateStatus + ", rebateOnlinePayment=" + rebateOnlinePayment + ", rebateOther=" + rebateOther + ", partialPaymentStatus=" + partialPaymentStatus + ", partialCollectedAmount=" + partialCollectedAmount + ", status=" + status + ", bankRefId=" + bankRefId + ", bankStatus=" + bankStatus + ", remarks=" + remarks + ", paymentMode=" + paymentMode + ", chequeDDNum=" + chequeDDNum + ", chequeDDDate=" + chequeDDDate + ", bankName=" + bankName + ", bankBranch=" + bankBranch + ", entryDateTime=" + entryDateTime + ", paymentApp=" + paymentApp + ", dueDate=" + dueDate + ", billDate=" + billDate + ", floorWiseTAXDetails=" + floorWiseTAXDetails + ", financialYear=" + financialYear + ", payerName=" + payerName + ", contactNo=" + contactNo + ", paymentPeriod=" + paymentPeriod + ", ifscCode=" + ifscCode + ", ReceiptDate=" + ReceiptDate + ", totalPayment=" + totalPayment + '}';
    }



    
}
