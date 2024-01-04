package com.tapestry.views.common.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;

public class TButton extends Button
{
	private Dialog dialog;
	private Component dialogContent;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public TButton()
	{
		super();
	}

	public TButton(String text)
	{
		super(text);
	}

	public TButton(Component icon)
	{
		super(icon);
	}

	public TButton(String text, Component icon)
	{
		super(text, icon);
	}

	public TButton(String text, ComponentEventListener<ClickEvent<Button>> clickListener)
	{
		super(text, clickListener);
	}

	public TButton(Component icon, ComponentEventListener<ClickEvent<Button>> clickListener)
	{
		super(icon, clickListener);
	}

	public TButton(String text, Component icon, ComponentEventListener<ClickEvent<Button>> clickListener)
	{
		super(text, icon, clickListener);
	}

	public Dialog getDialog()
	{
		return this.dialog;
	}

	public void setDialog(Dialog dialog)
	{
		this.dialog = dialog;
	}

	public Component getDialogContent()
	{
		return this.dialogContent;
	}

	public void setDialogContent(Component dialogContent)
	{
		this.dialogContent = dialogContent;
	}

}
