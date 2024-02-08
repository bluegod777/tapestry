package com.tapestry.models.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

// Note: this maps to the MothEntityEAO object

/**
 * TODO: define what is an entity, in relation to a parent and child, are both
 * entities?
 * 
 * Creating a user, creates an entity.
 */
@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Entity
{

	private String address;

	@NotEmpty
	private LocalDate birthday;

	@NotEmpty
	private String emailAddress;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	private String mobileNumber;

	private String recordId;

	private String state;

	private String type;
}
