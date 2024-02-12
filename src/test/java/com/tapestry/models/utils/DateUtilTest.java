package com.tapestry.models.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

public class DateUtilTest {

	@Test
	public void testToXml()
	{
		ZonedDateTime now = ZonedDateTime.now();
		String x = DateUtil.toXML(now);
		System.out.println("XXX "+ x);
	}
}
