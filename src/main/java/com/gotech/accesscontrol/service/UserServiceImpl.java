package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.config.Log4j2Config;
import com.gotech.accesscontrol.exception.InvalidUserException;
import com.gotech.accesscontrol.exception.UserAlreadyExistException;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.repository.UserRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gotech.accesscontrol.constant.Constants.created;
import static com.gotech.accesscontrol.constant.Constants.duplicate;
import static com.gotech.accesscontrol.constant.Constants.invalidUser;
import static com.gotech.accesscontrol.util.CommonUtil.isValidEmailId;
import static com.gotech.accesscontrol.util.CommonUtil.isValidFirstName;
import static com.gotech.accesscontrol.util.CommonUtil.isValidLastName;
import static com.gotech.accesscontrol.util.CommonUtil.isValidMobileNumber;
import static com.gotech.accesscontrol.util.CommonUtil.isValidPassword;
import static com.gotech.accesscontrol.util.CommonUtil.isValidUserType;
import static com.gotech.accesscontrol.util.UserMapper.toUser;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger serviceLogger = Log4j2Config.getLoggerVariable("UserServiceImpl");
    @Autowired
    private UserRepo userRepo;

    /**
     * validates & save user in system.
     */
    @Override
    public Response saveUser(UserRequest userRequest) {
        User user = toUser(userRequest);
        if (isValid(user)) {
            if (isDuplicate(user)) {
                return buildFailureResponse(HttpStatus.CONFLICT.toString(), duplicate + user);
            } else {
                userRepo.save(user);
                serviceLogger.info(created + user);
                return buildSuccessResponse(HttpStatus.CREATED.toString(), created + user);
            }
        } else {
            return buildFailureResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), invalidUser + user);
        }
    }

    @Override
    public Response buildSuccessResponse(String status, String message) {
        return new Response(status, message);
    }

    @Override
    public Response buildFailureResponse(String status, String message) {
        return new Response(status, message);
    }

    private boolean isValid(User user) {
        boolean check = false;
        try {
            if (isValidFirstName(user)
                    && isValidLastName(user)
                    && isValidMobileNumber(user)
                    && isValidEmailId(user)
                    && isValidPassword(user)
                    && isValidUserType(user)) {
                check = true;
            }
        } catch (InvalidUserException e) {
            serviceLogger.info(invalidUser, e);
        }
        return check;
    }

    private boolean isDuplicate(User user) {
        boolean check = false;
        List<User> savedUsers = userRepo.findUserByEmailAndMobileNumber(user.getEmailId(), user.getMobileNumber());
        try {
            if (!savedUsers.isEmpty()) {
                check = true;
                throw new UserAlreadyExistException(duplicate + user);
            }
        } catch (UserAlreadyExistException e) {
            serviceLogger.info(duplicate + user, e);
        }
        return check;
    }
}