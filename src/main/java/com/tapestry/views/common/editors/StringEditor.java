package com.tapestry.views.common.editors;

import com.tapestry.views.common.builders.TextFieldBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;

public class StringEditor extends EditorSkeleton
{
	private final String fieldTitle;
	private final String defaultValue;

	private TextField field;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public StringEditor(final String title, final String helpText, final String fieldTitle, final String defaultValue)
	{
		super(title, helpText);

		this.fieldTitle = fieldTitle;
		this.defaultValue = defaultValue;

		this.init();
	}

	@Override
	public Component getEditor()
	{
		this.field = TextFieldBuilder.create().widthFull().placeholder(this.fieldTitle).value(this.defaultValue).build();

		this.field.setAutoselect(true);
		this.field.setAutofocus(true);

		return this.field;
	}

	public StringEditor setPrefixIcon(final VaadinIcon icon)
	{
		this.field.setPrefixComponent(icon.create());

		return this;
	}
}
