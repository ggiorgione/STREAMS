package org.matsim.contrib.carsharing.entity;

import org.matsim.contrib.carsharing.manager.demand.RentalInfo;

import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.Date;
import java.util.function.Function;

public class Transformation {

	public static final Function<Response, Trip> getExaTrip = r -> r.readEntity(Trip.class);


	public static final Trip rentalInfo2Trip(RentalInfo rentalInfo){
		Trip trip = new Trip();
		trip.setActive(false);
		trip.setUser(new BigInteger(rentalInfo.getPersonId().toString()));
		trip.setCar(new BigInteger(rentalInfo.getVehId().toString()));
		trip.setIsBlocked(false);
		trip.setTripType(rentalInfo.getTripTypes());
		trip.setRealStart(new Date(rentalInfo.getRealStart()));

		return trip;
	}



}
