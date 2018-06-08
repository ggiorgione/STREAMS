package org.matsim.contrib.carsharing.rest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.Function;

public class RestConfiguration<R, D> {

    private String route;

    private MultivaluedHashMap<String, String> params;

	private List<String> pathParam;

    private String headerKey = HttpHeaders.AUTHORIZATION;

    private String headerValue;

	private Function<Response, R> function;

	private Entity<D> entity;



    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param route
     * @param function
     */
	public RestConfiguration(String route, Function<Response, R> function) {
        this.route = route;
        this.function = function;
    }


    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param route
     * @param headerValue
     * @param function
     */
	public RestConfiguration(String route, String headerValue, Function<Response, R> function) {
        this.route = route;
        this.headerValue = headerValue;
        this.function = function;
    }

    /**
     * Constructor generally used for post/put method. Pass a function to transform a result
     * @param route
     * @param headerValue
     * @param function
     * @param entity
     */
	public RestConfiguration(String route, String headerValue, Function<Response, R> function, Entity<D> entity) {
        this.route = route;
        this.headerValue = headerValue;
        this.function = function;
        this.entity = entity;
    }


    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param pathParam
     * @param headerValue
     * @param function
     */
    public RestConfiguration(List<String> pathParam, String headerValue, Function<Response, R> function) {
        this.pathParam = pathParam;
        this.headerValue = headerValue;
        this.function = function;
    }

    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param pathParam
     * @param headerValue
     * @param function
     * @param entity
     */
    public RestConfiguration(List<String> pathParam, String headerValue, Function<Response, R> function, Entity<D> entity) {
        this.pathParam = pathParam;
        this.headerValue = headerValue;
        this.function = function;
        this.entity = entity;
    }

    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param route
     * @param pathParam
     * @param headerValue
     * @param function
     */
	public RestConfiguration(String route, List<String> pathParam, String headerValue,
                             Function<Response, R> function) {
        this.route = route;
        this.pathParam = pathParam;
        this.headerValue = headerValue;
        this.function = function;
    }


    /**
     * Constructor generally used for post/put method. Pass a function to transform a result
     * @param route
     * @param pathParam
     * @param headerValue
     * @param function
     * @param entity
     */
	public RestConfiguration(String route, List<String> pathParam, String headerValue,
                             Function<Response, R> function,
                             Entity<D> entity) {
        this.route = route;
        this.pathParam = pathParam;
        this.headerValue = headerValue;
        this.function = function;
        this.entity = entity;
    }

    /**
     * Constructor generally used for post/put method. Pass a function to transform a result
     * @param route
     * @param params
     * @param headerValue
     * @param function
     * @param entity
     */
    public RestConfiguration(String route, MultivaluedHashMap<String, String> params,
                             String headerValue, Function<Response, R> function, Entity<D> entity) {
        this.route = route;
        this.params = params;
        this.headerValue = headerValue;
        this.function = function;
        this.entity = entity;
    }

    /**
     * Constructor generally used for post/put method. Pass a function to transform a result
     * @param route
     * @param params
     * @param pathParam
     * @param headerValue
     * @param function
     * @param entity
     */
	public RestConfiguration(String route, MultivaluedHashMap<String, String> params, List<String> pathParam,
                             String headerValue, Function<Response, R> function, Entity<D> entity) {
        this.route = route;
        this.params = params;
        this.pathParam = pathParam;
        this.headerValue = headerValue;
        this.function = function;
        this.entity = entity;
    }

    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param route
     * @param params
     * @param headerValue
     * @param function
     */
	public RestConfiguration(String route, MultivaluedHashMap<String, String> params, String headerValue,
                             Function<Response, R> function) {
        this.route = route;
        this.params = params;
        this.headerValue = headerValue;
        this.function = function;
    }

    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param route
     * @param params
     * @param pathParam
     * @param headerValue
     * @param function
     */
	public RestConfiguration(String route, MultivaluedHashMap<String, String> params, List<String> pathParam,
                             String headerValue, Function<Response, R> function) {
        this.route = route;
        this.params = params;
        this.pathParam = pathParam;
        this.headerValue = headerValue;
        this.function = function;
    }

    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param route
     * @param headerKey
     * @param headerValue
     * @param function
     */
	public RestConfiguration(String route, String headerKey, String headerValue, Function<Response, R> function) {
        this.route = route;
        this.headerKey = headerKey;
        this.headerValue = headerValue;
        this.function = function;
    }

    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param route
     * @param params
     * @param function
     */
	public RestConfiguration(String route, MultivaluedHashMap<String, String> params, Function<Response, R> function) {
        this.route = route;
        this.params = params;
        this.function = function;
    }

    /**
     * Constructor generally used for get method. Pass a function to transform a result
     * @param route
     * @param params
     * @param headerKey
     * @param headerValue
     * @param function
     */
	public RestConfiguration(String route, MultivaluedHashMap<String, String> params, String headerKey,
                             String headerValue, Function<Response, R> function) {
        this.route = route;
        this.params = params;
        this.headerKey = headerKey;
        this.headerValue = headerValue;
        this.function = function;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }


    public MultivaluedHashMap<String, String> getParams() {
        return params;
    }

    public void setParams(MultivaluedHashMap<String, String> params) {
        this.params = params;
    }

	public List<String> getPathParam() {
        return pathParam;
    }

	public void setPathParam(List<String> pathParam) {
        this.pathParam = pathParam;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

	public Function<Response, R> getFunction() {
        return function;
    }

	public void setFunction(Function<Response, R> function) {
        this.function = function;
    }

	public Entity<D> getEntity() {
        return entity;
    }

	public void setEntity(Entity<D> entity) {
        this.entity = entity;
    }
}
