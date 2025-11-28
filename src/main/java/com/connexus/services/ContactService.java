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
    List<Contact> searchContacts(String name, String email, String phoneNumber);

    // Get Contacts by User ID
    List<Contact> getContactsByUserId(String userId);

    // Get Contacts by User
    Page<Contact> getContactsByUser(User user, int page, int size, String sortField, String direction);
}
