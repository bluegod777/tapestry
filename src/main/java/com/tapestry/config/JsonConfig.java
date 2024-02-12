package com.tapestry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JsonConfig
{

	@Bean
	public ObjectMapper mapper()
	{
		return JsonMapper.builder().addModule(new JavaTimeModule()).build();
	}
}
