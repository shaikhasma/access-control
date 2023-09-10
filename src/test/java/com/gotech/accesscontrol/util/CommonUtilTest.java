package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.exception.InvalidUserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.gotech.accesscontrol.constant.Constants.emailPattern;
import static com.gotech.accesscontrol.constant.Constants.mobileNoPattern;
import static com.gotech.accesscontrol.constant.Constants.namePattern;
import static com.gotech.accesscontrol.constant.Constants.passwordPattern;
import static com.gotech.accesscontrol.util.CommonUtil.checkValidEmailId;
import static com.gotech.accesscontrol.util.CommonUtil.checkValidMobileNumber;
import static com.gotech.accesscontrol.util.CommonUtil.checkValidName;
import static com.gotech.accesscontrol.util.CommonUtil.checkValidPassword;
import static com.gotech.accesscontrol.util.CommonUtil.checkValidUserType;
import static com.gotech.accesscontrol.util.CommonUtil.isValidEmailId;
import static com.gotech.accesscontrol.util.CommonUtil.isValidFirstName;
import static com.gotech.accesscontrol.util.CommonUtil.isValidLastName;
import static com.gotech.accesscontrol.util.CommonUtil.isValidMobileNumber;
import static com.gotech.accesscontrol.util.CommonUtil.isValidPassword;
import static com.gotech.accesscontrol.util.CommonUtil.isValidUserType;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CommonUtilTest {
    private final User user = User.builder()
            .firstName("Vinay")
            .lastName("Shinde")
            .mobileNumber("8847592130")
            .emailId("vs@gmail.com")
            .password("vshinde@1234")
            .userType(UserType.BRANCH_MANAGER)
            .build();

    private final User user1 = User.builder()
            .firstName(" ")
            .lastName("")
            .mobileNumber("jkhgf")
            .emailId("vsgmail.com")
            .password("vs@1234")
            .userType(UserType.BRANCH_MANAGER)
            .build();

    @Test
    public void testCheckValidEmailId() {
        boolean check = checkValidEmailId(user.getEmailId());
        assertTrue(user.getEmailId().matches(emailPattern));
        assertTrue(check);
    }

    @Test
    public void testCheckValidFirstName() {
        boolean check = checkValidName(user.getFirstName());
        assertTrue(user.getFirstName().matches(namePattern));
        assertTrue(check);
    }

    @Test
    public void testCheckValidLastName() {
        boolean check = checkValidName(user.getLastName());
        assertTrue(user.getLastName().matches(namePattern));
        assertTrue(check);
    }

    @Test
    public void testCheckValidMobileNumber() {
        boolean check = checkValidMobileNumber(user.getMobileNumber());
        assertTrue(user.getMobileNumber().matches(mobileNoPattern));
        assertTrue(check);
    }

    @Test
    public void testCheckValidPassword() {
        boolean check = checkValidPassword(user.getPassword());
        assertTrue(user.getPassword().matches(passwordPattern));
        assertTrue(check);
    }

    @Test
    public void testCheckValidUserType() {
        boolean check = checkValidUserType(user.getUserType().toString());
        assertEquals(user.getUserType(), UserType.BRANCH_MANAGER);
        assertTrue(check);
    }

    @Test
    public void testIsValidFirstName() {
        boolean check = isValidFirstName(user);
        assertFalse(isEmpty(user.getFirstName()));
        assertFalse(isBlank(user.getFirstName()));
        assertTrue(checkValidName(user.getFirstName()));
        assertTrue(check);
    }

    @Test
    public void testIsValidLastName() {
        boolean check = isValidLastName(user);
        assertFalse(isEmpty(user.getLastName()));
        assertFalse(isBlank(user.getLastName()));
        assertTrue(checkValidName(user.getLastName()));
        assertTrue(check);
    }

    @Test
    public void testIsValidMobileNumber() {
        boolean check = isValidMobileNumber(user);
        assertFalse(isEmpty(user.getMobileNumber()));
        assertFalse(isBlank(user.getMobileNumber()));
        assertTrue(checkValidMobileNumber(user.getMobileNumber()));
        assertTrue(check);
    }

    @Test
    public void testIsValidEmailId() {
        boolean check = isValidEmailId(user);
        assertFalse(isEmpty(user.getEmailId()));
        assertFalse(isBlank(user.getEmailId()));
        assertTrue(checkValidEmailId(user.getEmailId()));
        assertTrue(check);
    }

    @Test
    public void testIsValidPassword() {
        boolean check = isValidPassword(user);
        assertFalse(isEmpty(user.getPassword()));
        assertFalse(isBlank(user.getPassword()));
        assertTrue(checkValidPassword(user.getPassword()));
        assertTrue(check);
    }

    @Test
    public void testValidUserType() {
        boolean check = isValidUserType(user);
        assertFalse(isEmpty(user.getUserType().toString()));
        assertFalse(isBlank(user.getUserType().toString()));
        assertTrue(checkValidUserType(user.getUserType().toString()));
        assertTrue(check);
    }

    @Test
    public void testIsValidFirstNameThrowsException() {
        assertFalse(isEmpty(user1.getFirstName()));
        assertTrue(isBlank(user1.getFirstName()));
        assertFalse(checkValidName(user1.getFirstName()));
        assertThrows(InvalidUserException.class, () -> isValidFirstName(user1));
    }

    @Test
    public void testIsValidLastNameThrowsException() {
        assertTrue(isEmpty(user1.getLastName()));
        assertTrue(isBlank(user1.getLastName()));
        assertFalse(checkValidName(user1.getLastName()));
        assertThrows(InvalidUserException.class, () -> isValidLastName(user1));
    }

    @Test
    public void testIsValidMobileNumberThrowsException() {
        assertFalse(isEmpty(user1.getMobileNumber()));
        assertFalse(isBlank(user1.getMobileNumber()));
        assertFalse(checkValidMobileNumber(user1.getMobileNumber()));
        assertThrows(InvalidUserException.class, () -> isValidMobileNumber(user1));
    }

    @Test
    public void testIsValidEmailIdThrowsException() {
        assertFalse(isEmpty(user1.getEmailId()));
        assertFalse(isBlank(user1.getEmailId()));
        assertFalse(checkValidEmailId(user1.getEmailId()));
        assertThrows(InvalidUserException.class, () -> isValidEmailId(user1));
    }

    @Test
    public void testIsValidPasswordThrowsException() {
        assertFalse(isEmpty(user1.getPassword()));
        assertFalse(isBlank(user1.getPassword()));
        assertFalse(checkValidPassword(user1.getPassword()));
        assertThrows(InvalidUserException.class, () -> isValidPassword(user1));
    }
}