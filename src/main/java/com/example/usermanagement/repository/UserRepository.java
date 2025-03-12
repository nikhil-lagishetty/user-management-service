package com.example.usermanagement.repository;

import com.example.usermanagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link User} entities.
 * This interface provides CRUD operations for {@link User} entities
 * using Spring Data MongoDB's {@link MongoRepository}.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Finds a user by their ID.
     *
     * @param id the unique identifier of the user
     * @return the user with the given ID, or {@code null} if no user was found
     */
    // MongoRepository provides built-in CRUD methods, including findById, so no need to define find method here.

}
