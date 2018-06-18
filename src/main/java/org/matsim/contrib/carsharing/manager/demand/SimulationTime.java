package org.matsim.contrib.carsharing.manager.demand;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class SimulationTime {

    private PrintStream out;

    private long startingSimulationTime;

    public SimulationTime() throws IOException {
        this.startingSimulationTime = setStartingTime();
        Socket socket = new Socket("172.18.0.14", 1987);
        this.out = new PrintStream(socket.getOutputStream());
    }

    public void pushCurrentTimeMillis(long l) {
        if (out != null) {
            out.println(l);
        }
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
