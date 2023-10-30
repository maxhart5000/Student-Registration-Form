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
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService StudentService){
        this.studentService=StudentService;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home-page";
    }

    @GetMapping("/home/directory")
    public String studentDirectory(Model theModel) {

        // Get the Students from db
        List<Student> theStudents = studentService.findAll();

        // Order the list by last name
        theStudents.sort(Comparator.comparing(Student::getLastName));

        // Add to the spring model
        theModel.addAttribute("students", theStudents);

        return "student-directory";
    }

    @GetMapping("/home/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // Create model attribute to bind form data
        Student Student = new Student();

        theModel.addAttribute("student", Student);

        return "student-form";
    }

    @PostMapping("/home/save")
    public String saveForm(@ModelAttribute("student") @Valid Student Student, BindingResult result) {

        // Check if the student entry is valid - if not return the student form
        if(result.hasErrors()) {
            return "student-form";
        }

        // Save the Student
        studentService.save(Student);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/student-directory";
    }

    @GetMapping("/home/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId")int theId, Model theModel) {

        // Get the Student from the service
        Student Student = studentService.findById(theId);

        // Set the Student in the model to prepopulate the form
        theModel.addAttribute("student", Student);

        // Send over to our form
        return "student-form";
    }

    @GetMapping("/home/delete")
    public String delete(@RequestParam("studentId") int theId) {

        // Delete the Student by ID
        studentService.deleteById(theId);

        // Use a redirect to prevent duplicate deletions
        return "redirect:/student-directory";
    }
}


