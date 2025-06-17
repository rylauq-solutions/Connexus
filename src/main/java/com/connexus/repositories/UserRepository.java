package com.connexus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connexus.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    // Custom query methods can be defined here if needed
    // For example, to find a user by email:
    // Optional<User> findByEmail(String email);

    Optional<User> findByEmail(String email);
}
