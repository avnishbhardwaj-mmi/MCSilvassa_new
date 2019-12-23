package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tax_collection")
public class TaxCollectionBean {
	@Id 
	@Column(name="Property_ID")
	private String propertyID;
	
	@Column(name="Owner_Name")
	private String ownerName;
	
	@Column(name="Payment_Mode")
	private String paymentMode;
	
	@Column(name="Bank_Name")
	private String bankName;
	
	@Column(name="Cheque_No")
	private String chequeNo;
	
	@Column(name="Demand_Draft_No")
	private String demandDraftNo;
	
	@Column(name="Total_Tax_Amount")
	private String totalTaxAmount;
	
	@Column(name="Tax_Amount_Received")
	private String taxAmountReceived;
	
	@Column(name="Balance_Amount")
	private String balanceAmount;
		
	@Column(name="Arrear")
	private String arrear;
	
	@Column(name="Rebate")
	private String rebate;
	
	@Column(name="Interest")
	private String interest;
	
	@Column(name="Bill_No")
	private String billNo;
	
	@Column(name="Bill_Date")
	private String billDate;
	
	@Column(name="Bill_Due_Date")
	private String billDueDate;
	
	@Column(name="Remarks")
	private String remarks;
	
	public String getPropertyID() {
		return propertyID;
	}
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getDemandDraftNo() {
		return demandDraftNo;
	}
	public void setDemandDraftNo(String demandDraftNo) {
		this.demandDraftNo = demandDraftNo;
	}
	public String getTotalTaxAmount() {
		return totalTaxAmount;
	}
	public void setTotalTaxAmount(String totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}
	public String getTaxAmountReceived() {
		return taxAmountReceived;
	}
	public void setTaxAmountReceived(String taxAmountReceived) {
		this.taxAmountReceived = taxAmountReceived;
	}
	public String getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getArrear() {
		return arrear;
	}
	public void setArrear(String arrear) {
		this.arrear = arrear;
	}
	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillDueDate() {
		return billDueDate;
	}
	public void setBillDueDate(String billDueDate) {
		this.billDueDate = billDueDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
