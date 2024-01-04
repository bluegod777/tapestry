package com.tapestry.views.common.editors;

import com.tapestry.views.common.builders.HtmlLabelBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class EditorSkeleton extends VerticalLayout
{
	private final String title;
	private final String helpText;

	public EditorSkeleton(final String title, final String helpText)
	{
		this.title = title;
		this.helpText = helpText;
	}

	protected void init()
	{
		this.addClassName("input-dialog");

		this.add(HtmlLabelBuilder.create().className("title").text(this.title).build());
		this.add(HtmlLabelBuilder.create().className("help-text").text(this.helpText).build());

		this.add(this.getEditor());
	}

	public String getTitle()
	{
		return this.title;
	}

	public String getHelpText()
	{
		return this.helpText;
	}

	// -------------------------------------------------------------------
	// Our abstracts
	// -------------------------------------------------------------------
	public abstract Component getEditor();
}
