package org.matsim.contrib.carsharing.rest;

import org.matsim.contrib.carsharing.entity.Trip;

public interface RestService {


    Trip getTripById(String tripId);
}
