package com.hartcode.registration.dao;

import com.hartcode.registration.entity.User;

// This interface defines the contract for accessing and managing User data.
public interface UserDao {

    // Find a user by their username.
    // Returns the User entity that matches the specified username, or null if not found.
    public User findByUserName(String userName);

    // Saves the User entity
    public void save(User user);
}
