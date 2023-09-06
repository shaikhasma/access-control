package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.config.Log4j2Config;
import com.gotech.accesscontrol.exception.InvalidUserException;
import com.gotech.accesscontrol.exception.UserAlreadyExistException;
import com.gotech.accesscontrol.repository.UserRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.gotech.accesscontrol.constant.Constants.contactNumber;
import static com.gotech.accesscontrol.constant.Constants.duplicate;
import static com.gotech.accesscontrol.constant.Constants.emailId;
import static com.gotech.accesscontrol.constant.Constants.firstName;
import static com.gotech.accesscontrol.constant.Constants.for_;
import static com.gotech.accesscontrol.constant.Constants.invalid;
import static com.gotech.accesscontrol.constant.Constants.invalidContactNo;
import static com.gotech.accesscontrol.constant.Constants.invalidEmailId;
import static com.gotech.accesscontrol.constant.Constants.invalidFirstName;
import static com.gotech.accesscontrol.constant.Constants.invalidLastName;
import static com.gotech.accesscontrol.constant.Constants.invalidPassword;
import static com.gotech.accesscontrol.constant.Constants.lastName;
import static com.gotech.accesscontrol.constant.Constants.notDuplicate;
import static com.gotech.accesscontrol.constant.Constants.password;
import static com.gotech.accesscontrol.constant.Constants.valid;
import static com.gotech.accesscontrol.constant.Constants.validMessage;
import static com.gotech.accesscontrol.util.CommonUtil.isValidEmail;
import static com.gotech.accesscontrol.util.CommonUtil.isValidMobileNo;
import static com.gotech.accesscontrol.util.CommonUtil.isValidName;
import static com.gotech.accesscontrol.util.CommonUtil.isValidPassword;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class ValidateUser {
    @Autowired
    private UserRepo userRepo;
    private static final Logger validationlogger = Log4j2Config.getLoggerVariable("ValidateUser");

    public String isValid(User user) {

        String returnMessage = " ";

        List<User> savedUsers = userRepo.findUserByEmailAndMobileNumber(user.getEmailId(), user.getMobileNumber());
        if (!savedUsers.isEmpty()) {
            validationlogger.warn(duplicate + user);
            returnMessage = duplicate + user;
            throw new UserAlreadyExistException(returnMessage);
        } else {
            validationlogger.info(notDuplicate + user);
            if (isEmpty(user.getFirstName())
                    || isBlank(user.getFirstName())
                    || !isValidName(user.getFirstName())) {
                validationlogger.warn(invalid + firstName + for_ + user);
                returnMessage = returnMessage + invalidFirstName;
            }
            if (isEmpty(user.getLastName())
                    || isBlank(user.getLastName())
                    || !isValidName(user.getLastName())) {
                validationlogger.warn(invalid + lastName + for_ + user);
                returnMessage = returnMessage + invalidLastName;
            }
            if (isEmpty(user.getMobileNumber())
                    || isBlank(user.getMobileNumber())
                    || !isValidMobileNo(user.getMobileNumber())) {
                validationlogger.warn(invalid + contactNumber + for_ + user);
                returnMessage = returnMessage + invalidContactNo;
            }
            if (isEmpty(user.getEmailId())
                    || isBlank(user.getEmailId())
                    || !isValidEmail(user.getEmailId())) {
                validationlogger.warn(invalid + emailId + for_ + user);
                returnMessage = returnMessage + invalidEmailId;
            }
            if (isEmpty(user.getPassword())
                    || isBlank(user.getPassword())
                    || !isValidPassword(user.getPassword())) {
                validationlogger.warn(invalid + password + for_ + user);
                returnMessage = returnMessage + invalidPassword;
            }
            if (isEmpty(returnMessage) || isBlank(returnMessage)) {
                validationlogger.info(validMessage + user);
                returnMessage = valid;
            } else {
                throw new InvalidUserException(returnMessage);
            }
            return returnMessage;
        }
    }
}