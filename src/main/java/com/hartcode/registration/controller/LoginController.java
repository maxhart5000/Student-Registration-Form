package com.hartcode.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/home/showLoginPage")
    public String showLoginPage() {
        return "login-page";
    }

    @GetMapping("/home/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }
}
