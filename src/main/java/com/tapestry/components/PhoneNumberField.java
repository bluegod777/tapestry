package com.tapestry.components;

import com.vaadin.flow.component.textfield.TextField;

public class PhoneNumberField extends TextField {
  public PhoneNumberField() {
    // TODO: this does not format on type, or allow spaces, e.g. (253) 561-5555
    setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
    setAllowedCharPattern("[0-9()+-]");
  }
}
