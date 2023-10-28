package com.hartcode.registration.controller;

import com.hartcode.registration.entity.Student;
import com.hartcode.registration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class StudentRestController {
    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Expose "/students" and return a list of students from db
    @GetMapping("/students")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    // Expose "/students/{studentId}" to find a specific student based on student ID
    @GetMapping("/students/{studentId}")
    public Student findStudent(@PathVariable int studentId) {
        Student student = studentService.findById(studentId);

        if(student==null){
            throw new RuntimeException("Student ID not found - " + studentId);
        }

        return student;
    }

    // Add mapping for POST "/students" to add new student
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {

        // In case pass an id in JSON, set it to 0.
        // Force the save of a new student - oppose to update.
        student.setStudentId(0);

        Student theStudent = studentService.save(student);

        return theStudent;
    }

    // Add mapping for PUT "/students" to update existing student
    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student) {

         Student theStudent = studentService.save(student);

         return theStudent;
    }

    // Add mapping for DELETE "/student/{studentId}" - to delete student
    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId) {
        Student student = studentService.findById(studentId);

        // Check the ID is associated to a student before deleting
        if (student == null) {
            throw new RuntimeException("Student ID not found - " + studentId);
        }

        studentService.deleteById(studentId);
        return "Student " + studentId + " was deleted successfully";
    }
}
