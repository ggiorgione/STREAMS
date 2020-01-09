package org.matsim.contrib.carsharing.events.handlers;

import org.matsim.contrib.carsharing.events.AvailableVehiclesNumberEvent;
import org.matsim.core.events.handler.EventHandler;

public interface AvailableVehiclesNumberEventHandler extends EventHandler {

    void handleEvent(AvailableVehiclesNumberEvent event);

}
