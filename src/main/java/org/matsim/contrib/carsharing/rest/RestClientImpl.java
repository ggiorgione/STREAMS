package org.matsim.contrib.carsharing.rest;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.matsim.contrib.carsharing.manager.PropertyManager;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.Properties;

public class RestClientImpl implements RestClient{


    private Client client;
    private WebTarget target;


    @Inject
    public RestClientImpl(PropertyManager propertyManager) {
        Properties properties = propertyManager.getAppExaProperties();

        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        final ClientConfig cc = new ClientConfig();
        cc.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);
        cc.property(ApacheClientProperties.CONNECTION_MANAGER_SHARED, true);
        cc.connectorProvider(new ApacheConnectorProvider());

        this.client = ClientBuilder.newClient(cc);
        //this.client = ClientBuilder.newClient();

        this.target = client.target(properties.getProperty("serverpath"));
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public WebTarget getTarget() {
        return target;
    }


}
