package com.tapestry.models.entity.relationships;

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
public class Relationship {

	@NotEmpty
	private String aEntityRecordId;

	@NotEmpty
	private String bEntityRecordId;

	private String recordId;

	private RelationshipRecordState state;

	@NotEmpty
	private RelationshipType type;
	
}
