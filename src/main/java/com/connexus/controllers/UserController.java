package com.connexus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // * User Dashboard Page
    @PostMapping("/dashboard")
    public String userDashboard() {
        System.out.println("User Dashboard Page");
        return "user/dashboard";
    }

    // * User Profile Page
    @GetMapping("/profile")
    public String userProfile() {
        System.out.println("User Profile Page");
        return "user/profile";
    }

    // * User Add Contacts

    // * User View Contacts

    // * User Edit Contacts

    // * User Delete Contacts

    // * User Search Contacts
}
