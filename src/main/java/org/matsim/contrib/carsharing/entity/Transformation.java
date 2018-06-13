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

		return trip;
	}

	public static OTAPush rentalInfo2OTAPush(RentalInfo rentalInfo) {
		OTAPush push = new OTAPush();
		OTAKey key = new OTAKey();
		key.setExtId(rentalInfo.getTripId().toString());
		key.setRealEndDate(rentalInfo.getRealEnd());
		push.setKey(key);
		OTAVehicleSynthesis vehicleSynthesis = new OTAVehicleSynthesis();
		push.setVehicleSynthesis(vehicleSynthesis);
		// TODO set vehicleSynthesis.{mileage, energyLevel}
		// TODO set vehicleSynthesis.gpsInformation -- unfortunately, MatSim uses two-dimensional
		//      cartesian coordinates which cannot be transformed to longitude / latitude easily
		return push;
	}

}
