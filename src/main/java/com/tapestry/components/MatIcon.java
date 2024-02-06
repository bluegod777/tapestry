package com.tapestry.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

public class MatIcon extends Div
{
  private Span icon = new Span();

  public MatIcon(final String icon)
  {
    this(icon, true);
  }

  public MatIcon(final String icon, final Boolean clickable)
  {
    this.addClassName("icon");
    this.icon.addClassNames("m-i", "m-i-outlined");
    this.icon.add(icon);
    this.add(this.icon);

    if (clickable)
    {
      this.addClassName("c");
    }
  }

  public void replace(final String icon)
  {
    this.icon.setText(icon);
  }
}