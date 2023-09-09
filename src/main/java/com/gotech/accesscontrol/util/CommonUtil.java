package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.config.Log4j2Config;
import com.gotech.accesscontrol.constant.Constants;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.exception.InvalidUserException;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gotech.accesscontrol.constant.Constants.mobileNumber;
import static com.gotech.accesscontrol.constant.Constants.emailId;
import static com.gotech.accesscontrol.constant.Constants.firstName;
import static com.gotech.accesscontrol.constant.Constants.for_;
import static com.gotech.accesscontrol.constant.Constants.invalid;
import static com.gotech.accesscontrol.constant.Constants.lastName;
import static com.gotech.accesscontrol.constant.Constants.password;
import static com.gotech.accesscontrol.constant.Constants.userType;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class CommonUtil {
    private static final Pattern emailPattern = Pattern.compile(Constants.emailPattern);
    private static final Pattern mobileNoPattern = Pattern.compile(Constants.mobileNoPattern);
    private static final Pattern namePattern = Pattern.compile(Constants.namePattern);
    private static final Pattern passPattern = Pattern.compile(Constants.passwordPattern);
    private static final Logger utilLogger = Log4j2Config.getLoggerVariable("CommonUtil");
    private static String message = "";

    public static boolean checkValidEmailId(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkValidMobileNumber(String contactNo) {
        Matcher matcher = mobileNoPattern.matcher(contactNo);
        return matcher.matches();
    }

    public static boolean checkValidName(String name) {
        Matcher matcher = namePattern.matcher(name);
        return matcher.matches();
    }

    public static boolean checkValidPassword(String password) {
        Matcher matcher = passPattern.matcher(password);
        return matcher.matches();
    }

    public static boolean checkValidUserType(String userType) {
        boolean check = false;
        for (UserType userType1 : UserType.values()) {
            if (userType1.getUserType().equals(userType)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static boolean isValidFirstName(User user) {
        if (isEmpty(user.getFirstName())
                || isBlank(user.getFirstName())
                || !checkValidName(user.getFirstName())) {
            message = invalid + firstName + for_ + user;
            utilLogger.info(message);
            throw new InvalidUserException(message);
        } else {
            return true;
        }
    }

    public static boolean isValidLastName(User user) {
        if (isEmpty(user.getLastName())
                || isBlank(user.getLastName())
                || !checkValidName(user.getLastName())) {
            message = invalid + lastName + for_ + user;
            utilLogger.info(message);
            throw new InvalidUserException(message);
        } else {
            return true;
        }
    }

    public static boolean isValidMobileNumber(User user) {
        if (isEmpty(user.getMobileNumber())
                || isBlank(user.getMobileNumber())
                || !checkValidMobileNumber(user.getMobileNumber())) {
            message = invalid + mobileNumber + for_ + user;
            utilLogger.info(message);
            throw new InvalidUserException(message);
        } else {
            return true;
        }
    }

    public static boolean isValidEmailId(User user) {
        if (isEmpty(user.getEmailId())
                || isBlank(user.getEmailId())
                || !checkValidEmailId(user.getEmailId())) {
            message = invalid + emailId + for_ + user;
            utilLogger.info(message);
            throw new InvalidUserException(message);
        } else {
            return true;
        }
    }

    public static boolean isValidPassword(User user) {
        if (isEmpty(user.getPassword())
                || isBlank(user.getPassword())
                || !checkValidPassword(user.getPassword())) {
            message = invalid + password + for_ + user;
            utilLogger.info(message);
            throw new InvalidUserException(message);
        } else {
            return true;
        }
    }

    public static boolean isValidUserType(User user) {
        if (isEmpty(user.getUserType().toString())
                || isBlank(user.getUserType().toString())
                || !checkValidUserType(user.getUserType().toString())) {
            message = invalid + userType + for_ + user;
            utilLogger.info(message);
            throw new InvalidUserException(message);
        } else {
            return true;
        }
    }
}