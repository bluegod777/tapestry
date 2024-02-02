package com.tapestry.models.entity;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


// Note: this maps to the MothEntityEAO object

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {

	private String address;

	private Calendar birthday;

	private String emailAddress;

	private String firstName;

	private String lastName;

	private String mobileNumber;

	private String recordId;

	private String state;

	private String type;
	
}
