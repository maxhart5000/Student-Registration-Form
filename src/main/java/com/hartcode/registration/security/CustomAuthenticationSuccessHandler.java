package com.hartcode.registration.security;

import com.hartcode.registration.entity.User;
import com.hartcode.registration.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // Inject the UserService
    private UserService userService;

    @Autowired
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // Get the username from the authentication object
        String userName = authentication.getName();

        // Find the User entity in the database by the username
        User user = userService.findByUserName(userName);

        // Get the current session or create a new one
        HttpSession session = request.getSession();

        // Set the user as an attribute in the session
        session.setAttribute("user", user);

        // Redirect the user to the specified URL after successful authentication
        response.sendRedirect(request.getContextPath() + "/home/directory");
    }
}
