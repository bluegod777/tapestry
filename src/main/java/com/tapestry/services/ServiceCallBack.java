package com.tapestry.services;

import jakarta.annotation.Nullable;

@FunctionalInterface
public interface ServiceCallBack<T>
{
	void onResponse(boolean error, @Nullable T value);
}
