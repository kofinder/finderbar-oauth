package com.finder.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/10/30
 */
@SpringBootApplication
@EntityScan("com.finder.oauth.domain")
@EnableJpaRepositories("com.finder.oauth.repository")
@ComponentScan(basePackages = { "com.finder.oauth" })
@EnableAutoConfiguration
@JsonAutoDetect
public class OauthApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OauthApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}
	
}
