package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.model.dto.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {    // convertor of types
    public User toUser(UserRequest userRequest) {     // converts UserRequest to user
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .mobileNumber(userRequest.getMobileNumber())
                .emailId(userRequest.getEmailId())
                .password(userRequest.getPassword())
                .userType(userRequest.getUserType())
                .build();
    }
}
