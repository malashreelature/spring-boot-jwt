package com.springsecurity.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name="Employee_info")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String email;
    private String department;
    private LocalDate dob;
    @Transient
    private Integer age;

    public Employee(Long id, String name, String email, String department, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.dob = dob;

    }
    public Employee(){

    }

    public Employee(String name, String email, String department, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.dob = dob;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }




}
