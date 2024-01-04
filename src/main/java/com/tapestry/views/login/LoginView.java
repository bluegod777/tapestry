package com.tapestry.views.login;

import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.common.builders.VerticalLayoutBuilder;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

//@AnonymousAllowed
//@PageTitle("Login")
//@Route(value = "login")
public class LoginView extends LoginOverlay // implements BeforeEnterObserver
{

	@SuppressWarnings("unused")
	private final AuthenticatedUser authenticatedUser;

	public LoginView(AuthenticatedUser authenticatedUser)
	{
		this.authenticatedUser = authenticatedUser;
		// this.setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), this.getClass()));

		LoginI18n i18n = LoginI18n.createDefault();
		i18n.setHeader(new LoginI18n.Header());
		i18n.getHeader().setTitle("Tapestry");
		i18n.getHeader().setDescription("If you don't have an account you can use the Sign Up button above, or login below.");

		this.setTitle(new TapestryLogo());

		i18n.setAdditionalInformation(null);
		this.setI18n(i18n);

		this.setForgotPasswordButtonVisible(true);
		this.setOpened(true);
	}

//	@Override
//	public void beforeEnter(BeforeEnterEvent event)
//	{
//		if (this.authenticatedUser.get().isPresent())
//		{
//			// Already logged in
//			this.setOpened(false);
//
//			event.forwardTo("");
//		}
//
//		this.setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
//	}

	// -------------------------------------------------------------------
	// Our performers
	// -------------------------------------------------------------------
	protected void performSignup(final ClickEvent<Button> e)
	{
		System.out.println("We are here!");
	}

	private class TapestryLogo extends VerticalLayout
	{
		// -------------------------------------------------------------------
		// There is some kind of "immutable" issue here
		//
		// -------------------------------------------------------------------
		public TapestryLogo()
		{
			this.setWidth("100%");

			// Text title = new Text("Welcome To");
			// title.getStyle().set("font-size", "var(--lumo-font-size-xl)");

			// Image logo = new Image();
			// logo.setSrc("https://webdav-001.ayt360.org/repository/public/tapestry.com/Tapestry-script-logo-white.png");

			final var logo = VerticalLayoutBuilder.create().addClass("consumer-tapestry-name-white-as-background").build();

			// Button signup = ButtonBuilder.create().large().alignCenter().primary().text("Sign Up").listener(e -> LoginView.this.performSignup(e)).build();

			this.add(logo);
			// this.add(HtmlLabelBuilder.create().text("If you don't have an account you can use the Sign Up button, or login below.").build());
			// this.add(signup);
		}
	}
}
