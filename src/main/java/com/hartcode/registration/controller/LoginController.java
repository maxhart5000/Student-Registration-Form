package com.hartcode.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/home/showLoginPage")
    public String showLoginPage() {
        // This method handles the GET request for showing the login page.
        // It returns the "login-page" template.
        return "login-page";
    }

    @GetMapping("/home/access-denied")
    public String showAccessDeniedPage() {
        // This method handles the GET request for showing the access denied page.
        // It returns the "access-denied" template.
        return "access-denied";
    }
}
