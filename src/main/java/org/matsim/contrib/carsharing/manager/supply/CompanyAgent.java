package org.matsim.contrib.carsharing.manager.supply;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.carsharing.vehicles.CSVehicle;
import org.matsim.core.api.experimental.events.EventsManager;

public interface CompanyAgent {

	CSVehicle vehicleRequest(EventsManager eventsManager, Double time, Id<Person> personId, Link locationLink, Link destinationLink, String carsharingType,
							 String carType);


}
