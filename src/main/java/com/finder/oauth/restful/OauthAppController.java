package com.finder.oauth.restful;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.oauth.annotation.BizURI;
import com.finder.oauth.common.BizApiConst;
import com.finder.oauth.data.AuthClientData;
import com.finder.oauth.dto.OAuthDTO;
import com.finder.oauth.exception.BizApiException;
import com.finder.oauth.exception.ExceptionType;
import com.finder.oauth.serialization.ToApiJsonSerializer;
import com.finder.oauth.service.BizLogger;
import com.finder.oauth.service.OauthReadPlatformService;
import com.finder.oauth.service.OauthWritePlatformService;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/10/30
 */
@BizURI(apiPath = "/api/v1/")
public class OauthAppController implements BizApiConst {
	private final ToApiJsonSerializer toJsonSerializer;
	private final OauthReadPlatformService oauthReadPlatformService;
	private final OauthWritePlatformService oauthWritePlatformService;
	private final BizLogger logger;

	@Autowired
	public OauthAppController(final BizLogger logger, final OauthReadPlatformService oauthReadPlatformService,
			final OauthWritePlatformService oauthWritePlatformService, final ToApiJsonSerializer toJsonSerializer) {
		this.logger = logger;
		this.oauthWritePlatformService = oauthWritePlatformService;
		this.oauthReadPlatformService = oauthReadPlatformService;
		this.toJsonSerializer = toJsonSerializer;
	}

	@GetMapping(RESOURCE_WITH_ID)
	public String getOauthApp(@PathVariable("id") Long id) throws Exception {
		try {
			final AuthClientData user = this.oauthReadPlatformService.retrive(id);
			return this.toJsonSerializer.serialize(user);
		} catch (Exception ex) {
			this.logger.logError(ERROR_RESOURCE_LOGGER, ex.getMessage(), true);
			throw new BizApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionType.COMMON_EXCEPTION);
		}
	}

	@PostMapping(RESOURCE)
	public String saveOauthApp(@RequestBody OAuthDTO dto) throws Exception {
		try {
			final Long ret = this.oauthWritePlatformService.save(dto);
			return this.toJsonSerializer.serialize(ret);
		} catch (Exception ex) {
			this.logger.logError(ERROR_RESOURCE_LOGGER, ex.getMessage(), true);
			throw new BizApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionType.COMMON_EXCEPTION);
		}
	}
	
	@PutMapping(RESOURCE_WITH_ID)
	public String updateOauthApp(@PathVariable("id") Long id, @RequestBody OAuthDTO dto) throws Exception {
		try {
			final Long ret = this.oauthWritePlatformService.update(id, dto);
			return this.toJsonSerializer.serialize(ret);
		} catch (Exception ex) {
			this.logger.logError(ERROR_RESOURCE_LOGGER, ex.getMessage(), true);
			throw new BizApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionType.COMMON_EXCEPTION);
		}
	}
	
	@GetMapping(SEARCH_RESOURCE)
	public String searchResources(@RequestParam(value = "keyword", required = false) String keyword, 
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "offset", defaultValue = "0") Integer offset) {
		try {
			final Collection<AuthClientData> resources = this.oauthReadPlatformService.retriveAll(keyword, limit, offset);
			return this.toJsonSerializer.serialize(resources, 100, true);
		} catch (Exception ex) {
			this.logger.logError(ERROR_RESOURCE_LOGGER, ex.getMessage(), true);
			throw new BizApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionType.COMMON_EXCEPTION);
		}
	}

}
