package org.matsim.contrib.carsharing.entity;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTAGPSInformation {

	private BigDecimal latitude;

	private BigDecimal longitude;

	private BigInteger speed;

	private BigInteger altitude;

	private String captureDate;

	private Integer fixQuality;

	private	Integer	satelliteNumber;

	private	Double	pdop;

	private	Double	hdop;

	private	Double	vdop;

	private	Integer	timeToFix;

	private	String	gpsAmp;


	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(String captureDate) {
		this.captureDate = captureDate;
	}

	public BigInteger getSpeed() {
		return speed;
	}

	public void setSpeed(BigInteger speed) {
		this.speed = speed;
	}

	public BigInteger getAltitude() {
		return altitude;
	}

	public void setAltitude(BigInteger altitude) {
		this.altitude = altitude;
	}

	public Integer getFixQuality() {
		return fixQuality;
	}

	public void setFixQuality(Integer fixQuality) {
		this.fixQuality = fixQuality;
	}

	public Integer getSatelliteNumber() {
		return satelliteNumber;
	}

	public void setSatelliteNumber(Integer satelliteNumber) {
		this.satelliteNumber = satelliteNumber;
	}

	public Double getPdop() {
		return pdop;
	}

	public void setPdop(Double pdop) {
		this.pdop = pdop;
	}

	public Double getHdop() {
		return hdop;
	}

	public void setHdop(Double hdop) {
		this.hdop = hdop;
	}

	public Double getVdop() {
		return vdop;
	}

	public void setVdop(Double vdop) {
		this.vdop = vdop;
	}

	public Integer getTimeToFix() {
		return timeToFix;
	}

	public void setTimeToFix(Integer timeToFix) {
		this.timeToFix = timeToFix;
	}

	public String getGpsAmp() {
		return gpsAmp;
	}

	public void setGpsAmp(String gpsAmp) {
		this.gpsAmp = gpsAmp;
	}
}
