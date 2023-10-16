package com.finder.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.finder.oauth.common.SecurityConstants;
import com.finder.oauth.handler.CustomAccessDeniedHandler;
import com.finder.oauth.handler.UnauthorizedEntryPoint;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/10/30
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter implements SecurityConstants {
	private final TokenStore tokenStore;
	private final UnauthorizedEntryPoint unAuthorizeEntryPoint;
	private final CustomAccessDeniedHandler accessDeniedHanlder;

	@Autowired
	public ResourceServerConfiguration(final TokenStore tokenStore, final UnauthorizedEntryPoint unAuthorizeEntryPoint,
			final CustomAccessDeniedHandler accessDeniedHanlder) {
		this.tokenStore = tokenStore;
		this.unAuthorizeEntryPoint = unAuthorizeEntryPoint;
		this.accessDeniedHanlder = accessDeniedHanlder;
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore).resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/webjars/**").permitAll()
				.antMatchers(HttpMethod.GET, SECURED_API_ENDPOINT).access(SECURED_READ_SCOPE)
				.antMatchers(HttpMethod.POST, SECURED_API_ENDPOINT).access(SECURED_WRITE_SCOPE)
				.antMatchers(HttpMethod.PATCH, SECURED_API_ENDPOINT).access(SECURED_WRITE_SCOPE)
				.antMatchers(HttpMethod.PUT, SECURED_API_ENDPOINT).access(SECURED_WRITE_SCOPE)
				.antMatchers(HttpMethod.DELETE, SECURED_API_ENDPOINT).access(SECURED_TRUST_SCOPE).anyRequest()
				.authenticated().and().exceptionHandling().authenticationEntryPoint(unAuthorizeEntryPoint)
				.accessDeniedHandler(accessDeniedHanlder);
	}

}
