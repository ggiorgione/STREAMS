package org.matsim.contrib.carsharing.events;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.Event;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;

import java.util.LinkedHashMap;
import java.util.Map;

public class NoVehicleCarSharingEvent extends Event{

	public static final String EVENT_TYPE = "no carsharing vehicle";

	private final Link originLink;

	private final Id<Link> destinationLinkId;

	private final String carsharingType;

	private String companyId;

	private Id<Person> personId;

	public NoVehicleCarSharingEvent(double time, String carsharingType, String companyId, Link currentLink, Link destinationLink, Id<Person> personId) {
		super(time);
		this.originLink = currentLink;
		this.destinationLinkId = destinationLink.getId();
		this.carsharingType = carsharingType;
		this.companyId = companyId;
		this.personId = personId;
	}

	@Override
	public String getEventType() {
		return EVENT_TYPE;
	}

	public Id<Link> getOriginLinkId(){
		return this.originLink.getId();
	}

	public Id<Link> getDestinationLinkId(){
		return this.destinationLinkId;
	}

	public String getCarsharingType() {
		return this.carsharingType;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	@Override
	public Map<String, String> getAttributes() {
		Map<String, String> attr = new LinkedHashMap<String, String>(super.getAttributes());
		attr.put(ATTRIBUTE_TYPE, getEventType());
		attr.put("NodeCoordX", Double.toString(originLink.getFromNode().getCoord().getX()));
		attr.put("NodeCoordY", Double.toString(originLink.getFromNode().getCoord().getY()));
		attr.put("OriginLinkId", originLink.getId() !=null ? originLink.getId().toString() : "");
		attr.put("DestinationLinkId", destinationLinkId!=null ? destinationLinkId.toString() : "");
		attr.put("CarsharingType", carsharingType);
		attr.put("CompanyId", companyId);
		attr.put("Personid", personId!=null ? personId.toString() : "");

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
