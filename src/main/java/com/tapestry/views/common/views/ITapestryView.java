package com.tapestry.views.common.views;

import com.tapestry.data.dataservice.State;
import com.tapestry.data.entity.User;
import com.tapestry.utils.ValidateUtils;
import com.tapestry.views.common.builders.DialogBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public interface ITapestryView
{
	public User getTapestryUser();

	default boolean validateFieldAsMobileNumber(final TextField mobileNumberField)
	{
		if (!ValidateUtils.validatePhoneNumber(mobileNumberField.getValue()))
		{
			DialogBuilder.create().asError("The mobile number you entered does not appear to be valid.").build().open();

			return false;
		}

		return true;
	}

	default boolean validateFieldAsEmailAddress(final TextField emailAddress)
	{
		if (!ValidateUtils.validateEmailAddress(emailAddress.getValue()))
		{
			DialogBuilder.create().asError("The email address you entered does not appear to be valid.").build().open();

			return false;

		}

		return true;
	}

	default boolean validateFieldAsSocialSecurityNumber(final PasswordField ssn)
	{
		if (!ValidateUtils.validateSocialSecurityNumber(ssn.getValue()))
		{
			DialogBuilder.create().asError("The SSN you entered doesn't seem to be formatted correctly.").build().open();

			return false;

		}

		return true;
	}

	default boolean validatePasswordMatch(final PasswordField p1, final PasswordField p2)
	{
		if (!p1.getValue().equalsIgnoreCase(p2.getValue()))
		{
			DialogBuilder.create().asError("The passwords that you entered do not match.").build().open();

			return false;

		}

		return true;
	}

	@SuppressWarnings("unchecked")
	default boolean validateField(final Component... components)
	{
		for (Component c : components)
		{
			if (c instanceof TextField)
			{
				if (((TextField) c).getValue().isEmpty())
				{
					DialogBuilder.create().asRequiredFieldMissing(((TextField) c).getLabel()).build().open();

					return false;
				}
			}

			if (c instanceof PasswordField)
			{
				if (((PasswordField) c).getValue().isEmpty())
				{
					DialogBuilder.create().asRequiredFieldMissing(((PasswordField) c).getLabel()).build().open();

					return false;
				}
			}

			if (c instanceof ComboBox<?>)
			{
				if (((ComboBox<State>) c).getValue() == null)
				{
					DialogBuilder.create().asRequiredFieldMissing(((ComboBox<State>) c).getLabel()).build().open();

					return false;
				}
			}

		}

		return true;
	}
}
