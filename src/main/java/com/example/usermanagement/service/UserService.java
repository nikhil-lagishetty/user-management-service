package com.example.usermanagement.service;

import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service class responsible for business logic related to {@link User} entities.
 * This class interacts with the {@link UserRepository} to perform CRUD operations
 * and handle user-specific business logic.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new {@link UserService} instance with the given {@link UserRepository}.
     *
     * @param userRepository the repository to interact with user data
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user by saving the provided {@link User} object to the database.
     * The user's registration date is set to the current time before saving.
     *
     * @param user the user to be registered
     * @return the saved {@link User} object
     */
    public User registerUser(User user) {
        user.setRegistrationDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their unique identifier.
     * If no user is found, a {@link UserNotFoundException} is thrown.
     *
     * @param id the unique identifier of the user
     * @return the {@link User} object with the given ID
     * @throws UserNotFoundException if no user is found with the given ID
     */
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }
}
