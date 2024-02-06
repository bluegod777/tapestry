package com.tapestry.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tapestry.components.Container;
import com.tapestry.services.user.UserService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

public class AppLayout extends VerticalLayout implements RouterLayout
{

	Logger logger = LoggerFactory.getLogger(AppLayout.class);

	public AppLayout(@Autowired final UserService userService)
	{
		Container container = new Container();
		container.add(new AppNavbar(userService));

		this.add(container);
	}
}
