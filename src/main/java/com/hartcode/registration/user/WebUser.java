package com.hartcode.registration.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WebUser {

    // Username field with validation constraints
    @NotNull(message = "Please enter your username")
    @Size(min = 5, max = 10)
    private String userName;

    // Password field with validation constraints
    @NotNull(message = "Please enter your password")
    @Size(min = 5, max = 10)
    private String password;

    // First name field with validation constraints
    @NotNull(message = "Please enter your name")
    @Size(min = 2, max = 10)
    private String firstName;

    // Last name field with validation constraints
    @NotNull(message = "Please enter your name")
    @Size(min = 2, max = 10)
    private String lastName;

    // Email field with validation constraints
    @NotNull(message = "Please enter your email")
    @Email
    private String email;

    @NotNull(message="Please select your role")
    private String role;

    // Getters and setters for the fields

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Custom toString method for displaying the WebUser object

    @Override
    public String toString() {
        return "WebUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
