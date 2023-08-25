package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.converter.UserConverter;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired UserRepo userRepo;
  @Autowired UserConverter userConverter;

  public String saveUser(UserRequest userRequest) {
    userRepo.save(userConverter.requestToUser(userRequest));
    return "User created successfully. ";
  }
}
