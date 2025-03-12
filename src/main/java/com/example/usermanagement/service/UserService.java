package com.example.usermanagement.service;

import com.example.usermanagement.exception.DuplicateEmailException;
import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

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
     * Registers a new user in the system.
     * Checks if the email already exists before saving.
     *
     * @param user The user entity to be registered.
     * @return The saved user entity.
     * @throws DuplicateEmailException If the email is already registered.
     */
    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new DuplicateEmailException("Email already exists: " + user.getEmail());
        }

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
