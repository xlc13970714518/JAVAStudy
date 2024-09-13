package com.xlc.domain.repository;

import com.xlc.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * jpa关键词查询是通过方法名称关键字的搭配，底层生成sql的方式来实现与数据库的交互，其关键词的搭配又很多方式，
 * 基本能覆盖表查询的所又情况，其余关键字查询可以自行百度；
 *
 *jpa同样支持写sql语句来操作数据，sql 有两种呈现形式：
 * JPQL 形式的 sql 语句，from 后面是以类名呈现的。
 * 原生的 sql 语句，需要使用 nativeQuery = true 指定使用原生 sql
 *
 * */

public interface EmployeeDao extends JpaRepository <Employee, Object>{
    List<Employee> findByLastName(String lastName);

    Long deleteById(Integer id);

    /**
     * 这是使用正常的 sql 语句去查询:name 是通过 @Param 注解去确定的*/
    @Query(nativeQuery = true, value = "select id, last_name,email,gender,department,birth\n" +
            "from employee where  gender = :gender ")
    List<Employee> getByGender(@Param("gender") String gender);

    /**
     * 使用的 JPQL 的 sql 形式 from 后面是类名
     * ?1 代表是的是方法中的第一个参数*/
    @Query(value = "select e\n" +
            "from employee e where  e.lastName=?1  and e.gender = ?2 ")
    List<Employee> getByLastNameAndGender(String lastName, String gender);
}
