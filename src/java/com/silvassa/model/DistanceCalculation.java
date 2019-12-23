package com.silvassa.model;

import java.util.Map;


// TODO: Auto-generated Javadoc
/**
 * The Class DistanceCalculation.
 */
public class DistanceCalculation {

	/** The vehicle id. */
	private String vehicleId;
	
	/** The state. */
	private String state;
	
	/** The district. */
	private String district;
	
	/** The trans name. */
	private String transName;
	
	/** The invc date. */
	private String invcDate;
	
	/** The truck no. */
	private String truckNo;
	
	/** The destination. */
	private String destination;
	
	/** The avg speed. */
	private Double avgSpeed;
	
	/** The distance travelled. */
	private Double distanceTravelled;
	
	/** The today distance. */
	private String todayDistance;
	
	/** The movement time. */
	private String movementTime;
	
	/** The stoppage time. */
	private String stoppageTime;
	
	/** The duration. */
	private String duration;
	
	/** The ideal time. */
	private String idealTime;
	
	/** The variance. */
	private String variance;
	
	/** The remarks. */
	private String remarks;
	
	/** The gate out date. */
	private String gateOutDate;
	
	/** The gate out time. */
	private String gateOutTime;
	
	/** The address. */
	private String address;
	
	/** The ship lat. */
	private String shipLat;
	
	/** The ship long. */
	private String shipLong;
	
	/** The plant lat. */
	private String plantLat;
	
	/** The plant long. */
	private String plantLong;
	
	/** The plant geofence id. */
	private String plantGeofenceId;
	
	/** The ship geofence id. */
	private String shipGeofenceId;
	
	/** The actual arrival time. */
	private String actualArrivalTime;
	
	/** The mmi delivery id. */
	private String mmiDeliveryId;
	
	/** The plnt rt ID. */
	private String plntRtID;
	
	/** The sp rt id. */
	private String spRtId; // ship route id
	
	private String epochTimeStamp;
	private String tripClosedFlag;
    private String impParam;
    private Map<String,String>stateListMap;
	
	
	public Map<String, String> getStateListMap() {
		return stateListMap;
	}

	public void setStateListMap(Map<String, String> stateListMap) {
		this.stateListMap = stateListMap;
	}

	public String getImpParam() {
		return impParam;
	}

	public void setImpParam(String impParam) {
		this.impParam = impParam;
	}
	
	public String getTripClosedFlag() {
		return tripClosedFlag;
	}

	public void setTripClosedFlag(String tripClosedFlag) {
		this.tripClosedFlag = tripClosedFlag;
	}

	public String getEpochTimeStamp() {
		return epochTimeStamp;
	}

	public void setEpochTimeStamp(String epochTimeStamp) {
		this.epochTimeStamp = epochTimeStamp;
	}

	/**
	 * Gets the plnt rt ID.
	 *
	 * @return the plntRtID
	 */
	public String getPlntRtID() {
		return plntRtID;
	}
	
	/**
	 * Sets the plnt rt ID.
	 *
	 * @param plntRtID the plntRtID to set
	 */
	public void setPlntRtID(String plntRtID) {
		this.plntRtID = plntRtID;
	}
	
	/**
	 * Gets the sp rt id.
	 *
	 * @return the spRtId
	 */
	public String getSpRtId() {
		return spRtId;
	}
	
	/**
	 * Sets the sp rt id.
	 *
	 * @param spRtId the spRtId to set
	 */
	public void setSpRtId(String spRtId) {
		this.spRtId = spRtId;
	}
	
	/**
	 * Gets the mmi delivery id.
	 *
	 * @return the mmiDeliveryId
	 */
	public String getMmiDeliveryId() {
		return mmiDeliveryId;
	}
	
