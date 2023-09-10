package com.gotech.accesscontrol.util;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.model.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.gotech.accesscontrol.util.UserMapper.toUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
    @Test
    public void testToUser() {
        UserRequest userRequest = UserRequest.builder()
                .firstName("Vinay")
                .lastName("Shinde")
                .mobileNumber("8847592130")
                .emailId("vs@gmail.com")
                .password("vshinde@1234")
                .userType(UserType.BRANCH_MANAGER)
                .build();

        User user = toUser(userRequest);
        assertEquals(user.getFirstName(), userRequest.getFirstName());
        assertEquals(user.getLastName(), userRequest.getLastName());
        assertEquals(user.getMobileNumber(), userRequest.getMobileNumber());
        assertEquals(user.getEmailId(), userRequest.getEmailId());
        assertEquals(user.getPassword(), userRequest.getPassword());
        assertEquals(user.getUserType(), userRequest.getUserType());
    }

    @Test
    public void toUserResponse() {
        User user = User.builder()
                .firstName("Vinay")
                .lastName("Shinde")
                .mobileNumber("8847592130")
                .emailId("vs@gmail.com")
                .password("vshinde@1234")
                .userType(UserType.BRANCH_MANAGER)
                .build();

        UserResponse userResponse = UserMapper.toUserResponse(user);
        assertEquals(user.getFirstName(), userResponse.getFirstName());
        assertEquals(user.getLastName(), userResponse.getLastName());
        assertEquals(user.getMobileNumber(), userResponse.getMobileNumber());
        assertEquals(user.getEmailId(), userResponse.getEmailId());
        assertEquals(user.getPassword(), userResponse.getPassword());
        assertEquals(user.getUserType(), userResponse.getUserType());
    }
}