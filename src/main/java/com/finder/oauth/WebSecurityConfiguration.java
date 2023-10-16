package com.finder.oauth;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.finder.oauth.common.SecurityConstants;
import com.finder.oauth.utils.SecurityProperties;


/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/11/19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements SecurityConstants{

	private final DataSource dataSource;
	private final SecurityProperties securityProperties;
	private UserDetailsService userDetailsService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public WebSecurityConfiguration(final DataSource dataSource, final SecurityProperties securityProperties) {
		this.dataSource = dataSource;
		this.securityProperties = securityProperties;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JdbcClientDetailsService jdbcClientDetailsService() {
		return new JdbcClientDetailsService(this.dataSource);
	}

	@Bean
	public DefaultAuthenticationEventPublisher authenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}

	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(jdbcTemplate.getDataSource());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		SecurityProperties.JwtProperties jwtProperties = securityProperties.getJwt();
		KeyPair keyPair = keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setKeyPair(keyPair);
		return jwtAccessTokenConverter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		if (userDetailsService == null) {
			userDetailsService = new JdbcDaoImpl();
			((JdbcDaoImpl) userDetailsService).setDataSource(dataSource);
		}
		return userDetailsService;

	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationEventPublisher(authenticationEventPublisher()).userDetailsService(userDetailsService())
				.passwordEncoder(passEncoder());
	}
	
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/resources/**", "/login", "/logout").antMatchers(HttpMethod.OPTIONS, "/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers(SECURED_API_ENDPOINT).fullyAuthenticated()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/oauth/token").permitAll()
				.antMatchers("/api-docs/**").permitAll()
				.anyRequest()
				.authenticated().and().anonymous().disable();
	}

	private KeyPair keyPair(SecurityProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
		return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias(),
				jwtProperties.getKeyPairPassword().toCharArray());
	}

	private KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.JwtProperties jwtProperties) {
		return new KeyStoreKeyFactory(jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
	}

}
