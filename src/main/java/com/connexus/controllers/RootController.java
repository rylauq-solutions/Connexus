package com.connexus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.connexus.entities.User;
import com.connexus.helpers.Helper;
import com.connexus.services.UserService;

@ControllerAdvice
public class RootController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }

        System.out.println("Adding logged-in user information to the model");
        String name = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(name);
        System.out.println(user);

        logger.info("User Logged-in Name: " + user.getName());
        logger.info("User Logged-in Email: " + user.getEmail());
        model.addAttribute("loggedInUser", user);
    }

}
