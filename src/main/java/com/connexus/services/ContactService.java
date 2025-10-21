package com.connexus.services;

import java.util.List;

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
    List<Contact> getContactsByUser(User user);
}
