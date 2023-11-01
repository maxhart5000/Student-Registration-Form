package com.hartcode.registration.service;

import com.hartcode.registration.entity.Student;

import java.util.List;

// This is a service interface for managing Student entities.
public interface StudentService {

    // Retrieve a list of all students in the system.
    public List<Student> findAll();

    // Find a student by their unique ID.
    public Student findById(int id);

    // Save a student's information or update an existing student.
    public Student save(Student student);

    // Delete a student by their unique ID.
    public void deleteById(int id);
}
