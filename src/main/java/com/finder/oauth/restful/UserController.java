package com.finder.oauth.restful;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.oauth.annotation.BizURI;
import com.finder.oauth.common.BizApiConst;
import com.finder.oauth.data.UserData;
import com.finder.oauth.domain.Action;
import com.finder.oauth.dto.UserDTO;
import com.finder.oauth.exception.BizApiException;
import com.finder.oauth.exception.ExceptionType;
import com.finder.oauth.serialization.ToApiJsonSerializer;
import com.finder.oauth.service.BizLogger;
import com.finder.oauth.service.UserReadPlatformService;
import com.finder.oauth.service.UserWritePlatformService;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/11/06
 */
@BizURI(apiPath = "/api/v1/")
public class UserController implements BizApiConst {
	private final ToApiJsonSerializer toJsonSerializer;
	private final UserWritePlatformService userWritePlatformService;
	private final UserReadPlatformService userReadPlatformService;
	private final BizLogger logger;

	@Autowired
	public UserController(final UserWritePlatformService userWritePlatformService,
			final UserReadPlatformService userReadPlatformService, final ToApiJsonSerializer toJsonSerializer,
			final BizLogger logger) {
		this.userWritePlatformService = userWritePlatformService;
		this.userReadPlatformService = userReadPlatformService;
		this.toJsonSerializer = toJsonSerializer;
		this.logger = logger;
	}
	
	@GetMapping(USER_PRINCIPAL)
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

	@PostMapping(USER)
	public String saveUser(@RequestBody UserDTO dto) throws Exception {
		try {
			final Long ret = this.userWritePlatformService.save(dto);
			return this.toJsonSerializer.serialize(ret, Action.INSERTED);
		} catch (Exception ex) {
			this.logger.logError(ERROR_RESOURCE_LOGGER, ex.getMessage(), true);
			throw new BizApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionType.COMMON_EXCEPTION);
		}
	}

	@PutMapping(USER_WITH_ID)
	public String updateUser(@PathVariable("id") Long id, @RequestBody UserDTO dto) throws Exception {
		try {
			final int ret = this.userWritePlatformService.update(id, dto);
			return this.toJsonSerializer.serialize(ret, Action.UPDATED);
		} catch (Exception ex) {
			this.logger.logError(ERROR_RESOURCE_LOGGER, ex.getMessage(), true);
			throw new BizApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionType.COMMON_EXCEPTION);
		}
	}

	@DeleteMapping(USER_WITH_ID)
	public String deleteUser(@PathVariable("id") Long id) {
		try {
			final int ret = this.userWritePlatformService.delete(id);
			return this.toJsonSerializer.serialize(ret, Action.DELETED);
		} catch (Exception ex) {
			this.logger.logError(ERROR_RESOURCE_LOGGER, ex.getMessage(), true);
			throw new BizApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionType.COMMON_EXCEPTION);
		}
	}

	@GetMapping(USER_WITH_ID)
	public String getUser(@PathVariable("id") Long id) throws Exception {
		try {
			UserData user = this.userReadPlatformService.retrive(id);
			return this.toJsonSerializer.serialize(user);
		} catch (Exception ex) {
			this.logger.logError(ERROR_RESOURCE_LOGGER, ex.getMessage(), true);
			throw new BizApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionType.COMMON_EXCEPTION);
		}
	}

	@GetMapping(SEARCH_USER)
	public String searchUser(@RequestParam(value = "keyword", required = false) String keyword, 
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "offset", defaultValue = "0") Integer offset) {
		try {
			final Collection<UserData> users = this.userReadPlatformService.retriveAll(keyword, limit, offset);
			return this.toJsonSerializer.serialize(users, 100, true);
		} catch (Exception ex) {
			this.logger.logError(ERROR_RESOURCE_LOGGER, ex.getMessage(), true);
			throw new BizApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionType.COMMON_EXCEPTION);
		}
	}

}
