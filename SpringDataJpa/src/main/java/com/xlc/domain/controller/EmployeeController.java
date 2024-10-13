package com.xlc.domain.controller;

import com.xlc.demo.util.ApiResult;
import com.xlc.domain.service.EmployeeService;
import com.xlc.domain.vo.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping( "/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/detail")
    public EmployeeVo getEmployeeById(@Param("id") Long id){
        return employeeService.findEmployeeById(id);
    }
    
    @PostMapping("/save")
    ApiResult<Long> saveEmployee(@Valid EmployeeVo employeeVo){
        return ApiResult.success(employeeService.addOrUpdateEmployee(employeeVo));
    }
}
