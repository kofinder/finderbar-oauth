package com.finder.oauth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finder.oauth.domain.AuthClientDetails;
import com.finder.oauth.domain.User;
import com.finder.oauth.dto.OAuthDTO;
import com.finder.oauth.exception.BusinessException;
import com.finder.oauth.repository.AuthClientDetailRepository;
import com.finder.oauth.service.OauthWritePlatformService;
import com.finder.oauth.service.PlatformSecurityContext;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/43/03
 */
@Service
public class OauthWritePlatformServiceImpl implements OauthWritePlatformService {

	private final AuthClientDetailRepository authClientRepo;
	private final PasswordEncoder passEncoder;
	private final PlatformSecurityContext context;

	public OauthWritePlatformServiceImpl(final AuthClientDetailRepository authClientRepo,
			final PlatformSecurityContext context, final PasswordEncoder passEncoder) {
		this.authClientRepo = authClientRepo;
		this.passEncoder = passEncoder;
		this.context = context;
	}

	@Override
	public Long save(OAuthDTO dto) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("systeminfo", dto.getDescription());
			
			final User user = this.context.authenticatedUser();
			final AuthClientDetails auth = new AuthClientDetails();
			auth.setAppName(dto.getAppName());
			auth.setClientId(dto.getOwnerName());
			auth.setClientName(dto.getOwnerName());
			auth.setOwnerEmail(dto.getOwnerEmail());
			auth.setScope(dto.getScope());
			auth.setAuthorizedGrantTypes(dto.getGrantTypes());
			auth.setWebServerRedirectUri(dto.getRedirectUri());
			auth.setClientSecret(passEncoder.encode(dto.getAppSerect()));
			auth.setAccessTokenValidity(dto.getAccessTokenValidity());
			auth.setRefreshTokenValidity(dto.getRefreshTokenValidity());
			auth.setAdditionalInformation(mapper.writeValueAsString(dto.getDescription()));
			auth.setAuthorities("RESOURCE");
			auth.setAvatar(dto.getAppLogo());
			auth.setCreatedAt(new Date());
			auth.setCreatedBy(user.getId());
			auth.setUpdatedAt(new Date());
			auth.setUpdatedBy(user.getId());

			AuthClientDetails result = this.authClientRepo.save(auth);
			return result.getId();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
    @Transactional
	public Long update(Long id, OAuthDTO dto) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("systeminfo", dto.getDescription());
			
			final User user = this.context.authenticatedUser();
			final AuthClientDetails auth = new AuthClientDetails();
			auth.setId(id);
			auth.setAppName(dto.getAppName());
			auth.setClientId(dto.getOwnerName());
			auth.setClientName(dto.getOwnerName());
			auth.setOwnerEmail(dto.getOwnerEmail());
			auth.setScope(dto.getScope());
			auth.setAuthorizedGrantTypes(dto.getGrantTypes());
			auth.setWebServerRedirectUri(dto.getRedirectUri());
			auth.setClientSecret(passEncoder.encode(dto.getAppSerect()));
			auth.setAccessTokenValidity(dto.getAccessTokenValidity());
			auth.setRefreshTokenValidity(dto.getRefreshTokenValidity());
			auth.setAdditionalInformation(mapper.writeValueAsString(dto.getDescription()));
			auth.setAuthorities("RESOURCE");
			auth.setAvatar(dto.getAppLogo());
			auth.setUpdatedAt(new Date());
			auth.setUpdatedBy(user.getId());

			AuthClientDetails result = this.authClientRepo.save(auth);
			return result.getId();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public int delete(Long id) {
		this.authClientRepo.deleteById(id);
		return 0;
	}

}
