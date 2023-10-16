package com.finder.oauth.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.finder.oauth.domain.User;
import com.finder.oauth.repository.UserRepository;
import com.finder.oauth.service.PlatformSecurityContext;

@Service
public class PlatformSecurityContextImpl implements PlatformSecurityContext {
	private final UserRepository userRepo;

	@Autowired
	public PlatformSecurityContextImpl(final UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User authenticatedUser() {
		User currentUser = null;
		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication auth = context.getAuthentication();
			if (auth != null) {
				currentUser = this.userRepo.findByUsername((String) auth.getPrincipal());
			}

		}
		return currentUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> resourceAuthoritiy(String resourceId) {
		final User user = this.userRepo.findByUsername(resourceId);
		return user.getAuthorities();
	}
}
