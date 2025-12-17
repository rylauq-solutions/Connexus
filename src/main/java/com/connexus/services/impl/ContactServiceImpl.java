package com.connexus.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        var contactOld = contactRepository.findById(contact.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contact.getId()));

        // Update fields
        contactOld.setName(contact.getName());
        contactOld.setEmail(contact.getEmail());
        contactOld.setPhoneNumber(contact.getPhoneNumber());
        contactOld.setAddress(contact.getAddress());
        contactOld.setDescription(contact.getDescription());
        contactOld.setFavorite(contact.isFavorite());
        contactOld.setWebsiteLink(contact.getWebsiteLink());
        contactOld.setLinkedInLink(contact.getLinkedInLink());
        contactOld.setPicture(contact.getPicture());
        contactOld.setCloudinaryImagepublicId(contact.getCloudinaryImagepublicId());

        return contactRepository.save(contactOld);
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
    public List<Contact> getContactsByUserId(String userId) {
        return contactRepository.findByUserId(userId);
    }

    @Override
    public Page<Contact> getContactsByUser(User user, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepository.findByUser(user, pageable);
    }

    @Override
    public Page<Contact> searchContactsByName(String nameKeyword, int size, int page, String sortBy, String order,
            User user) {
        var pageable = PageRequest.of(page, size,
                order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return contactRepository.findByUserAndNameContaining(user, nameKeyword, pageable);
    }

    @Override
    public Page<Contact> searchContactsByEmail(String emailKeyword, int size, int page, String sortBy, String order,
            User user) {
        var pageable = PageRequest.of(page, size,
                order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return contactRepository.findByUserAndEmailContaining(user, emailKeyword, pageable);
    }

    @Override
    public Page<Contact> searchContactsByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy,
            String order, User user) {
        var pageable = PageRequest.of(page, size,
                order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return contactRepository.findByUserAndPhoneNumberContaining(user, phoneNumberKeyword, pageable);
    }

}
