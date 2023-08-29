package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.config.Log4j2Config;
import com.gotech.accesscontrol.constant.ExceptionMessages;
import com.gotech.accesscontrol.constant.Fields;
import com.gotech.accesscontrol.exception.InvalidUserException;
import com.gotech.accesscontrol.exception.UserAlreadyExistException;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.repository.UserRepo;
import com.gotech.accesscontrol.util.ValidateUser;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.gotech.accesscontrol.constant.LogsMessageConstants.createLog;
import static com.gotech.accesscontrol.constant.LogsMessageConstants.duplicateUserLog;
import static com.gotech.accesscontrol.constant.LogsMessageConstants.invalidUserLog;
import static com.gotech.accesscontrol.constant.LogsMessageConstants.saveLog;
import static com.gotech.accesscontrol.util.CommonUtil.toUser;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger serviceLogger = Log4j2Config.getLoggerVariable("UserServiceImpl");
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ValidateUser validateUser;

    /* takes userRequest & converts it into user
    then saves user into repo & returns success message. */
    @Override
    public Response saveUser(UserRequest userRequest) {
        User user = toUser(userRequest);
        String validate = validateUser.isValid(user);
        if (validate.equals(Fields.valid)) {
            if (validateUser.isUserAlreadyExist(user).equals(Fields.empty)) {
                userRepo.save(user);
                serviceLogger.info(saveLog + user);
            } else {
                UserAlreadyExistException exception =
                        new UserAlreadyExistException(duplicateUserLog + user);
                exception.printStackTrace();
                serviceLogger.error(duplicateUserLog + user);
                return new Response(HttpStatus.CONFLICT.toString(), ExceptionMessages.userAlreadyExist);
            }
        } else {
            InvalidUserException exception = new InvalidUserException(invalidUserLog + user);
            exception.printStackTrace();
            serviceLogger.error(invalidUserLog + user);
            return new Response(HttpStatus.UNPROCESSABLE_ENTITY.toString(), validate);
        }
        return new Response(HttpStatus.CREATED.toString(), createLog);
    }
}