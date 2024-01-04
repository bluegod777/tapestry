package com.tapestry.views.common.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Span;

public class HtmlLabel extends Composite<Span> implements HasText
{
	private final Span content = new Span();
	private String text;
	private String title;
	private String toolTip;

	// -------------------------------------------------------------------
	//
	// -------------------------------------------------------------------
	public HtmlLabel()
	{
		this("");
	}

	public HtmlLabel(final String htmlText)
	{
		this.setText(htmlText);
	}

	@Override
	public Span getContent()
	{
		return this.content;
	}

	@Override
	public void setClassName(final String className)
	{
		this.content.setClassName(className);
	}

	@Override
	public void addClassName(final String className)
	{
		this.content.addClassName(className);
	}

	@Override
	protected Span initContent()
	{
		return this.content;
	}

	public HtmlLabel setStyle(final String name, final String value)
	{
		this.getContent().getElement().getStyle().set(name, value);

		return this;
	}

	@Override
	public void setText(final String htmlText)
	{
		String htmlTextToAdd = htmlText;

		// handle a null
		if (htmlTextToAdd == null)
		{
			htmlTextToAdd = "";
		}

		// If we are setting the same text, we can just return
		if (htmlTextToAdd.equals(this.text))
		{
			return;
		}

		// Html-ize the string if we need to
		if (!htmlTextToAdd.startsWith("<html>"))
		{
			htmlTextToAdd = this.toHTML(htmlTextToAdd);
		}

		// Add the text
		this.text = htmlTextToAdd;
		this.content.removeAll();
		this.content.add(new Html("<span>" + htmlText + "</span>"));
	}

	@Override
	public String getText()
	{
		return this.text;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(final String title)
	{
		this.title = title;
	}

	public String setToolTipText()
	{
		return this.toolTip;
	}

	public void setToolTipText(final String toolTip)
	{
		this.toolTip = toolTip;
	}

	public String toHTML(final String source)
	{
		if ((source == null) || source.isEmpty())
		{
			return "<html><body></body></html>";
		}

		final BufferedReader st = new BufferedReader(new StringReader(source));
		final StringBuilder buf = new StringBuilder("<html><body>");

		try
		{
			String str = st.readLine();

			while (str != null)
			{
				if (str.equalsIgnoreCase("<br/>"))
				{
					str = "<br>";
				}

				buf.append(str);

				if (!str.equalsIgnoreCase("<br>"))
				{
					buf.append("<br>");
				}

				str = st.readLine();
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		buf.append("</body></html>");

		return buf.toString();
	}

}
