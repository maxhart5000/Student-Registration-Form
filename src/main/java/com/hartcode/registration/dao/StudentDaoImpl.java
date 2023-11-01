package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// This class implements the StudentDao interface for accessing and managing Student data.
@Repository
public class StudentDaoImpl implements StudentDao {

    // Define a field for entityManager to work with the database.
    private final EntityManager entityManager;

    // Constructor to inject the EntityManager.
    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {
        // Retrieve a list of all students from the database.

        // Create a query to retrieve all Student entities.
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);

        // Return the list of students obtained from the query.
        return query.getResultList();
    }

    @Override
    public Student findById(int theId) {
        // Retrieve a student by their unique identifier (ID).

        // Use the EntityManager's find method to retrieve a Student by ID.
        return entityManager.find(Student.class, theId);
    }

    @Override
    public Student save(Student theStudent) {
        // Save a student's information in the database.

        // Use the EntityManager's merge method to save or update the Student entity.
        return entityManager.merge(theStudent);
    }

    @Override
    public void deleteById(int theId) {
        // Delete a student by their unique identifier (ID).

        // Use the EntityManager's find method to retrieve the Student by ID.
        Student student = entityManager.find(Student.class, theId);

        // Use the EntityManager's remove method to delete the Student.
        entityManager.remove(student);
    }
}
