package com.gotech.accesscontrol.service;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static com.gotech.accesscontrol.constant.Constants.created;
import static com.gotech.accesscontrol.constant.Constants.duplicate;
import static com.gotech.accesscontrol.constant.Constants.invalidUser;
import static com.gotech.accesscontrol.util.UserMapper.toUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    private final UserRequest userRequest = UserRequest.builder()
            .firstName("Vinay")
            .lastName("Shinde")
            .mobileNumber("8847592130")
            .emailId("vs@gmail.com")
            .password("vshinde@1234")
            .userType(UserType.BRANCH_MANAGER)
            .build();

    private final User user = User.builder()
            .firstName(userRequest.getFirstName())
            .lastName(userRequest.getLastName())
            .mobileNumber(userRequest.getMobileNumber())
            .emailId(userRequest.getEmailId())
            .password(userRequest.getPassword())
            .userType(userRequest.getUserType())
            .build();

    @Test
    public void testSaveUser() {
        Response successResponse = Response.builder()
                .statusCode(HttpStatus.CREATED.toString())
                .statusMessage(created + user)
                .build();

        User user1 = toUser(userRequest);
        when(userRepo.save(user)).thenReturn(user);
        Response response = userServiceImpl.saveUser(userRequest);
        assertEquals(user, user1);
        assertEquals(response, successResponse);
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    public void testSaveDuplicateUser() {
        Response failureResponse = Response.builder()
                .statusCode(HttpStatus.CONFLICT.toString())
                .statusMessage(duplicate + user)
                .build();

        List<User> savedUsers = new ArrayList<>();
        savedUsers.add(user);
        when(userRepo.findUserByEmailAndMobileNumber(anyString(), anyString())).thenReturn(savedUsers);
        savedUsers = userRepo.findUserByEmailAndMobileNumber(user.getEmailId(), user.getMobileNumber());
        User user2 = savedUsers.get(0);
        User user1 = toUser(userRequest);
        Response response = userServiceImpl.saveUser(userRequest);
        assertEquals(user, user1);
        assertEquals(user, user2);
        assertEquals(response, failureResponse);
    }

    @Test
    public void testSaveInvalidUser() {
        UserRequest testInvalidUser = UserRequest.builder()
                .firstName("Vinay")
                .lastName("Shinde")
                .mobileNumber("8847592130")
                .emailId("vsgmail.com")
                .password("vshinde@1234")
                .userType(UserType.BRANCH_MANAGER)
                .build();

        User user1 = toUser(testInvalidUser);

        Response failureResponse = Response.builder()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .statusMessage(invalidUser + user1)
                .build();

        Response response = userServiceImpl.saveUser(testInvalidUser);
        assertEquals(user1.getFirstName(), testInvalidUser.getFirstName());
        assertEquals(user1.getLastName(), testInvalidUser.getLastName());
        assertEquals(user1.getMobileNumber(), testInvalidUser.getMobileNumber());
        assertEquals(user1.getEmailId(), testInvalidUser.getEmailId());
        assertEquals(user1.getPassword(), testInvalidUser.getPassword());
        assertEquals(user1.getUserType(), testInvalidUser.getUserType());
        assertEquals(response, failureResponse);
    }
}