package com.connexus.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.connexus.entities.Contact;
import com.connexus.entities.User;
import com.connexus.forms.ContactForm;
import com.connexus.helpers.AppConstants;
import com.connexus.helpers.Helper;
import com.connexus.helpers.Message;
import com.connexus.helpers.MessageType;
import com.connexus.services.ContactService;
import com.connexus.services.ImageService;
import com.connexus.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private static Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

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

        // Process profile image upload ONLY if file is provided
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String fileName = UUID.randomUUID().toString();
            String fileURL = imageService.uploadContactImage(contactForm.getContactImage(), fileName);
            contact.setPicture(fileURL);
            contact.setCloudinaryImagepublicId(fileName);
            logger.info("Contact image uploaded: {}", fileURL);
        } else {
            // Set default image if no image uploaded
            contact.setPicture("https://i.pinimg.com/736x/f2/d6/d2/f2d6d26e83996554e9334afd3c730dbe.jpg");
            logger.info("No image provided, using default image");
        }

        // Saving to database
        contactService.saveContact(contact);
        System.out.println(contactForm);

        // Set success message
        session.setAttribute("message",
                Message.builder().content("Contact added successfully!").type(MessageType.green).build());

        return "redirect:/user/contacts/add";
    }

    @RequestMapping
    public String viewContacts(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {
        // Load all the contacts of user
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);
        Page<Contact> pageContact = contactService.getContactsByUser(user, page, size, sortBy, direction);
        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        return "user/contacts";
    }

    // Search handler
    @RequestMapping("/search")
    public String searchContacts(
            @RequestParam("field") String field,
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication) {

        logger.info("Field: {}", field);
        logger.info("Keyword: {}", keyword);

        User user = userService.getUserByEmail(
                Helper.getEmailOfLoggedInUser(authentication));

        Page<Contact> pageContact;
        if (field.equalsIgnoreCase("name")) {
            pageContact = contactService.searchContactsByName(
                    keyword, size, page, sortBy, direction, user);
        } else if (field.equalsIgnoreCase("email")) {
            pageContact = contactService.searchContactsByEmail(
                    keyword, size, page, sortBy, direction, user);
        } else if (field.equalsIgnoreCase("phone") || field.equalsIgnoreCase("phoneNumber")) {
            pageContact = contactService.searchContactsByPhoneNumber(
                    keyword, size, page, sortBy, direction, user);
        } else {
            pageContact = Page.empty();
        }

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", size);
        model.addAttribute("searchField", field);
        model.addAttribute("searchKeyword", keyword);

        return "user/search";
    }

    // Delete Contact
    @RequestMapping("/delete/{contactId}")
    public String deleteContact(
            @PathVariable("contactId") String contactId,
            HttpSession session) {

        contactService.deleteContact(contactId);

        session.setAttribute("message",
                Message.builder()
                        .content("Contact deleted successfully!")
                        .type(MessageType.green)
                        .build());

        logger.info("Contact deleted with id: {}", contactId);

        return "redirect:/user/contacts";
    }

    // View to Update Contact
    @RequestMapping("/update/{contactId}")
    public String updateContactFormView(
            @PathVariable("contactId") String contactId,
            Model model) {

        var contact = contactService.getContactById(contactId);

        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setLinkedInLink(contact.getLinkedInLink());
        contactForm.setPicture(contact.getPicture());

        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId", contactId);

        return "user/update_contact";
    }

    // Process Update Contact
    @PostMapping("/update/{contactId}")
    public String updateContact(
            @PathVariable("contactId") String contactId,
            @Valid @ModelAttribute ContactForm contactForm,
            BindingResult result,
            Model model,
            HttpSession session) {

        // Validate Form
        if (result.hasErrors()) {
            session.setAttribute("message",
                    Message.builder()
                            .content("Please correct the errors in the form!")
                            .type(MessageType.red)
                            .build());
            model.addAttribute("contactId", contactId);

            // Preserve the existing picture URL on validation error
            var contact = contactService.getContactById(contactId);
            contactForm.setPicture(contact.getPicture());

            return "user/update_contact";
        }

        // Get existing contact
        var contact = contactService.getContactById(contactId);

        // Update contact fields
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setFavorite(contactForm.isFavorite());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedInLink());

        // Process image upload if new file is provided
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String fileName = UUID.randomUUID().toString();
            String fileURL = imageService.uploadContactImage(contactForm.getContactImage(), fileName);
            contact.setPicture(fileURL);
            contact.setCloudinaryImagepublicId(fileName);
            logger.info("Contact image updated: {}", fileURL);
        }
        // If no new image, keep the existing one (don't change picture field)

        // Save updated contact
        contactService.updateContact(contact);

        session.setAttribute("message",
                Message.builder()
                        .content("Contact updated successfully!")
                        .type(MessageType.green)
                        .build());

        logger.info("Contact updated with id: {}", contactId);

        return "redirect:/user/contacts";
    }

}
