package com.finder.oauth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.finder.oauth.dto.UserDTO;

public interface UserWritePlatformService extends UserDetailsService {
	Long save(UserDTO dto);

	int update(Long id, UserDTO dto);

	int delete(Long id);
}
