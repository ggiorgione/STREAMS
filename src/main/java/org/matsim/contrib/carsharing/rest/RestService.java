package org.matsim.contrib.carsharing.rest;

import org.matsim.contrib.carsharing.entity.Trip;
import org.matsim.contrib.carsharing.manager.demand.RentalInfo;

import java.math.BigInteger;

public interface RestService {


    Trip getTripById(String tripId);

    Trip rentCar(RentalInfo rentalInfo);

    void startTrip(Trip trip);

    Trip cancelTrip(BigInteger id);

    Trip endTrip(RentalInfo RentalInfo);
}
