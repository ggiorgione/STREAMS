package org.matsim.contrib.carsharing.events.handlers;

import org.matsim.contrib.carsharing.events.NoVehicleAtStationCarSharingEvent;
import org.matsim.core.events.handler.EventHandler;

public interface NoVehicleAtStationCarSharingEventHandler extends EventHandler {

   public void handleEvent(NoVehicleAtStationCarSharingEvent event);


}
