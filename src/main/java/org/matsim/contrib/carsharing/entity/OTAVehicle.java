package org.matsim.contrib.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTAVehicle {

	private BigInteger id;

	private String extId;


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

}
