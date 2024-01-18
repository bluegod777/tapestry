package com.tapestry.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tapestry.views.auth.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

// TODO: we'll need to customize this to, not use VaadinWebSecutiry? This isn't designed
// to be used as a token to api authentication, but it's own internal server authentication.
// Begs the question, do we need a public API..
// @see https://dzone.com/articles/spring-security-authentication

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers(
				AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.*")).permitAll());
		super.configure(http);
		setLoginView(http, LoginView.class);
	}

	@Bean
	UserDetailsService users() {
		// 'password'
		final String pw = "{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW";

		UserDetails user = User.builder()
				.username("user")
				.password(pw)
				.roles("USER")
				.build();

		UserDetails admin = User.builder()
				.username("admin")
				.password(pw)
				.roles("USER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}

}