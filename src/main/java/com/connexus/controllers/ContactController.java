package com.connexus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.connexus.entities.Contact;
import com.connexus.entities.User;
import com.connexus.forms.ContactForm;
import com.connexus.helpers.Helper;
import com.connexus.helpers.Message;
import com.connexus.helpers.MessageType;
import com.connexus.services.ContactService;
import com.connexus.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
            Authentication authentication, HttpSession session) {

        // Process the contact form submission
        String username = Helper.getEmailOfLoggedInUser(authentication);

        // Validate Form
        if (result.hasErrors()) {
            session.setAttribute("message",
                    Message.builder().content("Please correct the errors in the form!").type(MessageType.red).build());
            return "user/add_contact";
        }

        // Form to Contact
        User user = userService.getUserByEmail(username);

        // todo Process profile image upload

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        // Saving to database
        contactService.saveContact(contact);
        System.out.println(contactForm);

        // todo Set the contact picture URL

        // Set success message
        session.setAttribute("message",
                Message.builder().content("Contact added successfully!").type(MessageType.green).build());

        return "redirect:/user/contacts/add";
    }

}
