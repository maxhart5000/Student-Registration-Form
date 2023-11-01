package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// This class implements the RoleDao interface for accessing and managing Role data.
@Repository
public class RoleDaoImpl implements RoleDao {

    // Define a field for entityManager to work with the database.
    private final EntityManager entityManager;

    // Constructor to inject the EntityManager.
    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String theRoleName) {
        // Retrieve and read a Role entity from the database using its name.

        // Create a query to retrieve a Role entity by name.
        TypedQuery<Role> theQuery = entityManager.createQuery("from Role where name=:roleName", Role.class);

        // Set the parameter for the query.
        theQuery.setParameter("roleName", theRoleName);

        Role theRole = null;

        try {
            // Attempt to retrieve a single result (Role entity) from the query.
            theRole = theQuery.getSingleResult();
        } catch (Exception e) {
            // If no result is found, catch the exception and set theRole to null.
            theRole = null;
        }

        return theRole;
    }
}
