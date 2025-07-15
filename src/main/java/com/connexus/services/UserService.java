package com.connexus.services;

import java.util.List;
import java.util.Optional;

import com.connexus.entities.User;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updatUser(User user);

    void deleteUser(String id);

    boolean isUserExists(String id);

    boolean isUserExistsByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    // Additional methods can be defined as needed for user management
}
