package com.finder.oauth.dto;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/11/08
 */
public class GrantedAuthorityDTO implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 8568809630894934391L;
	private String name;

	public GrantedAuthorityDTO(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}
