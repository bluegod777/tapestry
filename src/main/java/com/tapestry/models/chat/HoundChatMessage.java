package com.tapestry.models.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tapestry.models.utils.TextUtil;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class HoundChatMessage
{
	String chatRecordId;
	@Builder.Default
	byte[] messageResponse = new byte[0];
	String prompt;
	String recordId;
	@Builder.Default
	byte[] response = new byte[0];

	public String getMessageResponseAsString()
	{
		return TextUtil.toString(this.messageResponse);
	}

	public String getReponseAsString()
	{
		return TextUtil.toString(this.response);
	}
}
