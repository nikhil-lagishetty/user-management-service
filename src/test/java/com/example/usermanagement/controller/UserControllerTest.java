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

/**
 * Unit test class for {@link UserController}.
 * This class tests the endpoints defined in the UserController class to ensure that the user-related operations,
 * such as registering a user and fetching a user by ID, work as expected.
 * It uses the {@link MockMvc} framework to perform mock HTTP requests and validate the responses.
 */
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    /**
     * Initializes the test setup before each test case.
     * Sets up the mock service, initializes MockMvc for testing, and prepares the ObjectMapper.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Tests the user registration endpoint for a successful scenario.
     * Verifies that when a valid user DTO is provided, the user is successfully registered,
     * and the response contains the correct user details with status 201 Created.
     *
     * @throws Exception If any exception occurs during the test execution.
     */
    @Test
    void testRegisterUser_Success() throws Exception {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("John Doe", 25, "france", "john@example.com", "1234567890");
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

    /**
     * Tests the user registration endpoint for an invalid input scenario.
     * Verifies that when an invalid user DTO is provided (with empty name and invalid email),
     * the response returns a status 400 Bad Request.
     *
     * @throws Exception If any exception occurs during the test execution.
     */
    @Test
    void testRegisterUser_InvalidInput() throws Exception {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("", 15, "USA", "invalidemail", "");

        mockMvc.perform(post("/users/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Tests the "getUserById" endpoint for a successful scenario.
     * Verifies that when a valid user ID is provided, the correct user details are returned with a status 200 OK.
     *
     * @throws Exception If any exception occurs during the test execution.
     */
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

    /**
     * Tests the "getUserById" endpoint when a user is not found.
     * Verifies that when a non-existent user ID is provided, the response returns status 404 Not Found with a suitable message.
     *
     * @throws Exception If any exception occurs during the test execution.
     */
    @Test
    void testGetUserById_NotFound() throws Exception {
        when(userService.getUserById("99")).thenReturn(null);

        mockMvc.perform(get("/users/getUserById/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
