package org.matsim.contrib.carsharing.manager.supply;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

import com.google.inject.Inject;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.carsharing.events.NoVehicleAtStationCarSharingEvent;
import org.matsim.contrib.carsharing.stations.CarsharingStation;
import org.matsim.contrib.carsharing.stations.OneWayCarsharingStation;
import org.matsim.contrib.carsharing.vehicles.CSVehicle;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.utils.collections.QuadTree;
import org.matsim.core.utils.geometry.CoordUtils;

/** 
 * @author balac
 */
public class OneWayContainer implements VehiclesContainer{	
	
	private QuadTree<CarsharingStation> owvehicleLocationQuadTree;	
	private Map<String, CarsharingStation> stationsMap;
	private Map<CSVehicle, Link> owvehiclesMap ;
	private Map<CSVehicle, String> vehicleToStationMap;

	private @Inject EventsManager eventsManager;

	
	public OneWayContainer(QuadTree<CarsharingStation> owvehicleLocationQuadTree2,
			Map<String, CarsharingStation> stationsMap,
			Map<CSVehicle, Link> owvehiclesMap2, Map<CSVehicle, String> vehicleToStationMap) {
		this.owvehicleLocationQuadTree = owvehicleLocationQuadTree2;
		this.stationsMap = stationsMap;
		this.owvehiclesMap = owvehiclesMap2;
		this.vehicleToStationMap = vehicleToStationMap;
	}

	public boolean reserveVehicle(CSVehicle vehicle) {
		Link link = this.owvehiclesMap.get(vehicle);

		if (link == null) {
			return false;
		}

		Coord coord = link.getCoord();
		this.owvehiclesMap.remove(vehicle);			
		
		Collection<CarsharingStation> stations = 
				owvehicleLocationQuadTree.getDisk(coord.getX(), coord.getY(), 0.0);
		
		for (CarsharingStation cs : stations) {
			
			if (((OneWayCarsharingStation)cs).getVehicles(vehicle.getType()).contains(vehicle)) {
				((OneWayCarsharingStation)cs).removeCar(vehicle);
				this.vehicleToStationMap.remove(vehicle);
			}

		}	

		return true;
	}

	public void parkVehicle(CSVehicle vehicle, Link link) {
		Coord coord = link.getCoord();			
		owvehiclesMap.put(vehicle, link);			
		CarsharingStation station = owvehicleLocationQuadTree.getClosest(coord.getX(), coord.getY());
		((OneWayCarsharingStation)station).addCar(vehicle.getType(), vehicle);
		this.vehicleToStationMap.put(vehicle, station.getStationId());
		
	}	
	
	public void freeParkingSpot(CSVehicle vehicle) {
		OneWayCarsharingStation station = (OneWayCarsharingStation) this.stationsMap.get(this.vehicleToStationMap.get(vehicle));
		station.freeParkingSpot();
	}

	@Override
	public Link getVehicleLocation(CSVehicle vehicle) {

		return this.owvehiclesMap.get(vehicle);
	}

	@Override
	public CSVehicle findClosestAvailableVehicle(Link startLink, String typeOfVehicle, double searchDistance, Consumer<CarsharingStation> fireEvent, Consumer<CarsharingStation> fireAvailableVehiclesNum) {
		return null;
	}


	@Override
	public CSVehicle findClosestAvailableVehicle(EventsManager eventsManager, Double time, Link startLink, String typeOfVehicle, double searchDistance, Id<Person> personId, String carsharingType, Link destinationLink) {
		Consumer<CarsharingStation> fireEvent = station -> eventsManager.processEvent(new NoVehicleAtStationCarSharingEvent(time, startLink, destinationLink, carsharingType, "", personId, station, typeOfVehicle));
		return findClosestAvailableVehicle(startLink, typeOfVehicle, searchDistance, fireEvent);
	}

	@Override
	public CSVehicle findClosestAvailableVehicle(Link startLink, String typeOfVehicle, double searchDistance, Consumer<CarsharingStation> fireEvent) {


		//find the closest available car and reserve it (make it unavailable)
		//if no cars within certain radius return null
		Collection<CarsharingStation> location = 
				owvehicleLocationQuadTree.getDisk(startLink.getCoord().getX(), 
						startLink.getCoord().getY(), searchDistance);
		if (location.isEmpty()) return null;

		CarsharingStation closest = null;
		double closestFound = searchDistance;
		for(CarsharingStation station: location) {
			
			Coord coord = station.getLink().getCoord();
			
			if (CoordUtils.calcEuclideanDistance(startLink.getCoord(), coord) < closestFound){
				if (((OneWayCarsharingStation)station).getNumberOfVehicles(typeOfVehicle) > 0) {
					closest = station;
					closestFound = CoordUtils.calcEuclideanDistance(startLink.getCoord(), coord);
				}else
					fireEvent.accept(station);
			}

		}		
		if (closest != null) {
			CSVehicle vehicleToBeUsed = ((OneWayCarsharingStation)closest).getVehicles(typeOfVehicle).get(0);
			return vehicleToBeUsed;
		}		
		return null;
		
		
	}

	@Override
	public Link findClosestAvailableParkingLocation(Link destinationLink, double searchDstance) {		

		//find the closest available parking space and reserve it (make it unavailable)
		//if there are no parking spots within search radius, return null		

		Collection<CarsharingStation> location = 
				this.owvehicleLocationQuadTree.getDisk(destinationLink.getCoord().getX(), 
						destinationLink.getCoord().getY(), searchDstance);
		if (location.isEmpty()) return null;

		CarsharingStation closest = null;
		double closestFound = searchDstance;
		for(CarsharingStation station: location) {
			
			Coord coord = station.getLink().getCoord();
			
			if (CoordUtils.calcEuclideanDistance(destinationLink.getCoord(), coord) < closestFound 
					&& ((OneWayCarsharingStation)station).getAvaialbleParkingSpots() > 0) {
				closest = station;
				closestFound = CoordUtils.calcEuclideanDistance(destinationLink.getCoord(), coord);
			}
		}
		if (closest == null)
			return null;
		return closest.getLink();
	}

	@Override
	public void reserveParking(Link destinationLink) {
		Coord coord = destinationLink.getCoord();

		OneWayCarsharingStation station = (OneWayCarsharingStation) this.owvehicleLocationQuadTree.getClosest(coord.getX(), coord.getY());

		((OneWayCarsharingStation)station).reserveParkingSpot();	
		
	}
	
}
