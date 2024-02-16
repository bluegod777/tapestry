package com.tapestry.services.user.types;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class CreateUserRequest
{
	String password;
	String emailAddress;
	String firstName;
	String lastName;
	String mobileNumber;
}
