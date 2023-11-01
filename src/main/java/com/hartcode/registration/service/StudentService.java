package com.hartcode.registration.service;

import com.hartcode.registration.entity.Student;

import java.util.List;

// service interface - default methods to be implemented in StudentServiceImpl
// Creates a bridge between sql db (in this case DAO) and Controller. Eliminates direct access between the two.
public interface StudentService {
    public List<Student> findAll();
    public Student findById(int id);
    public Student save(Student student);
    public void deleteById(int id);
}
