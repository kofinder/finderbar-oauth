
package com.finder.oauth.common;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/11/19
 */
public interface SecurityConstants {
	public static final String RESOURCE_ID = "jovian-finder-rest-api";
	public static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
	public static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
	public static final String SECURED_TRUST_SCOPE = "#oauth2.hasScope('trust')";
	public static final String SECURED_API_ENDPOINT = "/api/**";
}
