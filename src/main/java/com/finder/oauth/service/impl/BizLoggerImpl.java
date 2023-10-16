package com.finder.oauth.service.impl;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.finder.oauth.service.BizLogger;
import com.finder.oauth.utils.JsonUtil;

@Service
public class BizLoggerImpl implements BizLogger {
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public void logError(String name, String message, boolean procced) {
		String loggerName = new StringBuffer().append(name).append(": {}").toString();
		logger.error(loggerName, JsonUtil.prettyJSON(message));
		if (procced) {
			logger.debug("*************************************END*************************************************");
		}

	}

	@Override
	public void logRequest(String name, Object obj, boolean procced) {
		if (procced) {
			logger.debug("***********************************START***************************************************");
		}
		String loggerName = new StringBuffer().append(name).append(": {}").toString();
		logger.debug(loggerName, JsonUtil.prettyJSON(obj));

	}

	@Override
	public void logResponse(String name, Object obj, boolean procced) {

		String loggerName = new StringBuffer().append(name).append(": {}").toString();
		logger.debug(loggerName, JsonUtil.prettyJSON(obj));

		if (procced) {
			logger.debug("***********************************END***************************************************");
		}
	}

}
