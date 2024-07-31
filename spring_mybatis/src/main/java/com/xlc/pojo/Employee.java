package com.xlc.pojo;


import java.sql.Date;
import java.text.SimpleDateFormat;

public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private String department;
    private Date birth;

    public Integer getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public Employee(Integer id, String lastName, String email, String gender, String department, java.sql.Date birth) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = birth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public  String getBirth() {
        System.out.println(birth);
        return new SimpleDateFormat("yyyy-MM").format(birth);
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
