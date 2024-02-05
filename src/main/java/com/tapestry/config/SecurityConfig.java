package com.tapestry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tapestry.views.auth.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

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