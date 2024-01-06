package com.tapestry.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

	public static OffsetDateTime getInstance(final int year, final int month, final int day)
	{
		// Specify the date, time, and time zone offset
		LocalDate date = LocalDate.of(year, month, day);
		LocalTime time = LocalTime.of(0, 0); // Midnight
		ZoneOffset offset = ZoneOffset.UTC; // or any other offset you desire

		// Create OffsetDateTime
		OffsetDateTime offsetDateTime = OffsetDateTime.of(date, time, offset);

		return offsetDateTime;
	}
}
