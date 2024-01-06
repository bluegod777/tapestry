package com.tapestry.data.entity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
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

	@NotNull
	private OffsetDateTime birthdate;

	@JsonIgnore
	private String hashedPassword;

	private Set<UserRole> roles;

	private byte[] profilePicture;

	@Builder.Default
	private List<User> children = new ArrayList<>();;

	// -------------------------------------------------------------------
	// Our stuff
	// -------------------------------------------------------------------
	public String getFullName()
	{
		return String.format("%s %s", this.getFirstName(), this.getLastName());

	}
}
