package com.finder.oauth.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

/**
 * @author jameslwin
 * @createdAt - Mon Feb, 2020
 */

public class CommonException extends BizApiException {

	private static final long serialVersionUID = 1892640383295317964L;

	public CommonException(HttpStatus statusCode, int rawStatusCode, Map<String, Object> errBody) {
		super(statusCode, rawStatusCode, rawStatusCode, errBody, ExceptionType.COMMON_EXCEPTION);
	}

}
