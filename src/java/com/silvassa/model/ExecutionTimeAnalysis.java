package com.silvassa.model;

import javax.persistence.Column;

// TODO: Auto-generated Javadoc
/**
 * The Class ExecutionTimeAnalysis.
 */
public class ExecutionTimeAnalysis {

	/** The delivery. */
	private String delivery;
	
	/** The trans name. */
	private String transName;
	
	/** The gate out time. */
	private String gateOutTime;
	
	/** The gate out date. */
	private String gateOutDate;
	
	/** The destination. */
	private String destination;
	
	/** The plnt lat. */
	private String plnt_lat;
	
	/** The cust lat. */
	private String cust_lat;
	
	/** The plnt long. */
	private String plnt_long;
	
	/** The cust long. */
	private String cust_long;
	
	/** The duration. */
	// expected arrival time
	private String duration;

	
	/** The expected arrival date. */
	private String expectedArrivalDate;
	
	/** The actual arrival date. */
	private String actualArrivalDate;

	/** The actual arrival time. */
	private String actualArrivalTime;
	
	/** The vehicle id. */
	private String vehicleId;

	/** The variance time. */
	private String varianceTime;
	
	/** The stopage. */
	private String stopage;
	
	/** The remarks. */
	private String remarks;
	
	/** The plant geofence id. */
	private String plantGeofenceId;
	
	/** The ship geofence id. */
	private String shipGeofenceId;
	
	/** The ideal time. */
	private String idealTime;
	
	/** The mmi deliv id. */
	private String mmiDelivId;
	
	/** The ship rt id. */
	private String shipRtId;
	
	/** The plnt rt id. */
	private String plntRtId;
	
	/** The yard date. */
	
	private String yardDate;

	/** The yard time. */
	
	private String yardtime;
	
	private String truckNO;
	public String getTruckNO() {
		return truckNO;
	}

	public void setTruckNO(String truckNO) {
		this.truckNO = truckNO;
	}

	private String expectedArrivalTime;
	
	public String getExpectedArrivalTime() {
		return expectedArrivalTime;
	}

	public void setExpectedArrivalTime(String expectedArrivalTime) {
		this.expectedArrivalTime = expectedArrivalTime;
	}

	public String getYardDate() {
		return yardDate;
	}

	public void setYardDate(String yardDate) {
		this.yardDate = yardDate;
	}

	public String getYardtime() {
		return yardtime;
	}

	public void setYardtime(String yardtime) {
		this.yardtime = yardtime;
	}

	/**
	 * Gets the plnt rt id.
	 *
	 * @return the plntRtId
	 */
	public String getPlntRtId() {
		return plntRtId;
	}

	/**
	 * Sets the plnt rt id.
	 *
	 * @param plntRtId the plntRtId to set
	 */
	public void setPlntRtId(String plntRtId) {
		this.plntRtId = plntRtId;
	}

	/**
	 * Gets the ship rt id.
	 *
	 * @return the shipRtId
	 */
	public String getShipRtId() {
		return shipRtId;
	}

	/**
	 * Sets the ship rt id.
	 *
	 * @param shipRtId the shipRtId to set
	 */
	public void setShipRtId(String shipRtId) {
		this.shipRtId = shipRtId;
	}

	/**
	 * Gets the mmi deliv id.
	 *
	 * @return the mmiDelivId
	 */
	public String getMmiDelivId() {
		return mmiDelivId;
	}

	/**
	 * Sets the mmi deliv id.
	 *
	 * @param mmiDelivId the mmiDelivId to set
	 */
	public void setMmiDelivId(String mmiDelivId) {
		this.mmiDelivId = mmiDelivId;
	}

	/**
	 * Gets the plant geofence id.
	 *
	 * @return the plantGeofenceId
	 */
	public String getPlantGeofenceId() {
		return plantGeofenceId;
	}

	/**
	 * Sets the plant geofence id.
	 *
	 * @param plantGeofenceId            the plantGeofenceId to set
	 */
	public void setPlantGeofenceId(String plantGeofenceId) {
		this.plantGeofenceId = plantGeofenceId;
	}

	/**
	 * Gets the ship geofence id.
	 *
	 * @return the shipGeofenceId
	 */
	public String getShipGeofenceId() {
		return shipGeofenceId;
	}

	/**
	 * Sets the ship geofence id.
	 *
	 * @param shipGeofenceId            the shipGeofenceId to set
	 */
	public void setShipGeofenceId(String shipGeofenceId) {
		this.shipGeofenceId = shipGeofenceId;
	}

	/**
	 * Gets the actual arrival date.
	 *
	 * @return the actualArrivalDate
	 */
	public String getActualArrivalDate() {
		return actualArrivalDate;
	}

	/**
	 * Sets the actual arrival date.
	 *
	 * @param actualArrivalDate            the actualArrivalDate to set
	 */
	public void setActualArrivalDate(String actualArrivalDate) {
		this.actualArrivalDate = actualArrivalDate;
	}

	/**
	 * Gets the actual arrival time.
	 *
	 * @return the actualArrivalTime
	 */
	public String getActualArrivalTime() {
		return actualArrivalTime;
	}

	/**
	 * Sets the actual arrival time.
	 *
	 * @param actualArrivalTime            the actualArrivalTime to set
	 */
	public void setActualArrivalTime(String actualArrivalTime) {
		this.actualArrivalTime = actualArrivalTime;
	}

