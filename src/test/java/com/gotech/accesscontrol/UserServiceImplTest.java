package com.gotech.accesscontrol;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.exception.InvalidUserException;
import com.gotech.accesscontrol.exception.UserAlreadyExistException;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.repository.UserRepo;
import com.gotech.accesscontrol.service.UserServiceImpl;
import com.gotech.accesscontrol.util.ValidateUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static com.gotech.accesscontrol.constant.Constants.empty;
import static com.gotech.accesscontrol.constant.Constants.invalidLastName;
import static com.gotech.accesscontrol.constant.Constants.notEmpty;
import static com.gotech.accesscontrol.constant.Constants.valid;
import static com.gotech.accesscontrol.constants.TestConstants.duplicateTest;
import static com.gotech.accesscontrol.constants.TestConstants.invalidTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;
    @Mock
    private ValidateUser validateUser;
    @Autowired
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    private User user;
    private User savedUser;
    private UserRequest userRequest;
    private String message;
    private Response successResponse;

    public void setUp() {
        userRequest = UserRequest.builder()
                .firstName("Vinay")
                .lastName("Shinde")
                .mobileNumber("8847592130")
                .emailId("vs@gmail.com")
                .password("vs@1234")
                .userType(UserType.BRANCH_MANAGER)
                .build();

        user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .mobileNumber(userRequest.getMobileNumber())
                .emailId(userRequest.getEmailId())
                .password(userRequest.getPassword())
                .userType(userRequest.getUserType())
                .build();

        successResponse = Response.builder()
                .statusCode(HttpStatus.CREATED.toString())
                .statusMessage("user created successfully. ")
                .build();
    }

    public void tearDown() {
        userRequest = null;
        user = savedUser = null;
        message = null;
        successResponse = null;
    }

    @Test
    public void testSaveUser() {
        this.setUp();
        when(userRepo.save(user)).thenReturn(user);
        when(validateUser.isValid(any(User.class))).thenReturn(valid);
        when(validateUser.isUserAlreadyExist(any())).thenReturn(empty);
        Response response = userServiceImpl.saveUser(userRequest);
        assertEquals(response, successResponse);
        verify(userRepo, times(1)).save(any());
        this.tearDown();
    }

    @Test
    public void testSaveDuplicateUser() {
        this.setUp();
        List<User> savedUsers = new ArrayList<>();
        savedUsers.add(user);
        when(validateUser.isValid(any(User.class))).thenReturn(valid);
        lenient().when(userRepo.findUserByEmailAndMobileNumber(user.getEmailId(), user.getMobileNumber()))
                .thenReturn(savedUsers);
        when(validateUser.isUserAlreadyExist(any())).thenReturn(notEmpty);
        Exception ex = assertThrows(UserAlreadyExistException.class, () -> userServiceImpl.saveUser(userRequest));
        assertEquals(duplicateTest, ex.getMessage());
        this.tearDown();
    }

    @Test
    public void testSaveInvalidUser() {
        this.setUp();
        user.setLastName(" ");
        when(validateUser.isValid(any(User.class))).thenReturn(invalidLastName);
        Exception ex = assertThrows(InvalidUserException.class, () -> userServiceImpl.saveUser(userRequest));
        assertEquals(invalidTest, ex.getMessage());
        this.tearDown();
    }
}
