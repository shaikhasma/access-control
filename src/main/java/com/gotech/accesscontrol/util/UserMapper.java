package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.model.dto.UserResponse;

public class UserMapper {
    /**
     * convertor of DTO
     * converts UserRequest to User
     * and returns User
     */
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

    /**
     * convertor of User
     * converts User to UserResponse
     * and returns UserResponse
     */
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobileNumber(user.getMobileNumber())
                .emailId(user.getEmailId())
                .password(user.getPassword())
                .userType(user.getUserType())
                .build();
    }
}