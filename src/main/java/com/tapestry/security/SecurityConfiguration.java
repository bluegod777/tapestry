package com.tapestry.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tapestry.views.login.LoginSignupView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity
{

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		// return new BCryptPasswordEncoder();

		return NoOpPasswordEncoder.getInstance();
	}

	@SuppressWarnings("removal")
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		// http.authorizeHttpRequests(authz -> authz.requestMatchers("/images/*.png").permitAll().requestMatchers("/line-awesome/**/*.svg").permitAll().anyRequest().authenticated());

		http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/images/*.png")).permitAll();

		// Icons from the line-awesome addon
		http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/line-awesome/**/*.svg")).permitAll();

		super.configure(http);

		this.setLoginView(http, LoginSignupView.class);
	}

}