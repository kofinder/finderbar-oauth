package com.finder.oauth.service;

import java.util.Collection;

import com.finder.oauth.data.UserData;

public interface UserReadPlatformService {
	UserData retrive(Long id);

	Collection<UserData> retriveAll(String keyword, int limit, int offset);
}
