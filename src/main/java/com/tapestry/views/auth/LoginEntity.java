package com.tapestry.views.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginEntity {
  @NotEmpty
  private String phone = "";

  @NotEmpty
  private String password = "";
}