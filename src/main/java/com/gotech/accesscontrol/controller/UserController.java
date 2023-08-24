package com.gotech.accesscontrol.controller;

import com.gotech.accesscontrol.model.dto.SignUpRequest;
import com.gotech.accesscontrol.model.dto.SignUpResponse;
import com.gotech.accesscontrol.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Services for user")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  /**
   *
   * @return
   */

  @ApiOperation("/sign-up")
  @GetMapping("/sign-up")
  public SignUpResponse signUP(SignUpRequest signUpRequest) {
    return new SignUpResponse(HttpStatus.OK.toString(),"User SignUp Successfully");
  }


}
