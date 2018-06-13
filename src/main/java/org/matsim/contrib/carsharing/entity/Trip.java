package org.matsim.contrib.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Date;

import static org.matsim.contrib.carsharing.entity.DateUtils.DATE_TIME_PATTERN;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trip extends BaseEntity {

	public Trip(){
		super();
	}


	public Trip(Date creationDate, Date modificationDate, boolean active, Integer externalId) {
		super(creationDate, modificationDate, active, externalId);
	}

	private BigInteger id;

	@Override
	public BigInteger getId() {
		return id;
	}

	@Override
	public void setId(BigInteger id) {
		this.id = id;
	}


	private Schedule scheduleEntry;


	private BigInteger user;


	private BigInteger car;


	private BigInteger geoLocation;


	private BigInteger carType;

	private Double distance;

	private Double startKM;

	private Double endKM;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
	private Date realStart;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
	private Date realEnd;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
	private Date cancelledDate;

	private Double startFuelLevel;

	private Double endFuelLevel;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
	private Date cleaningPerformedDate;

	private Boolean isBlocked;

	private BigInteger groupId;

	private TripTypes tripType;

	private Double startKMOBU;

	private Double endKMOBU;

	private Double startKMMobile;

	private Double endKMMobile;

	private Boolean isMaintenance;

	private String startLocation;

	private String endLocation;

	private Boolean realStartUpdated;

	private Boolean toBeEnded;

	private Boolean startTripMexSent;

	private Boolean endTripMexSent;

	private String status;


	public Boolean getRealStartUpdated() {
		return realStartUpdated;
	}

	public void setRealStartUpdated(Boolean realStartUpdated) {
		this.realStartUpdated = realStartUpdated;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getStartKM() {
		return startKM;
	}

	public void setStartKM(Double startKM) {
		this.startKM = startKM;
	}

	public Double getEndKM() {
		return endKM;
	}

	public void setEndKM(Double endKM) {
		this.endKM = endKM;
	}

	public Date getRealStart() {
		return realStart;
	}

	public void setRealStart(Date realStart) {
		this.realStart = realStart;
	}

	public Date getRealEnd() {
		return realEnd;
	}

	public void setRealEnd(Date realEnd) {
		this.realEnd = realEnd;
	}

	public Double getStartFuelLevel() {
		return startFuelLevel;
	}

	public void setStartFuelLevel(Double startFuelLevel) {
		this.startFuelLevel = startFuelLevel;
	}

	public Double getEndFuelLevel() {
		return endFuelLevel;
	}

	public void setEndFuelLevel(Double endFuelLevel) {
		this.endFuelLevel = endFuelLevel;
	}

	public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}

	public void setCleaningPerformedDate(Date cleaningPerformedDate) {
		this.cleaningPerformedDate = cleaningPerformedDate;
	}

	public Date getCleaningPerformedDate() {
		return cleaningPerformedDate;
	}

	public Schedule getScheduleEntry() {
		return scheduleEntry;
	}

	public void setScheduleEntry(Schedule scheduleEntry) {
		this.scheduleEntry = scheduleEntry;
	}

	public BigInteger getCar() {
		return car;
	}

	public void setCar(BigInteger car) {
		this.car = car;
	}

	public BigInteger getUser() {
		return user;
	}

	public void setUser(BigInteger user) {
		this.user = user;
	}

	public BigInteger getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(BigInteger geoLocation) {
		this.geoLocation = geoLocation;
	}

	public BigInteger getCarType() {
		return carType;
	}

	public void setCarType(BigInteger carType) {
		this.carType = carType;
	}

	public Boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Boolean blocked) {
		isBlocked = blocked;
	}

	public BigInteger getGroupId() {
		return groupId;
	}

	public void setGroupId(BigInteger groupId) {
		this.groupId = groupId;
	}

	public TripTypes getTripType() {
		return tripType;
	}

	public void setTripType(TripTypes tripType) {
		this.tripType = tripType;
	}

	public Double getStartKMOBU() {
		return startKMOBU;
	}

	public void setStartKMOBU(Double startKMOBU) {
		this.startKMOBU = startKMOBU;
	}

	public Double getEndKMOBU() {
		return endKMOBU;
	}

	public void setEndKMOBU(Double endKMOBU) {
		this.endKMOBU = endKMOBU;
	}

	public Double getStartKMMobile() {
		return startKMMobile;
	}

	public void setStartKMMobile(Double startKMMobile) {
		this.startKMMobile = startKMMobile;
	}

	public Double getEndKMMobile() {
		return endKMMobile;
	}

	public void setEndKMMobile(Double endKMMobile) {
		this.endKMMobile = endKMMobile;
	}

	public Boolean getIsMaintenance() {
		return isMaintenance;
	}

	public void setIsMaintenance(Boolean isMaintenance) {
		this.isMaintenance = isMaintenance;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public Boolean getStartTripMexSent() {
		return startTripMexSent;
	}

	public void setStartTripMexSent(Boolean startTripMexSent) {
		this.startTripMexSent = startTripMexSent;
	}

	public Boolean getEndTripMexSent() {
		return endTripMexSent;
	}

	public void setEndTripMexSent(Boolean endTripMexSent) {
		this.endTripMexSent = endTripMexSent;
	}


	public Boolean getToBeEnded() {
		return toBeEnded;
	}

	public void setToBeEnded(Boolean toBeEnded) {
		this.toBeEnded = toBeEnded;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
