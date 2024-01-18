package com.tapestry.views.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationEntity {
  @NotEmpty
  private String firstName = "";

  @NotEmpty
  private String lastName = "";

  @Email
  @NotEmpty
  private String email = "";

  @NotEmpty
  private String phone = "";

  @NotEmpty
  private String password = "";
}