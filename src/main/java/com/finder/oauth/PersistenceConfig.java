package com.finder.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.finder.oauth.domain.AuditorAwareImpl;
import com.finder.oauth.domain.User;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/10/30
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PersistenceConfig {
	@Bean
    public AuditorAware<User> auditorAware(){
        return new AuditorAwareImpl();
    }
}
