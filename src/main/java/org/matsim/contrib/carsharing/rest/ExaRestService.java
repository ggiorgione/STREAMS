package org.matsim.contrib.carsharing.rest;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.matsim.contrib.carsharing.entity.Trip;
import org.matsim.contrib.carsharing.manager.demand.RentalInfo;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;

import static org.matsim.contrib.carsharing.entity.Transformation.getExaTrip;
import static org.matsim.contrib.carsharing.entity.Transformation.rentalInfo2Trip;

public class ExaRestService implements RestService{

    private static final String TRIP_SIM_URI = "exa-booking/rest/sim/trips";
    private static final String TRIP_URI = "exa-booking/rest/trips";
    private static final Logger log = Logger.getLogger(ExaRestService.class);

    @Inject
    private KeycloakTokenManager tokenManager;

    @Inject
    private HttpInvoker httpInvoker;


    public Trip getTripById(String tripId){

        LinkedList<String> pathParams = new LinkedList<>();
        pathParams.add(TRIP_URI);
        pathParams.add(tripId);

        RestConfiguration<Trip, Void> restConf = new RestConfiguration<>(pathParams, getAccessToken(), getExaTrip);

        return httpInvoker.accessResource(httpInvoker.getMessage, restConf);

    }


    public Trip rentCar(RentalInfo rentalInfo){

        Trip trip = rentalInfo2Trip(rentalInfo);

        LinkedList<String> pathParams = new LinkedList<>();
        pathParams.add(TRIP_SIM_URI);

        RestConfiguration<Trip, Trip> restConf = new RestConfiguration<>(pathParams, getAccessToken(), getExaTrip, Entity.entity(trip, MediaType.APPLICATION_JSON));

        return httpInvoker.accessResource(httpInvoker.sendPostMessage, restConf);

    }







    private String getAccessToken(){
        return tokenManager.getToken().getAccessToken();
    }





}
