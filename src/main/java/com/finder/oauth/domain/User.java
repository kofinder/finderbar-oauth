package com.finder.oauth.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {
		"username" }, name = "USER_UNIQUE_USERNAME"))
public class User extends AbstractPersistableCustom<Long> implements PlatformUser {

	private static final long serialVersionUID = 1L;

	@Column(name = "username")
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_no")
	private String phoneNo;

	@Column
	private String password;

	@Column
	private Boolean accountExpired;

	@Column
	private Boolean accountLocked;

	@Column
	private Boolean credentialsExpired;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "verified")
	private Boolean verified;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "updated_by")
	private Long updatedBy;

	@OneToMany(mappedBy = "user", targetEntity = UserAuthority.class, cascade = {
			CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<UserAuthority> userAuthorities = new HashSet<UserAuthority>();

	@OneToOne(targetEntity = UserProfile.class, cascade = CascadeType.ALL)
	private UserProfile profile;

//	private Set<GrantedAuthority> grantedAuthorities;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public Boolean getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public Boolean getCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Set<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return populateGrantedAuthorities();
	}

	private List<GrantedAuthority> populateGrantedAuthorities() {
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		for (final UserAuthority userAuthorities : this.userAuthorities) {
			Authority authorities = userAuthorities.getAuthority();
			grantedAuthorities.add(new SimpleGrantedAuthority(authorities.getAuthority()));
		}
		return grantedAuthorities;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
