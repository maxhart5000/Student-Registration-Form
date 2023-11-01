package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Student;

import java.util.List;

public interface StudentDao {

    public List<Student> findAll();

    public Student findById(int theId);

    public Student save(Student theEmployee);

    public void deleteById(int theId);
}
