package org.matsim.contrib.carsharing.rest;

import org.matsim.contrib.carsharing.entity.Trip;
import org.matsim.contrib.carsharing.manager.demand.RentalInfo;

public interface RestService {


    Trip getTripById(String tripId);

    Trip rentCar(RentalInfo rentalInfo);
}
