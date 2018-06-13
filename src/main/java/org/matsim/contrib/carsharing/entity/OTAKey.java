package org.matsim.contrib.carsharing.entity;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.matsim.contrib.carsharing.entity.DateUtils.instant2String;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTAKey {

	private BigInteger id;

	private String extId;

	private Boolean carDisplay;

	private String beginDate;

	private String endDate;

	private String realBeginDate;

	private String realEndDate;

	private String vehicleExtId;

	private String accessDeviceExtId;

	private Boolean freezeSecurity;

	private Boolean singleShotSecurity;


	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public Boolean getCarDisplay() {
		return carDisplay;
	}

	public void setCarDisplay(Boolean carDisplay) {
		this.carDisplay = carDisplay;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRealBeginDate() {
		return realBeginDate;
	}

	public void setRealBeginDate(String realBeginDate) {
		this.realBeginDate = realBeginDate;
	}

	public String getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(String realEndDate) {
		this.realEndDate = realEndDate;
	}

	public void setRealEndDate(Instant realEndDate) {
		this.realEndDate = instant2String(realEndDate);
	}

	public String getVehicleExtId() {
		return vehicleExtId;
	}

	public void setVehicleExtId(String vehicleExtId) {
		this.vehicleExtId = vehicleExtId;
	}

	public String getAccessDeviceExtId() {
		return accessDeviceExtId;
	}

	public void setAccessDeviceExtId(String accessDeviceExtId) {
		this.accessDeviceExtId = accessDeviceExtId;
	}

	public Boolean getFreezeSecurity() {
		return freezeSecurity;
	}

	public void setFreezeSecurity(Boolean freezeSecurity) {
		this.freezeSecurity = freezeSecurity;
	}

	public Boolean getSingleShotSecurity() {
		return singleShotSecurity;
	}

	public void setSingleShotSecurity(Boolean singleShotSecurity) {
		this.singleShotSecurity = singleShotSecurity;
	}
}
