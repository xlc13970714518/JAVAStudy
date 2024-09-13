package com.xlc;

import com.xlc.domain.repository.EmployeeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataJpaApplicationTests {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    void contextLoads() {
        System.out.println(employeeDao.findAll());
    }
    @Test
    void findByLastName(){
        System.out.println(employeeDao.findByLastName("张三"));
    }

    @Test
    void getByGender(){
        System.out.println(employeeDao.getByGender("1"));
    }

    @Test
    void getByLastNameAndGender(){
        System.out.println("测试JPQL" + employeeDao.getByLastNameAndGender("张三", "1"));
    }

}
