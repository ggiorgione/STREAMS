package org.matsim.contrib.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTAPush {

	private OTAKey key;

	private OTAVehicle vehicle;

	private OTAVehicleSynthesis vehicleSynthesis;

	private OTASynthesis synthesis;

	private OTAAccessDevice accessDevice;

	private String operationCode;

	private String operationState;

	private String platform;


	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperationState() {
		return operationState;
	}

	public void setOperationState(String operationState) {
		this.operationState = operationState;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public OTAKey getKey() {
		return key;
	}

	public void setKey(OTAKey key) {
		this.key = key;
	}

	public OTASynthesis getSynthesis() {
		return synthesis;
	}

	public void setSynthesis(OTASynthesis synthesis) {
		this.synthesis = synthesis;
	}

	public OTAVehicleSynthesis getVehicleSynthesis() {
		return vehicleSynthesis;
	}

	public void setVehicleSynthesis(OTAVehicleSynthesis vehicleSynthesis) {
		this.vehicleSynthesis = vehicleSynthesis;
	}

	public OTAAccessDevice getAccessDevice() {
		return accessDevice;
	}

	public void setAccessDevice(OTAAccessDevice accessDevice) {
		this.accessDevice = accessDevice;
	}

	public OTAVehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(OTAVehicle vehicle) {
		this.vehicle = vehicle;
	}


}
