package com.tapestry.data.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tapestry.data.entity.enums.Role;

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
	String name;
	String recordId;
	List<Role> roles;
	String token;
	String userName;
}
