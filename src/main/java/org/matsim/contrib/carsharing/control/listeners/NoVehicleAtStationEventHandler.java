package org.matsim.contrib.carsharing.control.listeners;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.carsharing.events.NoVehicleAtStationCarSharingEvent;
import org.matsim.contrib.carsharing.events.handlers.NoVehicleAtStationCarSharingEventHandler;
import org.matsim.contrib.carsharing.stations.CarsharingStation;

import java.util.ArrayList;
import java.util.List;

public class NoVehicleAtStationEventHandler implements NoVehicleAtStationCarSharingEventHandler{

    List<NoVehicleAtStationInfo> noVehicleAtStationInfos = new ArrayList<NoVehicleAtStationInfo>();

    @Override
    public void reset(int iteration) {
        noVehicleAtStationInfos = new ArrayList<>();

    }

    @Override
    public void handleEvent(NoVehicleAtStationCarSharingEvent event) {
        NoVehicleAtStationInfo info = new NoVehicleAtStationInfo();
        info.originLinkId = event.getOriginLinkId();
        info.personId = event.getPersonId();
        info.carsharingStation = event.getCarsharingStation();
        info.carsharingType = event.getCarsharingType();
        noVehicleAtStationInfos.add(info);
    }




    public class NoVehicleAtStationInfo{

        Link originLinkId;

        Id<Person> personId;

        CarsharingStation carsharingStation;

        String carsharingType;

        @Override
        public String toString() {
            return "NoVehicleAtStationInfo{" +
                    "originLinkId=" + originLinkId +
                    ", personId=" + personId +
                    ", carsharingStation=" + carsharingStation +
                    ", carsharingType='" + carsharingType + '\'' +
                    '}';
        }
    }
}
