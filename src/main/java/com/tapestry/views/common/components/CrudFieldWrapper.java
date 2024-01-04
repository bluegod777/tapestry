package com.tapestry.views.common.components;

import com.tapestry.views.common.builders.ButtonBuilder;
import com.tapestry.views.common.builders.HorizontalLayoutBuilder;
import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CrudFieldWrapper extends VerticalLayout
{
	private final String title;
	private final Component field;

	private Button editButton;
	private Button addButton;

	private HorizontalLayout options;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public static CrudFieldWrapper wrap(final String title, final Component field)
	{
		return new CrudFieldWrapper(title, field);
	}

	public CrudFieldWrapper(final String title, final Component field)
	{
		this.title = title;
		this.field = field;

		this.init();
	}

	public CrudFieldWrapper makeEditable(final ComponentEventListener<ClickEvent<Button>> listener)
	{
		this.editButton = ButtonBuilder.create().icon(VaadinIcon.PENCIL).className("option").text("Edit").listener(listener).build();

		this.options.add(this.editButton);

		return this;
	}

	public CrudFieldWrapper makeAddable(final ComponentEventListener<ClickEvent<Button>> listener)
	{
		this.addButton = ButtonBuilder.create().icon(VaadinIcon.PLUS).className("option").text("Add").listener(listener).build();

		this.options.add(this.addButton);

		return this;
	}

	private void init()
	{
		this.addClassName("crud-field-wrapper");

		HtmlLabel title = HtmlLabelBuilder.create().text(this.title).widthFull().className("title").build();

		this.add(HorizontalLayoutBuilder.create().widthFull().add(title).add(this.options = HorizontalLayoutBuilder.create().sizeUndefined().build()).build());

		// Prep and add the field
		//
		// We may need to take into account the type of fields we are dealing with
		this.field.addClassName("values");
		this.add(this.field);
	}
}
