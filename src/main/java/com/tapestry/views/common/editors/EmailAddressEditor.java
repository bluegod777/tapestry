package com.tapestry.views.common.editors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.EmailField;

public class EmailAddressEditor extends EditorSkeleton
{
	private final String fieldTitle;
	private final String defaultValue;

	private EmailField field;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public EmailAddressEditor(final String title, final String helpText, final String fieldTitle, final String defaultValue)
	{
		super(title, helpText);

		this.fieldTitle = fieldTitle;
		this.defaultValue = defaultValue;

		this.init();
	}

	@Override
	public Component getEditor()
	{
		this.field = new EmailField(this.fieldTitle);
		this.field.setSizeFull();
		this.field.setValue(this.defaultValue);

		this.field.setRequiredIndicatorVisible(true);
		this.field.setPattern("^\\S+@\\S+\\.\\S+$");
		this.field.setAutoselect(true);
		this.field.setAutofocus(true);

		this.setPrefixIcon(VaadinIcon.ENVELOPE);

		return this.field;
	}

	public EmailAddressEditor setPrefixIcon(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}
}
