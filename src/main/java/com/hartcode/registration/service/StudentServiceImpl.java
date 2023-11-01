package com.hartcode.registration.service;

import com.hartcode.registration.dao.StudentDao;
import com.hartcode.registration.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> findAll() {
       return this.studentDao.findAll();
    }

    @Override
    public Student findById(int id) {
        Optional<Student> result = Optional.ofNullable(studentDao.findById(id));
        Student student = null;

        if(result.isPresent()) {
            student=result.get();
        } else {
            // Could not find employee
            throw new RuntimeException("Did not fnd employee by ID - " + id);
        }
        return student;
    }

    @Transactional
    @Override
    public Student save(Student student) {
        return this.studentDao.save(student);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        this.studentDao.deleteById(id);
    }
}
