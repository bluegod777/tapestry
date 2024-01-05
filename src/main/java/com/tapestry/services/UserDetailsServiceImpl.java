package com.tapestry.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService
{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		UserDetailsServiceImpl.log.info("UserDetailsServiceImpl::loadUserByUsername - user is : {}", username);

		// Load user details from your data source (e.g., database)
		// For simplicity, a hard-coded user is used here
		return User.withUsername("user").password("user") // {noop} for plain text (don't use in production)
				.roles("USER", "ADMIN").build();
	}
}