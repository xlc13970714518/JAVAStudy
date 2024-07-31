package com.xlc.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import com.xlc.pojo.Employee;

@Mapper
public interface EmployeeMapper {
    List<Employee> selectAll();
}
