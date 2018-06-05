package org.matsim.contrib.carsharing.rest;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.matsim.contrib.carsharing.entity.Trip;

import java.util.LinkedList;

import static org.matsim.contrib.carsharing.entity.Transformation.getExaTrip;

public class ExaRestService implements RestService{

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


    private String getAccessToken(){
        return tokenManager.getToken().getAccessToken();
    }





}
