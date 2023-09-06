package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.exception.InvalidUserException;
import com.gotech.accesscontrol.exception.UserAlreadyExistException;
import com.gotech.accesscontrol.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.gotech.accesscontrol.constant.Constants.valid;
import static com.gotech.accesscontrol.util.CommonUtil.isValidName;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateUserTest {
    @Mock
    private UserRepo userRepo;
    @Autowired
    @InjectMocks
    private ValidateUser validateUser;
    private User user;
    private String returnMessage = "";

    public void setUp() {
        user = User.builder()
                .firstName("Vinay")
                .lastName("Shinde")
                .mobileNumber("8847592130")
                .emailId("vs@gmail.com")
                .password("vshinde@1234")
                .userType(UserType.BRANCH_MANAGER)
                .build();
    }

    @Test
    public void testIsValidMethodReturnsValid() {
        this.setUp();
        List<User> savedUsers = new ArrayList<>();
        when(userRepo.findUserByEmailAndMobileNumber(anyString(), anyString())).thenReturn(savedUsers);
        savedUsers = userRepo.findUserByEmailAndMobileNumber(user.getEmailId(), user.getMobileNumber());
        boolean checkEmpty = savedUsers.isEmpty();
        assertTrue(checkEmpty);
        returnMessage = valid;
        assertFalse(isEmpty(returnMessage));
        assertFalse(isBlank(returnMessage));
        assertEquals(returnMessage, valid);
    }

    @Test
    public void testIsValidMethodThrowsUserAlreadyExistException() {
        this.setUp();
        List<User> savedUsers = new ArrayList<>();
        savedUsers.add(user);
        when(userRepo.findUserByEmailAndMobileNumber(anyString(), anyString())).thenReturn(savedUsers);
        savedUsers = userRepo.findUserByEmailAndMobileNumber(user.getEmailId(), user.getMobileNumber());
        boolean checkEmpty = savedUsers.isEmpty();
        assertFalse(checkEmpty);
        assertThrows(UserAlreadyExistException.class, () -> validateUser.isValid(user));
    }

    @Test
    public void testIsValidMethodThrowsInvalidUserException() {
        this.setUp();
        List<User> savedUsers = new ArrayList<>();
        when(userRepo.findUserByEmailAndMobileNumber(anyString(), anyString())).thenReturn(savedUsers);
        savedUsers = userRepo.findUserByEmailAndMobileNumber(user.getEmailId(), user.getMobileNumber());
        boolean checkEmpty = savedUsers.isEmpty();
        assertTrue(checkEmpty);
        user.setFirstName(" ");
        assertFalse(isEmpty(user.getFirstName()));
        assertTrue(isBlank(user.getFirstName()));
        assertFalse(isValidName(user.getFirstName()));
        assertThrows(InvalidUserException.class, () -> validateUser.isValid(user));
    }
}