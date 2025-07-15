package com.connexus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.connexus.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // * User Dashboard Page
    @RequestMapping("/dashboard")
    public String userDashboard() {
        System.out.println("User Dashboard Page");
        return "user/dashboard";
    }

    // * User Profile Page
    @RequestMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        System.out.println("User Profile Page");
        return "user/profile";
    }

    // * User Add Contacts

    // * User View Contacts

    // * User Edit Contacts

    // * User Delete Contacts

    // * User Search Contacts
}
