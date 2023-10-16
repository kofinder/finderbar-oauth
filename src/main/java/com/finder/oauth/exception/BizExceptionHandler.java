package com.finder.oauth.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.finder.oauth.exception.BizApiException;
import com.finder.oauth.utils.JsonUtil;

@RestController
@ControllerAdvice
public class BizExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ BizApiException.class, AuthenticationException.class })
	public ResponseEntity<?> handleThrowableBusinessException(final BizApiException ex) {
		return new ResponseEntity<Object>(json(ex), ex.gethttpStatus());
	}

	private String json(BizApiException ex) {
		final Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("code", ex.getCode());
		result.put("status", ex.gethttpStatus());
		result.put("message", "Please Contact to Administrator!");
		result.put("developerMessage", ex.getErrorBody());
		return JsonUtil.toJSON(result);
	}

}