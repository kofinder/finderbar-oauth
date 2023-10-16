package com.finder.oauth.service;

import java.util.Collection;

import com.finder.oauth.data.AuthClientData;
/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/10/30
 */
public interface OauthReadPlatformService {
	AuthClientData retrive(Long id);

	Collection<AuthClientData> retriveAll(String keyword, int limit, int offset);
}
