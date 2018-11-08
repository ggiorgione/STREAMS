package org.matsim.contrib.carsharing.events;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.Event;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.carsharing.stations.CarsharingStation;

import java.util.LinkedHashMap;
import java.util.Map;

public class NoVehicleAtStationCarSharingEvent extends Event{

	public static final String EVENT_TYPE = "no carsharing vehicle at station";

	private final Link originLinkId;

	private final Link destinationLinkId;

	private final String carsharingType;

	private String companyId;

	private Id<Person> personId;

	private CarsharingStation carsharingStation;

	private String vehicleType;


	/**
	 * Constructor NoVehicleAtStationCarSharingEvent class
	 * @param time
	 * @param originLinkId
	 * @param destinationLinkId
	 * @param carsharingType
	 * @param companyId
	 * @param personId
	 * @param carsharingStation
	 * @param vehicleType
	 */
	public NoVehicleAtStationCarSharingEvent(double time, Link originLinkId, Link destinationLinkId, String carsharingType, String companyId, Id<Person> personId, CarsharingStation carsharingStation, String vehicleType) {
		super(time);
		this.originLinkId = originLinkId;
		this.destinationLinkId = destinationLinkId;
		this.carsharingType = carsharingType;
		this.companyId = companyId;
		this.personId = personId;
		this.carsharingStation = carsharingStation;
		this.vehicleType = vehicleType;
	}

	@Override
	public String getEventType() {
		return EVENT_TYPE;
	}

	public Link getOriginLinkId(){
		return this.originLinkId;
	}

	public Link getDestinationLinkId(){
		return this.destinationLinkId;
	}

	public String getCarsharingType() {
		return this.carsharingType;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public Id<Person> getPersonId() {
		return personId;
	}

	public CarsharingStation getCarsharingStation() {
		return carsharingStation;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	@Override
	public Map<String, String> getAttributes() {
		Map<String, String> attr = new LinkedHashMap<String, String>();
		attr.put(ATTRIBUTE_TYPE, getEventType());
		attr.put("OriginLinkId", originLinkId!=null ? (originLinkId.getId()!=null ? originLinkId.getId().toString() : "") : "");
		attr.put("DestinationLinkId", destinationLinkId!=null ? (destinationLinkId.getId()!=null ? destinationLinkId.getId().toString() : "") : "");
		attr.put("CarsharingType", carsharingType);
		attr.put("CompanyId", companyId);
		attr.put("Personid", personId!=null ? personId.toString() : "");
		attr.put("StationId", carsharingStation!=null ? carsharingStation.getStationId() : "");
		attr.put("VehicleType", vehicleType);

		return attr;
	}

	@Override
	public String toString() {
		Map<String,String> attr = this.getAttributes() ;
		StringBuilder eventXML = new StringBuilder("\t<event ");
		for (Map.Entry<String, String> entry : attr.entrySet()) {
			eventXML.append(entry.getKey());
			eventXML.append("=\"");
			eventXML.append(entry.getValue());
			eventXML.append("\" ");
		}
		eventXML.append(" />");
		return eventXML.toString();
	}


}
