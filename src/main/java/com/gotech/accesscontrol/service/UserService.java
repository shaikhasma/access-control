package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;
import org.apache.catalina.LifecycleState;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Response saveUser(UserRequest userRequest);

    public List<User> getAll();

    User getLoginData(String identifier, String password);
}
