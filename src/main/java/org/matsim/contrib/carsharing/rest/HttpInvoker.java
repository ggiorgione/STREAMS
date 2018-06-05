package org.matsim.contrib.carsharing.rest;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.matsim.contrib.carsharing.rest.oauth.OauthUtils;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.function.Function;

public class HttpInvoker {

    private static final Logger log = Logger.getLogger(HttpInvoker.class);

    @Inject
    private KeycloakTokenManager tokenManager;

    @Inject
    private RestClientImpl restClient;


    private WebTarget addParameters(MultivaluedMap<String, String> params, WebTarget target) {
        if (params != null) {
            for (String key : params.keySet()) {
                List<String> value = params.get(key);
                target = target.queryParam(key, value.toArray(new String[0]));
            }
        }
        return target;
    }

    private WebTarget addPathParameters(List<String> pathParamList, WebTarget target) {
        if (pathParamList != null) {
            for (String pathParam : pathParamList) {
                target = target.path(pathParam);
            }
        }
        return target;
    }

    protected Function<RestConfiguration, Response> sendPostMessage = r -> buildRestMessage(r).post(r.getEntity());

    protected Function<RestConfiguration, Response> sendPutMessage = r -> buildRestMessage(r).put(r.getEntity());

    protected Function<RestConfiguration, Response> getMessage = r -> buildRestMessage(r).get();

    protected Function<RestConfiguration, Response> deleteMessage = r -> buildRestMessage(r).delete();

    private Invocation.Builder buildRestMessage(RestConfiguration restConf) {
        WebTarget target = restClient.getTarget();
        target = addPathParameters(restConf.getPathParam(), target);
        target = addParameters(restConf.getParams(), target);
        return addHeaderParam(target.request(), restConf);

    }

    private Invocation.Builder addHeaderParam(Invocation.Builder builder, RestConfiguration restConf) {
        return builder.header(restConf.getHeaderKey(), OauthUtils.getAuthorizationHeaderForAccessToken(restConf.getHeaderValue()));
    }

    /**
     * Access to a protected resource with Oauth 2.0 Rules
     * Get a resource with access token. If the token is expired get a new access token and recall the resource
     */

    protected <R, D> R accessResource(Function<RestConfiguration, Response> callFn, RestConfiguration<R, D> restConf) {

        Response response = callFn.apply(restConf);
        if (response.getStatus() == HttpURLConnection.HTTP_UNAUTHORIZED || response.getStatus() == HttpURLConnection.HTTP_FORBIDDEN) {
            return handleUnauthorizedResponseStatus(callFn, restConf);
        } else if (response.getStatus() >= HttpURLConnection.HTTP_OK && response.getStatus() < HttpURLConnection.HTTP_MULT_CHOICE) {
            return apply(restConf, response);
        } else {
            return handleUnknownResponseStatus(response, restConf);
        }

    }



    private <R, D> R apply(RestConfiguration<R, D> restConf, Response response) {
        return restConf.getFunction().apply(response);
    }

    private <R, D> R handleUnknownResponseStatus(Response response, RestConfiguration<R, D> restConf) {

        String url = "null";

        if(response.getLocation() != null) {
            url = response.getLocation().toString();
        } else if (restConf != null && restConf.getRoute() != null) {
            url = restConf.getRoute();

            if(restConf.getPathParam() != null) {
                url += restConf.getPathParam().toString();
            }
        }

        throw new RuntimeException(String.format("Remote server call failed status: %d, url: %s", response.getStatus(), url));
    }

    private <R, D> R handleUnauthorizedResponseStatus(Function<RestConfiguration, Response> callFn, RestConfiguration<R, D> restConf) {
        log.info("Access token is invalid or expired. Regenerating access token....");
        tokenManager.retrieveToken();
        //Get new token and validate access token
        if (OauthUtils.isValid(tokenManager.getToken().getAccessToken())) {
            return callBackWithNewAccessToken(callFn, restConf);
        } else {
            throw new RuntimeException("Could not regenerate access token");
        }
    }

    private <R, D> R callBackWithNewAccessToken(Function<RestConfiguration, Response> callFn, RestConfiguration<R, D> restConf) {
        restConf.setHeaderValue(tokenManager.getToken().getAccessToken());
        Response response = callFn.apply(restConf);
        if (response.getStatus() >= HttpURLConnection.HTTP_BAD_REQUEST) {
            throw new RuntimeException("Could not access protected resource:" + restConf.getRoute() + " Server returned http code: " + response.getStatus());
        } else {
            return restConf.getFunction().apply(response);
        }
    }


}
