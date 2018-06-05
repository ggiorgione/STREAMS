package org.matsim.contrib.carsharing.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public interface RestClient {

    Client getClient();

    WebTarget getTarget();



}
