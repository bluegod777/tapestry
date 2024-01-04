package com.tapestry.views.login;

import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.common.builders.AnchorBuilder;
import com.tapestry.views.common.builders.ButtonBuilder;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.builders.TapestryBuilder;
import com.tapestry.views.common.views.TapestryRouterViewSkeleton;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login | Tapestry Login")
@AnonymousAllowed
public class LoginSignupView extends TapestryRouterViewSkeleton<LoginSignupView> implements BeforeEnterObserver
{
	// private LoginForm login;
	private LoginView login;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public LoginSignupView(final AuthenticatedUser authenticatedUser, final TapestryRepository repository)
	{
		super(authenticatedUser, repository);

		this.init();
	}

	@Override
	public LoginSignupView init()
	{
		this.addClassName("home-view");
		this.setSpacing(true);
		this.setSizeFull();

		this.add(TapestryBuilder.getTapestryLogo());
		this.add(HtmlLabelBuilder.create().className("consumer-tag-line").text("Literacy through action").build());
		// this.add(this.buildProxyConsumerCard());

		this.add(ButtonBuilder.create().large().primary().alignCenter().text("Get Signed Up").listener(this::performApplyNow).build());
		this.add(ButtonBuilder.create().large().alignCenter().text("Login").listener(this::peformLogin).build());
		this.add(AnchorBuilder.create().alignCenter().href("https://www.tapestrycard.com").text("Learn More About Tapestry").build());

		return this;
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event)
	{
//		// inform the user about an authentication error
//		if (event.getLocation().getQueryParameters().getParameters().containsKey("error"))
//		{
//			this.login.setError(true);
//		}
	}

	// -------------------------------------------------------------------
	// Our performers
	// -------------------------------------------------------------------
	private void performApplyNow(final ClickEvent<Button> event)
	{
		this.removeAll();

		SignupView signup = new SignupView(this.getAuthenticatedUser(), this.getRepository(), e ->
		{
			this.peformLogin(e);
		});

		this.add(signup);
	}

	private void peformLogin(final ClickEvent<Button> event)
	{
		this.removeAll();

		// Allocate the login form, we may use it later
		this.login = new LoginView(this.getAuthenticatedUser());
		this.login.setAction("login");
	}

}
