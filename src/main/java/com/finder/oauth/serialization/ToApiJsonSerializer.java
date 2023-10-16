package com.finder.oauth.serialization;

import com.finder.oauth.domain.Action;

public interface ToApiJsonSerializer {
	String serialize(Object object);
	String serialize(Object object, Action action);
	String serialize(Object object, int totalCount, boolean hasNext);
}
