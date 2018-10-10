package org.matsim.contrib.carsharing.manager;


import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.matsim.contrib.carsharing.runExample.RunCarsharing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManagerImpl implements PropertyManager{

    private final static Logger log = Logger.getLogger(PropertyManagerImpl.class);

    private Properties appExaProperties;

    @Inject
    public PropertyManagerImpl(String configExamotiveFile) {
        loadConficuration(configExamotiveFile);
    }


    private void loadConficuration(String configurationFile) {
        try {
            appExaProperties = new Properties();
            appExaProperties.load(new FileInputStream(configurationFile));
        } catch (IOException e) {
            log.error("Error loading properties {}", e);
        }

    }


    public Properties getAppExaProperties() {
        return appExaProperties;
    }
}
