package com.finder.oauth.service;

//
public interface BizLogger {
	void logError(String name, String message, boolean procced);

	void logRequest(String name, Object obj, boolean procced);

	void logResponse(String name, Object obj, boolean procced);
}
