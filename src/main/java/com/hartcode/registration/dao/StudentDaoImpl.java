package com.hartcode.registration.dao;

import com.hartcode.registration.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    // Define field for entityManager
    private EntityManager entityManager;

    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {

        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);

        return query.getResultList();
    }

    @Override
    public Student findById(int theId) {

        return entityManager.find(Student.class, theId);
    }

    @Override
    public Student save(Student theStudent) {
        return entityManager.merge(theStudent);
    }

    @Override
    public void deleteById(int theId) {
        Student student = entityManager.find(Student.class, theId);

        entityManager.remove(student);
    }
}
