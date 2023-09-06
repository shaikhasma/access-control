package com.gotech.accesscontrol.controller;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.model.dto.ChangePasswordRequest;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "Services for user")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

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





  @ApiOperation("/get-all-users")
  @GetMapping("/get-all-users")
  public List<User> getAllUsers()
  {
    return userServiceImpl.getAll();
  }

//  @ApiOperation("/sign-in")
//  @PostMapping("/sign-in")
//  public Optional<User> getLoginData(@RequestParam(required = false) String emailormobile, @RequestParam String password){
//    return userServiceImpl.getLoginData(emailormobile, password);
//  }

  @ApiOperation("/sign-in")
  @PostMapping("/sign-in")
  public User getLoginData(@RequestParam("identifier") String identifier, @RequestParam("password") String password) {
    return userServiceImpl.getLoginData(identifier,password);


  }

}


