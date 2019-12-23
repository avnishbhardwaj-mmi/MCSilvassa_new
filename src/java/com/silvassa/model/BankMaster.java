package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sl_pay_ms_bank")
public class BankMaster {
	@Id
	@Column(name ="bank_Id")
	private String bankId;
	
	@Column(name ="bank_name")
	private String bankName;

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
}
