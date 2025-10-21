package com.connexus.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connexus.entities.Contact;
import com.connexus.entities.User;
import com.connexus.helpers.ResourceNotFoundException;
import com.connexus.repositories.ContactRepository;
import com.connexus.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(String contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));
    }

    @Override
    public void deleteContact(String contactId) {
        var contact = getContactById(contactId);
        contactRepository.delete(contact);
    }

    @Override
    public List<Contact> searchContacts(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchContacts'");
    }

    @Override
    public List<Contact> getContactsByUserId(String userId) {
        return contactRepository.findByUserId(userId);
    }

    @Override
    public List<Contact> getContactsByUser(User user) {
        return contactRepository.findByUser(user);
    }

}
