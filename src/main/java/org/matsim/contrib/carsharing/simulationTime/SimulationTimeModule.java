package org.matsim.contrib.carsharing.simulationTime;

import com.google.inject.*;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.events.ShutdownEvent;
import org.matsim.core.controler.listener.ShutdownListener;

public class SimulationTimeModule extends AbstractModule {

    private Controler controler;

    public SimulationTimeModule(Controler controler) {
        this.controler = controler;
    }

    @Override
    public void install() {
        bind(SimulationTimeProvider.class);
        addControlerListenerBinding().to(SimulationTimeShutdownListener.class);
    }

    public static class SimulationTimeShutdownListener implements ShutdownListener {

        @Inject
        private SimulationTimeProvider simulationTimeProvider;

        @Override
        public void notifyShutdown(ShutdownEvent event) {
            simulationTimeProvider.notifyStop();
        }
    }

}
