package com.tapestry.views.common.editors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;

public class PhoneNumberEditor extends EditorSkeleton
{
	private final String fieldTitle;
	private final String defaultValue;

	private TextField field;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public PhoneNumberEditor(final String title, final String helpText, final String fieldTitle, final String defaultValue)
	{
		super(title, helpText);

		this.fieldTitle = fieldTitle;
		this.defaultValue = defaultValue;

		this.init();
	}

	@Override
	public Component getEditor()
	{
		this.field = new TextField(this.fieldTitle);
		this.field.setSizeFull();
		this.field.setValue(this.defaultValue);

		this.field.setRequiredIndicatorVisible(true);
		this.field.setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
		this.field.setAllowedCharPattern("[0-9()+-]");
		this.field.setMinLength(5);
		this.field.setMaxLength(18);
		this.field.setAutoselect(true);
		this.field.setAutofocus(true);

		this.setPrefixIcon(VaadinIcon.PHONE);

		return this.field;
	}

	public PhoneNumberEditor setPrefixIcon(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}
}
