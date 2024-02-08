package com.tapestry.models.content;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
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
public class ContentSearchResponse {
	
	
	@NotEmpty
	private List<Content> content;
	
	private Long id;

	
}
