package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Student;

import java.util.List;

// DAO interface - default methods to be implemented in StudentDAOImpl

public interface StudentDAO {
    List<Student> findAll();
    Student findById(int id);
    Student save(Student student);
    void deleteById(int id);
}
