package com.example.usermanagement.controller;

import com.example.usermanagement.dto.UserRegistrationDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testRegisterUser_Success() throws Exception {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("John Doe", 25, "USA", "john@example.com", "1234567890");
        User user = new User();
        user.setId("1");
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setCountry(userDTO.getCountry());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setNotificationPreference("email");

        when(userService.registerUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
                        .param("notification", "email"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testRegisterUser_InvalidInput() throws Exception {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("", 15, "USA", "invalidemail", "");

        mockMvc.perform(post("/users/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetUserById_Success() throws Exception {
        User user = new User();
        user.setId("1");
        user.setName("John Doe");
        user.setAge(25);
        user.setCountry("USA");
        user.setEmail("john@example.com");

        when(userService.getUserById("1")).thenReturn(user);

        mockMvc.perform(get("/users/getUserById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        when(userService.getUserById("99")).thenReturn(null);

        mockMvc.perform(get("/users/getUserById/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
