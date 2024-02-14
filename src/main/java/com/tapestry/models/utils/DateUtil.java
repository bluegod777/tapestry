package com.tapestry.models.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	
	public static String toXML(ZonedDateTime date)
	{
		return date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
	
}
