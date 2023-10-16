package com.finder.oauth.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.finder.oauth.data.UserData;
import com.finder.oauth.domain.Gender;
import com.finder.oauth.service.UserReadPlatformService;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/10/30
 */
@Service
public class UserReadPlatformServiceImpl implements UserReadPlatformService {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserReadPlatformServiceImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public UserData retrive(Long id) {
		final UserMapper rm = new UserMapper();
		final String sql = "select " + rm.schema() + " where u.id=?";
		return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { id });
	}

	@Override
	public Collection<UserData> retriveAll(String keyword, int limit, int offset) {
		final String word = keyword == null ? "" : keyword;
		final String search = new StringBuilder().append("%").append(word).append("%").toString();
	
		final UserMapper rm = new UserMapper();
		final String sql = "select " + rm.schema()
				+ " where u.username like ? or u.email like ? or u.phone_no like ? limit ? offset ?";
		return this.jdbcTemplate.query(sql, rm, new Object[] { search, search, search, limit, offset });
	}

	private static final class UserMapper implements RowMapper<UserData> {
		public String schema() {
			final StringBuffer sb = new StringBuffer();
			sb.append("u.id as id,");
			sb.append("u.username as userName,");
			sb.append("u.email as email,");
			sb.append("u.phone_no as phoneNo,");
			sb.append("u.account_expired as accountExpired,");
			sb.append("u.account_locked as accountLocked,");
			sb.append("u.credentials_expired as credentialsExpired,");
			sb.append("u.enabled as enabled,");
			sb.append("u.verified as verified,");
			sb.append("u.created_at as createdAt,");
			sb.append("u.updated_at as updatedAt,");
			sb.append("u.created_by as createdBy,");
			sb.append("u.updated_by as updatedBy,");
			sb.append("p.first_name as firstName,");
			sb.append("p.last_name as lastName,");
			sb.append("p.gender as gender,");
			sb.append("p.avatar as avatar,");
			sb.append("p.nrc as nrc,");
			sb.append("p.birthday as birthday,");
			sb.append("p.address as address ");
			sb.append("from users u ");
			sb.append("inner join user_profile p  on p.id = u.id ");

			return sb.toString();
		}

		@Override
		public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
			final long id = rs.getLong("id");
			final String firstName = rs.getString("firstName");
			final String lastName = rs.getString("lastName");
			final String userName = rs.getString("userName");
			final String email = rs.getString("email");
			final String phoneNo = rs.getString("phoneNo");

			final Boolean accountExpired = rs.getBoolean("accountExpired");
			final Boolean accountLocked = rs.getBoolean("accountLocked");
			final Boolean credentialsExpired = rs.getBoolean("credentialsExpired");
			final Boolean enabled = rs.getBoolean("enabled");
			final Boolean verified = rs.getBoolean("verified");
			final Date createdAt = rs.getDate("createdAt");
			final Date updatedAt = rs.getDate("updatedAt");
			final Long createdBy = rs.getLong("createdBy");
			final Long updatedBy = rs.getLong("updatedBy");

			final String nrc = rs.getString("nrc");
			final String avatar = rs.getString("avatar");
			final Gender gender = Gender.valueOf(rs.getString("gender"));
			final String birthday = rs.getString("birthday");
			final String address = rs.getString("address");

			return UserData.instance(id, firstName, lastName, userName, email, phoneNo, gender, accountExpired,
					accountLocked, credentialsExpired, enabled, nrc, avatar, birthday, address, verified, createdAt,
					updatedAt, createdBy, updatedBy);
		}
	}

}
