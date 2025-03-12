package com.example.usermanagement.controller;

import com.example.usermanagement.dto.UserRegistrationDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing user-related operations such as registration and retrieval.
 * This controller provides endpoints to register a new user and retrieve a user by their ID.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a UserController with the given UserService.
     *
     * @param userService The UserService instance to manage user-related operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user with the provided registration data.
     *
     * @param userDTO      The UserRegistrationDTO containing user details.
     * @param notification The notification preference (default is "email").
     * @return A ResponseEntity containing the created User object and a status of 201 (CREATED).
     */
    @PostMapping("/createUser")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody UserRegistrationDTO userDTO,
            @RequestParam(name = "notification", defaultValue = "email") String notification) {

        User user = convertToUser(userDTO);
        user.setNotificationPreference(notification);
        User savedUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A ResponseEntity containing the user object if found, otherwise returns a 404 (NOT FOUND) with an error message.
     */
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(user.get());
    }

    /**
     * Converts a UserRegistrationDTO to a User entity.
     *
     * @param dto The UserRegistrationDTO to convert.
     * @return A User entity with the corresponding details from the DTO.
     */
    private User convertToUser(UserRegistrationDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setCountry(dto.getCountry());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        return user;
    }
}
