package com.tapestry.models.entity;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class EntityTest
{
	@Autowired
	ObjectMapper mapper;

	@Test
	public void testEntity() throws Exception
	{
		final Entity obj = Entity.builder().birthday(LocalDate.now()).build();
		final byte[] bytes = this.mapper.writeValueAsBytes(obj);
		final String s = new String(bytes, "UTF-8");
		System.out.println(s);
	}
}
