package com.finder.oauth.service;

import com.finder.oauth.dto.OAuthDTO;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/43/03
 */
public interface OauthWritePlatformService {
	Long save(OAuthDTO dto);

	Long update(Long id, OAuthDTO dto);

	int delete(Long id);
}
