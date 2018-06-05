package org.matsim.contrib.carsharing.rest;

import org.matsim.contrib.carsharing.entity.Token;
import org.matsim.contrib.carsharing.manager.PropertyManager;
import org.matsim.contrib.carsharing.manager.PropertyManagerImpl;

public interface TokenManager {

    void retrieveToken();

    Token getToken();
}
