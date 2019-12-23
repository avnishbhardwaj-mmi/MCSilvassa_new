package com.silvassa.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "state_master")
public class StateBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1175943544862451832L;
	@Column(name = "state_name")
	private String stateName;
	@Id
	@Column(name = "state_code")
	private String stateCode;
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	@Override
    public String toString() {

        String data =   "\""+stateCode+"\":\""+stateName+"\"";
                 
                
        return data;
    }

}
