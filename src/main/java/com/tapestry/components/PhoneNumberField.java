package com.tapestry.components;

import com.vaadin.componentfactory.addons.inputmask.InputMask;
import com.vaadin.flow.component.textfield.TextField;

public class PhoneNumberField extends TextField
{
  public PhoneNumberField()
  {
    setAllowedCharPattern("[0-9()+-]");
    new InputMask("1 (000) 000-0000").extend(this);
  }

  @Override
  public String getValue()
  {
    return super.getValue().replaceAll("[^0-9]", "");
  }
}
