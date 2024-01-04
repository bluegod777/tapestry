package com.tapestry.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public final class CalendarUtils
{
	public static OffsetDateTime now()
	{
		return OffsetDateTime.now();
	}

	public static OffsetDateTime daysFromNow(final int days)
	{
		return OffsetDateTime.now().plusDays(days);
	}

	public static String format(final OffsetDateTime datetime)
	{
		return CalendarUtils.format(datetime, "yyyy-MM-dd HH:mm:ssXXX");
	}

	public static String formatDateOnly(final OffsetDateTime datetime)
	{
		return CalendarUtils.format(datetime, "MM-dd-yyyy");
	}

	public static String format(final OffsetDateTime datetime, final String format)
	{
		// Define a DateTimeFormatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

		// Format the OffsetDateTime to a string
		String formattedDateTime = datetime.format(formatter);

		// Print the formatted string
		return formattedDateTime;
	}
}
