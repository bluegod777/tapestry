package com.tapestry.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public abstract class ClientSkeleton
{

	Logger logger;

	protected ClientSkeleton(Class<?> clazz)
	{
		logger = LoggerFactory.getLogger(clazz);
	}

	protected final void logIt(final String title, final ResponseEntity<?> response)
	{
		if (response.getStatusCode().isError())
		{
			this.logger.warn("Error calling  {}", title);
			this.logger.warn("   Status Code {}", response.getStatusCode());
		}
	}

	protected final void info(String format, Object... args)
	{
		this.logger.info(format, args);
	}

	protected final void warn(String format, Object... args)
	{
		this.logger.warn(format, args);
	}

	protected final void error(String format, Object... args)
	{
		this.logger.error(format, args);
	}

	protected final void debug(String format, Object... args)
	{
		this.logger.debug(format, args);
	}

	protected final void logException(final String title, final Exception e)
	{
		this.logger.warn("Error calling {} : {}", title, e.getMessage());
	}

}
