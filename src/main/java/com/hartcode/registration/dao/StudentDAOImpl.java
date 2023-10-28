package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // Define a field for the entityManager
    private EntityManager entityManager;

    // Set up constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {

        // Create a query
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);

        // Execute the query and get result list
        List<Student> students = query.getResultList();

        // Return the student list
        return students;
    }

    @Override
    public Student findById(int id) {

        // Get the student by id
        Student student = entityManager.find(Student.class, id);

        // Return the student
        return student;
    }

    @Override
    public Student save(Student student) {

        // Save the student
        Student theStudent = entityManager.merge(student);

        // Return the student from db
        return theStudent;
    }

    @Override
    public void deleteById(int id) {

        // Find the student by id
        Student student = entityManager.find(Student.class, id);

        // Delete the student from db
        entityManager.remove(student);
    }
}
