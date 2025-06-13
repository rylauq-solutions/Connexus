package com.connexus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

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
    public String register() {
        return new String("register");
    }
}
