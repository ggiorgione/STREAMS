package org.matsim.contrib.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.BigInteger;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTASynthesis {


	private String creationDate;

	private String lastSynthesisDate;

	private BigInteger vehicleId;

	private String vehicleExtId;

	private OTAGPSInformation gpsInformation;

	private OTAGPSInformation gprsInformation;

	private BigDecimal energyLevel;

	private String energyType;

	private BigDecimal mileageTotal;

	private String odometerUnit;

	private String distanceType;

	private String fuelUnit;

	private BigDecimal batteryVoltage;

	private BigDecimal activeDtcNumber;

	@JsonProperty("isEngineRunning")
	private Boolean isEngineRunning;

	@JsonProperty("isDoorsLocked")
	private Boolean isDoorsLocked;

	@JsonProperty("isConnectedToCharger")
	private Boolean isConnectedToCharger;

	@JsonProperty("isMalfunctionIndicatorLamp")
	private Boolean isMalfunctionIndicatorLamp;

	OTANetworkInformation networkInformation;


	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastSynthesisDate() {
		return lastSynthesisDate;
	}

	public void setLastSynthesisDate(String lastSynthesisDate) {
		this.lastSynthesisDate = lastSynthesisDate;
	}

	public BigInteger getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(BigInteger vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleExtId() {
		return vehicleExtId;
	}

	public void setVehicleExtId(String vehicleExtId) {
		this.vehicleExtId = vehicleExtId;
	}

	public OTAGPSInformation getGprsInformation() {
		return gprsInformation;
	}

	public void setGprsInformation(OTAGPSInformation gprsInformation) {
		this.gprsInformation = gprsInformation;
	}

	public BigDecimal getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(BigDecimal energyLevel) {
		this.energyLevel = energyLevel;
	}

	public String getEnergyType() {
		return energyType;
	}

	public void setEnergyType(String energyType) {
		this.energyType = energyType;
	}

	public BigDecimal getMileageTotal() {
		return mileageTotal;
	}

	public void setMileageTotal(BigDecimal mileageTotal) {
		this.mileageTotal = mileageTotal;
	}

	public String getOdometerUnit() {
		return odometerUnit;
	}

	public void setOdometerUnit(String odometerUnit) {
		this.odometerUnit = odometerUnit;
	}

	public String getDistanceType() {
		return distanceType;
	}

	public void setDistanceType(String distanceType) {
		this.distanceType = distanceType;
	}

	public String getFuelUnit() {
		return fuelUnit;
	}

	public void setFuelUnit(String fuelUnit) {
		this.fuelUnit = fuelUnit;
	}

	public BigDecimal getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(BigDecimal batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public BigDecimal getActiveDtcNumber() {
		return activeDtcNumber;
	}

	public void setActiveDtcNumber(BigDecimal activeDtcNumber) {
		this.activeDtcNumber = activeDtcNumber;
	}

	public Boolean getEngineRunning() {
		return isEngineRunning;
	}

	public void setEngineRunning(Boolean isEngineRunning) {
		this.isEngineRunning = isEngineRunning;
	}

	public Boolean getDoorsLocked() {
		return isDoorsLocked;
	}

	public void setDoorsLocked(Boolean doorsLocked) {
		isDoorsLocked = doorsLocked;
	}

	public Boolean getConnectedToCharger() {
		return isConnectedToCharger;
	}

	public void setConnectedToCharger(Boolean connectedToCharger) {
		isConnectedToCharger = connectedToCharger;
	}

	public Boolean getMalfunctionIndicatorLamp() {
		return isMalfunctionIndicatorLamp;
	}

	public void setMalfunctionIndicatorLamp(Boolean malfunctionIndicatorLamp) {
		isMalfunctionIndicatorLamp = malfunctionIndicatorLamp;
	}

	public OTAGPSInformation getGpsInformation() {
		return gpsInformation;
	}

	public void setGpsInformation(OTAGPSInformation gpsInformation) {
		this.gpsInformation = gpsInformation;
	}

	public OTANetworkInformation getNetworkInformation() {
		return networkInformation;
	}

	public void setNetworkInformation(OTANetworkInformation networkInformation) {
		this.networkInformation = networkInformation;
	}
}