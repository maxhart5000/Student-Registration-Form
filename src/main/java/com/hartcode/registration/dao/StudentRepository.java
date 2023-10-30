package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// Make use of JpaRepository to define default DAO
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
