package com.finder.oauth.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.finder.oauth.domain.User;

public interface PlatformSecurityContext {
	User authenticatedUser();
	Collection<? extends GrantedAuthority> resourceAuthoritiy(String resourceId);
}
