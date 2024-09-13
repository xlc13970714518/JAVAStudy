package com.xlc;

import com.xlc.domain.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringDataJpaApplication {
    @Autowired
    static EmployeeDao employeeDao;

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    void init(){
        employeeDao = applicationContext.getBean(EmployeeDao.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
        System.out.println(employeeDao.findAll());

    }

}
