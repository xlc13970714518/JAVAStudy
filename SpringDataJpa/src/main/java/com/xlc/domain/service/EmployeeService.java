package com.xlc.domain.service;

import com.xlc.domain.vo.EmployeeVo;

public interface EmployeeService {
    Long addOrUpdateEmployee(EmployeeVo employeeVo);

    EmployeeVo findEmployeeById(Long id);
}


