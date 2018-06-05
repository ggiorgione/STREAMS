package org.matsim.contrib.carsharing.rest.oauth;

public class OAuthConstants {

    private OAuthConstants() {

    }

    public static final String GRANT_TYPE = "grant_type";
    public static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";

    public static final String TOKEN_TYPE_BEARER = "Bearer";

    public static final int HTTP_OK = 200;
    public static final int HTTP_REDIRECTION = 300;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_UNAUTHORIZED = 401;


}
