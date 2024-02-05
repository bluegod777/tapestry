package com.tapestry.components;

import com.vaadin.flow.component.textfield.TextField;

public class PhoneNumberField extends TextField
{
  public PhoneNumberField()
  {
    // TODO: this does not format on type, or allow spaces, e.g. (253) 561-5555
    setAllowedCharPattern("[0-9()+-]");
  }
}
