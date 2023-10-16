package com.finder.oauth.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.stereotype.Component;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/11/19
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {

	private static Logger logger = LoggerFactory.getLogger(AuthenticationEventListener.class);

	@Override
	public void onApplicationEvent(AbstractAuthenticationEvent event) {

		try {
			Authentication authentication = event.getAuthentication();
			logger.info("[{}] logged in successfully", authentication.getName());
			if ((event instanceof InteractiveAuthenticationSuccessEvent)
					|| (event instanceof SessionFixationProtectionEvent)) {
				logger.debug("Authentication event [{}]", event.getClass());
				return;
			}

			if (authentication.isAuthenticated()) {
				logger.info("[{}] logged in successfully", authentication.getName());
			} else {
				logger.warn("[{}] failed to log in with password [{}]", authentication.getName(),
						authentication.getCredentials().toString().replaceAll(".", "*"));
				// Simulate a stacktrace
				if (authentication.getName().equals("admin")) {
					throw new RuntimeException("There is no admin here...");
				}
			}
		} finally {
			MDC.clear();
		}
	}

}
