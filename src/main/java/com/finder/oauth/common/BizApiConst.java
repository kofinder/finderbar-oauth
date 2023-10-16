package com.finder.oauth.common;

public interface BizApiConst {
	public static final String REQ_OAUTH_SERVER_LOGGER = "client.message.tracing.sent";
	public static final String RES_AUTHORIZE_SERVER_LOGGER = "server.message.tracing.response";
	public static final String ERROR_RESOURCE_LOGGER = "server.message.tracing.error";
	
	public static final String USER_PRINCIPAL= "myself";
	public static final String USER = "user";
	public static final String USER_WITH_ID = "user/{id}";
	public static final String SEARCH_USER = "users";

	public static final String RESOURCE = "oauthapp";
	public static final String RESOURCE_WITH_ID = "oauthapp/{id}";
	public static final String SEARCH_RESOURCE = "oauthapps";

	public static final String WIZARD_PASSWORD = "myanmarinuse@#$%2016";
}
