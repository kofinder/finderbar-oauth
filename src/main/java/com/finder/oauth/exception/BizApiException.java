package com.finder.oauth.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class BizApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	private String userMessage;
	private Object errorBody;
	private ExceptionType originalEx;
	private int code;
	private int status;
	private Throwable cause;

	public BizApiException(String userMessage) {
		super(userMessage);
	}

	public BizApiException(Throwable cause) {
		super(cause);
	}

	public BizApiException(String userMessage, Throwable cause) {
		super(userMessage, cause);
	}

	public BizApiException(String userMessage, HttpStatus httpStatus, int code, int status, Object object,
			ExceptionType originalEx) {
		super(userMessage);
		this.httpStatus = httpStatus;
		this.errorBody = object;
		this.originalEx = originalEx;
		this.code = code;
		this.status = status;
	}

	public BizApiException(HttpStatus httpStatus, int code, int status, Map<String, Object> errorBody,
			ExceptionType originalEx) {
		this.httpStatus = httpStatus;
		this.errorBody = errorBody;
		this.originalEx = originalEx;
		this.code = code;
		this.status = status;
	}

	public HttpStatus gethttpStatus() {
		return httpStatus;
	}

	public void sethttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public Object getErrorBody() {
		return errorBody;
	}

	public void setErrorBody(Map<String, Object> errorBody) {
		this.errorBody = errorBody;
	}

	public ExceptionType getOriginalEx() {
		return originalEx;
	}

	public void setOriginalEx(ExceptionType originalEx) {
		this.originalEx = originalEx;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

}
