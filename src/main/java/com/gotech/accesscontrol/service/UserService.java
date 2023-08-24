package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    UserRepo userRepo;
}
