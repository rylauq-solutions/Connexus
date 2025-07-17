package com.connexus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    
    
    @RequestMapping("/add")
    public String addContactView() {
        return "user/add_contact";
    }

}
