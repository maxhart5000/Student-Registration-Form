package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Student;

import java.util.List;

// This interface defines the contract for accessing and managing Student data.
public interface StudentDao {

    // Retrieve a list of all students.
    public List<Student> findAll();

    // Retrieve a student by their unique identifier (ID).
    public Student findById(int theId);

    // Save a student's information in the data store. Returns the saved student.
    public Student save(Student theEmployee);

    // Delete a student by their unique identifier (ID).
    public void deleteById(int theId);
}
