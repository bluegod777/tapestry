package com.tapestry.utils;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DumpUtils
{
	// -------------------------------------------------------------------
	// Standard Dump Util
	// -------------------------------------------------------------------
	public static void dump(final int indent, final List<?> source, final String title)
	{
		DumpUtils.infof(DumpUtils.indent(indent) + "%s [List<?>]", title);

		source.forEach(o ->
		{
			DumpUtils.log.info(DumpUtils.indent(indent) + "> item [%d][%s]", source.indexOf(o), o.getClass().getSimpleName());

			DumpUtils.dumpClass(indent + 1, o);
		});
	}

	public static void dump(final int indent, final Object source)
	{
		DumpUtils.infof("DumpUtil::dump : [%s]", source.getClass().getSimpleName());
		DumpUtils.dumpClass(indent, source);

	}

	public static void dump(final Object source)
	{
		DumpUtils.infof("DumpUtil::dump : [%s]", source.getClass().getSimpleName());

		DumpUtils.dumpClass(0, source);

	}

	private static void dumpClass(final int indent, final Object source)
	{

		final List<Field> fields = new ArrayList<>();

		// -------------------------------------------------------------------
		// Do the super class first
		// -------------------------------------------------------------------
		if (source.getClass().getSuperclass() != null)
		{
			Collections.addAll(fields, source.getClass().getSuperclass().getDeclaredFields());
		}

		// -------------------------------------------------------------------
		// Do the normal class
		// -------------------------------------------------------------------
		Collections.addAll(fields, source.getClass().getDeclaredFields());

		// -------------------------------------------------------------------
		// Dump the current level fields
		// -------------------------------------------------------------------
		for (final Field f : fields)
		{
			try
			{
				f.setAccessible(true);

				final var value = f.get(source);

				if (value != null)
				{
					if (value instanceof Calendar)
					{
						DumpUtils.infof(DumpUtils.indent(indent) + "> %s : %s", f.getName(),
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(((Calendar) value).toInstant()));
					} else if (value instanceof List)
					{
						// Skip for the moment, done down below
					} else
					{
						try
						{
							DumpUtils.infof(DumpUtils.indent(indent) + "> %s : %s", f.getName(), value.toString());
						} catch (final Exception e)
						{
							DumpUtils.errorf(DumpUtils.indent(indent) + "> error dumping field : [%s][%s]", f.getName(),
									e.getMessage());
						}
					}
				} else
				{
					DumpUtils.infof(DumpUtils.indent(indent) + "> %s : <null>", f.getName());
				}
			} catch (final IllegalArgumentException | IllegalAccessException e)
			{
				System.out.println(f.getName());
				e.printStackTrace();
			}
		}

		// -------------------------------------------------------------------
		// Now dump thnigs likes lists
		// -------------------------------------------------------------------
		for (final Field f : source.getClass().getDeclaredFields())
		{
			try
			{
				f.setAccessible(true);

				final var value = f.get(source);

				if (value != null)
				{
					if (value instanceof List)
					{
						DumpUtils.dump(indent + 1, (List<?>) value, f.getName());
					}
				} else
				{
					DumpUtils.infof(DumpUtils.indent(indent) + "> %s : <null>", f.getName());
				}
			} catch (final IllegalArgumentException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void infof(final String format, final Object... args)
	{
		DumpUtils.log.info(String.format(format, args));
	}

	public static void errorf(final String format, final Object... args)
	{
		DumpUtils.log.error(String.format(format, args));
	}

	public static String indent(final int level)
	{
		final StringBuilder indentString = new StringBuilder();

		for (int ii = 0; ii < level; ii++)
		{
			indentString.append("    ");
		}

		return indentString.toString();
	}

}
