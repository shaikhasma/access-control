package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.repository.UserRepo;
import com.gotech.accesscontrol.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepo userRepo;
  @Autowired
  CommonUtil commonUtil;

  public String saveUser(UserRequest userRequest) {
    userRepo.save(commonUtil.toUser(userRequest));
    return "User created successfully. ";
  }
}
