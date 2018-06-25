package org.matsim.contrib.carsharing.manager.demand.membership;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimulationTimeConnection {

    private static final Logger logger = Logger.getLogger(SimulationTimeConnection.class);

    private int port;
    private Socket socket = new Socket();
    private BufferedReader in;
    private PrintWriter out;
    private boolean receivedHeartbeat;
    private int pendingTimeRequests;
    private int missedHeartbeats;

    public SimulationTimeConnection(int port) {
        this.port = port;
    }

    public synchronized void connect() throws IOException {
        socket = new Socket("localhost", port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
        receivedHeartbeat = false;
        missedHeartbeats = 0;
    }

    public synchronized void close() throws IOException {
        out.close();
        in.close();
        socket.close();
        receivedHeartbeat = false;
        missedHeartbeats = 0;
    }

    public synchronized void reconnect() throws IOException {
        close();
        connect();
    }

    public synchronized void provideTime(long l) {
        logger.debug("simulation time provider sends timestamp");
        out.println(l);
        out.flush();
        pendingTimeRequests--;
    }

    public synchronized void sendHeartbeat() {
        out.println("heartbeat");
        out.flush();
    }

    public synchronized boolean receivedHeartbeat() throws IOException {
        read();
        return receivedHeartbeat;
    }

    public synchronized boolean clientRequestedTime() throws IOException {
        read();
        return pendingTimeRequests > 0;
    }

    public synchronized void notifyMissedHeartbeat() {
        missedHeartbeats++;
        logger.debug("missed " + missedHeartbeats + " heartbeats");
    }

    public synchronized void acceptHeartbeat() {
        assert receivedHeartbeat;
        receivedHeartbeat = false;
        missedHeartbeats = 0;
    }

    public synchronized int getMissedHeartbeats() {
        return missedHeartbeats;
    }

    private synchronized void read() throws IOException {
        if (!socket.isClosed()) {
            while (in.ready()) {
                String msg = in.readLine();
                switch (msg) {
                    case "heartbeat":
                        receivedHeartbeat = true;
                        logger.debug("received heartbeat");
                        break;
                    case "timestamp":
                        pendingTimeRequests++;
                        logger.debug("received time request");
                        break;
                    default:
                        logger.error("ignoring unknown message " + msg);
                }
            }
        }
    }

}