	/**
	 * Gets the vehicle id.
	 *
	 * @return the vehicleId
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * Sets the vehicle id.
	 *
	 * @param vehicleId            the vehicleId to set
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * Gets the variance time.
	 *
	 * @return the varianceTime
	 */
	public String getVarianceTime() {
		return varianceTime;
	}

	/**
	 * Sets the variance time.
	 *
	 * @param varianceTime            the varianceTime to set
	 */
	public void setVarianceTime(String varianceTime) {
		this.varianceTime = varianceTime;
	}

	/**
	 * Gets the stopage.
	 *
	 * @return the stopage
	 */
	public String getStopage() {
		return stopage;
	}

	/**
	 * Sets the stopage.
	 *
	 * @param stopage            the stopage to set
	 */
	public void setStopage(String stopage) {
		this.stopage = stopage;
	}

	/**
	 * Gets the remarks.
	 *
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Sets the remarks.
	 *
	 * @param remarks            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * Gets the expected arrival date.
	 *
	 * @return the expectedArrivalDate
	 */
	public String getExpectedArrivalDate() {
		return expectedArrivalDate;
	}

	/**
	 * Sets the expected arrival date.
	 *
	 * @param expectedArrivalDate            the expectedArrivalDate to set
	 */
	public void setExpectedArrivalDate(String expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}

	/**
	 * Gets the ideal time.
	 *
	 * @return the idealTime
	 */
	public String getIdealTime() {
		return idealTime;
	}

	/**
	 * Sets the ideal time.
	 *
	 * @param idealTime            the idealTime to set
	 */
	public void setIdealTime(String idealTime) {
		this.idealTime = idealTime;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Gets the plnt lat.
	 *
	 * @return the plnt_lat
	 */
	public String getPlnt_lat() {
		return plnt_lat;
	}

	/**
	 * Sets the plnt lat.
	 *
	 * @param plnt_lat            the plnt_lat to set
	 */
	public void setPlnt_lat(String plnt_lat) {
		this.plnt_lat = plnt_lat;
	}

	/**
	 * Gets the cust lat.
	 *
	 * @return the cust_lat
	 */
	public String getCust_lat() {
		return cust_lat;
	}

	/**
	 * Sets the cust lat.
	 *
	 * @param cust_lat            the cust_lat to set
	 */
	public void setCust_lat(String cust_lat) {
		this.cust_lat = cust_lat;
	}

	/**
	 * Gets the plnt long.
	 *
	 * @return the plnt_long
	 */
	public String getPlnt_long() {
		return plnt_long;
	}

	/**
	 * Sets the plnt long.
	 *
	 * @param plnt_long            the plnt_long to set
	 */
	public void setPlnt_long(String plnt_long) {
		this.plnt_long = plnt_long;
	}

	/**
	 * Gets the cust long.
	 *
	 * @return the cust_long
	 */
	public String getCust_long() {
		return cust_long;
	}

	/**
	 * Sets the cust long.
	 *
	 * @param cust_long            the cust_long to set
	 */
	public void setCust_long(String cust_long) {
		this.cust_long = cust_long;
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Sets the destination.
	 *
	 * @param destination the new destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Gets the delivery.
	 *
	 * @return the delivery
	 */
	public String getDelivery() {
		return delivery;
	}

	/**
	 * Sets the delivery.
	 *
	 * @param delivery the new delivery
	 */
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	/**
	 * Gets the trans name.
	 *
	 * @return the trans name
	 */
	public String getTransName() {
		return transName;
	}

	/**
	 * Sets the trans name.
	 *
	 * @param transName the new trans name
	 */
	public void setTransName(String transName) {
		this.transName = transName;
	}

	/**
	 * Gets the gate out time.
	 *
	 * @return the gate out time
	 */
	public String getGateOutTime() {
		return gateOutTime;
	}

	/**
	 * Sets the gate out time.
	 *
	 * @param gateOutTime the new gate out time
	 */
	public void setGateOutTime(String gateOutTime) {
		this.gateOutTime = gateOutTime;
	}

	/**
	 * Gets the gate out date.
	 *
	 * @return the gate out date
	 */
	public String getGateOutDate() {
		return gateOutDate;
	}

	/**
	 * Sets the gate out date.
	 *
	 * @param gateOutDate the new gate out date
	 */
	public void setGateOutDate(String gateOutDate) {
		this.gateOutDate = gateOutDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExecutionTimeAnalysis [delivery=" + delivery + ", transName="
				+ transName + ", gateOutTime=" + gateOutTime + ", gateOutDate="
				+ gateOutDate + ", destination=" + destination + ", plnt_lat="
				+ plnt_lat + ", cust_lat=" + cust_lat + ", plnt_long="
				+ plnt_long + ", cust_long=" + cust_long + ", duration="
				+ duration + ", idealTime=" + idealTime
				+ ", expectedArrivalDate=" + expectedArrivalDate
				+ ", actualArrivalDate=" + actualArrivalDate
				+ ", actualArrivalTime=" + actualArrivalTime + ", vehicleId="
				+ vehicleId + ", varianceTime=" + varianceTime + ", stopage="
				+ stopage + ", remarks=" + remarks + "]";
	}

}
