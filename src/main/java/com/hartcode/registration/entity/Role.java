package com.hartcode.registration.entity;

import jakarta.persistence.*;

// This class represents the Role entity and is mapped to the "role" table in the database.
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    // Default no-argument constructor.
    public Role() {
    }

    // Constructor that sets the name of the role.
    public Role(String name) {
        this.name = name;
    }

    // Define getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
