package com.connexus.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.connexus.entities.Contact;
import com.connexus.entities.User;

public interface ContactService {
    // Save Contacts
    Contact saveContact(Contact contact);

    // Update Contacts
    Contact updateContact(Contact contact);

    // Get Contacts
    List<Contact> getAllContacts();

    // Get Contact by ID
    Contact getContactById(String contactId);

    // Delete Contact
    void deleteContact(String contactId);

    // Search Contacts
    Page<Contact> searchContactsByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchContactsByEmail(String emailKeyword, int size, int page, String sortBy, String order,
            User user);

    Page<Contact> searchContactsByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy,
            String order, User user);

    // Get Contacts by User ID
    List<Contact> getContactsByUserId(String userId);

    // Get Contacts by User
    Page<Contact> getContactsByUser(User user, int page, int size, String sortField, String direction);
}
