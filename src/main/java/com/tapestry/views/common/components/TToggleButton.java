package com.tapestry.views.common.components;

import com.tapestry.views.common.builders.HorizontalLayoutBuilder;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TToggleButton extends VerticalLayout
{
	private final String buttonText;
	private final String buttonHelpText;
	private ToggleButton toggleButton;

	public TToggleButton(final String buttonText, final String buttonHelpText)
	{
		this.buttonText = buttonText;
		this.buttonHelpText = buttonHelpText;

		this.init();
	}

	private void init()
	{
		this.addClassName("t-toogle-button");
		this.setSpacing(false);
		this.setPadding(false);

		this.toggleButton = new ToggleButton();

		this.add(HorizontalLayoutBuilder.create().tight().spacing(true).add(this.toggleButton).add(HtmlLabelBuilder.create().className("text").text(this.buttonText).build()).build());
		this.add(HtmlLabelBuilder.create().className("help-text").text(this.buttonHelpText).build());
	}

	public TToggleButton listener(HasValue.ValueChangeListener<? super ComponentValueChangeEvent<Checkbox, Boolean>> listener)
	{
		this.toggleButton.addValueChangeListener(listener);

		return this;
	}

	public void setValue(final Boolean value)
	{
		this.toggleButton.setValue(value);
	}

	public Boolean getValue()
	{
		return this.toggleButton.getValue();
	}
}
