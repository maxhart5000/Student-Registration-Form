package com.hartcode.registration.service;

import com.hartcode.registration.entity.User;
import com.hartcode.registration.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

// This is a service interface for managing User entities, and it extends UserDetailsService.
public interface UserService extends UserDetailsService {

    // Find a user by their username
    User findByUserName(String userName);

    // Save the user to the database
    void save(WebUser webUser);
}
