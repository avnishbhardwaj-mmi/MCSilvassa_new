package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sl_pay_ms_payment_mode")
public class PaymentModeMaster {
	@Id
	@Column(name ="mode_Id")
	private String modeId;
	
	@Column(name ="mode_name")
	private String modeName;
	
	public String getModeId() {
		return modeId;
	}
	public void setModeId(String modeId) {
		this.modeId = modeId;
	}
	public String getMode() {
		return modeName;
	}
	public void setMode(String modeName) {
		this.modeName = modeName;
	}
}
