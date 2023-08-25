package com.gotech.accesscontrol.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequest {

  @NotNull private String name;

  @NotNull private String emailId;

  @NotNull private String contactNo;

  @NotNull private String password;

  @NotNull private String confirmPassword;
}
