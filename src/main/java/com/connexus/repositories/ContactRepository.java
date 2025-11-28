package com.connexus.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connexus.entities.Contact;
import com.connexus.entities.User;

public interface ContactRepository extends JpaRepository<Contact, String> {

    // Get contact by user
    Page<Contact> findByUser(User user, Pageable pageable); // * Custom finder method

    // Get contact by user ID
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId); // * Custom query method

    // Search contacts by name
    Page<Contact> findByUserAndNameContaining(User user, String nameKeyword, Pageable pageable);

    // Search contacts by email
    Page<Contact> findByUserAndEmailContaining(User user, String emailKeyword, Pageable pageable);

    // Search contacts by phone number
    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phoneNumberKeyword, Pageable pageable);
}
