package org.matsim.contrib.carsharing.manager.supply;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.carsharing.stations.CarsharingStation;
import org.matsim.contrib.carsharing.vehicles.CSVehicle;
import org.matsim.core.api.experimental.events.EventsManager;

import java.util.function.Consumer;

/**
 * @author balac
 */
public interface VehiclesContainer {
	
	public boolean reserveVehicle(CSVehicle vehicle);
	public void parkVehicle(CSVehicle vehicle, Link link);
	public Link getVehicleLocation(CSVehicle vehicle);
	public CSVehicle findClosestAvailableVehicle(Link startLink, String typeOfVehicle, double searchDistance, Consumer<CarsharingStation> fireEvent);
	public CSVehicle findClosestAvailableVehicle(EventsManager eventsManager, Double time, Link startLink, String typeOfVehicle, double searchDistance, Id<Person> personId, String carsharingType, Link destinationLink);
	public Link findClosestAvailableParkingLocation(Link destinationLink, double searchDistance);
	public void reserveParking(Link destinationLink);

}
