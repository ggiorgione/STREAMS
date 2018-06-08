package org.matsim.contrib.carsharing.manager.demand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class SimulationTime {


    private long startingSimulationTime;

    public SimulationTime() {
        this.startingSimulationTime = setStartingTime();
    }

    public long getStartingSimulationTime() {
        return startingSimulationTime;
    }

    public synchronized void resetSimulationTime(){
        this.startingSimulationTime = setStartingTime();
    }

    private long setStartingTime(){
        LocalDateTime local = LocalDate.now().atStartOfDay();
        return local.toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
    }

}
