package com.tapestry.services.entity;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tapestry.services.user.UserService;
import com.tapestry.views.auth.LoginEntity;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.internal.CurrentInstance;

@SpringBootTest
public class EntityServiceTest {

	@Autowired
	EntityService entityService;
	
	@Autowired
	UserService userService;
	
	//	@Test
	// Does not work -- there is no Auth Context
	public void testGetEntity() throws InterruptedException
	{
		String username = "7758309851";
		
		CurrentInstance.set(UI.class, new UI());
		
		{
			final CountDownLatch latch = new CountDownLatch(1);
			
			final LoginEntity loginEntity = new LoginEntity();
			loginEntity.setPhone(username);
			loginEntity.setPassword("abcdefg");

			this.userService.login(loginEntity, (error, user) ->
			{
				Assertions.assertFalse(error);
				Assertions.assertTrue(user.isAuthenticated());
				latch.countDown();
				
				System.out.println("XXX We are authenticated");
			});

			final boolean success = latch.await(30, TimeUnit.MINUTES);
			Assertions.assertTrue(success);
		}
		{
			final CountDownLatch latch = new CountDownLatch(1);

			this.entityService.getEntity(username, (error, entity) ->
			{
				System.out.println("XXX error: " + error);
				Assertions.assertFalse(error);
				Assertions.assertTrue(!entity.getEmailAddress().isEmpty());
				latch.countDown();
			});
		
			final boolean success = latch.await(30, TimeUnit.MINUTES);
			Assertions.assertTrue(success);
		}
	}

}
