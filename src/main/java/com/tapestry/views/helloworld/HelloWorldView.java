package com.tapestry.views.helloworld;

import com.tapestry.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class HelloWorldView extends HorizontalLayout
{

	private TextField name;
	private Button sayHello;

	public HelloWorldView()
	{
		this.name = new TextField("Your name");
		this.sayHello = new Button("Say hello");
		this.sayHello.addClickListener(e ->
		{
			Notification.show("Hello " + this.name.getValue());
		});
		this.sayHello.addClickShortcut(Key.ENTER);

		this.setMargin(true);
		this.setVerticalComponentAlignment(Alignment.END, this.name, this.sayHello);

		this.add(this.name, this.sayHello);
	}

}
