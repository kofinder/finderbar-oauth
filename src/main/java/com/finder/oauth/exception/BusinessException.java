package com.finder.oauth.exception;

/**
 * @author jameslwin
 * @createdAt - Mon Feb, 2020
 */
public class BusinessException extends BizApiException {
	private static final long serialVersionUID = 6787644359617893158L;

	public BusinessException(String developerMessage) {
		super(developerMessage);
	}

}
