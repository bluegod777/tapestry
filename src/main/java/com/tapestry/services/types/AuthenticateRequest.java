package com.tapestry.services.types;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class AuthenticateRequest
{
	String password;
	String userName;
}
