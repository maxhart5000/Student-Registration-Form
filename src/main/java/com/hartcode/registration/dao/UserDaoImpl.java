package com.hartcode.registration.dao;

import com.hartcode.registration.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// This class implements the UserDao interface for accessing and managing User data.
@Repository
public class UserDaoImpl implements UserDao {

    // Define a field for entityManager to work with the database.
    private final EntityManager entityManager;

    // Constructor to inject the EntityManager.
    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String theUserName) {
        // Retrieve a user by their username from the database.

        // Create a query to retrieve a User entity by username.
        TypedQuery<User> theQuery = entityManager.createQuery("from User where userName=:uName", User.class);

        // Set the parameter for the query.
        theQuery.setParameter("uName", theUserName);

        User theUser = null;

        try {
            // Attempt to retrieve a single result (User entity) from the query.
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            // If no result is found, catch the exception and set theUser to null.
            theUser = null;
        }

        return theUser;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }
}
