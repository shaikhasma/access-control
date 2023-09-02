package com.gotech.accesscontrol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotech.accesscontrol.constant.enums.UserType;
import com.gotech.accesscontrol.controller.UserController;
import com.gotech.accesscontrol.model.dto.Response;
import com.gotech.accesscontrol.model.dto.UserRequest;
import com.gotech.accesscontrol.service.UserServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private UserController userController;
    private UserRequest userRequest;
    private Response response;

    public void setUp() {
        userRequest = UserRequest.builder()
                .firstName("Vinay")
                .lastName("Shinde")
                .mobileNumber("8847592130")
                .emailId("vs@gmail.com")
                .password("vs@1234")
                .userType(UserType.BRANCH_MANAGER)
                .build();

        response = Response.builder().statusMessage("user created successfully. ")
                .statusCode(HttpStatus.OK.toString())
                .build();

        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    public void tearDown() {
        userRequest = null;
        response = null;
    }

    @Test
    public void testPostMappingSaveMethod() throws Exception {
        this.setUp();
        String jsonString = "{}";
        when(userService.saveUser(any(UserRequest.class)))
                .thenReturn(response);
        try {
            jsonString = objectMapper.writeValueAsString(userRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        mockMvc.perform(
                        post("/user/register").contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status-code", CoreMatchers.is(response.getStatusCode())))
                .andExpect(jsonPath("$.status-message").value(response.getStatusMessage()))
                .andDo(print());
        verify(userService, times(1)).saveUser(any(UserRequest.class));
        this.tearDown();
    }
}