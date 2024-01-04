package com.tapestry.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class TapestryUtils
{
	public static String formatCurrency(final BigDecimal value)
	{
		NumberFormat numberFormat = NumberFormat.getInstance();

		numberFormat.setMaximumFractionDigits(2); // Set maximum number of decimal places

		String formattedValue = numberFormat.format(value);

		return formattedValue;
	}

	public static String formatCurrencyForDisplay(final BigDecimal value)
	{
		try
		{
			if (value != null)
			{
				BigDecimal absValue = value.abs();

				return (value.compareTo(BigDecimal.ZERO) > 0 ? "+" : "-") + "$" + TapestryUtils.formatCurrency(absValue);
			}

			return "(null)";
		}

		catch (Exception e)
		{
			e.printStackTrace();

			return "(error)";
		}
	}

	public static String formatSimpleCurrencyForDisplay(final BigDecimal value)
	{
		try
		{
			if (value != null)
			{
				BigDecimal absValue = value.abs();

				return "$" + TapestryUtils.formatCurrency(absValue);
			}

			return "(null)";
		}

		catch (Exception e)
		{
			e.printStackTrace();

			return "(error)";
		}
	}

}
