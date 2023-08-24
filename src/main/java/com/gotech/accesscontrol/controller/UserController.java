package com.gotech.accesscontrol.controller;

import com.gotech.accesscontrol.model.dto.ChangePasswordRequest;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.SignUpRequest;
import com.gotech.accesscontrol.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Services for user")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  /**
   *
   * @param signUpRequest
   * @return
   */

  @ApiOperation("/sign-up")
  @GetMapping("/sign-up")
  public Response signUP(SignUpRequest signUpRequest) {
    return new Response(HttpStatus.OK.toString(),"User SignUp Successfully");
  }

  @ApiOperation("/change-password")
  @GetMapping("/change-password")
  public Response changePassword(ChangePasswordRequest signUpRequest) {
    return new Response(HttpStatus.OK.toString(),"User SignUp Successfully");
  }


}
