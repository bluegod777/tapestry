package com.tapestry.models.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tapestry.models.user.enums.Role;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails
{
	boolean authenticated;
	String recordId;
	List<Role> roles;
	String token;

	@JsonProperty("userName")
	String username;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
	}

	@Override
	public String getPassword()
	{
		return "";
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return this.authenticated;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}
}
