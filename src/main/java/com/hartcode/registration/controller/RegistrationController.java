package com.hartcode.registration.controller;

import com.hartcode.registration.entity.User;
import com.hartcode.registration.service.UserService;
import com.hartcode.registration.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class RegistrationController {

    // Create a logger for this controller
    private final Logger logger = Logger.getLogger(getClass().getName());

    // Inject the UserService
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // Initialize a WebDataBinder to preprocess data
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {

        // Create a StringTrimmerEditor to trim whitespace from input strings
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // Register the StringTrimmerEditor to trim strings
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // Handle GET request to show the registration form
    @GetMapping("/home/showRegistrationForm")
    public String showRegistrationForm(Model theModel) {

        // Add a WebUser object to the model as a form backing object
        theModel.addAttribute("webUser", new WebUser());

        // Return the name of the registration form view
        return "registration-form";
    }

    // Handle POST request to process the submitted registration form
    @PostMapping("/home/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("webUser") WebUser webUser,
                                          BindingResult bindingResult,
                                          HttpSession httpSession, Model theModel) {
        String userName = webUser.getUserName();
        logger.info("Processing the registration form for: " + userName);

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }

        // Check if a user with the same username already exists
        User userExists = userService.findByUserName(userName);
        if (userExists != null) {
            // Add a new WebUser and an error message to the model
            theModel.addAttribute("webUser", new WebUser());
            theModel.addAttribute("registrationError", "A user with this username already exists.");

            logger.warning("Username already exists");
            return "registration-form";
        }

        // Save the new user
        userService.save(webUser);

        logger.info("Successfully created a new user: " + userName);

        // Store the user in the session
        httpSession.setAttribute("user", webUser);

        // Return the registration confirmation view
        return "registration-confirmation";
    }
}
