package org.matsim.contrib.carsharing.manager.demand.membership;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SimulationTimeConnection {

    private int port;
    private Socket socket = new Socket();
    private BufferedReader in;
    private PrintWriter out;

    public SimulationTimeConnection(int port) {
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket("localhost", port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    }

    public void close() throws IOException {
        requestShutdown();
        out.close();
        in.close();
        socket.close();
    }

    public void provideTime(long l) {
        out.println(l);
        out.flush();
    }

    private void requestShutdown() {
        out.println("close");
        out.flush();
    }

    public boolean requestedTime() throws IOException {
        if (in.ready()) {
            in.readLine();
            return true;
        } else {
            return false;
        }
    }

}
