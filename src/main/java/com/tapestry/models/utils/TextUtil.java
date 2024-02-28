package com.tapestry.models.utils;

import java.io.UnsupportedEncodingException;

public final class TextUtil
{

	public static String toString(final byte[] buffer)
	{
		try
		{
			return new String(buffer, "UTF-8");
		}
		catch (final UnsupportedEncodingException e)
		{
			return new String(buffer);
		}
	}

	private TextUtil()
	{
		// empty
	}

}
