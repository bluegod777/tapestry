package com.tapestry.views.common.editors;

import com.tapestry.data.dataservice.State;
import com.tapestry.views.common.builders.ComboBoxBuilder;
import com.tapestry.views.common.builders.TextFieldBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class MailingAddressEditor extends EditorSkeleton
{
	private TextField addressLine1;
	private TextField addressLine2;
	private TextField city;
	private ComboBox<State> state;
	private TextField zipcode;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public MailingAddressEditor(final String title, final String helpText)
	{
		super(title, helpText);

		this.init();
	}

	@Override
	public Component getEditor()
	{

		this.addressLine1 = TextFieldBuilder.create().label("Address Line 1").requiredIndicatorVisible(true).build();
		this.addressLine2 = TextFieldBuilder.create().label("Address Line 2 (optional)").build();
		this.city = TextFieldBuilder.create().label("City").requiredIndicatorVisible(true).build();

		this.state = ComboBoxBuilder.<State>create().label("State").items(State.STATES).itemLabelGenerator(State::toString).build();

		this.zipcode = TextFieldBuilder.create().label("Zipcode").pattern("^[0-9]{5}$").maxLenght(5).requiredIndicatorVisible(true).build();

		FormLayout formLayout = new FormLayout();

		formLayout.add(this.addressLine1, 2);
		formLayout.add(this.addressLine2, 2);
		formLayout.add(this.city, 2);
		formLayout.add(this.state, this.zipcode);

		return formLayout;
	}
}
