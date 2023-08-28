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

  public String saveUser(UserRequest userRequest) {  // takes userRequest, saves it & returns success message
    userRepo.save(commonUtil.toUser(userRequest));  // converts userRequest into user before saving
    return "User created successfully. ";
  }
}
