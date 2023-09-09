package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;

public interface UserService {
    Response saveUser(UserRequest userRequest);

    Response buildSuccessResponse(String status, String message);

    Response buildFailureResponse(String status, String message);
}
