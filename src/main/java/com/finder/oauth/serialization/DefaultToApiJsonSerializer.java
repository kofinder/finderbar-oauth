package com.finder.oauth.serialization;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.finder.oauth.domain.Action;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/11/06
 */
@Component
public final class DefaultToApiJsonSerializer implements ToApiJsonSerializer {

	private final Gson gson;

	public DefaultToApiJsonSerializer() {
		final GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		builder.setPrettyPrinting();
		this.gson = builder.create();
	}

	@Override
	public String serialize(Object obj) {
		Map<String, Object> map = new TreeMap<>();
		map.put("body", obj);
		map.put("status", HttpStatus.OK);
		map.put("code", 200);
		map.put("message", "success");
		return this.gson.toJson(map);
	}

	@Override
	public String serialize(Object object, Action action) {
		Map<String, Object> map = new TreeMap<>();
		Map<String, Object> actionMap = new LinkedHashMap<>();
		actionMap.put("id", object);
		actionMap.put("success", true);
		actionMap.put("command", action);
		map.put("body", actionMap);
		map.put("status", HttpStatus.OK);
		map.put("code", 200);
		map.put("message", "success");
		return this.gson.toJson(map);
	}

	@Override
	public String serialize(Object object, int totalCount, boolean hasNext) {
		Map<String, Object> map = new TreeMap<>();
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("datums", object);
		body.put("totalCount", totalCount);
		body.put("hasNext", hasNext);
		map.put("body", body);
		map.put("status", HttpStatus.OK);
		map.put("code", 200);
		map.put("message", "success");
		return this.gson.toJson(map);
	}

}
