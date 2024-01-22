package com.tapestry.services.user.types;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data @Builder @Jacksonized
public class ValidateOtpRequest
{
	String otpPassword;

}
