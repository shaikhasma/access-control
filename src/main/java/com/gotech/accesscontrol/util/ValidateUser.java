package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.config.Log4j2Config;
import com.gotech.accesscontrol.constant.Constants;
import com.gotech.accesscontrol.repository.UserRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;


@Component
public class ValidateUser {
    @Autowired
    private UserRepo userRepo;
    private static final Logger validationlogger = Log4j2Config.getLoggerVariable("ValidateUser");
    public String isValid(User user) {

        String returnMessage = " ";

        if (isEmpty(user.getFirstName())
                || isBlank(user.getFirstName())
                || !CommonUtil.isValidName(user.getFirstName())) {
            validationlogger.warn(Constants.invalid + Constants.firstName + Constants.for_ + user);
            returnMessage = returnMessage + Constants.invalidFirstName;
        }
        if (isEmpty(user.getLastName())
                || isBlank(user.getLastName())
                || !CommonUtil.isValidName(user.getLastName())) {
            validationlogger.warn(Constants.invalid + Constants.lastName + Constants.for_ + user);
            returnMessage = returnMessage + Constants.invalidLastName;
        }
        if (isEmpty(user.getMobileNumber())
                || isBlank(user.getMobileNumber())
                || !CommonUtil.isValidMobileNo(user.getMobileNumber())) {
            validationlogger.warn(Constants.invalid + Constants.contactNumber + Constants.for_ + user);
            returnMessage = returnMessage + Constants.invalidContactNo;
        }
        if (isEmpty(user.getEmailId())
                || isBlank(user.getEmailId())
                || !CommonUtil.isValidEmail(user.getEmailId())) {
            validationlogger.warn(Constants.invalid + Constants.emailId + Constants.for_ + user);
            returnMessage = returnMessage + Constants.invalidEmailId;
        }
        if (isEmpty(user.getPassword())
                || isBlank(user.getPassword())
                || !CommonUtil.isValidPassword(user.getPassword())) {
            validationlogger.warn(Constants.invalid + Constants.password + Constants.for_ + user);
            returnMessage = returnMessage + Constants.invalidPassword;
        }
        if (isEmpty(returnMessage) || isBlank(returnMessage)) {
            validationlogger.info(Constants.validMessage + user);
            returnMessage = Constants.valid;
        }
        return returnMessage;
    }

    public String isUserAlreadyExist(User user) {
        List<User> saveUser = userRepo.findUserByEmailAndMobileNumber(user.getEmailId(), user.getMobileNumber());
        if (!saveUser.isEmpty()) {
            validationlogger.warn(Constants.duplicate + user);
            return Constants.notEmpty;
        } else {
            validationlogger.info(Constants.notDuplicate + user);
            return Constants.empty;
        }
    }
}
