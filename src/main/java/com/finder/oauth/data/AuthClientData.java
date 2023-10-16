package com.finder.oauth.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

@SuppressWarnings("unused")
public class AuthClientData {
	private final Long id;

	private final String clientId;

	private final String uuid;

	private final String clientName;

	private final String resourceIds;

	private final String clientSecret;

	private final String avatar;

	private final String scope;

	private final String authorizedGrantTypes;

	private final String webServerRedirectUri;

	private final String authorities;

	private final Integer accessTokenValidity;

	private final Integer refreshTokenValidity;

	private final String additionalInformation;

	private final Integer autoapprove;

	private final Boolean enabled;

	private final String[] scopes;

	private final String[] grantTypes;

	private final String ownerEmail;

	private final Date createdAt;

	private final Date updatedAt;

	private final Long createdBy;

	private final Long updatedBy;

	public AuthClientData(Long id, String clientId, String uuid, String clientName, String resourceIds,
			String clientSecret, String avatar, String scope, String authorizedGrantTypes, String webServerRedirectUri,
			String authorities, Integer accessTokenValidity, Integer refreshTokenValidity, String additionalInformation,
			Integer autoapprove, Boolean enabled, String[] scopes, String[] grantTypes, String ownerEmail,
			Date createdAt, Date updatedAt, Long createdBy, Long updatedBy) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.uuid = uuid;
		this.clientName = clientName;
		this.resourceIds = resourceIds;
		this.clientSecret = clientSecret;
		this.avatar = avatar;
		this.scope = scope;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.webServerRedirectUri = webServerRedirectUri;
		this.authorities = authorities;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.additionalInformation = additionalInformation;
		this.autoapprove = autoapprove;
		this.enabled = enabled;
		this.scopes = scopes;
		this.grantTypes = grantTypes;
		this.ownerEmail = ownerEmail;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public static AuthClientData instance(Long id, String clientId, String uuid, String clientName, String resourceIds,
			String clientSecret, String avatar, String scope, String authorizedGrantTypes, String webServerRedirectUri,
			String authorities, Integer accessTokenValidity, Integer refreshTokenValidity, String additionalInformation,
			Integer autoapprove, Boolean enabled, String[] scopes, String[] grantTypes, String ownerEmail,
			Date createdAt, Date updatedAt, Long createdBy, Long updatedBy) {
		return new AuthClientData(id, clientId, uuid, clientName, resourceIds, clientSecret, avatar, scope,
				authorizedGrantTypes, webServerRedirectUri, authorities, accessTokenValidity, refreshTokenValidity,
				additionalInformation, autoapprove, enabled, scopes, grantTypes, ownerEmail, createdAt, updatedAt,
				createdBy, updatedBy);
	}

}
