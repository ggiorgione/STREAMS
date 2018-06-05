package org.matsim.contrib.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

import static org.matsim.contrib.carsharing.entity.DateUtils.DATE_TIME_PATTERN;


public abstract class BaseEntity {

	public BaseEntity(){}

	public BaseEntity(Date creationDate, Date modificationDate, boolean active, Integer externalId) {
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.active = active;
		this.externalId = externalId;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
	private Date creationDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
	private Date modificationDate;

	private boolean active = true;

	private Integer externalId;




	public abstract BigInteger getId();

	public abstract void setId(BigInteger id);


	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Integer getExternalId() {
		return externalId;
	}

	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
