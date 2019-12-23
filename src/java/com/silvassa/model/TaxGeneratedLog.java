package com.silvassa.model;

// default package
// Generated Jan 5, 2017 10:44:56 AM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TaxGeneratedLog generated by hbm2java
 */
@Entity
@Table(name = "tax_generated_log", schema = "public")
public class TaxGeneratedLog implements java.io.Serializable {


	private int id;
	private String generatedby;
//	private String generatedon; 
	
	private String zoneid; //character varying(5),
	private String ward; //character varying(50),
	private String locality;// character varying(50)
	private Date generatedyear;
	
	public TaxGeneratedLog() {
	}

	public TaxGeneratedLog(int id) {
		this.id = id;
	}

	
	public TaxGeneratedLog(int id, String paramType, String paramName,
			String generatedby, String zoneid, String ward, String locality,Date generatedyear) {
		super();
		this.id = id;
		
		this.generatedby = generatedby;
		this.zoneid = zoneid;
		this.ward = ward;
		this.locality = locality;
		this.generatedyear = generatedyear;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, columnDefinition = "serial")
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * @return the generatedby
	 */
	@Column(name = "generatedby")
	public String getGeneratedby() {
		return generatedby;
	}

	/**
	 * @param generatedby
	 *            the generatedby to set
	 */
	public void setGeneratedby(String generatedby) {
		this.generatedby = generatedby;
	}

	/**
	 * @return the zoneid
	 */
	@Column(name = "zoneid")
	public String getZoneid() {
		return zoneid;
	}

	/**
	 * @param zoneid the zoneid to set
	 */
	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}

	/**
	 * @return the ward
	 */
	@Column(name = "ward")
	public String getWard() {
		return ward;
	}

	/**
	 * @param ward the ward to set
	 */
	public void setWard(String ward) {
		this.ward = ward;
	}

	/**
	 * @return the locality
	 */
	@Column(name = "locality")
	public String getLocality() {
		return locality;
	}

	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * @return the generatedyear
	 */
	@Column(name = "generatedyear")
	public Date getGeneratedyear() {
		return generatedyear;
	}

	/**
	 * @param generatedyear the generatedyear to set
	 */
	public void setGeneratedyear(Date generatedyear) {
		this.generatedyear = generatedyear;
	}


}