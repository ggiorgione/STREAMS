package org.matsim.contrib.carsharing.manager.demand;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.carsharing.entity.TripTypes;
import org.matsim.vehicles.Vehicle;

import java.math.BigInteger;
import java.time.Instant;

/**
 * @author balac
 */
public class RentalInfo {
	private String carsharingType;
	public String getCarsharingType() {
		return carsharingType;
	}
	public void setCarsharingType(String carsharingType) {
		this.carsharingType = carsharingType;
	}
	public double getStartTime() {
		return startTime;
	}
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	public double getEndTime() {
		return endTime;
	}
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	public Id<Link> getOriginLinkId() {
		return originLinkId;
	}
	public void setOriginLinkId(Id<Link> originLinkId) {
		this.originLinkId = originLinkId;
	}
	public Id<Link> getPickupLinkId() {
		return pickupLinkId;
	}
	public void setPickupLinkId(Id<Link> pickupLinkId) {
		this.pickupLinkId = pickupLinkId;
	}
	public Id<Link> getDropoffLinkId() {
		return dropoffLinkId;
	}
	public void setDropoffLinkId(Id<Link> dropoffLinkId) {
		this.dropoffLinkId = dropoffLinkId;
	}
	public Id<Link> getEndLinkId() {
		return endLinkId;
	}
	public void setEndLinkId(Id<Link> endLinkId) {
		this.endLinkId = endLinkId;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getInVehicleTime() {
		return inVehicleTime;
	}
	public void setInVehicleTime(double inVehicleTime) {
		this.inVehicleTime = inVehicleTime;
	}
	public double getAccessStartTime() {
		return accessStartTime;
	}
	public void setAccessStartTime(double accessStartTime) {
		this.accessStartTime = accessStartTime;
	}
	public double getAccessEndTime() {
		return accessEndTime;
	}
	public void setAccessEndTime(double accessEndTime) {
		this.accessEndTime = accessEndTime;
	}
	public double getEgressStartTime() {
		return egressStartTime;
	}
	public void setEgressStartTime(double egressStartTime) {
		this.egressStartTime = egressStartTime;
	}
	public double getEgressEndTime() {
		return egressEndTime;
	}
	public void setEgressEndTime(double egressEndTime) {
		this.egressEndTime = egressEndTime;
	}
	public Id<Vehicle> getVehId() {
		return vehId;
	}
	public void setVehId(Id<Vehicle> vehId) {
		this.vehId = vehId;
	}
	private double startTime = 0.0;
	private double endTime = 0.0;
	private Id<Link> originLinkId = null;
	private Id<Link> pickupLinkId = null;
	private Id<Link> dropoffLinkId = null;
	private Id<Link> endLinkId = null;
	private double distance = 0.0;
	private double inVehicleTime = 0.0;
	private double accessStartTime = 0.0;
	private double accessEndTime = 0.0;
	private double egressStartTime = 0.0;
	private double egressEndTime = 0.0;
	private Id<Vehicle> vehId = null;

	private TripTypes tripTypes;

	private Id<Person> personId;

	private Instant realEnd;

	private BigInteger tripId;
	
	private double  tripCost = 0.0;

	public TripTypes getTripTypes() {
		return tripTypes;
	}

	public void setTripTypes(TripTypes tripTypes) {
		this.tripTypes = tripTypes;
	}


	public Id<Person> getPersonId() {
		return personId;
	}

	public void setPersonId(Id<Person> personId) {
		this.personId = personId;
	}

	public Instant getRealEnd() {
		return realEnd;
	}

	public BigInteger getTripId() {
		return tripId;
	}

	public void setTripId(BigInteger tripId) {
		this.tripId = tripId;
	}

	public void setRealEnd(Instant realEnd) {
		this.realEnd = realEnd;
	}
	
	public Double getTripCost() {
		return tripCost;
	}
	public void setTripCost(Double tripCost) {
		this.tripCost = tripCost;
	}

	public String toString() {
		
		return carsharingType + "," + Double.toString(startTime) + "," + Double.toString(endTime) + "," +
				originLinkId + "," + pickupLinkId + "," +	dropoffLinkId + "," + endLinkId + "," + 
				Double.toString(distance) + "," + Double.toString(inVehicleTime) + "," +
				Double.toString(accessEndTime - accessStartTime) + "," + Double.toString(egressEndTime - egressStartTime) +
		"," + vehId + "," + tripCost ;
	}
}
