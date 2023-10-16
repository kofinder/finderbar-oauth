package com.finder.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final TokenStore tokenStore;
	private final JdbcTemplate jdbcTemplate;
	private final JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired
	public AuthorizationServerConfiguration(final UserDetailsService userDetailsService, final TokenStore tokenStore,
			final PasswordEncoder passwordEncoder, final AuthenticationManager authenticationManager,
			final JdbcTemplate jdbcTemplate, final JwtAccessTokenConverter jwtAccessTokenConverter) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jdbcTemplate = jdbcTemplate;
		this.tokenStore = tokenStore;
		this.jwtAccessTokenConverter = jwtAccessTokenConverter;
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer.jdbc(jdbcTemplate.getDataSource());
	}

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.passwordEncoder(this.passwordEncoder).tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(this.tokenStore)
				//.tokenEnhancer(tokenEnhancer())
				.accessTokenConverter(this.jwtAccessTokenConverter)
				//.authorizationCodeServices()
				.authenticationManager(this.authenticationManager)
				.userDetailsService(this.userDetailsService);
	}
	
//	@Bean
//	public TokenEnhancer tokenEnhancer() {
//	   return new CustomTokenEnhancer();
//	}

}
