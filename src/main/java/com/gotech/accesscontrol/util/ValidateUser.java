package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.config.Log4j2Config;
import com.gotech.accesscontrol.constant.ExceptionMessages;
import com.gotech.accesscontrol.constant.Fields;
import com.gotech.accesscontrol.repository.UserRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class ValidateUser {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ValidateName validateName;
    @Autowired
    private ValidateMobileNo validateMobileNo;
    @Autowired
    private ValidateEmail validateEmail;
    @Autowired
    private ValidatePassword validatePassword;
    private static final Logger validationlogger = Log4j2Config.getLoggerVariable("ValidateUser");

    public String isValid(User user) {
        int count = 0, i = 1;
        String returnMessage = "";

        switch (i) {
            case 1:
                if (isEmpty(user.getFirstName())
                        || user.getFirstName().equals("")
                        || user.getFirstName().equals(" ")
                        || !validateName.isValidName(user.getFirstName())) {
                    validationlogger.warn(Fields.invalid + Fields.firstName + Fields.for_ + user);
                    count++;
                    i++;
                    returnMessage = returnMessage + ExceptionMessages.invalidFirstName;
                }
            case 2:
                if (isEmpty(user.getLastName())
                        || user.getLastName().equals("")
                        || user.getLastName().equals(" ")
                        || !validateName.isValidName(user.getLastName())) {
                    validationlogger.warn(Fields.invalid + Fields.lastName + Fields.for_ + user);
                    count++;
                    i++;
                    returnMessage = returnMessage + ExceptionMessages.invalidLastName;
                }
            case 3:
                if (isEmpty(user.getMobileNumber())
                        || user.getMobileNumber().equals("")
                        || user.getMobileNumber().equals(" ")
                        || !validateMobileNo.isValidMobileNo(user.getMobileNumber())) {
                    validationlogger.warn(Fields.invalid + Fields.contactNumber + Fields.for_ + user);
                    count++;
                    i++;
                    returnMessage = returnMessage + ExceptionMessages.invalidContactNo;
                }
            case 4:
                if (isEmpty(user.getEmailId())
                        || user.getEmailId().equals("")
                        || user.getEmailId().equals(" ")
                        || !validateEmail.isValidEmail(user.getEmailId())) {
                    validationlogger.warn(Fields.invalid + Fields.emailId + Fields.for_ + user);
                    count++;
                    i++;
                    returnMessage = returnMessage + ExceptionMessages.invalidEmailId;
                }
            case 5:
                if (isEmpty(user.getPassword())
                        || user.getPassword().equals("")
                        || user.getPassword().equals(" ")
                        || !validatePassword.isValidPassword(user.getPassword())) {
                    validationlogger.warn(Fields.invalid + Fields.password + Fields.for_ + user);
                    count++;
                    i++;
                    returnMessage = returnMessage + ExceptionMessages.invalidPassword;
                }
            case 6:
                if (count == 0) {
                    validationlogger.info(Fields.validMessage + user);
                    returnMessage = Fields.valid;
                }
            default:
                return returnMessage;
        }
    }

    public String isUserAlreadyExist(User user) {
        List<User> saveUser = userRepo.findUserByEmailAndMobileNumber(user.getEmailId(), user.getMobileNumber());
        if (!saveUser.isEmpty()) {
            validationlogger.warn(Fields.duplicate + user);
            return Fields.notEmpty;
        } else {
            validationlogger.info(Fields.notDuplicate + user);
            return Fields.empty;
        }
    }
}
