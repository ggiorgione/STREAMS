package org.matsim.contrib.carsharing.manager;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public interface PropertyManager {


    static final String IS_BACKEND_ENABLED = "enable.backend.call";


    Properties getAppExaProperties();

}
