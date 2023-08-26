package com.gotech.accesscontrol;

import com.gotech.accesscontrol.Entity.User;
import com.gotech.accesscontrol.constant.UserType;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.repository.UserRepo;
import com.gotech.accesscontrol.service.UserService;
import com.gotech.accesscontrol.util.CommonUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;
    @Mock
    private CommonUtil commonUtil;
    @Autowired
    @InjectMocks
    private UserService userService;
    private User user;
    private User savedUser;
    private UserRequest userRequest;
    private String message;
    @BeforeEach
    public void setUp(){
        userRequest=UserRequest.builder()
                .id("e7b1e875-25ab-4d13-9b76-d8bff03921ad")
                .firstName("Vinay")
                .lastName("Shinde")
                .mobileNumber("8847592130")
                .emailId("vs@gmail.com")
                .password("vs@1234")
                .userType(UserType.BRANCH_MANAGER)
                .build();

        user=User.builder()
                .userId(userRequest.getId())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .mobileNumber(userRequest.getMobileNumber())
                .emailId(userRequest.getEmailId())
                .password(userRequest.getPassword())
                .userType(userRequest.getUserType())
                .build();
    }
    @AfterEach
    public void tearDown(){
        userRequest=null;
        user=savedUser=null;
        message=null;
    }
    @Test
    @DisplayName("given userRequest when save then returns success message.")
    public void testSaveUser()throws Exception{
        when(userRepo.save(user)).thenReturn(user);
        when(commonUtil.toUser(userRequest)).thenReturn(user);
        message=userService.saveUser(userRequest);
        savedUser=userRepo.save(user);
        assertEquals(user,savedUser);
        assertEquals("User created successfully. ",message);
    }
}
