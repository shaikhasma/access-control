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

import static com.gotech.accesscontrol.constant.Constants.created;
import static com.gotech.accesscontrol.util.UserMapper.toUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void testSaveUser() {
        UserRequest userRequest = UserRequest.builder()
                .firstName("Vinay")
                .lastName("Shinde")
                .mobileNumber("8847592130")
                .emailId("vs@gmail.com")
                .password("vshinde@1234")
                .userType(UserType.BRANCH_MANAGER)
                .build();

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .mobileNumber(userRequest.getMobileNumber())
                .emailId(userRequest.getEmailId())
                .password(userRequest.getPassword())
                .userType(userRequest.getUserType())
                .build();

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
}