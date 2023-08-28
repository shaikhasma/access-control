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

  // takes userRequest, saves it & returns success message
  public String saveUser(UserRequest userRequest) {
    // converts userRequest into user before saving then saves user into repo
    userRepo.save(commonUtil.toUser(userRequest));
    return "User created successfully. ";
  }
}
