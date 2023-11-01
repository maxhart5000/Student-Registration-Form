package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Role;

// This interface defines the contract for accessing and managing Role data.
public interface RoleDao {

    // Find a role by its name
    // Returns the Role entity that matches the specified name, or null if not found.
    public Role findRoleByName(String theRoleName);
}
