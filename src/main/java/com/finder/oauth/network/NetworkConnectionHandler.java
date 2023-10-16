package com.finder.oauth.network;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author - jameslwin
 * @createdAt - Dec 8, 2019
 */

@Component
public class NetworkConnectionHandler {

	private RestTemplate rest;
	private HttpHeaders headers;
	private HttpStatus status;
	private int statusCode;
	@Value(value = "${mw.connectTimeout:0}")
	private int connectTimeout;
	@Value(value = "${mw.readTimeout:0}")
	private int readTimeout;

	public NetworkConnectionHandler() {
		this.rest = new RestTemplate();
		this.headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		rest.getMessageConverters().add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(connectTimeout);
		clientHttpRequestFactory.setReadTimeout(readTimeout);
		rest.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory));

	}

	public String get(String uri, String json) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
		this.setStatusCode(responseEntity.getStatusCodeValue());
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}