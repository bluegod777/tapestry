/* Copyright 2011, The Anonymously Your Foundation */
package com.tapestry.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author omey
 */
public class ValidateUtils
{

	public static void main(String[] args)
	{
		System.out.println(ValidateUtils.validateSocialSecurityNumber("123-45-6789"));
	}

	public static void isPositive(final int value)
	{
		if (value <= 0)
		{
			throw new RuntimeException(String.format("Value %s should be positive", Integer.valueOf(value)));
		}
	}

	public static void isTrue(final boolean condition)
	{
		ValidateUtils.isTrue(condition, "The condition is supposed to be true");
	}

	public static void isTrue(final boolean condition, final String format, final Object... args)
	{
		if (condition)
		{
			return;
		}

		throw new RuntimeException(String.format(format, args));
	}

	public static <T> T notNull(final T obj)
	{
		return ValidateUtils.notNull(obj, "The object is supposed to be non-null");
	}

	public static <T> T notNull(final T obj, final String format, final Object... args)
	{
		if (obj != null)
		{
			return obj;
		}

		throw new RuntimeException(String.format(format, args));
	}

	public static void neverUsed(final Object object)
	{
		if (object != null)
		{
			ValidateUtils.notNull(object);
		}
	}

	public static void neverUsed(final Object... objects)
	{
		if (objects != null)
		{
			ValidateUtils.notNull(objects);
		}
	}

	public static boolean isEmpty(final List<?> list)
	{
		if (list == null)
		{
			return true;
		}

		return list.isEmpty();
	}

	public static boolean isNotNull(final Object obj)
	{
		return obj != null;
	}

	public static boolean isNull(final Object obj)
	{
		return obj == null;
	}

	public static boolean isNullOrEmpty(final String s)
	{
		if (s == null)
		{
			return true;
		}

		if (s.isEmpty())
		{
			return true;
		}

		return false;
	}

	public static boolean isNotNullOrEmpty(final String s)
	{
		if (s == null)
		{
			return false;
		}

		if (s.isEmpty())
		{
			return false;
		}

		return true;
	}

	// -------------------------------------------------------------------
	// Phone Support
	// -------------------------------------------------------------------
	private static final String MOBILE_NUMBER_REGEX = "^(\\+\\d{1,3})?\\d{10}$";

	public static boolean validatePhoneNumber(final String mobileNumber)
	{
		Pattern pattern = Pattern.compile(ValidateUtils.MOBILE_NUMBER_REGEX);

		Matcher matcher = pattern.matcher(mobileNumber);

		return matcher.matches();
	}

	// -------------------------------------------------------------------
	// Email Support
	// -------------------------------------------------------------------
	private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

	public static boolean validateEmailAddress(final String emailAddress)
	{
		Pattern pattern = Pattern.compile(ValidateUtils.EMAIL_REGEX);

		Matcher matcher = pattern.matcher(emailAddress);

		return matcher.matches();
	}

	// -------------------------------------------------------------------
	// Social Security
	// -------------------------------------------------------------------
	private static final String SSN_REGEX = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";

	public static boolean validateSocialSecurityNumber(final String ssn)
	{
		Pattern pattern = Pattern.compile(ValidateUtils.SSN_REGEX);

		Matcher matcher = pattern.matcher(ssn);

		return matcher.matches();
	}

}
