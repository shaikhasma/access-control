package com.gotech.accesscontrol.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gotech.accesscontrol.constant.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {      // <---- request dto to get user data from client

  @JsonIgnore
  private String id;
  private String firstName;
  private String lastName;
  private String mobileNumber;
  private String emailId;
  private String password;
  private UserType userType;

}
