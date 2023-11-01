package com.hartcode.registration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// This class represents the Student entity and is mapped to the "student" table in the database.
@Entity
@Table(name = "student")
public class Student {

    // Define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int studentId;

    @Column(name = "first_name")
    @NotEmpty(message = "Please enter your first name")
    @Size(min = 2, max = 10)
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Please enter your last name")
    @Size(min = 2, max = 10)
    private String lastName;

    @Column(name = "sex")
    @NotEmpty(message = "Please select your sex")
    private String sex;

    @Column(name = "major")
    @NotEmpty(message = "Please select your major")
    private String major;

    @Column(name = "email")
    @NotEmpty(message = "Please enter your email")
    @Email
    private String email;

    @Column(name = "phone_number")
    @NotEmpty(message = "Please enter your phone number")
    @Pattern(
            regexp = "^(\\+[0-9]{1,3}[-.\\s])?\\(?[0-9]{3}\\)?[-.\\s]?[0-9]{3}[-.\\s]?[0-9]{4}$",
            message = "Invalid international phone number format"
    )
    private String phoneNumber;

    // Define constructors

    // Default no-argument constructor.
    public Student() {
    }

    // Constructor that initializes a student with specified attributes.
    public Student(String firstName, String lastName, String sex, String major, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.major = major;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Define getters and setters

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", major='" + major + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
