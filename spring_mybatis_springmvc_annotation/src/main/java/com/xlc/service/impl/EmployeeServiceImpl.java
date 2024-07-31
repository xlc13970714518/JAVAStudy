package com.xlc.service.impl;

import com.xlc.mapper.EmployeeMapper;
import com.xlc.pojo.Employee;
import com.xlc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }
}
