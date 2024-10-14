package com.xlc.domain.service;

import com.xlc.domain.vo.EmployeeVo;

import java.util.Map;

public interface EmployeeService {
    Long addOrUpdateEmployee(EmployeeVo employeeVo);

    EmployeeVo findEmployeeById(Long id);

    Map<String, Object> findEmployeeList(Integer pageNum, Integer pageSize);
}


