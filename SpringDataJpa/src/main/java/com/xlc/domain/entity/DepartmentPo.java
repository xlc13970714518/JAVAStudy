package com.xlc.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity(name = "department")
@Accessors(chain = true)
public class DepartmentPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "department_name")
    private String departmentName;
}
