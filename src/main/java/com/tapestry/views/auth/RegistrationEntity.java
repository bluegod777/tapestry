package com.tapestry.views.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

public class RegistrationEntity {
  @Getter
  @Setter
  @NotEmpty
  private String name = "";

  @Getter
  @Setter
  @Email
  @NotEmpty
  private String email = "";
}