	/**
	 * Sets the mmi delivery id.
	 *
	 * @param mmiDeliveryId the mmiDeliveryId to set
	 */
	public void setMmiDeliveryId(String mmiDeliveryId) {
		this.mmiDeliveryId = mmiDeliveryId;
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
	 * @param actualArrivalTime the actualArrivalTime to set
	 */
	public void setActualArrivalTime(String actualArrivalTime) {
		this.actualArrivalTime = actualArrivalTime;
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
	 * @param plantGeofenceId the plantGeofenceId to set
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
	 * @param shipGeofenceId the shipGeofenceId to set
	 */
	public void setShipGeofenceId(String shipGeofenceId) {
		this.shipGeofenceId = shipGeofenceId;
	}
	
	/**
	 * Gets the ship lat.
	 *
	 * @return the shipLat
	 */
	public String getShipLat() {
		return shipLat;
	}
	
	/**
	 * Sets the ship lat.
	 *
	 * @param shipLat the shipLat to set
	 */
	public void setShipLat(String shipLat) {
		this.shipLat = shipLat;
	}
	
	/**
	 * Gets the ship long.
	 *
	 * @return the shipLong
	 */
	public String getShipLong() {
		return shipLong;
	}
	
	/**
	 * Sets the ship long.
	 *
	 * @param shipLong the shipLong to set
	 */
	public void setShipLong(String shipLong) {
		this.shipLong = shipLong;
	}
	
	/**
	 * Gets the plant lat.
	 *
	 * @return the plantLat
	 */
	public String getPlantLat() {
		return plantLat;
	}
	
	/**
	 * Sets the plant lat.
	 *
	 * @param plantLat the plantLat to set
	 */
	public void setPlantLat(String plantLat) {
		this.plantLat = plantLat;
	}
	
	/**
	 * Gets the plant long.
	 *
	 * @return the plantLong
	 */
	public String getPlantLong() {
		return plantLong;
	}
	
	/**
	 * Sets the plant long.
	 *
	 * @param plantLong the plantLong to set
	 */
	public void setPlantLong(String plantLong) {
		this.plantLong = plantLong;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the today distance.
	 *
	 * @return the todayDistance
	 */
	public String getTodayDistance() {
		return todayDistance;
	}
	
	/**
	 * Sets the today distance.
	 *
	 * @param todayDistance the todayDistance to set
	 */
	public void setTodayDistance(String todayDistance) {
		this.todayDistance = todayDistance;
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
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	/**
	 * Gets the gate out date.
	 *
	 * @return the gateOutDate
	 */
	public String getGateOutDate() {
		return gateOutDate;
	}
	
	/**
	 * Sets the gate out date.
	 *
	 * @param gateOutDate the gateOutDate to set
	 */
	public void setGateOutDate(String gateOutDate) {
		this.gateOutDate = gateOutDate;
	}
	
	/**
	 * Gets the gate out time.
	 *
	 * @return the gateOutTime
	 */
	public String getGateOutTime() {
		return gateOutTime;
	}
	
	/**
	 * Sets the gate out time.
	 *
	 * @param gateOutTime the gateOutTime to set
	 */
	public void setGateOutTime(String gateOutTime) {
		this.gateOutTime = gateOutTime;
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
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	/**
	 * Gets the avg speed.
	 *
	 * @return the avgSpeed
	 */
	public Double getAvgSpeed() {
		return avgSpeed;
	}
	
	/**
	 * Sets the avg speed.
	 *
	 * @param avgSpeed the avgSpeed to set
	 */
	public void setAvgSpeed(Double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}
	
	/**
	 * Gets the distance travelled.
	 *
	 * @return the distanceTravelled
	 */
	public Double getDistanceTravelled() {
		return distanceTravelled;
	}
	
	/**
	 * Sets the distance travelled.
	 *
	 * @param distanceTravelled the distanceTravelled to set
	 */
	public void setDistanceTravelled(Double distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}
	
	/**
	 * Gets the movement time.
	 *
	 * @return the movementTime
	 */
	public String getMovementTime() {
		return movementTime;
	}
	
	/**
	 * Sets the movement time.
	 *
	 * @param movementTime the movementTime to set
	 */
	public void setMovementTime(String movementTime) {
		this.movementTime = movementTime;
	}
	
	/**
	 * Gets the stoppage time.
	 *
	 * @return the stoppageTime
	 */
	public String getStoppageTime() {
		return stoppageTime;
	}
	
	/**
	 * Sets the stoppage time.
	 *
	 * @param stoppageTime the stoppageTime to set
	 */
	public void setStoppageTime(String stoppageTime) {
		this.stoppageTime = stoppageTime;
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
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
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
	 * @param idealTime the idealTime to set
	 */
	public void setIdealTime(String idealTime) {
		this.idealTime = idealTime;
	}
	
	/**
	 * Gets the variance.
	 *
	 * @return the variance
	 */
	public String getVariance() {
		return variance;
	}
	
	/**
	 * Sets the variance.
	 *
	 * @param variance the variance to set
	 */
	public void setVariance(String variance) {
		this.variance = variance;
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
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Gets the district.
	 *
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	
	/**
	 * Sets the district.
	 *
	 * @param district the new district
	 */
	public void setDistrict(String district) {
		this.district = district;
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
	 * Gets the invc date.
	 *
	 * @return the invc date
	 */
	public String getInvcDate() {
		return invcDate;
	}
	
	/**
	 * Sets the invc date.
	 *
	 * @param invcDate the new invc date
	 */
	public void setInvcDate(String invcDate) {
		this.invcDate = invcDate;
	}
	
	/**
	 * Gets the truck no.
	 *
	 * @return the truck no
	 */
	public String getTruckNo() {
		return truckNo;
	}
	
	/**
	 * Sets the truck no.
	 *
	 * @param truckNo the new truck no
	 */
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DistanceCalculation [vehicleId=" + vehicleId + ", state="
				+ state + ", district=" + district + ", transName=" + transName
				+ ", invcDate=" + invcDate + ", truckNo=" + truckNo
				+ ", destination=" + destination + ", avgSpeed=" + avgSpeed
				+ ", distanceTravelled=" + distanceTravelled
				+ ", todayDistance=" + todayDistance + ", movementTime="
				+ movementTime + ", stoppageTime=" + stoppageTime
				+ ", duration=" + duration + ", idealTime=" + idealTime
				+ ", variance=" + variance + ", remarks=" + remarks
				+ ", gateOutDate=" + gateOutDate + ", gateOutTime="
				+ gateOutTime + ", address=" + address + ", shipLat=" + shipLat
				+ ", shipLong=" + shipLong + ", plantLat=" + plantLat
				+ ", plantLong=" + plantLong + ", plantGeofenceId="
				+ plantGeofenceId + ", shipGeofenceId=" + shipGeofenceId
				+ ", actualArrivalTime=" + actualArrivalTime
				+ ", mmiDeliveryId=" + mmiDeliveryId + ", plntRtID=" + plntRtID
				+ ", spRtId=" + spRtId + "]";
	}
	
	
}
