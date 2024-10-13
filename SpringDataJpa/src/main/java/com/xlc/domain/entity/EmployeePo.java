package com.xlc.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity(name = "employee")
@Accessors(chain = true)
public class EmployeePo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "department")
    private Long department;

    @Column(name = "birth")
    private String birth;
}
