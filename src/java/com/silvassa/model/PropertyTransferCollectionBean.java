/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author CEINFO
 */
@Entity
@Table (name="sl_payment_property_transfer")
public class PropertyTransferCollectionBean {
    @Id
    @Column(name = "sno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    @Column(name = "payrefid")
    private String payRefId;
    
    @Column(name = "property_ref_id")
    private String propertyRefId;

    // Amount Details
    @Column(name = "amountdemand")
    private String demandAmount;
    @Column(name = "amountpaid")
    private String sl_payment_amount;
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
    @Column(name = "bankname")
    private String bankName;
    @Column(name = "entrydatetime")
    private Date entryDateTime;
    @Column(name = "financial_year")
    private String financialYear;
    @Column(name = "payment_app")
    private String paymentApp;
    @Column(name = "bankbranch")
    private String bankBranch;
    @Column(name = "cheque_dd_date", nullable = true)
    private String chequeDDDate;
    @Column(name = "cheque_dd_num")
    private String chequeDDNum;
    @Column(name="payer_name")
    private String payerName;
    @Column(name="contactno")
    private String contactNo;  
    @Column(name="transer_type")
    private String transerType;
    @Column(name="property_unique_id")
    private String propertyUniqueId;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the payRefId
     */
    public String getPayRefId() {
        return payRefId;
    }

    /**
     * @param payRefId the payRefId to set
     */
    public void setPayRefId(String payRefId) {
        this.payRefId = payRefId;
    }

    /**
     * @return the propertyRefId
     */
    public String getPropertyRefId() {
        return propertyRefId;
    }

    /**
     * @param propertyRefId the propertyRefId to set
     */
    public void setPropertyRefId(String propertyRefId) {
        this.propertyRefId = propertyRefId;
    }

    /**
     * @return the demandAmount
     */
    public String getDemandAmount() {
        return demandAmount;
    }

    /**
     * @param demandAmount the demandAmount to set
     */
    public void setDemandAmount(String demandAmount) {
        this.demandAmount = demandAmount;
    }

    /**
     * @return the sl_payment_amount
     */
    public String getSl_payment_amount() {
        return sl_payment_amount;
    }

    /**
     * @param sl_payment_amount the sl_payment_amount to set
     */
    public void setSl_payment_amount(String sl_payment_amount) {
        this.sl_payment_amount = sl_payment_amount;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the bankRefId
     */
    public String getBankRefId() {
        return bankRefId;
    }

    /**
     * @param bankRefId the bankRefId to set
     */
    public void setBankRefId(String bankRefId) {
        this.bankRefId = bankRefId;
    }

    /**
     * @return the bankStatus
     */
    public String getBankStatus() {
        return bankStatus;
    }

    /**
     * @param bankStatus the bankStatus to set
     */
    public void setBankStatus(String bankStatus) {
        this.bankStatus = bankStatus;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the paymentMode
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the entryDateTime
     */
    public Date getEntryDateTime() {
        return entryDateTime;
    }

    /**
     * @param entryDateTime the entryDateTime to set
     */
    public void setEntryDateTime(Date entryDateTime) {
        this.entryDateTime = entryDateTime;
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
     * @return the paymentApp
     */
    public String getPaymentApp() {
        return paymentApp;
    }

    /**
     * @param paymentApp the paymentApp to set
     */
    public void setPaymentApp(String paymentApp) {
        this.paymentApp = paymentApp;
    }

    /**
     * @return the bankBranch
     */
    public String getBankBranch() {
        return bankBranch;
    }

    /**
     * @param bankBranch the bankBranch to set
     */
    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    /**
     * @return the chequeDDDate
     */
    public String getChequeDDDate() {
        return chequeDDDate;
    }

    /**
     * @param chequeDDDate the chequeDDDate to set
     */
    public void setChequeDDDate(String chequeDDDate) {
        this.chequeDDDate = chequeDDDate;
    }

    /**
     * @return the chequeDDNum
     */
    public String getChequeDDNum() {
        return chequeDDNum;
    }

    /**
     * @param chequeDDNum the chequeDDNum to set
     */
    public void setChequeDDNum(String chequeDDNum) {
        this.chequeDDNum = chequeDDNum;
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
     * @return the transerType
     */
    public String getTranserType() {
        return transerType;
    }

    /**
     * @param transerType the transerType to set
     */
    public void setTranserType(String transerType) {
        this.transerType = transerType;
    }

    /**
     * @return the propertyUniqueId
     */
    public String getPropertyUniqueId() {
        return propertyUniqueId;
    }

    /**
     * @param propertyUniqueId the propertyUniqueId to set
     */
    public void setPropertyUniqueId(String propertyUniqueId) {
        this.propertyUniqueId = propertyUniqueId;
    }
}
