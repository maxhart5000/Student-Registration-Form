package com.hartcode.registration.service;

import com.hartcode.registration.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

// This is a service interface for managing User entities, and it extends UserDetailsService.
public interface UserService extends UserDetailsService {

    // Find a user by their username.
    public User findByUserName(String userName);
}
