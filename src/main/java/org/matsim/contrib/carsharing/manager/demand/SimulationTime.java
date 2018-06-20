package org.matsim.contrib.carsharing.manager.demand;

import org.matsim.contrib.carsharing.manager.demand.membership.SimulationTimeConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

public class SimulationTime extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(SimulationTime.class);

    private static final int MIN_TIME_PORT = 1988;

    private static final int NUM_MICROSERVICES = 9;

    private Set<SimulationTimeConnection> connections = new HashSet<>();

    private long currentTimeMillis;

    private long startingSimulationTime;

    private boolean running = true;

    public SimulationTime() throws IOException {
        this.startingSimulationTime = setStartingTime();
        for (int port = MIN_TIME_PORT; port < MIN_TIME_PORT + NUM_MICROSERVICES; port++) {
            SimulationTimeConnection connection = new SimulationTimeConnection(port);
            connection.connect();
            connections.add(connection);
        }
        this.start();
    }

    @Override
    public void run() {
        try {
            while (isRunning()) {
                for (SimulationTimeConnection c: connections) {
                    try {
                        if (c.requestedTime()) {
                            logger.info("providing time");
                            c.provideTime(getCurrentTimeMillis());
                        }
                    } catch (IOException e) {
                        logger.error("exception while listening for time requests", e);
                    }
                }
            }
        } finally {
            cleanUp();
        }
    }

    private void cleanUp() {
        for (SimulationTimeConnection c: connections) {
            try {
                c.close();
            } catch (IOException e) {
                logger.error("exception while cleaning up simulation time provider", e);
            }
        }
    }

    public synchronized void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }

    private synchronized long getCurrentTimeMillis() {
        return currentTimeMillis;
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

    private synchronized  boolean isRunning() {
        return running;
    }

    public synchronized void notifyStop() {
        running = false;
    }

}
