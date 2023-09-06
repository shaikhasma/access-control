package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.config.Log4j2Config;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.repository.UserRepo;
import com.gotech.accesscontrol.util.ValidateUser;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.gotech.accesscontrol.constant.Constants.valid;
import static com.gotech.accesscontrol.util.CommonUtil.toUser;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger serviceLogger = Log4j2Config.getLoggerVariable("UserServiceImpl");
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ValidateUser validateUser;

    /**
     * validates & save user in system.
     */
    @Override
    public Response saveUser(UserRequest userRequest) {
        User user = toUser(userRequest);
        if (validateUser.isValid(user).equals(valid)) {
            userRepo.save(user);
            serviceLogger.info("user saved successfully. " + user);
        }
        return new Response(HttpStatus.CREATED.toString(), "user created successfully. ");
    }
}