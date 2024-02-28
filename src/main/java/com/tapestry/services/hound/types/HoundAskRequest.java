package com.tapestry.services.hound.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class HoundAskRequest
{
	Boolean async;
	String chatRecordId;
	String timeout;
}
