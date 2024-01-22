package com.tapestry.models.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tapestry.models.user.enums.Role;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class User
{
	boolean authenticated;
	String firstName;
	String lastName;
	String recordId;
	List<Role> roles;
	String token;
	String userName;
}
