package org.matsim.contrib.carsharing.rest;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.matsim.contrib.carsharing.entity.Token;
import org.matsim.contrib.carsharing.manager.PropertyManager;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.matsim.contrib.carsharing.rest.oauth.OAuthConstants.*;

public class KeycloakTokenManager implements TokenManager{

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock read  = readWriteLock.readLock();

    private final Lock write = readWriteLock.writeLock();

    private Token token;

    private Properties properties;

    private RestClientImpl restClient;


    @Inject
    public KeycloakTokenManager(PropertyManager propertyManager, RestClientImpl restClient) {
    this.restClient = restClient;
        properties = propertyManager.getAppExaProperties();
        retrieveToken();
    }

    @Override
    public void retrieveToken() {
        write.lock();
        try {
            Form form = new Form();
            form.param(GRANT_TYPE, GRANT_TYPE_CLIENT_CREDENTIALS)
                    .param(CLIENT_SECRET, properties.getProperty("keycloak.client.secret"))
                    .param(CLIENT_ID, properties.getProperty("keycloack.clientId"));

            WebTarget webTarget = restClient.getClient().target(properties.getProperty("keycloak.token.uri"));
            Response response = webTarget.request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));

            if (response.getStatus() >= HTTP_OK && response.getStatus() < HTTP_REDIRECTION) {
                token = response.readEntity(Token.class);
            } else if (response.getStatus() == HTTP_UNAUTHORIZED || response.getStatus() == HTTP_FORBIDDEN) {
                throw new RuntimeException("Could not retrieve access token");
            } else
                throw new RuntimeException("Remote server call failed status: " + response.getStatus());

        }finally {
            write.unlock();
        }

    }

    @Override
    public Token getToken() {
        read.lock();
        try{
            return token;
        }finally {
            read.unlock();
        }
    }
}
