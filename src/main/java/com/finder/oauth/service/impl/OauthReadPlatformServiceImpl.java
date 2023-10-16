package com.finder.oauth.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.finder.oauth.data.AuthClientData;
import com.finder.oauth.service.OauthReadPlatformService;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/43/03
 */
@Service
public class OauthReadPlatformServiceImpl implements OauthReadPlatformService {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public OauthReadPlatformServiceImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public AuthClientData retrive(Long id) {
		final AuthClientMapper rm = new AuthClientMapper();
		final String sql = "select " + rm.schema() + " where c.id=?";
		return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { id });
	}

	@Override
	public Collection<AuthClientData> retriveAll(String keyword, int limit, int offset) {
		final String word = keyword == null ? "" : keyword;
		final String search = new StringBuilder().append("%").append(word).append("%").toString();
		final AuthClientMapper rm = new AuthClientMapper();
		final String sql = "select " + rm.schema()
				+ " where c.client_name like ? or c.owner_email like ? or c.client_id like ? limit ? offset ?";
		return this.jdbcTemplate.query(sql, rm, new Object[] { search, search, search, limit, offset });
	}

	private final class AuthClientMapper implements RowMapper<AuthClientData> {

		public String schema() {
			final StringBuffer sb = new StringBuffer();
			sb.append("c.id as id,");
			sb.append("c.uuid as uuid,");
			sb.append("c.client_id as clientId,");
			sb.append("c.client_name as clientName,");
			sb.append("c.resource_ids as resourceIds,");
			sb.append("c.client_secret as clientSecret,");
			sb.append("c.scope as scope,");
			sb.append("c.authorized_grant_types as authorizedGrantTypes,");
			sb.append("c.web_server_redirect_uri as webServerRedirectUri,");
			sb.append("c.authorities as authorities,");
			sb.append("c.access_token_validity as accessTokenValidity,");
			sb.append("c.refresh_token_validity as refreshTokenValidity,");
			sb.append("c.additional_information as additionalInformation,");
			sb.append("c.autoapprove as autoapprove,");
			sb.append("c.enabled as enabled,");
			sb.append("c.owner_email as ownerEmail,");
			sb.append("c.avatar as avatar,");
			sb.append("c.created_at as createdAt,");
			sb.append("c.updated_at as updatedAt,");
			sb.append("c.created_by as createdBy,");
			sb.append("c.updated_by as updatedBy ");
			sb.append("from oauth_client_details c ");

			return sb.toString();
		}

		@Override
		public AuthClientData mapRow(ResultSet rs, int rowNum) throws SQLException {
			final Long id = rs.getLong("id");
			final String uuid = rs.getString("uuid");
			final String clientId = rs.getString("clientId");
			final String clientName = rs.getString("clientName");
			final String resourceIds = rs.getString("resourceIds");
			final String clientSecret = rs.getString("clientSecret");
			final String scope = rs.getString("scope");
			final String authorizedGrantTypes = rs.getString("authorizedGrantTypes");
			final String webServerRedirectUri = rs.getString("webServerRedirectUri");
			final String authorities = rs.getString("authorities");
			final Integer accessTokenValidity = rs.getInt("accessTokenValidity");
			final Integer refreshTokenValidity = rs.getInt("refreshTokenValidity");
			final String additionalInformation = rs.getString("additionalInformation");
			final Integer autoapprove = rs.getInt("autoapprove");
			final Boolean enabled = rs.getBoolean("enabled");
			final String ownerEmail = rs.getString("ownerEmail");
			final String avatar = rs.getString("avatar");
			final Date createdAt = rs.getDate("createdAt");
			final Date updatedAt = rs.getDate("updatedAt");
			final Long createdBy = rs.getLong("createdBy");
			final Long updatedBy = rs.getLong("updatedBy");

			return AuthClientData.instance(id, clientId, uuid, clientName, resourceIds, clientSecret, avatar, scope,
					authorizedGrantTypes, webServerRedirectUri, authorities, accessTokenValidity, refreshTokenValidity,
					additionalInformation, autoapprove, enabled, null, null, ownerEmail, createdAt, updatedAt,
					createdBy, updatedBy);
		}

	}

}
