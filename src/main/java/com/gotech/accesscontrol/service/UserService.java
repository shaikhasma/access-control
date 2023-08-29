package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;

public interface UserService {
    public Response saveUser(UserRequest userRequest);
}
