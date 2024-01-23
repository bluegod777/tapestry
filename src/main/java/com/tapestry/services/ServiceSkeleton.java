package com.tapestry.services;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

public abstract class ServiceSkeleton
{
	private final ExecutorService executor;
	private final Logger logger;

	protected ServiceSkeleton(final Logger logger)
	{
		this.logger = logger;
		this.executor = Executors.newCachedThreadPool();
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
