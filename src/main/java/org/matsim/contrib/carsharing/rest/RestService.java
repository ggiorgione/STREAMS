package org.matsim.contrib.carsharing.rest;

import org.matsim.contrib.carsharing.entity.Trip;
import org.matsim.contrib.carsharing.manager.demand.RentalInfo;

import java.math.BigInteger;
import java.time.Instant;

public interface RestService {


    Trip getTripById(String tripId);

    Trip rentCar(RentalInfo rentalInfo);

    void startTrip(BigInteger tripId, Instant time);

    Trip cancelTrip(BigInteger id);

    Trip endTrip(RentalInfo RentalInfo);

    void clearTrips();

    void openDoors(BigInteger carId);

    void closeDoors(BigInteger carId);

    void startEngine(BigInteger carId);
}
