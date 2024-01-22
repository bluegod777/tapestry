package com.tapestry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tapestry.views.auth.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

// TODO: this is REQUIRED for vaadin to work. But we don't need it, and can't use it with
// this middle man server, which means no @AnonymousAllowed, @RolesAllowed, etc. How to wire
// this up to work with token authentication
// @see https://dzone.com/articles/spring-security-authentication

@EnableWebSecurity @Configuration
public class SecurityConfig extends VaadinWebSecurity
{

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.authorizeHttpRequests(
        auth -> auth.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.*")).permitAll());
    super.configure(http);
    setLoginView(http, LoginView.class);
  }

}