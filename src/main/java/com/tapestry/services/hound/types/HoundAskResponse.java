package com.tapestry.services.hound.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tapestry.models.chat.HoundChatMessage;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class HoundAskResponse
{
	HoundChatMessage chatMessage;
	String chatRecordId;
	Boolean complete;
}
