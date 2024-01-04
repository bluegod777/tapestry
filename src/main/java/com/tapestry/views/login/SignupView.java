package com.tapestry.views.login;

import com.tapestry.data.repositories.TapestryRepository;
import com.tapestry.security.AuthenticatedUser;
import com.tapestry.views.common.builders.ButtonBuilder;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.tapestry.views.common.builders.PasswordFieldBuilder;
import com.tapestry.views.common.builders.TapestryBuilder;
import com.tapestry.views.common.builders.TextFieldBuilder;
import com.tapestry.views.common.components.CardSwitcherLayout;
import com.tapestry.views.common.views.TapestryRouterViewSkeleton;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SignupView extends TapestryRouterViewSkeleton<SignupView> implements BeforeEnterObserver
{
	private final ComponentEventListener<ClickEvent<Button>> completionsListener;

	// Support for the wizard
	private CardSwitcherLayout swticher;
	private TextField firstName;
	private TextField lastName;
	private TextField mobilePhone;
	private TextField confirmationPin;
	private TextField emailAddress;
	private PasswordField loginPassword;
	private PasswordField confirmLoginPassword;
	private PasswordField socialSecurityNumber;

	// Tapestry user account
	// private UserEAO tapestryUserAccount;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public SignupView(final AuthenticatedUser authenticatedUser, final TapestryRepository repository, final ComponentEventListener<ClickEvent<Button>> completionsListener)
	{
		super(authenticatedUser, repository);

		this.completionsListener = completionsListener;

		this.init();
	}

	@Override
	public SignupView init()
	{
		this.addClassName("sign-up-view");
		this.setSpacing(true);
		this.setSizeFull();

		this.add(TapestryBuilder.getTapestryLogo());

		this.firstName = TextFieldBuilder.create().label("First Name").required().requiredIndicatorVisible(true).widthFull().build();
		this.lastName = TextFieldBuilder.create().label("Last Name").required().requiredIndicatorVisible(true).widthFull().build();

		this.mobilePhone = TextFieldBuilder.create().label("Mobile Phone").asPhoneNumber().widthFull().build();
		this.confirmationPin = TextFieldBuilder.create().label("Confirmation Pin").placeholder("ex. 1234").widthFull().build();

		this.emailAddress = TextFieldBuilder.create().label("Login Email Address").asEmailAddress().widthFull().build();
		this.loginPassword = PasswordFieldBuilder.create().label("Login Password").widthFull().build();
		this.confirmLoginPassword = PasswordFieldBuilder.create().label("Confirm Password").widthFull().build();

		this.socialSecurityNumber = PasswordFieldBuilder.create().widthFull().asSocialSecurityNumber().label("Social Security Number").build();

		this.swticher = new CardSwitcherLayout();

		// Gather the first & last name - once we do that we go
		// to the next step.
		this.swticher.addCard("Step 0", new SignUpViewStep1(e -> this.swticher.showCard("Step 1")));

		// Now, let's grab the consumers mobile number, but before we can go on :
		//
		// 1) make sure the number is unique
		// 2) add a new record to the DB
		// 3) send out the OTP
		this.swticher.addCard("Step 1", new SignUpViewStep2(e -> this.swticher.showCard("Step 2"), e -> this.swticher.showCard("Step 0")));

		// Allow the user to enter the OPT they get
		//
		// Q: what do we do if the user goes back and changes their number
		this.swticher.addCard("Step 2", new SignUpViewStep3(e -> this.swticher.showCard("Step 3"), e -> this.swticher.showCard("Step 1")));

		// Gather login account information
		//
		// username - their email address
		// Password & confirm
		this.swticher.addCard("Step 3", new SignUpViewStep4(e -> this.swticher.showCard("Step 4"), e -> this.swticher.showCard("Step 2")));

		// Gather their Social security number
		this.swticher.addCard("Step 4", new SignUpViewStep5(this::peformFinished, e -> this.swticher.showCard("Step 3")));

		// All done!
		this.swticher.addCard("Step 5", new SignUpViewStepCongratluation(this::peformGotoLogin));

		// Start at the beginning
		this.swticher.showCard("Step 0");

		this.add(this.swticher);

		return this;
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event)
	{
	}

	// -------------------------------------------------------------------
	// Our performers
	// -------------------------------------------------------------------
	private void peformFinished(ClickEvent<Button> e)
	{
		SignupView.log.info("Attempting to create the account ....");

		this.swticher.showCard("Step 5");
	}

	private void peformGotoLogin(ClickEvent<Button> e)
	{
		SignupView.log.info("We are going to loging!");

		this.completionsListener.onComponentEvent(null);
	}

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	protected class SignUpViewStep1 extends VerticalLayout
	{
		public SignUpViewStep1(final ComponentEventListener<ClickEvent<Button>> nextTab)
		{
			this.init(nextTab);
		}

		private void init(final ComponentEventListener<ClickEvent<Button>> nextTab)
		{
			this.addClassName("step");

			this.add(HtmlLabelBuilder.create().className("greeting").text("Hey There!").build());
			this.add(HtmlLabelBuilder.create().className("action-text").text("Let's start with some basic information.").build());

			this.add(SignupView.this.firstName, SignupView.this.lastName);

			this.add(HtmlLabelBuilder.create().className("help-text").text("If you can provide us with the legal spelling as we will be able to provide you with better tax documents.").build());

			this.add(ButtonBuilder.create().primary().widthFull().listener(e ->
			{
				// First let's make sure the fields are filled in.
				if (SignupView.this.validateField(SignupView.this.firstName, SignupView.this.lastName))
				{
					nextTab.onComponentEvent(null);
				}

			}).text("Next").build());
		}
	}

	protected class SignUpViewStep2 extends VerticalLayout
	{
		public SignUpViewStep2(final ComponentEventListener<ClickEvent<Button>> nextTab, final ComponentEventListener<ClickEvent<Button>> backTab)
		{
			this.init(nextTab, backTab);
		}

		private void init(final ComponentEventListener<ClickEvent<Button>> nextTab, final ComponentEventListener<ClickEvent<Button>> backTab)
		{
			this.addClassName("step");

			this.add(HtmlLabelBuilder.create().className("greeting").text("Mobile Number").build());
			this.add(HtmlLabelBuilder.create().className("action-text").text("Please enter your mobile number.").build());

			this.add(SignupView.this.mobilePhone);

			this.add(HtmlLabelBuilder.create().className("help-text").text("Your mobile number is how we are going to communicate with you on important alerts.  Don't worry, we will never send your any marketing text nor will be share your number with anyone else.").build());

			this.add(ButtonBuilder.create().primary().widthFull().listener(e ->
			{
				// First let's make sure the fields are filled in.
				if (SignupView.this.validateFieldAsMobileNumber(SignupView.this.mobilePhone))
				{
//					// Create the user
//					var user = SignupView.this.getRepository().getFrogClient().createUser(EmailAddressEAO.EMPTY, new PhoneNumberEAO(SignupView.this.mobilePhone.getValue()), SignupView.this.firstName.getValue(), SignupView.this.lastName.getValue());
//
//					if (user.isOK())
//					{
//						// Save off our user
//						SignupView.this.tapestryUserAccount = user.unwrap();
//
//						// Send out the OTP to the consumer
//						SignupView.this.getRepository().getFrogClient().resetPassword(user.unwrap().getMobilePhoneNumber());
//
//						// Move on to the next step
//						nextTab.onComponentEvent(null);
//					}
//					else
//					{
//						DialogBuilder.create().asError(user.getErrorMessage()).build().open();
//					}
				}
			}).text("Next").build());

			this.add(ButtonBuilder.create().error().widthFull().listener(backTab).text("Back").build());

		}
	}

	protected class SignUpViewStep3 extends VerticalLayout
	{
		public SignUpViewStep3(final ComponentEventListener<ClickEvent<Button>> nextTab, final ComponentEventListener<ClickEvent<Button>> backTab)
		{
			this.init(nextTab, backTab);
		}

		private void init(final ComponentEventListener<ClickEvent<Button>> nextTab, final ComponentEventListener<ClickEvent<Button>> backTab)
		{
			this.addClassName("step");

			this.add(HtmlLabelBuilder.create().className("greeting").text("Verify").build());
			this.add(HtmlLabelBuilder.create().className("action-text").text("We just sent a security pin to your phone.  Please enter it here so we know its you.").build());

			this.add(SignupView.this.confirmationPin);

			this.add(ButtonBuilder.create().widthFull().text("Resend Pin").listener(e ->
			{
				// Send out the OTP to the consumer
				// SignupView.this.getRepository().getFrogClient().resetPassword(SignupView.this.tapestryUserAccount.getMobilePhoneNumber());
			}).build());

			this.add(HtmlLabelBuilder.create().className("help-text").text("It's really important that the mobile number your gave us is you.  We'll be using your number to send you important alerts related your tapestry account.").build());

			this.add(ButtonBuilder.create().primary().widthFull().listener(e ->
			{
//				// make sure the user entered something
//				if (SignupView.this.validateField(SignupView.this.confirmationPin))
//				{
//					// Build the authentication request
//					final var request = AuthenticateRequestEAO.builder().mobileNumber(SignupView.this.tapestryUserAccount.getMobilePhoneNumber()).password(SignupView.this.confirmationPin.getValue()).build();
//
//					// Issue the authentication call
//					final var authenticate = SignupView.this.getRepository().getFrogClient().authenticate(request);
//
//					// Issue the validation call
//					if (authenticate.isOK())
//					{
//						// Move one
//						nextTab.onComponentEvent(null);
//					}
//					else
//					{
//						DialogBuilder.create().asError("Invalid confirmation pin. " + authenticate.getErrorMessage()).build().open();
//					}
//				}
			}).text("Next").build());

			this.add(ButtonBuilder.create().error().widthFull().listener(backTab).text("Back").build());

		}
	}

	protected class SignUpViewStep4 extends VerticalLayout
	{
		public SignUpViewStep4(final ComponentEventListener<ClickEvent<Button>> nextTab, final ComponentEventListener<ClickEvent<Button>> backTab)
		{
			this.init(nextTab, backTab);
		}

		private void init(final ComponentEventListener<ClickEvent<Button>> nextTab, final ComponentEventListener<ClickEvent<Button>> backTab)
		{
			this.addClassName("step");

			this.add(HtmlLabelBuilder.create().className("greeting").text("Account").build());
			this.add(HtmlLabelBuilder.create().className("action-text").text("Your email is going to be you username. You can also setup your password.").build());

			this.add(SignupView.this.emailAddress, SignupView.this.loginPassword, SignupView.this.confirmLoginPassword);

			this.add(HtmlLabelBuilder.create().className("help-text").text("You will be able to log into your Tapestry account account using your email and password.  You will also be able to access your account via your mobile number.").build());

			this.add(ButtonBuilder.create().primary().widthFull().listener(e ->
			{
				// make sure the user entered something
				if (SignupView.this.validateFieldAsEmailAddress(SignupView.this.emailAddress) && SignupView.this.validateField(SignupView.this.loginPassword, SignupView.this.confirmLoginPassword))
				{
					// Issue the validation call
					if (SignupView.this.validatePasswordMatch(SignupView.this.loginPassword, SignupView.this.confirmLoginPassword))
					{
//						// We need to save the information into the DB
//						UserEAO updateRequest = UserEAO.builder(SignupView.this.tapestryUserAccount).emailAddress(new EmailAddressEAO(SignupView.this.emailAddress.getValue())).build();
//
//						final var updated = SignupView.this.getRepository().getFrogClient().addOrUpdate(updateRequest);
//
//						if (updated.isOK())
//						{
//							// Save off the updated user
//							SignupView.this.tapestryUserAccount = updated.unwrap();
//
//							// System.out.println(SignupView.this.confirmationPin.getValue());
//							// System.out.println(SignupView.this.loginPassword.getValue());
//
//							// create the password update request
//							//
//							// at this point the old password is the confirmation pin
//							final var request = ChangePasswordRequestEAO.builder().mobileNumber(SignupView.this.tapestryUserAccount.getMobilePhoneNumber()).oldPassword(SignupView.this.confirmationPin.getValue()).newPassword(SignupView.this.loginPassword.getValue()).build();
//
//							// Update the pssword
//							final var password = SignupView.this.getRepository().getFrogClient().changePassword(request);
//
//							// Make sure it worked
//							if (password.isOK())
//							{
//								// Move one
//								nextTab.onComponentEvent(null);
//							}
//							else
//							{
//								SignupView.log.error("SignUpViewStep4::unable password : {}", password.getErrorMessage());
//
//								DialogBuilder.create().asError(password.getErrorMessage()).build().open();
//							}
//						}
//						else
//						{
//							SignupView.log.error("SignUpViewStep4::unable to update the email address : {}", updated.getErrorMessage());
//
//							DialogBuilder.create().asError(updated.getErrorMessage()).build().open();
//						}
					}
				}
			}).text("Next").build());

			this.add(ButtonBuilder.create().error().widthFull().listener(backTab).text("Back").build());

		}
	}

	protected class SignUpViewStep5 extends VerticalLayout
	{
		public SignUpViewStep5(final ComponentEventListener<ClickEvent<Button>> finish, final ComponentEventListener<ClickEvent<Button>> backTab)
		{
			this.init(finish, backTab);
		}

		private void init(final ComponentEventListener<ClickEvent<Button>> finish, final ComponentEventListener<ClickEvent<Button>> backTab)
		{
			this.addClassName("step");

			this.add(HtmlLabelBuilder.create().className("greeting").text("Identity").build());
			this.add(HtmlLabelBuilder.create().className("action-text").text("Federal regulations require that we verify your Social Security number.  This won't affect your credit score.").build());

			this.add(SignupView.this.socialSecurityNumber);

			this.add(HtmlLabelBuilder.create().className("help-text").text("And don't worry, we won't be sharing this informaiton with anyone else.").build());

			this.add(ButtonBuilder.create().primary().widthFull().listener(e ->
			{
				if (SignupView.this.validateFieldAsSocialSecurityNumber(SignupView.this.socialSecurityNumber))
				{
					finish.onComponentEvent(null);
				}
			}).text("Finish").build());
			this.add(ButtonBuilder.create().error().widthFull().listener(backTab).text("Back").build());

		}
	}

	protected class SignUpViewStepCongratluation extends VerticalLayout
	{
		public SignUpViewStepCongratluation(final ComponentEventListener<ClickEvent<Button>> toLoging)
		{
			this.init(toLoging);
		}

		private void init(final ComponentEventListener<ClickEvent<Button>> toLoging)
		{
			this.addClassName("step");

			this.add(HtmlLabelBuilder.create().className("greeting").text("Congratulations!").build());
			this.add(HtmlLabelBuilder.create().className("action-text").text("You have sucessfully created your Tapestry account! You can now login to your account.").build());

			this.add(ButtonBuilder.create().primary().widthFull().listener(toLoging).text("Got To Login").build());

		}
	}
}
