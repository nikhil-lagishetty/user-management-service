package com.example.usermanagement.controller;


import com.example.usermanagement.dto.UserRegistrationDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {



    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> registerUser(
            @Valid @RequestBody UserRegistrationDTO userDTO,
            @RequestParam(name = "notification", defaultValue = "email") String notification) {
        User user = convertToUser(userDTO);
        user.setNotificationPreference(notification);

        User savedUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    private User convertToUser(UserRegistrationDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setCountry(dto.getCountry());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        return user;
    }


    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}

