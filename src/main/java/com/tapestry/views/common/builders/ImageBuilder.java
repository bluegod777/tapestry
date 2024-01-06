package com.tapestry.views.common.builders;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

// -------------------------------------------------------------------
// Some standard locations
// ------------------------
// https://webdav-001.ayt360.org/repository/public/tapestry.com/avatars/fun-3d-cartoon-white-labrador-retriever_183364-118249.avif
// -------------------------------------------------------------------
public class ImageBuilder
{
	public static final String ROOT = "https://webdav-001.ayt360.org/repository/public/tapestry.com/";

	private final VerticalLayout wrapper;
	private Image image;

	public static ImageBuilder create()
	{
		return new ImageBuilder();
	}

	public ImageBuilder()
	{
		this.wrapper = VerticalLayoutBuilder.create().tight().sizeUndefined().build();
	}

	public ImageBuilder selfCenter()
	{
		this.wrapper.getStyle().set("align-self", "center");

		return this;
	}

	public ImageBuilder centerImage()
	{
		this.image.getStyle().set("align-self", "center");

		return this;
	}

	public ImageBuilder urlRelative(final String url, final String alt)
	{
		return this.url(ImageBuilder.ROOT + url, alt);
	}

	public ImageBuilder url(final String url, final String alt)
	{
		this.image = new Image(url, alt);

		this.wrapper.add(this.image);

		return this;
	}

	public ImageBuilder icon(final VaadinIcon icon)
	{
		this.wrapper.add(icon.create());

		return this;
	}

	public ImageBuilder imageWidth(final int w)
	{
		this.image.getElement().getStyle().set("width", String.format("%d", w));

		return this;
	}

	public ImageBuilder imageHeight(final int h)
	{
		this.image.getElement().getStyle().set("height", String.format("%dpx", h));

		return this;
	}

	public ImageBuilder imageWidth(final String w)
	{
		this.image.getElement().getStyle().set("width", w);

		return this;
	}

	public ImageBuilder imageHeight(final String h)
	{
		this.image.getElement().getStyle().set("height", h);

		return this;
	}

	public ImageBuilder padding(final String padding)
	{
		this.image.getElement().getStyle().set("padding", padding);

		return this;
	}

	public ImageBuilder wrapperClassName(final String className)
	{
		this.wrapper.setClassName(className);

		return this;
	}

	public ImageBuilder wrapperSizeUndefined()
	{
		this.wrapper.setSizeUndefined();

		return this;
	}

	public ImageBuilder className(final String className)
	{
		this.image.setClassName(className);

		return this;
	}

	public ImageBuilder addClassName(final String className)
	{
		// Make sure we are dealing with the image
		if (this.image != null)
		{
			this.image.addClassName(className);
		}

		// If not, we are dealing with an icon and add the
		// class to the wrapper
		this.wrapper.addClassName(className);

		return this;
	}

	public ImageBuilder attribute(final String attributeName, final boolean state)
	{
		this.wrapper.getElement().setAttribute(attributeName, state);

		return this;
	}

	public VerticalLayout build()
	{
		return this.wrapper;
	}

}
