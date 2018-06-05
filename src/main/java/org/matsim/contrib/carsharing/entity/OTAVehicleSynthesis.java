package org.matsim.contrib.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTAVehicleSynthesis {


	private String lastCaptureDate;

	private OTAGPSInformation gpsInformation;

	private OTAGPSInformation gprsInformation;

	private String lastEnergyCaptureDate;

	private BigDecimal energyLevel;

	private String energyType;

	private String fuelUnit;

	private String lastMileageCaptureDate;

	private BigDecimal mileage;

	private String odometerUnit;

	private BigDecimal batteryVoltage;

	private String doorsState;

	private Boolean engineRunning;

	private Boolean connectedToCharger;

	private Boolean malfunctionIndicatorLamp;

	private BigInteger activeDtcNumber;

	private OTANetworkInformation networkInformation;



	public String getLastCaptureDate() {
		return lastCaptureDate;
	}

	public void setLastCaptureDate(String lastCaptureDate) {
		this.lastCaptureDate = lastCaptureDate;
	}

	public OTAGPSInformation getGpsInformation() {
		return gpsInformation;
	}

	public void setGpsInformation(OTAGPSInformation gpsInformation) {
		this.gpsInformation = gpsInformation;
	}

	public OTAGPSInformation getGprsInformation() {
		return gprsInformation;
	}

	public void setGprsInformation(OTAGPSInformation gprsInformation) {
		this.gprsInformation = gprsInformation;
	}

	public String getLastEnergyCaptureDate() {
		return lastEnergyCaptureDate;
	}

	public void setLastEnergyCaptureDate(String lastEnergyCaptureDate) {
		this.lastEnergyCaptureDate = lastEnergyCaptureDate;
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

	public String getFuelUnit() {
		return fuelUnit;
	}

	public void setFuelUnit(String fuelUnit) {
		this.fuelUnit = fuelUnit;
	}

	public String getLastMileageCaptureDate() {
		return lastMileageCaptureDate;
	}

	public void setLastMileageCaptureDate(String lastMileageCaptureDate) {
		this.lastMileageCaptureDate = lastMileageCaptureDate;
	}

	public BigDecimal getMileage() {
		return mileage;
	}

	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}

	public String getOdometerUnit() {
		return odometerUnit;
	}

	public void setOdometerUnit(String odometerUnit) {
		this.odometerUnit = odometerUnit;
	}

	public BigDecimal getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(BigDecimal batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public String getDoorsState() {
		return doorsState;
	}

	public void setDoorsState(String doorsState) {
		this.doorsState = doorsState;
	}

	public Boolean getEngineRunning() {
		return engineRunning;
	}

	public void setEngineRunning(Boolean engineRunning) {
		this.engineRunning = engineRunning;
	}

	public Boolean getConnectedToCharger() {
		return connectedToCharger;
	}

	public void setConnectedToCharger(Boolean connectedToCharger) {
		this.connectedToCharger = connectedToCharger;
	}

	public Boolean getMalfunctionIndicatorLamp() {
		return malfunctionIndicatorLamp;
	}

	public void setMalfunctionIndicatorLamp(Boolean malfunctionIndicatorLamp) {
		this.malfunctionIndicatorLamp = malfunctionIndicatorLamp;
	}

	public BigInteger getActiveDtcNumber() {
		return activeDtcNumber;
	}

	public void setActiveDtcNumber(BigInteger activeDtcNumber) {
		this.activeDtcNumber = activeDtcNumber;
	}

	public OTANetworkInformation getNetworkInformation() {
		return networkInformation;
	}

	public void setNetworkInformation(OTANetworkInformation networkInformation) {
		this.networkInformation = networkInformation;
	}
}
