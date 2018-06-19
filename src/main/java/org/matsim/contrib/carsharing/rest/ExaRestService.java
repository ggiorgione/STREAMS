package org.matsim.contrib.carsharing.rest;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.matsim.contrib.carsharing.entity.OTAPush;
import org.matsim.contrib.carsharing.entity.Trip;
import org.matsim.contrib.carsharing.manager.demand.RentalInfo;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.matsim.contrib.carsharing.entity.DateUtils.instant2String;
import static org.matsim.contrib.carsharing.entity.Transformation.*;

public class ExaRestService implements RestService{

    private static final String TRIP_SIM_URI = "exa-booking/rest/sim/trips";
    private static final String TRIP_URI = "exa-booking/rest/trips";
    private static final String PUSH_SIM_URI = "exa-connection/rest/sim/push";
    private static final String TIME_URI = "exa-booking/rest/sim/time";
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
        pathParams.add(TRIP_URI);

        RestConfiguration<Trip, Trip> bookingConf = new RestConfiguration<>(pathParams, getAccessToken(), getExaTrip, Entity.entity(trip, MediaType.APPLICATION_JSON));

        return httpInvoker.accessResource(httpInvoker.sendPostMessage, bookingConf);
    }

    @Override
    public void startTrip(Trip trip) {
        List<String> pushRoute = asList(PUSH_SIM_URI, "key", "pickup");
        MultivaluedHashMap<String, String> params = new MultivaluedHashMap<>();
        params.add("tripId", trip.getId().toString());
        params.add("beginDate", instant2String(trip.getRealStart().toInstant()));
        // TODO: What's the purpose of 'route'?
        RestConfiguration<Void, String> pushConf = new RestConfiguration<>(
                null,
                params,
                pushRoute,
                getAccessToken(),
                r -> null,
                Entity.text(""));
        httpInvoker.accessResource(httpInvoker.sendPutMessage, pushConf);
    }

    @Override
    public Trip cancelTrip(BigInteger id) {
        List<String> route = asList(TRIP_URI, id.toString());
        RestConfiguration<Trip, Void> restConf = new RestConfiguration<>(route, getAccessToken(), getExaTrip);
        return httpInvoker.accessResource(httpInvoker.deleteMessage, restConf);
    }

    @Override
    public Trip endTrip(RentalInfo rentalInfo) {
        String token = getAccessToken();
        List<String> pushRoute = asList(PUSH_SIM_URI, "key", "putback");
        OTAPush push = rentalInfo2OTAPush(rentalInfo);
        Entity<OTAPush> entity = Entity.entity(push, MediaType.APPLICATION_JSON);
        RestConfiguration<Trip, OTAPush> pushConf = new RestConfiguration<>(pushRoute, token, getExaTrip, entity);
        return httpInvoker.accessResource(httpInvoker.sendPostMessage, pushConf);
    }

    @Override
    public void setTime(long l) {
        String token = getAccessToken();
        List<String> pathParam = asList(TIME_URI, "currentTimeMillis");
        Entity<String> entity = Entity.text(Long.toString(l));
        RestConfiguration<Void, String> conf = new RestConfiguration<>(pathParam, token, x -> null, entity);
        httpInvoker.accessResource(httpInvoker.sendPutMessage, conf);
    }

    @Override
    public void clearTrips() {
        String token = getAccessToken();
        List<String> pathParam = asList(TRIP_SIM_URI);
        RestConfiguration<Void, Void> conf = new RestConfiguration<>(pathParam, token, x -> null);
        httpInvoker.accessResource(httpInvoker.deleteMessage, conf);
    }

    private String getAccessToken(){
        return tokenManager.getToken().getAccessToken();
    }





}
