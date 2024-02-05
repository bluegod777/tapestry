package com.tapestry.services;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public abstract class ServiceSkeleton
{
	private final ExecutorService executor;
	private final Logger logger;

	protected ServiceSkeleton(final Class<?> clazz)
	{
		this.logger = LoggerFactory.getLogger(clazz);
		this.executor = Executors.newCachedThreadPool();
	}
	
	protected void info(String format, Object... args)
	{
		this.logger.info(String.format(format, args));
	}

	protected void info(String format, Object arg1)
	{
		this.logger.info(String.format(format, arg1));
	}

	protected void info(String format, Object arg1, Object arg2)
	{
		this.logger.info(String.format(format, arg1, arg2));
	}

	protected void debug(String format, Object... args)
	{
		this.logger.debug(format, args);
	}

	protected void warn(String format, Object... args)
	{
		this.logger.warn(format, args);
	}

	protected void error(String format, Object... args)
	{
		this.logger.error(format, args);
	}

	protected <V> void execute(final Callable<ResponseEntity<V>> callable, final ServiceCallBack<V> callBack)
	{
		this.executor.execute(() ->
		{
			try
			{
				final ResponseEntity<V> result = callable.call();
				callBack.onResponse(result.getStatusCode().isError(), result.getBody());
			}
			catch (final Exception e)
			{
				this.logger.warn("Error executing task", e);
				callBack.onResponse(true, null);
			}
		});
	}

	protected Logger getLogger()
	{
		return this.logger;
	}
}
