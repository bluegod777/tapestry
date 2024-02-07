package com.tapestry.services.user;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tapestry.services.user.UserService;
import com.tapestry.views.auth.LoginEntity;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.internal.CurrentInstance;

@SpringBootTest
public class UserServiceTest
{
	@Autowired
	UserService service;

	@Test
	public void login() throws InterruptedException
	{
		CurrentInstance.set(UI.class, new UI());

		final CountDownLatch latch = new CountDownLatch(1);

		final LoginEntity loginEntity = new LoginEntity();
		loginEntity.setPhone("7758309851");
		loginEntity.setPassword("abcdefg");

		this.service.login(loginEntity, (error, user) ->
		{
			Assertions.assertFalse(error);
			Assertions.assertTrue(user.isAuthenticated());
			latch.countDown();
		});

		final boolean success = latch.await(30, TimeUnit.MINUTES);
		Assertions.assertTrue(success);
	}
}
