package com.tapestry.views.common.builders;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TapestryBuilder
{
	public static VerticalLayout getTapestryLogo()
	{
		return VerticalLayoutBuilder.create().addClass("consumer-tapestry-name-green-as-background").build();
	}

}
