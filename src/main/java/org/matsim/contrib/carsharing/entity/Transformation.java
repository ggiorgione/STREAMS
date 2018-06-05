package org.matsim.contrib.carsharing.entity;

import javax.ws.rs.core.Response;
import java.util.function.Function;

public class Transformation {

	public static final Function<Response, Trip> getExaTrip = r -> r.readEntity(Trip.class);




}
