package com.tapestry.models.entity.relationships;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tapestry.models.entity.Entity;

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
public class RelationSearchResult {
	
	private List<Entity> entities;
	
	private List<String> recordids;
	
	public RelationSearchResult(List<Entity> entities, List<String> recordids)
	{
		this.entities = entities;
		this.recordids = recordids;
	}
	
	public RelationSearchResult()
	{
		this.entities = new ArrayList<Entity>();
		this.recordids = new ArrayList<String>();
	}

}
