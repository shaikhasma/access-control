package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.Constants;
import com.gotech.accesscontrol.model.dto.UserRequest;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommonUtil {
    private static final Pattern emailPattern = Pattern.compile(Constants.emailPattern);
    private static final Pattern mobileNoPattern = Pattern.compile(Constants.mobileNoPattern);
    private static final Pattern namePattern = Pattern.compile(Constants.namePattern);
    private static final Pattern passPattern = Pattern.compile(Constants.passwordPattern);

    /*
     *   convertor of DTO
     *   converts UserRequest to user
     *   and returns user
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

    public static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidMobileNo(String contactNo) {
        Matcher matcher = mobileNoPattern.matcher(contactNo);
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        Matcher matcher = namePattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = passPattern.matcher(password);
        return matcher.matches();
    }
}
