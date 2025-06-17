package com.connexus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.connexus.entities.User;
import com.connexus.forms.UserForm;
import com.connexus.services.UserService;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home Page Handler");
        model.addAttribute("name", "Connexus Application");
        model.addAttribute("message", "Welcome to the Home Page!");
        model.addAttribute("githubRepo", "https://github.com/er-YOUR_NEW_NAME/Connexus");
        return "home";
    }

    // About Route
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        System.out.println("About Page Loading");
        return "about";
    }

    // Services Route
    @RequestMapping("/services")
    public String servicesPage(Model model) {
        System.out.println("Services Page Loading");
        return "services";
    }

    // Contacts Route
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // Login Route
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    // Signup Route
    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);
        return "register";
    }

    // Processing Register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm) {
        System.out.println("Processing Registration");

        // Fetch form data

        // Validate form data

        // TODO: Add logic to handle registration

        // *Saving user to database
        // UserForm ===> User
        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .about(userForm.getAbout())
                .phoneNumber(userForm.getPhoneNumber())
                .profilePic("/images/Connexus-Monogram.png")
                .build();

        User savedUser = userService.saveUser(user);

        System.out.println("User saved: " + savedUser);

        // * Redirect to login page after successful registration
        return "redirect:/register";
    }
}
