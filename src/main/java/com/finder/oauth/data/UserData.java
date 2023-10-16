package com.finder.oauth.data;

import java.util.Date;

import com.finder.oauth.domain.Gender;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/10/30
 */
@SuppressWarnings("unused")
public class UserData {
	private final Long id;
	private final String firstName;
	private final String lastName;
	private final String userName;
	private final String email;
	private final String phoneNo;
	private final Gender gender;
	private final Boolean accountExpired;
	private final Boolean accountLocked;
	private final Boolean credentialsExpired;
	private final Boolean enabled;
	private final String nrc;
	private final String avatar;
	private final String birthday;
	private final String address;
	private final Boolean verified;
	private final Date createdAt;
	private final Date updatedAt;
	private final Long createdBy;
	private final Long updatedBy;

	public UserData(Long id, String firstName, String lastName, String userName, String email, String phoneNo,
			Gender gender, Boolean accountExpired, Boolean accountLocked, Boolean credentialsExpired, Boolean enabled,
			String nrc, String avatar, String birthday, String address, Boolean verified, Date createdAt,
			Date updatedAt, Long createdBy, Long updatedBy) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.credentialsExpired = credentialsExpired;
		this.enabled = enabled;
		this.nrc = nrc;
		this.avatar = avatar;
		this.birthday = birthday;
		this.address = address;
		this.verified = verified;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public static UserData instance(Long id, String firstName, String lastName, String userName, String email,
			String phoneNo, Gender gender, Boolean accountExpired, Boolean accountLocked,
			Boolean credentialsExpired, Boolean enabled, String nrc, String avatar, String birthday, String address,
			Boolean verified, Date createdAt, Date updatedAt, Long createdBy, Long updatedBy) {
		
		return new UserData(id, firstName, lastName, userName, email, phoneNo, gender, accountExpired, accountLocked,
				credentialsExpired, enabled, nrc, avatar, birthday, address, verified, createdAt, updatedAt, createdBy,
				updatedBy);

	}

}
