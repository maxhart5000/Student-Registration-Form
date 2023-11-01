package com.hartcode.registration.controller;

import com.hartcode.registration.entity.Student;
import com.hartcode.registration.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class MainController {

    private final StudentService studentService;

    @Autowired
    public MainController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Redirect to the home page
    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

    // Display the home page
    @GetMapping("/home")
    public String homePage() {
        return "home-page";
    }

    // Display the student directory
    @GetMapping("/home/directory")
    public String studentDirectory(Model theModel) {
        // Get the students from the database
        List<Student> theStudents = studentService.findAll();

        // Order the list by last name
        theStudents.sort(Comparator.comparing(Student::getLastName));

        // Add students to the Spring model
        theModel.addAttribute("students", theStudents);

        return "student-directory";
    }

    // Show the form for adding a new student
    @GetMapping("/home/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // Create a model attribute to bind form data
        Student student = new Student();
        theModel.addAttribute("student", student);

        return "student-form";
    }

    // Save a student's information
    @PostMapping("/home/save")
    public String saveForm(@ModelAttribute("student") @Valid Student student, BindingResult result) {
        // Check if the student entry is valid; if not, return the student form
        if (result.hasErrors()) {
            return "student-form";
        }

        // Save the student
        studentService.save(student);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/home/directory";
    }

    // Show the form for updating a student's information
    @GetMapping("/home/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
        // Get the student from the service
        Student student = studentService.findById(theId);

        // Set the student in the model to prepopulate the form
        theModel.addAttribute("student", student);

        return "student-form";
    }

    // Delete a student by ID
    @GetMapping("/home/delete")
    public String delete(@RequestParam("studentId") int theId) {
        // Delete the student by ID
        studentService.deleteById(theId);

        // Use a redirect to prevent duplicate deletions
        return "redirect:/home/directory";
    }
}
