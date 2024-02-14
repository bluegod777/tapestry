package com.tapestry.components;

import com.vaadin.componentfactory.addons.inputmask.InputMask;
import com.vaadin.flow.component.textfield.TextField;

public class PhoneNumberField extends TextField
{
  public PhoneNumberField()
  {
    setAllowedCharPattern("[0-9()+-]");
    new InputMask("(000) 000-0000").extend(this);
  }
}
