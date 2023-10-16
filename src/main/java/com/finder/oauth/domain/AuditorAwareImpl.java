package com.finder.oauth.domain;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/10/30
 */
public class AuditorAwareImpl implements AuditorAware<User> {

	@SuppressWarnings("unchecked")
	@Override
	public Optional<User> getCurrentAuditor() {
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		final Authentication authentication = securityContext.getAuthentication();
		return (Optional<User>) authentication.getPrincipal();
	}

}
