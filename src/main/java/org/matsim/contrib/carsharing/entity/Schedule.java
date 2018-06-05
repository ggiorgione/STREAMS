package org.matsim.contrib.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;


public class Schedule extends BaseEntity {


	private BigInteger id;

	public Schedule(){super();}

	public Schedule(Date creationDate, Date modificationDate, boolean active, Integer externalId) {
		super(creationDate, modificationDate, active, externalId);
	}

	@Override
	public BigInteger getId() {
		return id;
	}

	@Override
	public void setId(BigInteger id) {
		this.id = id;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date beginEntryDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date endEntryDate;

	public Date getBeginEntryDate() {
		return beginEntryDate;
	}

	public void setBeginEntryDate(Date beginEntryDate) {
		this.beginEntryDate = beginEntryDate;
	}

	public Date getEndEntryDate() {
		return endEntryDate;
	}

	public void setEndEntryDate(Date endEntryDate) {
		this.endEntryDate = endEntryDate;
	}


}
