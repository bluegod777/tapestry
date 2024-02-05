package com.tapestry.models.entity;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

// Note: this maps to the MothEntityEAO object

@Data @Builder @Jacksonized @JsonIgnoreProperties(ignoreUnknown = true) @Getter @Setter
public class Entity
{

	@NotEmpty
	private String address;

	@NotEmpty
	private Calendar birthday;

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