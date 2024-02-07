package com.tapestry.models.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Data 
@Builder 
@Jacksonized 
@JsonIgnoreProperties(ignoreUnknown = true) 
@Getter 
@Setter
public class Content {


	private String category;

	private byte[] content;

	private String contentType;

	private String contentURI;

	private int maxAge;

	private int minAge;

	private String recordId;

	private String summary;

	private String title;
	
}
