package com.gotech.accesscontrol.controller;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.model.dto.ChangePasswordRequest;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Services for user")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


  UserType userType;

  @Autowired
  private UserServiceImpl userServiceImpl;




  @ApiOperation("/change-password")
  @GetMapping("/change-password")
  public Response changePassword(ChangePasswordRequest signUpRequest) {
    return new Response(HttpStatus.OK.toString(), "User SignUp Successfully");
  }


  @ApiOperation("- register new user")
  @PostMapping("/register")
  public Response registerUser(@RequestBody UserRequest userRequest)
  {
    return userServiceImpl.saveUser(userRequest);
  }




//  @PreAuthorize("hasRole('ADMIN')")
  @ApiOperation("/get-all-users")
  @GetMapping("/get-all-users")
  public List<User> getAllUsers()
  {
    return userServiceImpl.getAll();
  }

}


