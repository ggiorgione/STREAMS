package org.matsim.contrib.carsharing.manager.demand;

import org.apache.log4j.Logger;
import org.matsim.contrib.carsharing.manager.demand.membership.SimulationTimeConnection;
import org.matsim.core.events.algorithms.TimeProvider;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Thread.sleep;

public class SimulationTimeProvider extends TimeProvider implements Runnable {

    private static final Logger logger = Logger.getLogger(SimulationTimeProvider.class);
    private static final int MIN_TIME_PORT = 1988;
    private static final int NUM_MICROSERVICES = 8;//Should be 9 but we discard scheduler microservice
    private static final int HEARTBEAT_RATE = 1000;
    private static final int CONNECTION_TIMEOUT = 2000;

    private Set<SimulationTimeConnection> connections = new HashSet<>();
    private long currentTimeMillis;
    private long startingSimulationTime;
    private boolean running = true;

    public SimulationTimeProvider() throws IOException {
        this.startingSimulationTime = setStartingTime();
        for (int port = MIN_TIME_PORT; port < MIN_TIME_PORT + NUM_MICROSERVICES; port++) {
            SimulationTimeConnection connection = new SimulationTimeConnection(port);
            connection.connect();
            connections.add(connection);
        }
        logger.info("simulation time provider is connected to all microservices");
        new Thread(this).start();
        new Thread(() -> {
            int missedHeartbeatsBeforeTimeout = Math.round(CONNECTION_TIMEOUT / HEARTBEAT_RATE);
            int missedHeartbeats = 0;
            while (isRunning()) {
                try {
                    sleep(HEARTBEAT_RATE);
                    for (SimulationTimeConnection c: connections) {
                        if (c.receivedHeartbeat()) {
                            c.acceptHeartbeat();
                        } else {
                            c.notifyMissedHeartbeat();
                            if (c.getMissedHeartbeats() >= missedHeartbeatsBeforeTimeout) {
                                logger.info("connection timed out, reconnecting");
                                c.reconnect();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    logger.error("heartbeat thread of simulation time provider was interrupted", e);
                } catch (IOException e) {
                    logger.error("Exception in heartbeat thread of simulation time provider", e);
                }
                logger.debug("simulation time provider sends heartbeat");
                connections.forEach(x -> x.sendHeartbeat());
            }
        }).start();
    }

    @Override
    public void run() {
        try {
            while (isRunning()) {
                for (SimulationTimeConnection c: connections) {
                    try {
                        if (c.clientRequestedTime()) {
                            c.provideTime(currentTimeMillis());
                        }
                    } catch (IOException e) {
                        logger.error("exception while listening for time requests", e);
                    }
                }
            }
        } finally {
            close();
        }
    }

    private void close() {
        for (SimulationTimeConnection c: connections) {
            try {
                c.close();
            } catch (IOException e) {
                logger.error("exception while disconnecting simulation time provider", e);
            }
        }
        logger.info("disconnected simulation time provider");
    }

    public synchronized void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }

    public synchronized long currentTimeMillis() {
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
