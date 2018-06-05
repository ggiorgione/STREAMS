package org.matsim.contrib.carsharing.rest.oauth;



public class OauthUtils {

	/**
	 * Token is valid
	 * @param str
	 * @return true is valid false viceversa
	 */
	public static boolean isValid(String str) {
		return (str != null && str.trim().length() > 0);
	}

	public static String getAuthorizationHeaderForAccessToken(String accessToken) {
		return OAuthConstants.TOKEN_TYPE_BEARER + " " + accessToken;
	}
}
