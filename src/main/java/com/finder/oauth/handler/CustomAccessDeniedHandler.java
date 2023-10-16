package com.finder.oauth.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/11/20
 */
@Component
public class CustomAccessDeniedHandler extends OAuth2AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
			throws IOException, ServletException {
		
		//super.handle(request, response, ex);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		String payload = auth.getName() + " was trying to access protected resource";
		String message = request.getContextPath() + "access-denied";

		final Map<String, Object> body = new HashMap<>();
		body.put("code", HttpServletResponse.SC_FORBIDDEN);
		body.put("payload", payload);
		body.put("message", message);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}
}
