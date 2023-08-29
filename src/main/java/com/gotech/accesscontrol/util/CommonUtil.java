package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.model.dto.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    /*convertor of DTO
    converts UserRequest to user and returns user*/

    public static User toUser(UserRequest userRequest) {
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
