package org.matsim.contrib.carsharing.manager.supply;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.carsharing.vehicles.CSVehicle;
import org.matsim.core.api.experimental.events.EventsManager;

public class CompanyAgentImpl implements CompanyAgent {
	
	private CompanyContainer companyContainer;
	
	public CompanyAgentImpl(CompanyContainer companyContainer, String strategyType) {
		this.companyContainer = companyContainer;
	}
	
	@Override
	public CSVehicle vehicleRequest(EventsManager eventsManager, Double time, Id<Person> personId, Link locationLink, Link destinationLink,
									String carsharingType, String vehicleType) {

		VehiclesContainer vehiclesContainer = companyContainer.getVehicleContainer(carsharingType);
		
		if (vehiclesContainer != null) {
			
			//Depending on the company strategy
			//here the company just provides the closest vehicle in the search radius
			CSVehicle vehicle = vehiclesContainer.findClosestAvailableVehicle(eventsManager, time, locationLink, vehicleType, 1000.0, personId, carsharingType, destinationLink);
			
			return vehicle;
		}
		
		else
			return null;
		
	}	
	
}
