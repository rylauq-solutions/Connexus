package com.connexus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connexus.entities.Contact;
import com.connexus.entities.User;

public interface ContactRepository extends JpaRepository<Contact, String> {

    // Get contact by user
    List<Contact> findByUser(User user); // * Custom finder method

    // Get contact by user ID
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId); // * Custom query method
}
