package com.finder.oauth.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author - jameslwin
 * @createdAt - 2020/20/03
 *
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface BizURI {
	@AliasFor(annotation = RequestMapping.class, attribute = "path")
	String apiPath();
}
