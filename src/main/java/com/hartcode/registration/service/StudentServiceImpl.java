package com.hartcode.registration.service;

import com.hartcode.registration.dao.StudentDao;
import com.hartcode.registration.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// This is the implementation of the StudentService interface.
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> findAll() {
        // Retrieve a list of all students from the data source via the StudentDao.
        return this.studentDao.findAll();
    }

    @Override
    public Student findById(int id) {
        // Find a student by their ID and handle the case where the student is not found.
        Optional<Student> result = Optional.ofNullable(studentDao.findById(id));
        Student student = null;

        if (result.isPresent()) {
            student = result.get();
        } else {
            // Could not find student
            throw new RuntimeException("Did not find student by ID - " + id);
        }
        return student;
    }

    @Transactional
    @Override
    public Student save(Student student) {
        // Save or update a student in the data source via the StudentDao.
        return this.studentDao.save(student);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        // Delete a student by their ID in the data source via the StudentDao.
        this.studentDao.deleteById(id);
    }
}
