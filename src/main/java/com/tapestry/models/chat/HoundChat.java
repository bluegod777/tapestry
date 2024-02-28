package com.tapestry.models.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class HoundChat
{
	String owner;
	String recordId;
	String systemMessage;
	String topicRecordId;

}
