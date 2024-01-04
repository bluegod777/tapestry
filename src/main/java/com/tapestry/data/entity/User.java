package com.tapestry.data.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User extends AbstractEntity
{

	@NotNull
	private String gid;

	@NotNull
	private String username;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String email;

	@NotNull
	private String mobilePhoneNumber;

	@JsonIgnore
	private String hashedPassword;

	private Set<UserRole> roles;

	private byte[] profilePicture;

	// -------------------------------------------------------------------
	// Our stuff
	// -------------------------------------------------------------------
}
