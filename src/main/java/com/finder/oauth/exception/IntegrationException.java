package com.finder.oauth.exception;

/**
 * @author jameslwin
 * @createdAt - Mon Feb, 2020
 */
public class IntegrationException extends BizApiException {
	private static final long serialVersionUID = 1L;

	public IntegrationException(String developerMessage) {
		super(developerMessage);
	}

}
