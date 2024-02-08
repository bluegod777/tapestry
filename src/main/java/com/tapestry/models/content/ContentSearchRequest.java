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
public class ContentSearchRequest {

	private Integer blockSize;

	private String category;

	private Long id;

	private Integer maxAge;

	private Integer minAge;

	private String weight;
	
}
